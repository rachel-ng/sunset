package com.mergbw.core.ui.subColorSetting;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SubColorSettingActivity$$ExternalSyntheticLambda2 implements Observer {
    public final /* synthetic */ SubColorSettingActivity f$0;

    public /* synthetic */ SubColorSettingActivity$$ExternalSyntheticLambda2(SubColorSettingActivity subColorSettingActivity) {
        this.f$0 = subColorSettingActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateAddResult(((Boolean) obj).booleanValue());
    }
}
