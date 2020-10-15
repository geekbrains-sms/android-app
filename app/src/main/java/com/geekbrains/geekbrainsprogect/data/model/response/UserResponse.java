package com.geekbrains.geekbrainsprogect.data.model.response;

import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {
    private List<User> users;

    public List<User> getUsers() {
        if(users == null)
        {
            users = new ArrayList<>();
        }
        return users;
    }
}
