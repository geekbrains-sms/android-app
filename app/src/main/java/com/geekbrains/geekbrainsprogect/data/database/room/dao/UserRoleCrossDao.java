package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.join.UserRoleCrossRef;

import io.reactivex.Completable;
@Dao
public abstract class UserRoleCrossDao extends BaseDao<UserRoleCrossRef> {
    @Query("DELETE FROM user_role_cross WHERE userId = :id")
    public abstract Completable deleteByUser(long id);
    @Query("DELETE FROM user_role_cross WHERE roleId = :id")
    abstract Completable deleteByRole(long id);
}
