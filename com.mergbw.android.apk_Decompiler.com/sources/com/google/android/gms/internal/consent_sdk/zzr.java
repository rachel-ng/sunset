package com.google.android.gms.internal.consent_sdk;

import com.google.android.ump.ConsentInformation;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final /* synthetic */ class zzr implements Runnable {
    public final /* synthetic */ ConsentInformation.OnConsentInfoUpdateFailureListener zza;
    public final /* synthetic */ zzg zzb;

    public /* synthetic */ zzr(ConsentInformation.OnConsentInfoUpdateFailureListener onConsentInfoUpdateFailureListener, zzg zzg) {
        this.zza = onConsentInfoUpdateFailureListener;
        this.zzb = zzg;
    }

    public final void run() {
        this.zza.onConsentInfoUpdateFailure(this.zzb.zza());
    }
}
