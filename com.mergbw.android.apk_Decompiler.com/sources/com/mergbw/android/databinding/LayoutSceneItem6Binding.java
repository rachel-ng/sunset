package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class LayoutSceneItem6Binding implements ViewBinding {
    public final ImageView ivItemIcon;
    public final LinearLayout layoutItem;
    private final ConstraintLayout rootView;
    public final TextView tvItemName;

    private LayoutSceneItem6Binding(ConstraintLayout constraintLayout, ImageView imageView, LinearLayout linearLayout, TextView textView) {
        this.rootView = constraintLayout;
        this.ivItemIcon = imageView;
        this.layoutItem = linearLayout;
        this.tvItemName = textView;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSceneItem6Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutSceneItem6Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_scene_item_6, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutSceneItem6Binding bind(View view) {
        int i = R.id.iv_item_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.layout_item;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.tv_item_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    return new LayoutSceneItem6Binding((ConstraintLayout) view, imageView, linearLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
