package com.mergbw.android.ui.deviceAlarm;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceAlarmActivity$$ExternalSyntheticLambda5 implements Observer {
    public final /* synthetic */ DeviceAlarmActivity f$0;

    public /* synthetic */ DeviceAlarmActivity$$ExternalSyntheticLambda5(DeviceAlarmActivity deviceAlarmActivity) {
        this.f$0 = deviceAlarmActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.setResult(((Boolean) obj).booleanValue());
    }
}
