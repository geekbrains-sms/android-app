package com.geekbrains.geekbrainsprogect.domain.model;

public class UnitModel {
    private String title;
    private String description;

    public UnitModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
