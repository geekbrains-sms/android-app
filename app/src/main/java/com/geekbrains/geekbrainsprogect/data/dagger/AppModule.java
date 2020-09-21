package com.geekbrains.geekbrainsprogect.data.dagger;

import android.app.Application;

import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.data.api.ApiHelper;
import com.geekbrains.geekbrainsprogect.data.api.IApiService;
import com.geekbrains.geekbrainsprogect.ui.product_list.view.ProductListActivity;
import com.geekbrains.geekbrainsprogect.ui.product_list.view.ProductListAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application)
    {
        this.application = application;
    }

    @Provides
    @Singleton
    ProductListAdapter provideProductListAdapter()
    {
        return new ProductListAdapter();
    }

}
