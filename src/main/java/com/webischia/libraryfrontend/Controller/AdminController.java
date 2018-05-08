package com.webischia.libraryfrontend.Controller;

import com.webischia.libraryfrontend.Model.Author;
import com.webischia.libraryfrontend.Model.User;
import com.webischia.libraryfrontend.Model.UserToken;
import com.webischia.libraryfrontend.Service.ManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    ManagementService managementService;

    public AdminController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @RequestMapping("/management")
    private String adminDash(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/profile")
    private String adminProfile(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/profile";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/search")
    private String adminSearch(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/search";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/author")
    private String authorDash(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());
            model.addAttribute("authors",managementService.getAllAuthors(UserInfo.getToken()));
            // model.addAttribute("tickets",ticketList);
            return "management/author/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/author/add")
    private String authorAdd(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());
            model.addAttribute("newauthor",new Author());
            // model.addAttribute("tickets",ticketList);
            return "management/author/add";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/author/add/new", method= RequestMethod.POST, params="action=add")
    private String addAuthorPost(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Author author) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user", UserInfo);
            managementService.addAuthor(author,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/author ";

        }
        return "redirect:/index";
    }


        @RequestMapping("/management/author/show/{id}")
    private String authorShow(HttpServletRequest request, Model model, @PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());
            model.addAttribute("author",managementService.showAuthor(id,UserInfo.getToken()));
            // model.addAttribute("tickets",ticketList);
            return "management/author/show";
        }
        return "redirect:/index";

    }



    @RequestMapping("/management/item")
    private String itemDash(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/item/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/item/add")
    private String itemAdd(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/item/add";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/item/show")
    private String itemShow(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/item/show";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/itemtype")
    private String itemTypeDash(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/itemtype/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/itemtype/add")
    private String itemTypeAdd(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/itemtype/add";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/itemtype/show")
    private String itemTypeShow(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/itemtype/show";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/publisher")
    private String publisherDash(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/publisher/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/publisher/add")
    private String publisherAdd(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/publisher/add";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/publisher/show")
    private String publisherShow(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/publisher/show";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/subject")
    private String subjectDash(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/subject/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/subject/add")
    private String subjectAdd(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/subject/add";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/subject/show")
    private String subjectShow(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/subject/show";
        }
        return "redirect:/index";

    }
}
