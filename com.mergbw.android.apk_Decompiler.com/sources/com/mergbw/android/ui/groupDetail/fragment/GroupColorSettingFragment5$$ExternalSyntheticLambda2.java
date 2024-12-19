package com.mergbw.android.ui.groupDetail.fragment;

import androidx.lifecycle.Observer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GroupColorSettingFragment5$$ExternalSyntheticLambda2 implements Observer {
    public final /* synthetic */ GroupColorSettingFragment5 f$0;

    public /* synthetic */ GroupColorSettingFragment5$$ExternalSyntheticLambda2(GroupColorSettingFragment5 groupColorSettingFragment5) {
        this.f$0 = groupColorSettingFragment5;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateCommonColor((List) obj);
    }
}
