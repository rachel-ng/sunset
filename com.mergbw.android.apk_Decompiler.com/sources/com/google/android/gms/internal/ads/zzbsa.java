package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationBannerAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzbsa implements MediationAdLoadCallback {
    final /* synthetic */ zzbrl zza;
    final /* synthetic */ zzbsg zzb;

    zzbsa(zzbsg zzbsg, zzbrl zzbrl) {
        this.zza = zzbrl;
        this.zzb = zzbsg;
    }

    public final void onFailure(AdError adError) {
        try {
            String canonicalName = this.zzb.zza.getClass().getCanonicalName();
            int code = adError.getCode();
            String message = adError.getMessage();
            String domain = adError.getDomain();
            zzm.zze(canonicalName + "failed to loaded mediation ad: ErrorCode = " + code + ". ErrorMessage = " + message + ". ErrorDomain = " + domain);
            this.zza.zzh(adError.zza());
            this.zza.zzi(adError.getCode(), adError.getMessage());
            this.zza.zzg(adError.getCode());
        } catch (RemoteException e) {
            zzm.zzh("", e);
        }
    }

    public final /* bridge */ /* synthetic */ Object onSuccess(Object obj) {
        try {
            this.zzb.zze = ((MediationBannerAd) obj).getView();
            this.zza.zzo();
        } catch (RemoteException e) {
            zzm.zzh("", e);
        }
        return new zzbrw(this.zza);
    }

    public final void onFailure(String str) {
        onFailure(new AdError(0, str, AdError.UNDEFINED_DOMAIN));
    }
}