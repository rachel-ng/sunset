package com.mergbw.android.ui.deviceDetail.fragment;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DIYListFragment$$ExternalSyntheticLambda1 implements Observer {
    public final /* synthetic */ DIYListFragment f$0;

    public /* synthetic */ DIYListFragment$$ExternalSyntheticLambda1(DIYListFragment dIYListFragment) {
        this.f$0 = dIYListFragment;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateSendResult(((Boolean) obj).booleanValue());
    }
}
