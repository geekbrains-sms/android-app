package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import java.util.List;
import io.reactivex.Observable;


public interface ProductTransactionRepository {
    Observable<List<ProductTransaction>> getAllSupplyProductTransactions();
    Observable<List<ProductTransaction>>getAllShipmentProductTransactions();
    Observable<List<ProductTransaction>>addSupplyTransactions(ProductTransaction productTransaction);
    Observable<List<ProductTransaction>>addShipmentTransactions(ProductTransaction productTransaction);
    Observable<List<ProductTransaction>>getProductTransactionById(long id);
}
