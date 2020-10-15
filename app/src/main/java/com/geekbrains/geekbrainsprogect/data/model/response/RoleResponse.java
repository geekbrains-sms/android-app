package com.geekbrains.geekbrainsprogect.data.model.response;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RoleResponse {
    private List<Role> roles;

    public List<Role> getRoles() {
        if(roles == null)
        {
            roles = new ArrayList<>();
        }
        return roles;
    }
}
