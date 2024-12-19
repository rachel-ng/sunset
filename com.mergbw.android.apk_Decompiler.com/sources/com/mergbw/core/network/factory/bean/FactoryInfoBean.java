package com.mergbw.core.network.factory.bean;

import java.io.Serializable;
import java.util.List;

public class FactoryInfoBean implements Serializable {
    private String avatar;
    private String email;
    private String nickName;
    private String phonenumber;
    private String promotionPicture;
    private String remark;
    private int roleType;
    private String siteCode;
    private List<SiteInfo> siteList;
    private int userId;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public void setPhonenumber(String str) {
        this.phonenumber = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public int getRoleType() {
        return this.roleType;
    }

    public void setRoleType(int i) {
        this.roleType = i;
    }

    public String getPromotionPicture() {
        return this.promotionPicture;
    }

    public void setPromotionPicture(String str) {
        this.promotionPicture = str;
    }

    public String getSiteCode() {
        return this.siteCode;
    }

    public void setSiteCode(String str) {
        this.siteCode = str;
    }

    public List<SiteInfo> getSiteList() {
        return this.siteList;
    }

    public void setSiteList(List<SiteInfo> list) {
        this.siteList = list;
    }

    public static class SiteInfo implements Serializable {
        private String siteCode;
        private String siteName;

        public String getSiteCode() {
            return this.siteCode;
        }

        public void setSiteCode(String str) {
            this.siteCode = str;
        }

        public String getSiteName() {
            return this.siteName;
        }

        public void setSiteName(String str) {
            this.siteName = str;
        }
    }
}
