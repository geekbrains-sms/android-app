package com.geekbrains.geekbrainsprogect.data.dagger.application;

import android.app.Application;
import android.content.Context;

import com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view.PersonalListAdapter;
import com.geekbrains.geekbrainsprogect.ui.product.category.view.CategoryListAdapter;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListAdapter;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    Context context;

    public AppModule(Context aContext) {
        context = aContext.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return context;
    }
}
