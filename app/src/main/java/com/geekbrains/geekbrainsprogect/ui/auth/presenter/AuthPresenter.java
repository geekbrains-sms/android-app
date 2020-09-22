package com.geekbrains.geekbrainsprogect.ui.auth.presenter;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.auth.model.AuthToken;
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
import okhttp3.ResponseBody;
import retrofit2.Response;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView> {
    public static String TAG = "AuthPresenter";

    public AuthPresenter()
    {
        AppData.getAppComponent().inject(this);
        AppData.setApiHelper(new ApiHelper());
    }
    public void signIn(String login, String password)
    {
        if(correctLoginAndPassword(login,password))
        {
            Single<Response<AuthToken>> single = AppData.getApiHelper().authUser(login, password);
            Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(request ->{

               if(request.isSuccessful())
               {
                   assert request.body() != null;
                   AuthToken authToken = request.body();
                   Log.d(TAG, "Auth successes: token: " + authToken.getToken());
                   AppData.getApiHelper().createApiService(authToken.getToken());
                   getViewState().startMainActivity();
               }
               else
               {
                   assert request.errorBody() != null;
                   Log.d(TAG, "Auth error: " + request.errorBody().string());
                   getViewState().showAlertDialog(request.errorBody().string());
               }
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
