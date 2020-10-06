package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;

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
import com.geekbrains.geekbrainsprogect.data.Role;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.google.android.material.card.MaterialCardView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalListAdapter extends RecyclerView.Adapter<PersonalListAdapter.ViewHolder> implements Filterable {
    private List<User> allUsers = new ArrayList<>();
    private List<User> filteredUser = new ArrayList<>();
    private List<User> selectedUser = new ArrayList<>();
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

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
        filteredUser.clear();
        selectedUser.clear();
        filteredUser.addAll(allUsers);
        onCheckedClickListener.onCheckedClick();
        notifyDataSetChanged();
    }

    public List<User> getSelectedUser() {
        return selectedUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(filteredUser.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredUser.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                List<User>allFunds = allUsers;
                if(charString.isEmpty())
                {
                    filteredUser = allFunds;
                }
                else
                {
                    List<User>filtered = new ArrayList<>();
                    for(User user: allFunds)
                    {
                        if(user.getFullname().toLowerCase().contains(charString.toLowerCase()))
                        {
                            filtered.add(user);
                        }
                    }
                    filteredUser = filtered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredUser;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredUser = (ArrayList<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, MaterialCardView.OnCheckedChangeListener {
        @BindView(R.id.user_name)
        TextView name;
        @BindView(R.id.user_login)
        TextView login;
        @BindView(R.id.user_role)
        TextView role;
        @BindView(R.id.user_card)
        MaterialCardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            cardView.setOnClickListener(this);
            cardView.setOnLongClickListener(this);
            cardView.setOnCheckedChangeListener(this);
        }

        public void bind(User user)
        {
            login.setText(user.getLogin());
            name.setText(user.getFullname());
            StringBuilder stringBuffer = new StringBuilder();
            for(Role role : user.getRoles())
            {
                if(stringBuffer.length() != 0)
                {
                    stringBuffer.append("/");
                }
                stringBuffer.append(role.getName());
            }
            role.setText(stringBuffer.toString());
        }

        @Override
        public void onClick(View v) {

            if(!checkedMode)
            {
                if(onItemClickListener != null)
                {
                    onItemClickListener.onItemClick(filteredUser.get(getAdapterPosition()));
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
                selectedUser.clear();
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
                User user = filteredUser.get(getAdapterPosition());
                if(!selectedUser.contains(user))
                {
                    selectedUser.add(user);
                }
            }
            else
            {
                checkedItem--;
                User user = filteredUser.get(getAdapterPosition());
                selectedUser.remove(user);
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
        void onItemClick(User user);
    }
    public interface IOnCheckedClickListener
    {
        void onCheckedClick();
    }

    }

