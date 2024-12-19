package com.mergbw.core.network.app.presenter;

import android.content.Context;
import com.alibaba.fastjson.JSON;
import com.mergbw.core.network.BasePresenter;
import com.mergbw.core.network.WebInterface;
import com.mergbw.core.network.app.NetworkHelper;
import com.mergbw.core.network.app.WebApi;
import com.mergbw.core.network.app.bean.CommonResult;
import com.mergbw.core.network.app.bean.DeviceModelInfo;
import com.mergbw.core.network.app.bean.DeviceTypeInfo;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BaseDataPresenter extends BasePresenter {
    private Context mContext;
    /* access modifiers changed from: private */
    public final WebInterface mView;
    private final WebApi mWebApi = NetworkHelper.getInstance().getApi();

    public BaseDataPresenter(Context context, WebInterface webInterface) {
        this.mContext = context;
        this.mView = webInterface;
    }

    public void getDeviceTypeList() {
        this.mWebApi.getDeviceTypeList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult<List<DeviceTypeInfo>>>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult<List<DeviceTypeInfo>> commonResult) {
                BaseDataPresenter.this.mView.showResult(501, commonResult);
            }

            public void onError(Throwable th) {
                BaseDataPresenter.this.mView.showError(501);
            }
        });
    }

    public void addDeviceType(DeviceTypeInfo deviceTypeInfo) {
        this.mWebApi.addDeviceType(RequestBody.Companion.create(JSON.toJSONString(deviceTypeInfo), MediaType.parse("application/json; charset=utf-8"))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult commonResult) {
                BaseDataPresenter.this.mView.showResult(502, commonResult);
            }

            public void onError(Throwable th) {
                BaseDataPresenter.this.mView.showError(502);
            }
        });
    }

    public void updateDeviceType(DeviceTypeInfo deviceTypeInfo) {
        this.mWebApi.updateDeviceType(RequestBody.Companion.create(JSON.toJSONString(deviceTypeInfo), MediaType.parse("application/json; charset=utf-8"))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult commonResult) {
                BaseDataPresenter.this.mView.showResult(503, commonResult);
            }

            public void onError(Throwable th) {
                BaseDataPresenter.this.mView.showError(503);
            }
        });
    }

    public void deleteDeviceType(int i) {
        this.mWebApi.deleteDeviceType(i).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult commonResult) {
                BaseDataPresenter.this.mView.showResult(504, commonResult);
            }

            public void onError(Throwable th) {
                BaseDataPresenter.this.mView.showError(504);
            }
        });
    }

    public void getDeviceModelList() {
        this.mWebApi.getDeviceModelList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult<List<DeviceModelInfo>>>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult<List<DeviceModelInfo>> commonResult) {
                BaseDataPresenter.this.mView.showResult(505, commonResult);
            }

            public void onError(Throwable th) {
                BaseDataPresenter.this.mView.showError(505);
            }
        });
    }

    public void addDeviceModel(DeviceModelInfo deviceModelInfo) {
        this.mWebApi.addDeviceModel(RequestBody.Companion.create(JSON.toJSONString(deviceModelInfo), MediaType.parse("application/json; charset=utf-8"))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult commonResult) {
                BaseDataPresenter.this.mView.showResult(506, commonResult);
            }

            public void onError(Throwable th) {
                BaseDataPresenter.this.mView.showError(506);
            }
        });
    }

    public void updateDeviceModel(DeviceModelInfo deviceModelInfo) {
        this.mWebApi.updateDeviceModel(RequestBody.Companion.create(JSON.toJSONString(deviceModelInfo), MediaType.parse("application/json; charset=utf-8"))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult commonResult) {
                BaseDataPresenter.this.mView.showResult(507, commonResult);
            }

            public void onError(Throwable th) {
                BaseDataPresenter.this.mView.showError(507);
            }
        });
    }

    public void deleteDeviceModel(int i) {
        this.mWebApi.deleteDeviceModel(i).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult commonResult) {
                BaseDataPresenter.this.mView.showResult(508, commonResult);
            }

            public void onError(Throwable th) {
                BaseDataPresenter.this.mView.showError(508);
            }
        });
    }
}
