package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgpi {
    private static final zzgpi zza = new zzgpi();
    private final AtomicReference zzb = new AtomicReference(new zzgqe(new zzgqa((zzgpz) null), (zzgqd) null));

    zzgpi() {
    }

    public static zzgpi zza() {
        return zza;
    }

    public final Class zzb(Class cls) throws GeneralSecurityException {
        return ((zzgqe) this.zzb.get()).zza(cls);
    }

    public final Object zzc(zzghi zzghi, Class cls) throws GeneralSecurityException {
        return ((zzgqe) this.zzb.get()).zzb(zzghi, cls);
    }

    public final Object zzd(zzgqk zzgqk, Class cls) throws GeneralSecurityException {
        return ((zzgqe) this.zzb.get()).zzc(zzgqk, cls);
    }

    public final synchronized void zze(zzgpy zzgpy) throws GeneralSecurityException {
        zzgqa zzgqa = new zzgqa((zzgqe) this.zzb.get(), (zzgpz) null);
        zzgqa.zza(zzgpy);
        this.zzb.set(new zzgqe(zzgqa, (zzgqd) null));
    }

    public final synchronized void zzf(zzghy zzghy) throws GeneralSecurityException {
        zzgqa zzgqa = new zzgqa((zzgqe) this.zzb.get(), (zzgpz) null);
        zzgqa.zzb(zzghy);
        this.zzb.set(new zzgqe(zzgqa, (zzgqd) null));
    }
}
