package com.geekbrains.geekbrainsprogect.data.database.room;

import androidx.room.RoomDatabase;

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


public abstract class Database extends RoomDatabase {
    static final int VERSION = 1;

    public abstract CategoryDao getCategoryDao();
    public abstract ContractorDao getContractorDao();
    public abstract FundDao getFundDao();
    public abstract ProductDao getProductDao();
    public abstract RoleDao getRoleDao();
    public abstract UserActionDao getUserActionDao();
    public abstract UserDao getUserDao();
    public abstract UserRoleDao getUserRoleDao();
    public abstract ProductCategoryDao getProductCategoryDao();
    public abstract ProductTransactionDao getProductTransactionDao();


}
