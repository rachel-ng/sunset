package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfdn implements zzgfa {
    public final /* synthetic */ zzfdq zza;
    public final /* synthetic */ zzfel zzb;
    public final /* synthetic */ zzfdp zzc;
    public final /* synthetic */ zzfej zzd;
    public final /* synthetic */ zzcys zze;

    public /* synthetic */ zzfdn(zzfdq zzfdq, zzfel zzfel, zzfdp zzfdp, zzfej zzfej, zzcys zzcys) {
        this.zza = zzfdq;
        this.zzb = zzfel;
        this.zzc = zzfdp;
        this.zzd = zzfej;
        this.zze = zzcys;
    }

    public final ListenableFuture zza(Object obj) {
        return this.zza.zzb(this.zzb, this.zzc, this.zzd, this.zze, (zzfdv) obj);
    }
}
