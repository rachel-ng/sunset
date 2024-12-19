package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import android.os.Handler;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzcb implements zzdn {
    private final zzdr zza;
    private final zzdr zzb;
    private final zzdr zzc;
    private final zzdr zzd;
    private final zzdr zze;
    private final zzdr zzf;
    private final zzdr zzg;
    private final zzdr zzh;

    public zzcb(zzdr zzdr, zzdr zzdr2, zzdr zzdr3, zzdr zzdr4, zzdr zzdr5, zzdr zzdr6, zzdr zzdr7, zzdr zzdr8) {
        this.zza = zzdr;
        this.zzb = zzdr2;
        this.zzc = zzdr3;
        this.zzd = zzdr4;
        this.zze = zzdr5;
        this.zzf = zzdr6;
        this.zzg = zzdr7;
        this.zzh = zzdr8;
    }

    /* renamed from: zzb */
    public final zzca zza() {
        Handler handler = zzcr.zza;
        zzdp.zza(handler);
        Executor executor = zzcr.zzb;
        zzdp.zza(executor);
        return new zzca((Application) this.zza.zza(), (zzbw) this.zzb.zza(), handler, executor, (zze) this.zze.zza(), ((zzao) this.zzf).zza(), (zzbb) this.zzg.zza(), (zzap) this.zzh.zza());
    }
}
