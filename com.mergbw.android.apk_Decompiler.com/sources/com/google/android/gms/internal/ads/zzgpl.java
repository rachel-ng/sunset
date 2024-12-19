package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgpl {
    private static final zzgpl zza = ((zzgpl) zzgqz.zza(new zzgpj()));
    private final AtomicReference zzb = new AtomicReference(new zzgqx(new zzgqr(), (zzgqw) null));

    public static zzgpl zzc() {
        return zza;
    }

    public final zzghi zza(zzgqq zzgqq, @Nullable zzgic zzgic) throws GeneralSecurityException {
        return ((zzgqx) this.zzb.get()).zza(zzgqq, zzgic);
    }

    public final zzghx zzb(zzgqq zzgqq) throws GeneralSecurityException {
        return ((zzgqx) this.zzb.get()).zzb(zzgqq);
    }

    public final zzgqq zzd(zzghi zzghi, Class cls, @Nullable zzgic zzgic) throws GeneralSecurityException {
        return ((zzgqx) this.zzb.get()).zzc(zzghi, cls, zzgic);
    }

    public final zzgqq zze(zzghx zzghx, Class cls) throws GeneralSecurityException {
        return ((zzgqx) this.zzb.get()).zzd(zzghx, cls);
    }

    public final synchronized void zzf(zzgon zzgon) throws GeneralSecurityException {
        zzgqr zzgqr = new zzgqr((zzgqx) this.zzb.get());
        zzgqr.zza(zzgon);
        this.zzb.set(new zzgqx(zzgqr, (zzgqw) null));
    }

    public final synchronized void zzg(zzgor zzgor) throws GeneralSecurityException {
        zzgqr zzgqr = new zzgqr((zzgqx) this.zzb.get());
        zzgqr.zzb(zzgor);
        this.zzb.set(new zzgqx(zzgqr, (zzgqw) null));
    }

    public final synchronized void zzh(zzgpq zzgpq) throws GeneralSecurityException {
        zzgqr zzgqr = new zzgqr((zzgqx) this.zzb.get());
        zzgqr.zzc(zzgpq);
        this.zzb.set(new zzgqx(zzgqr, (zzgqw) null));
    }

    public final synchronized void zzi(zzgpu zzgpu) throws GeneralSecurityException {
        zzgqr zzgqr = new zzgqr((zzgqx) this.zzb.get());
        zzgqr.zzd(zzgpu);
        this.zzb.set(new zzgqx(zzgqr, (zzgqw) null));
    }

    public final boolean zzj(zzgqq zzgqq) {
        return ((zzgqx) this.zzb.get()).zzi(zzgqq);
    }

    public final boolean zzk(zzgqq zzgqq) {
        return ((zzgqx) this.zzb.get()).zzj(zzgqq);
    }
}
