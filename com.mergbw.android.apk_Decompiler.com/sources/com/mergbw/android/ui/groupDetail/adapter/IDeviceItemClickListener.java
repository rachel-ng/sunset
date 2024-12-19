package com.mergbw.android.ui.groupDetail.adapter;

import com.mergbw.core.database.bean.DeviceInfoBean;

public interface IDeviceItemClickListener {
    void checkItem(DeviceInfoBean deviceInfoBean, boolean z);

    void clickConnect(DeviceInfoBean deviceInfoBean);

    void clickItem(DeviceInfoBean deviceInfoBean);

    void clickPower(DeviceInfoBean deviceInfoBean, boolean z);
}
