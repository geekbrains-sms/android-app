package com.geekbrains.geekbrainsprogect.domain.interactor.impl;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.repository.contract.CategoryRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ContractorRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductTransactionRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UnitRepository;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.ProductInteractor;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.model.UnitsWithCategories;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ProductInteractorImpl implements ProductInteractor {
    private static final String TAG = "ProductInteractor";

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ContractorRepository contractorRepository;
    UnitRepository unitRepository;
    ProductTransactionRepository productTransactionRepository;
    ProductMapper productMapper;

    @Inject
    public ProductInteractorImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                                 ContractorRepository contractorRepository, UnitRepository unitRepository,
                                 ProductTransactionRepository productTransactionRepository, ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.contractorRepository = contractorRepository;
        this.unitRepository = unitRepository;
        this.productTransactionRepository = productTransactionRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Flowable<List<ProductModel>> getProductListFromDB() {
        Log.d(TAG, "LoadProductFromDB");
         return productRepository.getProductListFromDB()
                 .subscribeOn(Schedulers.io())
                 .map(items -> productMapper.toModelList(items));
    }

    @Override
    public Completable addProduct(ProductModel product) {
        return productRepository.addProduct(product)
                .map(x -> productMapper.toEntity(x))
                .flatMapCompletable(this::dataSaved);

    }

    @Override
    public Completable deleteProducts(List<Long> productList) {
        return productRepository.deleteProducts(productList);
    }


    @Override
    public Completable saveProductFromServerToDB() {
        return unitRepository.getAllUnitsFromServer().andThen(productRepository.getProductFromServer()
                .flatMap(Observable::fromIterable)
                .flatMapCompletable(this::dataSaved));
//                .repeatWhen( x -> x.delay(60, TimeUnit.SECONDS));
    }

    @Override
    public Single<UnitsWithCategories> loadUnitsWithCategories() {
        return Flowable.zip(categoryRepository.getAllCategoriesFromBD(), unitRepository.getAllUnitFromBD(), UnitsWithCategories::new)
                .firstOrError();
    }

    private Completable dataSaved(ProductWithCategory productWithCategory)
    {
        Log.d(TAG, "startProductSaved(): product id - " + productWithCategory.getProduct().getId());
        return Completable.mergeArray(
                saveProductCategory(productWithCategory),
                loadContractors(productWithCategory));
    }


    private Completable saveProductCategory(ProductWithCategory productWithCategory)
    {
        return categoryRepository.addCategoryCross(productWithCategory);
    }

    private Completable loadContractors(ProductWithCategory productWithCategory) {
        return contractorRepository.addContractorsCross(productWithCategory.getId());
    }
}
