package com.mergbw.core.ui.DIYSetting;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DIYSettingActivity$$ExternalSyntheticLambda9 implements Observer {
    public final /* synthetic */ DIYSettingActivity f$0;

    public /* synthetic */ DIYSettingActivity$$ExternalSyntheticLambda9(DIYSettingActivity dIYSettingActivity) {
        this.f$0 = dIYSettingActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateSaveResult(((Boolean) obj).booleanValue());
    }
}
