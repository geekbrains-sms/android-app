package com.geekbrains.geekbrainsprogect.data.dagger.warehouse;

import com.geekbrains.geekbrainsprogect.data.api.service.AuthService;
import com.geekbrains.geekbrainsprogect.data.api.service.CategoryService;
import com.geekbrains.geekbrainsprogect.data.api.service.ContractorService;
import com.geekbrains.geekbrainsprogect.data.api.service.FundService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductTransactionService;
import com.geekbrains.geekbrainsprogect.data.api.service.RoleService;
import com.geekbrains.geekbrainsprogect.data.api.service.UnitService;
import com.geekbrains.geekbrainsprogect.data.api.service.UserActionService;
import com.geekbrains.geekbrainsprogect.data.api.service.UserService;
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
import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.UserMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;
import com.geekbrains.geekbrainsprogect.data.repository.contract.AuthRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.CategoryRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ContractorRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductTransactionRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UnitRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UserActionRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UserRepository;
import com.geekbrains.geekbrainsprogect.data.repository.impl.AuthRepositoryImpl;
import com.geekbrains.geekbrainsprogect.data.repository.impl.CategoryRepositoryImpl;
import com.geekbrains.geekbrainsprogect.data.repository.impl.ContractorRepositoryImpl;
import com.geekbrains.geekbrainsprogect.data.repository.impl.ProductRepositoryImpl;
import com.geekbrains.geekbrainsprogect.data.repository.impl.ProductTransactionRepositoryImpl;
import com.geekbrains.geekbrainsprogect.data.repository.impl.UnitRepositoryImpl;
import com.geekbrains.geekbrainsprogect.data.repository.impl.UserActionRepositoryImpl;
import com.geekbrains.geekbrainsprogect.data.repository.impl.UserRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @ProductScope
    @Provides
    ProductRepository provideProductRepository(ProductDao productDao, ProductMapper productMapper, ProductService productService, FundService fundService)
    {
        return new ProductRepositoryImpl(productDao, productMapper, productService, fundService);
    }

    @ProductScope
    @Provides
    ProductTransactionRepository provideProductTransactionRepository(ProductTransactionDao productTransactionDao,
                                                                     ProductTransactionCrossDao productTransactionCrossDao,
                                                                     ProductTransactionService productTransactionService,
                                                                     ProductTransactionMapper productTransactionMapper)
    {

        return new ProductTransactionRepositoryImpl(productTransactionDao, productTransactionCrossDao,productTransactionService,productTransactionMapper);
    }

    @ProductScope
    @Provides
    UnitRepository provideUnitRepository(UnitDao unitDao, UnitService unitService)
    {
        return new UnitRepositoryImpl(unitDao, unitService);
    }

    @ProductScope
    @Provides
    CategoryRepository provideCategoryRepository(CategoryDao categoryDao, CategoryService categoryService, ProductCategoryCrossDao productCategoryCrossDao)
    {
        return new CategoryRepositoryImpl(categoryDao, categoryService, productCategoryCrossDao);
    }
    @ProductScope
    @Provides
    ContractorRepository provideContractorRepository(ContractorDao contractorDao, ContractorService contractorService, ProductContractorCrossDao productContractorCrossDao)
    {
        return new ContractorRepositoryImpl(contractorService, contractorDao, productContractorCrossDao);
    }
    @ProductScope
    @Provides
    UserRepository userRepository(UserDao userDao, UserService userService, UserMapper userMapper, RoleService roleService, RoleDao roleDao, UserRoleCrossDao userRoleCrossDao)
    {
        return new UserRepositoryImpl(userDao,userService,userMapper,roleDao, roleService, userRoleCrossDao);
    }
    @ProductScope
    @Provides
    UserActionRepository userActionRepository(UserActionService userActionService, UserActionDao userActionDao)
    {
        return new UserActionRepositoryImpl(userActionService,userActionDao);
    }
    @ProductScope
    @Provides
    AuthRepository authRepository(AuthService authService)
    {
        return new AuthRepositoryImpl(authService);
    }


}
