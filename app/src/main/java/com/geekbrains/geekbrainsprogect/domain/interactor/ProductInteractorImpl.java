package com.geekbrains.geekbrainsprogect.domain.interactor;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.mapper.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.repository.contract.CategoryRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UnitRepository;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.model.UnitsWithCategories;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ProductInteractorImpl implements ProductInteractor {
    private static final String TAG = "ProductInteractor";

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    UnitRepository unitRepository;
    ProductMapper productMapper;


    @Inject
    public ProductInteractorImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UnitRepository unitRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.unitRepository = unitRepository;
        this.categoryRepository = categoryRepository;
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
        return productRepository.addProduct(product);
    }

    @Override
    public Completable deleteProducts(List<ProductModel> productList) {
        return productRepository.deleteProducts(productMapper.toEntityListProducts(productList));
    }


    @Override
    public Completable getProductFromServer() {
        return productRepository.getProductFromServer();
//                .repeatWhen( x -> x.delay(60, TimeUnit.SECONDS));
    }

    @Override
    public Single<UnitsWithCategories> loadUnitsWithCategories() {
        return Flowable.zip(unitRepository.getAllUnitFromBD(), categoryRepository.getAllCategoriesFromBD(), UnitsWithCategories::new)
                .firstOrError();
    }
}
