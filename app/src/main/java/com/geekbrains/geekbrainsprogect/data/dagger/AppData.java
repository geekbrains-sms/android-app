package com.geekbrains.geekbrainsprogect.data.dagger;

import android.app.Application;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.data.api.ApiHelper;

import java.util.List;
import javax.inject.Inject;

public class AppData extends Application {
    private static AppComponent appComponent;
    private static User currentUser;
    private static ApiHelper apiHelper;


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = generateAppComponent();
        appComponent.inject(this);
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
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

    public static void setApiHelper(ApiHelper helper) {
        apiHelper = helper;
    }

    public static ApiHelper getApiHelper() {
        return apiHelper;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
