package com.geekbrains.geekbrainsprogect.ui.product.detail.model;

public class Model {
    private int page = -1;
    private boolean editProduct = false;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isEditProduct() {
        return editProduct;
    }

    public void setEditProduct(boolean editProduct) {
        this.editProduct = editProduct;
    }
}
