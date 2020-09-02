package com.geekbrains.geekbrainsprogect.data.api;

import com.geekbrains.geekbrainsprogect.data.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private IApiService api;
    public ApiHelper(String username, String password)
    {
        api = createApiService(username, password);
    }

    private IApiService createApiService(String username, String password) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(username, password))
                .build();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
        return new Retrofit.Builder()
//                .baseUrl("http://91.207.146.7:8888")      // серв на компе Владимира
                .baseUrl("http://46.17.104.250:8888")        // локальный серв
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build()
                .create(IApiService.class);
    }

    public Single<List<User>>requestAllUsers(){
        return api.getAllUsers().subscribeOn(Schedulers.io());
    }
    public Single<String>registerUser(String login, String password)
    {
        return api.postUser(login, password).subscribeOn(Schedulers.io());
    }
    public Single<User>getUser(String username)
    {
        return api.getUser(username).subscribeOn(Schedulers.io());
    }


}
