package com.geekbrains.geekbrainsprogect.domain.interactor.contract;

import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.model.UnitsWithCategories;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface ProductInteractor {
    Flowable<List<ProductModel>> getProductListFromDB();
    Completable addProduct(ProductModel product);
    Completable deleteProducts(List<Long> productList);
    Completable saveProductFromServerToDB();
    Single<UnitsWithCategories> loadUnitsWithCategories();
}
