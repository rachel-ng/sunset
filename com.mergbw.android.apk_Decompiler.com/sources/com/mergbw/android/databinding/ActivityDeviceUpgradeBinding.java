package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;
import com.mergbw.android.ui.view.WaveProgressView;

public final class ActivityDeviceUpgradeBinding implements ViewBinding {
    public final Button btnComplete;
    public final Button btnUpgrade;
    public final ImageView ivBack;
    public final ImageView ivCompleteCover;
    public final LinearLayout layoutBeforeUpgrade;
    public final RelativeLayout layoutHead;
    public final LinearLayout layoutHint;
    public final ConstraintLayout layoutUpgrading;
    private final ConstraintLayout rootView;
    public final TextView tvFirmwareVersion;
    public final TextView tvNewFirmware;
    public final TextView tvUpgradePercent;
    public final TextView tvUpgradeTick;
    public final WaveProgressView viewProgress;

    private ActivityDeviceUpgradeBinding(ConstraintLayout constraintLayout, Button button, Button button2, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, RelativeLayout relativeLayout, LinearLayout linearLayout2, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, WaveProgressView waveProgressView) {
        this.rootView = constraintLayout;
        this.btnComplete = button;
        this.btnUpgrade = button2;
        this.ivBack = imageView;
        this.ivCompleteCover = imageView2;
        this.layoutBeforeUpgrade = linearLayout;
        this.layoutHead = relativeLayout;
        this.layoutHint = linearLayout2;
        this.layoutUpgrading = constraintLayout2;
        this.tvFirmwareVersion = textView;
        this.tvNewFirmware = textView2;
        this.tvUpgradePercent = textView3;
        this.tvUpgradeTick = textView4;
        this.viewProgress = waveProgressView;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceUpgradeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityDeviceUpgradeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_device_upgrade, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityDeviceUpgradeBinding bind(View view) {
        View view2 = view;
        int i = R.id.btn_complete;
        Button button = (Button) ViewBindings.findChildViewById(view2, i);
        if (button != null) {
            i = R.id.btn_upgrade;
            Button button2 = (Button) ViewBindings.findChildViewById(view2, i);
            if (button2 != null) {
                i = R.id.iv_back;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view2, i);
                if (imageView != null) {
                    i = R.id.iv_complete_cover;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view2, i);
                    if (imageView2 != null) {
                        i = R.id.layout_before_upgrade;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view2, i);
                        if (linearLayout != null) {
                            i = R.id.layout_head;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view2, i);
                            if (relativeLayout != null) {
                                i = R.id.layout_hint;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view2, i);
                                if (linearLayout2 != null) {
                                    i = R.id.layout_upgrading;
                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view2, i);
                                    if (constraintLayout != null) {
                                        i = R.id.tv_firmware_version;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view2, i);
                                        if (textView != null) {
                                            i = R.id.tv_new_firmware;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view2, i);
                                            if (textView2 != null) {
                                                i = R.id.tv_upgrade_percent;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                if (textView3 != null) {
                                                    i = R.id.tv_upgrade_tick;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                    if (textView4 != null) {
                                                        i = R.id.view_progress;
                                                        WaveProgressView waveProgressView = (WaveProgressView) ViewBindings.findChildViewById(view2, i);
                                                        if (waveProgressView != null) {
                                                            return new ActivityDeviceUpgradeBinding((ConstraintLayout) view2, button, button2, imageView, imageView2, linearLayout, relativeLayout, linearLayout2, constraintLayout, textView, textView2, textView3, textView4, waveProgressView);
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
