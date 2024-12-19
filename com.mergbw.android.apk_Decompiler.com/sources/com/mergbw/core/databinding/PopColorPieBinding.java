package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;
import top.defaults.colorpicker.ColorPickerView;

public final class PopColorPieBinding implements ViewBinding {
    public final ImageView ivClose;
    private final FrameLayout rootView;
    public final ColorPickerView viewColorPie;

    private PopColorPieBinding(FrameLayout frameLayout, ImageView imageView, ColorPickerView colorPickerView) {
        this.rootView = frameLayout;
        this.ivClose = imageView;
        this.viewColorPie = colorPickerView;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static PopColorPieBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static PopColorPieBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_color_pie, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopColorPieBinding bind(View view) {
        int i = R.id.iv_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.view_color_pie;
            ColorPickerView colorPickerView = (ColorPickerView) ViewBindings.findChildViewById(view, i);
            if (colorPickerView != null) {
                return new PopColorPieBinding((FrameLayout) view, imageView, colorPickerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
