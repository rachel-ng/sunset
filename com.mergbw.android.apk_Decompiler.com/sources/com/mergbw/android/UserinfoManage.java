package com.mergbw.android;

import android.content.SharedPreferences;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mergbw.core.network.app.bean.UserResponse;
import com.mergbw.core.utils.AppUtils;

public class UserinfoManage {
    private static UserinfoManage instance;
    private static SharedPreferences mUserData;

    public static UserinfoManage getInstance() {
        if (instance == null) {
            instance = new UserinfoManage();
        }
        return instance;
    }

    public UserinfoManage() {
        mUserData = AppUtils.getApp().getSharedPreferences("userInfo", 0);
    }

    public void loginSuccess(UserResponse userResponse) {
        setIsLogin(true);
        setUsername(userResponse.getUsername());
        setNickname(userResponse.getNickname());
        setAvatar(userResponse.getIcon());
    }

    public boolean IsLogin() {
        return mUserData.getBoolean(FirebaseAnalytics.Event.LOGIN, false);
    }

    public void setIsLogin(boolean z) {
        mUserData.edit().putBoolean(FirebaseAnalytics.Event.LOGIN, z).apply();
    }

    public String getUsername() {
        return mUserData.getString("username", (String) null);
    }

    private void setUsername(String str) {
        mUserData.edit().putString("username", str).apply();
    }

    public String getNickname() {
        return mUserData.getString("nickname", getUsername());
    }

    public void setNickname(String str) {
        mUserData.edit().putString("nickname", str).apply();
    }

    public String getAvatar() {
        return mUserData.getString("avatar", (String) null);
    }

    private void setAvatar(String str) {
        mUserData.edit().putString("avatar", str).apply();
    }

    public int getListType() {
        return mUserData.getInt("listType", 1);
    }

    public void setListType(int i) {
        mUserData.edit().putInt("listType", i).apply();
    }

    public boolean isReceive() {
        return mUserData.getBoolean("receive", false);
    }

    public void setIsReceive(boolean z) {
        mUserData.edit().putBoolean("receive", z).apply();
    }

    public int getAudioSensitive() {
        return mUserData.getInt("AudioSensitive", 70);
    }

    public void setAudioSensitive(int i) {
        mUserData.edit().putInt("AudioSensitive", i).apply();
    }

    public long getLastAdShowTime() {
        return mUserData.getLong("LastAdShowTime", 0);
    }

    public void setLastAdShowTime(long j) {
        mUserData.edit().putLong("LastAdShowTime", j).apply();
    }
}
