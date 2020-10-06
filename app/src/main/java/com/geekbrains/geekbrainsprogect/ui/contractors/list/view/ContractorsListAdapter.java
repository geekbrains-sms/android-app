package com.geekbrains.geekbrainsprogect.ui.contractors.list.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.Contractor;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContractorsListAdapter extends RecyclerView.Adapter<ContractorsListAdapter.ViewHolder> implements Filterable {
    private List<Contractor> allContractors = new ArrayList<>();
    private List<Contractor> filteredContractors = new ArrayList<>();
    private List<Contractor> selectedContractors = new ArrayList<>();
    private IOnItemClickListener onItemClickListener;
    private IOnCheckedClickListener onCheckedClickListener;
    private boolean checkedMode = false;
    private long checkedItem = 0;

    public void setOnItemClickListener(IOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnCheckedClickListener(IOnCheckedClickListener onCheckedClickListener) {
        this.onCheckedClickListener = onCheckedClickListener;
    }

    public void setAllContractor(List<Contractor> allUsers) {
        this.allContractors = allUsers;
        filteredContractors.clear();
        filteredContractors.addAll(allUsers);
        notifyDataSetChanged();
    }

    public List<Contractor> getSelectedContractors() {
        return selectedContractors;
    }

    @NonNull
    @Override
    public ContractorsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contractor_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractorsListAdapter.ViewHolder holder, int position) {
        holder.bind(filteredContractors.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredContractors.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                List<Contractor>allFunds = allContractors;
                if(charString.isEmpty())
                {
                    filteredContractors = allFunds;
                }
                else
                {
                    List<Contractor>filtered = new ArrayList<>();
                    for(Contractor contractor: allFunds)
                    {
                        if(contractor.getTitle().toLowerCase().contains(charString.toLowerCase()))
                        {
                            filtered.add(contractor);
                        }
                    }
                    filteredContractors = filtered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredContractors;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredContractors = (ArrayList<Contractor>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, MaterialCardView.OnCheckedChangeListener {
        @BindView(R.id.contractor_title)
        TextView title;
        @BindView(R.id.contractor_card)
        MaterialCardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            cardView.setOnClickListener(this);
            cardView.setOnLongClickListener(this);
            cardView.setOnCheckedChangeListener(this);
        }

        public void bind(Contractor contractor)
        {
            title.setText(contractor.getTitle());
        }

        @Override
        public void onClick(View v) {

            if(!checkedMode)
            {
                if(onItemClickListener != null)
                {
                    onItemClickListener.onItemClick(filteredContractors.get(getAdapterPosition()));
                }
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
                selectedContractors.clear();
                onCheckedClickListener.onCheckedClick();
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
                Contractor contractor = filteredContractors.get(getAdapterPosition());
                if(!selectedContractors.contains(contractor))
                {
                    selectedContractors.add(contractor);
                }
            }
            else
            {
                checkedItem--;
                Contractor contractor = filteredContractors.get(getAdapterPosition());
                selectedContractors.remove(contractor);
            }
            if(checkedItem == 0)
            {
                onCheckedClickListener.onCheckedClick();
                checkedMode = false;
            }
        }
    }
    public interface IOnItemClickListener
    {
        void onItemClick(Contractor contractor);
    }
    public interface IOnCheckedClickListener
    {
        void onCheckedClick();
    }
}
