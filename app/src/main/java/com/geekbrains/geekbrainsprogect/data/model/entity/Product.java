package com.geekbrains.geekbrainsprogect.data.model.entity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "product")
public class Product {
    @PrimaryKey
    int id;
    String title;
    String description;
    @ColumnInfo(name = "unit_id")
    @ForeignKey(entity = Unit.class, parentColumns = "id", childColumns = "unit_id")
    long idUnit;
    String imagePath;
    @Ignore
    List<Category>categories;


    public Product(String title, String description, List<Category>categories, long unitId) {
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.idUnit = unitId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public List<Category> getCategoryList() {
        return categories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categories = categoryList;
    }



}
