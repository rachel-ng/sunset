package com.mergbw.core.network.app.bean;

public class FileInfo {
    private Integer id;
    private String md5;
    private String name;
    private Long size;
    private String suffix;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String str) {
        this.suffix = str;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long l) {
        this.size = l;
    }
}
