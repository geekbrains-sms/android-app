package com.geekbrains.geekbrainsprogect.data.model.entity.join;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;

@Entity(tableName = "product_category_join",
        primaryKeys = {"product_id", "category_id"},
        foreignKeys = {
                @ForeignKey(entity = Product.class,
                        parentColumns ={"id"},
                        childColumns ={"product_id"}),
                @ForeignKey(entity= Category.class,
                        parentColumns={"id"},
                        childColumns={"category_id"})
        })
public class ProductCategoryJoin {
    @ColumnInfo(name = "product_id")
    private final long productId;
    @ColumnInfo(name = "category_id")
    private final long categoryId;

    public ProductCategoryJoin(long productId, long categoryId) {
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
