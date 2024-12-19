package com.google.android.gms.internal.consent_sdk;

import com.google.android.ump.ConsentInformation;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final /* synthetic */ class zzp implements Runnable {
    public final /* synthetic */ zzu zza;
    public final /* synthetic */ ConsentInformation.OnConsentInfoUpdateSuccessListener zzb;
    public final /* synthetic */ zzz zzc;

    public /* synthetic */ zzp(zzu zzu, ConsentInformation.OnConsentInfoUpdateSuccessListener onConsentInfoUpdateSuccessListener, zzz zzz) {
        this.zza = zzu;
        this.zzb = onConsentInfoUpdateSuccessListener;
        this.zzc = zzz;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
