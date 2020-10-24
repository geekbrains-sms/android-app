package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;


import java.util.List;

import io.reactivex.Observable;

public interface RoleRepository {
    Observable<List<Role>> getAllRoles();
}
