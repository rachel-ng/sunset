package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class ActivityDeviceInfoBinding implements ViewBinding {
    public final Button btnDeleteDevice;
    public final ImageView ivBack;
    public final ImageView ivDeviceIcon;
    public final ConstraintLayout layoutDeviceAlias;
    public final ConstraintLayout layoutFirmwareUpdate;
    public final RelativeLayout layoutHead;
    public final ConstraintLayout layoutLedNum;
    private final ConstraintLayout rootView;
    public final TextView tvAliasTitle;
    public final TextView tvDeviceAlias;
    public final TextView tvDeviceName;
    public final TextView tvFirmwareTick;
    public final TextView tvFirmwareVersion;
    public final TextView tvLedNum;

    private ActivityDeviceInfoBinding(ConstraintLayout constraintLayout, Button button, ImageView imageView, ImageView imageView2, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, RelativeLayout relativeLayout, ConstraintLayout constraintLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = constraintLayout;
        this.btnDeleteDevice = button;
        this.ivBack = imageView;
        this.ivDeviceIcon = imageView2;
        this.layoutDeviceAlias = constraintLayout2;
        this.layoutFirmwareUpdate = constraintLayout3;
        this.layoutHead = relativeLayout;
        this.layoutLedNum = constraintLayout4;
        this.tvAliasTitle = textView;
        this.tvDeviceAlias = textView2;
        this.tvDeviceName = textView3;
        this.tvFirmwareTick = textView4;
        this.tvFirmwareVersion = textView5;
        this.tvLedNum = textView6;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceInfoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityDeviceInfoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_device_info, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityDeviceInfoBinding bind(View view) {
        View view2 = view;
        int i = R.id.btn_delete_device;
        Button button = (Button) ViewBindings.findChildViewById(view2, i);
        if (button != null) {
            i = R.id.iv_back;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view2, i);
            if (imageView != null) {
                i = R.id.iv_device_icon;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view2, i);
                if (imageView2 != null) {
                    i = R.id.layout_device_alias;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view2, i);
                    if (constraintLayout != null) {
                        i = R.id.layout_firmware_update;
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view2, i);
                        if (constraintLayout2 != null) {
                            i = R.id.layout_head;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view2, i);
                            if (relativeLayout != null) {
                                i = R.id.layout_led_num;
                                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view2, i);
                                if (constraintLayout3 != null) {
                                    i = R.id.tv_alias_title;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view2, i);
                                    if (textView != null) {
                                        i = R.id.tv_device_alias;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view2, i);
                                        if (textView2 != null) {
                                            i = R.id.tv_device_name;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view2, i);
                                            if (textView3 != null) {
                                                i = R.id.tv_firmware_tick;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                if (textView4 != null) {
                                                    i = R.id.tv_firmware_version;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                    if (textView5 != null) {
                                                        i = R.id.tv_led_num;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                        if (textView6 != null) {
                                                            return new ActivityDeviceInfoBinding((ConstraintLayout) view2, button, imageView, imageView2, constraintLayout, constraintLayout2, relativeLayout, constraintLayout3, textView, textView2, textView3, textView4, textView5, textView6);
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
