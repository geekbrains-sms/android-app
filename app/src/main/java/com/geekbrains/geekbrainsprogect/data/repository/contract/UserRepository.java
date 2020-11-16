package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;

public interface UserRepository {
    Flowable<List<UserModel>> getUserList();
    Completable saveUsersFromServerToDB();
    Completable editUser(UserModel user);
    Completable addUser(UserModel user);
    Completable deleteUser(long id);
    Flowable<List<Role>> getRoleList();
}
