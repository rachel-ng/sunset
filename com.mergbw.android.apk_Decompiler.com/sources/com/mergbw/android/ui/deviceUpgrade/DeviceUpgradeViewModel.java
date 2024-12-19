package com.mergbw.android.ui.deviceUpgrade;

import android.app.Application;
import android.content.Intent;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.clj.fastble.data.BleDevice;
import com.mergbw.core.ble.BleCallback;
import com.mergbw.core.ble.BleStatus;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.UpgradeErrorRecordBean;
import com.mergbw.core.database.presenter.IUpgradeErrorRecordDatabaseListener;
import com.mergbw.core.database.presenter.UpgradeErrorRecordDatabasePresenter;
import java.util.List;

public class DeviceUpgradeViewModel extends AndroidViewModel {
    /* access modifiers changed from: private */
    public final MutableLiveData<BleStatus> mConnectStatusData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public DeviceInfoBean mDeviceInfo;
    private final MutableLiveData<DeviceInfoBean> mDeviceViewData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final MutableLiveData<List<UpgradeErrorRecordBean>> mErrorRecordListData = new MutableLiveData<>();
    private final UpgradeErrorRecordDatabasePresenter mRecordPresenter;

    public DeviceUpgradeViewModel(Application application) {
        super(application);
        RGBDeviceManager.getInstance().setCallback("DeviceUpgradeViewModel", new BleCallback() {
            public void onDisconnected(BleDevice bleDevice) {
                if (bleDevice.getMac().equals(DeviceUpgradeViewModel.this.mDeviceInfo.getDeviceMac())) {
                    DeviceUpgradeViewModel.this.mConnectStatusData.postValue(BleStatus.DISCONNECTED);
                }
            }
        });
        this.mRecordPresenter = new UpgradeErrorRecordDatabasePresenter(new IUpgradeErrorRecordDatabaseListener() {
            public void onAddSuccess() {
            }

            public void onDeleteSuccess() {
            }

            public void onUpdateSuccess() {
            }

            public void onGetRecordList(List<UpgradeErrorRecordBean> list) {
                DeviceUpgradeViewModel.this.mErrorRecordListData.postValue(list);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        RGBDeviceManager.getInstance().removeCallback("DeviceUpgradeViewModel");
    }

    public void setDeviceViewDataObserver(LifecycleOwner lifecycleOwner, Observer<DeviceInfoBean> observer) {
        this.mDeviceViewData.observe(lifecycleOwner, observer);
    }

    public void initData(Intent intent) {
        DeviceInfoBean deviceInfoBean = (DeviceInfoBean) intent.getSerializableExtra("device");
        this.mDeviceInfo = deviceInfoBean;
        this.mDeviceViewData.postValue(deviceInfoBean);
    }

    public DeviceInfoBean getCurrentDevice() {
        return this.mDeviceInfo;
    }

    public void setConnectStatusObserver(LifecycleOwner lifecycleOwner, Observer<BleStatus> observer) {
        this.mConnectStatusData.observe(lifecycleOwner, observer);
    }

    public void addErrorRecord(UpgradeErrorRecordBean upgradeErrorRecordBean) {
        this.mRecordPresenter.addRecord(upgradeErrorRecordBean);
    }

    public void setErrorRecordListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<UpgradeErrorRecordBean>> observer) {
        this.mErrorRecordListData.observe(lifecycleOwner, observer);
        this.mRecordPresenter.getRecordList();
    }

    public void deleteErrorRecord(UpgradeErrorRecordBean upgradeErrorRecordBean) {
        this.mRecordPresenter.deleteConfig(upgradeErrorRecordBean);
    }
}
