package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.util.zzg;
import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzcap extends zzcav {
    private final Clock zzb;
    private final zzcap zzc = this;
    private final zzhky zzd;
    private final zzhky zze;
    private final zzhky zzf;
    private final zzhky zzg;
    private final zzhky zzh;
    private final zzhky zzi;
    private final zzhky zzj;
    private final zzhky zzk;

    /* synthetic */ zzcap(Context context, Clock clock, zzg zzg2, zzcau zzcau, zzcao zzcao) {
        this.zzb = clock;
        zzhkp zza = zzhkq.zza(context);
        this.zzd = zza;
        zzhkp zza2 = zzhkq.zza(zzg2);
        this.zze = zza2;
        this.zzf = zzhko.zzc(new zzcah(zza, zza2));
        zzhkp zza3 = zzhkq.zza(clock);
        this.zzg = zza3;
        zzhkp zza4 = zzhkq.zza(zzcau);
        this.zzh = zza4;
        zzhky zzc2 = zzhko.zzc(new zzcaj(zza3, zza2, zza4));
        this.zzi = zzc2;
        zzcal zzcal = new zzcal(zza3, zzc2);
        this.zzj = zzcal;
        this.zzk = zzhko.zzc(new zzcba(zza, zzcal));
    }

    /* access modifiers changed from: package-private */
    public final zzcag zza() {
        return (zzcag) this.zzf.zzb();
    }

    /* access modifiers changed from: package-private */
    public final zzcak zzb() {
        return new zzcak(this.zzb, (zzcai) this.zzi.zzb());
    }

    /* access modifiers changed from: package-private */
    public final zzcaz zzc() {
        return (zzcaz) this.zzk.zzb();
    }
}
