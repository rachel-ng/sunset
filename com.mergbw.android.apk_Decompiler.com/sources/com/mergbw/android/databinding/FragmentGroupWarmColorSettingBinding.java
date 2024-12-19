package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;
import top.defaults.colorpicker.WarmColorPickerView;

public final class FragmentGroupWarmColorSettingBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final WarmColorPickerView viewWarmColorPie;

    private FragmentGroupWarmColorSettingBinding(FrameLayout frameLayout, WarmColorPickerView warmColorPickerView) {
        this.rootView = frameLayout;
        this.viewWarmColorPie = warmColorPickerView;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentGroupWarmColorSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentGroupWarmColorSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_group_warm_color_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentGroupWarmColorSettingBinding bind(View view) {
        int i = R.id.view_warm_color_pie;
        WarmColorPickerView warmColorPickerView = (WarmColorPickerView) ViewBindings.findChildViewById(view, i);
        if (warmColorPickerView != null) {
            return new FragmentGroupWarmColorSettingBinding((FrameLayout) view, warmColorPickerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
