package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import com.geekbrains.geekbrainsprogect.ui.auth.model.AuthData;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogTransaction extends AppCompatDialogFragment {
    private static final String TAG = "DialogTransaction";
    @BindView(R.id.providers_auto_complete)
    AutoCompleteTextView providersAutoComplete;
    @BindView(R.id.count_product_supply_edit_text)
    TextInputEditText editCountProduct;
    @BindView(R.id.comment_edit_text_transaction)
    TextInputEditText commentTransaction;
    public static final int TYPE_SHIPMENT = 0;
    public static final int TYPE_SUPPLY = 1;
    private int type;
    private List<Contractor> contractorList;
    private DialogInterface.OnClickListener positiveButtonListener;
    private IOnClickListener onClickListener;
    private Contractor selectedContractor;
    private ProductModel product;

    public DialogTransaction(ProductModel product, List<Contractor>contractors, int type, IOnClickListener iOnClickListener)
    {
        this.onClickListener = iOnClickListener;
        this.product = product;
        this.type = type;
        contractorList = contractors;
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
            long count = Integer.parseInt(Objects.requireNonNull(editCountProduct.getText()).toString());
            if(type == TYPE_SHIPMENT)
            {
                count *= -1;
            }


            if(selectedContractor != null && count != 0)
            {
                String date = new Date().toString();
                ProductTransactionModel productTransaction = new ProductTransactionModel(0, selectedContractor, AuthData.getCurrentUser(), date, count, Objects.requireNonNull(commentTransaction.getText()).toString(), product.getId());
                onClickListener.onClick(productTransaction);
            }
        };
    }

    private void createAdapter()
    {
        ArrayAdapter<Contractor>contractorArrayAdapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_list_item_1, contractorList);
        providersAutoComplete.setAdapter(contractorArrayAdapter);

        providersAutoComplete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                assert selectedContractor != null;
                Log.d(TAG, "selectedContractor = " + selectedContractor.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "selectedContractor = " + Objects.requireNonNull(selectedContractor).toString());
            }
        });

        providersAutoComplete.setOnItemClickListener((parent, view, position, id) -> {
            selectedContractor = contractorArrayAdapter.getItem(position);
            Log.d(TAG, "selectedContractor = " + Objects.requireNonNull(selectedContractor).toString());
        });
    }

    interface IOnClickListener
    {
        void onClick(ProductTransactionModel productTransaction);
    }

}
