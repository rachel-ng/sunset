package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;
import com.mergbw.core.ui.views.CustomScrollView;
import com.mergbw.core.ui.views.LineColorPickerView;

public final class FragmentAtmosphereSettingBinding implements ViewBinding {
    public final LinearLayout layoutColorPie;
    public final RecyclerView listClassicColor;
    public final RecyclerView listScene;
    private final CustomScrollView rootView;
    public final TextView tvClassicColor;
    public final LineColorPickerView viewColorLine;

    private FragmentAtmosphereSettingBinding(CustomScrollView customScrollView, LinearLayout linearLayout, RecyclerView recyclerView, RecyclerView recyclerView2, TextView textView, LineColorPickerView lineColorPickerView) {
        this.rootView = customScrollView;
        this.layoutColorPie = linearLayout;
        this.listClassicColor = recyclerView;
        this.listScene = recyclerView2;
        this.tvClassicColor = textView;
        this.viewColorLine = lineColorPickerView;
    }

    public CustomScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentAtmosphereSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentAtmosphereSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_atmosphere_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentAtmosphereSettingBinding bind(View view) {
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
                            return new FragmentAtmosphereSettingBinding((CustomScrollView) view, linearLayout, recyclerView, recyclerView2, textView, lineColorPickerView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
