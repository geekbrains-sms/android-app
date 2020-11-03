package com.geekbrains.geekbrainsprogect.data.database.room;

import androidx.room.RoomDatabase;

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
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UserRoleCrossDao;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductCategoryCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductContractorCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductTransactionCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.UserRoleCrossRef;


@androidx.room.Database(entities = {Product.class, ProductTransaction.class, User.class, UserAction.class, Category.class, Unit.class,
        Contractor.class, Role.class, ProductCategoryCrossRef.class, ProductContractorCrossRef.class, ProductTransactionCrossRef.class, UserRoleCrossRef.class}, version = 8)
public abstract class Database extends RoomDatabase {

    public abstract CategoryDao getCategoryDao();
    public abstract ContractorDao getContractorDao();
    public abstract ProductDao getProductDao();
    public abstract RoleDao getRoleDao();
    public abstract UserActionDao getUserActionDao();
    public abstract UserDao getUserDao();
    public abstract ProductTransactionDao getProductTransactionDao();
    public abstract ProductCategoryCrossDao getProductCategoryCrossDao();
    public abstract ProductContractorCrossDao getProductContractorCrossDao();
    public abstract ProductTransactionCrossDao getProductTransactionCrossDao();
    public abstract UnitDao getUnitDao();
    public abstract UserRoleCrossDao getUserRoleCrossDao();
}
