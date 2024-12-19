package com.mergbw.core.network.app.bean;

import java.util.List;

public class DeviceOverViewBean {
    private List<DeviceCountBean> deviceModeCount;
    private List<DeviceCountBean> deviceTypeCount;
    private List<DeviceCountBean> factoryIdCount;
    private List<DeviceCountBean> mostUseCount;
    private int todayAddDevice;
    private int todayUseDevice;
    private int totalDevice;

    public int getTotalDevice() {
        return this.totalDevice;
    }

    public void setTotalDevice(int i) {
        this.totalDevice = i;
    }

    public List<DeviceCountBean> getDeviceTypeCount() {
        return this.deviceTypeCount;
    }

    public void setDeviceTypeCount(List<DeviceCountBean> list) {
        this.deviceTypeCount = list;
    }

    public List<DeviceCountBean> getDeviceModeCount() {
        return this.deviceModeCount;
    }

    public void setDeviceModeCount(List<DeviceCountBean> list) {
        this.deviceModeCount = list;
    }

    public List<DeviceCountBean> getFactoryIdCount() {
        return this.factoryIdCount;
    }

    public void setFactoryIdCount(List<DeviceCountBean> list) {
        this.factoryIdCount = list;
    }

    public List<DeviceCountBean> getMostUseCount() {
        return this.mostUseCount;
    }

    public void setMostUseCount(List<DeviceCountBean> list) {
        this.mostUseCount = list;
    }

    public int getTodayAddDevice() {
        return this.todayAddDevice;
    }

    public void setTodayAddDevice(int i) {
        this.todayAddDevice = i;
    }

    public int getTodayUseDevice() {
        return this.todayUseDevice;
    }

    public void setTodayUseDevice(int i) {
        this.todayUseDevice = i;
    }
}
