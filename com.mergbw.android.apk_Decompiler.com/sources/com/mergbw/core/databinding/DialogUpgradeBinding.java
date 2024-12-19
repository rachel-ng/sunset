package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;

public final class DialogUpgradeBinding implements ViewBinding {
    public final ProgressBar pbUpgrade;
    private final FrameLayout rootView;
    public final TextView tvContent;
    public final TextView tvPbValue;
    public final TextView tvTitle;

    private DialogUpgradeBinding(FrameLayout frameLayout, ProgressBar progressBar, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = frameLayout;
        this.pbUpgrade = progressBar;
        this.tvContent = textView;
        this.tvPbValue = textView2;
        this.tvTitle = textView3;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogUpgradeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static DialogUpgradeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_upgrade, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogUpgradeBinding bind(View view) {
        int i = R.id.pb_upgrade;
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
        if (progressBar != null) {
            i = R.id.tv_content;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.tv_pb_value;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.tv_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView3 != null) {
                        return new DialogUpgradeBinding((FrameLayout) view, progressBar, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
