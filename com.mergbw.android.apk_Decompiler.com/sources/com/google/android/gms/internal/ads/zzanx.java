package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzanx implements zzadu {
    public static final zzaea zza = new zzanw();
    private final zzany zzb;
    private final zzfu zzc;
    private final zzfu zzd;
    private final zzft zze;
    private zzadx zzf;
    private long zzg;
    private long zzh;
    private boolean zzi;
    private boolean zzj;

    public zzanx() {
        throw null;
    }

    public zzanx(int i) {
        this.zzb = new zzany(true, (String) null, 0);
        this.zzc = new zzfu(2048);
        this.zzh = -1;
        zzfu zzfu = new zzfu(10);
        this.zzd = zzfu;
        byte[] zzM = zzfu.zzM();
        this.zze = new zzft(zzM, zzM.length);
    }

    public final int zzb(zzadv zzadv, zzaeq zzaeq) throws IOException {
        zzeq.zzb(this.zzf);
        int zza2 = zzadv.zza(this.zzc.zzM(), 0, 2048);
        if (!this.zzj) {
            this.zzf.zzO(new zzaes(C.TIME_UNSET, 0));
            this.zzj = true;
        }
        if (zza2 == -1) {
            return -1;
        }
        this.zzc.zzK(0);
        this.zzc.zzJ(zza2);
        if (!this.zzi) {
            this.zzb.zzd(this.zzg, 4);
            this.zzi = true;
        }
        this.zzb.zza(this.zzc);
        return 0;
    }

    public final /* synthetic */ List zzc() {
        return zzgbc.zzm();
    }

    public final void zzd(zzadx zzadx) {
        this.zzf = zzadx;
        this.zzb.zzb(zzadx, new zzapo(Integer.MIN_VALUE, 0, 1));
        zzadx.zzD();
    }

    public final void zze(long j, long j2) {
        this.zzi = false;
        this.zzb.zze();
        this.zzg = j2;
    }

    public final boolean zzf(zzadv zzadv) throws IOException {
        zzadi zzadi;
        int i = 0;
        while (true) {
            zzadi = (zzadi) zzadv;
            zzadi.zzm(this.zzd.zzM(), 0, 10, false);
            this.zzd.zzK(0);
            if (this.zzd.zzo() != 4801587) {
                break;
            }
            this.zzd.zzL(3);
            int zzl = this.zzd.zzl();
            i += zzl + 10;
            zzadi.zzl(zzl, false);
        }
        zzadv.zzj();
        zzadi.zzl(i, false);
        if (this.zzh == -1) {
            this.zzh = (long) i;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = i;
        do {
            zzadi.zzm(this.zzd.zzM(), 0, 2, false);
            this.zzd.zzK(0);
            if (!zzany.zzf(this.zzd.zzq())) {
                i4++;
                zzadv.zzj();
                zzadi.zzl(i4, false);
            } else {
                i2++;
                if (i2 >= 4 && i3 > 188) {
                    return true;
                }
                zzadi.zzm(this.zzd.zzM(), 0, 4, false);
                this.zze.zzk(14);
                int zzd2 = this.zze.zzd(13);
                if (zzd2 <= 6) {
                    i4++;
                    zzadv.zzj();
                    zzadi.zzl(i4, false);
                } else {
                    zzadi.zzl(zzd2 - 6, false);
                    i3 += zzd2;
                }
            }
            i2 = 0;
            i3 = 0;
        } while (i4 - i < 8192);
        return false;
    }
}
