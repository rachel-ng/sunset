package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;
import com.youth.banner.Banner;

public final class ActivityFactoryAreaBinding implements ViewBinding {
    public final ImageView ivBack;
    public final ImageView ivFactoryLogo;
    public final ImageView ivSite;
    public final RelativeLayout layoutHead;
    public final LinearLayout layoutSiteList;
    public final RecyclerView listNewProduct;
    private final LinearLayout rootView;
    public final TextView tvFactoryName;
    public final Banner viewBanner;

    private ActivityFactoryAreaBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, RelativeLayout relativeLayout, LinearLayout linearLayout2, RecyclerView recyclerView, TextView textView, Banner banner) {
        this.rootView = linearLayout;
        this.ivBack = imageView;
        this.ivFactoryLogo = imageView2;
        this.ivSite = imageView3;
        this.layoutHead = relativeLayout;
        this.layoutSiteList = linearLayout2;
        this.listNewProduct = recyclerView;
        this.tvFactoryName = textView;
        this.viewBanner = banner;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFactoryAreaBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityFactoryAreaBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_factory_area, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityFactoryAreaBinding bind(View view) {
        int i = R.id.iv_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.iv_factory_logo;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.iv_site;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView3 != null) {
                    i = R.id.layout_head;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                    if (relativeLayout != null) {
                        i = R.id.layout_site_list;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            i = R.id.list_new_product;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                            if (recyclerView != null) {
                                i = R.id.tv_factory_name;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView != null) {
                                    i = R.id.view_banner;
                                    Banner banner = (Banner) ViewBindings.findChildViewById(view, i);
                                    if (banner != null) {
                                        return new ActivityFactoryAreaBinding((LinearLayout) view, imageView, imageView2, imageView3, relativeLayout, linearLayout, recyclerView, textView, banner);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
