package com.geekbrains.geekbrainsprogect.ui.product.category.view;

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
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> implements Filterable {
    List<Category>allCategory = new ArrayList<>();
    List<Category>filterCategory = new ArrayList<>();
    Context context;
    ISelectItemListener selectItemListener;

    public void setAllCategory(Context context, List<Category> allCategory) {
        this.allCategory = allCategory;
        filterCategory.clear();
        filterCategory.addAll(allCategory);
        this.context = context;
        notifyDataSetChanged();
    }

    public void setSelectItemListener(ISelectItemListener selectItemListener) {
        this.selectItemListener = selectItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(filterCategory.get(position));
    }

    @Override
    public int getItemCount() {
        return filterCategory.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty())
                {
                    filterCategory = allCategory;
                }
                else
                {
                    List<Category>filtered = new ArrayList<>();
                    for(Category category: allCategory)
                    {
                        if(category.getTitle().contains(charString.toLowerCase()))
                        {
                            filtered.add(category);
                        }
                    }
                    filterCategory = filtered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterCategory;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterCategory = (ArrayList<Category>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @BindView(R.id.category_name)
        TextView categoryName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            categoryName.setOnClickListener(this);
        }

        public void bind(Category category)
        {
            categoryName.setText(category.getTitle());
        }

        @Override
        public void onClick(View v) {
            Category category = null;
            if(getAdapterPosition() != 0)
            {
                category = filterCategory.get(getAdapterPosition());
            }
            selectItemListener.onItemClick(category);
        }
    }

    public interface ISelectItemListener
    {
        void onItemClick(Category category);
    }
}
