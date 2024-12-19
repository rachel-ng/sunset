package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.mergbw.android.R;

public final class ActivityRegisterBinding implements ViewBinding {
    public final Button btnNext;
    public final Button btnSubmit;
    public final EditText etEmail;
    public final EditText etPassword;
    public final EditText etPasswordAgain;
    public final EditText etVerify;
    public final ImageView ivBack;
    public final LinearLayout layoutEmail;
    public final RelativeLayout layoutHead;
    public final LinearLayout layoutVerify;
    private final ConstraintLayout rootView;
    public final TextView tvEmialError;
    public final TextView tvPasswordAgainError;
    public final TextView tvPasswordError;
    public final TextView tvVerifyError;
    public final View viewEmailDivider;
    public final View viewPasswordAgainDivider;
    public final View viewPasswordDivider;
    public final View viewVerifyDvider;

    private ActivityRegisterBinding(ConstraintLayout constraintLayout, Button button, Button button2, EditText editText, EditText editText2, EditText editText3, EditText editText4, ImageView imageView, LinearLayout linearLayout, RelativeLayout relativeLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view, View view2, View view3, View view4) {
        this.rootView = constraintLayout;
        this.btnNext = button;
        this.btnSubmit = button2;
        this.etEmail = editText;
        this.etPassword = editText2;
        this.etPasswordAgain = editText3;
        this.etVerify = editText4;
        this.ivBack = imageView;
        this.layoutEmail = linearLayout;
        this.layoutHead = relativeLayout;
        this.layoutVerify = linearLayout2;
        this.tvEmialError = textView;
        this.tvPasswordAgainError = textView2;
        this.tvPasswordError = textView3;
        this.tvVerifyError = textView4;
        this.viewEmailDivider = view;
        this.viewPasswordAgainDivider = view2;
        this.viewPasswordDivider = view3;
        this.viewVerifyDvider = view4;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRegisterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityRegisterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_register, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009f, code lost:
        r1 = com.mergbw.android.R.id.view_email_divider;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a7, code lost:
        r1 = com.mergbw.android.R.id.view_password_again_divider;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00af, code lost:
        r1 = com.mergbw.android.R.id.view_password_divider;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b7, code lost:
        r1 = com.mergbw.android.R.id.view_verify_dvider;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mergbw.android.databinding.ActivityRegisterBinding bind(android.view.View r23) {
        /*
            r0 = r23
            int r1 = com.mergbw.android.R.id.btn_next
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r5 = r2
            android.widget.Button r5 = (android.widget.Button) r5
            if (r5 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.btn_submit
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r6 = r2
            android.widget.Button r6 = (android.widget.Button) r6
            if (r6 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.et_email
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r7 = r2
            android.widget.EditText r7 = (android.widget.EditText) r7
            if (r7 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.et_password
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r8 = r2
            android.widget.EditText r8 = (android.widget.EditText) r8
            if (r8 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.et_password_again
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r9 = r2
            android.widget.EditText r9 = (android.widget.EditText) r9
            if (r9 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.et_verify
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r10 = r2
            android.widget.EditText r10 = (android.widget.EditText) r10
            if (r10 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.iv_back
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r11 = r2
            android.widget.ImageView r11 = (android.widget.ImageView) r11
            if (r11 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.layout_email
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r12 = r2
            android.widget.LinearLayout r12 = (android.widget.LinearLayout) r12
            if (r12 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.layout_head
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r13 = r2
            android.widget.RelativeLayout r13 = (android.widget.RelativeLayout) r13
            if (r13 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.layout_verify
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r14 = r2
            android.widget.LinearLayout r14 = (android.widget.LinearLayout) r14
            if (r14 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.tv_emial_error
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r15 = r2
            android.widget.TextView r15 = (android.widget.TextView) r15
            if (r15 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.tv_password_again_error
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r16 = r2
            android.widget.TextView r16 = (android.widget.TextView) r16
            if (r16 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.tv_password_error
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r17 = r2
            android.widget.TextView r17 = (android.widget.TextView) r17
            if (r17 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.tv_verify_error
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r18 = r2
            android.widget.TextView r18 = (android.widget.TextView) r18
            if (r18 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.view_email_divider
            android.view.View r19 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            if (r19 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.view_password_again_divider
            android.view.View r20 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            if (r20 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.view_password_divider
            android.view.View r21 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            if (r21 == 0) goto L_0x00c9
            int r1 = com.mergbw.android.R.id.view_verify_dvider
            android.view.View r22 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            if (r22 == 0) goto L_0x00c9
            com.mergbw.android.databinding.ActivityRegisterBinding r1 = new com.mergbw.android.databinding.ActivityRegisterBinding
            r3 = r1
            r4 = r0
            androidx.constraintlayout.widget.ConstraintLayout r4 = (androidx.constraintlayout.widget.ConstraintLayout) r4
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return r1
        L_0x00c9:
            android.content.res.Resources r0 = r23.getResources()
            java.lang.String r0 = r0.getResourceName(r1)
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "Missing required view with ID: "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mergbw.android.databinding.ActivityRegisterBinding.bind(android.view.View):com.mergbw.android.databinding.ActivityRegisterBinding");
    }
}
