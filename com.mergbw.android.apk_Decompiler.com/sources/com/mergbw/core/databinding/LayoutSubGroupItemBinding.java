package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;
import com.mergbw.core.ui.views.GradientView;

public final class LayoutSubGroupItemBinding implements ViewBinding {
    public final CheckBox cbSelected;
    public final TextView groupName;
    private final ConstraintLayout rootView;
    public final GradientView viewSubGroup;

    private LayoutSubGroupItemBinding(ConstraintLayout constraintLayout, CheckBox checkBox, TextView textView, GradientView gradientView) {
        this.rootView = constraintLayout;
        this.cbSelected = checkBox;
        this.groupName = textView;
        this.viewSubGroup = gradientView;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSubGroupItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutSubGroupItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_sub_group_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutSubGroupItemBinding bind(View view) {
        int i = R.id.cb_selected;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, i);
        if (checkBox != null) {
            i = R.id.group_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.view_sub_group;
                GradientView gradientView = (GradientView) ViewBindings.findChildViewById(view, i);
                if (gradientView != null) {
                    return new LayoutSubGroupItemBinding((ConstraintLayout) view, checkBox, textView, gradientView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
