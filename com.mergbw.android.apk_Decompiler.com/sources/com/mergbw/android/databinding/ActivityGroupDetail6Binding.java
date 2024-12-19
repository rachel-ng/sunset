package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

public final class ActivityGroupDetail6Binding implements ViewBinding {
    public final CheckBox cbCheckAll;
    public final ImageView ivBack;
    public final ImageView ivBrightnessMax;
    public final ImageView ivBrightnessMin;
    public final ImageView ivDeviceIcon;
    public final SwitchCompat ivItemSwitch;
    public final ImageView ivSetting;
    public final ConstraintLayout layoutBrightness;
    public final FrameLayout layoutContent;
    public final ConstraintLayout layoutDeviceControl;
    public final ConstraintLayout layoutHead;
    public final RecyclerView listDevice;
    public final RecyclerView listTab;
    private final LinearLayout rootView;
    public final SeekBar sbBrightness;
    public final TextView tvGroupName;
    public final TextView tvItemModel;
    public final TextView tvTitle;
    public final View viewDivider;

    private ActivityGroupDetail6Binding(LinearLayout linearLayout, CheckBox checkBox, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, SwitchCompat switchCompat, ImageView imageView5, ConstraintLayout constraintLayout, FrameLayout frameLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, RecyclerView recyclerView, RecyclerView recyclerView2, SeekBar seekBar, TextView textView, TextView textView2, TextView textView3, View view) {
        this.rootView = linearLayout;
        this.cbCheckAll = checkBox;
        this.ivBack = imageView;
        this.ivBrightnessMax = imageView2;
        this.ivBrightnessMin = imageView3;
        this.ivDeviceIcon = imageView4;
        this.ivItemSwitch = switchCompat;
        this.ivSetting = imageView5;
        this.layoutBrightness = constraintLayout;
        this.layoutContent = frameLayout;
        this.layoutDeviceControl = constraintLayout2;
        this.layoutHead = constraintLayout3;
        this.listDevice = recyclerView;
        this.listTab = recyclerView2;
        this.sbBrightness = seekBar;
        this.tvGroupName = textView;
        this.tvItemModel = textView2;
        this.tvTitle = textView3;
        this.viewDivider = view;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGroupDetail6Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityGroupDetail6Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_group_detail_6, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c3, code lost:
        r1 = com.mergbw.android.R.id.view_divider;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mergbw.android.databinding.ActivityGroupDetail6Binding bind(android.view.View r23) {
        /*
            r0 = r23
            int r1 = com.mergbw.android.R.id.cb_check_all
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r5 = r2
            android.widget.CheckBox r5 = (android.widget.CheckBox) r5
            if (r5 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.iv_back
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r6 = r2
            android.widget.ImageView r6 = (android.widget.ImageView) r6
            if (r6 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.iv_brightness_max
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r7 = r2
            android.widget.ImageView r7 = (android.widget.ImageView) r7
            if (r7 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.iv_brightness_min
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r8 = r2
            android.widget.ImageView r8 = (android.widget.ImageView) r8
            if (r8 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.iv_device_icon
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r9 = r2
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            if (r9 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.iv_item_switch
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r10 = r2
            androidx.appcompat.widget.SwitchCompat r10 = (androidx.appcompat.widget.SwitchCompat) r10
            if (r10 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.iv_setting
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r11 = r2
            android.widget.ImageView r11 = (android.widget.ImageView) r11
            if (r11 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.layout_brightness
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r12 = r2
            androidx.constraintlayout.widget.ConstraintLayout r12 = (androidx.constraintlayout.widget.ConstraintLayout) r12
            if (r12 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.layout_content
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r13 = r2
            android.widget.FrameLayout r13 = (android.widget.FrameLayout) r13
            if (r13 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.layout_device_control
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r14 = r2
            androidx.constraintlayout.widget.ConstraintLayout r14 = (androidx.constraintlayout.widget.ConstraintLayout) r14
            if (r14 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.layout_head
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r15 = r2
            androidx.constraintlayout.widget.ConstraintLayout r15 = (androidx.constraintlayout.widget.ConstraintLayout) r15
            if (r15 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.list_device
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r16 = r2
            androidx.recyclerview.widget.RecyclerView r16 = (androidx.recyclerview.widget.RecyclerView) r16
            if (r16 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.list_tab
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r17 = r2
            androidx.recyclerview.widget.RecyclerView r17 = (androidx.recyclerview.widget.RecyclerView) r17
            if (r17 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.sb_brightness
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r18 = r2
            android.widget.SeekBar r18 = (android.widget.SeekBar) r18
            if (r18 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.tv_group_name
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r19 = r2
            android.widget.TextView r19 = (android.widget.TextView) r19
            if (r19 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.tv_item_model
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r20 = r2
            android.widget.TextView r20 = (android.widget.TextView) r20
            if (r20 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.tv_title
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r21 = r2
            android.widget.TextView r21 = (android.widget.TextView) r21
            if (r21 == 0) goto L_0x00d5
            int r1 = com.mergbw.android.R.id.view_divider
            android.view.View r22 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            if (r22 == 0) goto L_0x00d5
            com.mergbw.android.databinding.ActivityGroupDetail6Binding r1 = new com.mergbw.android.databinding.ActivityGroupDetail6Binding
            r3 = r1
            r4 = r0
            android.widget.LinearLayout r4 = (android.widget.LinearLayout) r4
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return r1
        L_0x00d5:
            android.content.res.Resources r0 = r23.getResources()
            java.lang.String r0 = r0.getResourceName(r1)
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "Missing required view with ID: "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mergbw.android.databinding.ActivityGroupDetail6Binding.bind(android.view.View):com.mergbw.android.databinding.ActivityGroupDetail6Binding");
    }
}
