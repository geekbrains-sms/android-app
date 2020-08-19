package com.geekbrains.geekbrainsprogect.data.dagger;

import android.app.Application;
import com.geekbrains.geekbrainsprogect.data.User;
import java.util.List;
import javax.inject.Inject;

public class AppData extends Application {
    private static AppComponent appComponent;
    @Inject
    List<User>userList;


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = generateAppComponent();
        appComponent.inject(this);


    }

    public List<User> getUserList() {
        return userList;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent generateAppComponent()
    {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

}
