package com.mergbw.core.network.factory;

import com.mergbw.core.network.factory.bean.FactoryActivityInfoBean;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;
import com.mergbw.core.network.factory.bean.FactoryProductInfoBean;
import java.util.List;

public abstract class FactoryDataCallback {
    public void onFactoryActivityResult(boolean z, String str, List<FactoryActivityInfoBean> list) {
    }

    public void onFactoryInfoResult(boolean z, String str, FactoryInfoBean factoryInfoBean) {
    }

    public void onFactoryProductResult(boolean z, String str, List<FactoryProductInfoBean.GoodsInfo> list) {
    }
}
