package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzeir implements zzhkp {
    private final zzhlg zza;
    private final zzhlg zzb;
    private final zzhlg zzc;

    public zzeir(zzhlg zzhlg, zzhlg zzhlg2, zzhlg zzhlg3) {
        this.zza = zzhlg;
        this.zzb = zzhlg2;
        this.zzc = zzhlg3;
    }

    public final /* bridge */ /* synthetic */ Object zzb() {
        return new zzeiq((Context) this.zza.zzb(), (zzctg) this.zzb.zzb(), (Executor) this.zzc.zzb());
    }
}