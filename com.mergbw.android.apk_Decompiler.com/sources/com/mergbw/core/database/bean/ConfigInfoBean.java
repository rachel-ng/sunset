package com.mergbw.core.database.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConfigInfoBean implements Serializable {
    private String DIYMode;
    private int RGBModel;
    private String SubMode;
    private String bleName;
    private List<DIYInfoBean> configDIYColor = new ArrayList();
    private List<SubColorBean> configSubColor = new ArrayList();
    private String deviceModel;
    private int deviceType;
    private String extra;
    private int factoryId;
    private int id;
    private String keyMode;
    private int ledNum;
    private int maxMixLight;
    private int maxOtherLight;
    private String name;
    private int nameLength;
    private String remoteControl;

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

    public int getFactoryId() {
        return this.factoryId;
    }

    public void setFactoryId(int i) {
        this.factoryId = i;
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

    public int getLedNum() {
        return this.ledNum;
    }

    public void setLedNum(int i) {
        this.ledNum = i;
    }

    public int getNameLength() {
        return this.nameLength;
    }

    public void setNameLength(int i) {
        this.nameLength = i;
    }

    public String getBleName() {
        return this.bleName;
    }

    public void setBleName(String str) {
        this.bleName = str;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public String getKeyMode() {
        return this.keyMode;
    }

    public void setKeyMode(String str) {
        this.keyMode = str;
    }

    public int getRGBModel() {
        return this.RGBModel;
    }

    public void setRGBModel(int i) {
        this.RGBModel = i;
    }

    public int getMaxMixLight() {
        return this.maxMixLight;
    }

    public void setMaxMixLight(int i) {
        this.maxMixLight = i;
    }

    public int getMaxOtherLight() {
        return this.maxOtherLight;
    }

    public void setMaxOtherLight(int i) {
        this.maxOtherLight = i;
    }

    public String getRemoteControl() {
        return this.remoteControl;
    }

    public void setRemoteControl(String str) {
        this.remoteControl = str;
    }

    public String getDIYMode() {
        return this.DIYMode;
    }

    public void setDIYMode(String str) {
        this.DIYMode = str;
    }

    public List<DIYInfoBean> getConfigDIYColor() {
        return this.configDIYColor;
    }

    public void setConfigDIYColor(List<DIYInfoBean> list) {
        this.configDIYColor = list;
    }

    public String getSubMode() {
        return this.SubMode;
    }

    public void setSubMode(String str) {
        this.SubMode = str;
    }

    public List<SubColorBean> getConfigSubColor() {
        return this.configSubColor;
    }

    public void setConfigSubColor(List<SubColorBean> list) {
        this.configSubColor = list;
    }
}
