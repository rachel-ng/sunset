package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzdrn implements zzhkp {
    private final zzhlg zza;
    private final zzhlg zzb;
    private final zzhlg zzc;

    public zzdrn(zzhlg zzhlg, zzhlg zzhlg2, zzhlg zzhlg3) {
        this.zza = zzhlg;
        this.zzb = zzhlg2;
        this.zzc = zzhlg3;
    }

    public final /* synthetic */ Object zzb() {
        int i = ((zzczc) this.zzc).zza().zzo.zza;
        if (i == 0) {
            throw null;
        } else if (i - 1 != 0) {
            return ((zzemy) this.zzb).zzb();
        } else {
            return ((zzemy) this.zza).zzb();
        }
    }
}
