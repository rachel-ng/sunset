package com.google.android.gms.internal.consent_sdk;

import com.google.android.ump.ConsentInformation;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final /* synthetic */ class zzh implements ConsentInformation.OnConsentInfoUpdateSuccessListener {
    public final /* synthetic */ zzj zza;

    public /* synthetic */ zzh(zzj zzj) {
        this.zza = zzj;
    }

    public final void onConsentInfoUpdateSuccess() {
        this.zza.zzb(false);
    }
}
