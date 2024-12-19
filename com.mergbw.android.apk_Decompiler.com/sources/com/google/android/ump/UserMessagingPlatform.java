package com.google.android.ump;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.internal.consent_sdk.zza;
import com.google.android.gms.internal.consent_sdk.zzbl;
import com.google.android.gms.internal.consent_sdk.zzbm;
import com.google.android.gms.internal.consent_sdk.zzbn;
import com.google.android.gms.internal.consent_sdk.zzcr;
import com.google.android.ump.ConsentForm;
import java.util.Objects;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class UserMessagingPlatform {

    /* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
    public interface OnConsentFormLoadFailureListener {
        void onConsentFormLoadFailure(FormError formError);
    }

    /* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
    public interface OnConsentFormLoadSuccessListener {
        void onConsentFormLoadSuccess(ConsentForm consentForm);
    }

    private UserMessagingPlatform() {
    }

    public static ConsentInformation getConsentInformation(Context context) {
        return zza.zza(context).zzb();
    }

    public static void loadAndShowConsentFormIfRequired(Activity activity, ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener) {
        if (zza.zza(activity).zzb().canRequestAds()) {
            onConsentFormDismissedListener.onConsentFormDismissed((FormError) null);
            return;
        }
        zzbn zzc = zza.zza(activity).zzc();
        zzcr.zza();
        zzbl zzbl = new zzbl(activity, onConsentFormDismissedListener);
        Objects.requireNonNull(onConsentFormDismissedListener);
        zzc.zzb(zzbl, new zzbm(onConsentFormDismissedListener));
    }

    public static void loadConsentForm(Context context, OnConsentFormLoadSuccessListener onConsentFormLoadSuccessListener, OnConsentFormLoadFailureListener onConsentFormLoadFailureListener) {
        zza.zza(context).zzc().zzb(onConsentFormLoadSuccessListener, onConsentFormLoadFailureListener);
    }

    public static void showPrivacyOptionsForm(Activity activity, ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener) {
        zza.zza(activity).zzc().zze(activity, onConsentFormDismissedListener);
    }
}
