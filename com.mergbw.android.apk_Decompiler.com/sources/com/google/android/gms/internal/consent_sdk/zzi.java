package com.google.android.gms.internal.consent_sdk;

import com.google.android.ump.ConsentInformation;
import com.google.android.ump.FormError;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final /* synthetic */ class zzi implements ConsentInformation.OnConsentInfoUpdateFailureListener {
    public final /* synthetic */ zzj zza;

    public /* synthetic */ zzi(zzj zzj) {
        this.zza = zzj;
    }

    public final void onConsentInfoUpdateFailure(FormError formError) {
        this.zza.zzb(false);
    }
}
