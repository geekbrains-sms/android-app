package com.geekbrains.geekbrainsprogect.data.dagger.application;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.geekbrains.geekbrainsprogect.data.database.room.Database;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.CategoryDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ContractorDao;

import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductCategoryCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductContractorCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.RoleDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UnitDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UserActionDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UserDao;


import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Singleton
    @Provides
    Database providesRoomDatabase(Context context) {
        return Room.databaseBuilder(context, Database.class, "database")
                .fallbackToDestructiveMigration()
                .build();

    }

    @Singleton
    @Provides
    ProductDao providesProductDao(Database database) {
        return database.getProductDao();
    }

    @Singleton
    @Provides
    CategoryDao providesCategoryDao(Database database) {
        return database.getCategoryDao();
    }

    @Singleton
    @Provides
    ContractorDao providesContractorDao(Database database) {
        return database.getContractorDao();
    }


    @Singleton
    @Provides
    ProductTransactionDao providesProductTransactionDao(Database database) {
        return database.getProductTransactionDao();
    }

    @Singleton
    @Provides
    RoleDao providesRoleDao(Database database) {
        return database.getRoleDao();
    }

    @Singleton
    @Provides
    UserActionDao providesUserActionDao(Database database) {
        return database.getUserActionDao();
    }

    @Singleton
    @Provides
    UserDao providesUserDao(Database database) {
        return database.getUserDao();
    }

    @Singleton
    @Provides
    ProductCategoryCrossDao providesProductCategoryCrossDao(Database database) {
        return database.getProductCategoryCrossDao();
    }
    @Singleton
    @Provides
    ProductContractorCrossDao providesProductContractorCrossDao(Database database) {
        return database.getProductContractorCrossDao();
    }
    @Singleton
    @Provides
    ProductTransactionCrossDao providesProductTransactionCrossDao(Database database) {
        return database.getProductTransactionCrossDao();
    }
    @Singleton
    @Provides
    UnitDao provideUnitDao(Database database)
    {
        return database.getUnitDao();
    }
}
