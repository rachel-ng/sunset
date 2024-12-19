package com.mergbw.android.ui.groupSetting;

import android.app.Application;
import android.content.Intent;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.android.ui.groupSetting.bean.DeviceGroupBean;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.GroupItemBean;
import com.mergbw.core.database.presenter.DeviceDatabasePresenter;
import com.mergbw.core.database.presenter.GroupDatabasePresenter;
import com.mergbw.core.database.presenter.IDeviceDatabaseListener;
import com.mergbw.core.database.presenter.IGroupDatabaseListener;
import com.mergbw.core.track.TrackManager;
import java.util.ArrayList;
import java.util.List;

public class GroupSettingViewModel extends AndroidViewModel {
    /* access modifiers changed from: private */
    public final MutableLiveData<Boolean> mAddResultData = new MutableLiveData<>();
    private final MutableLiveData<List<DeviceInfoBean>> mCanDeviceListData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final MutableLiveData<Boolean> mDeleteResultData = new MutableLiveData<>();
    private final DeviceDatabasePresenter mDeviceDatabasePresenter = new DeviceDatabasePresenter(new IDeviceDatabaseListener() {
        public void onAddSuccess() {
        }

        public void onDeleteSuccess() {
        }

        public void onGetDeviceInfo(DeviceInfoBean deviceInfoBean) {
        }

        public void onUpdateSuccess() {
        }

        public void onGetDeviceList(List<DeviceInfoBean> list) {
            ArrayList arrayList = new ArrayList();
            if (GroupSettingViewModel.this.mGroupInfo == null) {
                for (DeviceInfoBean next : list) {
                    next.setConnect(RGBDeviceManager.getInstance().isConnected(next.getDeviceMac()));
                }
                DeviceGroupBean deviceGroupBean = new DeviceGroupBean();
                deviceGroupBean.setType(DeviceGroupBean.CAN_ADD_DEVICE);
                deviceGroupBean.setDeviceList(list);
                arrayList.add(deviceGroupBean);
                GroupSettingViewModel.this.mDeviceListData.postValue(arrayList);
                return;
            }
            DeviceGroupBean deviceGroupBean2 = new DeviceGroupBean();
            deviceGroupBean2.setType(DeviceGroupBean.ADDED_DEVICE);
            deviceGroupBean2.setDeviceList(GroupSettingViewModel.this.mGroupInfo.getDeviceList());
            arrayList.add(deviceGroupBean2);
            ArrayList arrayList2 = new ArrayList();
            for (DeviceInfoBean next2 : list) {
                for (DeviceInfoBean deviceMac : GroupSettingViewModel.this.mGroupInfo.getDeviceList()) {
                    if (next2.getDeviceMac().equals(deviceMac.getDeviceMac())) {
                        arrayList2.add(next2);
                    }
                }
            }
            list.removeAll(arrayList2);
            arrayList2.clear();
            for (DeviceInfoBean next3 : list) {
                if (next3.getDeviceType() != GroupSettingViewModel.this.mGroupInfo.getDeviceType()) {
                    arrayList2.add(next3);
                }
            }
            list.removeAll(arrayList2);
            for (DeviceInfoBean next4 : list) {
                next4.setConnect(RGBDeviceManager.getInstance().isConnected(next4.getDeviceMac()));
            }
            DeviceGroupBean deviceGroupBean3 = new DeviceGroupBean();
            deviceGroupBean3.setType(DeviceGroupBean.CAN_ADD_DEVICE);
            deviceGroupBean3.setDeviceList(list);
            arrayList.add(deviceGroupBean3);
            GroupSettingViewModel.this.mDeviceListData.postValue(arrayList);
        }
    });
    /* access modifiers changed from: private */
    public final MutableLiveData<List<DeviceGroupBean>> mDeviceListData = new MutableLiveData<>();
    private final GroupDatabasePresenter mGroupDatabasePresenter = new GroupDatabasePresenter(new IGroupDatabaseListener() {
        public void onGetGroupInfo(GroupItemBean groupItemBean) {
        }

        public void onGetGroupList(List<GroupItemBean> list) {
        }

        public void onAddSuccess() {
            GroupSettingViewModel.this.mAddResultData.postValue(true);
        }

        public void onUpdateSuccess() {
            GroupSettingViewModel.this.mUpdateResultData.postValue(true);
        }

        public void onDeleteSuccess() {
            GroupSettingViewModel.this.mDeleteResultData.postValue(true);
        }
    });
    /* access modifiers changed from: private */
    public GroupItemBean mGroupInfo;
    private final MutableLiveData<GroupItemBean> mGroupInfoData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final MutableLiveData<Boolean> mUpdateResultData = new MutableLiveData<>();

    public GroupSettingViewModel(Application application) {
        super(application);
    }

    public void setGroupInfoObserver(LifecycleOwner lifecycleOwner, Observer<GroupItemBean> observer) {
        this.mGroupInfoData.observe(lifecycleOwner, observer);
    }

    public void setCanDeviceListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<DeviceInfoBean>> observer) {
        this.mCanDeviceListData.observe(lifecycleOwner, observer);
    }

    public void setDeviceListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<DeviceGroupBean>> observer) {
        this.mDeviceListData.observe(lifecycleOwner, observer);
    }

    public void setAddResultObserver(LifecycleOwner lifecycleOwner, Observer<Boolean> observer) {
        this.mAddResultData.observe(lifecycleOwner, observer);
    }

    public void setDeleteResultObserver(LifecycleOwner lifecycleOwner, Observer<Boolean> observer) {
        this.mDeleteResultData.observe(lifecycleOwner, observer);
    }

    public void setUpdateResultObserver(LifecycleOwner lifecycleOwner, Observer<Boolean> observer) {
        this.mUpdateResultData.observe(lifecycleOwner, observer);
    }

    public void initData(Intent intent) {
        GroupItemBean groupItemBean = (GroupItemBean) intent.getSerializableExtra("group");
        this.mGroupInfo = groupItemBean;
        this.mGroupInfoData.postValue(groupItemBean);
        this.mDeviceDatabasePresenter.getDeviceList();
    }

    public GroupItemBean getGroupInfo() {
        return this.mGroupInfo;
    }

    public void addGroup(String str, List<DeviceGroupBean> list) {
        ArrayList arrayList = new ArrayList();
        for (DeviceGroupBean next : list) {
            if (next.getType() == DeviceGroupBean.ADDED_DEVICE) {
                arrayList.addAll(next.getDeviceList());
            } else {
                for (DeviceInfoBean next2 : next.getDeviceList()) {
                    if (next2.isSelected()) {
                        arrayList.add(next2);
                    }
                }
            }
        }
        GroupItemBean groupItemBean = new GroupItemBean();
        groupItemBean.setGroupName(str);
        if (!arrayList.isEmpty()) {
            groupItemBean.setDeviceType(((DeviceInfoBean) arrayList.get(0)).getDeviceType());
        } else {
            groupItemBean.setDeviceType(1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == 0) {
                sb = new StringBuilder(((DeviceInfoBean) arrayList.get(i)).getDeviceMac());
            } else {
                sb.append(";");
                sb.append(((DeviceInfoBean) arrayList.get(i)).getDeviceMac());
            }
        }
        groupItemBean.setDevices(sb.toString());
        this.mGroupDatabasePresenter.addGroup(groupItemBean);
        TrackManager.getInstance().groupSettingTrack(1, groupItemBean);
        GoogleTrackManager.getInstance().groupSettingTrack(1, groupItemBean);
    }

    public void deleteGroup() {
        this.mGroupDatabasePresenter.deleteGroup(this.mGroupInfo);
        TrackManager.getInstance().groupSettingTrack(2, this.mGroupInfo);
        GoogleTrackManager.getInstance().groupSettingTrack(2, this.mGroupInfo);
    }

    public void updateGroup(String str, List<DeviceGroupBean> list) {
        if (this.mGroupInfo != null) {
            ArrayList arrayList = new ArrayList();
            for (DeviceGroupBean next : list) {
                if (next.getType() == DeviceGroupBean.ADDED_DEVICE) {
                    arrayList.addAll(next.getDeviceList());
                } else {
                    for (DeviceInfoBean next2 : next.getDeviceList()) {
                        if (next2.isSelected()) {
                            arrayList.add(next2);
                        }
                    }
                }
            }
            this.mGroupInfo.setGroupName(str);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                if (i == 0) {
                    sb = new StringBuilder(((DeviceInfoBean) arrayList.get(i)).getDeviceMac());
                } else {
                    sb.append(";");
                    sb.append(((DeviceInfoBean) arrayList.get(i)).getDeviceMac());
                }
            }
            this.mGroupInfo.setDevices(sb.toString());
            this.mGroupDatabasePresenter.updateGroup(this.mGroupInfo);
        }
    }
}
