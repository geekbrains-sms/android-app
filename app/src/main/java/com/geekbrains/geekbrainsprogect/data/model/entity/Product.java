package com.geekbrains.geekbrainsprogect.data.model.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.geekbrains.geekbrainsprogect.ui.base.Item;

import java.util.List;

@Entity(tableName = "product")
public class Product implements IProduct {
    @PrimaryKey
    @ColumnInfo(name = "productId", index = true)
    public long id;
    public String title;
    public String description;
    public String imageUrl;
    public double quantity;
    @ColumnInfo(name = "unit_id")
    public long idUnit;



    public Product(long id, String title, String description, long idUnit, double quantity, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.idUnit = idUnit;
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
        return imageUrl;
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
        this.imageUrl = imagePath;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public List<Category> getCategoryList() {
        return null;
    }

    @Override
    public Unit getUnit() {
        return null;
    }

    @Override
    public List<Contractor> getContractors() {
        return null;
    }

    public long getIdUnit() {
        return idUnit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                ", idUnit=" + idUnit +
                '}';
    }
}
