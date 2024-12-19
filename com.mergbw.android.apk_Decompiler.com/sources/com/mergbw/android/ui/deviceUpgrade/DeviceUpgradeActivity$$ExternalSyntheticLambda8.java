package com.mergbw.android.ui.deviceUpgrade;

import androidx.lifecycle.Observer;
import com.mergbw.core.ble.BleStatus;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceUpgradeActivity$$ExternalSyntheticLambda8 implements Observer {
    public final /* synthetic */ DeviceUpgradeActivity f$0;

    public /* synthetic */ DeviceUpgradeActivity$$ExternalSyntheticLambda8(DeviceUpgradeActivity deviceUpgradeActivity) {
        this.f$0 = deviceUpgradeActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.onConnectStatus((BleStatus) obj);
    }
}
