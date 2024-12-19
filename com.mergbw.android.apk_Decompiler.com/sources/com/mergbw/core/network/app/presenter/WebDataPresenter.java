package com.mergbw.core.network.app.presenter;

import android.content.Context;
import com.mergbw.core.network.ApiList;
import com.mergbw.core.network.BasePresenter;
import com.mergbw.core.network.WebInterface;
import com.mergbw.core.network.app.NetworkHelper;
import com.mergbw.core.network.app.WebApi;
import com.mergbw.core.network.app.bean.CommonResult;
import com.mergbw.core.network.app.bean.FirmwareVersionBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WebDataPresenter extends BasePresenter {
    private Context mContext;
    /* access modifiers changed from: private */
    public final WebInterface mView;
    private final WebApi mWebApi = NetworkHelper.getInstance().getApi();

    public WebDataPresenter(Context context, WebInterface webInterface) {
        this.mContext = context;
        this.mView = webInterface;
    }

    public void getAppVersionInfo(int i) {
        this.mWebApi.getAppVersionInfo(i).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult<FirmwareVersionBean>>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult<FirmwareVersionBean> commonResult) {
                WebDataPresenter.this.mView.showResult(ApiList.GET_VERSION_INFO, commonResult);
            }

            public void onError(Throwable th) {
                WebDataPresenter.this.mView.showError(ApiList.GET_VERSION_INFO);
            }
        });
    }
}
