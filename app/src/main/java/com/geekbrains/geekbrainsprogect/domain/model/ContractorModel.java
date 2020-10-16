package com.geekbrains.geekbrainsprogect.domain.model;

public class ContractorModel {
    private long id;
    private String title;

    public ContractorModel(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
