package com.mergbw.android.ui.deviceDetail.fragment;

import androidx.lifecycle.Observer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DIYListFragment$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ DIYListFragment f$0;

    public /* synthetic */ DIYListFragment$$ExternalSyntheticLambda0(DIYListFragment dIYListFragment) {
        this.f$0 = dIYListFragment;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateDIYListData((List) obj);
    }
}
