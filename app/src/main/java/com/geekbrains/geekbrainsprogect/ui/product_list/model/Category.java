package com.geekbrains.geekbrainsprogect.ui.product_list.model;

import com.geekbrains.geekbrainsprogect.ui.product.model.Product;

import java.util.List;

public class Category {
    long id;
    String title;
    String description;
    List<Product> products;

    public Category(String title)
    {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Product> getProducts() {
        return products;
    }
}
