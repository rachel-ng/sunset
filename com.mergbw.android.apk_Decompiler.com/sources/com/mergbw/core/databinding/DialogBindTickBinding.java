package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.mergbw.core.R;

public final class DialogBindTickBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final TextView tvContent;
    public final TextView tvTitle;
    public final LottieAnimationView viewLoading;

    private DialogBindTickBinding(FrameLayout frameLayout, TextView textView, TextView textView2, LottieAnimationView lottieAnimationView) {
        this.rootView = frameLayout;
        this.tvContent = textView;
        this.tvTitle = textView2;
        this.viewLoading = lottieAnimationView;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogBindTickBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static DialogBindTickBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_bind_tick, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogBindTickBinding bind(View view) {
        int i = R.id.tv_content;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.tv_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView2 != null) {
                i = R.id.view_loading;
                LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                if (lottieAnimationView != null) {
                    return new DialogBindTickBinding((FrameLayout) view, textView, textView2, lottieAnimationView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
