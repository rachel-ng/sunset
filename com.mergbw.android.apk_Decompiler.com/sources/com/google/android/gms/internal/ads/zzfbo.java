package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfbo implements zzhkp {
    private final zzhlg zza;
    private final zzhlg zzb;
    private final zzhlg zzc;
    private final zzhlg zzd;
    private final zzhlg zze;
    private final zzhlg zzf;
    private final zzhlg zzg;

    public zzfbo(zzhlg zzhlg, zzhlg zzhlg2, zzhlg zzhlg3, zzhlg zzhlg4, zzhlg zzhlg5, zzhlg zzhlg6, zzhlg zzhlg7) {
        this.zza = zzhlg;
        this.zzb = zzhlg2;
        this.zzc = zzhlg3;
        this.zzd = zzhlg4;
        this.zze = zzhlg5;
        this.zzf = zzhlg6;
        this.zzg = zzhlg7;
    }

    public final /* bridge */ /* synthetic */ Object zzb() {
        boolean booleanValue = ((zzfbc) this.zzb).zzb().booleanValue();
        boolean booleanValue2 = ((zzfbd) this.zzc).zzb().booleanValue();
        zzcbn zzcbn = new zzcbn();
        zzgge zzgge = zzcci.zza;
        zzhkx.zzb(zzgge);
        return new zzfbm((zzcby) this.zza.zzb(), booleanValue, booleanValue2, zzcbn, zzgge, ((zzfaz) this.zzf).zza(), (ScheduledExecutorService) this.zzg.zzb());
    }
}
