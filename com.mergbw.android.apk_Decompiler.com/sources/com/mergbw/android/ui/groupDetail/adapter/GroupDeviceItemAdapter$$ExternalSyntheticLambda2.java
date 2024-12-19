package com.mergbw.android.ui.groupDetail.adapter;

import android.widget.CompoundButton;
import com.mergbw.core.database.bean.DeviceInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GroupDeviceItemAdapter$$ExternalSyntheticLambda2 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ GroupDeviceItemAdapter f$0;
    public final /* synthetic */ DeviceInfoBean f$1;

    public /* synthetic */ GroupDeviceItemAdapter$$ExternalSyntheticLambda2(GroupDeviceItemAdapter groupDeviceItemAdapter, DeviceInfoBean deviceInfoBean) {
        this.f$0 = groupDeviceItemAdapter;
        this.f$1 = deviceInfoBean;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.f$0.m809lambda$onBindViewHolder$2$commergbwandroiduigroupDetailadapterGroupDeviceItemAdapter(this.f$1, compoundButton, z);
    }
}
