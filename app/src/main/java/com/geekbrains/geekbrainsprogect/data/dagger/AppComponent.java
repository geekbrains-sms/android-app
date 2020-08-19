package com.geekbrains.geekbrainsprogect.data.dagger;

import com.geekbrains.geekbrainsprogect.auth.presenter.AuthPresenter;
import com.geekbrains.geekbrainsprogect.data.api.ApiHelper;
import com.geekbrains.geekbrainsprogect.main.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(AuthPresenter authPresenter);
    void inject(MainPresenter mainPresenter);
    void inject(AppData appData);
    void inject(ApiHelper apiHelper);
}
