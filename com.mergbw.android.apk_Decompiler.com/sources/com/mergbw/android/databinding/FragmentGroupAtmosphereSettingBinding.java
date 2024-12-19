package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;
import com.mergbw.core.ui.views.LineColorPickerView;

public final class FragmentGroupAtmosphereSettingBinding implements ViewBinding {
    public final LinearLayout layoutColorPie;
    public final RecyclerView listClassicColor;
    public final RecyclerView listScene;
    private final ConstraintLayout rootView;
    public final TextView tvClassicColor;
    public final LineColorPickerView viewColorLine;

    private FragmentGroupAtmosphereSettingBinding(ConstraintLayout constraintLayout, LinearLayout linearLayout, RecyclerView recyclerView, RecyclerView recyclerView2, TextView textView, LineColorPickerView lineColorPickerView) {
        this.rootView = constraintLayout;
        this.layoutColorPie = linearLayout;
        this.listClassicColor = recyclerView;
        this.listScene = recyclerView2;
        this.tvClassicColor = textView;
        this.viewColorLine = lineColorPickerView;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentGroupAtmosphereSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentGroupAtmosphereSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_group_atmosphere_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentGroupAtmosphereSettingBinding bind(View view) {
        int i = R.id.layout_color_pie;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.list_classic_color;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView != null) {
                i = R.id.list_scene;
                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView2 != null) {
                    i = R.id.tv_classic_color;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.view_color_line;
                        LineColorPickerView lineColorPickerView = (LineColorPickerView) ViewBindings.findChildViewById(view, i);
                        if (lineColorPickerView != null) {
                            return new FragmentGroupAtmosphereSettingBinding((ConstraintLayout) view, linearLayout, recyclerView, recyclerView2, textView, lineColorPickerView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
