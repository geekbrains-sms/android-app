package com.geekbrains.geekbrainsprogect.data;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Role implements Serializable {
    @Expose
    private int id;
    @Expose
    private String name;

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

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
