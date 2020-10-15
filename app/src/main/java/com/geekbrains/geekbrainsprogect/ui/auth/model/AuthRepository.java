package com.geekbrains.geekbrainsprogect.ui.auth.model;

import com.geekbrains.geekbrainsprogect.data.api.ApiHelper;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;

import io.reactivex.Single;
import retrofit2.Response;

public class AuthRepository {
    private ApiHelper apiHelper;

    public AuthRepository()
    {
        apiHelper = AppData.getApiHelper();
    }

    public Single<Response<AuthToken>> postToServer(String login, String password)
    {
        return apiHelper.authUser(login, password);
    }
}
