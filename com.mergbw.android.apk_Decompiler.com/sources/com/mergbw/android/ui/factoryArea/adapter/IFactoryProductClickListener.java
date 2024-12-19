package com.mergbw.android.ui.factoryArea.adapter;

import com.mergbw.core.network.factory.bean.FactoryProductInfoBean;

public interface IFactoryProductClickListener {
    void clickProductItem(FactoryProductInfoBean.GoodsInfo goodsInfo);
}
