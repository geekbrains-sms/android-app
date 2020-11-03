package com.geekbrains.geekbrainsprogect.data.model.entity.join;

import androidx.room.Entity;

@Entity(tableName = "product_contractor_cross",primaryKeys = {"productId", "contractorId"})
public class ProductContractorCrossRef {
    public long productId;
    public long contractorId;

    public ProductContractorCrossRef(long productId, long contractorId) {
        this.productId = productId;
        this.contractorId = contractorId;
    }
}
