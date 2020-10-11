package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;

import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.ui.base.BaseView;
import com.geekbrains.geekbrainsprogect.ui.base.ListView;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface PersonalListView extends ListView<User> {}
