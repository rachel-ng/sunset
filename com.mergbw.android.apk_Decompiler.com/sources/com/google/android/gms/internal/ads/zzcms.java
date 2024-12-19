package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zzq;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzcms implements zzfez {
    private final zzcla zza;
    private final zzcms zzb = this;
    private final zzhky zzc;
    private final zzhky zzd;
    private final zzhky zze;
    private final zzhky zzf;
    private final zzhky zzg;
    private final zzhky zzh;
    private final zzhky zzi;

    /* synthetic */ zzcms(zzcla zzcla, Context context, String str, zzq zzq, zzcmr zzcmr) {
        this.zza = zzcla;
        zzhkp zza2 = zzhkq.zza(context);
        this.zzc = zza2;
        zzhkp zza3 = zzhkq.zza(zzq);
        this.zzd = zza3;
        zzhkp zza4 = zzhkq.zza(str);
        this.zze = zza4;
        zzhky zzc2 = zzhko.zzc(new zzepd(zzcla.zzM));
        this.zzf = zzc2;
        zzhky zzc3 = zzhko.zzc(new zzffx(zzcla.zzaE));
        this.zzg = zzc3;
        zzhky zzhky = zzc2;
        zzhky zzhky2 = zzc3;
        zzhky zzc4 = zzhko.zzc(new zzfex(zza2, zzcla.zzc, zzcla.zzO, zzhky, zzhky2, zzfhq.zza()));
        this.zzh = zzc4;
        this.zzi = zzhko.zzc(new zzepl(zza2, zza3, zza4, zzc4, zzhky, zzhky2, zzcla.zzl, zzcla.zzQ, zzcla.zzM));
    }

    public final zzepk zza() {
        return (zzepk) this.zzi.zzb();
    }
}
