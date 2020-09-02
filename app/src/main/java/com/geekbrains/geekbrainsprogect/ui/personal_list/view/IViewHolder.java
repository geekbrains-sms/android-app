package com.geekbrains.geekbrainsprogect.ui.personal_list.view;

import com.geekbrains.geekbrainsprogect.data.User;

public interface IViewHolder {
    void bind(User user);
    int getPos();
}
