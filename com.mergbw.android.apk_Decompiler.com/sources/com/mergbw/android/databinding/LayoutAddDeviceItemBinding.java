package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class LayoutAddDeviceItemBinding implements ViewBinding {
    public final Button btnConnect;
    public final ImageView ivItemIcon;
    private final FrameLayout rootView;
    public final TextView tvItemModel;
    public final TextView tvItemName;

    private LayoutAddDeviceItemBinding(FrameLayout frameLayout, Button button, ImageView imageView, TextView textView, TextView textView2) {
        this.rootView = frameLayout;
        this.btnConnect = button;
        this.ivItemIcon = imageView;
        this.tvItemModel = textView;
        this.tvItemName = textView2;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutAddDeviceItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutAddDeviceItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_add_device_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutAddDeviceItemBinding bind(View view) {
        int i = R.id.btn_connect;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.iv_item_icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.tv_item_model;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.tv_item_name;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        return new LayoutAddDeviceItemBinding((FrameLayout) view, button, imageView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
