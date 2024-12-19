package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class LayoutGroupNameItemBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvGroupName;

    private LayoutGroupNameItemBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.tvGroupName = textView;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutGroupNameItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutGroupNameItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_group_name_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutGroupNameItemBinding bind(View view) {
        int i = R.id.tv_group_name;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            return new LayoutGroupNameItemBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
