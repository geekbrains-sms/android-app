package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.geekbrains.geekbrainsprogect.ui.base.Item;

@Entity(tableName = "user_action")
public class UserAction implements Item {
    @PrimaryKey
    public Long id;
    public String type;
    public Long productId;
    public String productName;
    public String data;
    public String authorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String getItemName() {
        return toString();
    }
}
