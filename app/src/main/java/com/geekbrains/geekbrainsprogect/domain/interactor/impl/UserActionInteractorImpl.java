package com.geekbrains.geekbrainsprogect.domain.interactor.impl;

import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UserActionRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UserRepository;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.UserActionInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UserActionInteractorImpl implements UserActionInteractor {
    UserActionRepository userActionRepository;
    @Inject
    public UserActionInteractorImpl(UserActionRepository userActionRepository) {
        this.userActionRepository = userActionRepository;
    }

    @Override
    public Completable saveUserActionFromServerToBD() {
        return userActionRepository.saveUserActionFromServerToDB();
    }

    @Override
    public Flowable<List<UserAction>> getUserActionsFromBD() {
        return userActionRepository.getAllUserActions();
    }
}
