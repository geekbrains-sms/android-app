package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;


import java.util.List;

import io.reactivex.Observable;


public interface UserActionRepository {
    Observable<List<UserAction>> getAllUserActions();
    Observable<List<UserAction>>getUserActionByProductId(long id);
    Observable<List<UserAction>>getUserActionByAuthor(String author);
    Observable<List<UserAction>>getUserActionByData(String data);
}
