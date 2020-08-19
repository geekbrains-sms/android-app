package com.geekbrains.geekbrainsprogect.data.dagger;

import android.app.Application;

import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.data.api.ApiHelper;
import com.geekbrains.geekbrainsprogect.data.api.IApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application)
    {
        this.application = application;
    }

    @Singleton
    @Provides
    ApiHelper provideApiHelper()
    {
        return new ApiHelper();
    }

    @Singleton
    @Provides
    List<User> provideUserList()
    {
        return new ArrayList<>();
    }

    @Singleton
    @Provides
    IApiService provideApiService()
    {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
        return new Retrofit.Builder()
                .baseUrl("http://192.168.1.126:8080")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(IApiService.class);
    }

}
