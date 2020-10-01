package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.Contractor;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product.model.ProductTransaction;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogTransaction extends AppCompatDialogFragment {
    @BindView(R.id.providers_auto_complete)
    AutoCompleteTextView providersAutoComplete;
    @BindView(R.id.count_product_supply_edit_text)
    TextInputEditText editCountProduct;
    private DialogInterface.OnClickListener positiveButtonListener;
    private IOnClickListener onClickListener;
    private Contractor selectedContractor;
    private Product product;
    private boolean isSupply;

    public DialogTransaction(Product product, IOnClickListener iOnClickListener, boolean isSupply)
    {
        this.onClickListener = iOnClickListener;
        this.product = product;
        this.isSupply = isSupply;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.supply_dialog, null);
        ButterKnife.bind(this, view);
        supplyControl();
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(R.string.ok, positiveButtonListener)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {dismiss();})
                .create();
    }


    private void supplyControl() {
        createAdapter();
        createListeners();
    }

    private void createListeners() {
        positiveButtonListener = (dialog, which) -> {
            long count = Integer.parseInt(editCountProduct.getText().toString());

                ProductTransaction productTransaction = new ProductTransaction(product, selectedContractor,  count);
                onClickListener.onClick(productTransaction);

        };
    }

    private void createAdapter()
    {
        ArrayAdapter<Contractor>contractorArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()),android.R.layout.simple_list_item_1, AppData.getContractorList());
        providersAutoComplete.setAdapter(contractorArrayAdapter);

        providersAutoComplete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               selectedContractor = contractorArrayAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedContractor = null;
            }
        });
    }

    interface IOnClickListener
    {
        void onClick(ProductTransaction productTransaction);
    }

}
