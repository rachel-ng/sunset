package com.google.ads.mediation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.internal.util.client.zzf;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.zza;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class AbstractAdViewAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter, OnImmersiveModeUpdatedListener, zza {
    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    private AdLoader adLoader;
    protected AdView mAdView;
    protected InterstitialAd mInterstitialAd;

    /* access modifiers changed from: package-private */
    public AdRequest buildAdRequest(Context context, MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        AdRequest.Builder builder = new AdRequest.Builder();
        Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords != null) {
            for (String addKeyword : keywords) {
                builder.addKeyword(addKeyword);
            }
        }
        if (mediationAdRequest.isTesting()) {
            zzay.zzb();
            builder.zza(zzf.zzz(context));
        }
        if (mediationAdRequest.taggedForChildDirectedTreatment() != -1) {
            boolean z = true;
            if (mediationAdRequest.taggedForChildDirectedTreatment() != 1) {
                z = false;
            }
            builder.zzc(z);
        }
        builder.zzb(mediationAdRequest.isDesignedForFamilies());
        builder.addNetworkExtrasBundle(AdMobAdapter.class, buildExtrasBundle(bundle, bundle2));
        return builder.build();
    }

    /* access modifiers changed from: protected */
    public abstract Bundle buildExtrasBundle(Bundle bundle, Bundle bundle2);

    public String getAdUnitId(Bundle bundle) {
        return bundle.getString(AD_UNIT_ID_PARAMETER);
    }

    public View getBannerView() {
        return this.mAdView;
    }

    /* access modifiers changed from: package-private */
    public InterstitialAd getInterstitialAd() {
        return this.mInterstitialAd;
    }

    public zzdq getVideoController() {
        AdView adView = this.mAdView;
        if (adView != null) {
            return adView.zza().zza();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public AdLoader.Builder newAdLoader(Context context, String str) {
        return new AdLoader.Builder(context, str);
    }

    public void onDestroy() {
        AdView adView = this.mAdView;
        if (adView != null) {
            adView.destroy();
            this.mAdView = null;
        }
        if (this.mInterstitialAd != null) {
            this.mInterstitialAd = null;
        }
        if (this.adLoader != null) {
            this.adLoader = null;
        }
    }

    public void onImmersiveModeUpdated(boolean z) {
        InterstitialAd interstitialAd = this.mInterstitialAd;
        if (interstitialAd != null) {
            interstitialAd.setImmersiveMode(z);
        }
    }

    public void onPause() {
        AdView adView = this.mAdView;
        if (adView != null) {
            adView.pause();
        }
    }

    public void onResume() {
        AdView adView = this.mAdView;
        if (adView != null) {
            adView.resume();
        }
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        AdView adView = new AdView(context);
        this.mAdView = adView;
        adView.setAdSize(new AdSize(adSize.getWidth(), adSize.getHeight()));
        this.mAdView.setAdUnitId(getAdUnitId(bundle));
        this.mAdView.setAdListener(new zzb(this, mediationBannerListener));
        this.mAdView.loadAd(buildAdRequest(context, mediationAdRequest, bundle2, bundle));
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        InterstitialAd.load(context, getAdUnitId(bundle), buildAdRequest(context, mediationAdRequest, bundle2, bundle), new zzc(this, mediationInterstitialListener));
    }

    public void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        zze zze = new zze(this, mediationNativeListener);
        AdLoader.Builder withAdListener = newAdLoader(context, bundle.getString(AD_UNIT_ID_PARAMETER)).withAdListener(zze);
        withAdListener.zzc(nativeMediationAdRequest.getNativeAdOptions());
        withAdListener.withNativeAdOptions(nativeMediationAdRequest.getNativeAdRequestOptions());
        if (nativeMediationAdRequest.isUnifiedNativeAdRequested()) {
            withAdListener.zzb(zze);
        }
        if (nativeMediationAdRequest.zzb()) {
            for (String str : nativeMediationAdRequest.zza().keySet()) {
                withAdListener.zza(str, zze, true != ((Boolean) nativeMediationAdRequest.zza().get(str)).booleanValue() ? null : zze);
            }
        }
        AdLoader build = withAdListener.build();
        this.adLoader = build;
        build.loadAd(buildAdRequest(context, nativeMediationAdRequest, bundle2, bundle));
    }

    public void showInterstitial() {
        InterstitialAd interstitialAd = this.mInterstitialAd;
        if (interstitialAd != null) {
            interstitialAd.show((Activity) null);
        }
    }
}
