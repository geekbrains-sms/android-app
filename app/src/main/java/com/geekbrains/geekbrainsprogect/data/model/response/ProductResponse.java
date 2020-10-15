package com.geekbrains.geekbrainsprogect.data.model.response;

import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {
    private List<Product>products;

    public List<Product> getProducts() {
        if(products == null)
        {
            products = new ArrayList<>();
        }
        return products;
    }
}
