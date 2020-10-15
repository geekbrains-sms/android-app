package com.geekbrains.geekbrainsprogect.data.model.response;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponse {
    private List<Category> categories;

    public List<Category> getCategories() {
        if(categories == null)
        {
            categories = new ArrayList<>();
        }
        return categories;
    }
}
