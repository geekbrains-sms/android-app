package com.geekbrains.geekbrainsprogect.domain.interactor.contract;

import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface DetailProductInteractor {
    Completable addProductTransaction(ProductTransactionModel productTransactionModel);
    Completable editProduct(ProductModel productModel);
    Flowable<List<ProductModel>> subscribeToProductChangesById(List<Integer>productIndexList);
}
