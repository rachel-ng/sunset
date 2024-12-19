package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzgl {
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final long zzd;
    private final /* synthetic */ zzgh zze;

    private final long zzb() {
        return this.zze.zzg().getLong(this.zza, 0);
    }

    public final Pair<String, Long> zza() {
        long j;
        this.zze.zzt();
        this.zze.zzt();
        long zzb2 = zzb();
        if (zzb2 == 0) {
            zzc();
            j = 0;
        } else {
            j = Math.abs(zzb2 - this.zze.zzb().currentTimeMillis());
        }
        long j2 = this.zzd;
        if (j < j2) {
            return null;
        }
        if (j > (j2 << 1)) {
            zzc();
            return null;
        }
        String string = this.zze.zzg().getString(this.zzc, (String) null);
        long j3 = this.zze.zzg().getLong(this.zzb, 0);
        zzc();
        if (string == null || j3 <= 0) {
            return zzgh.zza;
        }
        return new Pair<>(string, Long.valueOf(j3));
    }

    private zzgl(zzgh zzgh, String str, long j) {
        this.zze = zzgh;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(j > 0);
        this.zza = str + ":start";
        this.zzb = str + ":count";
        this.zzc = str + ":value";
        this.zzd = j;
    }

    private final void zzc() {
        this.zze.zzt();
        long currentTimeMillis = this.zze.zzb().currentTimeMillis();
        SharedPreferences.Editor edit = this.zze.zzg().edit();
        edit.remove(this.zzb);
        edit.remove(this.zzc);
        edit.putLong(this.zza, currentTimeMillis);
        edit.apply();
    }

    public final void zza(String str, long j) {
        this.zze.zzt();
        if (zzb() == 0) {
            zzc();
        }
        if (str == null) {
            str = "";
        }
        long j2 = this.zze.zzg().getLong(this.zzb, 0);
        if (j2 <= 0) {
            SharedPreferences.Editor edit = this.zze.zzg().edit();
            edit.putString(this.zzc, str);
            edit.putLong(this.zzb, 1);
            edit.apply();
            return;
        }
        long j3 = j2 + 1;
        boolean z = (this.zze.zzq().zzv().nextLong() & Long.MAX_VALUE) < Long.MAX_VALUE / j3;
        SharedPreferences.Editor edit2 = this.zze.zzg().edit();
        if (z) {
            edit2.putString(this.zzc, str);
        }
        edit2.putLong(this.zzb, j3);
        edit2.apply();
    }
}
