package com.geekbrains.geekbrainsprogect.data.model.entity.join;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;

@Entity(tableName = "user_role_cross",primaryKeys = {"userId", "roleId"})
public class UserRoleCrossRef {
    public final long userId;
    public final long roleId;

    public UserRoleCrossRef(long userId, long roleId)
    {
        this.userId = userId;
        this.roleId = roleId;
    }

}
