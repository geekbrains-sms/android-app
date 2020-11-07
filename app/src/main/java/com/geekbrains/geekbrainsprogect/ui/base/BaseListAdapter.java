package com.geekbrains.geekbrainsprogect.ui.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.ui.product.category.view.CategoryListAdapter;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<T extends Item, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements Filterable {

    private List<T> itemList;
    private List<T> filteredItem;
    private final List<T> selectedList;
    private final Context context;
    private boolean checkedMode = false;
    private long checkedItemCount = 0;
    private IOnItemClickListener<T> onItemClickListener;
    private int itemResource;


    public BaseListAdapter(Context context, int itemResource)
    {
        this.itemList = new ArrayList<>();
        this.context = context;
        filteredItem = new ArrayList<>();
        selectedList = new ArrayList<>();
        this.itemResource = itemResource;
        resetFilterList();
    }

    private void resetFilterList()
    {
        if(filteredItem.size() > 0)
        {
            filteredItem.clear();
        }
        filteredItem.addAll(itemList);
        selectedList.clear();
        notifyDataSetChanged();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                List<T>filtered = new ArrayList<>();
                if(charString.isEmpty())
                {
                    filtered = itemList;
                }
                else
                {
                    for(T item: itemList)
                    {
                        if(item.getItemName().toLowerCase().contains(charString.toLowerCase()))
                        {
                            filtered.add(item);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredItem = (ArrayList<T>)results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setOnItemClickListener(IOnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public IOnItemClickListener<T> getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setItemList(List<T>itemList)
    {
        this.itemList = itemList;
        resetFilterList();
    }

    public int getItemResource() {
        return itemResource;
    }

    public List<T> getFilteredItem() {
        return filteredItem;
    }

    public List<T> getSelectedList() {
        return selectedList;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void setCheckedMode(boolean checkedMode) {
        this.checkedMode = checkedMode;
    }

    public boolean isCheckedMode() {
        return checkedMode;
    }

    public long getCheckedCount() {
        return checkedItemCount;
    }

    public void addCheckedItemCount()
    {
        checkedItemCount++;
    }
    public void removeCheckedItemCount()
    {
        checkedItemCount--;
    }

    public void setCheckedItemCount(long checkedItemCount) {
        this.checkedItemCount = checkedItemCount;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getItemCount() {
        return filteredItem.size();
    }

    public interface IOnItemClickListener<T extends Item>
    {
        void onItemClick(T item);
        void onItemChangeChecked();
    }


}

