package com.geekbrains.geekbrainsprogect.ui.product.category.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> implements Filterable {
    private List<Category>allCategory = new ArrayList<>();
    private List<Category>filterCategory = new ArrayList<>();
    private Context context;
    private ISelectItemListener selectItemListener;
    List<Category>selectedCategories = new ArrayList<>();
    private boolean checkedMode = false;
    private long checkedItem = 0;

    public void setAllCategory(Context context, List<Category> allCategory) {
        this.allCategory = allCategory;
        resetFilteredCategory();
        this.context = context;
        notifyDataSetChanged();
    }

    private void resetFilteredCategory()
    {
        filterCategory.clear();
        filterCategory.addAll(allCategory);
        filterCategory.add(0, new Category("ВСЕ"));
        selectedCategories.clear();
    }

    public List<Category> getSelectedCategories() {
        return selectedCategories;
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
                    resetFilteredCategory();
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, MaterialCardView.OnCheckedChangeListener
    {
        @BindView(R.id.category_name)
        TextView categoryName;
        @BindView(R.id.category_card)
        MaterialCardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cardView.setOnClickListener(this);
            cardView.setOnLongClickListener(this);
            cardView.setOnCheckedChangeListener(this);
        }

        public void bind(Category category)
        {
            categoryName.setText(category.getTitle());
        }


        @Override
        public void onClick(View v) {

            if(!checkedMode)
            {
                Category category = null;
                if(!filterCategory.get(getAdapterPosition()).getTitle().equals("ВСЕ"))
                {
                    category = filterCategory.get(getAdapterPosition());
                }
                selectItemListener.onItemClick(category);
            }
            else
            {
                checkedControl();
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(!checkedMode)
            {
                checkedMode = true;
                selectedCategories.clear();
                selectItemListener.onLongItemClick();
            }
            checkedControl();
            return true;
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
        public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
            if(isChecked)
            {
                checkedItem++;
                Category category = filterCategory.get(getAdapterPosition());
                if(!selectedCategories.contains(category))
                {
                    selectedCategories.add(category);
                }
            }
            else
            {
                checkedItem--;
                Category category = filterCategory.get(getAdapterPosition());
                selectedCategories.remove(category);
            }
            if(checkedItem == 0)
            {
                selectItemListener.onLongItemClick();
                checkedMode = false;
            }
        }
    }
    public interface ISelectItemListener
    {
        void onItemClick(Category category);
        void onLongItemClick();
    }
    }


