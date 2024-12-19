package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.mergbw.android.R;

public final class LayoutWhiteNoiseItemBinding implements ViewBinding {
    public final ImageView ivItemIcon;
    public final ImageView ivPlayState;
    public final FrameLayout layoutIcon;
    private final ConstraintLayout rootView;
    public final TextView tvItemName;
    public final LottieAnimationView viewPlaying;

    private LayoutWhiteNoiseItemBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, FrameLayout frameLayout, TextView textView, LottieAnimationView lottieAnimationView) {
        this.rootView = constraintLayout;
        this.ivItemIcon = imageView;
        this.ivPlayState = imageView2;
        this.layoutIcon = frameLayout;
        this.tvItemName = textView;
        this.viewPlaying = lottieAnimationView;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutWhiteNoiseItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutWhiteNoiseItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_white_noise_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutWhiteNoiseItemBinding bind(View view) {
        int i = R.id.iv_item_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.iv_play_state;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.layout_icon;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                if (frameLayout != null) {
                    i = R.id.tv_item_name;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.view_playing;
                        LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                        if (lottieAnimationView != null) {
                            return new LayoutWhiteNoiseItemBinding((ConstraintLayout) view, imageView, imageView2, frameLayout, textView, lottieAnimationView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
