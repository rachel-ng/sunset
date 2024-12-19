package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class ActivityWebviewBinding implements ViewBinding {
    public final ImageView ivBack;
    public final ConstraintLayout layoutHead;
    private final LinearLayout rootView;
    public final TextView tvTitle;
    public final ProgressBar webProgress;
    public final WebView webView;

    private ActivityWebviewBinding(LinearLayout linearLayout, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, ProgressBar progressBar, WebView webView2) {
        this.rootView = linearLayout;
        this.ivBack = imageView;
        this.layoutHead = constraintLayout;
        this.tvTitle = textView;
        this.webProgress = progressBar;
        this.webView = webView2;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWebviewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityWebviewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_webview, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityWebviewBinding bind(View view) {
        int i = R.id.iv_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.layout_head;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
            if (constraintLayout != null) {
                i = R.id.tv_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.web_progress;
                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                    if (progressBar != null) {
                        i = R.id.web_view;
                        WebView webView2 = (WebView) ViewBindings.findChildViewById(view, i);
                        if (webView2 != null) {
                            return new ActivityWebviewBinding((LinearLayout) view, imageView, constraintLayout, textView, progressBar, webView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
