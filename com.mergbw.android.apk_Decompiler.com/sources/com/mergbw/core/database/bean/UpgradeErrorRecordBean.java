package com.mergbw.core.database.bean;

import java.io.Serializable;

public class UpgradeErrorRecordBean implements Serializable {
    private String aliasName;
    private String deviceMac;
    private String deviceModel;
    private String deviceName;
    private int deviceType;
    private int errorCode;
    private int factoryID;
    private String recordTime;

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public String getAliasName() {
        return this.aliasName;
    }

    public void setAliasName(String str) {
        this.aliasName = str;
    }

    public int getFactoryID() {
        return this.factoryID;
    }

    public void setFactoryID(int i) {
        this.factoryID = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public String getRecordTime() {
        return this.recordTime;
    }

    public void setRecordTime(String str) {
        this.recordTime = str;
    }
}
