package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class FragmentMusicSettingBinding implements ViewBinding {
    public final ImageView ivFactoryArea;
    public final ConstraintLayout layoutSensitive;
    public final RecyclerView listMusicMode;
    private final ConstraintLayout rootView;
    public final SeekBar sbSensitive;
    public final TextView tvSensitive;
    public final TextView tvSensitivePercent;

    private FragmentMusicSettingBinding(ConstraintLayout constraintLayout, ImageView imageView, ConstraintLayout constraintLayout2, RecyclerView recyclerView, SeekBar seekBar, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.ivFactoryArea = imageView;
        this.layoutSensitive = constraintLayout2;
        this.listMusicMode = recyclerView;
        this.sbSensitive = seekBar;
        this.tvSensitive = textView;
        this.tvSensitivePercent = textView2;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentMusicSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentMusicSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_music_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentMusicSettingBinding bind(View view) {
        int i = R.id.iv_factory_area;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.layout_sensitive;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
            if (constraintLayout != null) {
                i = R.id.list_music_mode;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = R.id.sb_sensitive;
                    SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, i);
                    if (seekBar != null) {
                        i = R.id.tv_sensitive;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.tv_sensitive_percent;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                return new FragmentMusicSettingBinding((ConstraintLayout) view, imageView, constraintLayout, recyclerView, seekBar, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
