package com.mergbw.android.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import cn.carbswang.android.numberpickerview.library.NumberPickerView;
import com.mergbw.android.databinding.PopChooseTimeBinding;
import com.mergbw.core.R;

public class ChooseTimePopWindow extends PopupWindow {
    private int mChooseHour;
    private int mChooseMinute;
    private final OnTimeChooseListener mListener;

    public interface OnTimeChooseListener {
        void OnTime(int i, int i2);
    }

    public ChooseTimePopWindow(Context context, int i, int i2, OnTimeChooseListener onTimeChooseListener) {
        this.mListener = onTimeChooseListener;
        if (i > 23) {
            this.mChooseHour = 23;
        } else {
            this.mChooseHour = Math.max(i, 0);
        }
        if (i2 > 59) {
            this.mChooseMinute = 59;
        } else {
            this.mChooseMinute = Math.max(i2, 0);
        }
        PopChooseTimeBinding inflate = PopChooseTimeBinding.inflate(LayoutInflater.from(context));
        setContentView(inflate.getRoot());
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.pop_anim);
        inflate.tvCancel.setOnClickListener(new ChooseTimePopWindow$$ExternalSyntheticLambda0(this));
        inflate.tvDone.setOnClickListener(new ChooseTimePopWindow$$ExternalSyntheticLambda1(this));
        String[] strArr = new String[24];
        for (int i3 = 0; i3 < 24; i3++) {
            if (i3 < 10) {
                strArr[i3] = SessionDescription.SUPPORTED_SDP_VERSION + i3;
            } else {
                strArr[i3] = String.valueOf(i3);
            }
        }
        inflate.hourPicker.setDisplayedValuesAndPickedIndex(strArr, this.mChooseHour, false);
        inflate.hourPicker.setOnValueChangedListener(new ChooseTimePopWindow$$ExternalSyntheticLambda2(this));
        String[] strArr2 = new String[60];
        for (int i4 = 0; i4 < 60; i4++) {
            if (i4 < 10) {
                strArr2[i4] = SessionDescription.SUPPORTED_SDP_VERSION + i4;
            } else {
                strArr2[i4] = String.valueOf(i4);
            }
        }
        inflate.minutePicker.setDisplayedValuesAndPickedIndex(strArr2, this.mChooseMinute, false);
        inflate.minutePicker.setOnValueChangedListener(new ChooseTimePopWindow$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-mergbw-android-ui-view-ChooseTimePopWindow  reason: not valid java name */
    public /* synthetic */ void m873lambda$new$0$commergbwandroiduiviewChooseTimePopWindow(View view) {
        dismiss();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-mergbw-android-ui-view-ChooseTimePopWindow  reason: not valid java name */
    public /* synthetic */ void m874lambda$new$1$commergbwandroiduiviewChooseTimePopWindow(View view) {
        OnTimeChooseListener onTimeChooseListener = this.mListener;
        if (onTimeChooseListener != null) {
            onTimeChooseListener.OnTime(this.mChooseHour, this.mChooseMinute);
        }
        dismiss();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$2$com-mergbw-android-ui-view-ChooseTimePopWindow  reason: not valid java name */
    public /* synthetic */ void m875lambda$new$2$commergbwandroiduiviewChooseTimePopWindow(NumberPickerView numberPickerView, int i, int i2) {
        this.mChooseHour = i2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$3$com-mergbw-android-ui-view-ChooseTimePopWindow  reason: not valid java name */
    public /* synthetic */ void m876lambda$new$3$commergbwandroiduiviewChooseTimePopWindow(NumberPickerView numberPickerView, int i, int i2) {
        this.mChooseMinute = i2;
    }
}
