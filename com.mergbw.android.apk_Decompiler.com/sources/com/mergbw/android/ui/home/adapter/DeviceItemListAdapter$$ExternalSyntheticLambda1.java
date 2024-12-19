package com.mergbw.android.ui.home.adapter;

import android.widget.CompoundButton;
import com.mergbw.core.database.bean.DeviceInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceItemListAdapter$$ExternalSyntheticLambda1 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ DeviceItemListAdapter f$0;
    public final /* synthetic */ DeviceInfoBean f$1;

    public /* synthetic */ DeviceItemListAdapter$$ExternalSyntheticLambda1(DeviceItemListAdapter deviceItemListAdapter, DeviceInfoBean deviceInfoBean) {
        this.f$0 = deviceItemListAdapter;
        this.f$1 = deviceInfoBean;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.f$0.m857lambda$onBindViewHolder$1$commergbwandroiduihomeadapterDeviceItemListAdapter(this.f$1, compoundButton, z);
    }
}
