package com.geekbrains.geekbrainsprogect.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.geekbrains.geekbrainsprogect.ui.base.Item;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Parcelable, Item {

    private long id;
    private String login;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private List<Role> roles;


    public User(String login, String firstname, String lasname, String email, String phone, String password, List<Role>roles)
    {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lasname;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.password = password;
    }
    public User(Parcel parcel)
    {
        String [] data = new String[6];
        parcel.readStringArray(data);
        login = data[0];
        firstname = data[1];
        lastname = data[2];
        email = data[3];
        phone = data[4];
        password = data[5];
        id = parcel.readLong();
        parcel.readList(roles, Role.class.getClassLoader());
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login ='" + login + '\'' +
                ", fullname='" + getFullname() + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roles=" + roles +
                '}';
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{login, firstname, lastname, email, phone, password});
        dest.writeLong(id);
        dest.writeList(roles);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, firstname, lastname, email, password, phone, roles);
    }

    @Override
    public String getItemName() {
        return login;
    }
}
