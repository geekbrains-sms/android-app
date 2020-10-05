package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;

import com.geekbrains.geekbrainsprogect.data.User;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface PersonalListView extends MvpView {
    @StateStrategyType(value = SkipStrategy.class)
    void updateRecyclerView();
    void showToast(int msg);
    void setDataToAdapter(List<User> body);
    void showAlertDialog(String string);
}
