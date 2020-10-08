package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditCategoryDialog extends DialogFragment implements View.OnClickListener {
    private Product product;
    private static final int CATEGORIES_COUNT_IN_LINE = 4;
    private IOnClickListener onClickListener;
    private ArrayAdapter<Category>adapter;
    List<Category>categories = new ArrayList<>();

    @BindView(R.id.category_edit_text)
    AutoCompleteTextView editCategory;
    @BindView(R.id.category_container)
    LinearLayout mainContainer;

    public EditCategoryDialog(Product product, IOnClickListener iOnClickListener)
    {
        this.product = product;
        onClickListener = iOnClickListener;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.edit_category_product, null);
        ButterKnife.bind(this, view);
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.edit_category_product)
                .setView(view)
                .setPositiveButton(R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {})
                .create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListener();
        setAdapter();
        setDataToViews();
    }


    private void setDataToViews() {
        for(Category category: product.getCategoryList())
        {
            addDataCategory(category.getTitle());
        }
    }

    private void setListener() {
        AlertDialog dialog = (AlertDialog)getDialog();
        dialog.setOnShowListener(dialog1 -> {
            Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            button.setOnClickListener(v -> {
                if(!categories.isEmpty())
                {
                    product.setCategoryList(categories);
                    onClickListener.onClick();
                    dialog.dismiss();
                }
            });
        });
    }

    private void setAdapter() {
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, getActualCategory());
        editCategory.setAdapter(adapter);
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

    private void addCategory(TextView textView, String text) {
        Category category;
        category = new Category(text);
        categories.add(category);
        textView.setText(category.getTitle());
        updateAdapter();
        editCategory.setText(null);
    }

    private boolean emptyCategory(String text) {
        for(Category category: categories)
        {
            if(category.getTitle().equals(text))
            {
                return true;
            }
        }
        return false;
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
        for(Iterator<Category> iterator = categories.iterator(); iterator.hasNext();)
        {
            if(text.trim().equals(iterator.next().getTitle().trim()))
            {
                iterator.remove();
            }
        }
        updateAdapter();
        editCategory.setText(null);
    }

    private List<Category> getActualCategory()
    {
        List<Category>actualCategory = new ArrayList<>();
        for(Category category : AppData.getCategoryList())
        {
            boolean empty = false;
            for(Category category2 : categories)
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
    private void updateAdapter() {
        adapter.clear();
        adapter.addAll(getActualCategory());
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.add_category_button)
    void onClick()
    {
        String text = editCategory.getText().toString();
        if(checkText(text))
        {
            if(!emptyCategory(text))
            {
                addDataCategory(text);
            }
        }
        updateAdapter();
        editCategory.setText(null);
    }

    private void addDataCategory(String text) {
        containerCountControl();
        LinearLayout linearLayout = mainContainer.findViewById(10 + mainContainer.getChildCount() - 1);
        TextView textView = createTextViewCategory();
        linearLayout.addView(textView);
        addCategory(textView, text);
    }

    public interface IOnClickListener
    {
        void onClick();
    }
}
