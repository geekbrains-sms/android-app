package com.geekbrains.geekbrainsprogect.data.model.entity.join;

import androidx.room.Entity;

@Entity(tableName = "product_transaction_cross",primaryKeys = {"productId", "transactionId"})
public class ProductTransactionCrossRef {
    public long productId;
    public long transactionId;

    public ProductTransactionCrossRef(long productId, long transactionId) {
        this.productId = productId;
        this.transactionId = transactionId;
    }
}
