package com.geekbrains.geekbrainsprogect.ui.contractors.list.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.ui.base.BaseListAdapter;
import com.geekbrains.geekbrainsprogect.ui.base.CardViewHolder;
import com.google.android.material.card.MaterialCardView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContractorsListAdapter extends BaseListAdapter<Contractor, ContractorsListAdapter.ViewHolder> {


    public ContractorsListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ContractorsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contractor_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractorsListAdapter.ViewHolder holder, int position) {
        holder.bind(getFilteredItem().get(position));
    }

    public class ViewHolder extends CardViewHolder<Contractor> implements View.OnClickListener, View.OnLongClickListener, MaterialCardView.OnCheckedChangeListener {
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

            if(!isCheckedMode())
            {
                if(getOnItemClickListener() != null)
                {
                    getOnItemClickListener().onItemClick(getFilteredItem().get(getAdapterPosition()));
                }
            }
            else
            {
                checkedControl((MaterialCardView) v);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(!isCheckedMode())
            {
                setCheckedMode(true);
                getSelectedList().clear();
                getOnItemClickListener().onItemChangeChecked();
            }
            checkedControl((MaterialCardView) v);
            return true;
        }

        @Override
        public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
            Contractor contractor = getFilteredItem().get(getAdapterPosition());
            if(isChecked)
            {
                addCheckedItemCount();

                if(!getSelectedList().contains(contractor))
                {
                    getSelectedList().add(contractor);
                }
            }
            else
            {
                removeCheckedItemCount();
                getSelectedList().remove(contractor);
            }
            if(getCheckedCount() == 0)
            {
                getOnItemClickListener().onItemChangeChecked();
                setCheckedMode(false);
            }
        }
    }

}
