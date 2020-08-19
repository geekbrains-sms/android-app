package com.geekbrains.geekbrainsprogect.data;

import com.google.gson.annotations.Expose;

public class User {
    @Expose
    private int id;
    @Expose
    private String login;
    @Expose
    private String password;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
