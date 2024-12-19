package com.google.android.gms.internal.consent_sdk;

import android.os.Handler;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzbv implements zzdn {
    private final zzdr zza;
    private final zzdr zzb;
    private final zzdr zzc;

    public zzbv(zzdr zzdr, zzdr zzdr2, zzdr zzdr3) {
        this.zza = zzdr;
        this.zzb = zzdr2;
        this.zzc = zzdr3;
    }

    /* renamed from: zzb */
    public final zzbu zza() {
        Handler handler = zzcr.zza;
        zzdp.zza(handler);
        return new zzbu((zzbw) this.zza.zza(), handler, ((zzcb) this.zzc).zza());
    }
}
