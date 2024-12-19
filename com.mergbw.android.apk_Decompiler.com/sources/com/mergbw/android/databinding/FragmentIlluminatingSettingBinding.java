package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;
import com.mergbw.core.ui.views.CustomScrollView;
import com.mergbw.core.ui.views.LineColorPickerView;

public final class FragmentIlluminatingSettingBinding implements ViewBinding {
    public final RecyclerView listClassicColor;
    public final RecyclerView listScene;
    private final CustomScrollView rootView;
    public final LineColorPickerView viewColorLine;

    private FragmentIlluminatingSettingBinding(CustomScrollView customScrollView, RecyclerView recyclerView, RecyclerView recyclerView2, LineColorPickerView lineColorPickerView) {
        this.rootView = customScrollView;
        this.listClassicColor = recyclerView;
        this.listScene = recyclerView2;
        this.viewColorLine = lineColorPickerView;
    }

    public CustomScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentIlluminatingSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentIlluminatingSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_illuminating_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentIlluminatingSettingBinding bind(View view) {
        int i = R.id.list_classic_color;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            i = R.id.list_scene;
            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView2 != null) {
                i = R.id.view_color_line;
                LineColorPickerView lineColorPickerView = (LineColorPickerView) ViewBindings.findChildViewById(view, i);
                if (lineColorPickerView != null) {
                    return new FragmentIlluminatingSettingBinding((CustomScrollView) view, recyclerView, recyclerView2, lineColorPickerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
