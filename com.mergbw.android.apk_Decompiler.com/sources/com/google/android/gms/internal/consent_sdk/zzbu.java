package com.google.android.gms.internal.consent_sdk;

import android.os.Handler;
import android.webkit.WebView;
import java.util.Objects;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzbu extends WebView {
    private final Handler zza;
    /* access modifiers changed from: private */
    public final zzca zzb;
    /* access modifiers changed from: private */
    public boolean zzc = false;

    public zzbu(zzbw zzbw, Handler handler, zzca zzca) {
        super(zzbw);
        this.zza = handler;
        this.zzb = zzca;
    }

    static /* bridge */ /* synthetic */ boolean zzf(zzbu zzbu, String str) {
        return str != null && str.startsWith("consent://");
    }

    public final void zzc() {
        zzca zzca = this.zzb;
        Objects.requireNonNull(zzca);
        this.zza.post(new zzbr(zzca));
    }

    public final void zzd(String str, String str2) {
        this.zza.post(new zzbq(this, str + "(" + str2 + ");"));
    }
}
