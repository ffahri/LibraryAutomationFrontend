package com.webischia.libraryfrontend.Service;

import com.webischia.libraryfrontend.Model.UserToken;

public interface UserService {
    UserToken loginUser(String uname, String password);
}
