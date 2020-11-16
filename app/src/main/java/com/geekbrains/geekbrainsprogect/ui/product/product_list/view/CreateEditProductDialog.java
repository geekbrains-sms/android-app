package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.ui.base.BaseDialog;
import com.geekbrains.geekbrainsprogect.ui.base.Item;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.model.UnitsWithCategories;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateEditProductDialog extends BaseDialog {
    IOnClickListener iOnClickListener;

    @BindView(R.id.edit_name_create_product)
    TextInputEditText editName;
    @BindView(R.id.edits_description_create_product)
    TextInputEditText editDescription;
    @BindView(R.id.edit_unit_create_product)
    AutoCompleteTextView editUnit;
    @BindView(R.id.edit_unit_description_creat_product)
    TextInputEditText editUnitDescription;
    @BindView(R.id.edit_category_create_product)
    AutoCompleteTextView editCategory;
    @BindView(R.id.category_field_layout)
    LinearLayout categoryContainer;

    ProductModel productModel;

    List<Category>allCategory;
    List<Category>selectedCategory;
    private ArrayAdapter<Category> categoryArrayAdapter;
    private final List<Unit> unitList;

    public CreateEditProductDialog(IOnClickListener iOnClickListener, UnitsWithCategories unitsWithCategories)
    {
        this.allCategory = unitsWithCategories.getCategoryList();
        this.iOnClickListener = iOnClickListener;
        unitList = unitsWithCategories.getUnitList();
        selectedCategory = new ArrayList<>();
    }
    public CreateEditProductDialog(ProductModel productModel, UnitsWithCategories unitsWithCategories, IOnClickListener iOnClickListener)
    {
        this.productModel = productModel;
        this.allCategory = unitsWithCategories.getCategoryList();
        this.iOnClickListener = iOnClickListener;
        unitList = unitsWithCategories.getUnitList();
        selectedCategory = new ArrayList<>();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.create_product_dialog, null);
        ButterKnife.bind(this, view);
        setAdapters();
        setListener();

        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.create_product)
                .setView(view)
                .setPositiveButton(R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {})
                .create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setListener();
        super.onActivityCreated(savedInstanceState);
        if(productModel != null)
        {
            setDataToViews();
        }

    }

    private void setDataToViews() {
        for(Category category: productModel.getCategoryList())
        {
            addDataItem(Category.class, category, categoryContainer);
            selectedCategory.add(category);
        }
        updateAdapter(categoryArrayAdapter, allCategory, selectedCategory);
        editName.setText(productModel.getTitle());
        editDescription.setText(productModel.getTitle());
        ArrayAdapter<Unit>adapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_list_item_1, unitList);
        editUnit.setAdapter(adapter);
        editUnit.setText(productModel.getUnit().getTitle());
        editUnitDescription.setText(productModel.getUnit().getDescription());
    }

    @Override
    protected <T extends Item> void addItemToSelectedList(Class<T> type, TextView textView, T item) {
        if(type == Category.class)
        {
            Category category;
            if(item == null)
            {
                category = new Category(0,editCategory.getText().toString());
            }
            else
            {
                category = (Category)item;
            }
            textView.setText(category.getTitle());
            selectedCategory.add(category);
        }
    }


    private void setAdapters() {
        categoryArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, getActualItemList(allCategory, selectedCategory));
        ArrayAdapter<Unit>unitsArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, unitList);
        editUnit.setAdapter(unitsArrayAdapter);
        editCategory.setAdapter(categoryArrayAdapter);
    }

     protected void setListener() {

        editUnit.setOnItemClickListener((parent, view, position, id) -> {
                editUnitDescription.setText(unitList.get(position).getDescription());
        });
    }


    protected void mappedItem(DialogInterface dialogInterface)
    {
        String name = Objects.requireNonNull(editName.getText()).toString();
        String unit = editUnit.getText().toString();
        String unitDescription = Objects.requireNonNull(editUnitDescription.getText()).toString();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(unit) && !TextUtils.isEmpty(unitDescription) && selectedCategory.size() > 0) {
            Unit newUnit = getProductUnits();
            String description = Objects.requireNonNull(editDescription.getText()).toString();
            ProductModel product = new ProductModel(0, name, description, newUnit, null, selectedCategory, null,  0);
            if(productModel != null)
            {
                product.setId(productModel.getId());
                product.setContractors(productModel.getContractors());
                product.setQuantity(productModel.getQuantity());
            }
            iOnClickListener.onClick(product);
            dialogInterface.dismiss();
        }
    }

    @OnClick({R.id.add_category_button})
    void onClick()
    {
        addItemToContainer(Category.class, editCategory, selectedCategory, allCategory, categoryContainer, categoryArrayAdapter);
    }

    @Override
    public void onClick(View v) {
       removeItem(v, categoryContainer, selectedCategory, allCategory, editCategory, categoryArrayAdapter);
    }

    private Unit getProductUnits() {
        return new Unit(0, editUnit.getText().toString(), Objects.requireNonNull(editUnitDescription.getText()).toString());
    }

    public interface IOnClickListener
    {
        void onClick(ProductModel product);
    }
}
