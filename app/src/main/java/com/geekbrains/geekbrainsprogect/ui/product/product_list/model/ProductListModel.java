package com.geekbrains.geekbrainsprogect.ui.product.product_list.model;

import com.geekbrains.geekbrainsprogect.data.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListModel {
    private List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
