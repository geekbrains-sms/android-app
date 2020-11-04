package com.geekbrains.geekbrainsprogect.data.mapper.impl;

import com.geekbrains.geekbrainsprogect.data.api.model.UserDTO;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.UserMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.model.interf.IUser;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserMapperImpl implements UserMapper {

    @Override
    public UserModel toModel(IUser object) {
        long id = object.getId();
        String login = object.getLogin();
        String firstname = object.getFirstName();
        String lastname = object.getLastname();
        String phone = object.getPhone();
        String email = object.getEmail();
        List<Role> roles = object.getRoles();

        return new UserModel(id,login, firstname, lastname, email, phone, roles);
    }

    @Override
    public UserDTO toDto(IUser object) {
        long id = object.getId();
        String login = object.getLogin();
        String firstname = object.getFirstName();
        String lastname = object.getLastname();
        String phone = object.getPhone();
        String email = object.getEmail();
        String password = object.getPassword();
        List<Role> roles = object.getRoles();

        return new UserDTO(id, login, firstname, lastname, phone, email, password, roles);
    }

    @Override
    public User toEntity(IUser object) {
        long id = object.getId();
        String login = object.getLogin();
        String firstname = object.getFirstName();
        String lastname = object.getLastname();
        String phone = object.getPhone();
        String email = object.getEmail();
        List<Role> roles = object.getRoles();
        return new User(id, login, firstname, lastname, phone, email, roles);
    }

    @Override
    public List<UserModel> toModelList(List<? extends IUser> list) {
        List<UserModel>modelList = new ArrayList<>();
        for(IUser iUser : list)
        {
            modelList.add(toModel(iUser));
        }
        return modelList;
    }

    @Override
    public List<UserDTO> toDtoList(List<? extends IUser> list) {
        List<UserDTO>dtoList = new ArrayList<>();
        for(IUser iUser : list)
        {
            dtoList.add(toDto(iUser));
        }
        return dtoList;
    }

    @Override
    public List<User> toEntityList(List<? extends IUser> list) {
        List<User>userList = new ArrayList<>();
        for(IUser iUser : list)
        {
            userList.add(toEntity(iUser));
        }
        return userList;
    }
}
