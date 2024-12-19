package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaem implements zzaet {
    private final long[] zza;
    private final long[] zzb;
    private final long zzc;
    private final boolean zzd;

    public zzaem(long[] jArr, long[] jArr2, long j) {
        int length = jArr.length;
        int length2 = jArr2.length;
        zzeq.zzd(length == length2);
        boolean z = length2 > 0;
        this.zzd = z;
        if (!z || jArr2[0] <= 0) {
            this.zza = jArr;
            this.zzb = jArr2;
        } else {
            int i = length2 + 1;
            long[] jArr3 = new long[i];
            this.zza = jArr3;
            long[] jArr4 = new long[i];
            this.zzb = jArr4;
            System.arraycopy(jArr, 0, jArr3, 1, length2);
            System.arraycopy(jArr2, 0, jArr4, 1, length2);
        }
        this.zzc = j;
    }

    public final long zza() {
        return this.zzc;
    }

    public final zzaer zzg(long j) {
        if (!this.zzd) {
            zzaeu zzaeu = zzaeu.zza;
            return new zzaer(zzaeu, zzaeu);
        }
        int zzc2 = zzgd.zzc(this.zzb, j, true, true);
        zzaeu zzaeu2 = new zzaeu(this.zzb[zzc2], this.zza[zzc2]);
        if (zzaeu2.zzb != j) {
            long[] jArr = this.zzb;
            if (zzc2 != jArr.length - 1) {
                int i = zzc2 + 1;
                return new zzaer(zzaeu2, new zzaeu(jArr[i], this.zza[i]));
            }
        }
        return new zzaer(zzaeu2, zzaeu2);
    }

    public final boolean zzh() {
        return this.zzd;
    }
}
