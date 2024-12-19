package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class ActivityAgreementBinding implements ViewBinding {
    public final ImageView ivBack;
    private final LinearLayout rootView;
    public final TextView tvTitle;
    public final WebView webView;

    private ActivityAgreementBinding(LinearLayout linearLayout, ImageView imageView, TextView textView, WebView webView2) {
        this.rootView = linearLayout;
        this.ivBack = imageView;
        this.tvTitle = textView;
        this.webView = webView2;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAgreementBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityAgreementBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_agreement, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityAgreementBinding bind(View view) {
        int i = R.id.iv_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.tv_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.web_view;
                WebView webView2 = (WebView) ViewBindings.findChildViewById(view, i);
                if (webView2 != null) {
                    return new ActivityAgreementBinding((LinearLayout) view, imageView, textView, webView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
