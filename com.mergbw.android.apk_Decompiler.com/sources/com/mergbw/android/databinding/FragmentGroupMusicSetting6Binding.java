package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class FragmentGroupMusicSetting6Binding implements ViewBinding {
    public final RecyclerView listMusicMode;
    private final LinearLayout rootView;
    public final SeekBar sbSensitive;
    public final TextView tvSensitive;
    public final TextView tvSensitivePercent;

    private FragmentGroupMusicSetting6Binding(LinearLayout linearLayout, RecyclerView recyclerView, SeekBar seekBar, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.listMusicMode = recyclerView;
        this.sbSensitive = seekBar;
        this.tvSensitive = textView;
        this.tvSensitivePercent = textView2;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentGroupMusicSetting6Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentGroupMusicSetting6Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_group_music_setting_6, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentGroupMusicSetting6Binding bind(View view) {
        int i = R.id.list_music_mode;
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
                        return new FragmentGroupMusicSetting6Binding((LinearLayout) view, recyclerView, seekBar, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
