package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionsListDialog extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.toggle_supply_button)
    ToggleButton supplyToggle;
    @BindView(R.id.toggle_shipment_button)
    ToggleButton shipmentToggle;
    @BindView(R.id.transactions_recycler)
    RecyclerView transactionsList;

    TransactionsListAdapter adapter;
    ProductModel productModel;
    List<ProductTransactionModel>transactions;

    TransactionsListDialog(List<ProductTransactionModel>transactions, ProductModel productModel)
    {
        this.transactions = transactions;
        this.productModel = productModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void createRecycler() {
        transactionsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TransactionsListAdapter(getContext(), transactions, productModel);
        transactionsList.setAdapter(adapter);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.product_transactions_list, null);
        ButterKnife.bind(this, view);
        createRecycler();
        setListeners();
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.transactions_list)
                .setView(view)
                .setPositiveButton(R.string.ok, (dialog, which) -> {}).create();
    }

    public void setListeners()
    {
        shipmentToggle.setOnClickListener(this);
        supplyToggle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        controlToggleChecked();
    }

    private void controlToggleChecked() {
        if(shipmentToggle.isChecked() && supplyToggle.isChecked())
        {
            adapter.setAllTransactions();
        }
        else if(shipmentToggle.isChecked())
        {
            adapter.setOnlyShipmentTransaction();
        }
        else
        {
            adapter.setOnlySupplyTransaction();
        }
    }
}
