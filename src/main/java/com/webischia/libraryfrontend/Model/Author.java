package com.webischia.libraryfrontend.Model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Author implements Serializable {
    int authorID;
    String authorName;
    String authorLastName;

    public Author() {
    }

    public Author(int authorID, String authorName, String authorLastName) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.authorLastName = authorLastName;
    }
}
