package com.geekbrains.geekbrainsprogect.data.model.response;

import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UnitsResponse {
    private List<Unit> units;

    public List<Unit> getUnits() {
        if(units == null)
        {
            units = new ArrayList<>();
        }
        return units;
    }
}
