package com.mergbw.android.ui.groupSetting.bean;

import com.mergbw.core.database.bean.DeviceInfoBean;
import java.util.List;

public class DeviceGroupBean {
    public static int ADDED_DEVICE = 1;
    public static int CAN_ADD_DEVICE = 2;
    private List<DeviceInfoBean> deviceList;
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public List<DeviceInfoBean> getDeviceList() {
        return this.deviceList;
    }

    public void setDeviceList(List<DeviceInfoBean> list) {
        this.deviceList = list;
    }
}
