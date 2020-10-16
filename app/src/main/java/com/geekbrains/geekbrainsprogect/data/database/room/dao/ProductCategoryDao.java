package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Query;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductCategoryJoin;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ProductCategoryDao extends BaseDao<ProductCategoryJoin> {
    @Query("DELETE FROM product_category_join WHERE product_category_join.product_id = :id")
    void deleteByProductId(long id);
    @Query("SELECT * FROM product_category_join WHERE product_category_join.product_id = :id")
    Flowable<List<ProductCategoryJoin>> getByProductId(long id);
    @Query("DELETE FROM product_category_join")
    Completable deleteAll();
}
