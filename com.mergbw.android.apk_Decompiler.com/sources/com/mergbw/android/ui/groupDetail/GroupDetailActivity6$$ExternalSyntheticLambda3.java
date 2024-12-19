package com.mergbw.android.ui.groupDetail;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GroupDetailActivity6$$ExternalSyntheticLambda3 implements Observer {
    public final /* synthetic */ GroupDetailActivity6 f$0;

    public /* synthetic */ GroupDetailActivity6$$ExternalSyntheticLambda3(GroupDetailActivity6 groupDetailActivity6) {
        this.f$0 = groupDetailActivity6;
    }

    public final void onChanged(Object obj) {
        this.f$0.showLoading(((Boolean) obj).booleanValue());
    }
}
