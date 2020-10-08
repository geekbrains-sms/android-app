package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.Role;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalDialog extends DialogFragment implements View.OnClickListener {

    private static final int CATEGORIES_COUNT_IN_LINE = 3;
    @BindView(R.id.login_edit_text)
    TextInputEditText loginEdit;
    @BindView(R.id.firstname_edit_text)
    TextInputEditText firstNameEdit;
    @BindView(R.id.lastname_edit_text)
    TextInputEditText lastNameEdit;
    @BindView(R.id.password_edit_text)
    TextInputEditText passwordEdit;
    @BindView(R.id.password_confirm_edit_text)
    TextInputEditText passwordConfirmEdit;
    @BindView(R.id.email_edit_text)
    TextInputEditText emailEdit;
    @BindView(R.id.phone_edit_text)
    TextInputEditText phoneEdit;
    @BindView(R.id.select_roles)
    AutoCompleteTextView rolesSelect;
    @BindView(R.id.role_container)
    LinearLayout mainContainer;

    private List<Role>roles;
    private List<Role>userRoles = new ArrayList<>();
    private User user;
    private ArrayAdapter<Role>adapter;
    


    OnClickEditButton onClickEditButton;


    public PersonalDialog(User user, OnClickEditButton onClickEditButton, List<Role>roles)
    {
        this.onClickEditButton = onClickEditButton;
        this.roles = roles;
        this.user = user;
    }
    public PersonalDialog(OnClickEditButton onClickEditButton, List<Role>roles)
    {
        this.onClickEditButton = onClickEditButton;
        this.roles = roles;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.edit_personal_dialog, null);
        ButterKnife.bind(this, view);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {})
                .create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setClickListener();
        setAdapter();
        if(user != null)
        {
            setDataToViews();
        }
    }

    private void setClickListener() {
        AlertDialog dialog = (AlertDialog) getDialog();
        assert dialog != null;
        dialog.setOnShowListener(dialog1 -> {
            Button button = ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE);

            button.setOnClickListener(v -> {
                String login = loginEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();
                String confirmPassword = passwordConfirmEdit.getText().toString();
                String email = emailEdit.getText().toString();
                String phone = phoneEdit.getText().toString();
                String firstName = firstNameEdit.getText().toString();
                String lastName = lastNameEdit.getText().toString();

                if(password.equals(confirmPassword) && textEntered(login) && textEntered(password) && textEntered(firstName) && textEntered(lastName) && !userRoles.isEmpty())
                {
                    User userLocal = new User(login, firstName, lastName, email, phone, password, userRoles);
                    if(user != null)
                    {
                        userLocal.setId(user.getId());
                    }
                    onClickEditButton.onClick(userLocal, user);
                    dialog1.dismiss();
                }
            });
        });
    }
    private boolean textEntered(String text) {
        if(text != null && !text.trim().equals(""))
        {
            return true;
        }
        return false;
    }

    private void setDataToViews()
    {
        loginEdit.setText(user.getLogin());
        firstNameEdit.setText(user.getFirstname());
        lastNameEdit.setText(user.getLastname());

        StringBuilder builder = new StringBuilder();
        for(Role role : user.getRoles())
        {
            addDataCategory(role);
        }
        rolesSelect.setText(builder.toString());
    }


    private void setAdapter() {
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, getActualRoles());
        rolesSelect.setAdapter(adapter);
    }

    private void containerCountControl() {
        if(mainContainer.getChildCount() == 0)
        {
            createContainer();
        }
        LinearLayout container = mainContainer.findViewById(10 + mainContainer.getChildCount() - 1);
        if(container.getChildCount() == CATEGORIES_COUNT_IN_LINE)
        {
            createContainer();
        }
    }

    private void createContainer() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setId(10 + mainContainer.getChildCount());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setWeightSum(CATEGORIES_COUNT_IN_LINE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8,8,8,8);
        linearLayout.setLayoutParams(params);
        mainContainer.addView(linearLayout);
    }

    private TextView createTextViewCategory() {
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(14);
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        params.setMarginStart(8);
        params.setMarginEnd(8);
        textView.setLayoutParams(params);
        textView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        textView.setOnClickListener(this);
        Drawable closeIcon = ContextCompat.getDrawable(requireContext(), R.drawable.close_icon);
        int h = closeIcon.getIntrinsicHeight();
        int w = closeIcon.getIntrinsicWidth();
        closeIcon.setBounds(0,0,w,h);
        textView.setCompoundDrawables(null, null, closeIcon, null);
        return textView;
    }

    private void addRole(TextView textView, Role role) {

        userRoles.add(role);
        textView.setText(role.getName());
        updateAdapter();
        rolesSelect.setText(null);
    }

    private boolean checkText(String string)
    {
        return string != null && !string.trim().equals("");
    }

    @Override
    public void onClick(View v) {
        TextView view = (TextView) v;
        LinearLayout parent = (LinearLayout) v.getParent();
        removeCategory(view.getText().toString());
        parent.removeView(v);
        if(parent.getChildCount() == 0)
        {
            mainContainer.removeView(parent);
        }
    }

    private void removeCategory(String text) {
        for(Iterator<Role> iterator = userRoles.iterator(); iterator.hasNext();)
        {
            if(text.trim().equals(iterator.next().getName().trim()))
            {
                iterator.remove();
            }
        }
        updateAdapter();
        rolesSelect.setText(null);
    }

    private List<Role> getActualRoles()
    {
        List<Role>actualRole = new ArrayList<>();
        for(Role role : roles)
        {
            boolean empty = false;
            for(Role role2 : userRoles)
            {
                if (role.getName().trim().equals(role2.getName().trim())) {
                    empty = true;
                    break;
                }
            }
            if(!empty)
            {
                actualRole.add(role);
            }
        }
        return actualRole;
    }
    private void updateAdapter() {
        adapter.clear();
        adapter.addAll(getActualRoles());
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.add_role_button)
    void onClick()
    {
        String text = rolesSelect.getText().toString();
        if(checkText(text))
        {
            for(Role role : roles)
            {
                if(text.trim().equals(role.getName().trim()))
                {
                    addDataCategory(role);
                    break;
                }
            }
        }
        updateAdapter();
        rolesSelect.setText(null);
    }

    private void addDataCategory(Role role) {
        containerCountControl();
        LinearLayout linearLayout = mainContainer.findViewById(10 + mainContainer.getChildCount() - 1);
        TextView textView = createTextViewCategory();
        linearLayout.addView(textView);
        addRole(textView, role);
    }

    public interface OnClickEditButton
    {
        void onClick(User newUser, User oldUser);
    }
}
