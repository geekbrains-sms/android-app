package com.geekbrains.geekbrainsprogect.data.model.response;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ContractorResponse {
    private List<Contractor> contractors;

    public List<Contractor> getContractors() {
        if(contractors == null)
        {
            contractors = new ArrayList<>();
        }
        return contractors;
    }
}
