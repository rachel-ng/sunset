package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdo;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzit {
    final Context zza;
    String zzb;
    String zzc;
    String zzd;
    Boolean zze;
    long zzf;
    zzdo zzg;
    boolean zzh = true;
    Long zzi;
    String zzj;

    public zzit(Context context, zzdo zzdo, Long l) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zza = applicationContext;
        this.zzi = l;
        if (zzdo != null) {
            this.zzg = zzdo;
            this.zzb = zzdo.zzf;
            this.zzc = zzdo.zze;
            this.zzd = zzdo.zzd;
            this.zzh = zzdo.zzc;
            this.zzf = zzdo.zzb;
            this.zzj = zzdo.zzh;
            if (zzdo.zzg != null) {
                this.zze = Boolean.valueOf(zzdo.zzg.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
