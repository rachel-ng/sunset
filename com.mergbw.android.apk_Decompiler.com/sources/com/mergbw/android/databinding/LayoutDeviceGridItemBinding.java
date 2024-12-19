package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class LayoutDeviceGridItemBinding implements ViewBinding {
    public final ImageView ivItemIcon;
    public final ImageView ivItemState;
    public final SwitchCompat ivItemSwitch;
    private final FrameLayout rootView;
    public final TextView tvItemModel;
    public final TextView tvItemName;
    public final TextView tvSwitchState;

    private LayoutDeviceGridItemBinding(FrameLayout frameLayout, ImageView imageView, ImageView imageView2, SwitchCompat switchCompat, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = frameLayout;
        this.ivItemIcon = imageView;
        this.ivItemState = imageView2;
        this.ivItemSwitch = switchCompat;
        this.tvItemModel = textView;
        this.tvItemName = textView2;
        this.tvSwitchState = textView3;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDeviceGridItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutDeviceGridItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_device_grid_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutDeviceGridItemBinding bind(View view) {
        int i = R.id.iv_item_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.iv_item_state;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.iv_item_switch;
                SwitchCompat switchCompat = (SwitchCompat) ViewBindings.findChildViewById(view, i);
                if (switchCompat != null) {
                    i = R.id.tv_item_model;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.tv_item_name;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            i = R.id.tv_switch_state;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView3 != null) {
                                return new LayoutDeviceGridItemBinding((FrameLayout) view, imageView, imageView2, switchCompat, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
