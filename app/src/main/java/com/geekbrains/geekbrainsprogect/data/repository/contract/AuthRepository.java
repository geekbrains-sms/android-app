package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.api.model.AuthToken;

import io.reactivex.Single;
import retrofit2.Response;

public interface AuthRepository {
    Single<Response<AuthToken>> postToServer(String login, String password);
}
