package com.mergbw.android.ui.home.fragment;

import androidx.lifecycle.Observer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceGroupFragment$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ DeviceGroupFragment f$0;

    public /* synthetic */ DeviceGroupFragment$$ExternalSyntheticLambda0(DeviceGroupFragment deviceGroupFragment) {
        this.f$0 = deviceGroupFragment;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateGroupList((List) obj);
    }
}
