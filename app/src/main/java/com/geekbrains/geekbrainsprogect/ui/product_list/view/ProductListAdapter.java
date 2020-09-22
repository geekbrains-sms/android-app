package com.geekbrains.geekbrainsprogect.ui.product_list.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    IRecyclerProduct iRecyclerProduct;

    public void setRecyclerProduct(IRecyclerProduct iRecyclerProduct) {
        this.iRecyclerProduct = iRecyclerProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        iRecyclerProduct.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return iRecyclerProduct.getItemCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements IViewHolderProduct {
        @BindView(R.id.product_name)
        TextView name;
        @BindView(R.id.product_description)
        TextView description;
        @BindView(R.id.product_category)
        TextView category;
        @BindView(R.id.product_count)
        TextView count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(Product product) {
            name.setText(product.getTitle());
            description.setText(product.getDescription());
            count.setText(product.getQuantity() + "");
        }

        @Override
        public int getPos() {
            return getAdapterPosition();
        }
    }
}
