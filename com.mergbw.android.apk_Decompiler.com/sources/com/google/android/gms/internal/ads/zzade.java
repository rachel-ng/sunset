package com.google.android.gms.internal.ads;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzade {
    protected final zzacy zza;
    protected final zzadd zzb;
    protected zzada zzc;
    private final int zzd;

    protected zzade(zzadb zzadb, zzadd zzadd, long j, long j2, long j3, long j4, long j5, long j6, int i) {
        this.zzb = zzadd;
        this.zzd = i;
        this.zza = new zzacy(zzadb, j, 0, j3, j4, j5, j6);
    }

    protected static final int zzf(zzadv zzadv, long j, zzaeq zzaeq) {
        if (j == zzadv.zzf()) {
            return 0;
        }
        zzaeq.zza = j;
        return 1;
    }

    protected static final boolean zzg(zzadv zzadv, long j) throws IOException {
        long zzf = j - zzadv.zzf();
        if (zzf < 0 || zzf > PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
            return false;
        }
        ((zzadi) zzadv).zzo((int) zzf, false);
        return true;
    }

    public final int zza(zzadv zzadv, zzaeq zzaeq) throws IOException {
        while (true) {
            zzada zzada = this.zzc;
            zzeq.zzb(zzada);
            long zzb2 = zzada.zzf;
            int i = this.zzd;
            long zzc2 = zzada.zzh;
            if (zzada.zzg - zzb2 <= ((long) i)) {
                zzc(false, zzb2);
                return zzf(zzadv, zzb2, zzaeq);
            } else if (!zzg(zzadv, zzc2)) {
                return zzf(zzadv, zzc2, zzaeq);
            } else {
                zzadv.zzj();
                zzadc zza2 = this.zzb.zza(zzadv, zzada.zzb);
                int zza3 = zza2.zzb;
                if (zza3 == -3) {
                    zzc(false, zzc2);
                    return zzf(zzadv, zzc2, zzaeq);
                } else if (zza3 == -2) {
                    zzada.zzh(zzada, zza2.zzc, zza2.zzd);
                } else if (zza3 != -1) {
                    zzg(zzadv, zza2.zzd);
                    zzc(true, zza2.zzd);
                    return zzf(zzadv, zza2.zzd, zzaeq);
                } else {
                    zzada.zzg(zzada, zza2.zzc, zza2.zzd);
                }
            }
        }
    }

    public final zzaet zzb() {
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzc(boolean z, long j) {
        this.zzc = null;
        this.zzb.zzb();
    }

    public final void zzd(long j) {
        long j2 = j;
        zzada zzada = this.zzc;
        if (zzada == null || zzada.zza != j2) {
            zzacy zzacy = this.zza;
            zzada zzada2 = r1;
            zzada zzada3 = new zzada(j, zzacy.zzf(j2), 0, zzacy.zzc, zzacy.zzd, zzacy.zze, zzacy.zzf);
            this.zzc = zzada2;
        }
    }

    public final boolean zze() {
        return this.zzc != null;
    }
}
