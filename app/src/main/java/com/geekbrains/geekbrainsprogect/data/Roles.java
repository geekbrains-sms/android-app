package com.geekbrains.geekbrainsprogect.data;

public enum Roles {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    String title;



    Roles(String title)
    {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
