package com.mergbw.android.google;

import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.GroupItemBean;
import com.mergbw.core.track.TrackConstants;
import com.mergbw.core.utils.AppUtils;

public class GoogleTrackManager {
    private static GoogleTrackManager instance;

    public static GoogleTrackManager getInstance() {
        if (instance == null) {
            instance = new GoogleTrackManager();
        }
        return instance;
    }

    private void doTrack(String str, Bundle bundle) {
        FirebaseAnalytics.getInstance(AppUtils.getApp()).logEvent(str, bundle);
    }

    private Bundle getTrackInfo() {
        Bundle bundle = new Bundle();
        bundle.putString("userID", AppUtils.getUserId());
        bundle.putInt("platform", 1);
        bundle.putString("deviceModel", Build.MANUFACTURER + " + " + Build.BRAND + " + " + Build.MODEL);
        StringBuilder sb = new StringBuilder("Android ");
        sb.append(Build.VERSION.RELEASE);
        bundle.putString("systemVersion", sb.toString());
        bundle.putString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, getVersionName());
        return bundle;
    }

    private String getVersionName() {
        return getPackageInfo().versionName;
    }

    private PackageInfo getPackageInfo() {
        try {
            return AppUtils.getApp().getPackageManager().getPackageInfo(AppUtils.getApp().getPackageName(), 16384);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void launcherTrack(boolean z) {
        Bundle trackInfo = getTrackInfo();
        trackInfo.putInt("EventType", TrackConstants.EventType.APP_LAUNCHER.value);
        trackInfo.putInt("EventId", z ? 1 : 2);
        doTrack(TrackConstants.Page.APPLICATION.value, trackInfo);
    }

    public void homeClickTrack(int i, int i2, int i3) {
        Bundle trackInfo = getTrackInfo();
        trackInfo.putInt("EventType", TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.putInt("EventId", i);
        if (i2 != 0) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("AddEntry", (Object) Integer.valueOf(i2));
            trackInfo.putString("Data", JSON.toJSONString(jSONObject));
        }
        if (i3 != 0) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("AgreementType", (Object) Integer.valueOf(i3));
            trackInfo.putString("Data", JSON.toJSONString(jSONObject2));
        }
        doTrack(TrackConstants.Page.MAIN_PAGE.value, trackInfo);
    }

    public void addDeviceTrack(DeviceInfoBean deviceInfoBean) {
        Bundle trackInfo = getTrackInfo();
        trackInfo.putInt("EventType", TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.putInt("EventId", 1);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("DeviceType", (Object) Integer.valueOf(deviceInfoBean.getDeviceType()));
        jSONObject.put("DeviceModel", (Object) deviceInfoBean.getDeviceModel());
        jSONObject.put("FactoryID", (Object) Integer.valueOf(deviceInfoBean.getFactoryID()));
        jSONObject.put("DeviceName", (Object) deviceInfoBean.getDeviceName());
        jSONObject.put("DeviceAlias", (Object) deviceInfoBean.getAliasName());
        jSONObject.put("DeviceMac", (Object) deviceInfoBean.getDeviceMac());
        trackInfo.putString("Data", JSON.toJSONString(jSONObject));
        doTrack(TrackConstants.Page.ADD_DEVICE_PAGE.value, trackInfo);
    }

    public void deviceDetailTrack(int i, DeviceInfoBean deviceInfoBean, int i2) {
        Bundle trackInfo = getTrackInfo();
        trackInfo.putInt("EventType", TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.putInt("EventId", i);
        JSONObject jSONObject = new JSONObject();
        if (i == 1) {
            jSONObject.put("DeviceType", (Object) Integer.valueOf(deviceInfoBean.getDeviceType()));
            jSONObject.put("DeviceMac", (Object) deviceInfoBean.getDeviceMac());
            jSONObject.put("FactoryID", (Object) Integer.valueOf(deviceInfoBean.getFactoryID()));
        } else if (i == 2) {
            jSONObject.put("DIYType", (Object) Integer.valueOf(i2));
        }
        trackInfo.putString("Data", JSON.toJSONString(jSONObject));
        doTrack(TrackConstants.Page.DEVICE_DETAIL_PAGE.value, trackInfo);
    }

    public void alarmSettingTrack() {
        Bundle trackInfo = getTrackInfo();
        trackInfo.putInt("EventType", TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.putInt("EventId", 1);
        doTrack(TrackConstants.Page.ALARM_SETTING_PAGE.value, trackInfo);
    }

    public void deviceSettingTrack(int i, DeviceInfoBean deviceInfoBean, int i2) {
        Bundle trackInfo = getTrackInfo();
        trackInfo.putInt("EventType", TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.putInt("EventId", i);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("DeviceType", (Object) Integer.valueOf(deviceInfoBean.getDeviceType()));
        jSONObject.put("DeviceMac", (Object) deviceInfoBean.getDeviceMac());
        jSONObject.put("FactoryID", (Object) Integer.valueOf(deviceInfoBean.getFactoryID()));
        if (i == 1) {
            jSONObject.put("LedNum", (Object) Integer.valueOf(i2));
        }
        trackInfo.putString("Data", JSON.toJSONString(jSONObject));
        doTrack(TrackConstants.Page.DEVICE_SETTING_PAGE.value, trackInfo);
    }

    public void groupDetailTrack(int i, GroupItemBean groupItemBean, int i2) {
        Bundle trackInfo = getTrackInfo();
        trackInfo.putInt("EventType", TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.putInt("EventId", i);
        JSONObject jSONObject = new JSONObject();
        if (i == 1) {
            jSONObject.put("GroupName", (Object) groupItemBean.getGroupName());
            jSONObject.put("GroupType", (Object) Integer.valueOf(groupItemBean.getDeviceType()));
            if (groupItemBean.getDeviceMacList() != null) {
                jSONObject.put("DeviceNum", (Object) Integer.valueOf(groupItemBean.getDeviceMacList().length));
            } else {
                jSONObject.put("DeviceNum", (Object) 0);
            }
        } else if (i == 2) {
            jSONObject.put("Power", (Object) Integer.valueOf(i2));
        }
        trackInfo.putString("Data", JSON.toJSONString(jSONObject));
        doTrack(TrackConstants.Page.GROUP_DETAIL_PAGE.value, trackInfo);
    }

    public void groupSettingTrack(int i, GroupItemBean groupItemBean) {
        Bundle trackInfo = getTrackInfo();
        trackInfo.putInt("EventType", TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.putInt("EventId", i);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("GroupName", (Object) groupItemBean.getGroupName());
        jSONObject.put("GroupType", (Object) Integer.valueOf(groupItemBean.getDeviceType()));
        jSONObject.put("DeviceNum", (Object) Integer.valueOf(groupItemBean.getDeviceList().size()));
        trackInfo.putString("Data", JSON.toJSONString(jSONObject));
        doTrack(TrackConstants.Page.GROUP_SETTING_PAGE.value, trackInfo);
    }
}
