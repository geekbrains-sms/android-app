package com.geekbrains.geekbrainsprogect.ui.auth.model;

import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;

public class AuthData {
    private static UserModel currentUser;
    public static void createUser(String login)
    {
        currentUser = new UserModel(0,login, null,null,null,null, null);
    }

    public static UserModel getCurrentUser() {
        return currentUser;
    }

}
