package com.mergbw.core.database.bean;

import java.io.Serializable;
import java.util.List;

public class DIYInfoBean implements Serializable {
    private String colorValue;
    private String deviceMac;
    private int deviceType;
    private int diyType;
    private int id;
    private String name;
    private int style;
    private List<SubColorBean> subColorList;
    private int time;
    private int type;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public List<SubColorBean> getSubColorList() {
        return this.subColorList;
    }

    public void setSubColorList(List<SubColorBean> list) {
        this.subColorList = list;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getStyle() {
        return this.style;
    }

    public void setStyle(int i) {
        this.style = i;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int i) {
        this.time = i;
    }

    public String getColorValue() {
        return this.colorValue;
    }

    public void setColorValue(String str) {
        this.colorValue = str;
    }

    public int getDiyType() {
        return this.diyType;
    }

    public void setDiyType(int i) {
        this.diyType = i;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }
}
