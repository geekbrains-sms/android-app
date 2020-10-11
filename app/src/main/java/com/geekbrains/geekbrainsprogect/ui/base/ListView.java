package com.geekbrains.geekbrainsprogect.ui.base;

import com.geekbrains.geekbrainsprogect.ui.base.BaseView;

import java.util.List;

public interface ListView<T> extends BaseView {
    void setDataToAdapter(List<T> list);
    void updateRecyclerView();
}
