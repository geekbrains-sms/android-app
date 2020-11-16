package com.geekbrains.geekbrainsprogect.ui.product.product_list.model;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;

import java.util.List;

public class UnitsWithCategories {
    private final List<Unit> unitList;
    private final List<Category>categoryList;

    public List<Unit> getUnitList() {
        return unitList;
    }

    public UnitsWithCategories(List<Category> categoryList, List<Unit> unitList) {
        this.unitList = unitList;
        this.categoryList = categoryList;
    }
    public List<Category> getCategoryList() {
        return categoryList;
    }

}
