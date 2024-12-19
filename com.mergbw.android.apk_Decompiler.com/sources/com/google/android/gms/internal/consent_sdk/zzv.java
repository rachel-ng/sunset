package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import android.os.Handler;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzv implements zzdn {
    private final zzdr zza;
    private final zzdr zzb;
    private final zzdr zzc;
    private final zzdr zzd;
    private final zzdr zze;
    private final zzdr zzf;
    private final zzdr zzg;
    private final zzdr zzh;
    private final zzdr zzi;

    public zzv(zzdr zzdr, zzdr zzdr2, zzdr zzdr3, zzdr zzdr4, zzdr zzdr5, zzdr zzdr6, zzdr zzdr7, zzdr zzdr8, zzdr zzdr9) {
        this.zza = zzdr;
        this.zzb = zzdr2;
        this.zzc = zzdr3;
        this.zzd = zzdr4;
        this.zze = zzdr5;
        this.zzf = zzdr6;
        this.zzg = zzdr7;
        this.zzh = zzdr8;
        this.zzi = zzdr9;
    }

    /* renamed from: zzb */
    public final zzu zza() {
        Handler handler = zzcr.zza;
        zzdp.zza(handler);
        Executor executor = zzcr.zzb;
        zzdp.zza(executor);
        return new zzu((Application) this.zza.zza(), (zzab) this.zzb.zza(), handler, executor, (zzap) this.zze.zza(), (zzbn) this.zzf.zza(), ((zzo) this.zzg).zza(), ((zzaa) this.zzh).zza(), (zze) this.zzi.zza());
    }
}
