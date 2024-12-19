package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class ActivityFirstCheckPrivacyBinding implements ViewBinding {
    public final Button btnAccept;
    public final Button btnRefuse;
    public final ImageView ivAcceptPrivacy;
    public final ImageView ivLogo;
    private final ConstraintLayout rootView;
    public final TextView tvPrivacyAgreement;
    public final TextView tvPrivacyHint;
    public final TextView tvUserAgreement;

    private ActivityFirstCheckPrivacyBinding(ConstraintLayout constraintLayout, Button button, Button button2, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.btnAccept = button;
        this.btnRefuse = button2;
        this.ivAcceptPrivacy = imageView;
        this.ivLogo = imageView2;
        this.tvPrivacyAgreement = textView;
        this.tvPrivacyHint = textView2;
        this.tvUserAgreement = textView3;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFirstCheckPrivacyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityFirstCheckPrivacyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_first_check_privacy, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityFirstCheckPrivacyBinding bind(View view) {
        int i = R.id.btn_accept;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.btn_refuse;
            Button button2 = (Button) ViewBindings.findChildViewById(view, i);
            if (button2 != null) {
                i = R.id.iv_accept_privacy;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.iv_logo;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView2 != null) {
                        i = R.id.tv_privacy_agreement;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.tv_privacy_hint;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                i = R.id.tv_user_agreement;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView3 != null) {
                                    return new ActivityFirstCheckPrivacyBinding((ConstraintLayout) view, button, button2, imageView, imageView2, textView, textView2, textView3);
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
