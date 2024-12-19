package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;
import android.util.Log;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.UserMessagingPlatform;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzbn {
    private final zzdr zza;
    private final Executor zzb;
    private final AtomicReference zzc = new AtomicReference();
    private final AtomicReference zzd = new AtomicReference();

    zzbn(zzdr zzdr, Executor executor) {
        this.zza = zzdr;
        this.zzb = executor;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzbb zzbb) {
        AtomicReference atomicReference = this.zzd;
        Objects.requireNonNull(atomicReference);
        zzbb.zzb(new zzbe(atomicReference), zzbf.zza);
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, com.google.android.gms.internal.consent_sdk.zzav] */
    public final void zzb(UserMessagingPlatform.OnConsentFormLoadSuccessListener onConsentFormLoadSuccessListener, UserMessagingPlatform.OnConsentFormLoadFailureListener onConsentFormLoadFailureListener) {
        zzcr.zza();
        zzbp zzbp = (zzbp) this.zzc.get();
        if (zzbp == null) {
            onConsentFormLoadFailureListener.onConsentFormLoadFailure(new zzg(3, "No available form can be built.").zza());
            return;
        }
        ? zza2 = this.zza.zza();
        zza2.zza(zzbp);
        zza2.zzb().zza().zzb(onConsentFormLoadSuccessListener, onConsentFormLoadFailureListener);
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, com.google.android.gms.internal.consent_sdk.zzav] */
    public final void zzc() {
        zzbp zzbp = (zzbp) this.zzc.get();
        if (zzbp == null) {
            Log.e("UserMessagingPlatform", "Failed to load and cache a form due to null consent form resources.");
            return;
        }
        ? zza2 = this.zza.zza();
        zza2.zza(zzbp);
        zzbb zza3 = zza2.zzb().zza();
        zza3.zza = true;
        zzcr.zza.post(new zzbd(this, zza3));
    }

    public final void zzd(zzbp zzbp) {
        this.zzc.set(zzbp);
    }

    public final void zze(Activity activity, ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener) {
        zzcr.zza();
        zzj zzb2 = zza.zza(activity).zzb();
        if (zzb2 == null) {
            zzcr.zza.post(new zzbg(onConsentFormDismissedListener));
        } else if (!zzb2.isConsentFormAvailable() && zzb2.getPrivacyOptionsRequirementStatus() != ConsentInformation.PrivacyOptionsRequirementStatus.NOT_REQUIRED) {
            zzcr.zza.post(new zzbh(onConsentFormDismissedListener));
            zzb2.zza(activity);
        } else if (zzb2.getPrivacyOptionsRequirementStatus() == ConsentInformation.PrivacyOptionsRequirementStatus.NOT_REQUIRED) {
            zzcr.zza.post(new zzbi(onConsentFormDismissedListener));
        } else {
            ConsentForm consentForm = (ConsentForm) this.zzd.get();
            if (consentForm == null) {
                zzcr.zza.post(new zzbj(onConsentFormDismissedListener));
                return;
            }
            consentForm.show(activity, onConsentFormDismissedListener);
            this.zzb.execute(new zzbk(this));
        }
    }

    public final boolean zzf() {
        return this.zzc.get() != null;
    }
}
