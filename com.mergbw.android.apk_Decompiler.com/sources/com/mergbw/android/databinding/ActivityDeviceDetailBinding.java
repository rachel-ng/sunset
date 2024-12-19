package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.mergbw.android.R;

public final class ActivityDeviceDetailBinding implements ViewBinding {
    public final Button btnReconnect;
    public final ImageView ivAlarm;
    public final ImageView ivBack;
    public final ImageView ivBrightnessMax;
    public final ImageView ivBrightnessMin;
    public final ImageView ivConnectedFail;
    public final ImageView ivDeviceIcon;
    public final ImageView ivItemState;
    public final SwitchCompat ivItemSwitch;
    public final ImageView ivSetting;
    public final ConstraintLayout layoutBrightness;
    public final ConstraintLayout layoutConnectedFail;
    public final FrameLayout layoutContent;
    public final ConstraintLayout layoutDeviceControl;
    public final LinearLayout layoutDeviceSetting;
    public final ConstraintLayout layoutHead;
    public final RecyclerView listTab;
    private final ConstraintLayout rootView;
    public final SeekBar sbBrightness;
    public final TextView tvConnectedFail;
    public final TextView tvDeviceName;
    public final TextView tvItemModel;
    public final TextView tvTitle;
    public final View viewDivider;

    private ActivityDeviceDetailBinding(ConstraintLayout constraintLayout, Button button, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, SwitchCompat switchCompat, ImageView imageView8, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, FrameLayout frameLayout, ConstraintLayout constraintLayout4, LinearLayout linearLayout, ConstraintLayout constraintLayout5, RecyclerView recyclerView, SeekBar seekBar, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view) {
        this.rootView = constraintLayout;
        this.btnReconnect = button;
        this.ivAlarm = imageView;
        this.ivBack = imageView2;
        this.ivBrightnessMax = imageView3;
        this.ivBrightnessMin = imageView4;
        this.ivConnectedFail = imageView5;
        this.ivDeviceIcon = imageView6;
        this.ivItemState = imageView7;
        this.ivItemSwitch = switchCompat;
        this.ivSetting = imageView8;
        this.layoutBrightness = constraintLayout2;
        this.layoutConnectedFail = constraintLayout3;
        this.layoutContent = frameLayout;
        this.layoutDeviceControl = constraintLayout4;
        this.layoutDeviceSetting = linearLayout;
        this.layoutHead = constraintLayout5;
        this.listTab = recyclerView;
        this.sbBrightness = seekBar;
        this.tvConnectedFail = textView;
        this.tvDeviceName = textView2;
        this.tvItemModel = textView3;
        this.tvTitle = textView4;
        this.viewDivider = view;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityDeviceDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_device_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ff, code lost:
        r1 = com.mergbw.android.R.id.view_divider;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mergbw.android.databinding.ActivityDeviceDetailBinding bind(android.view.View r28) {
        /*
            r0 = r28
            int r1 = com.mergbw.android.R.id.btn_reconnect
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r5 = r2
            android.widget.Button r5 = (android.widget.Button) r5
            if (r5 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.iv_alarm
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r6 = r2
            android.widget.ImageView r6 = (android.widget.ImageView) r6
            if (r6 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.iv_back
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r7 = r2
            android.widget.ImageView r7 = (android.widget.ImageView) r7
            if (r7 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.iv_brightness_max
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r8 = r2
            android.widget.ImageView r8 = (android.widget.ImageView) r8
            if (r8 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.iv_brightness_min
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r9 = r2
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            if (r9 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.iv_connected_fail
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r10 = r2
            android.widget.ImageView r10 = (android.widget.ImageView) r10
            if (r10 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.iv_device_icon
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r11 = r2
            android.widget.ImageView r11 = (android.widget.ImageView) r11
            if (r11 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.iv_item_state
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r12 = r2
            android.widget.ImageView r12 = (android.widget.ImageView) r12
            if (r12 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.iv_item_switch
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r13 = r2
            androidx.appcompat.widget.SwitchCompat r13 = (androidx.appcompat.widget.SwitchCompat) r13
            if (r13 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.iv_setting
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r14 = r2
            android.widget.ImageView r14 = (android.widget.ImageView) r14
            if (r14 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.layout_brightness
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r15 = r2
            androidx.constraintlayout.widget.ConstraintLayout r15 = (androidx.constraintlayout.widget.ConstraintLayout) r15
            if (r15 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.layout_connected_fail
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r16 = r2
            androidx.constraintlayout.widget.ConstraintLayout r16 = (androidx.constraintlayout.widget.ConstraintLayout) r16
            if (r16 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.layout_content
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r17 = r2
            android.widget.FrameLayout r17 = (android.widget.FrameLayout) r17
            if (r17 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.layout_device_control
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r18 = r2
            androidx.constraintlayout.widget.ConstraintLayout r18 = (androidx.constraintlayout.widget.ConstraintLayout) r18
            if (r18 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.layout_device_setting
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r19 = r2
            android.widget.LinearLayout r19 = (android.widget.LinearLayout) r19
            if (r19 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.layout_head
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r20 = r2
            androidx.constraintlayout.widget.ConstraintLayout r20 = (androidx.constraintlayout.widget.ConstraintLayout) r20
            if (r20 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.list_tab
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r21 = r2
            androidx.recyclerview.widget.RecyclerView r21 = (androidx.recyclerview.widget.RecyclerView) r21
            if (r21 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.sb_brightness
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r22 = r2
            android.widget.SeekBar r22 = (android.widget.SeekBar) r22
            if (r22 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.tv_connected_fail
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r23 = r2
            android.widget.TextView r23 = (android.widget.TextView) r23
            if (r23 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.tv_device_name
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r24 = r2
            android.widget.TextView r24 = (android.widget.TextView) r24
            if (r24 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.tv_item_model
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r25 = r2
            android.widget.TextView r25 = (android.widget.TextView) r25
            if (r25 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.tv_title
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r26 = r2
            android.widget.TextView r26 = (android.widget.TextView) r26
            if (r26 == 0) goto L_0x0111
            int r1 = com.mergbw.android.R.id.view_divider
            android.view.View r27 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            if (r27 == 0) goto L_0x0111
            com.mergbw.android.databinding.ActivityDeviceDetailBinding r1 = new com.mergbw.android.databinding.ActivityDeviceDetailBinding
            r3 = r1
            r4 = r0
            androidx.constraintlayout.widget.ConstraintLayout r4 = (androidx.constraintlayout.widget.ConstraintLayout) r4
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            return r1
        L_0x0111:
            android.content.res.Resources r0 = r28.getResources()
            java.lang.String r0 = r0.getResourceName(r1)
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "Missing required view with ID: "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mergbw.android.databinding.ActivityDeviceDetailBinding.bind(android.view.View):com.mergbw.android.databinding.ActivityDeviceDetailBinding");
    }
}
