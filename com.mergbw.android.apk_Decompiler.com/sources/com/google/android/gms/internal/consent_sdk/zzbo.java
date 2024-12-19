package com.google.android.gms.internal.consent_sdk;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzbo implements zzdn {
    private final zzdr zza;
    private final zzdr zzb;

    public zzbo(zzdr zzdr, zzdr zzdr2) {
        this.zza = zzdr;
        this.zzb = zzdr2;
    }

    public final /* synthetic */ Object zza() {
        Executor executor = zzcr.zzb;
        zzdp.zza(executor);
        return new zzbn(this.zza, executor);
    }
}
