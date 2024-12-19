package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class LayoutSceneItemBinding implements ViewBinding {
    public final ImageView ivItemIcon;
    public final FrameLayout layoutIcon;
    private final ConstraintLayout rootView;
    public final TextView tvItemName;

    private LayoutSceneItemBinding(ConstraintLayout constraintLayout, ImageView imageView, FrameLayout frameLayout, TextView textView) {
        this.rootView = constraintLayout;
        this.ivItemIcon = imageView;
        this.layoutIcon = frameLayout;
        this.tvItemName = textView;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSceneItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutSceneItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_scene_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutSceneItemBinding bind(View view) {
        int i = R.id.iv_item_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.layout_icon;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                i = R.id.tv_item_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    return new LayoutSceneItemBinding((ConstraintLayout) view, imageView, frameLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
