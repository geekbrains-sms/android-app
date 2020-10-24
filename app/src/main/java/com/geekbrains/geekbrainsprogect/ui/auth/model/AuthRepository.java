package com.geekbrains.geekbrainsprogect.ui.auth.model;

import com.geekbrains.geekbrainsprogect.data.api.ApiHelper;
import com.geekbrains.geekbrainsprogect.data.api.dto.AuthDTO;
import com.geekbrains.geekbrainsprogect.data.api.service.AuthService;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class AuthRepository {
    private AuthService authService;
    @Inject
    public AuthRepository(AuthService authService) {
        this.authService = authService;
    }

    public Single<Response<AuthToken>> postToServer(String login, String password)
    {
        return authService.authUser(new AuthDTO(login, password));
    }
}
