package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.model.entity.UserRole;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public abstract class UserDao extends BaseDao<User> {
    @Query("SELECT * FROM users")
    public abstract Flowable<List<UserRole>> getAllUser();
    @Query("SELECT * FROM users WHERE userId = :id")
    public abstract Flowable<User>getUserById(long id);
    @Query("DELETE FROM users")
    public abstract void deleteAllUsers();
    @Query("DELETE FROM users WHERE userId = :id")
    public abstract void deleteUserById(long id);
}
