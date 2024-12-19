package com.mergbw.android.ui.deviceInfo;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceInfoActivity$$ExternalSyntheticLambda9 implements Observer {
    public final /* synthetic */ DeviceInfoActivity f$0;

    public /* synthetic */ DeviceInfoActivity$$ExternalSyntheticLambda9(DeviceInfoActivity deviceInfoActivity) {
        this.f$0 = deviceInfoActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.onDeleteResult(((Boolean) obj).booleanValue());
    }
}
