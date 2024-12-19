package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfph {
    private final zzfqv zza;
    private final String zzb;
    private final zzfoq zzc;
    private final String zzd = "Ad overlay";

    public zzfph(View view, zzfoq zzfoq, String str) {
        this.zza = new zzfqv(view);
        this.zzb = view.getClass().getCanonicalName();
        this.zzc = zzfoq;
    }

    public final zzfoq zza() {
        return this.zzc;
    }

    public final zzfqv zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzd;
    }

    public final String zzd() {
        return this.zzb;
    }
}
