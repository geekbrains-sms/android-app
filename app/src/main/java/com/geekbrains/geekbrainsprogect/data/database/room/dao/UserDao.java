package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface UserDao extends BaseDao<User> {
    @Query("SELECT * FROM users")
    Flowable<List<User>> getAllUser();
    @Query("SELECT * FROM users WHERE users.id = :id")
    Flowable<User>getUserById(long id);
    @Query("DELETE FROM users")
    Completable deleteAllUsers();
}
