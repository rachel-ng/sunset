package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaoo implements zzaoc {
    private final zzfu zza = new zzfu(10);
    private zzafa zzb;
    private boolean zzc;
    private long zzd = C.TIME_UNSET;
    private int zze;
    private int zzf;

    public final void zza(zzfu zzfu) {
        zzeq.zzb(this.zzb);
        if (this.zzc) {
            int zzb2 = zzfu.zzb();
            int i = this.zzf;
            if (i < 10) {
                int min = Math.min(zzb2, 10 - i);
                System.arraycopy(zzfu.zzM(), zzfu.zzd(), this.zza.zzM(), this.zzf, min);
                if (this.zzf + min == 10) {
                    this.zza.zzK(0);
                    if (this.zza.zzm() == 73 && this.zza.zzm() == 68 && this.zza.zzm() == 51) {
                        this.zza.zzL(3);
                        this.zze = this.zza.zzl() + 10;
                    } else {
                        zzfk.zzf("Id3Reader", "Discarding invalid ID3 tag");
                        this.zzc = false;
                        return;
                    }
                }
            }
            int min2 = Math.min(zzb2, this.zze - this.zzf);
            this.zzb.zzq(zzfu, min2);
            this.zzf += min2;
        }
    }

    public final void zzb(zzadx zzadx, zzapo zzapo) {
        zzapo.zzc();
        zzafa zzw = zzadx.zzw(zzapo.zza(), 5);
        this.zzb = zzw;
        zzal zzal = new zzal();
        zzal.zzK(zzapo.zzb());
        zzal.zzX(MimeTypes.APPLICATION_ID3);
        zzw.zzl(zzal.zzad());
    }

    public final void zzc() {
        int i;
        zzeq.zzb(this.zzb);
        if (this.zzc && (i = this.zze) != 0 && this.zzf == i) {
            zzeq.zzf(this.zzd != C.TIME_UNSET);
            this.zzb.zzs(this.zzd, 1, this.zze, 0, (zzaez) null);
            this.zzc = false;
        }
    }

    public final void zzd(long j, int i) {
        if ((i & 4) != 0) {
            this.zzc = true;
            this.zzd = j;
            this.zze = 0;
            this.zzf = 0;
        }
    }

    public final void zze() {
        this.zzc = false;
        this.zzd = C.TIME_UNSET;
    }
}
