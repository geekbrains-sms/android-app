package com.geekbrains.geekbrainsprogect.data.model.entity.join;

import androidx.room.Entity;

@Entity(tableName = "product_category_cross",primaryKeys = {"productId", "categoryId"})
public class ProductCategoryCrossRef {
    public long productId;
    public long categoryId;


    public ProductCategoryCrossRef(long productId, long categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }
}
