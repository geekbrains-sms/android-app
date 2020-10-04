package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> implements Filterable {
    Context context;
    List<Fund>productList = new ArrayList<>();
    List<Fund>filteredList = new ArrayList<>();
    List<Fund>selectedProduct = new ArrayList<>();
    IOnClickListener iOnClickListener;
    boolean checkedMode = false;
    long checkedItem = 0;

    public void setProductList(Context context, List<Fund> productList) {
        this.context = context;
        this.productList = productList;
        filteredList = productList;
        Log.d("Adapter", "productList: " + productList.size());
        Log.d("Adapter", "filteredList: " + filteredList.size());
        notifyDataSetChanged();
    }

    public void setIOnClickListener(IOnClickListener iOnClickListener) {
        this.iOnClickListener = iOnClickListener;
    }

    public boolean isCheckedMode() {
        return checkedMode;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(filteredList.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public List<Fund> getSelectedProduct() {
        return selectedProduct;
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty())
                {
                    filteredList = productList;
                }
                else
                {
                    List<Fund>filtered = new ArrayList<>();
                    for(Fund fund: productList)
                    {
                        if(fund.getProduct().getTitle().toLowerCase().contains(charString.toLowerCase()))
                        {
                            filtered.add(fund);
                        }
                    }
                    filteredList = filtered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                Log.d("Adapter", "productList: " + productList.size());
                Log.d("Adapter", "filteredList: " + filteredList.size());
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<Fund>) results.values;
                Log.d("Adapter", "productList: " + productList.size());
                Log.d("Adapter", "filteredList: " + filteredList.size());
                notifyDataSetChanged();
            }
        };
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, MaterialCardView.OnCheckedChangeListener {
        @BindView(R.id.card_view)
        MaterialCardView cardView;
        @BindView(R.id.product_name)
        TextView name;
        @BindView(R.id.product_units)
        TextView units;
        @BindView(R.id.product_description)
        TextView description;
        @BindView(R.id.product_category)
        TextView category;
        @BindView(R.id.product_count)
        TextView count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            cardView.setOnClickListener(this);
            cardView.setOnLongClickListener(this);
            cardView.setOnCheckedChangeListener(this);
        }
        public void bind(Fund fund) {
            Product product = fund.getProduct();
            name.setText(product.getTitle());
            description.setText(context.getString(R.string.description_field, product.getDescription()));
            count.setText(fund.getStringBalance());
            units.setText(product.getUnitsTitle());
            StringBuilder builder = new StringBuilder();
            for(Category category : product.getCategoryList())
            {
                builder.append(category.getTitle()).append("; ");
            }
            category.setText(context.getString(R.string.category_field, builder.toString()));
        }

        @Override
        public void onClick(View v) {
            if(!checkedMode)
            {
                if(selectedProduct.size() > 0)
                {
                    selectedProduct.set(0, filteredList.get(getAdapterPosition()));
                }
                else
                {
                    selectedProduct.add(0, filteredList.get(getAdapterPosition()));
                }
                iOnClickListener.onSingleClick();
            }
            else
            {
                checkedControl();
            }
        }

        private void resetCard()
        {
            if(cardView.isChecked())
            {
                if(checkedItem == 0)
                {
                    cardView.setChecked(false);
                }
            }
        }

        private void checkedControl() {
            if(cardView.isChecked())
            {
                cardView.setChecked(false);
            }
            else
            {
                cardView.setChecked(true);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(!checkedMode)
            {
                checkedMode = true;
                selectedProduct.clear();
                iOnClickListener.onClick();
            }
            checkedControl();
            return true;
        }

        @Override
        public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
            if(isChecked)
            {
                checkedItem++;
                Fund fund = filteredList.get(getAdapterPosition());
                if(!selectedProduct.contains(fund))
                {
                    selectedProduct.add(fund);
                }
            }
            else
            {
                checkedItem--;
                Fund fund = filteredList.get(getAdapterPosition());
                selectedProduct.remove(fund);
            }
            if(checkedItem == 0)
            {
                iOnClickListener.onClick();
                checkedMode = false;
            }
        }
    }

    public interface IOnClickListener
    {
        void onClick();
        void onSingleClick();
    }

}
