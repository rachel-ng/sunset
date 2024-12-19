package com.mergbw.android.ui.addDevice;

import com.mergbw.android.ui.addDevice.AddDeviceAdapter;
import com.mergbw.core.database.bean.DeviceInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AddDeviceActivity$$ExternalSyntheticLambda1 implements AddDeviceAdapter.DeviceItemClickCallback {
    public final /* synthetic */ AddDeviceActivity f$0;

    public /* synthetic */ AddDeviceActivity$$ExternalSyntheticLambda1(AddDeviceActivity addDeviceActivity) {
        this.f$0 = addDeviceActivity;
    }

    public final void onClick(DeviceInfoBean deviceInfoBean) {
        this.f$0.addDevice(deviceInfoBean);
    }
}
