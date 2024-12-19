package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Date;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfpc implements zzfpf {
    private static final zzfpc zzb = new zzfpc(new zzfpg());
    protected final zzfqc zza = new zzfqc();
    private Date zzc;
    private boolean zzd;
    private final zzfpg zze;
    private boolean zzf;

    private zzfpc(zzfpg zzfpg) {
        this.zze = zzfpg;
    }

    public static zzfpc zza() {
        return zzb;
    }

    public final Date zzb() {
        Date date = this.zzc;
        if (date != null) {
            return (Date) date.clone();
        }
        return null;
    }

    public final void zzc(boolean z) {
        if (!this.zzf && z) {
            Date date = new Date();
            Date date2 = this.zzc;
            if (date2 == null || date.after(date2)) {
                this.zzc = date;
                if (this.zzd) {
                    for (zzfon zzg : zzfpe.zza().zzb()) {
                        zzg.zzg().zzg(zzb());
                    }
                }
            }
        }
        this.zzf = z;
    }

    public final void zzd(Context context) {
        if (!this.zzd) {
            this.zze.zzd(context);
            this.zze.zze(this);
            this.zze.zzf();
            this.zzf = this.zze.zza;
            this.zzd = true;
        }
    }
}
