package com.geekbrains.geekbrainsprogect.data;

import com.geekbrains.geekbrainsprogect.ui.base.Item;

public class Contractor implements Item {
    private long id;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getItemName() {
        return title;
    }
}
