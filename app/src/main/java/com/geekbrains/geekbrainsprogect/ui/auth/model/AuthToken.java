package com.geekbrains.geekbrainsprogect.ui.auth.model;

import com.google.gson.annotations.Expose;

public class AuthToken {
    @Expose
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
