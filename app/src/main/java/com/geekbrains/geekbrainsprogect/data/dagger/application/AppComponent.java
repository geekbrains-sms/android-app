package com.geekbrains.geekbrainsprogect.data.dagger.application;

import com.geekbrains.geekbrainsprogect.data.dagger.warehouse.WarehouseComponent;
import com.geekbrains.geekbrainsprogect.ui.auth.presenter.AuthPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, RoomModule.class})
public interface AppComponent {
    WarehouseComponent productComponent(ProductModule module, ProductTransactionModule productTransactionModule);

    void inject(AuthPresenter authPresenter);
}
