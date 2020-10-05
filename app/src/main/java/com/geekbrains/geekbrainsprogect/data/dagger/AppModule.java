package com.geekbrains.geekbrainsprogect.data.dagger;

import android.app.Application;

import com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view.PersonalListAdapter;
import com.geekbrains.geekbrainsprogect.ui.product.category.view.CategoryListAdapter;
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

    @Provides
    @Singleton
    CategoryListAdapter provideCategoryListAdapter(){return new CategoryListAdapter();}

    @Provides
    @Singleton
    PersonalListAdapter providePersonalListAdapter(){return new PersonalListAdapter();}

}
