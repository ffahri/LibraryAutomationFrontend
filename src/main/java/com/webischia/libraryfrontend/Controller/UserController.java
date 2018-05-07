package com.webischia.libraryfrontend.Controller;

import com.webischia.libraryfrontend.Model.UserToken;
import com.webischia.libraryfrontend.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user")
    private String userDash(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("User")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

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
            return "user/profile";
        }
        return "redirect:/index";

    }

    @RequestMapping("/user/search")
    private String userSearch(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("User")) {
            //System.out.println(UserInfo.getToken().getAccess_token()); this way faster than debugging
            model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername());

            // model.addAttribute("tickets",ticketList);
            return "user/search";
        }
        return "redirect:/index";

    }
}
