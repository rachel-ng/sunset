package com.google.android.gms.internal.consent_sdk;

import android.util.Log;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final /* synthetic */ class zzbf implements UserMessagingPlatform.OnConsentFormLoadFailureListener {
    public static final /* synthetic */ zzbf zza = new zzbf();

    private /* synthetic */ zzbf() {
    }

    public final void onConsentFormLoadFailure(FormError formError) {
        Log.e("UserMessagingPlatform", "Failed to load and cache a form, error=".concat(String.valueOf(formError.getMessage())));
    }
}
