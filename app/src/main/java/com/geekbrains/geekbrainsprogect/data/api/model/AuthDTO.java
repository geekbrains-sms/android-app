package com.geekbrains.geekbrainsprogect.data.api.model;

import com.google.gson.annotations.Expose;

public class AuthDTO {
    @Expose
    String username;
    @Expose
    String password;

    public AuthDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
