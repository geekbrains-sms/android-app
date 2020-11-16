package com.geekbrains.geekbrainsprogect.data.model.entity.cross;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "product_category_cross",primaryKeys = {"productId", "categoryId"})
public class ProductCategoryCrossRef {
    private long productId;
    @ColumnInfo(index = true)
    private long categoryId;


    public ProductCategoryCrossRef(long productId, long categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public long getProductId() {
        return productId;
    }

    public long getCategoryId() {
        return categoryId;
    }
}
