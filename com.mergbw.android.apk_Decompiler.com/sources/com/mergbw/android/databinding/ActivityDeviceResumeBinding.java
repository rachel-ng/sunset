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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;
import com.mergbw.android.ui.view.WaveProgressView;

public final class ActivityDeviceResumeBinding implements ViewBinding {
    public final Button btnComplete;
    public final Button btnUpgrade;
    public final ImageView ivBack;
    public final ImageView ivCompleteCover;
    public final LinearLayout layoutBeforeUpgrade;
    public final RelativeLayout layoutHead;
    public final LinearLayout layoutHint;
    public final LinearLayout layoutRecord;
    public final ConstraintLayout layoutResume;
    public final ConstraintLayout layoutUpgrading;
    public final RecyclerView listRecord;
    private final ConstraintLayout rootView;
    public final TextView tvUpgradePercent;
    public final TextView tvUpgradeTick;
    public final WaveProgressView viewProgress;

    private ActivityDeviceResumeBinding(ConstraintLayout constraintLayout, Button button, Button button2, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, RelativeLayout relativeLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, RecyclerView recyclerView, TextView textView, TextView textView2, WaveProgressView waveProgressView) {
        this.rootView = constraintLayout;
        this.btnComplete = button;
        this.btnUpgrade = button2;
        this.ivBack = imageView;
        this.ivCompleteCover = imageView2;
        this.layoutBeforeUpgrade = linearLayout;
        this.layoutHead = relativeLayout;
        this.layoutHint = linearLayout2;
        this.layoutRecord = linearLayout3;
        this.layoutResume = constraintLayout2;
        this.layoutUpgrading = constraintLayout3;
        this.listRecord = recyclerView;
        this.tvUpgradePercent = textView;
        this.tvUpgradeTick = textView2;
        this.viewProgress = waveProgressView;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceResumeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityDeviceResumeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_device_resume, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityDeviceResumeBinding bind(View view) {
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
                                    i = R.id.layout_record;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view2, i);
                                    if (linearLayout3 != null) {
                                        i = R.id.layout_resume;
                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view2, i);
                                        if (constraintLayout != null) {
                                            i = R.id.layout_upgrading;
                                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view2, i);
                                            if (constraintLayout2 != null) {
                                                i = R.id.list_record;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view2, i);
                                                if (recyclerView != null) {
                                                    i = R.id.tv_upgrade_percent;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view2, i);
                                                    if (textView != null) {
                                                        i = R.id.tv_upgrade_tick;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                        if (textView2 != null) {
                                                            i = R.id.view_progress;
                                                            WaveProgressView waveProgressView = (WaveProgressView) ViewBindings.findChildViewById(view2, i);
                                                            if (waveProgressView != null) {
                                                                return new ActivityDeviceResumeBinding((ConstraintLayout) view2, button, button2, imageView, imageView2, linearLayout, relativeLayout, linearLayout2, linearLayout3, constraintLayout, constraintLayout2, recyclerView, textView, textView2, waveProgressView);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
