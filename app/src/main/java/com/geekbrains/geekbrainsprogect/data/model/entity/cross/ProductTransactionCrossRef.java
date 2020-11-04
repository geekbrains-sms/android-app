package com.geekbrains.geekbrainsprogect.data.model.entity.cross;

import androidx.room.Entity;

@Entity(tableName = "product_transaction_cross",primaryKeys = {"productId", "transactionId"})
public class ProductTransactionCrossRef {
    private long productId;
    private long transactionId;

    public ProductTransactionCrossRef(long productId, long transactionId) {
        this.productId = productId;
        this.transactionId = transactionId;
    }

    public long getProductId() {
        return productId;
    }

    public long getTransactionId() {
        return transactionId;
    }
}
