package com.geekbrains.geekbrainsprogect.data.dagger;

import android.app.Application;


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
