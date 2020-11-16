package com.geekbrains.geekbrainsprogect.ui.auth.presenter;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.api.AuthenticationInterceptor;
import com.geekbrains.geekbrainsprogect.data.repository.impl.AuthRepositoryImpl;
import com.geekbrains.geekbrainsprogect.ui.auth.model.AuthData;
import com.geekbrains.geekbrainsprogect.data.api.model.AuthToken;
import com.geekbrains.geekbrainsprogect.ui.auth.view.AuthView;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Response;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView> {
    public static String TAG = "AuthPresenter";
    private AuthRepositoryImpl repository;
    private AuthenticationInterceptor interceptor;
    @Inject
    public AuthPresenter(AuthRepositoryImpl authRepositoryImpl, AuthenticationInterceptor authenticationInterceptor)
    {
        this.repository = authRepositoryImpl;
        this.interceptor = authenticationInterceptor;
    }
    public void signIn(String login, String password)
    {
        if(correctLoginAndPassword(login,password))
        {
            getViewState().showProgressBar(true);
            Single<Response<AuthToken>> single = repository.postToServer(login, password);
            Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(request ->{

               if(request.isSuccessful())
               {
                   assert request.body() != null;
                   AuthToken authToken = request.body();
                   Log.d(TAG, "Auth successes: token: " + authToken.getToken());
                   AppData.token = authToken.getToken();
                   interceptor.setAuthToken(authToken.getToken());
                   AuthData.createUser(login);
                   getViewState().startMainActivity();
               }
               else
               {
                   assert request.errorBody() != null;
                   Log.d(TAG, "Auth error: " + request.errorBody().string());
                   getViewState().showAlertDialog(request.errorBody().string());
                   getViewState().showProgressBar(false);
               }
            }, throwable -> {
                getViewState().showAlertDialog(throwable.toString());
                getViewState().showProgressBar(false);
                Log.e(TAG, throwable.toString());
            });
        }
    }

    private boolean correctLoginAndPassword(String login, String password)
    {
        return login.trim().length() > 0 && password.trim().length() > 0;
    }
}
