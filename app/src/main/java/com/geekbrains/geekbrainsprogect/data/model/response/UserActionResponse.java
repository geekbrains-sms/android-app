package com.geekbrains.geekbrainsprogect.data.model.response;

import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserActionResponse {
    private List<UserAction> userActions;

    public List<UserAction> getUserActions() {
        if(userActions == null)
        {
            userActions = new ArrayList<>();
        }
        return userActions;
    }
}
