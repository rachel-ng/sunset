package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgpg {
    private static final zzgpg zza = new zzgpg();
    private static final zzgpf zzb = new zzgpf((zzgpe) null);
    private final AtomicReference zzc = new AtomicReference();

    public static zzgpg zza() {
        return zza;
    }

    public final zzgtm zzb() {
        zzgtm zzgtm = (zzgtm) this.zzc.get();
        return zzgtm == null ? zzb : zzgtm;
    }
}
