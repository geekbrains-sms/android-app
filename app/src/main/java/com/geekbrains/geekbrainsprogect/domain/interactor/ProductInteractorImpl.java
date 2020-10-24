package com.geekbrains.geekbrainsprogect.domain.interactor;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.mapper.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ProductInteractorImpl implements ProductInteractor {
    private static final String TAG = "ProductInteractor";

    ProductRepository productRepository;
    ProductMapper productMapper;

    @Inject
    public ProductInteractorImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
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
        return null;
    }

    @Override
    public Completable deleteProductById(long id) {
        return null;
    }

    @Override
    public Observable<String> deleteAllProduct() {
        return null;
    }

    @Override
    public Observable<ResponseBody> editProduct(Product product) {
        return null;
    }

    @Override
    public Completable getProductFromServer() {
        return productRepository.getProductFromServer();
    }
}
