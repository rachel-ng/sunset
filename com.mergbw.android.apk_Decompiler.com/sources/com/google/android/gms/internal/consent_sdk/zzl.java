package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;
import android.app.Application;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentRequestParameters;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
final class zzl {
    /* access modifiers changed from: private */
    public final Application zza;
    /* access modifiers changed from: private */
    public final zzap zzb;

    zzl(Application application, zzap zzap) {
        this.zza = application;
        this.zzb = zzap;
    }

    /* access modifiers changed from: package-private */
    public final zzci zzc(Activity activity, ConsentRequestParameters consentRequestParameters) throws zzg {
        ConsentDebugSettings consentDebugSettings = consentRequestParameters.getConsentDebugSettings();
        if (consentDebugSettings == null) {
            consentDebugSettings = new ConsentDebugSettings.Builder(this.zza).build();
        }
        return zzn.zza(new zzn(this, activity, consentDebugSettings, consentRequestParameters, (zzm) null));
    }
}
