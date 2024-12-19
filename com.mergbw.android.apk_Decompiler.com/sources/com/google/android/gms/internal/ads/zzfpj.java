package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfpj {
    private static final zzfpj zza = new zzfpj();
    private Context zzb;

    private zzfpj() {
    }

    public static zzfpj zzb() {
        return zza;
    }

    public final Context zza() {
        return this.zzb;
    }

    public final void zzc(Context context) {
        this.zzb = context != null ? context.getApplicationContext() : null;
    }
}
