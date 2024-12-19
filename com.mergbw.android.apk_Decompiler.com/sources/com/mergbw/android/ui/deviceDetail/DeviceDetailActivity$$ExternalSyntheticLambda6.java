package com.mergbw.android.ui.deviceDetail;

import androidx.lifecycle.Observer;
import com.mergbw.android.ui.addDevice.BindStatus;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceDetailActivity$$ExternalSyntheticLambda6 implements Observer {
    public final /* synthetic */ DeviceDetailActivity f$0;

    public /* synthetic */ DeviceDetailActivity$$ExternalSyntheticLambda6(DeviceDetailActivity deviceDetailActivity) {
        this.f$0 = deviceDetailActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateBindStatus((BindStatus) obj);
    }
}
