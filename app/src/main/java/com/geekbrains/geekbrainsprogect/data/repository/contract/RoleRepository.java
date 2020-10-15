package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.response.RoleResponse;

import io.reactivex.Observable;

public interface RoleRepository {
    Observable<RoleResponse> getAllRoles();
}
