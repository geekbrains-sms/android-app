package com.geekbrains.geekbrainsprogect.domain.model;

import com.geekbrains.geekbrainsprogect.ui.base.Item;

public class UserActionModel implements Item {
    private Long id;
    private String type;
    private Long productId;
    private String productName;
    private String data;
    private String authorName;

    public UserActionModel(Long id, String type, Long productId, String productName, String data, String authorName) {
        this.id = id;
        this.type = type;
        this.productId = productId;
        this.productName = productName;
        this.data = data;
        this.authorName = authorName;
    }

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
        return productName + " " + authorName;
    }
}
