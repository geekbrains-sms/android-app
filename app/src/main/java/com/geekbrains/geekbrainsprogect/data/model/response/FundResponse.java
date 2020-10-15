package com.geekbrains.geekbrainsprogect.data.model.response;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Fund;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FundResponse {
    private List<Fund> funds;

    public List<Fund> getFunds() {
        if(funds == null)
        {
            funds = new ArrayList<>();
        }
        return funds;
    }
}
