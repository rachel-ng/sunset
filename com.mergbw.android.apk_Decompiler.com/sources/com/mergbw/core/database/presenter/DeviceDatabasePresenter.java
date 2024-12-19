package com.mergbw.core.database.presenter;

import com.mergbw.core.database.AppDatabase;
import com.mergbw.core.database.bean.DeviceInfoBean;
import io.reactivex.functions.Action;
import java.util.List;

public class DeviceDatabasePresenter extends BaseDatabasePresenter {
    private final IDeviceDatabaseListener mListener;

    public DeviceDatabasePresenter(IDeviceDatabaseListener iDeviceDatabaseListener) {
        this.mListener = iDeviceDatabaseListener;
    }

    public void addDevice(DeviceInfoBean deviceInfoBean) {
        addDisposable(AppDatabase.getInstance().deviceInfoDao().insertDevice(deviceInfoBean), (Action) new DeviceDatabasePresenter$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addDevice$0$com-mergbw-core-database-presenter-DeviceDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m890lambda$addDevice$0$commergbwcoredatabasepresenterDeviceDatabasePresenter() throws Exception {
        IDeviceDatabaseListener iDeviceDatabaseListener = this.mListener;
        if (iDeviceDatabaseListener != null) {
            iDeviceDatabaseListener.onAddSuccess();
        }
    }

    public void getDeviceList() {
        addDisposable(AppDatabase.getInstance().deviceInfoDao().queryDeviceList(), new DeviceDatabasePresenter$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getDeviceList$1$com-mergbw-core-database-presenter-DeviceDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m893lambda$getDeviceList$1$commergbwcoredatabasepresenterDeviceDatabasePresenter(List list) throws Exception {
        IDeviceDatabaseListener iDeviceDatabaseListener = this.mListener;
        if (iDeviceDatabaseListener != null) {
            iDeviceDatabaseListener.onGetDeviceList(list);
        }
    }

    public void getDeviceInfo(String str) {
        addDisposable(AppDatabase.getInstance().deviceInfoDao().queryDeviceInfo(str), new DeviceDatabasePresenter$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getDeviceInfo$2$com-mergbw-core-database-presenter-DeviceDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m892lambda$getDeviceInfo$2$commergbwcoredatabasepresenterDeviceDatabasePresenter(DeviceInfoBean deviceInfoBean) throws Exception {
        IDeviceDatabaseListener iDeviceDatabaseListener = this.mListener;
        if (iDeviceDatabaseListener != null) {
            iDeviceDatabaseListener.onGetDeviceInfo(deviceInfoBean);
        }
    }

    public void deleteDevice(DeviceInfoBean deviceInfoBean) {
        addDisposable(AppDatabase.getInstance().deviceInfoDao().deleteDevice(deviceInfoBean), (Action) new DeviceDatabasePresenter$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$deleteDevice$3$com-mergbw-core-database-presenter-DeviceDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m891lambda$deleteDevice$3$commergbwcoredatabasepresenterDeviceDatabasePresenter() throws Exception {
        IDeviceDatabaseListener iDeviceDatabaseListener = this.mListener;
        if (iDeviceDatabaseListener != null) {
            iDeviceDatabaseListener.onDeleteSuccess();
        }
    }

    public void updateDevice(DeviceInfoBean deviceInfoBean) {
        addDisposable(AppDatabase.getInstance().deviceInfoDao().updateDevice(deviceInfoBean), (Action) new DeviceDatabasePresenter$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateDevice$4$com-mergbw-core-database-presenter-DeviceDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m894lambda$updateDevice$4$commergbwcoredatabasepresenterDeviceDatabasePresenter() throws Exception {
        IDeviceDatabaseListener iDeviceDatabaseListener = this.mListener;
        if (iDeviceDatabaseListener != null) {
            iDeviceDatabaseListener.onUpdateSuccess();
        }
    }
}
