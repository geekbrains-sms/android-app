package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RoleService {
    @GET("/api/v1/roles")
    Observable<List<Role>> getAllRoles();
}
