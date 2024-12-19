package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class LayoutGroupItemBinding implements ViewBinding {
    public final ImageView ivItemIcon;
    private final FrameLayout rootView;
    public final TextView tvItemName;
    public final TextView tvItemState;

    private LayoutGroupItemBinding(FrameLayout frameLayout, ImageView imageView, TextView textView, TextView textView2) {
        this.rootView = frameLayout;
        this.ivItemIcon = imageView;
        this.tvItemName = textView;
        this.tvItemState = textView2;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutGroupItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutGroupItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_group_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutGroupItemBinding bind(View view) {
        int i = R.id.iv_item_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.tv_item_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.tv_item_state;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    return new LayoutGroupItemBinding((FrameLayout) view, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
