package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface RoleDao extends BaseDao<Role> {
    @Query("SELECT * FROM roles")
    Flowable<List<Role>> getAllRoles();
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM roles INNER JOIN user_role_join ON roles.id = user_role_join.roleId WHERE user_role_join.userId = :id")
    Flowable<List<Role>> getRolesByUserId(long id);
    @Query("DELETE FROM roles")
    Completable deleteAllRoles();

}
