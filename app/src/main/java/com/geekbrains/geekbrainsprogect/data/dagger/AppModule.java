package com.geekbrains.geekbrainsprogect.data.dagger;

import android.app.Application;

import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListAdapter;

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
