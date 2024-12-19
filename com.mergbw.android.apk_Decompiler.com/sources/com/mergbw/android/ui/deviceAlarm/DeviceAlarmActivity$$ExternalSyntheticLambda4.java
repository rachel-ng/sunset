package com.mergbw.android.ui.deviceAlarm;

import androidx.lifecycle.Observer;
import com.mergbw.core.database.bean.DeviceInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceAlarmActivity$$ExternalSyntheticLambda4 implements Observer {
    public final /* synthetic */ DeviceAlarmActivity f$0;

    public /* synthetic */ DeviceAlarmActivity$$ExternalSyntheticLambda4(DeviceAlarmActivity deviceAlarmActivity) {
        this.f$0 = deviceAlarmActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateDeviceInfo((DeviceInfoBean) obj);
    }
}
