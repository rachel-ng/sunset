package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class ActivityAboutBinding implements ViewBinding {
    public final ImageView ivBack;
    public final ConstraintLayout layoutAgreement;
    public final RelativeLayout layoutHead;
    public final ConstraintLayout layoutPrivacy;
    public final ConstraintLayout layoutResume;
    private final LinearLayout rootView;

    private ActivityAboutBinding(LinearLayout linearLayout, ImageView imageView, ConstraintLayout constraintLayout, RelativeLayout relativeLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3) {
        this.rootView = linearLayout;
        this.ivBack = imageView;
        this.layoutAgreement = constraintLayout;
        this.layoutHead = relativeLayout;
        this.layoutPrivacy = constraintLayout2;
        this.layoutResume = constraintLayout3;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAboutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityAboutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_about, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityAboutBinding bind(View view) {
        int i = R.id.iv_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.layout_agreement;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
            if (constraintLayout != null) {
                i = R.id.layout_head;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                if (relativeLayout != null) {
                    i = R.id.layout_privacy;
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                    if (constraintLayout2 != null) {
                        i = R.id.layout_resume;
                        ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                        if (constraintLayout3 != null) {
                            return new ActivityAboutBinding((LinearLayout) view, imageView, constraintLayout, relativeLayout, constraintLayout2, constraintLayout3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
