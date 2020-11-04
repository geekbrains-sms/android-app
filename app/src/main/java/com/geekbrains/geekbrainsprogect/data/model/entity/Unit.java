package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity(tableName = "unit")
public class Unit {
    @PrimaryKey
    @ColumnInfo(name = "unitId", index = true)
    @Expose
    private long id;
    @Expose
    private String title;
    @Expose
    private String description;

    public Unit(long id,String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title; }
}
