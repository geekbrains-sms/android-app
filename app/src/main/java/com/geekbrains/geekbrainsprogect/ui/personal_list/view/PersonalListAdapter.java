package com.geekbrains.geekbrainsprogect.ui.personal_list.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.Roles;
import com.geekbrains.geekbrainsprogect.data.User;


import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalListAdapter extends RecyclerView.Adapter<PersonalListAdapter.ViewHolder> {
    private IRecyclerPersonal iRecyclerPersonal;

    public PersonalListAdapter(IRecyclerPersonal recyclerPersonal)
    {
        iRecyclerPersonal = recyclerPersonal;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        iRecyclerPersonal.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return iRecyclerPersonal.getItemCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements IViewHolder {
        @BindView(R.id.user_id)
        TextView id;
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
            id.setText(user.getId() + "");
            StringBuffer stringBuffer = new StringBuffer();
            for(Roles roles: user.getRoles())
            {
                if(stringBuffer.length() != 0)
                {
                    stringBuffer.append("/");
                }
                stringBuffer.append(roles.getName());
            }
            role.setText(stringBuffer.toString());

        }

        @Override
        public int getPos() {
            return getAdapterPosition();
        }

    }
}
