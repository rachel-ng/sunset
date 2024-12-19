package com.mergbw.android.ui.factoryArea;

import android.app.Application;
import android.content.Intent;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.mergbw.android.FactoryInfoManage;
import com.mergbw.core.network.factory.FactoryDataCallback;
import com.mergbw.core.network.factory.FactoryDataPresenter;
import com.mergbw.core.network.factory.bean.FactoryActivityInfoBean;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;
import com.mergbw.core.network.factory.bean.FactoryProductInfoBean;
import com.mergbw.core.utils.MeRGBWLog;
import java.util.List;

public class FactoryDataViewModel extends AndroidViewModel {
    private final FactoryDataPresenter mDataPresenter = new FactoryDataPresenter(new FactoryDataCallback() {
        public void onFactoryInfoResult(boolean z, String str, FactoryInfoBean factoryInfoBean) {
            super.onFactoryInfoResult(z, str, factoryInfoBean);
            if (z && factoryInfoBean != null) {
                FactoryDataViewModel.this.mFactoryInfoData.postValue(factoryInfoBean);
            }
        }

        public void onFactoryActivityResult(boolean z, String str, List<FactoryActivityInfoBean> list) {
            super.onFactoryActivityResult(z, str, list);
            if (z && list != null && !list.isEmpty()) {
                FactoryDataViewModel.this.mFactoryActivityListData.postValue(list);
            }
        }

        public void onFactoryProductResult(boolean z, String str, List<FactoryProductInfoBean.GoodsInfo> list) {
            super.onFactoryProductResult(z, str, list);
            if (z && list != null && !list.isEmpty()) {
                FactoryDataViewModel.this.mFactoryProductListData.postValue(list);
            }
        }
    });
    /* access modifiers changed from: private */
    public final MutableLiveData<List<FactoryActivityInfoBean>> mFactoryActivityListData = new MutableLiveData<>();
    private FactoryInfoBean mFactoryInfo;
    /* access modifiers changed from: private */
    public final MutableLiveData<FactoryInfoBean> mFactoryInfoData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final MutableLiveData<List<FactoryProductInfoBean.GoodsInfo>> mFactoryProductListData = new MutableLiveData<>();

    public FactoryDataViewModel(Application application) {
        super(application);
    }

    public void initData(Intent intent) {
        FactoryInfoBean factoryInfoBean = (FactoryInfoBean) intent.getSerializableExtra("factoryInfo");
        this.mFactoryInfo = factoryInfoBean;
        if (factoryInfoBean != null) {
            this.mFactoryInfoData.postValue(factoryInfoBean);
            loadData(getSiteCode());
        }
    }

    public void loadData(String str) {
        this.mDataPresenter.getFactoryActivityInfo(this.mFactoryInfo.getUserId(), str);
        this.mDataPresenter.getFactoryProductInfo(this.mFactoryInfo.getUserId(), str);
    }

    public void setFactoryInfoObserver(LifecycleOwner lifecycleOwner, Observer<FactoryInfoBean> observer) {
        this.mFactoryInfoData.observe(lifecycleOwner, observer);
    }

    public void setFactoryActivityListObserver(LifecycleOwner lifecycleOwner, Observer<List<FactoryActivityInfoBean>> observer) {
        this.mFactoryActivityListData.observe(lifecycleOwner, observer);
    }

    public void setFactoryProductListObserver(LifecycleOwner lifecycleOwner, Observer<List<FactoryProductInfoBean.GoodsInfo>> observer) {
        this.mFactoryProductListData.observe(lifecycleOwner, observer);
    }

    public String getSiteCode() {
        MeRGBWLog.i("getSiteCode IP Site: " + this.mFactoryInfo.getSiteCode());
        if (this.mFactoryInfo.getSiteCode() != null) {
            for (FactoryInfoBean.SiteInfo next : this.mFactoryInfo.getSiteList()) {
                if (this.mFactoryInfo.getSiteCode().equals(next.getSiteCode())) {
                    return next.getSiteCode();
                }
            }
        }
        String historySiteCode = FactoryInfoManage.getInstance().getHistorySiteCode(this.mFactoryInfo.getUserId());
        if (historySiteCode != null) {
            for (FactoryInfoBean.SiteInfo next2 : this.mFactoryInfo.getSiteList()) {
                if (historySiteCode.equals(next2.getSiteCode())) {
                    return next2.getSiteCode();
                }
            }
        }
        if (this.mFactoryInfo.getSiteList() == null || this.mFactoryInfo.getSiteList().isEmpty()) {
            return "US";
        }
        return this.mFactoryInfo.getSiteList().get(0).getSiteCode();
    }
}
