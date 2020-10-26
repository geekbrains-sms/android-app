package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.model.UnitsWithCategories;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateProductDialog extends DialogFragment implements View.OnClickListener {
    public static final int COUNT_ITEMS_TO_LINE = 4;
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

    private IOnClickListener iOnClickListener;
    private ArrayAdapter<Category>categoryArrayAdapter;
    private final List<Unit> unitList;
    private final List<Category> categoryList;
    private final List<Category> selectedCategories = new ArrayList<>();

    public CreateProductDialog(IOnClickListener iOnClickListener, UnitsWithCategories unitsWithCategories)
    {
        this.iOnClickListener = iOnClickListener;
        unitList = unitsWithCategories.getUnitList();
        this.categoryList = unitsWithCategories.getCategoryList();
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
        categoryArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, getCategoryList());
        ArrayAdapter<Unit>unitsArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, unitList);
        editUnit.setAdapter(unitsArrayAdapter);
        editCategory.setAdapter(categoryArrayAdapter);
    }

    private void setListener() {

        editUnit.setOnItemClickListener((parent, view, position, id) -> {
                editUnitDescription.setText(unitList.get(position).getDescription());
        });

        AlertDialog dialog = (AlertDialog) getDialog();
        assert dialog != null;
        dialog.setOnShowListener(dialog1 -> {
            Button button = ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE);

            button.setOnClickListener(v -> {
                String name = Objects.requireNonNull(editName.getText()).toString();
                String unit = editUnit.getText().toString();
                String unitDescription = Objects.requireNonNull(editUnitDescription.getText()).toString();

                if(checkText(name) && checkText(unit) && checkText(unitDescription) && selectedCategories.size() > 0) {
                    Unit newUnit = new Unit(0,unit, unitDescription);
                    String description = Objects.requireNonNull(editDescription.getText()).toString();
                    ProductModel product = new ProductModel(0, name, description, newUnit, null, selectedCategories, null, null, 0);
                    iOnClickListener.onClick(product);
                    dialog1.dismiss();
                }

            });
        });

    }

    @OnClick({R.id.add_category_button})
    void onClick()
    {
        if(checkText(editCategory.getText().toString()))
        {
            if(!emptyCategory(editCategory.getText().toString()))
            {
                containerCountControl();
                LinearLayout linearLayout = categoryContainer.findViewById(10 + categoryContainer.getChildCount() - 1);
                TextView textView = createTextViewCategory();
                linearLayout.addView(textView);
                addCategory(textView);
            }
        }
        updateAdapter();
        editCategory.setText(null);
    }

    private void containerCountControl() {
        if(categoryContainer.getChildCount() == 0)
        {
            createContainer();
        }
        LinearLayout container = categoryContainer.findViewById(10 + categoryContainer.getChildCount() - 1);
        if(container.getChildCount() == COUNT_ITEMS_TO_LINE)
        {
            createContainer();
        }
    }

    private void createContainer() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setId(10 + categoryContainer.getChildCount());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setWeightSum(COUNT_ITEMS_TO_LINE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8,8,8,8);
        linearLayout.setLayoutParams(params);
        categoryContainer.addView(linearLayout);
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
        textView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark));
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
        category = new Category(0,editCategory.getText().toString());
        selectedCategories.add(category);
        textView.setText(category.getTitle());
    }

    private boolean emptyCategory(String text) {
        for(Category category: selectedCategories)
        {
            if(category.getTitle().equals(text))
            {
               return true;
            }
        }
        return false;
    }

    private void updateAdapter() {
        categoryArrayAdapter.clear();
        categoryArrayAdapter.addAll(getCategoryList());
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
        for(Iterator<Category> iterator = selectedCategories.iterator(); iterator.hasNext();)
        {
            if(text.trim().equals(iterator.next().getTitle().trim()))
            {
                iterator.remove();
            }
        }
        updateAdapter();
        editCategory.setText(null);
    }

    private List<Category> getCategoryList()
    {
        List<Category>actualCategory = new ArrayList<>();
        for(Category category : categoryList)
        {
            boolean empty = false;
            for(Category category2 : selectedCategories)
            {
                if (category.getTitle().trim().equals(category2.getTitle().trim())) {
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
        void onClick(ProductModel product);
    }
}
