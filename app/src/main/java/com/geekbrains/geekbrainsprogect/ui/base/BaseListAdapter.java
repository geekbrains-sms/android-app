package com.geekbrains.geekbrainsprogect.ui.base;

import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<T extends Item, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements Filterable {

    private List<T> itemList;
    private List<T> filteredItem = new ArrayList<>();
    private List<T> selectedList;
    private Context context;
    private boolean checkedMode = false;
    private long checkedItemCount = 0;
    private IOnItemClickListener<T> onItemClickListener;


    public BaseListAdapter(Context context)
    {
        this.itemList = new ArrayList<>();
        this.context = context;
        filteredItem = new ArrayList<>();
        selectedList = new ArrayList<>();
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
//
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
