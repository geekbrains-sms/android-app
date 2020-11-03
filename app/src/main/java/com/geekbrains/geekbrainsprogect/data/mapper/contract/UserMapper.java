package com.geekbrains.geekbrainsprogect.data.mapper.contract;

import com.geekbrains.geekbrainsprogect.data.api.dto.UserDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.model.interf.IUser;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;

public interface UserMapper extends BaseMapper<UserDTO, User, UserModel, IUser> {
}
