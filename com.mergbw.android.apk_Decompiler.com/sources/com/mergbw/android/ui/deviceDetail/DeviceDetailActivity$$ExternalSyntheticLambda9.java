package com.mergbw.android.ui.deviceDetail;

import androidx.lifecycle.Observer;
import com.mergbw.android.ui.deviceDetail.bean.BrightnessInfo;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceDetailActivity$$ExternalSyntheticLambda9 implements Observer {
    public final /* synthetic */ DeviceDetailActivity f$0;

    public /* synthetic */ DeviceDetailActivity$$ExternalSyntheticLambda9(DeviceDetailActivity deviceDetailActivity) {
        this.f$0 = deviceDetailActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateBrightnessInfo((BrightnessInfo) obj);
    }
}
