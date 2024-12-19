package com.mergbw.core.track.bean;

public class TrackInfoBean {
    private String Data;
    private int EventId;
    private int EventType;
    private String Page;
    private String appVersion;
    private String deviceModel;
    private int platform;
    private String systemVersion;
    private String userID;

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public int getPlatform() {
        return this.platform;
    }

    public void setPlatform(int i) {
        this.platform = i;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public String getSystemVersion() {
        return this.systemVersion;
    }

    public void setSystemVersion(String str) {
        this.systemVersion = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public int getEventType() {
        return this.EventType;
    }

    public void setEventType(int i) {
        this.EventType = i;
    }

    public String getPage() {
        return this.Page;
    }

    public void setPage(String str) {
        this.Page = str;
    }

    public int getEventId() {
        return this.EventId;
    }

    public void setEventId(int i) {
        this.EventId = i;
    }

    public String getData() {
        return this.Data;
    }

    public void setData(String str) {
        this.Data = str;
    }
}
