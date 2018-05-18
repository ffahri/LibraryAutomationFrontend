package com.webischia.libraryfrontend.Controller;

import com.webischia.libraryfrontend.Model.Borrows;
import com.webischia.libraryfrontend.Model.Search;
import com.webischia.libraryfrontend.Model.Stock;
import com.webischia.libraryfrontend.Model.UserToken;
import com.webischia.libraryfrontend.Service.ManagementService;
import com.webischia.libraryfrontend.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

@Controller
public class UserController {

    private final UserService userService;
    private final ManagementService managementService;

    public UserController(UserService userService, ManagementService managementService) {
        this.userService = userService;
        this.managementService = managementService;
    }

    @RequestMapping("/user")
    private String userDash(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("User")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            Borrows[] brw = managementService.getUsersActive(UserInfo.getUsername(),UserInfo.getToken());
            Timestamp[] rtn = new Timestamp[brw.length];
            for(int i = 0; i<brw.length;i++)
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(brw[i].getBorrowDate());
                cal.add(Calendar.DAY_OF_WEEK, 15);
                //ts.setTime(cal.getTime().getTime()); // or
                brw[i].setReturnDate(new Timestamp(cal.getTime().getTime()));

            }
            model.addAttribute("borrows",brw);//managementService.getUsersActive(UserInfo.getUsername(),UserInfo.getToken()));

           // model.addAttribute("tickets",ticketList);
            return "user/index";
        }
        return "redirect:/index";

    }




    @RequestMapping("/user/profile")
    private String userProfile(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("User")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "management/profile";
        }
        return "redirect:/index";

    }

  
    @RequestMapping("/user/borrow/uzat/{stockID}")
    private String uzat(HttpServletRequest request, Model model, @PathVariable int stockID)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("User")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());
            managementService.uzat(stockID,UserInfo.getToken());
            // model.addAttribute("tickets",ticketList);
            return "redirect:/user";
        }
        return "redirect:/index";

    }

    @RequestMapping("/user/search")
    private String UserSearch(HttpServletRequest request, Model model,@ModelAttribute Search search)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("User")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("search",new Search());
            if(search.getSearchType()==1) {
                model.addAttribute("items", managementService.searchItemKeyword(search.getKeyword(), UserInfo.getToken()));
                return "user/search";
            }
            else if (search.getSearchType() == 2) {
                model.addAttribute("authors",managementService.searchByAuthor(search.getKeyword(),UserInfo.getToken()));

                return "user/search-author";

            }
            else if(search.getSearchType() == 3) {
                model.addAttribute("publishers",managementService.searchByPublisher(search.getKeyword(),UserInfo.getToken()));

                return "user/search-publisher";
            }
            return "user/search";
        }
        return "redirect:/index";

    }
    @RequestMapping("/user/search-author")
    private String UserSearchbyAuthor(HttpServletRequest request, Model model,@ModelAttribute Search search)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("User")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("search",new Search());
            model.addAttribute("authors",managementService.searchByAuthor(search.getKeyword(),UserInfo.getToken()));
            System.out.println(search.getKeyword());
            return "user/search-author";
        }
        return "redirect:/index";

    }

    @RequestMapping("/user/search/author/{authorID}")
    private String itemsByAuthor(HttpServletRequest request, Model model,@PathVariable int authorID)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("User")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("search",new Search());
            //model.addAttribute("authors",managementService.searchByAuthor(search.getKeyword(),UserInfo.getToken()));
            //System.out.println(search.getKeyword());
            model.addAttribute("items",managementService.searchItemsByAuthor(authorID,UserInfo.getToken()));
            return "user/search";
        }
        return "redirect:/index";

    }
    @RequestMapping("/user/search/publisher/{publisherID}")
    private String itemsByPublisher(HttpServletRequest request, Model model,@PathVariable int publisherID)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("User")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("search",new Search());
            //model.addAttribute("authors",managementService.searchByAuthor(search.getKeyword(),UserInfo.getToken()));
            //System.out.println(search.getKeyword());
            model.addAttribute("items",managementService.searchItemsByPublisher(publisherID,UserInfo.getToken()));
            return "user/search";
        }
        return "redirect:/index";

    }
    @RequestMapping("/user/search-publisher")
    private String UserSearchbyISBN(HttpServletRequest request, Model model,@ModelAttribute Search search)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("User")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("search",new Search());
            model.addAttribute("publishers",managementService.searchByPublisher(search.getKeyword(),UserInfo.getToken()));

            return "user/search-publisher";
        }
        return "redirect:/index";

    }
    @RequestMapping("/user/item/show/{id}")
    private String itemShow(HttpServletRequest request, Model model,@PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("User")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("newitem",managementService.showItem(id,UserInfo.getToken()));
            model.addAttribute("types",managementService.getAllTypes(UserInfo.getToken()));
            model.addAttribute("publishers",managementService.getAllPublisher(UserInfo.getToken()));
            model.addAttribute("authors",managementService.getAllAuthors(UserInfo.getToken()));
            model.addAttribute("subjects",managementService.getAllSubjects(UserInfo.getToken()));
            model.addAttribute("itemdto",managementService.showItemDTO(id,UserInfo.getToken()));
            model.addAttribute("stocks",managementService.showAllItemStock(id,UserInfo.getToken()));
            model.addAttribute("borrow.status",new String("Availabile"));
            return "user/item/show";
        }
        return "redirect:/index";
    }
        @RequestMapping("/user/item/showstock/{stockID}")
        private String showStock(HttpServletRequest request, Model model,@PathVariable int stockID)
        {
            UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
            if(UserInfo != null && UserInfo.getAccess().equals("User")) {
                model.addAttribute("user",UserInfo);
                Stock tmp = managementService.showStock(stockID,UserInfo.getToken());
                model.addAttribute("item",managementService.showItem(tmp.getItemID(),UserInfo.getToken()));
                model.addAttribute("stock",tmp);
                return "user/item/showStock";
            }
            return "redirect:/index";
        }
    }


