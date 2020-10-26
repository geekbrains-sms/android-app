package com.geekbrains.geekbrainsprogect.domain.interactor;

import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.model.UnitsWithCategories;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;

public interface ProductInteractor {
    Flowable<List<ProductModel>> getProductListFromDB();
    Completable addProduct(ProductModel product);
    Completable deleteProducts(List<ProductModel> productList);
    Completable getProductFromServer();
    Single<UnitsWithCategories> loadUnitsWithCategories();
}
