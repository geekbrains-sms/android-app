package com.geekbrains.geekbrainsprogect.data.api.dto;

import com.google.gson.annotations.Expose;

public class DeletedUserDTO{
    @Expose
    private Long id;
    @Expose
    private UserDTO user;
}
