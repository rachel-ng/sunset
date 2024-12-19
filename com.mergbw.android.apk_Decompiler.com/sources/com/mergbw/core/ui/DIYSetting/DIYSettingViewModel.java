package com.mergbw.core.ui.DIYSetting;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.alibaba.fastjson.JSON;
import com.mergbw.core.Constants;
import com.mergbw.core.bean.SubItemBean;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.database.bean.DIYInfoBean;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.SubColorBean;
import com.mergbw.core.database.presenter.ColorDatabasePresenter;
import com.mergbw.core.database.presenter.DIYColorDatabasePresenter;
import com.mergbw.core.database.presenter.IColorDatabaseListener;
import com.mergbw.core.database.presenter.IDIYColorDatabaseListener;
import com.mergbw.core.track.TrackManager;
import com.mergbw.core.utils.ColorUtils;
import com.mergbw.core.utils.MeRGBWLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DIYSettingViewModel extends AndroidViewModel {
    public static final int FROM_FACTORY = 2;
    public static final int FROM_USER = 1;
    private int DIYEffect = 1;
    private int DIYSpeed = 20;
    private int from = 1;
    private final ColorDatabasePresenter mColorDatabasePresenter = new ColorDatabasePresenter(new IColorDatabaseListener() {
        public void onAddSuccess() {
        }

        public void onDeleteSuccess() {
        }

        public void onUpdateSuccess() {
        }

        public void onGetColorList(List<ColorBean> list) {
            DIYSettingViewModel.this.mCommonColorListData.postValue(list);
        }
    });
    /* access modifiers changed from: private */
    public final MutableLiveData<List<ColorBean>> mCommonColorListData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public DIYInfoBean mCurrentDIYInfo;
    private DeviceInfoBean mCurrentDevice;
    private final DIYColorDatabasePresenter mDIYDatabasePresenter = new DIYColorDatabasePresenter(new IDIYColorDatabaseListener() {
        public void onAddSuccess() {
        }

        public void onDeleteSuccess() {
        }

        public void onGetDIYColor(DIYInfoBean dIYInfoBean) {
        }

        public void onGetDIYColorList(List<DIYInfoBean> list) {
        }

        public void onAddDiy(Long l) {
            DIYSettingViewModel.this.mCurrentDIYInfo.setId(DIYSettingViewModel$1$$ExternalSyntheticBackport0.m(l.longValue()));
            MeRGBWLog.i("addDIYColor id: " + l);
            DIYSettingViewModel.this.mSaveResultData.postValue(true);
        }

        public void onUpdateSuccess() {
            DIYSettingViewModel.this.mSaveResultData.postValue(true);
        }
    });
    private final MutableLiveData<DIYInfoBean> mDIYInfoData = new MutableLiveData<>();
    private final MutableLiveData<DeviceInfoBean> mDeviceInfoData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final MutableLiveData<Boolean> mSaveResultData = new MutableLiveData<>();
    private final MutableLiveData<List<SubColorBean>> mSubColorListData = new MutableLiveData<>();

    public DIYSettingViewModel(Application application) {
        super(application);
    }

    public void setDeviceInfoDataObserver(LifecycleOwner lifecycleOwner, Observer<DeviceInfoBean> observer) {
        this.mDeviceInfoData.observe(lifecycleOwner, observer);
    }

    public void setDIYInfoDataObserver(LifecycleOwner lifecycleOwner, Observer<DIYInfoBean> observer) {
        this.mDIYInfoData.observe(lifecycleOwner, observer);
    }

    public void setSubColorListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<SubColorBean>> observer) {
        this.mSubColorListData.observe(lifecycleOwner, observer);
    }

    public void setCommonColorListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<ColorBean>> observer) {
        this.mCommonColorListData.observe(lifecycleOwner, observer);
        this.mColorDatabasePresenter.getColorList();
    }

    public void initData(Intent intent) {
        this.mCurrentDevice = (DeviceInfoBean) intent.getSerializableExtra("device");
        this.mCurrentDIYInfo = (DIYInfoBean) intent.getSerializableExtra("diyInfo");
        this.from = intent.getIntExtra(TypedValues.TransitionType.S_FROM, 1);
        this.mDeviceInfoData.postValue(this.mCurrentDevice);
        ArrayList arrayList = new ArrayList();
        SubColorBean subColorBean = new SubColorBean();
        subColorBean.setType(1);
        DIYInfoBean dIYInfoBean = this.mCurrentDIYInfo;
        if (dIYInfoBean == null) {
            arrayList.add(subColorBean);
            SubColorBean subColorBean2 = new SubColorBean();
            subColorBean2.setColorValue(JSON.toJSONString(initSubData()));
            arrayList.add(1, subColorBean2);
        } else {
            this.mDIYInfoData.postValue(dIYInfoBean);
            if (this.mCurrentDIYInfo.getSubColorList() == null) {
                arrayList.add(subColorBean);
            } else {
                if (this.mCurrentDIYInfo.getSubColorList().size() < Constants.MAX_DIY_SUB_NUM) {
                    arrayList.add(subColorBean);
                }
                arrayList.addAll(this.mCurrentDIYInfo.getSubColorList());
            }
            this.DIYEffect = this.mCurrentDIYInfo.getStyle();
            this.DIYSpeed = this.mCurrentDIYInfo.getTime();
        }
        this.mSubColorListData.postValue(arrayList);
    }

    public void setSaveResultObserver(LifecycleOwner lifecycleOwner, Observer<Boolean> observer) {
        this.mSaveResultData.observe(lifecycleOwner, observer);
    }

    public DIYInfoBean getCurrentDIYInfo() {
        return this.mCurrentDIYInfo;
    }

    public int getDIYEffect() {
        return this.DIYEffect;
    }

    public void setDIYEffect(int i) {
        this.DIYEffect = i;
    }

    public void setDIYSpeed(int i) {
        this.DIYSpeed = i;
    }

    public List<SubItemBean> initSubData() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 20; i++) {
            SubItemBean subItemBean = new SubItemBean();
            subItemBean.setColor(Color.parseColor("#FF000000"));
            arrayList.add(subItemBean);
        }
        return arrayList;
    }

    public void saveDIYSetting(String str, List<SubColorBean> list) {
        ArrayList arrayList = new ArrayList(list);
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SubColorBean subColorBean = (SubColorBean) it.next();
            if (subColorBean.getType() == 1) {
                arrayList.remove(subColorBean);
                break;
            }
        }
        if (this.mCurrentDIYInfo == null) {
            DIYInfoBean dIYInfoBean = new DIYInfoBean();
            this.mCurrentDIYInfo = dIYInfoBean;
            dIYInfoBean.setType(1);
        }
        this.mCurrentDIYInfo.setColorValue(JSON.toJSONString(arrayList));
        this.mCurrentDIYInfo.setName(str);
        this.mCurrentDIYInfo.setStyle(this.DIYEffect);
        this.mCurrentDIYInfo.setTime(this.DIYSpeed);
        this.mCurrentDIYInfo.setDiyType(this.from == 2 ? 1 : 0);
        if (this.from == 1) {
            this.mCurrentDIYInfo.setDeviceType(this.mCurrentDevice.getDeviceType());
        }
        this.mDIYDatabasePresenter.addDIYColor(this.mCurrentDIYInfo);
        if (this.from == 1 || this.mCurrentDevice != null) {
            sendDIYColor((byte) this.mCurrentDIYInfo.getStyle(), (byte) this.mCurrentDIYInfo.getTime(), arrayList);
        }
        TrackManager.getInstance().deviceDetailTrack(2, (DeviceInfoBean) null, 2);
    }

    public void updateDIYSetting(List<SubColorBean> list) {
        ArrayList arrayList = new ArrayList(list);
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SubColorBean subColorBean = (SubColorBean) it.next();
            if (subColorBean.getType() == 1) {
                arrayList.remove(subColorBean);
                break;
            }
        }
        this.mCurrentDIYInfo.setColorValue(JSON.toJSONString(arrayList));
        this.mCurrentDIYInfo.setStyle(this.DIYEffect);
        this.mCurrentDIYInfo.setTime(this.DIYSpeed);
        this.mDIYDatabasePresenter.updateDIYColor(this.mCurrentDIYInfo);
        if (this.from == 1 || this.mCurrentDevice != null) {
            sendDIYColor((byte) this.mCurrentDIYInfo.getStyle(), (byte) this.mCurrentDIYInfo.getTime(), arrayList);
        }
    }

    private void sendDIYColor(byte b2, byte b3, List<SubColorBean> list) {
        ArrayList arrayList = new ArrayList(list);
        Collections.reverse(arrayList);
        int size = (arrayList.size() * 60) + 4;
        byte[] bArr = new byte[size];
        bArr[0] = 1;
        bArr[1] = b2;
        bArr[2] = b3;
        bArr[3] = (byte) arrayList.size();
        for (int i = 0; i < arrayList.size(); i++) {
            List<SubItemBean> subColor = ColorUtils.getSubColor(((SubColorBean) arrayList.get(i)).getColorValue());
            for (int i2 = 0; i2 < subColor.size(); i2++) {
                int color = subColor.get(i2).getColor();
                int i3 = (i * 60) + (i2 * 3);
                bArr[i3 + 4] = (byte) (Color.red(color) & 255);
                bArr[i3 + 5] = (byte) (Color.green(color) & 255);
                bArr[i3 + 6] = (byte) (Color.blue(color) & 255);
            }
        }
        int mtu = this.mCurrentDevice.getMtu() - 8;
        if (size > mtu) {
            int ceil = (int) Math.ceil(((double) size) / ((double) mtu));
            for (int i4 = 0; i4 < ceil; i4++) {
                if (i4 == ceil - 1) {
                    int i5 = i4 * mtu;
                    int i6 = size - i5;
                    byte[] bArr2 = new byte[i6];
                    System.arraycopy(bArr, i5, bArr2, 0, i6);
                    sendData(CommandUtils.getSeqCommand((byte) 9, bArr2, (byte) -1, i6));
                } else {
                    byte[] bArr3 = new byte[mtu];
                    System.arraycopy(bArr, i4 * mtu, bArr3, 0, mtu);
                    sendData(CommandUtils.getSeqCommand((byte) 9, bArr3, (byte) (i4 + 1), mtu));
                }
            }
            return;
        }
        sendData(CommandUtils.getSeqCommand((byte) 9, bArr, (byte) -1, size));
    }

    public void sendData(byte[] bArr) {
        RGBDeviceManager.getInstance().sendCommandData(this.mCurrentDevice, bArr);
    }
}
