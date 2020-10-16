package com.geekbrains.geekbrainsprogect.domain.model;

import com.geekbrains.geekbrainsprogect.ui.base.Item;

public class CategoryModel implements Item {
    private long id;
    private String title;

    public CategoryModel(long id, String title) {
        this.id = id;
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public long getId() {
        return id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getItemName() {
        return title;
    }
}
