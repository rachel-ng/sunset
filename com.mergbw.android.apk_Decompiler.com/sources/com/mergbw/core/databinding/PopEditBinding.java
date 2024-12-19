package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;

public final class PopEditBinding implements ViewBinding {
    public final EditText etEditText;
    private final FrameLayout rootView;
    public final TextView tvDone;
    public final TextView tvSkip;
    public final TextView tvTitle;

    private PopEditBinding(FrameLayout frameLayout, EditText editText, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = frameLayout;
        this.etEditText = editText;
        this.tvDone = textView;
        this.tvSkip = textView2;
        this.tvTitle = textView3;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static PopEditBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static PopEditBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_edit, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopEditBinding bind(View view) {
        int i = R.id.et_edit_text;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
        if (editText != null) {
            i = R.id.tv_done;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.tv_skip;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.tv_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView3 != null) {
                        return new PopEditBinding((FrameLayout) view, editText, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
