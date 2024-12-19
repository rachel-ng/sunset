package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.mergbw.core.R;
import com.mergbw.core.ui.views.CommonColorPickerView;
import com.mergbw.core.ui.views.SubListView;

public final class ActivitySubColorSettingBinding implements ViewBinding {
    public final ImageView ivBack;
    public final ConstraintLayout layoutHead;
    private final LinearLayout rootView;
    public final TextView tvAllCheck;
    public final TextView tvDeviceName;
    public final TextView tvSave;
    public final TextView tvTitle;
    public final CommonColorPickerView viewCommonColorPicker;
    public final View viewDivider;
    public final SubListView viewSubList;

    private ActivitySubColorSettingBinding(LinearLayout linearLayout, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, CommonColorPickerView commonColorPickerView, View view, SubListView subListView) {
        this.rootView = linearLayout;
        this.ivBack = imageView;
        this.layoutHead = constraintLayout;
        this.tvAllCheck = textView;
        this.tvDeviceName = textView2;
        this.tvSave = textView3;
        this.tvTitle = textView4;
        this.viewCommonColorPicker = commonColorPickerView;
        this.viewDivider = view;
        this.viewSubList = subListView;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySubColorSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivitySubColorSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_sub_color_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        r0 = com.mergbw.core.R.id.view_divider;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mergbw.core.databinding.ActivitySubColorSettingBinding bind(android.view.View r13) {
        /*
            int r0 = com.mergbw.core.R.id.iv_back
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r13, r0)
            r4 = r1
            android.widget.ImageView r4 = (android.widget.ImageView) r4
            if (r4 == 0) goto L_0x006a
            int r0 = com.mergbw.core.R.id.layout_head
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r13, r0)
            r5 = r1
            androidx.constraintlayout.widget.ConstraintLayout r5 = (androidx.constraintlayout.widget.ConstraintLayout) r5
            if (r5 == 0) goto L_0x006a
            int r0 = com.mergbw.core.R.id.tv_all_check
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r13, r0)
            r6 = r1
            android.widget.TextView r6 = (android.widget.TextView) r6
            if (r6 == 0) goto L_0x006a
            int r0 = com.mergbw.core.R.id.tv_device_name
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r13, r0)
            r7 = r1
            android.widget.TextView r7 = (android.widget.TextView) r7
            if (r7 == 0) goto L_0x006a
            int r0 = com.mergbw.core.R.id.tv_save
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r13, r0)
            r8 = r1
            android.widget.TextView r8 = (android.widget.TextView) r8
            if (r8 == 0) goto L_0x006a
            int r0 = com.mergbw.core.R.id.tv_title
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r13, r0)
            r9 = r1
            android.widget.TextView r9 = (android.widget.TextView) r9
            if (r9 == 0) goto L_0x006a
            int r0 = com.mergbw.core.R.id.view_common_color_picker
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r13, r0)
            r10 = r1
            com.mergbw.core.ui.views.CommonColorPickerView r10 = (com.mergbw.core.ui.views.CommonColorPickerView) r10
            if (r10 == 0) goto L_0x006a
            int r0 = com.mergbw.core.R.id.view_divider
            android.view.View r11 = androidx.viewbinding.ViewBindings.findChildViewById(r13, r0)
            if (r11 == 0) goto L_0x006a
            int r0 = com.mergbw.core.R.id.view_sub_list
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r13, r0)
            r12 = r1
            com.mergbw.core.ui.views.SubListView r12 = (com.mergbw.core.ui.views.SubListView) r12
            if (r12 == 0) goto L_0x006a
            com.mergbw.core.databinding.ActivitySubColorSettingBinding r0 = new com.mergbw.core.databinding.ActivitySubColorSettingBinding
            r3 = r13
            android.widget.LinearLayout r3 = (android.widget.LinearLayout) r3
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r0
        L_0x006a:
            android.content.res.Resources r13 = r13.getResources()
            java.lang.String r13 = r13.getResourceName(r0)
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "Missing required view with ID: "
            java.lang.String r13 = r1.concat(r13)
            r0.<init>(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mergbw.core.databinding.ActivitySubColorSettingBinding.bind(android.view.View):com.mergbw.core.databinding.ActivitySubColorSettingBinding");
    }
}
