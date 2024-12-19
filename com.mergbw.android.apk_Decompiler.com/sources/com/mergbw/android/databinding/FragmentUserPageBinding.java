package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class FragmentUserPageBinding implements ViewBinding {
    public final ImageView ivAvtar;
    public final ImageView ivItem1;
    public final ImageView ivItem2;
    public final ImageView ivItem3;
    public final ConstraintLayout layoutAbout;
    public final ConstraintLayout layoutContactUs;
    public final ConstraintLayout layoutLogin;
    public final ConstraintLayout layoutUserManual;
    private final FrameLayout rootView;
    public final TextView tvUsername;
    public final TextView tvVersion;

    private FragmentUserPageBinding(FrameLayout frameLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, TextView textView, TextView textView2) {
        this.rootView = frameLayout;
        this.ivAvtar = imageView;
        this.ivItem1 = imageView2;
        this.ivItem2 = imageView3;
        this.ivItem3 = imageView4;
        this.layoutAbout = constraintLayout;
        this.layoutContactUs = constraintLayout2;
        this.layoutLogin = constraintLayout3;
        this.layoutUserManual = constraintLayout4;
        this.tvUsername = textView;
        this.tvVersion = textView2;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentUserPageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentUserPageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_user_page, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentUserPageBinding bind(View view) {
        int i = R.id.iv_avtar;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.iv_item_1;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.iv_item_2;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView3 != null) {
                    i = R.id.iv_item_3;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView4 != null) {
                        i = R.id.layout_about;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                        if (constraintLayout != null) {
                            i = R.id.layout_contact_us;
                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                            if (constraintLayout2 != null) {
                                i = R.id.layout_login;
                                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                if (constraintLayout3 != null) {
                                    i = R.id.layout_user_manual;
                                    ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                    if (constraintLayout4 != null) {
                                        i = R.id.tv_username;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView != null) {
                                            i = R.id.tv_version;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView2 != null) {
                                                return new FragmentUserPageBinding((FrameLayout) view, imageView, imageView2, imageView3, imageView4, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, textView, textView2);
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
