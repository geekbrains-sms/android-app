package com.geekbrains.geekbrainsprogect.ui.product.actions.view;

import com.geekbrains.geekbrainsprogect.ui.product.actions.model.UserAction;

import java.util.List;

import moxy.MvpView;
import okhttp3.ResponseBody;

public interface ActionProductView extends MvpView {
    void setDataToAdapter(List<UserAction> body);
    void setAlertDialog(String text);
}
