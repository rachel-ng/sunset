package com.mergbw.android.ui.deviceInfo;

import android.app.Application;
import android.content.Intent;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.clj.fastble.data.BleDevice;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.android.task.ResetGroupDataTask;
import com.mergbw.core.ble.BleCallback;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.presenter.DeviceDatabasePresenter;
import com.mergbw.core.database.presenter.IDeviceDatabaseListener;
import com.mergbw.core.event.EventMessage;
import com.mergbw.core.event.GlobalEvent;
import com.mergbw.core.track.TrackManager;
import com.mergbw.core.utils.ThreadManager;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class DeviceInfoViewModel extends AndroidViewModel {
    private final DeviceDatabasePresenter mDatabasePresenter;
    /* access modifiers changed from: private */
    public final MutableLiveData<Boolean> mDeleteResultData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public DeviceInfoBean mDeviceInfo;
    /* access modifiers changed from: private */
    public final MutableLiveData<DeviceInfoBean> mDeviceViewData = new MutableLiveData<>();

    public DeviceInfoViewModel(Application application) {
        super(application);
        RGBDeviceManager.getInstance().setCallback("DeviceInfoViewModel", new BleCallback() {
            public void onNotifyData(BleDevice bleDevice, byte b2, byte[] bArr) {
                if (b2 == 2) {
                    DeviceInfoViewModel.this.mDeviceInfo.setFirmwareVersion(new String(new byte[]{bArr[4], bArr[5], bArr[6]}));
                    DeviceInfoViewModel.this.mDeviceViewData.postValue(DeviceInfoViewModel.this.mDeviceInfo);
                }
                if (b2 == 11 && bArr[4] == 0) {
                    DeviceInfoViewModel.this.sendData(CommandUtils.getCommand((byte) 0, (byte[]) null));
                }
            }
        });
        this.mDatabasePresenter = new DeviceDatabasePresenter(new IDeviceDatabaseListener() {
            public void onAddSuccess() {
            }

            public void onGetDeviceInfo(DeviceInfoBean deviceInfoBean) {
            }

            public void onGetDeviceList(List<DeviceInfoBean> list) {
            }

            public void onUpdateSuccess() {
                DeviceInfoViewModel.this.mDeviceViewData.postValue(DeviceInfoViewModel.this.mDeviceInfo);
                EventBus.getDefault().post(new EventMessage(GlobalEvent.DEVICE_ALIAS_NAME_CHANGE).setData(DeviceInfoViewModel.this.mDeviceInfo.getAliasName()));
            }

            public void onDeleteSuccess() {
                RGBDeviceManager.getInstance().disconnectDevice(DeviceInfoViewModel.this.mDeviceInfo);
                EventBus.getDefault().post(new EventMessage(GlobalEvent.DELETE_DEVICE_SUCCESS_ACTION).setData(DeviceInfoViewModel.this.mDeviceInfo));
                DeviceInfoViewModel.this.mDeleteResultData.postValue(true);
                ThreadManager.getLongPool().execute(new ResetGroupDataTask(DeviceInfoViewModel.this.mDeviceInfo.getDeviceMac()));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        RGBDeviceManager.getInstance().removeCallback("DeviceInfoViewModel");
    }

    public void setDeviceViewDataObserver(LifecycleOwner lifecycleOwner, Observer<DeviceInfoBean> observer) {
        this.mDeviceViewData.observe(lifecycleOwner, observer);
    }

    public void initData(Intent intent) {
        DeviceInfoBean deviceInfoBean = (DeviceInfoBean) intent.getSerializableExtra("device");
        this.mDeviceInfo = deviceInfoBean;
        this.mDeviceViewData.postValue(deviceInfoBean);
        sendData(CommandUtils.getCommand((byte) 2, (byte[]) null));
    }

    public void setDeleteResultObserver(LifecycleOwner lifecycleOwner, Observer<Boolean> observer) {
        this.mDeleteResultData.observe(lifecycleOwner, observer);
    }

    public DeviceInfoBean getCurrentDevice() {
        return this.mDeviceInfo;
    }

    public void updateAliasName(String str) {
        this.mDeviceInfo.setAliasName(str);
        this.mDatabasePresenter.updateDevice(this.mDeviceInfo);
    }

    public void deleteDevice() {
        this.mDatabasePresenter.deleteDevice(this.mDeviceInfo);
        TrackManager.getInstance().deviceSettingTrack(2, this.mDeviceInfo, 0);
        GoogleTrackManager.getInstance().deviceSettingTrack(2, this.mDeviceInfo, 0);
    }

    public void setLedNum(int i) {
        sendData(CommandUtils.getCommand((byte) 11, CommandUtils.getByteArray(i)));
        TrackManager.getInstance().deviceSettingTrack(1, this.mDeviceInfo, i);
        GoogleTrackManager.getInstance().deviceSettingTrack(1, this.mDeviceInfo, i);
    }

    public void sendData(byte[] bArr) {
        RGBDeviceManager.getInstance().sendCommandData(this.mDeviceInfo, bArr);
    }
}
