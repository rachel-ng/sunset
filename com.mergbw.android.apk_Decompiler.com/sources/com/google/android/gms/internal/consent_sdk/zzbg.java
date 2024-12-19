package com.google.android.gms.internal.consent_sdk;

import com.google.android.ump.ConsentForm;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final /* synthetic */ class zzbg implements Runnable {
    public final /* synthetic */ ConsentForm.OnConsentFormDismissedListener zza;

    public /* synthetic */ zzbg(ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener) {
        this.zza = onConsentFormDismissedListener;
    }

    public final void run() {
        this.zza.onConsentFormDismissed(new zzg(1, "No consentInformation.").zza());
    }
}
