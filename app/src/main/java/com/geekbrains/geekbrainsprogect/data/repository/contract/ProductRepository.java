package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.response.ProductResponse;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface ProductRepository {
    Flowable<List<ProductModel>> getProductList();
    Observable<ProductModel>addProduct(Product product);
    Observable<String>deleteProductById(long id);
    Observable<String>deleteAllProduct();
    Observable<ResponseBody>editProduct(Product product);
    Completable getProductFromServer();
}
