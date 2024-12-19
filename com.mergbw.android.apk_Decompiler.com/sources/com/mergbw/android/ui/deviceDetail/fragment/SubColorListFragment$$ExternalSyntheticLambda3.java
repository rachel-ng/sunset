package com.mergbw.android.ui.deviceDetail.fragment;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SubColorListFragment$$ExternalSyntheticLambda3 implements Observer {
    public final /* synthetic */ SubColorListFragment f$0;

    public /* synthetic */ SubColorListFragment$$ExternalSyntheticLambda3(SubColorListFragment subColorListFragment) {
        this.f$0 = subColorListFragment;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateSendResult(((Boolean) obj).booleanValue());
    }
}
