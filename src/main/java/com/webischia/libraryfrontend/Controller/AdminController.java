package com.webischia.libraryfrontend.Controller;

import com.webischia.libraryfrontend.Model.*;
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
    @RequestMapping("/management/author/delete/{id}")
    private String deleteAuthor(HttpServletRequest request, Model model, @PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());
            managementService.deleteAuthor(id,UserInfo.getToken());
            // model.addAttribute("tickets",ticketList);
            return "redirect:/management/author";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/author/edit/", method= RequestMethod.POST, params="action=edit")
    private String editAuthor(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Author author) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user", UserInfo);
            managementService.updateAuthor(author,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/author ";

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
            model.addAttribute("newitem",new Items());
            // model.addAttribute("tickets",ticketList);
            return "management/item/add";
        }
        return "redirect:/index";
    }
    @RequestMapping(value="/management/item/add/new", method= RequestMethod.POST, params="action=add")
    private String addItemPost(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Items item) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user", UserInfo);
            managementService.addItem(item,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/author ";

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

    @RequestMapping("/management/item/delete/{id}")
    private String deleteItem(HttpServletRequest request, Model model, @PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());
            managementService.deleteItem(id,UserInfo.getToken());
            // model.addAttribute("tickets",ticketList);
            return "redirect:/management/item";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/item/edit/", method= RequestMethod.POST, params="action=edit")
    private String editItem(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Items items) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user", UserInfo);
            managementService.updateItem(items,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/item ";

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
            model.addAttribute("types",managementService.getAllTypes(UserInfo.getToken()));
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
            model.addAttribute("newtype",new ItemType());
            // model.addAttribute("tickets",ticketList);
            return "management/itemtype/add";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/itemtype/add/new", method= RequestMethod.POST, params="action=add")
    private String addItemType(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute ItemType itemType) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user", UserInfo);
            managementService.addItemType(itemType,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/itemtype ";

        }
        return "redirect:/index";
    }

    @RequestMapping("/management/itemtype/show/{id}")
    private String itemTypeShow(HttpServletRequest request, Model model,@PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            model.addAttribute("itemtype",managementService.showItemType(id,UserInfo.getToken()));
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());
            // model.addAttribute("tickets",ticketList);
            return "management/itemtype/show";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/itemtype/delete/{id}")
    private String deleteItemType(HttpServletRequest request, Model model, @PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());
            managementService.deleteItemType(id,UserInfo.getToken());
            // model.addAttribute("tickets",ticketList);
            return "redirect:/management/itemtype";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/itemtype/edit/", method= RequestMethod.POST, params="action=edit")
    private String editItemType(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute ItemType itemType) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user", UserInfo);
            managementService.updateItemType(itemType,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/itemtype ";

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
            model.addAttribute("publishers",managementService.getAllPublisher(UserInfo.getToken()));
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
            model.addAttribute("newpublisher",new Publisher());
            // model.addAttribute("tickets",ticketList);
            return "management/publisher/add";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/publisher/add/new", method= RequestMethod.POST, params="action=add")
    private String addPublisherPost(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Publisher publisher) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user", UserInfo);
            managementService.addPublisher(publisher,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/publisher ";

        }
        return "redirect:/index";
    }

    @RequestMapping("/management/publisher/show/{id}")
    private String publisherShow(HttpServletRequest request, Model model,@PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername())
            model.addAttribute("publisher",managementService.showPublisher(id,UserInfo.getToken()));
            // model.addAttribute("tickets",ticketList);
            return "management/publisher/show";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/publisher/delete/{id}")
    private String deletePublisher(HttpServletRequest request, Model model, @PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());
            managementService.deletePublisher(id,UserInfo.getToken());
            // model.addAttribute("tickets",ticketList);
            return "redirect:/management/publisher";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/publisher/edit/", method= RequestMethod.POST, params="action=edit")
    private String editPublisher(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Publisher publisher) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user", UserInfo);
            managementService.updatePublisher(publisher,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/publisher ";

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
            model.addAttribute("subjects",managementService.getAllSubjects(UserInfo.getToken()));
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
            model.addAttribute("newsubject",new Subject());
            // model.addAttribute("tickets",ticketList);
            return "management/subject/add";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/subject/add/new", method= RequestMethod.POST, params="action=add")
    private String addSubjectPost(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Subject subject) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user", UserInfo);
            managementService.addSubject(subject,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/subject ";

        }
        return "redirect:/index";
    }

    @RequestMapping("/management/subject/show/{id}")
    private String subjectShow(HttpServletRequest request, Model model,@PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());
            model.addAttribute("subject",managementService.showSubject(id,UserInfo.getToken()));
            // model.addAttribute("tickets",ticketList);
            return "management/subject/show";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/subject/delete/{id}")
    private String deleteSubject(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Subject subject,@PathVariable int id) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user", UserInfo);
            managementService.deleteSubject(id,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/subject ";

        }
        return "redirect:/index";
    }

    @RequestMapping(value="/management/subject/edit/", method= RequestMethod.POST, params="action=edit")
    private String editSubject(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Subject subject) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user", UserInfo);
            managementService.updateSubject(subject,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/subject ";

        }
        return "redirect:/index";
    }

}
