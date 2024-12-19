package com.mergbw.android.ui.deviceDetail;

import androidx.lifecycle.Observer;
import com.mergbw.core.database.bean.DeviceInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceDetailActivity$$ExternalSyntheticLambda7 implements Observer {
    public final /* synthetic */ DeviceDetailActivity f$0;

    public /* synthetic */ DeviceDetailActivity$$ExternalSyntheticLambda7(DeviceDetailActivity deviceDetailActivity) {
        this.f$0 = deviceDetailActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateDetailViewData((DeviceInfoBean) obj);
    }
}
