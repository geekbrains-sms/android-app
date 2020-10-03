package com.geekbrains.geekbrainsprogect.data;

import com.google.gson.annotations.Expose;

import java.util.List;

public class User {
    @Expose
    private int id;
    @Expose
    private String username;
    @Expose
    private String email;
    @Expose
    private String phone;
    @Expose
    private List<Role> roles;

    private String firstname;
    private String lasname;

    public int getId() {
        return id;
    }
    public String getLogin() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return firstname + " " + lasname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLasname() {
        return lasname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLasname(String lasname) {
        this.lasname = lasname;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullname='" + getFullname() + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roles=" + roles +
                '}';
    }

    public List<Role> getRoles() {
        return roles;
    }
}
