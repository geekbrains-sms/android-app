package com.geekbrains.geekbrainsprogect.ui.product.detail.model;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.List;

public class EditProductData {
    private ProductModel product;
    private List<Unit> unitList;
    private List<Category>categoryList;

    public EditProductData(ProductModel product, List<Unit> unitList, List<Category> categoryList) {
        this.product = product;
        this.unitList = unitList;
        this.categoryList = categoryList;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
