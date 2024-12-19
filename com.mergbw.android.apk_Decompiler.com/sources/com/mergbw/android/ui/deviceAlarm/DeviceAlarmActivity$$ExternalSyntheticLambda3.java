package com.mergbw.android.ui.deviceAlarm;

import androidx.lifecycle.Observer;
import com.mergbw.core.ble.BleStatus;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceAlarmActivity$$ExternalSyntheticLambda3 implements Observer {
    public final /* synthetic */ DeviceAlarmActivity f$0;

    public /* synthetic */ DeviceAlarmActivity$$ExternalSyntheticLambda3(DeviceAlarmActivity deviceAlarmActivity) {
        this.f$0 = deviceAlarmActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.onConnectStatus((BleStatus) obj);
    }
}
