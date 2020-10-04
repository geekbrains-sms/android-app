package com.geekbrains.geekbrainsprogect.ui.product.category.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    List<Category>allCategory = new ArrayList<>();
    List<Category>filterCategory = new ArrayList<>();
    Context context;

    public void setAllCategory(Context context,List<Category> allCategory) {
        this.allCategory = allCategory;
        this.context = context;
        notifyDataSetChanged();
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

    class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.category_name)
        TextView categoryName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Category category)
        {
            categoryName.setText(category.getTitle());
        }
    }
}
