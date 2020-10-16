package com.geekbrains.geekbrainsprogect.data.model.entity.join;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;

@Entity(tableName = "user_role_join",
        primaryKeys = {"user_id", "role_id"},
        foreignKeys = {
                @ForeignKey(entity = User.class,
        parentColumns ={"id"},
        childColumns ={"user_id"}),
                @ForeignKey(entity= Role.class,
        parentColumns={"id"},
        childColumns={"role_id"})
        })
public class UserRoleJoin {
    public final long userId;
    public final long roleId;

    public UserRoleJoin(long userId, long roleId)
    {
        this.userId = userId;
        this.roleId = roleId;
    }

}
