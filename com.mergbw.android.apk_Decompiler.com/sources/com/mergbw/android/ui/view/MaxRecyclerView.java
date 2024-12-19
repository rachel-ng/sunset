package com.mergbw.android.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class MaxRecyclerView extends RecyclerView {
    public MaxRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MaxRecyclerView(Context context) {
        super(context);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
