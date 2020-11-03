package com.geekbrains.geekbrainsprogect.data.api.dto;


import com.google.gson.annotations.Expose;

public class CategoryTransactionDTO {
    @Expose
    private Long id;
    @Expose
    private String type;
    @Expose
    private Long categoryId;
    @Expose
    private String data;
    @Expose
    private String authorName;

    public CategoryTransactionDTO(Long id, String type, Long categoryId, String data, String authorName) {
        this.id = id;
        this.type = type;
        this.categoryId = categoryId;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
}
