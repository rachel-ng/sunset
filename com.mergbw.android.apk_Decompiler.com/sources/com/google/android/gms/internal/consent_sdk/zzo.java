package com.google.android.gms.internal.consent_sdk;

import android.app.Application;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzo implements zzdn {
    private final zzdr zza;
    private final zzdr zzb;

    public zzo(zzdr zzdr, zzdr zzdr2) {
        this.zza = zzdr;
        this.zzb = zzdr2;
    }

    /* renamed from: zzb */
    public final zzl zza() {
        return new zzl((Application) this.zza.zza(), (zzap) this.zzb.zza());
    }
}
