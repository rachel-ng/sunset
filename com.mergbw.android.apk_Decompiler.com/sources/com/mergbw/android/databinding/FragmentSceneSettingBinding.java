package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class FragmentSceneSettingBinding implements ViewBinding {
    public final FrameLayout layoutContent;
    public final RecyclerView listSceneType;
    private final LinearLayout rootView;

    private FragmentSceneSettingBinding(LinearLayout linearLayout, FrameLayout frameLayout, RecyclerView recyclerView) {
        this.rootView = linearLayout;
        this.layoutContent = frameLayout;
        this.listSceneType = recyclerView;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSceneSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentSceneSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_scene_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentSceneSettingBinding bind(View view) {
        int i = R.id.layout_content;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            i = R.id.list_scene_type;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView != null) {
                return new FragmentSceneSettingBinding((LinearLayout) view, frameLayout, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
