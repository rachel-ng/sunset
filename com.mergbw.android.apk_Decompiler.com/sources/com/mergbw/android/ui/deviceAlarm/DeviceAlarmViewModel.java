package com.mergbw.android.ui.deviceAlarm;

import android.app.Application;
import android.content.Intent;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.clj.fastble.data.BleDevice;
import com.mergbw.core.ble.BleCallback;
import com.mergbw.core.ble.BleStatus;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.DeviceInfoBean;

public class DeviceAlarmViewModel extends AndroidViewModel {
    /* access modifiers changed from: private */
    public final MutableLiveData<BleStatus> mConnectStatusData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public DeviceInfoBean mDeviceInfo;
    private final MutableLiveData<DeviceInfoBean> mDeviceViewData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final MutableLiveData<Boolean> mSetResultData = new MutableLiveData<>();

    public DeviceAlarmViewModel(Application application) {
        super(application);
        RGBDeviceManager.getInstance().setCallback("DeviceAlarmViewModel", new BleCallback() {
            public void onDisconnected(BleDevice bleDevice) {
                if (bleDevice.getMac().equals(DeviceAlarmViewModel.this.mDeviceInfo.getDeviceMac())) {
                    DeviceAlarmViewModel.this.mConnectStatusData.postValue(BleStatus.DISCONNECTED);
                }
            }

            public void onNotifyData(BleDevice bleDevice, byte b2, byte[] bArr) {
                if (b2 == 10) {
                    DeviceAlarmViewModel.this.mSetResultData.postValue(Boolean.valueOf(bArr[4] == 0));
                    DeviceAlarmViewModel.this.sendData(CommandUtils.getCommand((byte) 0, (byte[]) null));
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        RGBDeviceManager.getInstance().removeCallback("DeviceAlarmViewModel");
    }

    public void setConnectStatusObserver(LifecycleOwner lifecycleOwner, Observer<BleStatus> observer) {
        this.mConnectStatusData.observe(lifecycleOwner, observer);
    }

    public void setDeviceViewDataObserver(LifecycleOwner lifecycleOwner, Observer<DeviceInfoBean> observer) {
        this.mDeviceViewData.observe(lifecycleOwner, observer);
    }

    public void initData(Intent intent) {
        DeviceInfoBean deviceInfoBean = (DeviceInfoBean) intent.getSerializableExtra("device");
        this.mDeviceInfo = deviceInfoBean;
        this.mDeviceViewData.postValue(deviceInfoBean);
    }

    public void setSetResultObserver(LifecycleOwner lifecycleOwner, Observer<Boolean> observer) {
        this.mSetResultData.observe(lifecycleOwner, observer);
    }

    public DeviceInfoBean getDeviceInfo() {
        return this.mDeviceInfo;
    }

    public void sendData(byte[] bArr) {
        RGBDeviceManager.getInstance().sendCommandData(this.mDeviceInfo, bArr);
    }
}
