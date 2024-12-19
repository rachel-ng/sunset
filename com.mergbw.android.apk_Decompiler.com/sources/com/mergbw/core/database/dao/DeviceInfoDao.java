package com.mergbw.core.database.dao;

import com.mergbw.core.database.bean.DeviceInfoBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.List;

public interface DeviceInfoDao {
    Completable deleteDevice(DeviceInfoBean deviceInfoBean);

    Completable insertDevice(DeviceInfoBean deviceInfoBean);

    Maybe<DeviceInfoBean> queryDeviceInfo(String str);

    Flowable<List<DeviceInfoBean>> queryDeviceList();

    Completable updateDevice(DeviceInfoBean deviceInfoBean);
}
