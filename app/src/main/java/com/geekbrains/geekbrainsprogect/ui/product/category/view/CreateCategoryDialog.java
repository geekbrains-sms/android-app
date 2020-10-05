package com.geekbrains.geekbrainsprogect.ui.product.category.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateCategoryDialog extends DialogFragment {
    @BindView(R.id.edit_text_dialog)
    TextInputEditText editText;
    IOnClickListener iOnClickListener;

    public CreateCategoryDialog(IOnClickListener iOnClickListener)
    {
        this.iOnClickListener = iOnClickListener;
    }




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_enter_text, null);
        ButterKnife.bind(this, view);
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.create_category)
                .setView(view)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    String text = Objects.requireNonNull(editText.getText()).toString();
                    if(text != null && !text.trim().equals(""))
                    {
                        Category category = new Category(Objects.requireNonNull(editText.getText()).toString());
                        iOnClickListener.onClick(category);
                    }
                })
                .create();
    }


    public interface IOnClickListener
    {
        void onClick(Category category);
    }
}
