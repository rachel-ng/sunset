package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashSet;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzcks extends zzezk {
    private final zzfar zza;
    private final zzcla zzb;
    private final zzcks zzc = this;
    private final zzhky zzd;
    private final zzhky zze;
    private final zzhky zzf;
    private final zzhky zzg;
    private final zzhky zzh;
    private final zzhky zzi;
    private final zzhky zzj;
    private final zzhky zzk;
    private final zzhky zzl;
    private final zzhky zzm;
    private final zzhky zzn;

    /* synthetic */ zzcks(zzcla zzcla, zzfar zzfar, zzckr zzckr) {
        this.zzb = zzcla;
        this.zza = zzfar;
        zzfat zzfat = new zzfat(zzfar);
        this.zzd = zzfat;
        zzhky zzc2 = zzhko.zzc(zzdtx.zza());
        this.zze = zzc2;
        zzhky zzc3 = zzhko.zzc(zzdtv.zza());
        this.zzf = zzc3;
        zzhky zzc4 = zzhko.zzc(zzdtz.zza());
        this.zzg = zzc4;
        zzhky zzc5 = zzhko.zzc(zzdub.zza());
        this.zzh = zzc5;
        zzhks zzc6 = zzhkt.zzc(4);
        zzc6.zzb(zzfln.GMS_SIGNALS, zzc2);
        zzc6.zzb(zzfln.BUILD_URL, zzc3);
        zzc6.zzb(zzfln.HTTP, zzc4);
        zzc6.zzb(zzfln.PRE_PROCESS, zzc5);
        zzhkt zzc7 = zzc6.zzc();
        this.zzi = zzc7;
        zzhky zzc8 = zzhko.zzc(new zzduc(zzfat, zzcla.zzh, zzfko.zza(), zzc7));
        this.zzj = zzc8;
        zzhlc zza2 = zzhld.zza(0, 1);
        zza2.zza(zzc8);
        zzhld zzc9 = zza2.zzc();
        this.zzk = zzc9;
        zzflw zzflw = new zzflw(zzc9);
        this.zzl = zzflw;
        this.zzm = zzhko.zzc(new zzflv(zzfko.zza(), zzcla.zze, zzflw));
        this.zzn = zzhko.zzc(new zzfmo(zzcla.zzz));
    }

    public final zzexz zza() {
        Context zzb2 = this.zzb.zza.zzb();
        zzhkx.zzb(zzb2);
        zzcbm zzcbm = new zzcbm();
        zzgge zzgge = zzcci.zza;
        zzhkx.zzb(zzgge);
        zzfal zzfal = new zzfal(zzcbm, zzgge, zzfas.zza(this.zza));
        zzgge zzgge2 = zzcci.zza;
        zzhkx.zzb(zzgge2);
        HashSet hashSet = new HashSet();
        hashSet.add(new zzewe(zzfal, 0, (ScheduledExecutorService) this.zzb.zze.zzb()));
        return new zzexz(zzb2, zzgge2, hashSet, (zzfmn) this.zzn.zzb(), (zzdvc) this.zzb.zzM.zzb());
    }

    public final zzflt zzb() {
        return (zzflt) this.zzm.zzb();
    }
}
