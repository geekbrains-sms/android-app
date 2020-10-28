package com.geekbrains.geekbrainsprogect.data.dagger;

import android.content.Context;

import com.geekbrains.geekbrainsprogect.data.dagger.application.AppComponent;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppModule;
import com.geekbrains.geekbrainsprogect.data.dagger.application.DaggerAppComponent;
import com.geekbrains.geekbrainsprogect.data.dagger.warehouse.InteractorModule;
import com.geekbrains.geekbrainsprogect.data.dagger.warehouse.MapperModule;
import com.geekbrains.geekbrainsprogect.data.dagger.warehouse.RepositoryModule;
import com.geekbrains.geekbrainsprogect.data.dagger.warehouse.WarehouseComponent;


public class ComponentManager {
    private AppComponent appComponent;
    private WarehouseComponent warehouseComponent;

    private final Context context;

    public ComponentManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(context))
                    .build();
        }
        return appComponent;
    }

    public WarehouseComponent getWarehouseComponent() {
        if(warehouseComponent == null)
        {
            warehouseComponent = appComponent.productComponent(new InteractorModule(), new RepositoryModule(), new MapperModule());
        }
        return warehouseComponent;
    }

    public void clearWarehouseComponent()
    {
        warehouseComponent = null;
    }
}
