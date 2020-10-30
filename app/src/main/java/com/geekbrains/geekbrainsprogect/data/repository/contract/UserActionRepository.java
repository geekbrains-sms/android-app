package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;


public interface UserActionRepository {
    Flowable<List<UserAction>> getAllUserActions();
    Completable saveUserActionFromServerToDB();
    Observable<List<UserAction>>getUserActionByProductId(long id);
    Observable<List<UserAction>>getUserActionByAuthor(String author);
    Observable<List<UserAction>>getUserActionByData(String data);
}
