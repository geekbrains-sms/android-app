package com.geekbrains.geekbrainsprogect.data.dagger.application;

import com.geekbrains.geekbrainsprogect.data.dagger.warehouse.InteractorModule;
import com.geekbrains.geekbrainsprogect.data.dagger.warehouse.MapperModule;
import com.geekbrains.geekbrainsprogect.data.dagger.warehouse.RepositoryModule;
import com.geekbrains.geekbrainsprogect.data.dagger.warehouse.WarehouseComponent;
import com.geekbrains.geekbrainsprogect.ui.auth.presenter.AuthPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, RoomModule.class})
public interface AppComponent {
    WarehouseComponent productComponent(InteractorModule module, RepositoryModule productTransactionModule, MapperModule mapperModule);

    void inject(AuthPresenter authPresenter);
}
