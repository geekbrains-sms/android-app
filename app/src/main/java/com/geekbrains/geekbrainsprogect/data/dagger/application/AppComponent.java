package com.geekbrains.geekbrainsprogect.data.dagger.application;

import android.app.Application;

import com.geekbrains.geekbrainsprogect.data.dagger.ProductTransactionModule;
import com.geekbrains.geekbrainsprogect.data.dagger.WarehouseComponent;
import com.geekbrains.geekbrainsprogect.data.dagger.ProductModule;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.ui.auth.presenter.AuthPresenter;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, RoomModule.class})
public interface AppComponent {
    WarehouseComponent productComponent(ProductModule module, ProductTransactionModule productTransactionModule);

    void inject(AuthPresenter authPresenter);
}
