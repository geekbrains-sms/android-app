package com.geekbrains.geekbrainsprogect.data.mapper.impl;

import com.geekbrains.geekbrainsprogect.data.api.dto.UserDTO;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.UserMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.model.interf.IUser;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;

import java.util.List;

public class UserMapperImpl implements UserMapper {

    @Override
    public UserModel toModel(IUser object) {
        return null;
    }

    @Override
    public UserDTO toDto(IUser object) {
        return null;
    }

    @Override
    public User toEntity(IUser object) {
        return null;
    }

    @Override
    public List<UserModel> toModelList(List<? extends IUser> list) {
        return null;
    }

    @Override
    public List<UserDTO> toDtoList(List<? extends IUser> list) {
        return null;
    }

    @Override
    public List<User> toEntityList(List<? extends IUser> list) {
        return null;
    }
}
