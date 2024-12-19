package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzrn {
    private final zzdz[] zza;
    private final zzsh zzb;
    private final zzec zzc;

    public zzrn(zzdz... zzdzArr) {
        zzsh zzsh = new zzsh();
        zzec zzec = new zzec();
        zzdz[] zzdzArr2 = new zzdz[2];
        this.zza = zzdzArr2;
        System.arraycopy(zzdzArr, 0, zzdzArr2, 0, 0);
        this.zzb = zzsh;
        this.zzc = zzec;
        zzdzArr2[0] = zzsh;
        zzdzArr2[1] = zzec;
    }

    public final long zza(long j) {
        return this.zzc.zzi(j);
    }

    public final long zzb() {
        return this.zzb.zzo();
    }

    public final zzcl zzc(zzcl zzcl) {
        this.zzc.zzk(zzcl.zzc);
        this.zzc.zzj(zzcl.zzd);
        return zzcl;
    }

    public final boolean zzd(boolean z) {
        this.zzb.zzp(z);
        return z;
    }

    public final zzdz[] zze() {
        return this.zza;
    }
}
