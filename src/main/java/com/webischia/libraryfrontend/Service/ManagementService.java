package com.webischia.libraryfrontend.Service;

import com.webischia.libraryfrontend.Model.Author;

import java.util.List;

public interface ManagementService {

    void addAuthor(Author author,String token);
    void deleteAuthor(int id,String token);
    Author showAuthor(int id,String token);
    Author[] getAllAuthors(String token);
}
