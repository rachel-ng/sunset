package com.google.android.gms.internal.consent_sdk;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzf implements zzdn {
    private final zzdr zza;

    public zzf(zzdr zzdr) {
        this.zza = zzdr;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        Executor executor = zzcr.zzb;
        zzdp.zza(executor);
        return new zze(executor);
    }
}
