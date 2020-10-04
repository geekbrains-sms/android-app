package com.geekbrains.geekbrainsprogect.ui.product.model;

import com.geekbrains.geekbrainsprogect.ui.product.model.Product;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
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
