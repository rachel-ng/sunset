package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;

public final class PopDiyEffectBinding implements ViewBinding {
    public final ListView listEffect;
    private final FrameLayout rootView;
    public final TextView tvCancel;
    public final TextView tvDone;
    public final TextView tvTitle;

    private PopDiyEffectBinding(FrameLayout frameLayout, ListView listView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = frameLayout;
        this.listEffect = listView;
        this.tvCancel = textView;
        this.tvDone = textView2;
        this.tvTitle = textView3;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static PopDiyEffectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static PopDiyEffectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_diy_effect, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopDiyEffectBinding bind(View view) {
        int i = R.id.list_effect;
        ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
        if (listView != null) {
            i = R.id.tv_cancel;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.tv_done;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.tv_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView3 != null) {
                        return new PopDiyEffectBinding((FrameLayout) view, listView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
