package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.geekbrains.geekbrainsprogect.data.model.interf.IUser;

import java.util.List;
@Entity(tableName = "users")
public class User implements IUser {
    @PrimaryKey
    @ColumnInfo(name = "userId", index = true)
    public long id;
    public String login;
    public String firstname;
    public String lastname;
    public String email;
    public String phone;
    @Ignore
    public List<Role> roles;


    public User(long id, String login, String firstname, String lastname, String email, String phone, List<Role> roles) {
        this.id = id;
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }
    public User(long id, String login, String firstname, String lastname, String email, String phone) {
        this.id = id;
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String getFirstName() {
        return firstname;
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

    @Override
    public String getPassword() {
        return null;
    }

    public List<Role> getRoles() {
        return roles;
    }
    public void setId(long id) {
        this.id = id;
    }
}


