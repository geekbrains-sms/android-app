package com.geekbrains.geekbrainsprogect.data.model.entity.cross;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "product_contractor_cross",primaryKeys = {"productId", "contractorId"})
public class ProductContractorCrossRef {
    private long productId;
    @ColumnInfo(index = true)
    private long contractorId;

    public ProductContractorCrossRef(long productId, long contractorId) {
        this.productId = productId;
        this.contractorId = contractorId;
    }

    public long getProductId() {
        return productId;
    }

    public long getContractorId() {
        return contractorId;
    }
}
