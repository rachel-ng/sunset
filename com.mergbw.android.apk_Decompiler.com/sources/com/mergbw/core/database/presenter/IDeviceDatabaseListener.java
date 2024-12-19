package com.mergbw.core.database.presenter;

import com.mergbw.core.database.bean.DeviceInfoBean;
import java.util.List;

public interface IDeviceDatabaseListener extends IBaseDatabaseListener {
    void onGetDeviceInfo(DeviceInfoBean deviceInfoBean);

    void onGetDeviceList(List<DeviceInfoBean> list);
}
