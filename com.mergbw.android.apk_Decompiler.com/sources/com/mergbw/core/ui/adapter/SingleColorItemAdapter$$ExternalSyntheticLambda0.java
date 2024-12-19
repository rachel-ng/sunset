package com.mergbw.core.ui.adapter;

import android.view.View;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.ui.adapter.SingleColorItemAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SingleColorItemAdapter$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ SingleColorItemAdapter f$0;
    public final /* synthetic */ ColorBean f$1;
    public final /* synthetic */ SingleColorItemAdapter.ItemViewHolder f$2;

    public /* synthetic */ SingleColorItemAdapter$$ExternalSyntheticLambda0(SingleColorItemAdapter singleColorItemAdapter, ColorBean colorBean, SingleColorItemAdapter.ItemViewHolder itemViewHolder) {
        this.f$0 = singleColorItemAdapter;
        this.f$1 = colorBean;
        this.f$2 = itemViewHolder;
    }

    public final void onClick(View view) {
        this.f$0.m932lambda$onBindViewHolder$0$commergbwcoreuiadapterSingleColorItemAdapter(this.f$1, this.f$2, view);
    }
}
