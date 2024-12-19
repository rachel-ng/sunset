package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.mergbw.core.R;

public final class LayoutProgressDialogBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final LottieAnimationView viewLoading;

    private LayoutProgressDialogBinding(FrameLayout frameLayout, LottieAnimationView lottieAnimationView) {
        this.rootView = frameLayout;
        this.viewLoading = lottieAnimationView;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutProgressDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutProgressDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_progress_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutProgressDialogBinding bind(View view) {
        int i = R.id.view_loading;
        LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
        if (lottieAnimationView != null) {
            return new LayoutProgressDialogBinding((FrameLayout) view, lottieAnimationView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
