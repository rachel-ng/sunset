package com.mergbw.android.ui.home.adapter;

import android.widget.CompoundButton;
import com.mergbw.core.database.bean.DeviceInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceItemGridAdapter$$ExternalSyntheticLambda1 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ DeviceItemGridAdapter f$0;
    public final /* synthetic */ DeviceInfoBean f$1;

    public /* synthetic */ DeviceItemGridAdapter$$ExternalSyntheticLambda1(DeviceItemGridAdapter deviceItemGridAdapter, DeviceInfoBean deviceInfoBean) {
        this.f$0 = deviceItemGridAdapter;
        this.f$1 = deviceInfoBean;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.f$0.m855lambda$onBindViewHolder$1$commergbwandroiduihomeadapterDeviceItemGridAdapter(this.f$1, compoundButton, z);
    }
}
