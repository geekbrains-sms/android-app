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
    @Inject
    ApiHelper apiHelper;

    public AuthPresenter()
    {
        AppData.getAppComponent().inject(this);
    }

    public void registerNewUser(String login, String password)
    {
        if(correctLoginAndPassword(login, password))
        {
            Single<String> single = apiHelper.registerUser(login,password);
            Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(string -> {
                    Log.d(TAG, string + " register success");
                    }, throwable -> {
                        Log.e(TAG, throwable + " register error");
                    }
            );
        }
        else
        {
            getViewState().showToast(R.string.incorrectly_loggin_password);
        }
    }
    public void getAllUser()
    {
        Single<List<User>> single = apiHelper.requestAllUsers();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(userList ->{
            for(User user: userList)
            {
                Log.d(TAG, user.getLogin() + " : " + user.getPassword());
            }
        }, throwable -> {
            Log.e(TAG, Objects.requireNonNull(throwable.getMessage()));
        });
    }
    private boolean correctLoginAndPassword(String login, String password)
    {
        return login.trim().length() > 0 && password.trim().length() > 0;
    }
}
