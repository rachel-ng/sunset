package com.mergbw.android.ui.deviceDetail.viewModel;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.clj.fastble.data.BleDevice;
import com.mergbw.android.task.AudioRecordTask;
import com.mergbw.android.ui.addDevice.BindStatus;
import com.mergbw.android.ui.deviceDetail.bean.BrightnessInfo;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.ble.BleCallback;
import com.mergbw.core.ble.BleStatus;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.presenter.ColorDatabasePresenter;
import com.mergbw.core.database.presenter.IColorDatabaseListener;
import com.mergbw.core.network.factory.FactoryDataCallback;
import com.mergbw.core.network.factory.FactoryDataPresenter;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;
import com.mergbw.core.utils.ThreadManager;
import java.util.Date;
import java.util.List;

public class BaseDeviceDetailViewModel extends AndroidViewModel {
    private AudioRecordTask mAudioRecordTask;
    public final MutableLiveData<BindStatus> mBindStatusData = new MutableLiveData<>();
    public final MutableLiveData<BrightnessInfo> mBrightnessInfo = new MutableLiveData<>();
    public final ColorDatabasePresenter mColorDatabasePresenter;
    public final MutableLiveData<List<ColorBean>> mCommonColorListData = new MutableLiveData<>();
    public final MutableLiveData<BleStatus> mConnectStatusData = new MutableLiveData<>();
    public DeviceInfoBean mCurrentDevice;
    public int mCurrentSingleColor;
    private final FactoryDataPresenter mDataPresenter;
    public final MutableLiveData<DeviceInfoBean> mDetailViewData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final MutableLiveData<FactoryInfoBean> mFactoryInfoData = new MutableLiveData<>();
    private final String mTag;

    /* access modifiers changed from: protected */
    public void onBleResponse(byte b2, byte[] bArr) {
    }

    public void setBrightness(int i) {
    }

    public void setScene(SceneData sceneData) {
    }

    public BaseDeviceDetailViewModel(Application application, String str) {
        super(application);
        this.mTag = str;
        RGBDeviceManager.getInstance().setCallback(str, new BleCallback() {
            public void onAlreadyConnected(BleDevice bleDevice) {
                if (bleDevice.getMac().equals(BaseDeviceDetailViewModel.this.mCurrentDevice.getDeviceMac())) {
                    BaseDeviceDetailViewModel.this.mConnectStatusData.postValue(BleStatus.CONNECTED);
                    BaseDeviceDetailViewModel.this.mCurrentDevice.setConnect(true);
                }
            }

            public void onConnected(BleDevice bleDevice) {
                if (bleDevice.getMac().equals(BaseDeviceDetailViewModel.this.mCurrentDevice.getDeviceMac())) {
                    BaseDeviceDetailViewModel.this.mConnectStatusData.postValue(BleStatus.CONNECTED);
                    BaseDeviceDetailViewModel.this.mCurrentDevice.setConnect(true);
                }
            }

            public void onConnectedFailed(BleDevice bleDevice) {
                if (bleDevice.getMac().equals(BaseDeviceDetailViewModel.this.mCurrentDevice.getDeviceMac())) {
                    BaseDeviceDetailViewModel.this.mConnectStatusData.postValue(BleStatus.CONNECT_ERROR);
                    BaseDeviceDetailViewModel.this.mCurrentDevice.setConnect(false);
                }
            }

            public void onDisconnected(BleDevice bleDevice) {
                if (bleDevice.getMac().equals(BaseDeviceDetailViewModel.this.mCurrentDevice.getDeviceMac())) {
                    BaseDeviceDetailViewModel.this.mConnectStatusData.postValue(BleStatus.DISCONNECTED);
                    BaseDeviceDetailViewModel.this.mCurrentDevice.setConnect(false);
                }
            }

            public void onNotifySuccess(BleDevice bleDevice) {
                super.onNotifySuccess(bleDevice);
            }

            public void onNotifyData(BleDevice bleDevice, byte b2, byte[] bArr) {
                if (bleDevice.getMac().equals(BaseDeviceDetailViewModel.this.mCurrentDevice.getDeviceMac())) {
                    BaseDeviceDetailViewModel.this.onBleResponse(b2, bArr);
                }
            }

            public void onMtuChanged(BleDevice bleDevice, boolean z, int i) {
                super.onMtuChanged(bleDevice, z, i);
            }
        });
        this.mColorDatabasePresenter = new ColorDatabasePresenter(new IColorDatabaseListener() {
            public void onAddSuccess() {
            }

            public void onDeleteSuccess() {
            }

            public void onUpdateSuccess() {
            }

            public void onGetColorList(List<ColorBean> list) {
                BaseDeviceDetailViewModel.this.mCommonColorListData.postValue(list);
            }
        });
        this.mDataPresenter = new FactoryDataPresenter(new FactoryDataCallback() {
            public void onFactoryInfoResult(boolean z, String str, FactoryInfoBean factoryInfoBean) {
                super.onFactoryInfoResult(z, str, factoryInfoBean);
                if (z && factoryInfoBean != null) {
                    BaseDeviceDetailViewModel.this.mFactoryInfoData.postValue(factoryInfoBean);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        stopGetAudio();
        RGBDeviceManager.getInstance().removeCallback(this.mTag);
    }

    public void setDetailViewDataObserver(LifecycleOwner lifecycleOwner, Observer<DeviceInfoBean> observer) {
        this.mDetailViewData.observe(lifecycleOwner, observer);
    }

    public void setCommonColorListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<ColorBean>> observer) {
        this.mCommonColorListData.observe(lifecycleOwner, observer);
        this.mColorDatabasePresenter.getColorList();
    }

    public void initData(Intent intent) {
        DeviceInfoBean deviceInfoBean = (DeviceInfoBean) intent.getSerializableExtra("device");
        this.mCurrentDevice = deviceInfoBean;
        this.mDetailViewData.postValue(deviceInfoBean);
        sendData(CommandUtils.getCommand((byte) 0, (byte[]) null));
    }

    public void setConnectStatusObserver(LifecycleOwner lifecycleOwner, Observer<BleStatus> observer) {
        this.mConnectStatusData.observe(lifecycleOwner, observer);
    }

    public void setBindStatusObserver(LifecycleOwner lifecycleOwner, Observer<BindStatus> observer) {
        this.mBindStatusData.observe(lifecycleOwner, observer);
    }

    public void setBrightnessInfoObserver(LifecycleOwner lifecycleOwner, Observer<BrightnessInfo> observer) {
        this.mBrightnessInfo.observe(lifecycleOwner, observer);
    }

    public void setFactoryInfoObserver(LifecycleOwner lifecycleOwner, Observer<FactoryInfoBean> observer) {
        this.mFactoryInfoData.observe(lifecycleOwner, observer);
        this.mDataPresenter.getFactoryInfo(this.mCurrentDevice.getFactoryID());
    }

    public DeviceInfoBean getCurrentDevice() {
        return this.mCurrentDevice;
    }

    public void sendData(byte[] bArr) {
        RGBDeviceManager.getInstance().sendCommandData(this.mCurrentDevice, bArr);
    }

    public void saveSingleColor() {
        if (this.mCurrentSingleColor != 0) {
            ColorBean colorBean = new ColorBean();
            colorBean.setColorValue(this.mCurrentSingleColor);
            colorBean.setAddTime(new Date().getTime());
            this.mColorDatabasePresenter.addColor(colorBean);
        }
    }

    public void setPower(boolean z) {
        sendData(CommandUtils.getCommand((byte) 1, new byte[]{z ? (byte) 1 : 0}));
    }

    public void setColor(int i) {
        this.mCurrentSingleColor = i;
    }

    /* access modifiers changed from: private */
    public void sendAudioColor(int i) {
        RGBDeviceManager.getInstance().sendAudioColor(this.mCurrentDevice, CommandUtils.getCommand((byte) 3, new byte[]{(byte) (Color.red(i) & 255), (byte) (Color.green(i) & 255), (byte) (Color.blue(i) & 255)}));
    }

    public void startVoiceData(int i, int i2) {
        AudioRecordTask audioRecordTask = this.mAudioRecordTask;
        if (audioRecordTask == null) {
            this.mAudioRecordTask = new AudioRecordTask(i, (float) i2, new BaseDeviceDetailViewModel$$ExternalSyntheticLambda0(this));
            ThreadManager.getLongPool().execute(this.mAudioRecordTask);
            return;
        }
        audioRecordTask.setAudioSensitive(i);
        this.mAudioRecordTask.setHue((float) i2);
    }

    public void setAudioSensitive(int i) {
        AudioRecordTask audioRecordTask = this.mAudioRecordTask;
        if (audioRecordTask != null) {
            audioRecordTask.setAudioSensitive(i);
        }
    }

    public void stopGetAudio() {
        AudioRecordTask audioRecordTask = this.mAudioRecordTask;
        if (audioRecordTask != null) {
            audioRecordTask.release();
            this.mAudioRecordTask = null;
        }
    }
}
