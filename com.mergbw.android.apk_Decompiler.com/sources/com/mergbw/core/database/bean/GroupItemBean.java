package com.mergbw.core.database.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupItemBean implements Serializable {
    private List<DeviceInfoBean> deviceList = new ArrayList();
    private String[] deviceMacList;
    private int deviceType;
    private String devices;
    private int groupId;
    private String groupName;

    public int getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int i) {
        this.groupId = i;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public String getDevices() {
        return this.devices;
    }

    public void setDevices(String str) {
        this.devices = str;
    }

    public List<DeviceInfoBean> getDeviceList() {
        return this.deviceList;
    }

    public void setDeviceList(List<DeviceInfoBean> list) {
        this.deviceList = list;
    }

    public String[] getDeviceMacList() {
        return this.deviceMacList;
    }

    public void setDeviceMacList(String[] strArr) {
        this.deviceMacList = strArr;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }
}
