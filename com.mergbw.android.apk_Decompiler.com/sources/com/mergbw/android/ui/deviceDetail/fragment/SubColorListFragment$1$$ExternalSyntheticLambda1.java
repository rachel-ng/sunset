package com.mergbw.android.ui.deviceDetail.fragment;

import com.mergbw.android.ui.deviceDetail.fragment.SubColorListFragment;
import com.mergbw.core.database.bean.SubColorBean;
import com.mergbw.core.ui.views.ConfirmDialog;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SubColorListFragment$1$$ExternalSyntheticLambda1 implements ConfirmDialog.OnChooseListener {
    public final /* synthetic */ SubColorListFragment.AnonymousClass1 f$0;
    public final /* synthetic */ SubColorBean f$1;

    public /* synthetic */ SubColorListFragment$1$$ExternalSyntheticLambda1(SubColorListFragment.AnonymousClass1 r1, SubColorBean subColorBean) {
        this.f$0 = r1;
        this.f$1 = subColorBean;
    }

    public final void OnConfirmResult(boolean z) {
        this.f$0.m761lambda$longClickGroupItem$1$commergbwandroiduideviceDetailfragmentSubColorListFragment$1(this.f$1, z);
    }
}
