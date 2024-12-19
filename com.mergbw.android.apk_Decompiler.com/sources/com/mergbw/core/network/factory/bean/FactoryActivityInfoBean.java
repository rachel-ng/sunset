package com.mergbw.core.network.factory.bean;

public class FactoryActivityInfoBean {
    private String activityDesc;
    private int activityId;
    private String activityLink;
    private String activityName;
    private String activityPicture;
    private String activityType;

    public int getActivityId() {
        return this.activityId;
    }

    public void setActivityId(int i) {
        this.activityId = i;
    }

    public String getActivityType() {
        return this.activityType;
    }

    public void setActivityType(String str) {
        this.activityType = str;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String str) {
        this.activityName = str;
    }

    public String getActivityDesc() {
        return this.activityDesc;
    }

    public void setActivityDesc(String str) {
        this.activityDesc = str;
    }

    public String getActivityPicture() {
        return this.activityPicture;
    }

    public void setActivityPicture(String str) {
        this.activityPicture = str;
    }

    public String getActivityLink() {
        return this.activityLink;
    }

    public void setActivityLink(String str) {
        this.activityLink = str;
    }
}
