package com.mergbw.android.ui.factoryArea;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.mergbw.android.FactoryInfoManage;
import com.mergbw.android.databinding.ActivityFactoryAreaBinding;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.android.ui.factoryArea.adapter.FactoryPorductAdapter;
import com.mergbw.core.network.factory.FactoryDataHelper;
import com.mergbw.core.network.factory.bean.FactoryActivityInfoBean;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;
import com.mergbw.core.network.factory.bean.FactoryProductInfoBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.utils.MeRGBWLog;
import com.mergbw.core.utils.UIUtils;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import java.util.List;

public class FactoryAreaActivity extends BaseActivity {
    private ActivityFactoryAreaBinding mBinding;
    private String mCurrentSiteCode;
    private FactoryInfoBean mFactoryInfo;
    private FactoryPorductAdapter mItemAdapter;
    private FactoryDataViewModel mViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityFactoryAreaBinding inflate = ActivityFactoryAreaBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        initViews();
        subscribeUI();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new FactoryAreaActivity$$ExternalSyntheticLambda4(this));
        this.mBinding.viewBanner.setBannerRound(UIUtils.dp2px(12.0f));
        this.mBinding.listNewProduct.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        FactoryPorductAdapter factoryPorductAdapter = new FactoryPorductAdapter();
        this.mItemAdapter = factoryPorductAdapter;
        factoryPorductAdapter.setClickListener(new FactoryAreaActivity$$ExternalSyntheticLambda5(this));
        this.mBinding.listNewProduct.setAdapter(this.mItemAdapter);
        this.mBinding.layoutSiteList.setOnClickListener(new FactoryAreaActivity$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-factoryArea-FactoryAreaActivity  reason: not valid java name */
    public /* synthetic */ void m791lambda$initViews$0$commergbwandroiduifactoryAreaFactoryAreaActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-factoryArea-FactoryAreaActivity  reason: not valid java name */
    public /* synthetic */ void m792lambda$initViews$1$commergbwandroiduifactoryAreaFactoryAreaActivity(FactoryProductInfoBean.GoodsInfo goodsInfo) {
        if (goodsInfo != null && goodsInfo.getGoodsLink() != null) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(goodsInfo.getGoodsLink())));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-android-ui-factoryArea-FactoryAreaActivity  reason: not valid java name */
    public /* synthetic */ void m794lambda$initViews$3$commergbwandroiduifactoryAreaFactoryAreaActivity(View view) {
        FactoryInfoBean factoryInfoBean = this.mFactoryInfo;
        if (factoryInfoBean != null && factoryInfoBean.getSiteList() != null && !this.mFactoryInfo.getSiteList().isEmpty()) {
            new ChooseSiteDialog(this.mContext, this.mFactoryInfo.getSiteList(), this.mCurrentSiteCode, new FactoryAreaActivity$$ExternalSyntheticLambda3(this)).show();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-factoryArea-FactoryAreaActivity  reason: not valid java name */
    public /* synthetic */ void m793lambda$initViews$2$commergbwandroiduifactoryAreaFactoryAreaActivity(FactoryInfoBean.SiteInfo siteInfo) {
        this.mCurrentSiteCode = siteInfo.getSiteCode();
        FactoryInfoManage.getInstance().setHistorySiteCode(this.mFactoryInfo.getUserId(), siteInfo.getSiteCode());
        ((RequestBuilder) Glide.with(this.mContext).load(FactoryDataUtils.COUNTRY_FLAG.get(siteInfo.getSiteCode())).circleCrop()).into(this.mBinding.ivSite);
        showWaitDialog();
        this.mViewModel.loadData(siteInfo.getSiteCode());
    }

    private void subscribeUI() {
        FactoryDataViewModel factoryDataViewModel = (FactoryDataViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(FactoryDataViewModel.class);
        this.mViewModel = factoryDataViewModel;
        factoryDataViewModel.setFactoryInfoObserver(this, new FactoryAreaActivity$$ExternalSyntheticLambda0(this));
        this.mViewModel.setFactoryActivityListObserver(this, new FactoryAreaActivity$$ExternalSyntheticLambda1(this));
        this.mViewModel.setFactoryProductListObserver(this, new FactoryAreaActivity$$ExternalSyntheticLambda2(this));
        this.mViewModel.initData(getIntent());
    }

    /* access modifiers changed from: private */
    public void updateFactoryInfo(FactoryInfoBean factoryInfoBean) {
        this.mFactoryInfo = factoryInfoBean;
        if (factoryInfoBean != null) {
            this.mBinding.tvFactoryName.setText(factoryInfoBean.getNickName());
            RequestManager with = Glide.with(this.mContext);
            ((RequestBuilder) with.load(FactoryDataHelper.getFactoryFileAddress() + factoryInfoBean.getAvatar()).circleCrop()).into(this.mBinding.ivFactoryLogo);
            this.mCurrentSiteCode = this.mViewModel.getSiteCode();
            if (FactoryDataUtils.COUNTRY_FLAG.get(this.mCurrentSiteCode) != null) {
                ((RequestBuilder) Glide.with(this.mContext).load(FactoryDataUtils.COUNTRY_FLAG.get(this.mCurrentSiteCode)).circleCrop()).into(this.mBinding.ivSite);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateFactoryActivityList(List<FactoryActivityInfoBean> list) {
        hideWaitDialog();
        this.mBinding.viewBanner.addBannerLifecycleObserver(this).setAdapter(new BannerImageAdapter<FactoryActivityInfoBean>(list) {
            public void onBindView(BannerImageHolder bannerImageHolder, FactoryActivityInfoBean factoryActivityInfoBean, int i, int i2) {
                RequestManager with = Glide.with(bannerImageHolder.itemView);
                with.load(FactoryDataHelper.getFactoryFileAddress() + factoryActivityInfoBean.getActivityPicture()).into(bannerImageHolder.imageView);
            }
        }).setIndicator(new CircleIndicator(this)).setOnBannerListener(new OnBannerListener<FactoryActivityInfoBean>() {
            public void OnBannerClick(FactoryActivityInfoBean factoryActivityInfoBean, int i) {
                MeRGBWLog.i("OnBannerClick:" + factoryActivityInfoBean.getActivityLink());
                if (factoryActivityInfoBean.getActivityType().equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    JumpManager instance = JumpManager.getInstance();
                    String activityName = factoryActivityInfoBean.getActivityName();
                    instance.jumpToWebViewPage(activityName, FactoryDataHelper.getFactoryFileAddress() + factoryActivityInfoBean.getActivityLink());
                    return;
                }
                FactoryAreaActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(factoryActivityInfoBean.getActivityLink())));
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateFactoryProductList(List<FactoryProductInfoBean.GoodsInfo> list) {
        hideWaitDialog();
        this.mItemAdapter.setData(list);
    }
}
