package com.geekbrains.geekbrainsprogect.domain.interactor.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface UserActionInteractor {
    Completable saveUserActionFromServerToBD();
    Flowable<List<UserAction>> getUserActionsFromBD();
}
