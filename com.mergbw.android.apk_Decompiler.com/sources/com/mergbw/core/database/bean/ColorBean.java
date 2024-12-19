package com.mergbw.core.database.bean;

import android.graphics.Color;

public class ColorBean {
    private long addTime;
    private String alias;
    private int colorValue;
    private String deviceMac;

    public ColorBean() {
    }

    public ColorBean(String str) {
        setColorValue(Color.parseColor(str));
    }

    public int getColorValue() {
        return this.colorValue;
    }

    public void setColorValue(int i) {
        this.colorValue = i;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public long getAddTime() {
        return this.addTime;
    }

    public void setAddTime(long j) {
        this.addTime = j;
    }
}
