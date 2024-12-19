package com.mergbw.core.network.app.presenter;

import android.content.Context;
import com.mergbw.core.network.BasePresenter;
import com.mergbw.core.network.WebInterface;
import com.mergbw.core.network.app.NetworkHelper;
import com.mergbw.core.network.app.WebApi;
import com.mergbw.core.network.app.bean.CommonResult;
import com.mergbw.core.network.app.bean.DeviceOverViewBean;
import com.mergbw.core.network.app.bean.UserOverViewBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrackDataPresenter extends BasePresenter {
    private Context mContext;
    /* access modifiers changed from: private */
    public final WebInterface mView;
    private final WebApi mWebApi = NetworkHelper.getInstance().getApi();

    public TrackDataPresenter(Context context, WebInterface webInterface) {
        this.mContext = context;
        this.mView = webInterface;
    }

    public void getUserOverView() {
        this.mWebApi.getUserOverView().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult<UserOverViewBean>>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult<UserOverViewBean> commonResult) {
                TrackDataPresenter.this.mView.showResult(401, commonResult);
            }

            public void onError(Throwable th) {
                TrackDataPresenter.this.mView.showError(401);
            }
        });
    }

    public void getDeviceOverView() {
        this.mWebApi.getDeviceOverView().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult<DeviceOverViewBean>>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult<DeviceOverViewBean> commonResult) {
                TrackDataPresenter.this.mView.showResult(402, commonResult);
            }

            public void onError(Throwable th) {
                TrackDataPresenter.this.mView.showError(402);
            }
        });
    }
}
