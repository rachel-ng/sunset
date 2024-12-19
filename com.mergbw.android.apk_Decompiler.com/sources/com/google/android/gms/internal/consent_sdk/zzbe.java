package com.google.android.gms.internal.consent_sdk;

import com.google.android.ump.ConsentForm;
import com.google.android.ump.UserMessagingPlatform;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final /* synthetic */ class zzbe implements UserMessagingPlatform.OnConsentFormLoadSuccessListener {
    public final /* synthetic */ AtomicReference zza;

    public /* synthetic */ zzbe(AtomicReference atomicReference) {
        this.zza = atomicReference;
    }

    public final void onConsentFormLoadSuccess(ConsentForm consentForm) {
        this.zza.set(consentForm);
    }
}
