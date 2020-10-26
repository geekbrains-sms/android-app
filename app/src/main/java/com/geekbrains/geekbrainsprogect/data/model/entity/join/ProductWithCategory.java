package com.geekbrains.geekbrainsprogect.data.model.entity.join;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Junction;
import androidx.room.Relation;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;

import java.util.ArrayList;
import java.util.List;


public class ProductWithCategory implements IProduct {
    @Embedded
    public Product product;
    @Relation(parentColumn = "productId",
    entityColumn = "categoryId",
    associateBy = @Junction(ProductCategoryCrossRef.class))
    public List<Category>categories;
    @Relation(parentColumn = "productId",
            entityColumn = "contractorId",
            associateBy = @Junction(ProductContractorCrossRef.class))
    public List<Contractor>contractors;
    @Relation(parentColumn = "productId",
            entityColumn = "transactionId",
            associateBy = @Junction(ProductTransactionCrossRef.class))
    public List<ProductTransaction>productTransactions;

    @Relation(
            parentColumn = "unit_id",
            entityColumn = "unitId"
    )
    public Unit unit;

    public ProductWithCategory(Product product, List<Category> categories,
                               List<Contractor> contractors,
                               List<ProductTransaction> productTransactions, Unit unit) {
        this.product = product;
        this.categories = categories;
        this.contractors = contractors;
        this.productTransactions = productTransactions;
        this.unit = unit;
    }

    @Override
    public long getId() {
        return product.getId();
    }

    @Override
    public String getTitle() {
        return product.getTitle();
    }

    @Override
    public String getDescription() {
        return product.getDescription();
    }

    @Override
    public String getImagePath() {
        return product.getImagePath();
    }

    @Override
    public double getQuantity() {
        return product.getQuantity();
    }

    @Override
    public List<Category> getCategoryList() {
        return new ArrayList<>(categories);
    }

    @Override
    public Unit getUnit() {
        return unit;
    }

    @Override
    public List<Contractor> getContractors() {
        return contractors;
    }

    @Override
    public List<IProductTransactions> getProductTransactions() {
        return new ArrayList<>(productTransactions);
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
