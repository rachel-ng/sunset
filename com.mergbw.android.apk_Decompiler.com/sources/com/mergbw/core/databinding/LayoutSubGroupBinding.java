package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;
import com.mergbw.core.ui.views.SubItemListView;

public final class LayoutSubGroupBinding implements ViewBinding {
    public final ImageView ivAdd;
    private final ConstraintLayout rootView;
    public final TextView tvItemName;
    public final SubItemListView viewSubList;

    private LayoutSubGroupBinding(ConstraintLayout constraintLayout, ImageView imageView, TextView textView, SubItemListView subItemListView) {
        this.rootView = constraintLayout;
        this.ivAdd = imageView;
        this.tvItemName = textView;
        this.viewSubList = subItemListView;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSubGroupBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutSubGroupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_sub_group, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutSubGroupBinding bind(View view) {
        int i = R.id.iv_add;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.tv_item_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.view_sub_list;
                SubItemListView subItemListView = (SubItemListView) ViewBindings.findChildViewById(view, i);
                if (subItemListView != null) {
                    return new LayoutSubGroupBinding((ConstraintLayout) view, imageView, textView, subItemListView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
