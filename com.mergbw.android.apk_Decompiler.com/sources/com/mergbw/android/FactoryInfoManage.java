package com.mergbw.android;

import android.content.SharedPreferences;
import com.mergbw.core.utils.AppUtils;

public class FactoryInfoManage {
    private static FactoryInfoManage instance;
    private static SharedPreferences mFactoryData;

    public static FactoryInfoManage getInstance() {
        if (instance == null) {
            instance = new FactoryInfoManage();
        }
        return instance;
    }

    public FactoryInfoManage() {
        mFactoryData = AppUtils.getApp().getSharedPreferences("factoryInfo", 0);
    }

    public String getHistorySiteCode(int i) {
        return mFactoryData.getString(String.valueOf(i), (String) null);
    }

    public void setHistorySiteCode(int i, String str) {
        mFactoryData.edit().putString(String.valueOf(i), str).apply();
    }
}
