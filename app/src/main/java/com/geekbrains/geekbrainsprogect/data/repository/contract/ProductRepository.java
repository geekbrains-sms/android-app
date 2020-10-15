package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.response.ProductResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface ProductRepository {
    Observable<ProductResponse> getProductList();
    Observable<Product>addProduct(Product product);
    Observable<String>deleteProductById(long id);
    Observable<String>deleteAllProduct();
    Observable<ResponseBody>editProduct(Product product);
}
