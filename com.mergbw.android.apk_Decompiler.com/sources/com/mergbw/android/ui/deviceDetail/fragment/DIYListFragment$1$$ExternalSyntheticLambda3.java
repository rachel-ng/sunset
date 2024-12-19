package com.mergbw.android.ui.deviceDetail.fragment;

import com.mergbw.android.ui.deviceDetail.fragment.DIYListFragment;
import com.mergbw.core.database.bean.DIYInfoBean;
import com.mergbw.core.ui.views.ConfirmDialog;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DIYListFragment$1$$ExternalSyntheticLambda3 implements ConfirmDialog.OnChooseListener {
    public final /* synthetic */ DIYListFragment.AnonymousClass1 f$0;
    public final /* synthetic */ DIYInfoBean f$1;

    public /* synthetic */ DIYListFragment$1$$ExternalSyntheticLambda3(DIYListFragment.AnonymousClass1 r1, DIYInfoBean dIYInfoBean) {
        this.f$0 = r1;
        this.f$1 = dIYInfoBean;
    }

    public final void OnConfirmResult(boolean z) {
        this.f$0.m745lambda$longClickDIYItem$1$commergbwandroiduideviceDetailfragmentDIYListFragment$1(this.f$1, z);
    }
}
