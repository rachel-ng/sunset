package com.mergbw.android.ui.deviceDetail.viewModel;

import android.app.Application;
import android.graphics.Color;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.alibaba.fastjson.JSON;
import com.mergbw.android.ui.addDevice.BindStatus;
import com.mergbw.core.Constants;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.bean.SubItemBean;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.database.bean.DIYInfoBean;
import com.mergbw.core.database.bean.SubColorBean;
import com.mergbw.core.database.presenter.DIYColorDatabasePresenter;
import com.mergbw.core.database.presenter.IDIYColorDatabaseListener;
import com.mergbw.core.database.presenter.ISubColorDatabaseListener;
import com.mergbw.core.database.presenter.SubColorDatabasePresenter;
import com.mergbw.core.utils.ColorUtils;
import com.mergbw.core.utils.MeRGBWLog;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeviceDetailViewModel1 extends BaseDeviceDetailViewModel {
    /* access modifiers changed from: private */
    public List<DIYInfoBean> mDIYColorList;
    private final DIYColorDatabasePresenter mDIYDatabasePresenter = new DIYColorDatabasePresenter(new IDIYColorDatabaseListener() {
        public void onAddDiy(Long l) {
        }

        public void onAddSuccess() {
        }

        public void onDeleteSuccess() {
        }

        public void onGetDIYColor(DIYInfoBean dIYInfoBean) {
        }

        public void onUpdateSuccess() {
        }

        public void onGetDIYColorList(List<DIYInfoBean> list) {
            List unused = DeviceDetailViewModel1.this.mDIYColorList = list;
            for (DIYInfoBean next : list) {
                next.setSubColorList(JSON.parseArray(next.getColorValue(), SubColorBean.class));
            }
            DeviceDetailViewModel1.this.mDIYListData.postValue(DeviceDetailViewModel1.this.mDIYColorList);
        }
    });
    public final MutableLiveData<List<DIYInfoBean>> mDIYListData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> mSendDIYResultData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> mSendSubResultData = new MutableLiveData<>();
    public final MutableLiveData<List<SubColorBean>> mSubColorListData = new MutableLiveData<>();
    private final SubColorDatabasePresenter mSubDatabasePresenter = new SubColorDatabasePresenter(new ISubColorDatabaseListener() {
        public void onAddSubColor(Long l) {
        }

        public void onAddSuccess() {
        }

        public void onDeleteSuccess() {
        }

        public void onGetSubColor(SubColorBean subColorBean) {
        }

        public void onUpdateSuccess() {
        }

        public void onGetSubColorList(List<SubColorBean> list) {
            DeviceDetailViewModel1.this.mSubColorListData.postValue(list);
        }
    });

    public DeviceDetailViewModel1(Application application) {
        super(application, "DeviceDetailViewModel1");
    }

    /* access modifiers changed from: protected */
    public void onBleResponse(byte b2, byte[] bArr) {
        if (b2 == 0) {
            MeRGBWLog.i("DeviceDetailViewModel onNotifyData");
            ViewDataUtils.analyseSyncData(this.mCurrentDevice, bArr);
            this.mDetailViewData.postValue(this.mCurrentDevice);
        }
        if (b2 == 14) {
            byte b3 = bArr[4];
            if (b3 == 1) {
                this.mCurrentDevice.setBound(true);
                this.mBindStatusData.postValue(BindStatus.BOUND);
            } else if (b3 == 2) {
                this.mCurrentDevice.setBound(false);
                this.mBindStatusData.postValue(BindStatus.UNBIND);
            } else if (b3 == 3) {
                this.mCurrentDevice.setBound(false);
                this.mBindStatusData.postValue(BindStatus.TIMEOUT);
            }
        }
        if (b2 == 4) {
            if (bArr[4] != 0) {
                this.mSendSubResultData.postValue(false);
            } else if (bArr[2] == -1) {
                this.mSendSubResultData.postValue(true);
            }
        }
        if (b2 != 9) {
            return;
        }
        if (bArr[4] != 0) {
            this.mSendDIYResultData.postValue(false);
        } else if (bArr[2] == -1) {
            this.mSendDIYResultData.postValue(true);
        }
    }

    public void setSubColorListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<SubColorBean>> observer) {
        this.mSubColorListData.observe(lifecycleOwner, observer);
        this.mSubDatabasePresenter.getColorList(this.mCurrentDevice.getDeviceType());
    }

    public void setSendSubResultObserver(LifecycleOwner lifecycleOwner, Observer<Boolean> observer) {
        this.mSendSubResultData.observe(lifecycleOwner, observer);
    }

    public void setSendDIYResultObserver(LifecycleOwner lifecycleOwner, Observer<Boolean> observer) {
        this.mSendDIYResultData.observe(lifecycleOwner, observer);
    }

    public void setDIYListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<DIYInfoBean>> observer) {
        this.mDIYListData.observe(lifecycleOwner, observer);
        this.mDIYDatabasePresenter.getDIYColorList(this.mCurrentDevice.getDeviceType());
    }

    public void setBrightness(int i) {
        super.setBrightness(i);
        sendData(CommandUtils.getCommand((byte) 5, CommandUtils.getByteArray((i + 5) * 10)));
    }

    public void setColor(int i) {
        super.setColor(i);
        sendData(CommandUtils.getCommand((byte) 3, ColorUtils.getHSVColorData(i)));
    }

    public void setScene(SceneData sceneData) {
        super.setScene(sceneData);
        sendData(CommandUtils.getCommand((byte) 6, CommandUtils.getByteArray(sceneData.getSceneIndex())));
        sendData(CommandUtils.getCommand((byte) 15, new byte[]{(byte) (sceneData.getSpeed() & 255)}));
    }

    public void setMusicModel(int i) {
        sendData(CommandUtils.getCommand((byte) 7, new byte[]{(byte) (i & 255)}));
    }

    public void setMusicSens(int i) {
        double d = ((((double) i) / 100.0d) * ((double) (100 - Constants.MIN_SENSITIVE_VALUE))) + ((double) Constants.MIN_SENSITIVE_VALUE);
        MeRGBWLog.i("setMusicSens musicSens: " + i + " senValue: " + d);
        sendData(CommandUtils.getCommand((byte) 8, new byte[]{(byte) (((int) d) & 255)}));
    }

    public void setSubColor(SubColorBean subColorBean) {
        List<SubItemBean> subColor = ColorUtils.getSubColor(subColorBean.getColorValue());
        byte[] bArr = new byte[61];
        bArr[0] = 1;
        for (int i = 0; i < subColor.size(); i++) {
            int color = subColor.get(i).getColor();
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

    public void updateSubColor(SubColorBean subColorBean) {
        if (subColorBean != null) {
            this.mSubDatabasePresenter.updateSubColor(subColorBean);
        }
    }

    public void deleteSubColor(SubColorBean subColorBean) {
        if (subColorBean != null) {
            this.mSubDatabasePresenter.deleteSubColor(subColorBean);
        }
    }

    public void setDIYColor(DIYInfoBean dIYInfoBean) {
        sendDIYColor(dIYInfoBean);
    }

    public void updateDIYColor(DIYInfoBean dIYInfoBean) {
        if (dIYInfoBean != null) {
            this.mDIYDatabasePresenter.updateDIYColor(dIYInfoBean);
        }
    }

    public void deleteDIYColor(DIYInfoBean dIYInfoBean) {
        if (dIYInfoBean != null) {
            this.mDIYDatabasePresenter.deleteDIYColor(dIYInfoBean);
        }
    }

    private void sendDIYColor(DIYInfoBean dIYInfoBean) {
        ArrayList arrayList = new ArrayList(dIYInfoBean.getSubColorList());
        Collections.reverse(arrayList);
        int size = (arrayList.size() * 60) + 4;
        byte[] bArr = new byte[size];
        bArr[0] = 1;
        bArr[1] = (byte) dIYInfoBean.getStyle();
        bArr[2] = (byte) dIYInfoBean.getTime();
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
}
