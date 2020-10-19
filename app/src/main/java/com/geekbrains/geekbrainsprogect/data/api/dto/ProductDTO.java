package com.geekbrains.geekbrainsprogect.data.api.dto;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import java.util.List;

public class ProductDTO {
    private Long id;
    private String title;
    private List<Category> categories;
    private Unit unit;
    private Image image;
    String description;

    public ProductDTO(Long id, String title, List<Category> categories, Unit unit, Image image, String description) {
        this.id = id;
        this.title = title;
        this.categories = categories;
        this.unit = unit;
        this.image = image;
        this.description = description;
    }

    public Long getId() {
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
        return image.getImage();
    }


    public List<Category> getCategoryList() {
        return categories;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
