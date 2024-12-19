package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.mergbw.android.R;

public final class ActivityLoginBinding implements ViewBinding {
    public final Button btnLogin;
    public final Button btnRegister;
    public final EditText etPassword;
    public final EditText etUsername;
    public final ImageView ivBack;
    public final RelativeLayout layoutHead;
    private final ConstraintLayout rootView;
    public final TextView tvEmailError;
    public final TextView tvForgetPassword;
    public final TextView tvPasswordError;
    public final TextView tvWelcome;
    public final View viewEmailDivider;
    public final View viewPasswordDivider;

    private ActivityLoginBinding(ConstraintLayout constraintLayout, Button button, Button button2, EditText editText, EditText editText2, ImageView imageView, RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view, View view2) {
        this.rootView = constraintLayout;
        this.btnLogin = button;
        this.btnRegister = button2;
        this.etPassword = editText;
        this.etUsername = editText2;
        this.ivBack = imageView;
        this.layoutHead = relativeLayout;
        this.tvEmailError = textView;
        this.tvForgetPassword = textView2;
        this.tvPasswordError = textView3;
        this.tvWelcome = textView4;
        this.viewEmailDivider = view;
        this.viewPasswordDivider = view2;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLoginBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityLoginBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_login, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0070, code lost:
        r1 = com.mergbw.android.R.id.view_email_divider;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0078, code lost:
        r1 = com.mergbw.android.R.id.view_password_divider;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mergbw.android.databinding.ActivityLoginBinding bind(android.view.View r17) {
        /*
            r0 = r17
            int r1 = com.mergbw.android.R.id.btn_login
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r5 = r2
            android.widget.Button r5 = (android.widget.Button) r5
            if (r5 == 0) goto L_0x008a
            int r1 = com.mergbw.android.R.id.btn_register
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r6 = r2
            android.widget.Button r6 = (android.widget.Button) r6
            if (r6 == 0) goto L_0x008a
            int r1 = com.mergbw.android.R.id.et_password
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r7 = r2
            android.widget.EditText r7 = (android.widget.EditText) r7
            if (r7 == 0) goto L_0x008a
            int r1 = com.mergbw.android.R.id.et_username
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r8 = r2
            android.widget.EditText r8 = (android.widget.EditText) r8
            if (r8 == 0) goto L_0x008a
            int r1 = com.mergbw.android.R.id.iv_back
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r9 = r2
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            if (r9 == 0) goto L_0x008a
            int r1 = com.mergbw.android.R.id.layout_head
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r10 = r2
            android.widget.RelativeLayout r10 = (android.widget.RelativeLayout) r10
            if (r10 == 0) goto L_0x008a
            int r1 = com.mergbw.android.R.id.tv_email_error
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r11 = r2
            android.widget.TextView r11 = (android.widget.TextView) r11
            if (r11 == 0) goto L_0x008a
            int r1 = com.mergbw.android.R.id.tv_forget_password
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r12 = r2
            android.widget.TextView r12 = (android.widget.TextView) r12
            if (r12 == 0) goto L_0x008a
            int r1 = com.mergbw.android.R.id.tv_password_error
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r13 = r2
            android.widget.TextView r13 = (android.widget.TextView) r13
            if (r13 == 0) goto L_0x008a
            int r1 = com.mergbw.android.R.id.tv_welcome
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r14 = r2
            android.widget.TextView r14 = (android.widget.TextView) r14
            if (r14 == 0) goto L_0x008a
            int r1 = com.mergbw.android.R.id.view_email_divider
            android.view.View r15 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            if (r15 == 0) goto L_0x008a
            int r1 = com.mergbw.android.R.id.view_password_divider
            android.view.View r16 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            if (r16 == 0) goto L_0x008a
            com.mergbw.android.databinding.ActivityLoginBinding r1 = new com.mergbw.android.databinding.ActivityLoginBinding
            r4 = r0
            androidx.constraintlayout.widget.ConstraintLayout r4 = (androidx.constraintlayout.widget.ConstraintLayout) r4
            r3 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return r1
        L_0x008a:
            android.content.res.Resources r0 = r17.getResources()
            java.lang.String r0 = r0.getResourceName(r1)
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "Missing required view with ID: "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mergbw.android.databinding.ActivityLoginBinding.bind(android.view.View):com.mergbw.android.databinding.ActivityLoginBinding");
    }
}
