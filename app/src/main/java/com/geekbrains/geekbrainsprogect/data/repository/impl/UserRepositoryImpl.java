package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.api.service.RoleService;
import com.geekbrains.geekbrainsprogect.data.api.service.UserService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.RoleDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UserDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UserRoleCrossDao;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.UserMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.cross.UserRoleCrossRef;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UserRepository;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UserRepositoryImpl implements UserRepository {
    UserDao userDao;
    UserService userService;
    UserMapper userMapper;
    RoleDao roleDao;
    RoleService roleService;
    UserRoleCrossDao userRoleCrossDao;
    @Inject
    public UserRepositoryImpl(UserDao userDao, UserService userService, UserMapper userMapper, RoleDao roleDao, RoleService roleService, UserRoleCrossDao userRoleCrossDao) {
        this.userDao = userDao;
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleDao = roleDao;
        this.roleService = roleService;
        this.userRoleCrossDao = userRoleCrossDao;
    }




    @Override
    public Flowable<List<UserModel>> getUserList() {
        return userDao.getAllUser()
                .map(x -> userMapper.toModelList(x));
    }

    @Override
    public Completable saveUsersFromServerToDB() {
        return getRolesFromServer().andThen(userService.getActualUsers()
                .doOnNext(x -> {
                        userDao.deleteAllUsers();
                        userDao.insertAll(userMapper.toEntityList(x));

                }))
                .flatMapIterable(x -> x)
                .flatMapCompletable(x -> Completable.fromRunnable(()->{
                    for(Role role : x.getRoles())
                    {
                        userRoleCrossDao.insert(new UserRoleCrossRef(x.getId(), role.getId()));
                    }
                }));
    }

    @Override
    public Completable editUser(UserModel user) {
        return userService.editUser(user.getId(), userMapper.toDto(user))
                .flatMapCompletable(x -> Completable.fromRunnable(() -> {
                    userDao.update(userMapper.toEntity(user));
                }));
    }

    @Override
    public Completable addUser(UserModel user) {
        return userService.addUser(userMapper.toDto(user))
                                .map(x -> userMapper.toEntity(x))
                                .flatMapCompletable(x -> Completable.fromRunnable(()-> userDao.insert(x)));
    }

    @Override
    public Completable deleteUser(long id) {
        return userService.deleteUser(id)
                .flatMapCompletable(x -> Completable.fromRunnable(() -> userDao.deleteUserById(id)));
    }

    @Override
    public Flowable<List<Role>> getRoleList() {
        return roleDao.getAllRoles();
    }

    private Completable getRolesFromServer()
    {
        return roleService.getAllRoles()
                .flatMapCompletable(x -> roleDao.deleteAllRoles().andThen(Completable.fromRunnable(() -> roleDao.insertAll(x))));
    }
}
