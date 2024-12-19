package com.mergbw.core.network.factory;

import com.mergbw.core.network.BasePresenter;
import com.mergbw.core.network.factory.bean.BaseResult;
import com.mergbw.core.network.factory.bean.FactoryActivityInfoBean;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;
import com.mergbw.core.network.factory.bean.FactoryProductInfoBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class FactoryDataPresenter extends BasePresenter {
    /* access modifiers changed from: private */
    public final FactoryDataCallback mCallback;
    private final FactoryDataApi mWebApi = FactoryDataHelper.getInstance().getApi();

    public FactoryDataPresenter(FactoryDataCallback factoryDataCallback) {
        this.mCallback = factoryDataCallback;
    }

    public void getFactoryInfo(int i) {
        this.mWebApi.getFactoryInfo(i).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseResult<FactoryInfoBean>>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(BaseResult<FactoryInfoBean> baseResult) {
                if (baseResult.getCode() == 200) {
                    FactoryDataPresenter.this.mCallback.onFactoryInfoResult(true, baseResult.getMsg(), baseResult.getData());
                }
            }

            public void onError(Throwable th) {
                FactoryDataPresenter.this.mCallback.onFactoryInfoResult(false, "", (FactoryInfoBean) null);
            }
        });
    }

    public void getFactoryActivityInfo(int i, String str) {
        this.mWebApi.getFactoryActivityInfo(i, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseResult<List<FactoryActivityInfoBean>>>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(BaseResult<List<FactoryActivityInfoBean>> baseResult) {
                if (baseResult.getCode() == 200) {
                    FactoryDataPresenter.this.mCallback.onFactoryActivityResult(true, baseResult.getMsg(), baseResult.getData());
                }
            }

            public void onError(Throwable th) {
                FactoryDataPresenter.this.mCallback.onFactoryActivityResult(false, "", (List<FactoryActivityInfoBean>) null);
            }
        });
    }

    public void getFactoryProductInfo(int i, String str) {
        this.mWebApi.getFactoryProductInfo(i, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseResult<List<FactoryProductInfoBean>>>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(BaseResult<List<FactoryProductInfoBean>> baseResult) {
                if (baseResult.getCode() == 200 && baseResult.getData() != null && !baseResult.getData().isEmpty() && ((FactoryProductInfoBean) baseResult.getData().get(0)).getGoodsList() != null && !((FactoryProductInfoBean) baseResult.getData().get(0)).getGoodsList().isEmpty()) {
                    FactoryDataPresenter.this.mCallback.onFactoryProductResult(true, baseResult.getMsg(), ((FactoryProductInfoBean) baseResult.getData().get(0)).getGoodsList());
                }
            }

            public void onError(Throwable th) {
                FactoryDataPresenter.this.mCallback.onFactoryProductResult(false, "", (List<FactoryProductInfoBean.GoodsInfo>) null);
            }
        });
    }
}
