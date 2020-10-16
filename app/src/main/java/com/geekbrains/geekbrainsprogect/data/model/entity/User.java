package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity(tableName = "users")
public class User {
    @PrimaryKey
    private long id;
    private String login;
    private String firstname;
    private String lastname;
    private String email;
    @Ignore
    private String password;
    private String phone;
    @Ignore
    private List<Role> roles;


    public User(String login, String firstname, String lasname, String email, String phone, String password, List<Role> roles) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lasname;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public List<Role> getRoles() {
        return roles;
    }


    public void setId(long id) {
        this.id = id;
    }
}


