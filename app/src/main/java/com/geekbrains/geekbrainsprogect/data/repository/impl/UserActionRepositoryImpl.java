package com.geekbrains.geekbrainsprogect.data.repository.impl;

import androidx.constraintlayout.helper.widget.Flow;

import com.geekbrains.geekbrainsprogect.data.api.service.UserActionService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UserActionDao;
import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UserActionRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class UserActionRepositoryImpl implements UserActionRepository {
    UserActionService userActionService;
    UserActionDao userActionDao;
    @Inject
    public UserActionRepositoryImpl(UserActionService userActionService, UserActionDao userActionDao) {
        this.userActionService = userActionService;
        this.userActionDao = userActionDao;
    }

    @Override
    public Flowable<List<UserAction>> getAllUserActions() {
        return userActionDao.getAllUserActions();
    }

    @Override
    public Completable saveUserActionFromServerToDB() {
        return userActionService.getAllUserActions()
                .flatMapCompletable(x -> Completable.fromRunnable(()-> {
                    userActionDao.insertAll(x);}));
    }
}
