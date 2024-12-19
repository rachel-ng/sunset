package com.mergbw.android.ui.home.fragment;

import androidx.lifecycle.Observer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceListFragment$$ExternalSyntheticLambda1 implements Observer {
    public final /* synthetic */ DeviceListFragment f$0;

    public /* synthetic */ DeviceListFragment$$ExternalSyntheticLambda1(DeviceListFragment deviceListFragment) {
        this.f$0 = deviceListFragment;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateDeviceList((List) obj);
    }
}
