package com.webischia.libraryfrontend.Service;

import com.webischia.libraryfrontend.Model.City;
import com.webischia.libraryfrontend.Model.User;
import com.webischia.libraryfrontend.Model.UserAdressDTO;
import com.webischia.libraryfrontend.Model.UserToken;

public interface UserService {
    UserToken loginUser(String uname, String password);
    UserAdressDTO register(UserAdressDTO dto);
    City[] getAllCities();
}
