package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import cn.carbswang.android.numberpickerview.library.NumberPickerView;
import com.mergbw.android.R;

public final class PopChooseTimeBinding implements ViewBinding {
    public final NumberPickerView hourPicker;
    public final NumberPickerView minutePicker;
    private final FrameLayout rootView;
    public final TextView tvCancel;
    public final TextView tvDone;
    public final TextView tvTitle;

    private PopChooseTimeBinding(FrameLayout frameLayout, NumberPickerView numberPickerView, NumberPickerView numberPickerView2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = frameLayout;
        this.hourPicker = numberPickerView;
        this.minutePicker = numberPickerView2;
        this.tvCancel = textView;
        this.tvDone = textView2;
        this.tvTitle = textView3;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static PopChooseTimeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static PopChooseTimeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_choose_time, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopChooseTimeBinding bind(View view) {
        int i = R.id.hour_picker;
        NumberPickerView numberPickerView = (NumberPickerView) ViewBindings.findChildViewById(view, i);
        if (numberPickerView != null) {
            i = R.id.minute_picker;
            NumberPickerView numberPickerView2 = (NumberPickerView) ViewBindings.findChildViewById(view, i);
            if (numberPickerView2 != null) {
                i = R.id.tv_cancel;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.tv_done;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.tv_title;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView3 != null) {
                            return new PopChooseTimeBinding((FrameLayout) view, numberPickerView, numberPickerView2, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
