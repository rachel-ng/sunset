package com.mergbw.core.network.app.bean;

public class FirmwareVersionBean {
    private String description;
    private int deviceType;
    private int fileId;
    private FileInfo fileInfo;
    private String version;
    private int vpFileId;
    private FileInfo vpFileInfo;
    private String vpVersion;

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public int getFileId() {
        return this.fileId;
    }

    public void setFileId(int i) {
        this.fileId = i;
    }

    public String getVpVersion() {
        return this.vpVersion;
    }

    public void setVpVersion(String str) {
        this.vpVersion = str;
    }

    public int getVpFileId() {
        return this.vpFileId;
    }

    public void setVpFileId(int i) {
        this.vpFileId = i;
    }

    public FileInfo getFileInfo() {
        return this.fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo2) {
        this.fileInfo = fileInfo2;
    }

    public FileInfo getVpFileInfo() {
        return this.vpFileInfo;
    }

    public void setVpFileInfo(FileInfo fileInfo2) {
        this.vpFileInfo = fileInfo2;
    }
}
