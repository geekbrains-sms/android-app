package com.geekbrains.geekbrainsprogect.ui.product.model;

import com.geekbrains.geekbrainsprogect.ui.product.model.Product;

import java.util.List;

public class Category {
    long id;
    String title;


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

    @Override
    public String toString() {
        return title;
    }
}
