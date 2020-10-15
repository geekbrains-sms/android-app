package com.geekbrains.geekbrainsprogect.data.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Role implements Serializable {
    @Expose
    private int id;
    @Expose
    private String name;
    private String title;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
