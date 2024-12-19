package com.mergbw.android.ui.deviceDetail.fragment;

import androidx.lifecycle.Observer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SubColorListFragment$$ExternalSyntheticLambda2 implements Observer {
    public final /* synthetic */ SubColorListFragment f$0;

    public /* synthetic */ SubColorListFragment$$ExternalSyntheticLambda2(SubColorListFragment subColorListFragment) {
        this.f$0 = subColorListFragment;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateSubColor((List) obj);
    }
}
