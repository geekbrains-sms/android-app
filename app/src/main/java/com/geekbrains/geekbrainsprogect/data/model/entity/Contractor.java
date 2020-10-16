package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contractor")
public class Contractor{
    @PrimaryKey
    private long id;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
