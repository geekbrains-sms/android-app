package com.geekbrains.geekbrainsprogect.domain.interactor.impl;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.repository.contract.RoleRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UserRepository;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.UserInteractor;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UserInteractorImpl implements UserInteractor {
    UserRepository userRepository;
    @Inject
    public UserInteractorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Completable saveUserFromServerToDB() {
        return userRepository.saveUsersFromServerToDB();
    }

    @Override
    public Flowable<List<UserModel>> getUserList() {
        return userRepository.getUserList();
    }

    @Override
    public Completable addUser(UserModel userModel) {
        return userRepository.addUser(userModel);
    }

    @Override
    public Completable editUser(UserModel userModel) {
        return userRepository.editUser(userModel);
    }

    @Override
    public Completable deleteUser(UserModel userModel) {
        return userRepository.deleteUser(userModel.getId());
    }

    @Override
    public Flowable<Role> getAllRolesList() {
        return null;
    }
}
