package com.geekbrains.geekbrainsprogect.data.api;

import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiHelper {
    @Inject
    IApiService api;

    public ApiHelper()
    {
        AppData.getAppComponent().inject(this);
    }

    public Single<List<User>>requestAllUsers(){
        return api.getAllUsers().subscribeOn(Schedulers.io());
    }
    public Single<String>registerUser(String login, String password)
    {
        return api.postUser(login, password).subscribeOn(Schedulers.io());
    }
}
