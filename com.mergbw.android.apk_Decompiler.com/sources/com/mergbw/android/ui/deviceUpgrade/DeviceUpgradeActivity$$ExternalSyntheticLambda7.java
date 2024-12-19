package com.mergbw.android.ui.deviceUpgrade;

import androidx.lifecycle.Observer;
import com.mergbw.core.database.bean.DeviceInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceUpgradeActivity$$ExternalSyntheticLambda7 implements Observer {
    public final /* synthetic */ DeviceUpgradeActivity f$0;

    public /* synthetic */ DeviceUpgradeActivity$$ExternalSyntheticLambda7(DeviceUpgradeActivity deviceUpgradeActivity) {
        this.f$0 = deviceUpgradeActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateDeviceInfo((DeviceInfoBean) obj);
    }
}
