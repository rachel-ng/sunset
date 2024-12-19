package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzba {
    final String zza;
    final String zzb;
    final long zzc;
    final long zzd;
    final zzbc zze;
    private final String zzf;

    /* access modifiers changed from: package-private */
    public final zzba zza(zzhj zzhj, long j) {
        return new zzba(zzhj, this.zzf, this.zza, this.zzb, this.zzc, j, this.zze);
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        String valueOf = String.valueOf(this.zze);
        return "Event{appId='" + str + "', name='" + str2 + "', params=" + valueOf + VectorFormat.DEFAULT_SUFFIX;
    }

    zzba(zzhj zzhj, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzbc zzbc;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zza = str2;
        this.zzb = str3;
        this.zzf = TextUtils.isEmpty(str) ? null : str;
        this.zzc = j;
        this.zzd = j2;
        if (j2 != 0 && j2 > j) {
            zzhj.zzj().zzu().zza("Event created with reverse previous/current timestamps. appId", zzfw.zza(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzbc = new zzbc(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String str4 = (String) it.next();
                if (str4 == null) {
                    zzhj.zzj().zzg().zza("Param name can't be null");
                    it.remove();
                } else {
                    Object zzb2 = zzhj.zzt().zzb(str4, bundle2.get(str4));
                    if (zzb2 == null) {
                        zzhj.zzj().zzu().zza("Param value can't be null", zzhj.zzk().zzb(str4));
                        it.remove();
                    } else {
                        zzhj.zzt().zza(bundle2, str4, zzb2);
                    }
                }
            }
            zzbc = new zzbc(bundle2);
        }
        this.zze = zzbc;
    }

    private zzba(zzhj zzhj, String str, String str2, String str3, long j, long j2, zzbc zzbc) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzbc);
        this.zza = str2;
        this.zzb = str3;
        this.zzf = TextUtils.isEmpty(str) ? null : str;
        this.zzc = j;
        this.zzd = j2;
        if (j2 != 0 && j2 > j) {
            zzhj.zzj().zzu().zza("Event created with reverse previous/current timestamps. appId, name", zzfw.zza(str2), zzfw.zza(str3));
        }
        this.zze = zzbc;
    }
}
