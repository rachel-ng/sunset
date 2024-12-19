package com.mergbw.core.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import com.mergbw.core.R;
import com.mergbw.core.databinding.PopColorPieBinding;

public class ColorPiePopWindow extends PopupWindow {
    private final OnColorPickListener mListener;

    public interface OnColorPickListener {
        void onColorValue(int i);
    }

    public ColorPiePopWindow(Context context, OnColorPickListener onColorPickListener) {
        this.mListener = onColorPickListener;
        PopColorPieBinding inflate = PopColorPieBinding.inflate(LayoutInflater.from(context));
        setContentView(inflate.getRoot());
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.pop_anim);
        inflate.ivClose.setOnClickListener(new ColorPiePopWindow$$ExternalSyntheticLambda0(this));
        inflate.viewColorPie.subscribe(new ColorPiePopWindow$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-mergbw-core-ui-views-ColorPiePopWindow  reason: not valid java name */
    public /* synthetic */ void m941lambda$new$0$commergbwcoreuiviewsColorPiePopWindow(View view) {
        dismiss();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-mergbw-core-ui-views-ColorPiePopWindow  reason: not valid java name */
    public /* synthetic */ void m942lambda$new$1$commergbwcoreuiviewsColorPiePopWindow(int i, boolean z, boolean z2) {
        OnColorPickListener onColorPickListener;
        if (z && (onColorPickListener = this.mListener) != null) {
            onColorPickListener.onColorValue(i);
        }
    }
}
