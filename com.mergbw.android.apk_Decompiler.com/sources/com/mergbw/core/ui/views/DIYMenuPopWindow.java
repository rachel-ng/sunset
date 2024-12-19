package com.mergbw.core.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import com.mergbw.core.R;
import com.mergbw.core.databinding.PopDiyMenuBinding;

public class DIYMenuPopWindow extends PopupWindow {
    public static final int DELETE_CODE = 3;
    public static final int EDIT_CODE = 2;
    public static final int RENAME_CODE = 1;
    private final OnMenuClickListener mListener;

    public interface OnMenuClickListener {
        void OnMenuClick(int i);
    }

    public DIYMenuPopWindow(Context context, OnMenuClickListener onMenuClickListener) {
        this.mListener = onMenuClickListener;
        PopDiyMenuBinding inflate = PopDiyMenuBinding.inflate(LayoutInflater.from(context));
        setContentView(inflate.getRoot());
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.pop_anim);
        inflate.getRoot().setOnClickListener(new DIYMenuPopWindow$$ExternalSyntheticLambda0(this));
        inflate.menuItem4.setOnClickListener(new DIYMenuPopWindow$$ExternalSyntheticLambda1(this));
        inflate.menuItem1.setOnClickListener(new DIYMenuPopWindow$$ExternalSyntheticLambda2(this));
        inflate.menuItem2.setOnClickListener(new DIYMenuPopWindow$$ExternalSyntheticLambda3(this));
        inflate.menuItem3.setOnClickListener(new DIYMenuPopWindow$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-mergbw-core-ui-views-DIYMenuPopWindow  reason: not valid java name */
    public /* synthetic */ void m951lambda$new$0$commergbwcoreuiviewsDIYMenuPopWindow(View view) {
        dismiss();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-mergbw-core-ui-views-DIYMenuPopWindow  reason: not valid java name */
    public /* synthetic */ void m952lambda$new$1$commergbwcoreuiviewsDIYMenuPopWindow(View view) {
        dismiss();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$2$com-mergbw-core-ui-views-DIYMenuPopWindow  reason: not valid java name */
    public /* synthetic */ void m953lambda$new$2$commergbwcoreuiviewsDIYMenuPopWindow(View view) {
        OnMenuClickListener onMenuClickListener = this.mListener;
        if (onMenuClickListener != null) {
            onMenuClickListener.OnMenuClick(1);
        }
        if (isShowing()) {
            dismiss();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$3$com-mergbw-core-ui-views-DIYMenuPopWindow  reason: not valid java name */
    public /* synthetic */ void m954lambda$new$3$commergbwcoreuiviewsDIYMenuPopWindow(View view) {
        OnMenuClickListener onMenuClickListener = this.mListener;
        if (onMenuClickListener != null) {
            onMenuClickListener.OnMenuClick(2);
        }
        if (isShowing()) {
            dismiss();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$4$com-mergbw-core-ui-views-DIYMenuPopWindow  reason: not valid java name */
    public /* synthetic */ void m955lambda$new$4$commergbwcoreuiviewsDIYMenuPopWindow(View view) {
        OnMenuClickListener onMenuClickListener = this.mListener;
        if (onMenuClickListener != null) {
            onMenuClickListener.OnMenuClick(3);
        }
        if (isShowing()) {
            dismiss();
        }
    }
}
