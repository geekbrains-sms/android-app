package com.geekbrains.geekbrainsprogect.data.api.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RoleService {
    @GET("/api/v1/roles")
    Observable<List<RoleDTO>> getAllRoles();
}
