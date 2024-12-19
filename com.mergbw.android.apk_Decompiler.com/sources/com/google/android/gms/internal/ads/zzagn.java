package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzagn extends zzaeh {
    private final long zza;

    public zzagn(zzadv zzadv, long j) {
        super(zzadv);
        zzeq.zzd(zzadv.zzf() >= j);
        this.zza = j;
    }

    public final long zzd() {
        return super.zzd() - this.zza;
    }

    public final long zze() {
        return super.zze() - this.zza;
    }

    public final long zzf() {
        return super.zzf() - this.zza;
    }
}
