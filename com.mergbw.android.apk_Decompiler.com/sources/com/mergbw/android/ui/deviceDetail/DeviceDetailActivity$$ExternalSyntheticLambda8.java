package com.mergbw.android.ui.deviceDetail;

import androidx.lifecycle.Observer;
import com.mergbw.core.ble.BleStatus;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceDetailActivity$$ExternalSyntheticLambda8 implements Observer {
    public final /* synthetic */ DeviceDetailActivity f$0;

    public /* synthetic */ DeviceDetailActivity$$ExternalSyntheticLambda8(DeviceDetailActivity deviceDetailActivity) {
        this.f$0 = deviceDetailActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.onConnectStatus((BleStatus) obj);
    }
}
