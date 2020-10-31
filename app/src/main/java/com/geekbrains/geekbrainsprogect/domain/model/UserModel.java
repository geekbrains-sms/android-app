package com.geekbrains.geekbrainsprogect.domain.model;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.interf.IUser;
import com.geekbrains.geekbrainsprogect.ui.base.Item;

import java.util.List;

public class UserModel implements Item, IUser {
    private long id;
    private String login;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private List<Role> roles;

    public UserModel(long id, String login, String firstname, String lastname, String email, String phone, List<Role> roles) {
        this.id = id;
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String getFirstName() {
        return firstname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getItemName() {
        return login + " " + firstname + " " + " " + lastname;
    }
}
