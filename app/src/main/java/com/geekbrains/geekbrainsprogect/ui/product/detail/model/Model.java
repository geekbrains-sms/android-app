package com.geekbrains.geekbrainsprogect.ui.product.detail.model;

import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private int page = -1;
    private List<ProductModel>productList = new ArrayList<>();
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public List<ProductModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductModel> productList) {
        this.productList = productList;
    }
}
