package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.geekbrains.geekbrainsprogect.ui.base.Item;
import com.google.gson.annotations.Expose;

@Entity(tableName = "contractor")
public class Contractor implements Item {
    @PrimaryKey
    @ColumnInfo(name = "contractorId", index = true)
    @Expose
    public long id;
    @Expose
    public String title;

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

    @Override
    public String getItemName() {
        return title;
    }
}
