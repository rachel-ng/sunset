package com.mergbw.android.ui.addDevice;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.data.BleScanState;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.core.ble.BleCallback;
import com.mergbw.core.ble.CastDataBean;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.presenter.DeviceDatabasePresenter;
import com.mergbw.core.database.presenter.IDeviceDatabaseListener;
import com.mergbw.core.event.EventMessage;
import com.mergbw.core.event.GlobalEvent;
import com.mergbw.core.track.TrackManager;
import com.mergbw.core.utils.AppUtils;
import com.mergbw.core.utils.MeRGBWLog;
import com.mergbw.core.utils.StringUtils;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class AddDeviceViewModel extends AndroidViewModel {
    /* access modifiers changed from: private */
    public DeviceInfoBean mAddDevice;
    /* access modifiers changed from: private */
    public List<DeviceInfoBean> mAddedDevice;
    private final DeviceDatabasePresenter mDatabasePresenter;
    /* access modifiers changed from: private */
    public final List<DeviceInfoBean> mDeviceList = new ArrayList();
    /* access modifiers changed from: private */
    public AddDeviceInterView mView;

    public AddDeviceViewModel(Application application) {
        super(application);
        RGBDeviceManager.getInstance().setCallback("AddDeviceViewModel", new BleCallback() {
            public void onAlreadyConnected(BleDevice bleDevice) {
                if (AddDeviceViewModel.this.mAddDevice != null && AddDeviceViewModel.this.mAddDevice.getDeviceMac().equals(bleDevice.getMac())) {
                    RGBDeviceManager.getInstance().sendCommandData(bleDevice, CommandUtils.getCommand((byte) 14, AppUtils.getUniId()));
                }
            }

            public void onConnectedFailed(BleDevice bleDevice) {
                if (AddDeviceViewModel.this.mAddDevice != null && AddDeviceViewModel.this.mAddDevice.getDeviceMac().equals(bleDevice.getMac()) && AddDeviceViewModel.this.mView != null) {
                    AddDeviceViewModel.this.mView.updateBindResult(AddDeviceViewModel.this.mAddDevice, BindStatus.ERROR);
                }
            }

            public void onNotifySuccess(BleDevice bleDevice) {
                if (AddDeviceViewModel.this.mAddDevice != null && AddDeviceViewModel.this.mAddDevice.getDeviceMac().equals(bleDevice.getMac())) {
                    RGBDeviceManager.getInstance().sendCommandData(bleDevice, CommandUtils.getCommand((byte) 14, AppUtils.getUniId()));
                }
            }

            public void onNotifyData(BleDevice bleDevice, byte b2, byte[] bArr) {
                if (AddDeviceViewModel.this.mAddDevice != null && AddDeviceViewModel.this.mAddDevice.getDeviceMac().equals(bleDevice.getMac()) && b2 == 14) {
                    byte b3 = bArr[4];
                    if (b3 == 1) {
                        if (AddDeviceViewModel.this.mView != null) {
                            AddDeviceViewModel.this.mView.updateBindResult(AddDeviceViewModel.this.mAddDevice, BindStatus.BOUND);
                        }
                    } else if (b3 == 2) {
                        if (AddDeviceViewModel.this.mView != null) {
                            AddDeviceViewModel.this.mView.updateBindResult(AddDeviceViewModel.this.mAddDevice, BindStatus.UNBIND);
                        }
                    } else if (b3 == 3 && AddDeviceViewModel.this.mView != null) {
                        AddDeviceViewModel.this.mView.updateBindResult(AddDeviceViewModel.this.mAddDevice, BindStatus.TIMEOUT);
                    }
                }
            }
        });
        DeviceDatabasePresenter deviceDatabasePresenter = new DeviceDatabasePresenter(new IDeviceDatabaseListener() {
            public void onDeleteSuccess() {
            }

            public void onGetDeviceInfo(DeviceInfoBean deviceInfoBean) {
            }

            public void onUpdateSuccess() {
            }

            public void onGetDeviceList(List<DeviceInfoBean> list) {
                List unused = AddDeviceViewModel.this.mAddedDevice = list;
            }

            public void onAddSuccess() {
                if (AddDeviceViewModel.this.mView != null) {
                    AddDeviceViewModel.this.mView.updateAddResult(true);
                }
                EventBus.getDefault().post(new EventMessage(GlobalEvent.ADD_DEVICE_SUCCESS_ACTION));
            }
        });
        this.mDatabasePresenter = deviceDatabasePresenter;
        deviceDatabasePresenter.getDeviceList();
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        RGBDeviceManager.getInstance().removeCallback("AddDeviceViewModel");
    }

    public void setViewListener(AddDeviceInterView addDeviceInterView) {
        this.mView = addDeviceInterView;
    }

    public void scanDevice() {
        this.mDeviceList.clear();
        BleManager.getInstance().initScanRule(new BleScanRuleConfig.Builder().build());
        BleManager.getInstance().scan(new BleScanCallback() {
            public void onScanFinished(List<BleDevice> list) {
                if ((list.isEmpty() || AddDeviceViewModel.this.mDeviceList.isEmpty()) && AddDeviceViewModel.this.mView != null) {
                    AddDeviceViewModel.this.mView.updateScanResult(false);
                }
            }

            public void onScanStarted(boolean z) {
                if (!z && AddDeviceViewModel.this.mView != null) {
                    AddDeviceViewModel.this.mView.updateScanResult(false);
                }
            }

            public void onScanning(BleDevice bleDevice) {
                if (bleDevice.getName() != null && bleDevice.getMac() != null) {
                    for (DeviceInfoBean deviceMac : AddDeviceViewModel.this.mAddedDevice) {
                        if (bleDevice.getMac().equals(deviceMac.getDeviceMac())) {
                            return;
                        }
                    }
                    MeRGBWLog.i("device: " + bleDevice.getName() + " data: " + StringUtils.byte2HexStr(bleDevice.getScanRecord()));
                    List<CastDataBean> access$400 = AddDeviceViewModel.this.analyseCastData(bleDevice.getScanRecord());
                    for (CastDataBean castDataBean : access$400) {
                        if (castDataBean.getType() == 2 && castDataBean.getLen() == 3 && castDataBean.getData()[0] == 25 && castDataBean.getData()[1] == 53) {
                            DeviceInfoBean deviceInfoBean = new DeviceInfoBean();
                            deviceInfoBean.setDeviceName(bleDevice.getName());
                            deviceInfoBean.setDeviceMac(bleDevice.getMac());
                            AddDeviceViewModel.this.analyseFactoryInfo(deviceInfoBean, access$400);
                            AddDeviceViewModel.this.mDeviceList.add(deviceInfoBean);
                            if (AddDeviceViewModel.this.mView != null) {
                                AddDeviceViewModel.this.mView.updateDeviceList(AddDeviceViewModel.this.mDeviceList);
                                return;
                            }
                            return;
                        }
                    }
                }
            }
        });
    }

    public void cancelScan() {
        if (BleManager.getInstance().getScanSate() == BleScanState.STATE_SCANNING) {
            BleManager.getInstance().cancelScan();
        }
    }

    public void connectDevice(DeviceInfoBean deviceInfoBean) {
        this.mAddDevice = deviceInfoBean;
        RGBDeviceManager.getInstance().connectDevice(deviceInfoBean);
    }

    public void addDevice(DeviceInfoBean deviceInfoBean) {
        this.mDatabasePresenter.addDevice(deviceInfoBean);
        TrackManager.getInstance().addDeviceTrack(deviceInfoBean);
        GoogleTrackManager.getInstance().addDeviceTrack(deviceInfoBean);
    }

    /* access modifiers changed from: private */
    public void analyseFactoryInfo(DeviceInfoBean deviceInfoBean, List<CastDataBean> list) {
        for (CastDataBean next : list) {
            if (next.getType() == -1) {
                if (next.getData().length >= 11) {
                    deviceInfoBean.setDeviceType(next.getData()[10] & 255);
                }
                if (next.getData().length >= 21) {
                    byte[] bArr = new byte[10];
                    System.arraycopy(next.getData(), 11, bArr, 0, 10);
                    deviceInfoBean.setDeviceModel(new String(bArr, StandardCharsets.UTF_8));
                }
                if (next.getData().length >= 27) {
                    byte[] bArr2 = new byte[4];
                    System.arraycopy(next.getData(), 23, bArr2, 0, 4);
                    deviceInfoBean.setFactoryID(StringUtils.bytesToInt(bArr2));
                    return;
                }
                return;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: byte} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.mergbw.core.ble.CastDataBean> analyseCastData(byte[] r9) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = r1
        L_0x0007:
            int r3 = r9.length
            int r3 = r3 + -1
            if (r2 >= r3) goto L_0x0039
            byte r3 = r9[r2]
            if (r3 != 0) goto L_0x0013
            int r2 = r2 + 1
            goto L_0x0007
        L_0x0013:
            com.mergbw.core.ble.CastDataBean r4 = new com.mergbw.core.ble.CastDataBean
            r4.<init>()
            r4.setLen(r3)
            int r5 = r2 + 1
            byte r5 = r9[r5]
            r4.setType(r5)
            int r5 = r3 + -1
            byte[] r6 = new byte[r5]
            r4.setData(r6)
            int r6 = r2 + 2
            byte[] r7 = r4.getData()
            java.lang.System.arraycopy(r9, r6, r7, r1, r5)
            int r2 = r2 + r3
            int r2 = r2 + 1
            r0.add(r4)
            goto L_0x0007
        L_0x0039:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mergbw.android.ui.addDevice.AddDeviceViewModel.analyseCastData(byte[]):java.util.List");
    }
}
