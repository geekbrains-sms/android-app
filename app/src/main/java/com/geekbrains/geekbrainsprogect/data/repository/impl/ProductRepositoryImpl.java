package com.geekbrains.geekbrainsprogect.data.repository.impl;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.api.dto.FundDTO;
import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
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
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductCategoryCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductContractorCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductTransactionCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ProductRepositoryImpl implements ProductRepository {
    private static final String TAG = "ProductRepository";
    ProductDao productDao;
    ProductContractorCrossDao productContractorCrossDao;
    ProductCategoryCrossDao productCategoryCrossDao;
    ProductTransactionCrossDao productTransactionCrossDao;
    ProductTransactionDao productTransactionDao;
    FundService fundService;
    ContractorDao contractorDao;
    CategoryDao categoryDao;
    ProductTransactionService productTransactionService;
    ContractorService contractorService;
    ProductMapper productMapper;
    ProductTransactionMapper productTransactionMapper;
    UnitDao unitDao;
    ProductService productService;
    @Inject
    public ProductRepositoryImpl(ProductDao productDao, ProductContractorCrossDao productContractorCrossDao,
                                 ProductCategoryCrossDao productCategoryCrossDao, ProductTransactionCrossDao productTransactionCrossDao,
                                 ProductTransactionDao productTransactionDao, FundService fundService, ContractorDao contractorDao,
                                 ProductTransactionService productTransactionService, ContractorService contractorService,
                                 ProductMapper productMapper, ProductTransactionMapper productTransactionMapper, UnitDao unitDao, CategoryDao categoryDao,
                                 ProductService productService) {
        this.productDao = productDao;
        this.productContractorCrossDao = productContractorCrossDao;
        this.productCategoryCrossDao = productCategoryCrossDao;
        this.productTransactionCrossDao = productTransactionCrossDao;
        this.productTransactionDao = productTransactionDao;
        this.fundService = fundService;
        this.contractorDao = contractorDao;
        this.productTransactionService = productTransactionService;
        this.contractorService = contractorService;
        this.productMapper = productMapper;
        this.productTransactionMapper = productTransactionMapper;
        this.unitDao = unitDao;
        this.productService = productService;
        this.categoryDao = categoryDao;
    }

    @Override
    public Flowable<List<ProductWithCategory>> getProductListFromDB() {
        Log.d(TAG, "LoadProductFromDB");
        return productDao.getAllProduct()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void deleteAllProduct() {
        productDao.deleteAllProduct();
        Log.d(TAG, " deleteAllProduct()");
    }

    @Override
    public Completable getProductFromServer() {
        Log.d(TAG, "getProductFromServer()");
        return fundService.getAllFunds()
                    .map(x -> productMapper.toEntityListProducts(x))
                    .doOnNext(x -> {
                        deleteAllProduct();
                        productDao.insertAll(x);
                    })
                    .map(x -> productMapper.toEntityList(x))
                    .flatMap(Observable::fromIterable)
                    .doOnNext(this::saveUnitsToDatabase)
                    .flatMapCompletable(this::dataSaved);
                }

    @Override
    public Completable deleteProducts(List<Product> products) {

        Completable[]completables = new Completable[products.size()];
        for(int i = 0; i < products.size(); i++)
        {
            completables[i] = productService.deleteProductById(products.get(i).getId());
        }
         return Completable.mergeArray(completables)
                .andThen(productDao.deleteAll(products));
    }

    @Override
    public Completable addProduct(ProductModel productModel) {
        return productService.addProduct(productMapper.toDto(productModel))
                .map(x -> productMapper.toEntity(x))
                .doOnNext(x -> {
                    productDao.insert(x.product);
                    saveUnitsToDatabase(x);
                })
                .flatMapCompletable(this::dataSaved);
    }

    private  Completable dataSaved(ProductWithCategory productWithCategory)
    {
        Log.d(TAG, "startProductSaved(): product id - " + productWithCategory.product.getId());
       return Completable.mergeArray(loadProductTransactions(productWithCategory), saveProductCategory(productWithCategory), loadContractors(productWithCategory));
    }

    private Completable loadProductTransactions(ProductWithCategory productWithCategory) {

        return productTransactionService.getProductTransactionById(productWithCategory.getId())
                .map(items -> productTransactionMapper.toEntityList(items))
                .doOnNext(x -> productTransactionCrossDao.deleteByProduct(productWithCategory.product.getId()))
                .flatMap(Observable::fromIterable)
                .flatMapCompletable(x -> Completable.fromRunnable(() -> {productTransactionCrossDao.insert(new ProductTransactionCrossRef(productWithCategory.product.getId(),x.getId()));
                    productTransactionDao.insert(x);
                    Log.d(TAG, "loadProductTransactions: product id - " + productWithCategory.product.getId() + ", productTransactionID - " + x.getId());
                }));
    }

    private void saveUnitsToDatabase(ProductWithCategory productWithCategory) {
        Log.d(TAG, "saveUnitsToDatabase() start");
        unitDao.insert(productWithCategory.getUnit());
    }
    public Completable saveProductCategory(ProductWithCategory productWithCategory)
    {
        return Completable.fromRunnable(()->{
            productCategoryCrossDao.deleteByProduct(productWithCategory.product.getId());
            for(Category category: productWithCategory.getCategoryList())
           {
               Log.d(TAG, "saveCategoryToDatabase() start");
               categoryDao.insert(category);
               productCategoryCrossDao.insert(new ProductCategoryCrossRef(productWithCategory.product.id, category.getId()));
           }
        });
    }

    public Completable loadContractors(ProductWithCategory productWithCategory) {
        Log.d(TAG, "loadContractors() start");
        return contractorService.getProvidersByProductId(productWithCategory.product.getId())
                .doOnNext(x -> productContractorCrossDao.deleteByProduct(productWithCategory.product.getId()))
                .flatMap(Observable::fromIterable)
                .flatMapCompletable(x ->
                        Completable.fromRunnable(() -> {
                            contractorDao.insert(x);
                            productContractorCrossDao.insert(new ProductContractorCrossRef(productWithCategory.product.getId(), x.getId()));
                            Log.d(TAG, "loadContractors product id - " + productWithCategory.product.getId());
                        }));
    }
}
