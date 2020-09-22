package com.geekbrains.geekbrainsprogect.ui.personal_list.view;

import com.geekbrains.geekbrainsprogect.data.User;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface PersonalListView extends MvpView {
    @StateStrategyType(value = SkipStrategy.class)
    void updateRecyclerView();
    void showToast(String msg);
}
