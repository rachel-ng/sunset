package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class DialogChooseSiteBinding implements ViewBinding {
    public final RecyclerView listSite;
    private final FrameLayout rootView;
    public final TextView tvTitle;

    private DialogChooseSiteBinding(FrameLayout frameLayout, RecyclerView recyclerView, TextView textView) {
        this.rootView = frameLayout;
        this.listSite = recyclerView;
        this.tvTitle = textView;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogChooseSiteBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static DialogChooseSiteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_choose_site, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogChooseSiteBinding bind(View view) {
        int i = R.id.list_site;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            i = R.id.tv_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                return new DialogChooseSiteBinding((FrameLayout) view, recyclerView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
