package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.geekbrains.geekbrainsprogect.data.model.entity.cross.ProductCategoryCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.cross.ProductContractorCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;

import java.util.ArrayList;
import java.util.List;


public class ProductWithCategory implements IProduct {
    @Embedded
    private final Product product;
    @Relation(parentColumn = "productId",
    entityColumn = "categoryId",
    associateBy = @Junction(ProductCategoryCrossRef.class))
    private final List<Category>categories;
    @Relation(parentColumn = "productId",
            entityColumn = "contractorId",
            associateBy = @Junction(ProductContractorCrossRef.class))
    private final List<Contractor>contractors;


    @Relation(
            parentColumn = "unit_id",
            entityColumn = "unitId"
    )
    public Unit unit;

    public ProductWithCategory(Product product, List<Category> categories,
                               List<Contractor> contractors, Unit unit) {
        this.product = product;
        this.categories = categories;
        this.contractors = contractors;
        this.unit = unit;
    }

    public Product getProduct() {
        return product;
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

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
