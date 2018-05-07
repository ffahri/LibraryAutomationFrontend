package com.webischia.libraryfrontend.Controller;

import com.webischia.libraryfrontend.Model.User;
import com.webischia.libraryfrontend.Model.UserToken;
import com.webischia.libraryfrontend.Service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    private final UserService userService;
    DefaultListableBeanFactory beanFactory =
            new DefaultListableBeanFactory();

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"","/","/index"})
    private String getIndex()
    {
        return "index";
    }

    @RequestMapping({"/register","/register/"})
    private String register(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }
    @RequestMapping({"/login","/login/"})
    private String login(Model model)
    {
        model.addAttribute("user", new UserToken());
        return "login";
    }
    @PostMapping
    @RequestMapping("/register/try")
    private String getRegister(@Valid @ModelAttribute User user, HttpServletRequest request , BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            throw new RuntimeException("HATALI VERİ GİRİLDİ");
        //ShoppingCart cart = (ShoppingCart)request.getSession().setAttribute("cart",value);
        //UserToken test = (UserToken)request.getSession().setAttribute()
        //System.out.println(user.getPassword());
       //DÜZELT - > //apiService.register(user.getName(),user.getSurname(),user.getEmail(),user.getPassword());

        return "redirect:/index";

    }
    @PostMapping
    @RequestMapping("/login/try")
    private String getLogin(@ModelAttribute UserToken user, HttpServletRequest request)
    {
        //ShoppingCart cart = (ShoppingCart)request.getSession().setAttribute("cart",value);
        //UserToken test = (UserToken)request.getSession().setAttribute()
        //System.out.println(user.getPassword());
        UserToken userToken = userService.loginUser(user.getUsername(),user.getPassword());

        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("(?<=\\[).+?(?=\\])")
                .matcher(Jwts.parser()
                        .setSigningKey(DatatypeConverter.parseBase64Binary("TWFZemtTam1relBDNTdM"))
                        .parseClaimsJws(userToken.getToken()).toString());
        while (m.find()) {
            allMatches.add(m.group());
        }
        userToken.setAccess(allMatches.get(2));
        if(allMatches.get(2).equals("User")) {
            userToken.setAccess_id(1);
            request.getSession().setAttribute("userinfo",userToken); //ALL HAIL THE HTTPSESSION \v/
            return "redirect:/user";
        }
        else if(allMatches.get(2).equals("Admin")) {
            userToken.setAccess_id(2);
            request.getSession().setAttribute("userinfo",userToken); //ALL HAIL THE HTTPSESSION \v/
            return "redirect:/management";

        }

        //request.getSession().setAttribute("userinfo",userToken); //ALL HAIL THE HTTPSESSION \v/
        return "redirect:index";

    }

    @RequestMapping("/logout")
    private String logout(HttpServletRequest request,Model model)
    {
        request.getSession().invalidate();
        model.addAttribute("status","logout yaptınız");//todo güzel index sayfası
        return "redirect:/index";
    }
}