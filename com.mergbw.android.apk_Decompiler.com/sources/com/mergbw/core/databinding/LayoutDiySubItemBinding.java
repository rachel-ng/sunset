package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;
import com.mergbw.core.ui.views.SubItemListView;

public final class LayoutDiySubItemBinding implements ViewBinding {
    public final ImageView ivAdd;
    public final ImageView ivDelete;
    private final FrameLayout rootView;
    public final SubItemListView viewSubList;

    private LayoutDiySubItemBinding(FrameLayout frameLayout, ImageView imageView, ImageView imageView2, SubItemListView subItemListView) {
        this.rootView = frameLayout;
        this.ivAdd = imageView;
        this.ivDelete = imageView2;
        this.viewSubList = subItemListView;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDiySubItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutDiySubItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_diy_sub_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutDiySubItemBinding bind(View view) {
        int i = R.id.iv_add;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.iv_delete;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.view_sub_list;
                SubItemListView subItemListView = (SubItemListView) ViewBindings.findChildViewById(view, i);
                if (subItemListView != null) {
                    return new LayoutDiySubItemBinding((FrameLayout) view, imageView, imageView2, subItemListView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
