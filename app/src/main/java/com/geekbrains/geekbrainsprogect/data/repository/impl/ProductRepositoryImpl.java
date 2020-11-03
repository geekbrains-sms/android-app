package com.geekbrains.geekbrainsprogect.data.repository.impl;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.api.service.FundService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductDao;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class ProductRepositoryImpl implements ProductRepository {
    private static final String TAG = "ProductRepository";
    ProductDao productDao;
    ProductMapper productMapper;
    ProductService productService;
    FundService fundService;
    @Inject
    public ProductRepositoryImpl(ProductDao productDao, ProductMapper productMapper,
                                 ProductService productService, FundService fundService) {
        this.productDao = productDao;
        this.productMapper = productMapper;
        this.productService = productService;
        this.fundService = fundService;
    }

    @Override
    public Flowable<List<ProductWithCategory>> getProductListFromDB() {
        Log.d(TAG, "LoadProductFromDB");
        return productDao.getAllProduct();
    }

    @Override
    public Flowable<List<ProductWithCategory>> getProductListFromDbByIds(List<Long> idList) {
        return productDao.getProductsByIds(idList);
    }

    @Override
    public Observable<ProductWithCategory> getProductFromDbById(long id) {
        return productDao.getProductById(id);
    }

    @Override
    public void deleteAllProduct() {
        productDao.deleteAllProduct();
        Log.d(TAG, " deleteAllProduct()");
    }

    @Override
    public Observable<List<ProductWithCategory>> getProductFromServer() {
        Log.d(TAG, "getProductFromServer()");
        return fundService.getAllFunds()
                    .doOnNext(x -> {
                        deleteAllProduct();
                        productDao.insertAll(productMapper.toEntityListProducts(x));
                    })
                    .map(x -> productMapper.toEntityList(x));
                }

    @Override
    public Completable deleteProducts(List<Long> productsId) {

        Completable[]completables = new Completable[productsId.size()];
        for(int i = 0; i < productsId.size(); i++)
        {
            completables[i] = productService.deleteProductById(productsId.get(i));
        }
         return Completable.mergeArray(completables)
                .andThen(productDao.deleteAllById(productsId));
    }

    @Override
    public Observable<ProductWithCategory> addProduct(ProductModel productModel) {
        return productService.addProduct(productMapper.toDto(productModel))
                .map(x -> productMapper.toEntity(x))
                .doOnNext(x -> {
                    productDao.insert(x.product);
                });
    }

    @Override
    public Observable<ProductWithCategory> editProduct(ProductModel productModel) {
        return productService.editProduct(productMapper.toDto(productModel))
                .flatMap(x -> fundService.getFundsByProductId(productModel.getId()))
                .map(x -> productMapper.toEntity(x))
                .doOnNext(x -> productDao.update(x.product));
    }

    @Override
    public Completable updateProductFromServerById(long id) {
        return fundService.getFundsByProductId(id)
                .map(x -> productMapper.toEntity(x))
                .flatMapCompletable(x -> Completable.fromRunnable(() -> productDao.insert(x.product)));
    }
}
