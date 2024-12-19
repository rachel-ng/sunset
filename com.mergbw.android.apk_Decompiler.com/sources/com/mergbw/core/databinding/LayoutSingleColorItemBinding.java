package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;
import com.mergbw.core.ui.views.ColorCheckableView;

public final class LayoutSingleColorItemBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final ColorCheckableView viewColorItem;

    private LayoutSingleColorItemBinding(LinearLayout linearLayout, ColorCheckableView colorCheckableView) {
        this.rootView = linearLayout;
        this.viewColorItem = colorCheckableView;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSingleColorItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutSingleColorItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_single_color_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutSingleColorItemBinding bind(View view) {
        int i = R.id.view_color_item;
        ColorCheckableView colorCheckableView = (ColorCheckableView) ViewBindings.findChildViewById(view, i);
        if (colorCheckableView != null) {
            return new LayoutSingleColorItemBinding((LinearLayout) view, colorCheckableView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
