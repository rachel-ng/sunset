package com.mergbw.android.ui.home.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.clj.fastble.data.BleDevice;
import com.mergbw.core.ble.BleCallback;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.GroupItemBean;
import com.mergbw.core.database.presenter.DeviceDatabasePresenter;
import com.mergbw.core.database.presenter.GroupDatabasePresenter;
import com.mergbw.core.database.presenter.IDeviceDatabaseListener;
import com.mergbw.core.database.presenter.IGroupDatabaseListener;
import com.mergbw.core.utils.AppUtils;
import com.mergbw.core.utils.StringUtils;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private final DeviceDatabasePresenter mDatabasePresenter = new DeviceDatabasePresenter(new IDeviceDatabaseListener() {
        public void onAddSuccess() {
        }

        public void onDeleteSuccess() {
        }

        public void onGetDeviceInfo(DeviceInfoBean deviceInfoBean) {
        }

        public void onUpdateSuccess() {
        }

        public void onGetDeviceList(List<DeviceInfoBean> list) {
            List unused = HomeViewModel.this.mDeviceList = list;
            HomeViewModel.this.mDeviceListData.postValue(HomeViewModel.this.mDeviceList);
            for (DeviceInfoBean connectDevice : HomeViewModel.this.mDeviceList) {
                RGBDeviceManager.getInstance().connectDevice(connectDevice);
            }
        }
    });
    /* access modifiers changed from: private */
    public List<DeviceInfoBean> mDeviceList = new ArrayList();
    /* access modifiers changed from: private */
    public final MutableLiveData<List<DeviceInfoBean>> mDeviceListData = new MutableLiveData<>();
    private final GroupDatabasePresenter mGroupDatabasePresenter;
    /* access modifiers changed from: private */
    public final MutableLiveData<List<GroupItemBean>> mGroupListData = new MutableLiveData<>();

    public HomeViewModel(Application application) {
        super(application);
        RGBDeviceManager.getInstance().setCallback("HomeViewModel", new BleCallback() {
            public void onAlreadyConnected(BleDevice bleDevice) {
                Iterator it = HomeViewModel.this.mDeviceList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DeviceInfoBean deviceInfoBean = (DeviceInfoBean) it.next();
                    if (deviceInfoBean.getDeviceMac().equals(bleDevice.getMac())) {
                        deviceInfoBean.setConnect(true);
                        deviceInfoBean.setMtu(RGBDeviceManager.getInstance().getMtu(deviceInfoBean));
                        HomeViewModel.this.deviceInitData(deviceInfoBean);
                        break;
                    }
                }
                HomeViewModel.this.mDeviceListData.postValue(HomeViewModel.this.mDeviceList);
            }

            public void onConnected(BleDevice bleDevice) {
                Iterator it = HomeViewModel.this.mDeviceList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DeviceInfoBean deviceInfoBean = (DeviceInfoBean) it.next();
                    if (deviceInfoBean.getDeviceMac().equals(bleDevice.getMac())) {
                        deviceInfoBean.setConnect(true);
                        break;
                    }
                }
                HomeViewModel.this.mDeviceListData.postValue(HomeViewModel.this.mDeviceList);
            }

            public void onDisconnected(BleDevice bleDevice) {
                Iterator it = HomeViewModel.this.mDeviceList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DeviceInfoBean deviceInfoBean = (DeviceInfoBean) it.next();
                    if (deviceInfoBean.getDeviceMac().equals(bleDevice.getMac())) {
                        deviceInfoBean.setConnect(false);
                        deviceInfoBean.setOpen(false);
                        break;
                    }
                }
                HomeViewModel.this.mDeviceListData.postValue(HomeViewModel.this.mDeviceList);
            }

            public void onNotifySuccess(BleDevice bleDevice) {
                for (DeviceInfoBean deviceInfoBean : HomeViewModel.this.mDeviceList) {
                    if (deviceInfoBean.getDeviceMac().equals(bleDevice.getMac())) {
                        HomeViewModel.this.deviceInitData(deviceInfoBean);
                        return;
                    }
                }
            }

            public void onNotifyData(BleDevice bleDevice, byte b2, byte[] bArr) {
                for (DeviceInfoBean deviceInfoBean : HomeViewModel.this.mDeviceList) {
                    if (deviceInfoBean.getDeviceMac().equals(bleDevice.getMac())) {
                        if (b2 == 14) {
                            if (deviceInfoBean.getDeviceType() == 1) {
                                byte b3 = bArr[4];
                                if (b3 == 1) {
                                    deviceInfoBean.setBound(true);
                                    HomeViewModel.this.syncInfo(deviceInfoBean);
                                } else if (b3 == 2 || b3 == 3) {
                                    deviceInfoBean.setBound(false);
                                }
                            } else if (deviceInfoBean.getDeviceType() == 6 && bArr[4] == 0) {
                                deviceInfoBean.setBound(true);
                                HomeViewModel.this.syncInfo(deviceInfoBean);
                            }
                            HomeViewModel.this.mDeviceListData.postValue(HomeViewModel.this.mDeviceList);
                        }
                        if (b2 == 0) {
                            ViewDataUtils.analyseSyncData(deviceInfoBean, bArr);
                            HomeViewModel.this.mDeviceListData.postValue(HomeViewModel.this.mDeviceList);
                        }
                        if (b2 == 1) {
                            RGBDeviceManager.getInstance().sendCommandData(bleDevice, CommandUtils.getCommand((byte) 0, (byte[]) null));
                            return;
                        }
                        return;
                    }
                }
            }

            public void onMtuChanged(BleDevice bleDevice, boolean z, int i) {
                for (DeviceInfoBean deviceInfoBean : HomeViewModel.this.mDeviceList) {
                    if (deviceInfoBean.getDeviceMac().equals(bleDevice.getMac())) {
                        deviceInfoBean.setMtu(i);
                        return;
                    }
                }
            }
        });
        this.mGroupDatabasePresenter = new GroupDatabasePresenter(new IGroupDatabaseListener() {
            public void onAddSuccess() {
            }

            public void onDeleteSuccess() {
            }

            public void onGetGroupInfo(GroupItemBean groupItemBean) {
            }

            public void onUpdateSuccess() {
            }

            public void onGetGroupList(List<GroupItemBean> list) {
                HomeViewModel.this.mGroupListData.postValue(list);
            }
        });
    }

    /* access modifiers changed from: private */
    public void deviceInitData(DeviceInfoBean deviceInfoBean) {
        int deviceType = deviceInfoBean.getDeviceType();
        if (deviceType == 1) {
            RGBDeviceManager.getInstance().sendCommandData(deviceInfoBean, CommandUtils.getCommand((byte) 14, AppUtils.getUniId()));
        } else if (deviceType == 5) {
            deviceInfoBean.setBound(true);
            syncInfo(deviceInfoBean);
        } else if (deviceType == 6) {
            RGBDeviceManager.getInstance().sendCommandData(deviceInfoBean, CommandUtils.getCommand((byte) 14, (byte[]) null));
        }
    }

    /* access modifiers changed from: private */
    public void syncInfo(DeviceInfoBean deviceInfoBean) {
        RGBDeviceManager.getInstance().sendCommandData(deviceInfoBean, CommandUtils.getCommand((byte) 0, (byte[]) null));
        Calendar instance = Calendar.getInstance();
        RGBDeviceManager.getInstance().sendCommandData(deviceInfoBean, CommandUtils.getCommand((byte) 12, new byte[]{(byte) ((instance.get(1) >> 8) & 255), (byte) (instance.get(1) & 255), (byte) ((instance.get(2) + 1) & 255), (byte) (instance.get(5) & 255), (byte) (instance.get(11) & 255), (byte) (instance.get(12) & 255), (byte) (instance.get(13) & 255), (byte) (StringUtils.getWeekInt() & 255)}));
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        RGBDeviceManager.getInstance().removeCallback("HomeViewModel");
    }

    public void setDeviceListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<DeviceInfoBean>> observer) {
        this.mDeviceListData.observe(lifecycleOwner, observer);
        this.mDatabasePresenter.getDeviceList();
    }

    public void setGroupListDataObserver(LifecycleOwner lifecycleOwner, Observer<List<GroupItemBean>> observer) {
        this.mGroupListData.observe(lifecycleOwner, observer);
        this.mGroupDatabasePresenter.getGroupListWithFlowable();
    }

    public void sendData(DeviceInfoBean deviceInfoBean, byte[] bArr) {
        RGBDeviceManager.getInstance().sendCommandData(deviceInfoBean, bArr);
    }
}
