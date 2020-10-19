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
    long id;
    String title;
    String description;
    String imageUrl;
    double quantity;
    @ColumnInfo(name = "unit_id")
    @ForeignKey(entity = Unit.class, parentColumns = "id", childColumns = "unit_id")
    long idUnit;
    String imagePath;


    public Product(long id, String title, String description, long unitId, double quantity, String imageUrl) {
        this.title = title;
        this.description = description;
        this.idUnit = unitId;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
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

    public String getImagePath() {
        return imagePath;
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

    public double getQuantity() {
        return quantity;
    }
}
