package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class LayoutSceneItem2Binding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvItemName;

    private LayoutSceneItem2Binding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.tvItemName = textView;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSceneItem2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutSceneItem2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_scene_item_2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutSceneItem2Binding bind(View view) {
        int i = R.id.tv_item_name;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            return new LayoutSceneItem2Binding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
