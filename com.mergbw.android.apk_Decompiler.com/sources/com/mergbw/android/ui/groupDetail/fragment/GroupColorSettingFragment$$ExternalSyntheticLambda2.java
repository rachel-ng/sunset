package com.mergbw.android.ui.groupDetail.fragment;

import androidx.lifecycle.Observer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GroupColorSettingFragment$$ExternalSyntheticLambda2 implements Observer {
    public final /* synthetic */ GroupColorSettingFragment f$0;

    public /* synthetic */ GroupColorSettingFragment$$ExternalSyntheticLambda2(GroupColorSettingFragment groupColorSettingFragment) {
        this.f$0 = groupColorSettingFragment;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateCommonColor((List) obj);
    }
}
