package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.api.dto.AuthDTO;
import com.geekbrains.geekbrainsprogect.data.api.service.AuthService;
import com.geekbrains.geekbrainsprogect.data.repository.contract.AuthRepository;
import com.geekbrains.geekbrainsprogect.ui.auth.model.AuthToken;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class AuthRepositoryImpl implements AuthRepository {
    private AuthService authService;
    @Inject
    public AuthRepositoryImpl(AuthService authService) {
        this.authService = authService;
    }

    public Single<Response<AuthToken>> postToServer(String login, String password)
    {
        return authService.authUser(new AuthDTO(login, password));
    }
}
