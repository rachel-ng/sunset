package com.mergbw.core.network.app.bean;

public class UserOverViewBean {
    private int androidUser;
    private int iphoneUser;
    private int todayAddUser;
    private int todayUseUser;
    private int totalUser;

    public int getTotalUser() {
        return this.totalUser;
    }

    public void setTotalUser(int i) {
        this.totalUser = i;
    }

    public int getAndroidUser() {
        return this.androidUser;
    }

    public void setAndroidUser(int i) {
        this.androidUser = i;
    }

    public int getIphoneUser() {
        return this.iphoneUser;
    }

    public void setIphoneUser(int i) {
        this.iphoneUser = i;
    }

    public int getTodayAddUser() {
        return this.todayAddUser;
    }

    public void setTodayAddUser(int i) {
        this.todayAddUser = i;
    }

    public int getTodayUseUser() {
        return this.todayUseUser;
    }

    public void setTodayUseUser(int i) {
        this.todayUseUser = i;
    }
}
