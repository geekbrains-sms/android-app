package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product.model.Unit;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateProductDialog extends DialogFragment{
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

    private Category selectedCategory;
    private IOnClickListener iOnClickListener;

    public CreateProductDialog(IOnClickListener iOnClickListener)
    {
        this.iOnClickListener = iOnClickListener;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.create_product_dialog, null);
        ButterKnife.bind(this, view);
        setAdapters();

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
    }

    private void setAdapters() {
        ArrayAdapter<Category>categoryArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, AppData.getCategoryList());
        ArrayAdapter<Unit>unitsArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, AppData.getUnitList());

        editUnit.setAdapter(unitsArrayAdapter);
        editCategory.setAdapter(categoryArrayAdapter);
    }

    private void setListener() {
        editUnit.setOnItemClickListener((parent, view, position, id) -> {
                editUnitDescription.setText(AppData.getUnitList().get(position).getDescription());
        });

        editCategory.setOnItemClickListener((parent, view, position, id) -> {
            selectedCategory = AppData.getCategoryList().get(position);
        });

        AlertDialog dialog = (AlertDialog) getDialog();
        assert dialog != null;
        dialog.setOnShowListener(dialog1 -> {
            Button button = ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE);

            button.setOnClickListener(v -> {
                String name = Objects.requireNonNull(editName.getText()).toString();
                String unit = editUnit.getText().toString();
                String unitDescription = Objects.requireNonNull(editUnitDescription.getText()).toString();

                if(checkText(name) && checkText(unit) && checkText(unitDescription) && selectedCategory != null) {

                    List<Category> categories = new ArrayList<>();
                    categories.add(selectedCategory);

                    Unit newUnit = new Unit(unit, unitDescription);
                    String description = Objects.requireNonNull(editDescription.getText()).toString();
                    Product product = new Product(name, description, categories, newUnit);
                    iOnClickListener.onClick(product);
                    dialog1.dismiss();
                }

            });
        });

    }

    private boolean checkText(String string)
    {
        return string != null && !string.trim().equals("");
    }

    public interface IOnClickListener
    {
        void onClick(Product product);
    }
}
