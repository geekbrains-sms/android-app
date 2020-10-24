package com.geekbrains.geekbrainsprogect.data.api.dto;

public class AuthDTO {
    String username;
    String password;

    public AuthDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
