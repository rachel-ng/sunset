package com.mergbw.android.ui.addDevice;

import com.mergbw.core.database.bean.DeviceInfoBean;
import java.util.List;

public interface AddDeviceInterView {
    void updateAddResult(boolean z);

    void updateBindResult(DeviceInfoBean deviceInfoBean, BindStatus bindStatus);

    void updateDeviceList(List<DeviceInfoBean> list);

    void updateScanResult(boolean z);
}
