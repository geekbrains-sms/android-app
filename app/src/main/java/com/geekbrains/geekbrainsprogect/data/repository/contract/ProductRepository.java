package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;

public interface ProductRepository {
    Flowable<List<ProductWithCategory>> getProductListFromDB();
    void deleteAllProduct();
    Completable getProductFromServer();
    Completable deleteProducts(List<Product>products);
    Completable addProduct(ProductModel productModel);
}
