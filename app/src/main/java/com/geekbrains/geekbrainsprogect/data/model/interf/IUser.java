package com.geekbrains.geekbrainsprogect.data.model.interf;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;

import java.util.List;

public interface IUser {
   long getId();
   String getLogin();
   String getFirstName();
   String getLastname();
   String getPhone();
   String getPassword();
   String getEmail();
   List<Role>getRoles();
}
