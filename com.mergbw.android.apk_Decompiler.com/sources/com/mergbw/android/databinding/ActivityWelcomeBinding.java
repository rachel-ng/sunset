package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.mergbw.android.R;

public final class ActivityWelcomeBinding implements ViewBinding {
    private final RelativeLayout rootView;

    private ActivityWelcomeBinding(RelativeLayout relativeLayout) {
        this.rootView = relativeLayout;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWelcomeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityWelcomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_welcome, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityWelcomeBinding bind(View view) {
        if (view != null) {
            return new ActivityWelcomeBinding((RelativeLayout) view);
        }
        throw new NullPointerException("rootView");
    }
}
