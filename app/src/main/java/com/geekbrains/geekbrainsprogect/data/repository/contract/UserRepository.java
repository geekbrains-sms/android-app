package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.model.response.UserResponse;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;

public interface UserRepository {
    Single<UserResponse> getActualUsers();
    Single<User>getUserById(long id);
    Single<ResponseBody>editUser(long id, User user);
    Single<Response<User>>addUser(User user);
    Single<ResponseBody>deleteUser(long id);
}
