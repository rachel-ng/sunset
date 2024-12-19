package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.mergbw.android.R;

public final class ActivityAddDeviceBinding implements ViewBinding {
    public final Button btnScanAgain;
    public final ImageView ivBack;
    public final ConstraintLayout layoutDeviceList;
    public final RelativeLayout layoutHead;
    public final LinearLayout layoutNoDevice;
    public final ConstraintLayout layoutSearch;
    public final ListView listDevice;
    private final ConstraintLayout rootView;
    public final TextView tvScanHint;
    public final TextView tvScanTick;
    public final LottieAnimationView viewLoading;

    private ActivityAddDeviceBinding(ConstraintLayout constraintLayout, Button button, ImageView imageView, ConstraintLayout constraintLayout2, RelativeLayout relativeLayout, LinearLayout linearLayout, ConstraintLayout constraintLayout3, ListView listView, TextView textView, TextView textView2, LottieAnimationView lottieAnimationView) {
        this.rootView = constraintLayout;
        this.btnScanAgain = button;
        this.ivBack = imageView;
        this.layoutDeviceList = constraintLayout2;
        this.layoutHead = relativeLayout;
        this.layoutNoDevice = linearLayout;
        this.layoutSearch = constraintLayout3;
        this.listDevice = listView;
        this.tvScanHint = textView;
        this.tvScanTick = textView2;
        this.viewLoading = lottieAnimationView;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAddDeviceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityAddDeviceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_add_device, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityAddDeviceBinding bind(View view) {
        int i = R.id.btn_scan_again;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.iv_back;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.layout_device_list;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                if (constraintLayout != null) {
                    i = R.id.layout_head;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                    if (relativeLayout != null) {
                        i = R.id.layout_no_device;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            i = R.id.layout_search;
                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                            if (constraintLayout2 != null) {
                                i = R.id.list_device;
                                ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
                                if (listView != null) {
                                    i = R.id.tv_scan_hint;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView != null) {
                                        i = R.id.tv_scan_tick;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView2 != null) {
                                            i = R.id.view_loading;
                                            LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                                            if (lottieAnimationView != null) {
                                                return new ActivityAddDeviceBinding((ConstraintLayout) view, button, imageView, constraintLayout, relativeLayout, linearLayout, constraintLayout2, listView, textView, textView2, lottieAnimationView);
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
