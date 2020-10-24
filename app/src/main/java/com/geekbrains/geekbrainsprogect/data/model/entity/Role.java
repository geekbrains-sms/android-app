package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
@Entity(tableName = "roles")
public class Role {
    @PrimaryKey
    public int id;
    public String name;
    public String title;

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
}
