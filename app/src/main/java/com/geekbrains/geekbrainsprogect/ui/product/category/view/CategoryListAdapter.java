package com.geekbrains.geekbrainsprogect.ui.product.category.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.base.BaseListAdapter;
import com.geekbrains.geekbrainsprogect.ui.base.CardViewHolder;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListAdapter extends BaseListAdapter<Category, CategoryListAdapter.ViewHolder> {


    public CategoryListAdapter(Context context) {
        super(context, R.layout.category_item);
    }

    @Override
    public void setItemList(List<Category> itemList) {
        itemList.add(0, new Category(-1, "ВСЕ"));
        super.setItemList(itemList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemResource(), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getFilteredItem().get(position));
    }


    class ViewHolder extends CardViewHolder<Category> implements View.OnClickListener, View.OnLongClickListener, MaterialCardView.OnCheckedChangeListener {
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

        public void bind(Category category) {
            categoryName.setText(category.getTitle());

            if(category.getId() < 0)
            {
                cardView.setCheckable(false);
            }
        }


        @Override
        public void onClick(View v) {
            if (!isCheckedMode()) {
                if (getOnItemClickListener() != null) {
                    getOnItemClickListener().onItemClick(getFilteredItem().get(getAdapterPosition()));
                }
            } else {
                checkedControl((MaterialCardView) v);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (!isCheckedMode()) {
                setCheckedMode(true);
                getSelectedList().clear();
                getOnItemClickListener().onItemChangeChecked();
            }
            checkedControl((MaterialCardView) v);
            return true;
        }

        @Override
        public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
            Category category = getFilteredItem().get(getAdapterPosition());
            if (isChecked) {

                addCheckedItemCount();

                if (!getSelectedList().contains(category)) {
                    getSelectedList().add(category);
                }
            } else {
                removeCheckedItemCount();
                getSelectedList().remove(category);
            }
            if (getCheckedCount() == 0) {
                getOnItemClickListener().onItemChangeChecked();
                setCheckedMode(false);
            }
        }
    }
}


