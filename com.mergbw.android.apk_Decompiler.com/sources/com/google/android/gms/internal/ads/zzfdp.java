package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzw;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfdp implements zzfjx {
    public final zzfej zza;
    public final zzfel zzb;
    public final zzl zzc;
    public final String zzd;
    public final Executor zze;
    public final zzw zzf;
    public final zzfjm zzg;

    public zzfdp(zzfej zzfej, zzfel zzfel, zzl zzl, String str, Executor executor, zzw zzw, zzfjm zzfjm) {
        this.zza = zzfej;
        this.zzb = zzfel;
        this.zzc = zzl;
        this.zzd = str;
        this.zze = executor;
        this.zzf = zzw;
        this.zzg = zzfjm;
    }

    public final zzfjm zza() {
        return this.zzg;
    }

    public final Executor zzb() {
        return this.zze;
    }
}
