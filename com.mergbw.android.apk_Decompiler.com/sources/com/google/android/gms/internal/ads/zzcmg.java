package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzcmg implements zzdwl {
    private final zzcla zza;
    private Context zzb;
    private zzbmo zzc;

    /* synthetic */ zzcmg(zzcla zzcla, zzcmf zzcmf) {
        this.zza = zzcla;
    }

    public final /* synthetic */ zzdwl zza(zzbmo zzbmo) {
        zzbmo.getClass();
        this.zzc = zzbmo;
        return this;
    }

    public final /* synthetic */ zzdwl zzb(Context context) {
        context.getClass();
        this.zzb = context;
        return this;
    }

    public final zzdwm zzc() {
        zzhkx.zzc(this.zzb, Context.class);
        zzhkx.zzc(this.zzc, zzbmo.class);
        return new zzcmi(this.zza, this.zzb, this.zzc, (zzcmh) null);
    }
}
