package com.mergbw.android.ui.groupSetting;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GroupSettingActivity$$ExternalSyntheticLambda11 implements Observer {
    public final /* synthetic */ GroupSettingActivity f$0;

    public /* synthetic */ GroupSettingActivity$$ExternalSyntheticLambda11(GroupSettingActivity groupSettingActivity) {
        this.f$0 = groupSettingActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.deleteGroupResult(((Boolean) obj).booleanValue());
    }
}
