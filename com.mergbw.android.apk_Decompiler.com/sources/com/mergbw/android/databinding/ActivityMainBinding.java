package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class ActivityMainBinding implements ViewBinding {
    public final FrameLayout adViewContainer;
    public final FrameLayout layoutContent;
    public final RadioButton rbHomePage;
    public final RadioButton rbUserPage;
    public final RadioGroup rgBottomBar;
    private final ConstraintLayout rootView;

    private ActivityMainBinding(ConstraintLayout constraintLayout, FrameLayout frameLayout, FrameLayout frameLayout2, RadioButton radioButton, RadioButton radioButton2, RadioGroup radioGroup) {
        this.rootView = constraintLayout;
        this.adViewContainer = frameLayout;
        this.layoutContent = frameLayout2;
        this.rbHomePage = radioButton;
        this.rbUserPage = radioButton2;
        this.rgBottomBar = radioGroup;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityMainBinding bind(View view) {
        int i = R.id.ad_view_container;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            i = R.id.layout_content;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout2 != null) {
                i = R.id.rb_home_page;
                RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(view, i);
                if (radioButton != null) {
                    i = R.id.rb_user_page;
                    RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(view, i);
                    if (radioButton2 != null) {
                        i = R.id.rg_bottom_bar;
                        RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, i);
                        if (radioGroup != null) {
                            return new ActivityMainBinding((ConstraintLayout) view, frameLayout, frameLayout2, radioButton, radioButton2, radioGroup);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
