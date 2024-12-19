package com.mergbw.android.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.mergbw.android.R;
import com.mergbw.android.databinding.ActivityMainBinding;
import com.mergbw.android.google.GoogleRemoteConfigManager;
import com.mergbw.android.ui.home.fragment.HomePageFragment;
import com.mergbw.android.ui.home.fragment.UserPageFragment;
import com.mergbw.core.ui.BaseBluetoothActivity;
import com.mergbw.core.ui.BaseFragment;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends BaseBluetoothActivity {
    private static final String AD_UNIT_ID = "ca-app-pub-1990806801257609/1753227141";
    public static final String TEST_DEVICE_HASHED_ID = "2C8F0266781DFC249CD1BF6FE51E9D0B";
    private AdView adView;
    private final AtomicBoolean initialLayoutComplete = new AtomicBoolean(false);
    private final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);
    private ActivityMainBinding mBinding;
    private BaseFragment mCurrentFragment;
    private HomePageFragment mHomePage;
    private UserPageFragment mUserPage;

    static /* synthetic */ void lambda$initializeMobileAdsSdk$2(InitializationStatus initializationStatus) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityMainBinding inflate = ActivityMainBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        initViews();
        this.mBinding.getRoot().post(new MainActivity$$ExternalSyntheticLambda4(this));
    }

    private void initViews() {
        this.mBinding.rgBottomBar.setOnCheckedChangeListener(new MainActivity$$ExternalSyntheticLambda2(this));
        this.mHomePage = new HomePageFragment();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.layout_content, this.mHomePage);
        beginTransaction.commit();
        this.mCurrentFragment = this.mHomePage;
        if (GoogleRemoteConfigManager.getInstance().isShowBannerAd()) {
            this.mBinding.adViewContainer.getViewTreeObserver().addOnGlobalLayoutListener(new MainActivity$$ExternalSyntheticLambda3(this));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-home-MainActivity  reason: not valid java name */
    public /* synthetic */ void m850lambda$initViews$0$commergbwandroiduihomeMainActivity(RadioGroup radioGroup, int i) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.rb_home_page) {
            if (this.mHomePage == null) {
                this.mHomePage = new HomePageFragment();
            }
            switchContent(this.mHomePage);
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_user_page) {
            if (this.mUserPage == null) {
                this.mUserPage = new UserPageFragment();
            }
            switchContent(this.mUserPage);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-home-MainActivity  reason: not valid java name */
    public /* synthetic */ void m851lambda$initViews$1$commergbwandroiduihomeMainActivity() {
        if (!this.initialLayoutComplete.getAndSet(true)) {
            loadBanner();
        }
    }

    /* access modifiers changed from: protected */
    public void bluetoothReady() {
        HomePageFragment homePageFragment = this.mHomePage;
        if (homePageFragment != null) {
            homePageFragment.bluetoothReady();
        }
    }

    public void switchContent(BaseFragment baseFragment) {
        if (this.mCurrentFragment != baseFragment) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            if (!baseFragment.isAdded()) {
                beginTransaction.hide(this.mCurrentFragment).add(R.id.layout_content, (Fragment) baseFragment).commit();
            } else {
                beginTransaction.hide(this.mCurrentFragment).show(baseFragment).commit();
            }
            this.mCurrentFragment = baseFragment;
        }
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onPause() {
        AdView adView2 = this.adView;
        if (adView2 != null) {
            adView2.pause();
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        AdView adView2 = this.adView;
        if (adView2 != null) {
            adView2.resume();
        }
    }

    public void onDestroy() {
        AdView adView2 = this.adView;
        if (adView2 != null) {
            adView2.destroy();
        }
        super.onDestroy();
    }

    private void initializeMobileAdsSdk() {
        if (!this.isMobileAdsInitializeCalled.getAndSet(true)) {
            MobileAds.setRequestConfiguration(new RequestConfiguration.Builder().setTestDeviceIds(Collections.singletonList(TEST_DEVICE_HASHED_ID)).build());
            new Thread(new MainActivity$$ExternalSyntheticLambda7(this)).start();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initializeMobileAdsSdk$4$com-mergbw-android-ui-home-MainActivity  reason: not valid java name */
    public /* synthetic */ void m853lambda$initializeMobileAdsSdk$4$commergbwandroiduihomeMainActivity() {
        MobileAds.initialize(this, new MainActivity$$ExternalSyntheticLambda5());
        runOnUiThread(new MainActivity$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initializeMobileAdsSdk$3$com-mergbw-android-ui-home-MainActivity  reason: not valid java name */
    public /* synthetic */ void m852lambda$initializeMobileAdsSdk$3$commergbwandroiduihomeMainActivity() {
        if (this.initialLayoutComplete.get()) {
            loadBanner();
        }
    }

    private void loadBanner() {
        AdView adView2 = new AdView(this);
        this.adView = adView2;
        adView2.setAdUnitId(AD_UNIT_ID);
        this.adView.setAdSize(getAdSize());
        this.mBinding.adViewContainer.removeAllViews();
        this.mBinding.adViewContainer.addView(this.adView);
        this.adView.loadAd(new AdRequest.Builder().build());
    }

    public AdSize getAdSize() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        if (Build.VERSION.SDK_INT >= 30) {
            i = getWindowManager().getCurrentWindowMetrics().getBounds().width();
        }
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, (int) (((float) i) / displayMetrics.density));
    }
}
