package com.google.android.gms.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.internal.zzir;
import com.google.android.gms.measurement.internal.zziu;
import com.google.android.gms.measurement.internal.zzkk;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zza extends AppMeasurement.zza {
    private final zzkk zza;

    public final int zza(String str) {
        return this.zza.zza(str);
    }

    public final long zzf() {
        return this.zza.zzf();
    }

    public final Boolean zza() {
        return (Boolean) this.zza.zza(4);
    }

    public final Double zzb() {
        return (Double) this.zza.zza(2);
    }

    public final Integer zzc() {
        return (Integer) this.zza.zza(3);
    }

    public final Long zzd() {
        return (Long) this.zza.zza(1);
    }

    public final Object zza(int i) {
        return this.zza.zza(i);
    }

    public final String zzg() {
        return this.zza.zzg();
    }

    public final String zzh() {
        return this.zza.zzh();
    }

    public final String zzi() {
        return this.zza.zzi();
    }

    public final String zzj() {
        return this.zza.zzj();
    }

    public final String zze() {
        return (String) this.zza.zza(0);
    }

    public final List<Bundle> zza(String str, String str2) {
        return this.zza.zza(str, str2);
    }

    public final Map<String, Object> zza(boolean z) {
        return this.zza.zza((String) null, (String) null, z);
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        return this.zza.zza(str, str2, z);
    }

    public zza(zzkk zzkk) {
        super();
        Preconditions.checkNotNull(zzkk);
        this.zza = zzkk;
    }

    public final void zzb(String str) {
        this.zza.zzb(str);
    }

    public final void zza(String str, String str2, Bundle bundle) {
        this.zza.zza(str, str2, bundle);
    }

    public final void zzc(String str) {
        this.zza.zzc(str);
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        this.zza.zzb(str, str2, bundle);
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        this.zza.zza(str, str2, bundle, j);
    }

    public final void zza(zziu zziu) {
        this.zza.zza(zziu);
    }

    public final void zza(Bundle bundle) {
        this.zza.zza(bundle);
    }

    public final void zza(zzir zzir) {
        this.zza.zza(zzir);
    }

    public final void zzb(zziu zziu) {
        this.zza.zzb(zziu);
    }
}
