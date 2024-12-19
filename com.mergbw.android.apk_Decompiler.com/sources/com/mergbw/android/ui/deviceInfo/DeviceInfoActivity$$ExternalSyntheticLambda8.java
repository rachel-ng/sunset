package com.mergbw.android.ui.deviceInfo;

import androidx.lifecycle.Observer;
import com.mergbw.core.database.bean.DeviceInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceInfoActivity$$ExternalSyntheticLambda8 implements Observer {
    public final /* synthetic */ DeviceInfoActivity f$0;

    public /* synthetic */ DeviceInfoActivity$$ExternalSyntheticLambda8(DeviceInfoActivity deviceInfoActivity) {
        this.f$0 = deviceInfoActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateDeviceInfo((DeviceInfoBean) obj);
    }
}
