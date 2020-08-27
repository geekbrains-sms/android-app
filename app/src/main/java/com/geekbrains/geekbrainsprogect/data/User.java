package com.geekbrains.geekbrainsprogect.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @Expose
    private int id;
    @Expose
    @SerializedName(value = "username")
    private String login;
    @Expose
    private String fullname;
    private String password;
    @Expose
    private String email;
    @Expose
    private String phone;
    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }
}
