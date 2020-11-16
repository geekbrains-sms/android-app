package com.geekbrains.geekbrainsprogect.data.model.entity.cross;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "user_role_cross",primaryKeys = {"userId", "roleId"})
public class UserRoleCrossRef {
    private final long userId;
    @ColumnInfo(index = true)
    private final long roleId;

    public UserRoleCrossRef(long userId, long roleId)
    {
        this.userId = userId;
        this.roleId = roleId;
    }

    public long getUserId() {
        return userId;
    }

    public long getRoleId() {
        return roleId;
    }
}
