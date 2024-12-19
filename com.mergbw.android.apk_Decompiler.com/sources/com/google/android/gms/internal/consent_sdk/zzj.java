package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;
import android.util.Log;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzj implements ConsentInformation {
    private final zzap zza;
    private final zzu zzb;
    private final zzbn zzc;
    private final Object zzd = new Object();
    private final Object zze = new Object();
    private boolean zzf = false;
    private boolean zzg = false;
    private ConsentRequestParameters zzh = new ConsentRequestParameters.Builder().build();

    public zzj(zzap zzap, zzu zzu, zzbn zzbn) {
        this.zza = zzap;
        this.zzb = zzu;
        this.zzc = zzbn;
    }

    public final boolean canRequestAds() {
        int i;
        if (!zzc()) {
            i = 0;
        } else {
            i = this.zza.zza();
        }
        if (i == 1 || i == 3) {
            return true;
        }
        return false;
    }

    public final int getConsentStatus() {
        if (!zzc()) {
            return 0;
        }
        return this.zza.zza();
    }

    public final ConsentInformation.PrivacyOptionsRequirementStatus getPrivacyOptionsRequirementStatus() {
        if (!zzc()) {
            return ConsentInformation.PrivacyOptionsRequirementStatus.UNKNOWN;
        }
        return this.zza.zzb();
    }

    public final boolean isConsentFormAvailable() {
        return this.zzc.zzf();
    }

    public final void requestConsentInfoUpdate(Activity activity, ConsentRequestParameters consentRequestParameters, ConsentInformation.OnConsentInfoUpdateSuccessListener onConsentInfoUpdateSuccessListener, ConsentInformation.OnConsentInfoUpdateFailureListener onConsentInfoUpdateFailureListener) {
        synchronized (this.zzd) {
            this.zzf = true;
        }
        this.zzh = consentRequestParameters;
        this.zzb.zzc(activity, consentRequestParameters, onConsentInfoUpdateSuccessListener, onConsentInfoUpdateFailureListener);
    }

    public final void reset() {
        this.zzc.zzd((zzbp) null);
        this.zza.zze();
        synchronized (this.zzd) {
            this.zzf = false;
        }
    }

    public final void zza(Activity activity) {
        if (!zzc() || zzd()) {
            boolean zzc2 = zzc();
            boolean zzd2 = zzd();
            Log.w("UserMessagingPlatform", "Retry request is not executed. consentInfoUpdateHasBeenCalled=" + zzc2 + ", retryRequestIsInProgress=" + zzd2);
            return;
        }
        zzb(true);
        this.zzb.zzc(activity, this.zzh, new zzh(this), new zzi(this));
    }

    public final void zzb(boolean z) {
        synchronized (this.zze) {
            this.zzg = z;
        }
    }

    public final boolean zzc() {
        boolean z;
        synchronized (this.zzd) {
            z = this.zzf;
        }
        return z;
    }

    public final boolean zzd() {
        boolean z;
        synchronized (this.zze) {
            z = this.zzg;
        }
        return z;
    }
}
