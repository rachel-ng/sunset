package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzpp implements zzpq {
    private static final zzgz<Boolean> zza;
    private static final zzgz<Boolean> zzb;
    private static final zzgz<Boolean> zzc;
    private static final zzgz<Boolean> zzd;

    static {
        zzhh zza2 = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.sgtm.google_signal.enable", false);
        zzb = zza2.zza("measurement.sgtm.preview_mode_enabled", true);
        zzc = zza2.zza("measurement.sgtm.service", true);
        zzd = zza2.zza("measurement.sgtm.upload_queue", false);
        zza2.zza("measurement.id.sgtm", 0);
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
}
