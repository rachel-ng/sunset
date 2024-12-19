package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzajk {
    public final zzaen zza;
    public final long zzb;
    public final long zzc;
    public final int zzd;
    public final int zze;
    public final long[] zzf;

    private zzajk(zzaen zzaen, long j, long j2, long[] jArr, int i, int i2) {
        this.zza = zzaen;
        this.zzb = j;
        this.zzc = j2;
        this.zzf = jArr;
        this.zzd = i;
        this.zze = i2;
    }

    public static zzajk zza(zzaen zzaen, zzfu zzfu) {
        long[] jArr;
        int i;
        int i2;
        int zzg = zzfu.zzg();
        int zzp = (zzg & 1) != 0 ? zzfu.zzp() : -1;
        long zzu = (zzg & 2) != 0 ? zzfu.zzu() : -1;
        if ((zzg & 4) == 4) {
            long[] jArr2 = new long[100];
            for (int i3 = 0; i3 < 100; i3++) {
                jArr2[i3] = (long) zzfu.zzm();
            }
            jArr = jArr2;
        } else {
            jArr = null;
        }
        if ((zzg & 8) != 0) {
            zzfu.zzL(4);
        }
        if (zzfu.zzb() >= 24) {
            zzfu.zzL(21);
            int zzo = zzfu.zzo();
            i = zzo & 4095;
            i2 = zzo >> 12;
        } else {
            i2 = -1;
            i = -1;
        }
        return new zzajk(zzaen, (long) zzp, zzu, jArr, i2, i);
    }
}
