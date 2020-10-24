package com.geekbrains.geekbrainsprogect.data.dagger.application;

import android.app.Application;
import android.content.Context;

import com.geekbrains.geekbrainsprogect.data.dagger.ComponentManager;
import com.geekbrains.geekbrainsprogect.data.dagger.ProductModule;
import com.geekbrains.geekbrainsprogect.data.dagger.ProductTransactionModule;
import com.geekbrains.geekbrainsprogect.data.dagger.WarehouseComponent;


public class AppData extends Application {
    private static ComponentManager componentsManager;
    public static String token;

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
}
