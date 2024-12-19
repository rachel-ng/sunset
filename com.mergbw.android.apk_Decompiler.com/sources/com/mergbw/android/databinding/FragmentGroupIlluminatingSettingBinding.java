package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;
import com.mergbw.core.ui.views.LineColorPickerView;

public final class FragmentGroupIlluminatingSettingBinding implements ViewBinding {
    public final RecyclerView listClassicColor;
    public final RecyclerView listScene;
    private final LinearLayout rootView;
    public final LineColorPickerView viewColorLine;

    private FragmentGroupIlluminatingSettingBinding(LinearLayout linearLayout, RecyclerView recyclerView, RecyclerView recyclerView2, LineColorPickerView lineColorPickerView) {
        this.rootView = linearLayout;
        this.listClassicColor = recyclerView;
        this.listScene = recyclerView2;
        this.viewColorLine = lineColorPickerView;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentGroupIlluminatingSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentGroupIlluminatingSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_group_illuminating_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentGroupIlluminatingSettingBinding bind(View view) {
        int i = R.id.list_classic_color;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            i = R.id.list_scene;
            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView2 != null) {
                i = R.id.view_color_line;
                LineColorPickerView lineColorPickerView = (LineColorPickerView) ViewBindings.findChildViewById(view, i);
                if (lineColorPickerView != null) {
                    return new FragmentGroupIlluminatingSettingBinding((LinearLayout) view, recyclerView, recyclerView2, lineColorPickerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
