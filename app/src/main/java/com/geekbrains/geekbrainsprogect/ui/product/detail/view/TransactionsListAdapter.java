package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.ViewHolder> {
    private Context context;
    private List<ProductTransactionModel>allTransactions;
    private List<ProductTransactionModel>filteredTransactions = new ArrayList<>();
    private List<ProductTransactionModel>supplyTransactions = new ArrayList<>();
    private List<ProductTransactionModel>shipmentTransactions = new ArrayList<>();

    public TransactionsListAdapter(Context context, List<ProductTransactionModel>productTransactions)
    {
        this.context = context;
        allTransactions = productTransactions;
        filteredTransactions.addAll(allTransactions);
        addSupplyTransactions();
        addShipmentTransaction();
    }

    public void setOnlySupplyTransaction()
    {
        filteredTransactions = new ArrayList<>();
        filteredTransactions.addAll(supplyTransactions);
        notifyDataSetChanged();
    }

    private void addSupplyTransactions() {
        for(ProductTransactionModel transaction: allTransactions)
        {
            if(transaction.getQuantity() > 0)
            {
                supplyTransactions.add(transaction);
            }
        }
    }

    public void setOnlyShipmentTransaction()
    {
        filteredTransactions = new ArrayList<>();
        filteredTransactions.addAll(shipmentTransactions);
        notifyDataSetChanged();
    }

    private void addShipmentTransaction() {
        for(ProductTransactionModel transaction: allTransactions)
        {
            if(transaction.getQuantity() < 0)
            {
                shipmentTransactions.add(transaction);
            }
        }
    }

    public void setAllTransactions()
    {
        filteredTransactions = new ArrayList<>();
        filteredTransactions.addAll(allTransactions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsListAdapter.ViewHolder holder, int position) {
        holder.bind(filteredTransactions.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredTransactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_name_transaction)
        TextView productName;
        @BindView(R.id.type_transaction)
        TextView type;
        @BindView(R.id.comment_transaction)
        TextView comment;
        @BindView(R.id.user_transaction)
        TextView userName;
        @BindView(R.id.date_trasaction)
        TextView date;
        @BindView(R.id.count_transaction)
        TextView count;
        @BindView(R.id.units_product_trasaction)
        TextView units;
        @BindView(R.id.contractor_transaction)
        TextView contractor;
        @BindView(R.id.transactions_card_view)
        MaterialCardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ProductTransactionModel transaction)
        {
//            productName.setText(context.getString(R.string.product_name_field, transaction.getProduct().getTitle()));
            if(transaction.getQuantity() > 0)
            {
                type.setText(context.getString(R.string.type_transaction_field, context.getString(R.string.supply)));
                cardView.setStrokeColor(Color.GREEN);
                contractor.setText(context.getString(R.string.provider_field_transaction, transaction.getContractor().getTitle()));
            }
            else
            {
                type.setText(context.getString(R.string.type_transaction_field, context.getString(R.string.shipment)));
                cardView.setStrokeColor(Color.RED);
                contractor.setText(context.getString(R.string.contractor_field_transaction, transaction.getContractor().getTitle()));
            }
            comment.setText(context.getString(R.string.comment_trasaction_field, transaction.getComment()));
//            userName.setText(context.getString(R.string.operator, transaction.getUser().getFullname()));
//            date.setText(transaction.getDate().toString());
            count.setText(transaction.getQuantity() + "");
//            units.setText(transaction.getProduct().getUnitsTitle());

        }
    }
}
