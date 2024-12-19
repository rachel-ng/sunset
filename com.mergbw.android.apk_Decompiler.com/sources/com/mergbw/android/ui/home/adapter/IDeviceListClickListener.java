package com.mergbw.android.ui.home.adapter;

import com.mergbw.core.database.bean.DeviceInfoBean;

public interface IDeviceListClickListener {
    void clickDeviceItem(DeviceInfoBean deviceInfoBean);

    void clickPower(DeviceInfoBean deviceInfoBean, boolean z);
}
