package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class FragmentSubColorListBinding implements ViewBinding {
    public final ImageView ivAdd;
    public final ConstraintLayout layoutEmptyAdd;
    public final RecyclerView listSubColor;
    private final FrameLayout rootView;

    private FragmentSubColorListBinding(FrameLayout frameLayout, ImageView imageView, ConstraintLayout constraintLayout, RecyclerView recyclerView) {
        this.rootView = frameLayout;
        this.ivAdd = imageView;
        this.layoutEmptyAdd = constraintLayout;
        this.listSubColor = recyclerView;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSubColorListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentSubColorListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sub_color_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentSubColorListBinding bind(View view) {
        int i = R.id.iv_add;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.layout_empty_add;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
            if (constraintLayout != null) {
                i = R.id.list_sub_color;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    return new FragmentSubColorListBinding((FrameLayout) view, imageView, constraintLayout, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
