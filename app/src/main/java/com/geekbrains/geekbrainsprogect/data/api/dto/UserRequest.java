package com.geekbrains.geekbrainsprogect.data.api.dto;


import com.google.gson.annotations.Expose;

public class UserRequest {
    @Expose
   String username;
    @Expose
   String password;

    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
