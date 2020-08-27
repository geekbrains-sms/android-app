package com.geekbrains.geekbrainsprogect.ui.auth.presenter;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.auth.view.AuthView;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.data.api.ApiHelper;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;

import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView> {
    private String TAG = "AuthPresenter";

    public AuthPresenter()
    {
        AppData.getAppComponent().inject(this);
    }
    public void signIn(String login, String password)
    {
        AppData.setApiHelper(new ApiHelper(login,password));
        if(correctLoginAndPassword(login,password))
        {
            Single<User> single = AppData.getApiHelper().getUser(login);
            Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(user ->{
               Log.d(TAG, "Auth successful: " + user.getLogin() + ":" + user.getPassword());
               AppData.setCurrentUser(user);
               getViewState().startMainActivity();
            }, throwable -> {
                getViewState().showAlertDialog(throwable.toString());
                Log.e(TAG, throwable.toString());
            });
        }
    }

    private boolean correctLoginAndPassword(String login, String password)
    {
        return login.trim().length() > 0 && password.trim().length() > 0;
    }
}
