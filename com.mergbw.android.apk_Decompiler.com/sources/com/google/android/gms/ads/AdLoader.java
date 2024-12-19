package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.formats.AdManagerAdViewOptions;
import com.google.android.gms.ads.formats.OnAdManagerAdViewLoadedListener;
import com.google.android.gms.ads.formats.zzf;
import com.google.android.gms.ads.formats.zzi;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zzbn;
import com.google.android.gms.ads.internal.client.zzbq;
import com.google.android.gms.ads.internal.client.zzdx;
import com.google.android.gms.ads.internal.client.zzeu;
import com.google.android.gms.ads.internal.client.zzfk;
import com.google.android.gms.ads.internal.client.zzg;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeCustomFormatAd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzbep;
import com.google.android.gms.internal.ads.zzbgi;
import com.google.android.gms.internal.ads.zzbhk;
import com.google.android.gms.internal.ads.zzbka;
import com.google.android.gms.internal.ads.zzbkc;
import com.google.android.gms.internal.ads.zzbkd;
import com.google.android.gms.internal.ads.zzbrb;
import com.google.android.gms.internal.ads.zzbut;
import com.google.android.gms.internal.ads.zzbuv;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public class AdLoader {
    private final zzp zza;
    private final Context zzb;
    private final zzbn zzc;

    /* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
    public static class Builder {
        private final Context zza;
        private final zzbq zzb;

        public Builder(Context context, String str) {
            zzbq zzc = zzay.zza().zzc(context, str, new zzbrb());
            this.zza = (Context) Preconditions.checkNotNull(context, "context cannot be null");
            this.zzb = zzc;
        }

        public AdLoader build() {
            try {
                return new AdLoader(this.zza, this.zzb.zze(), zzp.zza);
            } catch (RemoteException e) {
                zzm.zzh("Failed to build AdLoader.", e);
                return new AdLoader(this.zza, new zzeu().zzc(), zzp.zza);
            }
        }

        public Builder forCustomFormatAd(String str, NativeCustomFormatAd.OnCustomFormatAdLoadedListener onCustomFormatAdLoadedListener, NativeCustomFormatAd.OnCustomClickListener onCustomClickListener) {
            zzbut zzbut = new zzbut(onCustomFormatAdLoadedListener, onCustomClickListener);
            try {
                this.zzb.zzh(str, zzbut.zzb(), zzbut.zza());
            } catch (RemoteException e) {
                zzm.zzk("Failed to add custom format ad listener", e);
            }
            return this;
        }

        public Builder forNativeAd(NativeAd.OnNativeAdLoadedListener onNativeAdLoadedListener) {
            try {
                this.zzb.zzk(new zzbuv(onNativeAdLoadedListener));
            } catch (RemoteException e) {
                zzm.zzk("Failed to add google native ad listener", e);
            }
            return this;
        }

        public Builder withAdListener(AdListener adListener) {
            try {
                this.zzb.zzl(new zzg(adListener));
            } catch (RemoteException e) {
                zzm.zzk("Failed to set AdListener.", e);
            }
            return this;
        }

        public Builder withAdManagerAdViewOptions(AdManagerAdViewOptions adManagerAdViewOptions) {
            try {
                this.zzb.zzm(adManagerAdViewOptions);
            } catch (RemoteException e) {
                zzm.zzk("Failed to specify Ad Manager banner ad options", e);
            }
            return this;
        }

        public Builder withNativeAdOptions(NativeAdOptions nativeAdOptions) {
            try {
                this.zzb.zzo(new zzbhk(4, nativeAdOptions.shouldReturnUrlsForImageAssets(), -1, nativeAdOptions.shouldRequestMultipleImages(), nativeAdOptions.getAdChoicesPlacement(), nativeAdOptions.getVideoOptions() != null ? new zzfk(nativeAdOptions.getVideoOptions()) : null, nativeAdOptions.zzc(), nativeAdOptions.getMediaAspectRatio(), nativeAdOptions.zza(), nativeAdOptions.zzb(), nativeAdOptions.zzd() - 1));
            } catch (RemoteException e) {
                zzm.zzk("Failed to specify native ad options", e);
            }
            return this;
        }

        @Deprecated
        public final Builder zza(String str, com.google.android.gms.ads.formats.zzg zzg, zzf zzf) {
            zzbka zzbka = new zzbka(zzg, zzf);
            try {
                this.zzb.zzh(str, zzbka.zzd(), zzbka.zzc());
            } catch (RemoteException e) {
                zzm.zzk("Failed to add custom template ad listener", e);
            }
            return this;
        }

        @Deprecated
        public final Builder zzb(zzi zzi) {
            try {
                this.zzb.zzk(new zzbkd(zzi));
            } catch (RemoteException e) {
                zzm.zzk("Failed to add google native ad listener", e);
            }
            return this;
        }

        @Deprecated
        public final Builder zzc(com.google.android.gms.ads.formats.NativeAdOptions nativeAdOptions) {
            try {
                this.zzb.zzo(new zzbhk(nativeAdOptions));
            } catch (RemoteException e) {
                zzm.zzk("Failed to specify native ad options", e);
            }
            return this;
        }

        public Builder forAdManagerAdView(OnAdManagerAdViewLoadedListener onAdManagerAdViewLoadedListener, AdSize... adSizeArr) {
            if (adSizeArr == null || adSizeArr.length <= 0) {
                throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
            }
            try {
                this.zzb.zzj(new zzbkc(onAdManagerAdViewLoadedListener), new zzq(this.zza, adSizeArr));
            } catch (RemoteException e) {
                zzm.zzk("Failed to add Google Ad Manager banner ad listener", e);
            }
            return this;
        }
    }

    AdLoader(Context context, zzbn zzbn, zzp zzp) {
        this.zzb = context;
        this.zzc = zzbn;
        this.zza = zzp;
    }

    private final void zzb(zzdx zzdx) {
        zzbep.zza(this.zzb);
        if (((Boolean) zzbgi.zzc.zze()).booleanValue()) {
            if (((Boolean) zzba.zzc().zza(zzbep.zzlg)).booleanValue()) {
                zzb.zzb.execute(new zza(this, zzdx));
                return;
            }
        }
        try {
            this.zzc.zzg(this.zza.zza(this.zzb, zzdx));
        } catch (RemoteException e) {
            zzm.zzh("Failed to load ad.", e);
        }
    }

    public boolean isLoading() {
        try {
            return this.zzc.zzi();
        } catch (RemoteException e) {
            zzm.zzk("Failed to check if ad is loading.", e);
            return false;
        }
    }

    public void loadAd(AdRequest adRequest) {
        zzb(adRequest.zza);
    }

    public void loadAds(AdRequest adRequest, int i) {
        try {
            this.zzc.zzh(this.zza.zza(this.zzb, adRequest.zza), i);
        } catch (RemoteException e) {
            zzm.zzh("Failed to load ads.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzdx zzdx) {
        try {
            this.zzc.zzg(this.zza.zza(this.zzb, zzdx));
        } catch (RemoteException e) {
            zzm.zzh("Failed to load ad.", e);
        }
    }

    public void loadAd(AdManagerAdRequest adManagerAdRequest) {
        zzb(adManagerAdRequest.zza);
    }
}
