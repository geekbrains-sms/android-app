package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.join.UserRoleJoin;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface UserRoleDao extends BaseDao<UserRoleDao> {
    @Query("DELETE FROM user_role_join WHERE user_role_join.userId = :id")
    void deleteByUserId(long id);
    @Query("SELECT * FROM user_role_join WHERE user_role_join.userId = :id")
    Flowable<List<UserRoleJoin>> getByUserId(long id);
    @Query("DELETE FROM user_role_join")
    Completable deleteAll();
}
