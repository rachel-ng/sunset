package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzcmi implements zzdwm {
    /* access modifiers changed from: private */
    public final Context zza;
    private final zzbmo zzb;
    private final zzcla zzc;
    private final zzcmi zzd = this;
    private final zzhky zze;
    private final zzhky zzf;
    private final zzhky zzg;
    private final zzhky zzh;

    /* synthetic */ zzcmi(zzcla zzcla, Context context, zzbmo zzbmo, zzcmh zzcmh) {
        this.zzc = zzcla;
        this.zza = context;
        this.zzb = zzbmo;
        zzhkp zza2 = zzhkq.zza(this);
        this.zze = zza2;
        zzhkp zza3 = zzhkq.zza(zzbmo);
        this.zzf = zza3;
        zzdwi zzdwi = new zzdwi(zza3);
        this.zzg = zzdwi;
        this.zzh = zzhko.zzc(new zzdwk(zza2, zzdwi));
    }

    public final zzdwd zzb() {
        return new zzcmc(this.zzc, this.zzd, (zzcmb) null);
    }

    public final zzdwj zzd() {
        return (zzdwj) this.zzh.zzb();
    }
}
