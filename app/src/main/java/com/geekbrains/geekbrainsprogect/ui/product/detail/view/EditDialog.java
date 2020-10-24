package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditDialog extends DialogFragment {
    public static final int PRODUCT_NAME = 1;
    public static final int PRODUCT_UNITS = 2;
    public static final int PRODUCT_CATEGORY = 3;
    public static final int PRODUCT_DESCRIPTION = 4;

    private int type;
    private Product product;
    private IOnClick iOnClick;

    @BindView(R.id.edit_dialog_dropbox_autocomplite_layout)
    TextInputLayout autocompliteLayout;
    @BindView(R.id.edit_dialog_autocomlete_edittext)
    AutoCompleteTextView autocopleteText;
    @BindView(R.id.edit_dialog_edit_text_field)
    TextInputEditText editText;

    public EditDialog(Product product, int type, IOnClick iOnClick)
    {
        this.type = type;
        this.product = product;
        this.iOnClick = iOnClick;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        int resource = R.layout.edit_dialog;
        View view = requireActivity().getLayoutInflater().inflate(resource, null);
        ButterKnife.bind(this, view);
        fieldsVisibility();
        setClickListeners();

        return new AlertDialog.Builder(getContext())
                .setView(view)
                .setTitle(R.string.edit_title)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    if(type == PRODUCT_NAME)
                    {
                        product.setTitle(Objects.requireNonNull(editText.getText()).toString());
                    }
                    else if(type == PRODUCT_UNITS)
                    {
                        Unit unit = getProductUnits();
//                        product.setUnits(unit);
                    }
                    else if(type == PRODUCT_DESCRIPTION)
                    {
                        product.setDescription(Objects.requireNonNull(editText.getText()).toString());
                    }
                    iOnClick.onClick(product);
                })
                .create();
    }

    private Unit getProductUnits() {
        return new Unit(0,autocopleteText.getText().toString(), Objects.requireNonNull(editText.getText()).toString());
    }

    private void fieldsVisibility() {
        if(type == PRODUCT_NAME)
        {
            autocompliteLayout.setVisibility(View.GONE);
            editText.setText(product.getTitle());
        }
        else if(type == PRODUCT_DESCRIPTION)
        {
            autocompliteLayout.setVisibility(View.GONE);
            editText.setText(product.getDescription());
        }
        else if(type == PRODUCT_UNITS)
        {
//            editText.setHint(R.string.fullname_unit_hint);
//            ArrayAdapter<Unit>adapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_list_item_1, AppData.getUnitList());
//            autocopleteText.setAdapter(adapter);
//            autocopleteText.setText(product.getUnit().getTitle());
//            editText.setText(product.getUnit().getDescription());
        }
    }

    private void setClickListeners()
    {
//        autocopleteText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                editText.setText(AppData.getUnitList().get(position).getDescription());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    public interface IOnClick
    {
        void onClick(Product product);
    }
}
