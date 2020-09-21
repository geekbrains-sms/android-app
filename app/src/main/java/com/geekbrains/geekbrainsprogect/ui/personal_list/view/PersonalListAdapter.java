package com.geekbrains.geekbrainsprogect.ui.personal_list.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.Role;
import com.geekbrains.geekbrainsprogect.data.User;



import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalListAdapter extends ContextMenuRecyclerView.Adapter<PersonalListAdapter.ViewHolder> {
    private IRecyclerPersonal iRecyclerPersonal;
    OnItemLongClickListener onItemLongClickListener;

    public PersonalListAdapter(IRecyclerPersonal recyclerPersonal)
    {
        iRecyclerPersonal = recyclerPersonal;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
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

    public class ViewHolder extends ContextMenuRecyclerView.ViewHolder implements IViewHolder, View.OnClickListener, View.OnLongClickListener{
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
        public int getPos() {
            return getAdapterPosition();
        }


        @Override
        public void onClick(View v) {
            v.showContextMenu();        }

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
}
