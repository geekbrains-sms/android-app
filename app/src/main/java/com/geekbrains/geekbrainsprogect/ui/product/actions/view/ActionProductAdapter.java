package com.geekbrains.geekbrainsprogect.ui.product.actions.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.base.BaseListAdapter;
import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionProductAdapter extends BaseListAdapter<UserAction, ActionProductAdapter.ViewHolder> {


    public ActionProductAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.action_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getFilteredItem().get(position));
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
