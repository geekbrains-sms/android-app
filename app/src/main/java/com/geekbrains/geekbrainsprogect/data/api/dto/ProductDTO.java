package com.geekbrains.geekbrainsprogect.data.api.dto;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.google.gson.annotations.Expose;

import java.util.List;

public class ProductDTO implements IProduct {
    @Expose
    private Long id;
    @Expose
    private String title;
    @Expose
    private List<Category> categories;
    @Expose
    private Unit unit;
    @Expose
    private Image image;
    @Expose
    String description;

    public ProductDTO(Long id, String title, List<Category> categories, Unit unit, Image image, String description) {
        this.id = id;
        this.title = title;
        this.categories = categories;
        this.unit = unit;
        this.image = image;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public List<Contractor> getContractors() {
        return null;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }


    public String getImagePath() {
        return null;
    }

    @Override
    public double getQuantity() {
        return 0;
    }

    public List<Category> getCategoryList() {
        return categories;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
