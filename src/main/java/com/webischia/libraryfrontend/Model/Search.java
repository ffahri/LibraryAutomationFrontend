package com.webischia.libraryfrontend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search {

    String keyword;
    String publisherName;
    String authorName;
    int searchType;
    int authorID;
    int publisherID;
}
