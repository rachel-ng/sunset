package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class FragmentDiySettingBinding implements ViewBinding {
    public final FrameLayout layoutContent;
    public final RadioButton rbDiy;
    public final RadioButton rbSub;
    public final RadioGroup rgDiyType;
    private final LinearLayout rootView;

    private FragmentDiySettingBinding(LinearLayout linearLayout, FrameLayout frameLayout, RadioButton radioButton, RadioButton radioButton2, RadioGroup radioGroup) {
        this.rootView = linearLayout;
        this.layoutContent = frameLayout;
        this.rbDiy = radioButton;
        this.rbSub = radioButton2;
        this.rgDiyType = radioGroup;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDiySettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentDiySettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_diy_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentDiySettingBinding bind(View view) {
        int i = R.id.layout_content;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            i = R.id.rb_diy;
            RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(view, i);
            if (radioButton != null) {
                i = R.id.rb_sub;
                RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(view, i);
                if (radioButton2 != null) {
                    i = R.id.rg_diy_type;
                    RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, i);
                    if (radioGroup != null) {
                        return new FragmentDiySettingBinding((LinearLayout) view, frameLayout, radioButton, radioButton2, radioGroup);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
