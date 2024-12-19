package com.mergbw.core.ui.subColorSetting;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.clj.fastble.data.BleDevice;
import com.mergbw.core.bean.SubItemBean;
import com.mergbw.core.ble.BleCallback;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.SubColorBean;
import com.mergbw.core.database.presenter.ColorDatabasePresenter;
import com.mergbw.core.database.presenter.IColorDatabaseListener;
import com.mergbw.core.database.presenter.ISubColorDatabaseListener;
import com.mergbw.core.database.presenter.SubColorDatabasePresenter;
import com.mergbw.core.track.TrackManager;
import java.util.List;

public class SubColorViewModel extends AndroidViewModel {
    public static final int FROM_FACTORY = 2;
    public static final int FROM_USER = 1;
    private int from = 1;
    /* access modifiers changed from: private */
    public final MutableLiveData<Boolean> mAddResultData = new MutableLiveData<>();
    private final ColorDatabasePresenter mColorDatabasePresenter;
    /* access modifiers changed from: private */
    public final MutableLiveData<List<ColorBean>> mCommonColorListData = new MutableLiveData<>();
    private DeviceInfoBean mCurrentDevice;
    /* access modifiers changed from: private */
    public final MutableLiveData<Boolean> mDeleteResultData = new MutableLiveData<>();
    private final MutableLiveData<DeviceInfoBean> mDeviceInfoData = new MutableLiveData<>();
    private SubColorBean mSubColor;
    private final MutableLiveData<SubColorBean> mSubColorData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final MutableLiveData<List<SubColorBean>> mSubColorListData = new MutableLiveData<>();
    private final SubColorDatabasePresenter mSubColorPresenter;

    public SubColorViewModel(Application application) {
        super(application);
        RGBDeviceManager.getInstance().setCallback("SubColorViewModel", new BleCallback() {
            public void onNotifyData(BleDevice bleDevice, byte b2, byte[] bArr) {
                super.onNotifyData(bleDevice, b2, bArr);
            }
        });
        this.mSubColorPresenter = new SubColorDatabasePresenter(new ISubColorDatabaseListener() {
            public void onAddSubColor(Long l) {
            }

            public void onGetSubColor(SubColorBean subColorBean) {
            }

            public void onGetSubColorList(List<SubColorBean> list) {
                SubColorViewModel.this.mSubColorListData.postValue(list);
            }

            public void onAddSuccess() {
                SubColorViewModel.this.mAddResultData.postValue(true);
            }

            public void onUpdateSuccess() {
                SubColorViewModel.this.mAddResultData.postValue(true);
            }

            public void onDeleteSuccess() {
                SubColorViewModel.this.mDeleteResultData.postValue(true);
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
                SubColorViewModel.this.mCommonColorListData.postValue(list);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        RGBDeviceManager.getInstance().removeCallback("SubColorViewModel");
    }

    public void setDeviceInfoDataObserver(LifecycleOwner lifecycleOwner, Observer<DeviceInfoBean> observer) {
        this.mDeviceInfoData.observe(lifecycleOwner, observer);
    }

    public void setSubColorDataObserver(LifecycleOwner lifecycleOwner, Observer<SubColorBean> observer) {
        this.mSubColorData.observe(lifecycleOwner, observer);
    }

    public void initData(Intent intent) {
        this.mCurrentDevice = (DeviceInfoBean) intent.getSerializableExtra("device");
        this.mSubColor = (SubColorBean) intent.getSerializableExtra("subColor");
        this.from = intent.getIntExtra(TypedValues.TransitionType.S_FROM, 1);
        this.mSubColorData.postValue(this.mSubColor);
        this.mDeviceInfoData.postValue(this.mCurrentDevice);
    }

    public SubColorBean getSubColor() {
        return this.mSubColor;
    }

    public void setSubColorListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<SubColorBean>> observer) {
        this.mSubColorListData.observe(lifecycleOwner, observer);
        this.mSubColorPresenter.getColorList();
    }

    public void setAddResultObserver(LifecycleOwner lifecycleOwner, Observer<Boolean> observer) {
        this.mAddResultData.observe(lifecycleOwner, observer);
    }

    public void setCommonColorListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<ColorBean>> observer) {
        this.mCommonColorListData.observe(lifecycleOwner, observer);
        this.mColorDatabasePresenter.getColorList();
    }

    public void addSubColor(SubColorBean subColorBean) {
        if (this.from == 1) {
            subColorBean.setDeviceType(this.mCurrentDevice.getDeviceType());
        }
        subColorBean.setSubType(this.from == 2 ? 1 : 0);
        this.mSubColorPresenter.addColor(subColorBean);
        TrackManager.getInstance().deviceDetailTrack(2, (DeviceInfoBean) null, 1);
    }

    public void updateSubColor(String str) {
        SubColorBean subColorBean = this.mSubColor;
        if (subColorBean != null) {
            subColorBean.setColorValue(str);
            this.mSubColorPresenter.updateSubColor(this.mSubColor);
        }
    }

    public void setDeleteResultObserver(LifecycleOwner lifecycleOwner, Observer<Boolean> observer) {
        this.mDeleteResultData.observe(lifecycleOwner, observer);
    }

    public void deleteSubColor(List<SubColorBean> list) {
        this.mSubColorPresenter.deleteColor(list);
    }

    public void sendData(byte[] bArr) {
        RGBDeviceManager.getInstance().sendCommandData(this.mCurrentDevice, bArr);
    }

    public void setSubColor(List<SubItemBean> list) {
        if (this.mCurrentDevice != null) {
            byte[] bArr = new byte[61];
            bArr[0] = 1;
            for (int i = 0; i < list.size(); i++) {
                int color = list.get(i).getColor();
                int i2 = i * 3;
                bArr[i2 + 1] = (byte) (Color.red(color) & 255);
                bArr[i2 + 2] = (byte) (Color.green(color) & 255);
                bArr[i2 + 3] = (byte) (Color.blue(color) & 255);
            }
            int mtu = this.mCurrentDevice.getMtu() - 8;
            if (61 > mtu) {
                int ceil = (int) Math.ceil(((double) 61) / ((double) mtu));
                for (int i3 = 0; i3 < ceil; i3++) {
                    if (i3 == ceil - 1) {
                        int i4 = i3 * mtu;
                        int i5 = 61 - i4;
                        byte[] bArr2 = new byte[i5];
                        System.arraycopy(bArr, i4, bArr2, 0, i5);
                        sendData(CommandUtils.getSeqCommand((byte) 4, bArr2, (byte) -1, i5));
                    } else {
                        byte[] bArr3 = new byte[mtu];
                        System.arraycopy(bArr, i3 * mtu, bArr3, 0, mtu);
                        sendData(CommandUtils.getSeqCommand((byte) 4, bArr3, (byte) (i3 + 1), mtu));
                    }
                }
                return;
            }
            sendData(CommandUtils.getSeqCommand((byte) 4, bArr, (byte) -1, 61));
        }
    }
}
