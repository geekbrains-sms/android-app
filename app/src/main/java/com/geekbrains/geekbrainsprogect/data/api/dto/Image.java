package com.geekbrains.geekbrainsprogect.data.api.dto;

import com.google.gson.annotations.Expose;

public class Image {
    @Expose
    private Long id;
    @Expose
    private String title;
    @Expose
    private String image;

    public Image(Long id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
