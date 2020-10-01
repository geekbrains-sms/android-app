package com.geekbrains.geekbrainsprogect.data;

public class Contractor {
    private long id;
    private String title;

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
