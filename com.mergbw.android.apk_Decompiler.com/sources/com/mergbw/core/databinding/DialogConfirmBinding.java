package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;

public final class DialogConfirmBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final TextView tvCancel;
    public final TextView tvContent;
    public final TextView tvOk;
    public final TextView tvTitle;

    private DialogConfirmBinding(FrameLayout frameLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = frameLayout;
        this.tvCancel = textView;
        this.tvContent = textView2;
        this.tvOk = textView3;
        this.tvTitle = textView4;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogConfirmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static DialogConfirmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_confirm, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogConfirmBinding bind(View view) {
        int i = R.id.tv_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.tv_content;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView2 != null) {
                i = R.id.tv_ok;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView3 != null) {
                    i = R.id.tv_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView4 != null) {
                        return new DialogConfirmBinding((FrameLayout) view, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
