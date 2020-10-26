package com.geekbrains.geekbrainsprogect.data.dagger.warehouse;

import com.geekbrains.geekbrainsprogect.data.api.service.CategoryService;
import com.geekbrains.geekbrainsprogect.data.api.service.ContractorService;
import com.geekbrains.geekbrainsprogect.data.api.service.FundService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductTransactionService;
import com.geekbrains.geekbrainsprogect.data.api.service.UnitService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.CategoryDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ContractorDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductCategoryCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductContractorCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UnitDao;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.repository.contract.CategoryRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductTransactionRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UnitRepository;
import com.geekbrains.geekbrainsprogect.data.repository.impl.CategoryRepositoryImpl;
import com.geekbrains.geekbrainsprogect.data.repository.impl.ProductRepositoryImpl;
import com.geekbrains.geekbrainsprogect.data.repository.impl.ProductTransactionRepositoryImpl;
import com.geekbrains.geekbrainsprogect.data.repository.impl.UnitRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @ProductScope
    @Provides
    ProductRepository provideProductRepository(ProductDao productDao, ProductContractorCrossDao productContractorCrossDao,
                                               ProductCategoryCrossDao productCategoryCrossDao, ProductTransactionCrossDao productTransactionCrossDao,
                                               ProductTransactionDao productTransactionDao, FundService fundService, ContractorDao contractorDao,
                                               ProductTransactionService productTransactionService, ContractorService contractorService,
                                               ProductMapper productMapper, ProductTransactionMapper productTransactionMapper, UnitDao unitDao, CategoryDao categoryDao,
                                               ProductService productService)
    {
        return new ProductRepositoryImpl(productDao, productContractorCrossDao, productCategoryCrossDao,
                productTransactionCrossDao, productTransactionDao, fundService, contractorDao, productTransactionService,
                contractorService, productMapper, productTransactionMapper, unitDao, categoryDao, productService);
    }

    @ProductScope
    @Provides
    ProductTransactionRepository provideProductTransactionRepository()
    {
        return new ProductTransactionRepositoryImpl();
    }

    @ProductScope
    @Provides
    UnitRepository provideUnitRepository(UnitDao unitDao, UnitService unitService)
    {
        return new UnitRepositoryImpl(unitDao, unitService);
    }

    @ProductScope
    @Provides
    CategoryRepository provideCategoryRepository(CategoryDao categoryDao, CategoryService categoryService)
    {
        return new CategoryRepositoryImpl(categoryDao, categoryService);
    }


}
