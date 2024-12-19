package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgzf {
    private final zzgze zza;

    private zzgzf(zzgze zzgze) {
        this.zza = zzgze;
    }

    public static zzgzf zzb(byte[] bArr, zzgic zzgic) {
        return new zzgzf(zzgze.zzb(bArr));
    }

    public static zzgzf zzc(int i) {
        return new zzgzf(zzgze.zzb(zzgqo.zzb(i)));
    }

    public final int zza() {
        return this.zza.zza();
    }

    public final byte[] zzd(zzgic zzgic) {
        return this.zza.zzc();
    }
}
