package com.mergbw.core.track;

import android.content.pm.PackageInfo;
import android.os.Build;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.GroupItemBean;
import com.mergbw.core.network.app.NetworkCallback;
import com.mergbw.core.network.app.presenter.UserDataPresenter;
import com.mergbw.core.track.TrackConstants;
import com.mergbw.core.track.bean.TrackInfoBean;
import com.mergbw.core.utils.AppUtils;

public class TrackManager {
    private static TrackManager instance;
    private final boolean isTrack = false;
    private final UserDataPresenter mPresenter = new UserDataPresenter((NetworkCallback) null);

    private void doTrack(TrackInfoBean trackInfoBean) {
    }

    public static TrackManager getInstance() {
        if (instance == null) {
            instance = new TrackManager();
        }
        return instance;
    }

    private TrackManager() {
    }

    private /* synthetic */ void lambda$doTrack$0(TrackInfoBean trackInfoBean) {
        this.mPresenter.track(trackInfoBean);
    }

    private TrackInfoBean getTrackInfo() {
        TrackInfoBean trackInfoBean = new TrackInfoBean();
        trackInfoBean.setUserID(AppUtils.getUserId());
        trackInfoBean.setPlatform(1);
        trackInfoBean.setDeviceModel(Build.MANUFACTURER + " + " + Build.BRAND + " + " + Build.MODEL);
        StringBuilder sb = new StringBuilder("Android ");
        sb.append(Build.VERSION.RELEASE);
        trackInfoBean.setSystemVersion(sb.toString());
        trackInfoBean.setAppVersion(getVersionName());
        return trackInfoBean;
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
        TrackInfoBean trackInfo = getTrackInfo();
        trackInfo.setEventType(TrackConstants.EventType.APP_LAUNCHER.value);
        trackInfo.setPage(TrackConstants.Page.APPLICATION.value);
        trackInfo.setEventId(z ? 1 : 2);
        doTrack(trackInfo);
    }

    public void homeClickTrack(int i, int i2, int i3) {
        TrackInfoBean trackInfo = getTrackInfo();
        trackInfo.setEventType(TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.setPage(TrackConstants.Page.MAIN_PAGE.value);
        trackInfo.setEventId(i);
        if (i2 != 0) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("AddEntry", (Object) Integer.valueOf(i2));
            trackInfo.setData(JSON.toJSONString(jSONObject));
        }
        if (i3 != 0) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("AgreementType", (Object) Integer.valueOf(i3));
            trackInfo.setData(JSON.toJSONString(jSONObject2));
        }
        doTrack(trackInfo);
    }

    public void addDeviceTrack(DeviceInfoBean deviceInfoBean) {
        TrackInfoBean trackInfo = getTrackInfo();
        trackInfo.setEventType(TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.setPage(TrackConstants.Page.ADD_DEVICE_PAGE.value);
        trackInfo.setEventId(1);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("DeviceType", (Object) Integer.valueOf(deviceInfoBean.getDeviceType()));
        jSONObject.put("DeviceModel", (Object) deviceInfoBean.getDeviceModel());
        jSONObject.put("FactoryID", (Object) Integer.valueOf(deviceInfoBean.getFactoryID()));
        jSONObject.put("DeviceName", (Object) deviceInfoBean.getDeviceName());
        jSONObject.put("DeviceAlias", (Object) deviceInfoBean.getAliasName());
        jSONObject.put("DeviceMac", (Object) deviceInfoBean.getDeviceMac());
        trackInfo.setData(JSON.toJSONString(jSONObject));
        doTrack(trackInfo);
    }

    public void deviceDetailTrack(int i, DeviceInfoBean deviceInfoBean, int i2) {
        TrackInfoBean trackInfo = getTrackInfo();
        trackInfo.setEventType(TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.setPage(TrackConstants.Page.DEVICE_DETAIL_PAGE.value);
        trackInfo.setEventId(i);
        JSONObject jSONObject = new JSONObject();
        if (i == 1) {
            jSONObject.put("DeviceType", (Object) Integer.valueOf(deviceInfoBean.getDeviceType()));
            jSONObject.put("DeviceMac", (Object) deviceInfoBean.getDeviceMac());
            jSONObject.put("FactoryID", (Object) Integer.valueOf(deviceInfoBean.getFactoryID()));
        } else if (i == 2) {
            jSONObject.put("DIYType", (Object) Integer.valueOf(i2));
        }
        trackInfo.setData(JSON.toJSONString(jSONObject));
        doTrack(trackInfo);
    }

    public void alarmSettingTrack() {
        TrackInfoBean trackInfo = getTrackInfo();
        trackInfo.setEventType(TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.setPage(TrackConstants.Page.ALARM_SETTING_PAGE.value);
        trackInfo.setEventId(1);
        doTrack(trackInfo);
    }

    public void deviceSettingTrack(int i, DeviceInfoBean deviceInfoBean, int i2) {
        TrackInfoBean trackInfo = getTrackInfo();
        trackInfo.setEventType(TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.setPage(TrackConstants.Page.DEVICE_SETTING_PAGE.value);
        trackInfo.setEventId(i);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("DeviceType", (Object) Integer.valueOf(deviceInfoBean.getDeviceType()));
        jSONObject.put("DeviceMac", (Object) deviceInfoBean.getDeviceMac());
        jSONObject.put("FactoryID", (Object) Integer.valueOf(deviceInfoBean.getFactoryID()));
        if (i == 1) {
            jSONObject.put("LedNum", (Object) Integer.valueOf(i2));
        }
        trackInfo.setData(JSON.toJSONString(jSONObject));
        doTrack(trackInfo);
    }

    public void groupDetailTrack(int i, GroupItemBean groupItemBean, int i2) {
        TrackInfoBean trackInfo = getTrackInfo();
        trackInfo.setEventType(TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.setPage(TrackConstants.Page.GROUP_DETAIL_PAGE.value);
        trackInfo.setEventId(i);
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
        trackInfo.setData(JSON.toJSONString(jSONObject));
        doTrack(trackInfo);
    }

    public void groupSettingTrack(int i, GroupItemBean groupItemBean) {
        TrackInfoBean trackInfo = getTrackInfo();
        trackInfo.setEventType(TrackConstants.EventType.PAGE_ITEM_CLICK.value);
        trackInfo.setPage(TrackConstants.Page.GROUP_SETTING_PAGE.value);
        trackInfo.setEventId(i);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("GroupName", (Object) groupItemBean.getGroupName());
        jSONObject.put("GroupType", (Object) Integer.valueOf(groupItemBean.getDeviceType()));
        jSONObject.put("DeviceNum", (Object) Integer.valueOf(groupItemBean.getDeviceList().size()));
        trackInfo.setData(JSON.toJSONString(jSONObject));
        doTrack(trackInfo);
    }
}
