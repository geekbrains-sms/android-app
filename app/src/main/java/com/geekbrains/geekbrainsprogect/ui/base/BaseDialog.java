package com.geekbrains.geekbrainsprogect.ui.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class BaseDialog extends DialogFragment implements View.OnClickListener {
    public static final int COUNT_ITEMS_TO_LINE = 4;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        addPositiveButtonListener();
        super.onActivityCreated(savedInstanceState);
    }

    public void addPositiveButtonListener()
    {
        AlertDialog dialog = (AlertDialog) getDialog();
        assert dialog != null;
        dialog.setOnShowListener(dialog1 -> {
            Button button = ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE);

            button.setOnClickListener(v -> {
                mappedItem(dialog1);
            });
        });
    }

    protected <T extends Item> void addItemToContainer (Class<T> type, AutoCompleteTextView autoCompleteTextView,
                                                        List<T> selectedItems, List<T>allItems,
                                                        LinearLayout mainContainer, ArrayAdapter<T>arrayAdapter)
    {
        String text = autoCompleteTextView.getText().toString();
        if(!TextUtils.isEmpty(text))
        {
            if(!emptyItems(autoCompleteTextView.getText().toString(), selectedItems))
            {
                T item = searchItemsInList(text, allItems);
                containerCountControl(mainContainer);
                LinearLayout linearLayout = mainContainer.findViewById(10 + mainContainer.getChildCount() - 1);
                TextView textView = createTextView();
                linearLayout.addView(textView);
                addItemToSelectedList(type,textView, item);
            }
        }
        updateAdapter(arrayAdapter,allItems,selectedItems);
        autoCompleteTextView.setText(null);
    }

    private <T extends Item> T searchItemsInList(String text, List<T>allItems) {
        for(T item : allItems)
        {
            if(item.getItemName().equals(text))
            {
                return item;
            }
        }
        return null;
    }

    protected void containerCountControl(LinearLayout mainContainer) {
        if(mainContainer.getChildCount() == 0)
        {
            createContainer(mainContainer);
        }
        LinearLayout subContainer = mainContainer.findViewById(10 + mainContainer.getChildCount() - 1);
        if(subContainer.getChildCount() == COUNT_ITEMS_TO_LINE)
        {
            createContainer(mainContainer);
        }
    }

    private void createContainer(LinearLayout mainContainer) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setId(10 + mainContainer.getChildCount());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setWeightSum(COUNT_ITEMS_TO_LINE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8,8,8,8);
        linearLayout.setLayoutParams(params);
        mainContainer.addView(linearLayout);
    }

    private <T extends Item> boolean emptyItems(String text, List<T>selectedItems) {
        for(Item item: selectedItems)
        {
            if(item.getItemName().equals(text))
            {
                return true;
            }
        }
        return false;
    }

    private TextView createTextView() {
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
        int h = Objects.requireNonNull(closeIcon).getIntrinsicHeight();
        int w = closeIcon.getIntrinsicWidth();
        closeIcon.setBounds(0,0,w,h);
        textView.setCompoundDrawables(null, null, closeIcon, null);
        return textView;
    }

   protected <T extends Item> void removeItem(View v, LinearLayout mainContainer, List<T>selectedItems, List<T>allItems,
                                              AutoCompleteTextView autoCompleteTextView, ArrayAdapter<T>arrayAdapter)
   {
       TextView view = (TextView) v;
       LinearLayout parent = (LinearLayout) v.getParent();
       removeItemFromSelectedList(view.getText().toString(), selectedItems, allItems, autoCompleteTextView, arrayAdapter);
       parent.removeView(v);
       if(parent.getChildCount() == 0)
       {
           mainContainer.removeView(parent);
       }
   }

    private <T extends Item> void removeItemFromSelectedList(String text, List<T> selectedItems, List<T>allItems,
                                                             AutoCompleteTextView autoCompleteTextView,
                                                             ArrayAdapter<T> arrayAdapter) {
        for(Iterator<T> iterator = selectedItems.iterator(); iterator.hasNext();)
        {
            if(text.trim().equals(iterator.next().getItemName()))
            {
                iterator.remove();
            }
        }
        updateAdapter(arrayAdapter, allItems, selectedItems);
        autoCompleteTextView.setText(null);
    }

    protected <T extends Item> List<T> getActualItemList(List<T>allItems, List<T>selectedItems)
    {
        List<T> actualItem = new ArrayList<>();
        for(T item : allItems)
        {
            boolean empty = false;
            for(Item selectedItem : selectedItems)
            {
                if (item.getItemName().trim().equals(selectedItem.getItemName())) {
                    empty = true;
                    break;
                }
            }
            if(!empty)
            {
                actualItem.add(item);
            }
        }
        return actualItem;
    }

    protected <T extends Item> void updateAdapter(ArrayAdapter<T> arrayAdapter, List<T> allItems, List<T>selectedItems) {
        arrayAdapter.clear();
        arrayAdapter.addAll(getActualItemList(allItems, selectedItems));
        arrayAdapter.notifyDataSetChanged();
    }


    protected <T extends Item> void addDataItem(Class<T> type, T item, LinearLayout mainContainer) {
        containerCountControl(mainContainer);
        LinearLayout linearLayout = mainContainer.findViewById(10 + mainContainer.getChildCount() - 1);
        TextView textView = createTextView();
        linearLayout.addView(textView);
        addItem(type,textView, item);
    }

    private <T extends Item> void addItem(Class<T>type, TextView textView, T item)
    {
        addItemToSelectedList(type,textView, item);
    }
    protected abstract <T extends Item> void addItemToSelectedList(Class<T>type ,TextView textView, T item);
    protected abstract void mappedItem(DialogInterface dialogInterface);
}
