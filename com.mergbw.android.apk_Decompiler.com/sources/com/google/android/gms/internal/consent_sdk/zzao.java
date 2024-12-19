package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzao implements zzdn {
    private final zzdr zza;
    private final zzdr zzb;
    private final zzdr zzc;

    public zzao(zzdr zzdr, zzdr zzdr2, zzdr zzdr3) {
        this.zza = zzdr;
        this.zzb = zzdr2;
        this.zzc = zzdr3;
    }

    /* renamed from: zzb */
    public final zzan zza() {
        Executor executor = zzcr.zzb;
        zzdp.zza(executor);
        return new zzan((Application) this.zza.zza(), (zzap) this.zzb.zza(), executor);
    }
}
