package com.mergbw.core.database.bean;

import java.io.Serializable;

public class SubColorBean implements Serializable {
    private String alias;
    private boolean checked;
    private String colorValue;
    private String deviceMac;
    private int deviceType;
    private int id;
    private int subType;
    private int type;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getColorValue() {
        return this.colorValue;
    }

    public void setColorValue(String str) {
        this.colorValue = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getSubType() {
        return this.subType;
    }

    public void setSubType(int i) {
        this.subType = i;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }
}
