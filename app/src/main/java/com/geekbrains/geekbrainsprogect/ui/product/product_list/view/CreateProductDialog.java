package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.Edits;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.Contractor;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product.model.Unit;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateProductDialog extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.edit_name_create_product)
    TextInputEditText editName;
    @BindView(R.id.edits_description_create_product)
    TextInputEditText editDescription;
    @BindView(R.id.edit_unit_create_product)
    AutoCompleteTextView editUnit;
    @BindView(R.id.edit_unit_description_creat_product)
    TextInputEditText editUnitDescription;
    @BindView(R.id.edit_category_create_product)
    AutoCompleteTextView editCategory;
    @BindView(R.id.category_field_layout)
    LinearLayout categoryContainer;

    private Category selectedCategory;
    private List<Category>categories = new ArrayList<>();
    private IOnClickListener iOnClickListener;
    private ArrayAdapter<Category>categoryArrayAdapter;

    public CreateProductDialog(IOnClickListener iOnClickListener)
    {
        this.iOnClickListener = iOnClickListener;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.create_product_dialog, null);
        ButterKnife.bind(this, view);
        setAdapters();

        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.create_product)
                .setView(view)
                .setPositiveButton(R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {})
                .create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setListener();
        super.onActivityCreated(savedInstanceState);
    }

    private void setAdapters() {
        categoryArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, getActualCategory());
        ArrayAdapter<Unit>unitsArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, AppData.getUnitList());

        editUnit.setAdapter(unitsArrayAdapter);
        editCategory.setAdapter(categoryArrayAdapter);
    }

    private void setListener() {

        editUnit.setOnItemClickListener((parent, view, position, id) -> {
                editUnitDescription.setText(AppData.getUnitList().get(position).getDescription());
        });

        editCategory.setOnItemClickListener((parent, view, position, id) -> {
            selectedCategory = AppData.getCategoryList().get(position);
        });

        AlertDialog dialog = (AlertDialog) getDialog();
        assert dialog != null;
        dialog.setOnShowListener(dialog1 -> {
            Button button = ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE);

            button.setOnClickListener(v -> {
                String name = Objects.requireNonNull(editName.getText()).toString();
                String unit = editUnit.getText().toString();
                String unitDescription = Objects.requireNonNull(editUnitDescription.getText()).toString();

                if(checkText(name) && checkText(unit) && checkText(unitDescription) && categories.size() > 0) {
                    Unit newUnit = new Unit(unit, unitDescription);
                    String description = Objects.requireNonNull(editDescription.getText()).toString();
                    Product product = new Product(name, description, categories, newUnit);
                    iOnClickListener.onClick(product);
                    dialog1.dismiss();
                }

            });
        });

    }

    @OnClick({R.id.add_category_button})
    void onClick()
    {
        if(selectedCategory != null || checkText(editCategory.getText().toString()))
        {
            containerCountControl();
            LinearLayout linearLayout = categoryContainer.findViewById(10 + categoryContainer.getChildCount() - 1);
            TextView textView = createTextViewCategory();
            linearLayout.addView(textView);
            addCategory(textView);
        }
    }

    private void containerCountControl() {
        if(categoryContainer.getChildCount() == 0)
        {
            createContainer();
        }
        LinearLayout container = categoryContainer.findViewById(10 + categoryContainer.getChildCount() - 1);
        if(container.getChildCount() == 5)
        {
            createContainer();
        }
    }

    private void createContainer() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setId(10 + categoryContainer.getChildCount());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setWeightSum(5);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8,8,8,8);
        linearLayout.setLayoutParams(params);
        categoryContainer.addView(linearLayout);
    }

    private TextView createTextViewCategory() {
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(14);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        params.setMarginStart(8);
        params.setMarginEnd(8);
        textView.setLayoutParams(params);
        textView.setBackgroundColor(Color.GRAY);
        textView.setOnClickListener(this);
        Drawable closeIcon = ContextCompat.getDrawable(requireContext(), R.drawable.close_icon);
        int h = closeIcon.getIntrinsicHeight();
        int w = closeIcon.getIntrinsicWidth();
        closeIcon.setBounds(0,0,w,h);
        textView.setCompoundDrawables(null, null, closeIcon, null);
        return textView;
    }

    private void addCategory(TextView textView) {
        Category category;
        if(selectedCategory != null)
        {
            category = selectedCategory;
        }
        else
        {
            category = new Category(editCategory.getText().toString());

        }
        categories.add(category);
        textView.setText(category.getTitle());
        selectedCategory = null;
        updateAdapter();
    }

    private void updateAdapter() {
        categoryArrayAdapter.clear();
        categoryArrayAdapter.addAll(getActualCategory());
        categoryArrayAdapter.notifyDataSetChanged();
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
            categoryContainer.removeView(parent);
        }
    }

    private void removeCategory(String text) {
        for(Iterator<Category> iterator = categories.iterator(); iterator.hasNext();)
        {
            if(text.equals(iterator.next().getTitle()))
            {
                iterator.remove();
            }
        }
    }

    private List<Category> getActualCategory()
    {
        List<Category>actualCategory = new ArrayList<>();
        for(Category category : AppData.getCategoryList())
        {
            boolean empty = false;
            for(Category category2 : categories)
            {
                if (category.getTitle().equals(category2.getTitle())) {
                    empty = true;
                    break;
                }
            }
            if(!empty)
            {
                actualCategory.add(category);
            }
        }
        return actualCategory;
    }

    public interface IOnClickListener
    {
        void onClick(Product product);
    }
}
