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
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
             model.addAttribute("user",UserInfo);

             return "management/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/profile")
    private String adminProfile(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
 
             return "management/profile";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/search")
    private String adminSearch(HttpServletRequest request, Model model,@ModelAttribute Search search)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("search",new Search());
            if(search.getSearchType()==1) {
                model.addAttribute("items", managementService.searchItemKeyword(search.getKeyword(), UserInfo.getToken()));
                return "management/search";
            }
            else if (search.getSearchType() == 2) {
                model.addAttribute("authors",managementService.searchByAuthor(search.getKeyword(),UserInfo.getToken()));

                return "management/search-author";

            }
            else if(search.getSearchType() == 3) {
                model.addAttribute("publishers",managementService.searchByPublisher(search.getKeyword(),UserInfo.getToken()));

                return "management/search-publisher";
            }
            return "management/search";
        }
        return "redirect:/index";

    }
    @RequestMapping("/management/search-author")
    private String adminSearchbyAuthor(HttpServletRequest request, Model model,@ModelAttribute Search search)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("search",new Search());
            model.addAttribute("authors",managementService.searchByAuthor(search.getKeyword(),UserInfo.getToken()));
            System.out.println(search.getKeyword());
            return "management/search-author";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/search/author/{authorID}")
    private String itemsByAuthor(HttpServletRequest request, Model model,@PathVariable int authorID)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("search",new Search());
            //model.addAttribute("authors",managementService.searchByAuthor(search.getKeyword(),UserInfo.getToken()));
            //System.out.println(search.getKeyword());
            model.addAttribute("items",managementService.searchItemsByAuthor(authorID,UserInfo.getToken()));
            return "management/search";
        }
        return "redirect:/index";

    }
    @RequestMapping("/management/search/publisher/{publisherID}")
    private String itemsByPublisher(HttpServletRequest request, Model model,@PathVariable int publisherID)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("search",new Search());
            //model.addAttribute("authors",managementService.searchByAuthor(search.getKeyword(),UserInfo.getToken()));
            //System.out.println(search.getKeyword());
            model.addAttribute("items",managementService.searchItemsByPublisher(publisherID,UserInfo.getToken()));
            return "management/search";
        }
        return "redirect:/index";

    }
    @RequestMapping("/management/search-publisher")
    private String adminSearchbyISBN(HttpServletRequest request, Model model,@ModelAttribute Search search)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("search",new Search());
            model.addAttribute("publishers",managementService.searchByPublisher(search.getKeyword(),UserInfo.getToken()));

            return "management/search-publisher";
        }
        return "redirect:/index";

    }
//    @RequestMapping(value="/management/search", method= RequestMethod.POST, params="action=search")
//    private String doSearch (@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Search search) {
//        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
//        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
//             model.addAttribute("user", UserInfo);
//             model.addAttribute("items",managementService.searchItemKeyword(search.getKeyword(),UserInfo.getToken()));
//            //managementService.updateAuthor(author,UserInfo.getToken());
//            //return "management/search ";
//                return "search";
//        }
//        else {
//            return "redirect:/index";
//        }
//    }

    @RequestMapping("/management/author")
    private String authorDash(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             model.addAttribute("authors",managementService.getAllAuthors(UserInfo.getToken()));
             return "management/author/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/author/add")
    private String authorAdd(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             model.addAttribute("newauthor",new Author());
             return "management/author/add";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/author/add/new", method= RequestMethod.POST, params="action=add")
    private String addAuthorPost(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Author author) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
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
             model.addAttribute("user",UserInfo);
             model.addAttribute("author",managementService.showAuthor(id,UserInfo.getToken()));
             return "management/author/show";
        }
        return "redirect:/index";

    }
    @RequestMapping("/management/author/delete/{id}")
    private String deleteAuthor(HttpServletRequest request, Model model, @PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             managementService.deleteAuthor(id,UserInfo.getToken());
             return "redirect:/management/author";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/author/edit/", method= RequestMethod.POST, params="action=edit")
    private String editAuthor(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Author author) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
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
             model.addAttribute("user",UserInfo);
             model.addAttribute("items",managementService.getAllItems(UserInfo.getToken()));
             return "management/item/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/item/add")
    private String itemAdd(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             model.addAttribute("types",managementService.getAllTypes(UserInfo.getToken()));
             model.addAttribute("publishers",managementService.getAllPublisher(UserInfo.getToken()));
             model.addAttribute("newitem",new Items());
             model.addAttribute("authors",managementService.getAllAuthors(UserInfo.getToken()));
             model.addAttribute("subjects",managementService.getAllSubjects(UserInfo.getToken()));
             model.addAttribute("itemdto",new ItemDTO());
             return "management/item/add";
        }
        return "redirect:/index";
    }
    @RequestMapping(value="/management/item/add/new", method= RequestMethod.POST, params="action=add")
    private String addItemPost(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Items item,@ModelAttribute ItemDTO itemDTO){
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user", UserInfo);
             item.setStockNo(item.getISBN());
            // System.out.println(listauthor.getAuthList().length);
            managementService.addItem(item,itemDTO,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/item ";

        }
        return "redirect:/index";
    }
    @RequestMapping(value="/management/item/edit", method= RequestMethod.POST, params="action=add")
    private String editItem(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Items item,@ModelAttribute ItemDTO itemDTO){
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user", UserInfo);
            item.setStockNo(item.getISBN());
            // System.out.println(listauthor.getAuthList().length);
            managementService.updateItem(item,itemDTO,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/item ";

        }
        return "redirect:/index";
    }
    @RequestMapping("/management/item/show/{id}")
    private String itemShow(HttpServletRequest request, Model model,@PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             model.addAttribute("newitem",managementService.showItem(id,UserInfo.getToken()));
            model.addAttribute("types",managementService.getAllTypes(UserInfo.getToken()));
            model.addAttribute("publishers",managementService.getAllPublisher(UserInfo.getToken()));
            model.addAttribute("authors",managementService.getAllAuthors(UserInfo.getToken()));
            model.addAttribute("subjects",managementService.getAllSubjects(UserInfo.getToken()));
            model.addAttribute("itemdto",managementService.showItemDTO(id,UserInfo.getToken()));
            model.addAttribute("stocks",managementService.showAllItemStock(id,UserInfo.getToken()));
            model.addAttribute("borrow.status",new String("Availabile"));
             return "management/item/show";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/item/delete/{id}")
    private String deleteItem(HttpServletRequest request, Model model, @PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             managementService.deleteItem(id,UserInfo.getToken());
             return "redirect:/management/item";
        }
        return "redirect:/index";

    }



    @RequestMapping("/management/itemtype")
    private String itemTypeDash(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             model.addAttribute("types",managementService.getAllTypes(UserInfo.getToken()));
             return "management/itemtype/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/itemtype/add")
    private String itemTypeAdd(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             model.addAttribute("newtype",new ItemType());
             return "management/itemtype/add";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/itemtype/add/new", method= RequestMethod.POST, params="action=add")
    private String addItemType(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute ItemType itemType) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
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
             model.addAttribute("user",UserInfo);
            model.addAttribute("itemtype",managementService.showItemType(id,UserInfo.getToken()));
              return "management/itemtype/show";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/itemtype/delete/{id}")
    private String deleteItemType(HttpServletRequest request, Model model, @PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             managementService.deleteItemType(id,UserInfo.getToken());
             return "redirect:/management/itemtype";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/itemtype/edit/", method= RequestMethod.POST, params="action=edit")
    private String editItemType(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute ItemType itemType) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
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
             model.addAttribute("user",UserInfo);
             model.addAttribute("publishers",managementService.getAllPublisher(UserInfo.getToken()));
             return "management/publisher/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/publisher/add")
    private String publisherAdd(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             model.addAttribute("newpublisher",new Publisher());
             return "management/publisher/add";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/publisher/add/new", method= RequestMethod.POST, params="action=add")
    private String addPublisherPost(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Publisher publisher) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
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
             model.addAttribute("user",UserInfo);
            //List<Ticket> ticketList = apiService.userGetOwnTickets(UserInfo.getToken().getAccess_token(),UserInfo.getUsername())
            model.addAttribute("publisher",managementService.showPublisher(id,UserInfo.getToken()));
             return "management/publisher/show";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/publisher/delete/{id}")
    private String deletePublisher(HttpServletRequest request, Model model, @PathVariable int id)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             managementService.deletePublisher(id,UserInfo.getToken());
             return "redirect:/management/publisher";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/publisher/edit/", method= RequestMethod.POST, params="action=edit")
    private String editPublisher(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Publisher publisher) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
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
             model.addAttribute("user",UserInfo);
             model.addAttribute("subjects",managementService.getAllSubjects(UserInfo.getToken()));
             return "management/subject/index";
        }
        return "redirect:/index";

    }

    @RequestMapping("/management/subject/add")
    private String subjectAdd(HttpServletRequest request, Model model)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
             model.addAttribute("user",UserInfo);
             model.addAttribute("newsubject",new Subject());
             return "management/subject/add";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/subject/add/new", method= RequestMethod.POST, params="action=add")
    private String addSubjectPost(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Subject subject) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
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
             model.addAttribute("user",UserInfo);
             model.addAttribute("subject",managementService.showSubject(id,UserInfo.getToken()));
             return "management/subject/show";
        }
        return "redirect:/index";

    }

    @RequestMapping(value="/management/subject/delete/{id}")
    private String deleteSubject(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Subject subject,@PathVariable int id) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
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
             model.addAttribute("user", UserInfo);
            managementService.updateSubject(subject,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/subject ";

        }
        return "redirect:/index";
    }
    @RequestMapping("/management/item/add/stock/{itemID}")
    private String stockAdd(HttpServletRequest request, Model model,@PathVariable int itemID)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("item",managementService.showItem(itemID,UserInfo.getToken()));
            Stock tmp = new Stock();
            tmp.setItemID(itemID);
            model.addAttribute("newstock",tmp);
            return "management/item/stockAdd";
        }
        return "redirect:/index";
    }
    @RequestMapping(value="/management/item/add/stock/new/", method= RequestMethod.POST, params="action=add")
    private String addStockPost(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Stock stock){
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user", UserInfo);
            managementService.addStock(stock,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/item/show/"+stock.getItemID();

        }
        return "redirect:/index";
    }
    @RequestMapping("/management/item/showstock/{stockID}")
    private String showStock(HttpServletRequest request, Model model,@PathVariable int stockID)
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user",UserInfo);
            Stock tmp = managementService.showStock(stockID,UserInfo.getToken());
            model.addAttribute("item",managementService.showItem(tmp.getItemID(),UserInfo.getToken()));
            model.addAttribute("stock",tmp);
            return "management/item/showStock";
        }
        return "redirect:/index";
    }
    @RequestMapping(value="/management/item/add/stock/edit", method= RequestMethod.POST)
    private String editStockPost(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Stock stock){
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user", UserInfo);
            managementService.editStock(stock,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/item/stockEdit ";

        }
        return "redirect:/index";
    }
    @RequestMapping(value="/management/item/stock/delete/{id}")
    private String deleteStock(@ModelAttribute UserToken user, HttpServletRequest request, Model model ,@PathVariable int id) {
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user", UserInfo);
            managementService.deleteStock(id,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/item ";

        }
        return "redirect:/index";
    }


    @RequestMapping("/management/borrow/odunc")
    private String oduncVer(HttpServletRequest request, Model model )
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("usrbrw",new Borrows());
            return "management/borrow/add";
        }
        return "redirect:/index";

    }
    @RequestMapping(value="/management/borrow/odunc/new", method= RequestMethod.POST, params="action=add")
    private String doOdunc(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Borrows borrowDTO){
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user", UserInfo);

            managementService.oduncAl(borrowDTO,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/ ";

        }
        return "redirect:/index";
    }

    @RequestMapping("/management/borrow/iade")
    private String iadeAl(HttpServletRequest request, Model model )
    {
        UserToken UserInfo = (UserToken)request.getSession().getAttribute("userinfo");
        if(UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user",UserInfo);
            model.addAttribute("usrbrw",new Borrows());
            return "management/borrow/iadeAl";
        }
        return "redirect:/index";

    }
    @RequestMapping(value="/management/borrow/iade/new", method= RequestMethod.POST, params="action=add")
    private String doIade(@ModelAttribute UserToken user, HttpServletRequest request, Model model , @ModelAttribute Borrows borrowDTO){
        UserToken UserInfo = (UserToken) request.getSession().getAttribute("userinfo");
        if (UserInfo != null && UserInfo.getAccess().equals("Admin")) {
            model.addAttribute("user", UserInfo);
            borrowDTO.setMail(UserInfo.getUsername() );
            managementService.iadeAl(borrowDTO,UserInfo.getToken());
            //apiService.userCreateMessage(UserInfo.getToken(),UserInfo.getUsername(),newTicketDTO.getMessageContext(),newTicketDTO.getId());
            return "redirect:/management/ ";

        }
        return "redirect:/index";
    }
}
