package com.geekbrains.geekbrainsprogect.data.dagger.application;

import android.app.Application;

import com.geekbrains.geekbrainsprogect.data.dagger.ComponentManager;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;


public class AppData extends Application {
    private static ComponentManager componentsManager;
    public static String token;
    private static User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponentsTree();
        initAppComponent();
    }

    private void initAppComponent() {
        componentsManager.getAppComponent();
    }

    private void initComponentsTree() {
        componentsManager = new ComponentManager(this);
    }

    public static ComponentManager getComponentsManager() {
        return componentsManager;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        AppData.currentUser = currentUser;
    }
}
