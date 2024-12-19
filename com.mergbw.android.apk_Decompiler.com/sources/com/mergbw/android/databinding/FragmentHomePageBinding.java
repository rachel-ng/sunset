package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class FragmentHomePageBinding implements ViewBinding {
    public final ImageView ivAdd;
    public final ImageView ivIcon;
    public final FrameLayout layoutChildContent;
    public final RadioButton rbDevice;
    public final RadioButton rbGroup;
    public final RadioGroup rgTitleBar;
    private final LinearLayout rootView;

    private FragmentHomePageBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, FrameLayout frameLayout, RadioButton radioButton, RadioButton radioButton2, RadioGroup radioGroup) {
        this.rootView = linearLayout;
        this.ivAdd = imageView;
        this.ivIcon = imageView2;
        this.layoutChildContent = frameLayout;
        this.rbDevice = radioButton;
        this.rbGroup = radioButton2;
        this.rgTitleBar = radioGroup;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentHomePageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentHomePageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_home_page, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentHomePageBinding bind(View view) {
        int i = R.id.iv_add;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.iv_icon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.layout_child_content;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                if (frameLayout != null) {
                    i = R.id.rb_device;
                    RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(view, i);
                    if (radioButton != null) {
                        i = R.id.rb_group;
                        RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(view, i);
                        if (radioButton2 != null) {
                            i = R.id.rg_title_bar;
                            RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, i);
                            if (radioGroup != null) {
                                return new FragmentHomePageBinding((LinearLayout) view, imageView, imageView2, frameLayout, radioButton, radioButton2, radioGroup);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
