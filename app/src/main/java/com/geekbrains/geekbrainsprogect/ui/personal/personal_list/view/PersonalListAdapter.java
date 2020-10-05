package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.Role;
import com.geekbrains.geekbrainsprogect.data.User;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalListAdapter extends RecyclerView.Adapter<PersonalListAdapter.ViewHolder> {
    private List<User> allUsers = new ArrayList<>();
    private List<User> filteredUser = new ArrayList<>();
    private OnItemLongClickListener onItemLongClickListener;
    private IOnItemClickListener onItemClickListener;

    public void setOnItemClickListener(IOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
        filteredUser.addAll(allUsers);
        notifyDataSetChanged();
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        @BindView(R.id.user_id)
        TextView id;
        @BindView(R.id.user_name)
        TextView name;
        @BindView(R.id.user_login)
        TextView login;
        @BindView(R.id.user_role)
        TextView role;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void bind(User user)
        {
            login.setText(user.getLogin());
            name.setText(user.getFullname());
            id.setText(user.getId() + "");
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
            if(onItemClickListener != null)
            {
                onItemClickListener.onItemClick(filteredUser.get(getAdapterPosition()));
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(onItemLongClickListener != null)
            {
                onItemLongClickListener.onItemLongClick(getAdapterPosition() - 1, v);
            }
            return true;
        }
    }
    public interface OnItemLongClickListener
    {
        void onItemLongClick(int position, View view);
    }
    public interface IOnItemClickListener
    {
        void onItemClick(User user);
    }
}
