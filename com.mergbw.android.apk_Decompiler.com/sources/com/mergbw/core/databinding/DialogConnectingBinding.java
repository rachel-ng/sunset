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

public final class DialogConnectingBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final TextView tvTitle;
    public final LottieAnimationView viewLoading;

    private DialogConnectingBinding(FrameLayout frameLayout, TextView textView, LottieAnimationView lottieAnimationView) {
        this.rootView = frameLayout;
        this.tvTitle = textView;
        this.viewLoading = lottieAnimationView;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogConnectingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static DialogConnectingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_connecting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogConnectingBinding bind(View view) {
        int i = R.id.tv_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.view_loading;
            LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
            if (lottieAnimationView != null) {
                return new DialogConnectingBinding((FrameLayout) view, textView, lottieAnimationView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
