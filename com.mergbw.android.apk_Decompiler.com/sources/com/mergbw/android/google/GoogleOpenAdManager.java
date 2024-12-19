package com.mergbw.android.google;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.mergbw.android.UserinfoManage;
import com.mergbw.core.utils.MeRGBWLog;
import java.util.Date;

public class GoogleOpenAdManager {
    private static final String AD_UNIT_ID = "ca-app-pub-1990806801257609/8544275510";
    private static GoogleOpenAdManager instance;
    /* access modifiers changed from: private */
    public AppOpenAd appOpenAd = null;
    /* access modifiers changed from: private */
    public boolean isLoadingAd = false;
    public boolean isShowingAd = false;
    /* access modifiers changed from: private */
    public long loadTime = 0;

    public interface OnShowAdCompleteListener {
        void onShowAdComplete();

        void onShowAdFailed();

        void onShowAdSuccess();
    }

    public static GoogleOpenAdManager getInstance() {
        if (instance == null) {
            instance = new GoogleOpenAdManager();
        }
        return instance;
    }

    /* access modifiers changed from: private */
    public void loadAd(Context context) {
        if (!this.isLoadingAd) {
            this.isLoadingAd = true;
            AppOpenAd.load(context, AD_UNIT_ID, new AdRequest.Builder().build(), new AppOpenAd.AppOpenAdLoadCallback() {
                public void onAdLoaded(AppOpenAd appOpenAd) {
                    AppOpenAd unused = GoogleOpenAdManager.this.appOpenAd = appOpenAd;
                    boolean unused2 = GoogleOpenAdManager.this.isLoadingAd = false;
                    long unused3 = GoogleOpenAdManager.this.loadTime = new Date().getTime();
                    MeRGBWLog.i("onAdLoaded.");
                }

                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    boolean unused = GoogleOpenAdManager.this.isLoadingAd = false;
                    MeRGBWLog.e("onAdFailedToLoad: " + loadAdError.getMessage());
                }
            });
        }
    }

    private boolean wasLoadTimeLessThanNHoursAgo() {
        return new Date().getTime() - this.loadTime < 14400000;
    }

    private boolean showTimeLimit(int i) {
        return new Date().getTime() - UserinfoManage.getInstance().getLastAdShowTime() >= ((long) i) * 3600000;
    }

    private boolean isAdAvailable() {
        return this.appOpenAd != null && wasLoadTimeLessThanNHoursAgo() && showTimeLimit(GoogleRemoteConfigManager.getInstance().getShowAdHourLimit());
    }

    public void showAdIfAvailable(Activity activity) {
        showAdIfAvailable(activity, new OnShowAdCompleteListener() {
            public void onShowAdComplete() {
            }

            public void onShowAdFailed() {
            }

            public void onShowAdSuccess() {
            }
        });
    }

    public void showAdIfAvailable(final Activity activity, final OnShowAdCompleteListener onShowAdCompleteListener) {
        if (!GoogleRemoteConfigManager.getInstance().isShowOpenAd()) {
            MeRGBWLog.i("can not show open ad");
            onShowAdCompleteListener.onShowAdFailed();
        } else if (this.isShowingAd) {
            MeRGBWLog.i("The app open ad is already showing.");
            onShowAdCompleteListener.onShowAdFailed();
        } else if (!isAdAvailable()) {
            MeRGBWLog.e("The app open ad is not ready yet.");
            loadAd(activity);
            onShowAdCompleteListener.onShowAdFailed();
        } else {
            MeRGBWLog.i("Will show ad.");
            this.appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                public void onAdDismissedFullScreenContent() {
                    AppOpenAd unused = GoogleOpenAdManager.this.appOpenAd = null;
                    GoogleOpenAdManager.this.isShowingAd = false;
                    MeRGBWLog.i("onAdDismissedFullScreenContent.");
                    onShowAdCompleteListener.onShowAdComplete();
                    GoogleOpenAdManager.this.loadAd(activity);
                }

                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    AppOpenAd unused = GoogleOpenAdManager.this.appOpenAd = null;
                    GoogleOpenAdManager.this.isShowingAd = false;
                    MeRGBWLog.e("onAdFailedToShowFullScreenContent: " + adError.getMessage());
                    onShowAdCompleteListener.onShowAdFailed();
                    GoogleOpenAdManager.this.loadAd(activity);
                }

                public void onAdShowedFullScreenContent() {
                    onShowAdCompleteListener.onShowAdSuccess();
                    MeRGBWLog.i("onAdShowedFullScreenContent.");
                    UserinfoManage.getInstance().setLastAdShowTime(new Date().getTime());
                }
            });
            this.isShowingAd = true;
            this.appOpenAd.show(activity);
        }
    }
}
