package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;
import com.mergbw.core.ui.views.LineColorPickerView;
import top.defaults.colorpicker.ColorPickerView;

public final class LayoutCommonColorPickerBinding implements ViewBinding {
    public final LinearLayout layoutClassicColor;
    public final LinearLayout layoutCommonColor;
    public final RecyclerView listClassicColor;
    public final RecyclerView listCommonColor;
    private final ConstraintLayout rootView;
    public final LineColorPickerView viewColorLine;
    public final ColorPickerView viewColorPie;

    private LayoutCommonColorPickerBinding(ConstraintLayout constraintLayout, LinearLayout linearLayout, LinearLayout linearLayout2, RecyclerView recyclerView, RecyclerView recyclerView2, LineColorPickerView lineColorPickerView, ColorPickerView colorPickerView) {
        this.rootView = constraintLayout;
        this.layoutClassicColor = linearLayout;
        this.layoutCommonColor = linearLayout2;
        this.listClassicColor = recyclerView;
        this.listCommonColor = recyclerView2;
        this.viewColorLine = lineColorPickerView;
        this.viewColorPie = colorPickerView;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutCommonColorPickerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutCommonColorPickerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_common_color_picker, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutCommonColorPickerBinding bind(View view) {
        int i = R.id.layout_classic_color;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.layout_common_color;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout2 != null) {
                i = R.id.list_classic_color;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = R.id.list_common_color;
                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                    if (recyclerView2 != null) {
                        i = R.id.view_color_line;
                        LineColorPickerView lineColorPickerView = (LineColorPickerView) ViewBindings.findChildViewById(view, i);
                        if (lineColorPickerView != null) {
                            i = R.id.view_color_pie;
                            ColorPickerView colorPickerView = (ColorPickerView) ViewBindings.findChildViewById(view, i);
                            if (colorPickerView != null) {
                                return new LayoutCommonColorPickerBinding((ConstraintLayout) view, linearLayout, linearLayout2, recyclerView, recyclerView2, lineColorPickerView, colorPickerView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
