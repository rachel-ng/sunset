package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import androidx.work.WorkRequest;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzbb implements ConsentForm {
    boolean zza = false;
    private final Application zzb;
    private final zzab zzc;
    private final zzbw zzd;
    private final zzap zze;
    private final zzbp zzf;
    private final zzdr zzg;
    private Dialog zzh;
    private zzbu zzi;
    private final AtomicBoolean zzj = new AtomicBoolean();
    private final AtomicReference zzk = new AtomicReference();
    private final AtomicReference zzl = new AtomicReference();
    private final AtomicReference zzm = new AtomicReference();

    public zzbb(Application application, zzab zzab, zzbw zzbw, zzap zzap, zzbp zzbp, zzdr zzdr) {
        this.zzb = application;
        this.zzc = zzab;
        this.zzd = zzbw;
        this.zze = zzap;
        this.zzf = zzbp;
        this.zzg = zzdr;
    }

    private final void zzg() {
        Dialog dialog = this.zzh;
        if (dialog != null) {
            dialog.dismiss();
            this.zzh = null;
        }
        this.zzd.zza((Activity) null);
        zzay zzay = (zzay) this.zzm.getAndSet((Object) null);
        if (zzay != null) {
            zzay.zza.zzb.unregisterActivityLifecycleCallbacks(zzay);
        }
    }

    public final void show(Activity activity, ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener) {
        String str;
        zzcr.zza();
        if (!this.zzj.compareAndSet(false, true)) {
            if (true != this.zza) {
                str = "ConsentForm#show can only be invoked once.";
            } else {
                str = "Privacy options form is being loading. Please try again later.";
            }
            onConsentFormDismissedListener.onConsentFormDismissed(new zzg(3, str).zza());
            return;
        }
        this.zzi.zzc();
        zzay zzay = new zzay(this, activity);
        this.zzb.registerActivityLifecycleCallbacks(zzay);
        this.zzm.set(zzay);
        this.zzd.zza(activity);
        Dialog dialog = new Dialog(activity, 16973840);
        dialog.setContentView(this.zzi);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        if (window == null) {
            onConsentFormDismissedListener.onConsentFormDismissed(new zzg(3, "Activity with null windows is passed in.").zza());
            return;
        }
        window.setLayout(-1, -1);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setFlags(16777216, 16777216);
        this.zzl.set(onConsentFormDismissedListener);
        dialog.show();
        this.zzh = dialog;
        this.zzi.zzd("UMP_messagePresented", "");
    }

    /* access modifiers changed from: package-private */
    public final zzbu zza() {
        return this.zzi;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(UserMessagingPlatform.OnConsentFormLoadSuccessListener onConsentFormLoadSuccessListener, UserMessagingPlatform.OnConsentFormLoadFailureListener onConsentFormLoadFailureListener) {
        zzbu zzb2 = ((zzbv) this.zzg).zza();
        this.zzi = zzb2;
        zzb2.setBackgroundColor(0);
        zzb2.getSettings().setJavaScriptEnabled(true);
        zzb2.setWebViewClient(new zzbt(zzb2, (zzbs) null));
        this.zzk.set(new zzba(onConsentFormLoadSuccessListener, onConsentFormLoadFailureListener, (zzaz) null));
        zzbu zzbu = this.zzi;
        zzbp zzbp = this.zzf;
        zzbu.loadDataWithBaseURL(zzbp.zza(), zzbp.zzb(), "text/html", "UTF-8", (String) null);
        zzcr.zza.postDelayed(new zzax(this), WorkRequest.MIN_BACKOFF_MILLIS);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(int i) {
        zzg();
        ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener = (ConsentForm.OnConsentFormDismissedListener) this.zzl.getAndSet((Object) null);
        if (onConsentFormDismissedListener != null) {
            this.zze.zzg(3);
            onConsentFormDismissedListener.onConsentFormDismissed((FormError) null);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzd(zzg zzg2) {
        zzg();
        ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener = (ConsentForm.OnConsentFormDismissedListener) this.zzl.getAndSet((Object) null);
        if (onConsentFormDismissedListener != null) {
            onConsentFormDismissedListener.onConsentFormDismissed(zzg2.zza());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zze() {
        zzba zzba = (zzba) this.zzk.getAndSet((Object) null);
        if (zzba != null) {
            zzba.onConsentFormLoadSuccess(this);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzf(zzg zzg2) {
        zzba zzba = (zzba) this.zzk.getAndSet((Object) null);
        if (zzba != null) {
            zzba.onConsentFormLoadFailure(zzg2.zza());
        }
    }
}
