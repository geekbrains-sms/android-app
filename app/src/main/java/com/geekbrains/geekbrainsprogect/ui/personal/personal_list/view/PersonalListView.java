package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;

import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;
import com.geekbrains.geekbrainsprogect.ui.base.ListView;

import java.util.List;

public interface PersonalListView extends ListView<UserModel> {
    void showEditDialog(UserModel item, List<Role>roles);
    void showAddPersonalDialog(List<Role> roles);
}
