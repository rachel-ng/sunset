package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.mergbw.android.R;

public final class LayoutGroupDeviceListItemBinding implements ViewBinding {
    public final Button btnConnect;
    public final CheckBox cbItemCheck;
    public final ImageView ivItemIcon;
    public final ImageView ivItemState;
    public final SwitchCompat ivItemSwitch;
    private final FrameLayout rootView;
    public final TextView tvItemModel;
    public final TextView tvItemName;
    public final LottieAnimationView viewLoading;

    private LayoutGroupDeviceListItemBinding(FrameLayout frameLayout, Button button, CheckBox checkBox, ImageView imageView, ImageView imageView2, SwitchCompat switchCompat, TextView textView, TextView textView2, LottieAnimationView lottieAnimationView) {
        this.rootView = frameLayout;
        this.btnConnect = button;
        this.cbItemCheck = checkBox;
        this.ivItemIcon = imageView;
        this.ivItemState = imageView2;
        this.ivItemSwitch = switchCompat;
        this.tvItemModel = textView;
        this.tvItemName = textView2;
        this.viewLoading = lottieAnimationView;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutGroupDeviceListItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutGroupDeviceListItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_group_device_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutGroupDeviceListItemBinding bind(View view) {
        int i = R.id.btn_connect;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.cb_item_check;
            CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, i);
            if (checkBox != null) {
                i = R.id.iv_item_icon;
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
                                    i = R.id.view_loading;
                                    LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                                    if (lottieAnimationView != null) {
                                        return new LayoutGroupDeviceListItemBinding((FrameLayout) view, button, checkBox, imageView, imageView2, switchCompat, textView, textView2, lottieAnimationView);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
