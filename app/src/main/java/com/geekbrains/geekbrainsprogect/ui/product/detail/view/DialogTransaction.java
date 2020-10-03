package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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
    private DialogInterface.OnClickListener positiveButtonListener;
    private IOnClickListener onClickListener;
    private Contractor selectedContractor;
    private Product product;

    public DialogTransaction(Product product, IOnClickListener iOnClickListener)
    {
        this.onClickListener = iOnClickListener;
        this.product = product;
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

            if(selectedContractor != null && count != 0)
            {
                ProductTransaction productTransaction = new ProductTransaction(product, selectedContractor,  count, Objects.requireNonNull(commentTransaction.getText()).toString());
                onClickListener.onClick(productTransaction);
            }
        };
    }

    private void createAdapter()
    {
        ArrayAdapter<Contractor>contractorArrayAdapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_list_item_1, AppData.getContractorList());
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

        providersAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedContractor = contractorArrayAdapter.getItem(position);
                Log.d(TAG, "selectedContractor = " + Objects.requireNonNull(selectedContractor).toString());
            }
        });
    }

    interface IOnClickListener
    {
        void onClick(ProductTransaction productTransaction);
    }

}
