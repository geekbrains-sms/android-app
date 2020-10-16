package com.geekbrains.geekbrainsprogect.data.dagger;

import android.app.Application;

import androidx.room.Room;

import com.geekbrains.geekbrainsprogect.data.database.room.Database;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.CategoryDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ContractorDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.FundDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductCategoryDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.RoleDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UserActionDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UserDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UserRoleDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private Database database;

    public RoomModule(Application mApplication) {
        database = Room.databaseBuilder(mApplication, Database.class, "database").build();
    }

    @Singleton
    @Provides
    Database providesRoomDatabase() {
        return database;
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
    FundDao providesFundDao(Database database) {
        return database.getFundDao();
    }

    @Singleton
    @Provides
    ProductCategoryDao providesProductCategoryDao(Database database) {
        return database.getProductCategoryDao();
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
    UserRoleDao providesUserRoleDao(Database database) {
        return database.getUserRoleDao();
    }



}
