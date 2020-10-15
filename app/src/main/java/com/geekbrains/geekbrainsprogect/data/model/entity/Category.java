package com.geekbrains.geekbrainsprogect.data.model.entity;

import com.geekbrains.geekbrainsprogect.ui.base.Item;

import java.io.Serializable;

public class Category implements Serializable, Item {
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

    @Override
    public String getItemName() {
        return title;
    }
}
