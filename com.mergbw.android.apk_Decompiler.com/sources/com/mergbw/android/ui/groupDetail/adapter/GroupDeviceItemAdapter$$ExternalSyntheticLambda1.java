package com.mergbw.android.ui.groupDetail.adapter;

import android.widget.CompoundButton;
import com.mergbw.android.ui.groupDetail.adapter.GroupDeviceItemAdapter;
import com.mergbw.core.database.bean.DeviceInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GroupDeviceItemAdapter$$ExternalSyntheticLambda1 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ GroupDeviceItemAdapter f$0;
    public final /* synthetic */ DeviceInfoBean f$1;
    public final /* synthetic */ GroupDeviceItemAdapter.ItemViewHolder f$2;

    public /* synthetic */ GroupDeviceItemAdapter$$ExternalSyntheticLambda1(GroupDeviceItemAdapter groupDeviceItemAdapter, DeviceInfoBean deviceInfoBean, GroupDeviceItemAdapter.ItemViewHolder itemViewHolder) {
        this.f$0 = groupDeviceItemAdapter;
        this.f$1 = deviceInfoBean;
        this.f$2 = itemViewHolder;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.f$0.m808lambda$onBindViewHolder$1$commergbwandroiduigroupDetailadapterGroupDeviceItemAdapter(this.f$1, this.f$2, compoundButton, z);
    }
}
