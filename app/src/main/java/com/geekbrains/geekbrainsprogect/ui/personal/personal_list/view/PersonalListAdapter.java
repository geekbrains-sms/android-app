package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;
import com.geekbrains.geekbrainsprogect.ui.base.BaseListAdapter;
import com.geekbrains.geekbrainsprogect.ui.base.CardViewHolder;
import com.google.android.material.card.MaterialCardView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalListAdapter extends BaseListAdapter<UserModel, PersonalListAdapter.ViewHolder> {
    public PersonalListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getFilteredItem().get(position));
    }
    public class ViewHolder extends CardViewHolder<UserModel> implements View.OnClickListener, View.OnLongClickListener, MaterialCardView.OnCheckedChangeListener {
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
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(this);
            cardView.setOnLongClickListener(this);
            cardView.setOnCheckedChangeListener(this);
        }

        public void bind(UserModel user) {
            login.setText(user.getLogin());
            name.setText(user.getFirstName());
            StringBuilder stringBuffer = new StringBuilder();
            for (Role role : user.getRoles()) {
                if (stringBuffer.length() != 0) {
                    stringBuffer.append("/");
                }
                stringBuffer.append(role.getTitle());
            }
            role.setText(stringBuffer.toString());
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
            UserModel user = getFilteredItem().get(getAdapterPosition());
            if (isChecked) {

                addCheckedItemCount();

                if (!getSelectedList().contains(user)) {
                    getSelectedList().add(user);
                }
            } else {
                removeCheckedItemCount();
                getSelectedList().remove(user);
            }
            if (getCheckedCount() == 0) {
                getOnItemClickListener().onItemChangeChecked();
                setCheckedMode(false);
            }
        }
    }
}

