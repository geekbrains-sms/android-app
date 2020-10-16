package com.geekbrains.geekbrainsprogect.data.dagger;

import android.app.Application;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.api.ApiHelper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Fund;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;

import java.util.List;


public class AppData extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = generateAppComponent();
        appComponent.inject(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent generateAppComponent()
    {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .roomModule(new RoomModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

}
