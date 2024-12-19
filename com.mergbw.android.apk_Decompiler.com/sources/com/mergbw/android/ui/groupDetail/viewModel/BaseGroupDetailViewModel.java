package com.mergbw.android.ui.groupDetail.viewModel;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.clj.fastble.data.BleDevice;
import com.mergbw.android.task.AudioRecordTask;
import com.mergbw.android.ui.deviceDetail.bean.BrightnessInfo;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.ble.BleCallback;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.GroupItemBean;
import com.mergbw.core.database.presenter.ColorDatabasePresenter;
import com.mergbw.core.database.presenter.DeviceDatabasePresenter;
import com.mergbw.core.database.presenter.GroupDatabasePresenter;
import com.mergbw.core.database.presenter.IColorDatabaseListener;
import com.mergbw.core.database.presenter.IDeviceDatabaseListener;
import com.mergbw.core.database.presenter.IGroupDatabaseListener;
import com.mergbw.core.utils.StringUtils;
import com.mergbw.core.utils.ThreadManager;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.Date;
import java.util.List;

public class BaseGroupDetailViewModel extends AndroidViewModel {
    private AudioRecordTask mAudioRecordTask;
    public final MutableLiveData<BrightnessInfo> mBrightnessInfo = new MutableLiveData<>();
    public final ColorDatabasePresenter mColorDatabasePresenter = new ColorDatabasePresenter(new IColorDatabaseListener() {
        public void onAddSuccess() {
        }

        public void onDeleteSuccess() {
        }

        public void onUpdateSuccess() {
        }

        public void onGetColorList(List<ColorBean> list) {
            BaseGroupDetailViewModel.this.mCommonColorListData.postValue(list);
        }
    });
    public final MutableLiveData<List<ColorBean>> mCommonColorListData = new MutableLiveData<>();
    public GroupItemBean mCurrentGroup;
    public int mCurrentSingleColor;
    public final DeviceDatabasePresenter mDeviceDatabasePresenter = new DeviceDatabasePresenter(new IDeviceDatabaseListener() {
        public void onAddSuccess() {
        }

        public void onDeleteSuccess() {
        }

        public void onGetDeviceList(List<DeviceInfoBean> list) {
        }

        public void onUpdateSuccess() {
        }

        public void onGetDeviceInfo(DeviceInfoBean deviceInfoBean) {
            deviceInfoBean.setConnect(RGBDeviceManager.getInstance().isConnected(deviceInfoBean.getDeviceMac()));
            deviceInfoBean.setSelected(true);
            if (deviceInfoBean.isConnect()) {
                deviceInfoBean.setBound(true);
            }
            if (!BaseGroupDetailViewModel.this.mCurrentGroup.getDeviceList().contains(deviceInfoBean)) {
                BaseGroupDetailViewModel.this.mCurrentGroup.getDeviceList().add(deviceInfoBean);
            }
            if (!deviceInfoBean.isConnect()) {
                RGBDeviceManager.getInstance().connectDevice(deviceInfoBean);
            }
            if (BaseGroupDetailViewModel.this.mCurrentGroup.getDeviceMacList().length == BaseGroupDetailViewModel.this.mCurrentGroup.getDeviceList().size()) {
                BaseGroupDetailViewModel.this.mGroupInfoData.postValue(BaseGroupDetailViewModel.this.mCurrentGroup);
            }
        }
    });
    public final GroupDatabasePresenter mGroupDatabasePresenter = new GroupDatabasePresenter(new IGroupDatabaseListener() {
        public void onAddSuccess() {
        }

        public void onDeleteSuccess() {
        }

        public void onGetGroupList(List<GroupItemBean> list) {
        }

        public void onUpdateSuccess() {
        }

        public void onGetGroupInfo(GroupItemBean groupItemBean) {
            BaseGroupDetailViewModel.this.mCurrentGroup = groupItemBean;
            BaseGroupDetailViewModel.this.initGroupDevice();
        }
    });
    public final MutableLiveData<GroupItemBean> mGroupInfoData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> mShowLoading = new MutableLiveData<>();
    public final MutableLiveData<DeviceInfoBean> mSingleDeviceInfoData = new MutableLiveData<>();

    public void setBrightness(int i) {
    }

    public void setScene(SceneData sceneData) {
    }

    public void updateGroupInfo() {
    }

    public BaseGroupDetailViewModel(Application application) {
        super(application);
        RGBDeviceManager.getInstance().setCallback("BaseGroupDetailViewModel", new BleCallback() {
            public void onAlreadyConnected(BleDevice bleDevice) {
                for (DeviceInfoBean next : BaseGroupDetailViewModel.this.mCurrentGroup.getDeviceList()) {
                    if (next.getDeviceMac().equals(bleDevice.getMac())) {
                        next.setConnect(true);
                        BaseGroupDetailViewModel.this.mSingleDeviceInfoData.postValue(next);
                        return;
                    }
                }
            }

            public void onConnected(BleDevice bleDevice) {
                for (DeviceInfoBean next : BaseGroupDetailViewModel.this.mCurrentGroup.getDeviceList()) {
                    if (next.getDeviceMac().equals(bleDevice.getMac())) {
                        next.setConnect(true);
                        BaseGroupDetailViewModel.this.mSingleDeviceInfoData.postValue(next);
                        return;
                    }
                }
            }

            public void onConnectedFailed(BleDevice bleDevice) {
                for (DeviceInfoBean next : BaseGroupDetailViewModel.this.mCurrentGroup.getDeviceList()) {
                    if (next.getDeviceMac().equals(bleDevice.getMac())) {
                        next.setConnect(false);
                        next.setConnecting(false);
                        BaseGroupDetailViewModel.this.mSingleDeviceInfoData.postValue(next);
                        return;
                    }
                }
            }

            public void onDisconnected(BleDevice bleDevice) {
                for (DeviceInfoBean next : BaseGroupDetailViewModel.this.mCurrentGroup.getDeviceList()) {
                    if (next.getDeviceMac().equals(bleDevice.getMac())) {
                        next.setConnect(false);
                        next.setOpen(false);
                        BaseGroupDetailViewModel.this.mSingleDeviceInfoData.postValue(next);
                        return;
                    }
                }
            }

            public void onNotifyData(BleDevice bleDevice, byte b2, byte[] bArr) {
                for (DeviceInfoBean next : BaseGroupDetailViewModel.this.mCurrentGroup.getDeviceList()) {
                    if (next.getDeviceMac().equals(bleDevice.getMac())) {
                        if (b2 == 14) {
                            if (next.getDeviceType() == 1) {
                                byte b3 = bArr[4];
                                if (b3 == 1) {
                                    next.setBound(true);
                                } else if (b3 == 2 || b3 == 3) {
                                    next.setBound(false);
                                }
                            } else if (next.getDeviceType() == 6 && bArr[4] == 0) {
                                next.setBound(true);
                            }
                            BaseGroupDetailViewModel.this.mSingleDeviceInfoData.postValue(next);
                        }
                        if (b2 == 0) {
                            ViewDataUtils.analyseSyncData(next, bArr);
                            BaseGroupDetailViewModel.this.mSingleDeviceInfoData.postValue(next);
                            return;
                        }
                        return;
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        stopGetAudio();
        RGBDeviceManager.getInstance().removeCallback("BaseGroupDetailViewModel");
    }

    public void setGroupInfoDataObserver(LifecycleOwner lifecycleOwner, Observer<GroupItemBean> observer) {
        this.mGroupInfoData.observe(lifecycleOwner, observer);
    }

    public void initData(Intent intent) {
        GroupItemBean groupItemBean = (GroupItemBean) intent.getSerializableExtra("group");
        initGroupDevice();
        if (groupItemBean != null) {
            this.mGroupDatabasePresenter.getGroupInfo(groupItemBean.getGroupId());
        }
    }

    /* access modifiers changed from: private */
    public void initGroupDevice() {
        GroupItemBean groupItemBean = this.mCurrentGroup;
        if (groupItemBean == null) {
            return;
        }
        if (!StringUtils.isEmpty(groupItemBean.getDevices())) {
            String[] split = this.mCurrentGroup.getDevices().split(";");
            this.mCurrentGroup.setDeviceMacList(split);
            for (String deviceInfo : split) {
                this.mDeviceDatabasePresenter.getDeviceInfo(deviceInfo);
            }
            return;
        }
        this.mGroupInfoData.postValue(this.mCurrentGroup);
    }

    public GroupItemBean getCurrentGroup() {
        return this.mCurrentGroup;
    }

    public void setCommonColorListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<ColorBean>> observer) {
        this.mCommonColorListData.observe(lifecycleOwner, observer);
        this.mColorDatabasePresenter.getColorList();
    }

    public void setSingleDeviceInfoDataObserver(LifecycleOwner lifecycleOwner, Observer<DeviceInfoBean> observer) {
        this.mSingleDeviceInfoData.observe(lifecycleOwner, observer);
    }

    public void setBrightnessInfoObserver(LifecycleOwner lifecycleOwner, Observer<BrightnessInfo> observer) {
        this.mBrightnessInfo.observe(lifecycleOwner, observer);
    }

    public void setShowLoadingObserver(LifecycleOwner lifecycleOwner, Observer<Boolean> observer) {
        this.mShowLoading.observe(lifecycleOwner, observer);
    }

    public void changeChecked(DeviceInfoBean deviceInfoBean, boolean z) {
        for (DeviceInfoBean next : this.mCurrentGroup.getDeviceList()) {
            if (next.getDeviceMac().equals(deviceInfoBean.getDeviceMac())) {
                next.setSelected(z);
                return;
            }
        }
    }

    public void changeCheckedAll(boolean z) {
        for (DeviceInfoBean selected : this.mCurrentGroup.getDeviceList()) {
            selected.setSelected(z);
        }
    }

    public void sendData(byte[] bArr) {
        for (DeviceInfoBean next : this.mCurrentGroup.getDeviceList()) {
            if (RGBDeviceManager.getInstance().isConnected(next.getDeviceMac()) && next.isSelected()) {
                RGBDeviceManager.getInstance().sendCommandData(next, bArr);
            }
        }
    }

    public void sendDataSingle(DeviceInfoBean deviceInfoBean, byte[] bArr) {
        if (RGBDeviceManager.getInstance().isConnected(deviceInfoBean.getDeviceMac())) {
            RGBDeviceManager.getInstance().sendCommandData(deviceInfoBean, bArr);
        }
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

    public void setPowerSingle(DeviceInfoBean deviceInfoBean, boolean z) {
        sendDataSingle(deviceInfoBean, CommandUtils.getCommand((byte) 1, new byte[]{z ? (byte) 1 : 0}));
    }

    public void setRGBColor(int i) {
        this.mCurrentSingleColor = i;
    }

    /* access modifiers changed from: private */
    public void sendAudioColor(int i) {
        byte[] bArr = {(byte) (Color.red(i) & 255), (byte) (Color.green(i) & 255), (byte) (Color.blue(i) & 255)};
        for (DeviceInfoBean next : this.mCurrentGroup.getDeviceList()) {
            if (RGBDeviceManager.getInstance().isConnected(next.getDeviceMac()) && next.isSelected()) {
                RGBDeviceManager.getInstance().sendAudioColor(next, CommandUtils.getCommand((byte) 3, bArr));
            }
        }
    }

    public void startVoiceData(int i, int i2) {
        AudioRecordTask audioRecordTask = this.mAudioRecordTask;
        if (audioRecordTask == null) {
            this.mAudioRecordTask = new AudioRecordTask(i, (float) i2, new BaseGroupDetailViewModel$$ExternalSyntheticLambda0(this));
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
