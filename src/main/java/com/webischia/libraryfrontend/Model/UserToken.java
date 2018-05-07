package com.webischia.libraryfrontend.Model;

import lombok.Data;

@Data
public class UserToken {
    private String username;
    private String password;
    private String token;
    private String access;
    private int access_id;

}
