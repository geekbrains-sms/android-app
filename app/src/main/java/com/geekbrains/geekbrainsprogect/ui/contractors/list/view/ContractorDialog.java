package com.geekbrains.geekbrainsprogect.ui.contractors.list.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContractorDialog extends DialogFragment {
    private Contractor contractor;
    private IOnClickListener onClickListener;
    @BindView(R.id.edit_text_dialog)
    TextInputEditText nameEdit;

    public ContractorDialog(Contractor contractor, IOnClickListener iOnClickListener)
    {
        this.contractor = contractor;
        this.onClickListener = iOnClickListener;
    }
    public ContractorDialog(IOnClickListener iOnClickListener)
    {
        this.onClickListener = iOnClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_enter_text, null);
        ButterKnife.bind(this, view);
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.create_edit_contractor)
                .setView(view)
                .setPositiveButton(R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {})
                .create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListener();
        if(contractor != null)
        {
            nameEdit.setText(contractor.getTitle());
        }
    }

    private void setListener() {
        AlertDialog dialog = (AlertDialog)getDialog();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogLocal) {
                Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = Objects.requireNonNull(nameEdit.getText()).toString();
                        if(text != null && !text.trim().equals(""))
                        {
                            Contractor newContractor = new Contractor();
                            newContractor.setTitle(text);
                            if(contractor != null)
                            {
                                newContractor.setId(contractor.getId());
                            }
                            onClickListener.onClick(newContractor);
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    public interface IOnClickListener
    {
        void onClick(Contractor newContractor);
    }
}
