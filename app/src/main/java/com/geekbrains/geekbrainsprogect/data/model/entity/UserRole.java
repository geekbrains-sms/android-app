package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.geekbrains.geekbrainsprogect.data.model.entity.cross.UserRoleCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.interf.IUser;

import java.util.List;

public class UserRole implements IUser {
    @Embedded
    private final User user;
    @Relation(parentColumn = "userId",
            entityColumn = "roleId",
            associateBy = @Junction(UserRoleCrossRef.class))
    private final List<Role> roleList;

    public UserRole(User user, List<Role> roleList) {
        this.user = user;
        this.roleList = roleList;
    }

    @Override
    public long getId() {
        return user.getId();
    }

    @Override
    public String getLogin() {
        return user.getLogin();
    }

    @Override
    public String getFirstName() {
        return user.getFirstName();
    }

    @Override
    public String getLastname() {
        return user.getLastname();
    }

    @Override
    public String getPhone() {
        return user.getPhone();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public List<Role> getRoles() {
        return roleList;
    }
}
