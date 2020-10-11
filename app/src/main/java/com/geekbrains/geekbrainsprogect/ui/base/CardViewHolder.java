package com.geekbrains.geekbrainsprogect.ui.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;

public abstract class CardViewHolder<T extends Item> extends RecyclerView.ViewHolder {
    public CardViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void checkedControl(MaterialCardView cardView) {
        if(cardView.isChecked())
        {
            cardView.setChecked(false);
        }
        else
        {
            cardView.setChecked(true);
        }
    }
    public abstract void bind(T item);
}
