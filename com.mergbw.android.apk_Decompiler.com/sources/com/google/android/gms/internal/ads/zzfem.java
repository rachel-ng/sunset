package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.zzu;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfem implements zzhkp {
    private final zzhlg zza;
    private final zzhlg zzb;
    private final zzhlg zzc;

    public zzfem(zzhlg zzhlg, zzhlg zzhlg2, zzhlg zzhlg3) {
        this.zza = zzhlg;
        this.zzb = zzhlg2;
        this.zzc = zzhlg3;
    }

    /* renamed from: zza */
    public final zzfek zzb() {
        zzcbs zzcbs;
        Context context = (Context) this.zza.zzb();
        zzfiy zzfiy = (zzfiy) this.zzb.zzb();
        zzfjq zzfjq = (zzfjq) this.zzc.zzb();
        if (((Boolean) zzba.zzc().zza(zzbep.zzgA)).booleanValue()) {
            zzcbs = zzu.zzo().zzi().zzh();
        } else {
            zzcbs = zzu.zzo().zzi().zzi();
        }
        boolean z = false;
        if (zzcbs != null && zzcbs.zzh()) {
            z = true;
        }
        if (((Integer) zzba.zzc().zza(zzbep.zzgQ)).intValue() > 0) {
            if (!((Boolean) zzba.zzc().zza(zzbep.zzgz)).booleanValue() || z) {
                zzfjp zza2 = zzfjq.zza(zzfjg.AppOpen, context, zzfiy, new zzfdo(new zzfdl()));
                return new zzfdq(new zzfea(new zzfdz()), new zzfdw(zza2.zza, zzcci.zza), zza2.zzb, zza2.zza.zza().zzf, zzcci.zza);
            }
        }
        return new zzfdz();
    }
}
