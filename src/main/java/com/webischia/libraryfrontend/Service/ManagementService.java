package com.webischia.libraryfrontend.Service;

import com.webischia.libraryfrontend.Model.*;

import java.util.List;

public interface ManagementService {

    void addAuthor(Author author,String token);
    void deleteAuthor(int id,String token);
    void updateAuthor(Author author,String token);
    Author showAuthor(int id,String token);
    Author[] getAllAuthors(String token);

    void addSubject(Subject subject,String token);
    void deleteSubject(int id,String token);
    void updateSubject(Subject subject,String token);
    Subject showSubject(int id,String token);
    Subject[] getAllSubjects(String token);

    void addItemType(ItemType itemType,String token);
    void deleteItemType(int id,String token);
    void updateItemType(ItemType itemType,String token);
    ItemType showItemType(int id,String token);
    ItemType[] getAllTypes(String token);

    void addItem(Items items,String token);
    void deleteItem(int id,String token);
    void updateItem(Items items,String token);
    Items showItem(int id,String token);
    Items[] getAllItems(String token);

    void addPublisher(Publisher publisher,String token);
    void deletePublisher(int id,String token);
    void updatePublisher(Publisher publisher,String token);
    Publisher showPublisher(int id,String token);
    Publisher[] getAllPublisher(String token);

    Items[]searchItemKeyword(String keyword,String token);

    Items[] searchbyPost(Search search,String token);


}
