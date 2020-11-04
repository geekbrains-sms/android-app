package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.geekbrains.geekbrainsprogect.ui.base.Item;
import com.google.gson.annotations.Expose;

import java.io.Serializable;

@Entity(tableName = "category")
public class Category implements Item, Serializable {
    @PrimaryKey
    @ColumnInfo(name = "categoryId")
    @Expose
    private long id;
    @Expose
    private String title;


    public Category(long id, String title) {
        this.id = id;
        this.title = title;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setTitle(String title) {
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
