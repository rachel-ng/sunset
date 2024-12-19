package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class LayoutSceneGroupBinding implements ViewBinding {
    public final TextView groupName;
    private final LinearLayout rootView;

    private LayoutSceneGroupBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.groupName = textView;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSceneGroupBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutSceneGroupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_scene_group, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutSceneGroupBinding bind(View view) {
        int i = R.id.group_name;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            return new LayoutSceneGroupBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
