package com.geekbrains.geekbrainsprogect.data.api.model;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.interf.IUser;
import com.google.gson.annotations.Expose;

import java.util.List;

public class UserDTO implements IUser {
    @Expose
    private long id;
    @Expose
    private String login;
    @Expose(deserialize = false)
    private String password;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String phone;
    @Expose
    private String email;
    @Expose
    private List<Role> roles;

    public UserDTO(Long id, String login, String firstname, String lastname, String phone, String email, String password, List<Role> roles) {
        this.id = id;
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
