package com.google.android.gms.internal.consent_sdk;

import android.app.Application;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzbc implements zzdn {
    private final zzdr zza;
    private final zzdr zzb;
    private final zzdr zzc;
    private final zzdr zzd;
    private final zzdr zze;
    private final zzdr zzf;

    public zzbc(zzdr zzdr, zzdr zzdr2, zzdr zzdr3, zzdr zzdr4, zzdr zzdr5, zzdr zzdr6) {
        this.zza = zzdr;
        this.zzb = zzdr2;
        this.zzc = zzdr3;
        this.zzd = zzdr4;
        this.zze = zzdr5;
        this.zzf = zzdr6;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        return new zzbb((Application) this.zza.zza(), (zzab) this.zzb.zza(), (zzbw) this.zzc.zza(), (zzap) this.zzd.zza(), (zzbp) this.zze.zza(), this.zzf);
    }
}
