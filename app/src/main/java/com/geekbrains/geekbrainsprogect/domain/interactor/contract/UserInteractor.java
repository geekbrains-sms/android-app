package com.geekbrains.geekbrainsprogect.domain.interactor.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface UserInteractor {
    Completable saveUserFromServerToDB();
    Flowable<List<UserModel>> getUserList();
    Completable addUser(UserModel userModel);
    Completable editUser(UserModel userMode);
    Completable deleteUser(UserModel userModel);
    Flowable<List<Role>> getAllRolesList();
}
