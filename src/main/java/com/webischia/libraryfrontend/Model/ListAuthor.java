package com.webischia.libraryfrontend.Model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ListAuthor implements Serializable {

    List<Author> authorList;
}
