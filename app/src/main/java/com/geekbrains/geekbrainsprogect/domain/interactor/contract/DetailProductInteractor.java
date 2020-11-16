package com.geekbrains.geekbrainsprogect.domain.interactor.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.model.UnitsWithCategories;

import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface DetailProductInteractor {
    Completable addProductTransaction(ProductTransactionModel productTransactionModel, long productId);
    Completable editProduct(ProductModel productModel);
    Flowable<List<ProductModel>> subscribeToProductChangesById(List<Long>productIndexList);
    Single<UnitsWithCategories> getEditProductData();
    Completable saveContractorsFromServer();
    Single<List<Contractor>> getContractorsFromDB();
    Single<List<ProductTransactionModel>> getTransactionsByProduct(long id);
}
