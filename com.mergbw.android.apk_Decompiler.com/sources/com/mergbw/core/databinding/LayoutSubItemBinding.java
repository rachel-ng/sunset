package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;
import com.mergbw.core.ui.views.SubItemView;

public final class LayoutSubItemBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final SubItemView viewSub;

    private LayoutSubItemBinding(LinearLayout linearLayout, SubItemView subItemView) {
        this.rootView = linearLayout;
        this.viewSub = subItemView;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSubItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutSubItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_sub_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutSubItemBinding bind(View view) {
        int i = R.id.view_sub;
        SubItemView subItemView = (SubItemView) ViewBindings.findChildViewById(view, i);
        if (subItemView != null) {
            return new LayoutSubItemBinding((LinearLayout) view, subItemView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
