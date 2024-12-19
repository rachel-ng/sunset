package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzpi implements zzpf {
    private static final zzgz<Boolean> zza;
    private static final zzgz<Boolean> zzb;
    private static final zzgz<Boolean> zzc;
    private static final zzgz<Boolean> zzd;
    private static final zzgz<Boolean> zze;
    private static final zzgz<Boolean> zzf;
    private static final zzgz<Boolean> zzg;
    private static final zzgz<Boolean> zzh;

    static {
        zzhh zza2 = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.rb.attribution.ad_campaign_info", false);
        zzb = zza2.zza("measurement.rb.attribution.client2", true);
        zza2.zza("measurement.rb.attribution.dma_fix", true);
        zzc = zza2.zza("measurement.rb.attribution.followup1.service", false);
        zza2.zza("measurement.rb.attribution.index_out_of_bounds_fix", true);
        zzd = zza2.zza("measurement.rb.attribution.registration_regardless_consent", false);
        zze = zza2.zza("measurement.rb.attribution.service", true);
        zzf = zza2.zza("measurement.rb.attribution.enable_trigger_redaction", true);
        zzg = zza2.zza("measurement.rb.attribution.uuid_generation", true);
        zza2.zza("measurement.id.rb.attribution.improved_retry", 0);
        zzh = zza2.zza("measurement.rb.attribution.improved_retry", true);
    }

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zza().booleanValue();
    }

    public final boolean zzc() {
        return zzb.zza().booleanValue();
    }

    public final boolean zzd() {
        return zzc.zza().booleanValue();
    }

    public final boolean zze() {
        return zzd.zza().booleanValue();
    }

    public final boolean zzf() {
        return zze.zza().booleanValue();
    }

    public final boolean zzg() {
        return zzf.zza().booleanValue();
    }

    public final boolean zzh() {
        return zzg.zza().booleanValue();
    }

    public final boolean zzi() {
        return zzh.zza().booleanValue();
    }
}
