package com.geekbrains.geekbrainsprogect.ui.product.actions.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.product.actions.model.UserAction;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionProductAdapter extends RecyclerView.Adapter<ActionProductAdapter.ViewHolder> implements Filterable {
    private List<UserAction> userActionList = new ArrayList<>();
    private List<UserAction> filteredList = new ArrayList<>();

    public void setData(List<UserAction> list)
    {
        userActionList = list;
        filteredList.clear();
        filteredList.addAll(list);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.action_item, parent, false);
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

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                List<UserAction>allFunds = userActionList;
                if(charString.isEmpty())
                {
                    filteredList = allFunds;
                }
                else
                {
                    List<UserAction>filtered = new ArrayList<>();
                    for(UserAction userAction: userActionList)
                    {
                        if(userAction.getProductName().toLowerCase().contains(charString.toLowerCase()))
                        {
                            filtered.add(userAction);
                        }
                    }
                    filteredList = filtered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<UserAction>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.action_text)
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(UserAction userAction)
        {
           text.setText(userAction.toString());
        }
    }
}
