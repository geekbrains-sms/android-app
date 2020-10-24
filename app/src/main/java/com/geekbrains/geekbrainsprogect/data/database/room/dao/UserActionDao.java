package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
@Dao
public abstract class UserActionDao extends BaseDao<UserAction> {
    @Query("SELECT * FROM user_action")
    abstract Flowable<List<UserAction>> getAllUserActions();
    @Query("SELECT * FROM user_action WHERE user_action.id = :id")
    abstract Flowable<UserAction>getUserActionById(long id);
    @Query("SELECT * FROM user_action WHERE user_action.productId = :id")
    abstract Flowable<UserAction>getUserActionByProductId(long id);
    @Query("DELETE FROM user_action")
    abstract Completable deleteAllUserActions();
}
