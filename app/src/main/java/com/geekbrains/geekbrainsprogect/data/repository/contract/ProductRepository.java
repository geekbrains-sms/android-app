package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface ProductRepository {
    Flowable<List<ProductWithCategory>> getProductListFromDB();
    Flowable<List<ProductWithCategory>> getProductListFromDbByIds(List<Long>idList);
    Flowable<ProductWithCategory> getProductFromDbById(long id);
    void deleteAllProduct();
    Observable<List<ProductWithCategory>> getProductFromServer();
    Completable deleteProducts(List<Long>productsId);
    Observable<ProductWithCategory> addProduct(ProductModel productModel);
    Observable<ProductWithCategory> editProduct(ProductModel productModel);
}
