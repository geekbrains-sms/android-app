package com.geekbrains.geekbrainsprogect.domain.interactor.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import com.geekbrains.geekbrainsprogect.ui.product.detail.model.EditProductData;

import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public interface DetailProductInteractor {
    Completable addProductTransaction(ProductTransactionModel productTransactionModel);
    Completable editProduct(ProductModel productModel);
    Flowable<List<ProductModel>> subscribeToProductChangesById(List<Long>productIndexList);
    Single<EditProductData> getEditProductData();
    Completable saveContractorsFromServer();
    Single<List<Contractor>> getContractorsFromDB();
    Single<List<ProductTransactionModel>> getTransactionsByProduct(long id);
}
