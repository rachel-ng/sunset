package com.mergbw.core.database.bean;

import com.mergbw.core.bean.AlarmDataBean;
import com.mergbw.core.utils.StringUtils;
import java.io.Serializable;

public class DeviceInfoBean implements Serializable {
    private String aliasName;
    private int brightness;
    private AlarmDataBean closeAlarm;
    private String deviceMac;
    private String deviceModel;
    private String deviceName;
    private int deviceType;
    private int factoryID;
    private String firmwareVersion;
    private boolean isBound;
    private boolean isConnect;
    private boolean isConnecting;
    private boolean isOpen;
    private boolean isSelected;
    private boolean isWhiteOpen;
    private int ledNum;
    private int mtu;
    private AlarmDataBean openAlarm;
    private int sensitive;
    private int whiteBrightness;

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
        if (StringUtils.isEmpty(this.aliasName)) {
            return this.deviceName;
        }
        return this.aliasName;
    }

    public void setAliasName(String str) {
        this.aliasName = str;
    }

    public boolean isConnect() {
        return this.isConnect;
    }

    public void setConnect(boolean z) {
        this.isConnect = z;
    }

    public boolean isConnecting() {
        return this.isConnecting;
    }

    public void setConnecting(boolean z) {
        this.isConnecting = z;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public int getMtu() {
        return this.mtu;
    }

    public void setMtu(int i) {
        this.mtu = i;
    }

    public int getBrightness() {
        return this.brightness;
    }

    public void setBrightness(int i) {
        this.brightness = i;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
    }

    public AlarmDataBean getOpenAlarm() {
        return this.openAlarm;
    }

    public void setOpenAlarm(AlarmDataBean alarmDataBean) {
        this.openAlarm = alarmDataBean;
    }

    public AlarmDataBean getCloseAlarm() {
        return this.closeAlarm;
    }

    public void setCloseAlarm(AlarmDataBean alarmDataBean) {
        this.closeAlarm = alarmDataBean;
    }

    public int getLedNum() {
        return this.ledNum;
    }

    public void setLedNum(int i) {
        this.ledNum = i;
    }

    public int getSensitive() {
        return this.sensitive;
    }

    public void setSensitive(int i) {
        this.sensitive = i;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public boolean isBound() {
        return this.isBound;
    }

    public void setBound(boolean z) {
        this.isBound = z;
    }

    public int getFactoryID() {
        return this.factoryID;
    }

    public void setFactoryID(int i) {
        this.factoryID = i;
    }

    public boolean isWhiteOpen() {
        return this.isWhiteOpen;
    }

    public void setWhiteOpen(boolean z) {
        this.isWhiteOpen = z;
    }

    public int getWhiteBrightness() {
        return this.whiteBrightness;
    }

    public void setWhiteBrightness(int i) {
        this.whiteBrightness = i;
    }
}
