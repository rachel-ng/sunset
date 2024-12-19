package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzcli implements zzfbt {
    private final zzcla zza;
    private Context zzb;
    private String zzc;

    /* synthetic */ zzcli(zzcla zzcla, zzclh zzclh) {
        this.zza = zzcla;
    }

    public final /* synthetic */ zzfbt zza(String str) {
        str.getClass();
        this.zzc = str;
        return this;
    }

    public final /* synthetic */ zzfbt zzb(Context context) {
        context.getClass();
        this.zzb = context;
        return this;
    }

    public final zzfbu zzc() {
        zzhkx.zzc(this.zzb, Context.class);
        zzhkx.zzc(this.zzc, String.class);
        return new zzclk(this.zza, this.zzb, this.zzc, (zzclj) null);
    }
}
