package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface UserActionDao extends BaseDao<UserActionDao> {
    @Query("SELECT * FROM user_action")
    Flowable<List<UserAction>> getAllUserActions();
    @Query("SELECT * FROM user_action WHERE user_action.id = :id")
    Flowable<UserAction>getUserActionById(long id);
    @Query("SELECT * FROM user_action WHERE user_action.productId = :id")
    Flowable<UserAction>getUserActionByProductId(long id);
    @Query("DELETE FROM user_action")
    Completable deleteAllUserActions();
}
