package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzejl implements zzgfa {
    public final /* synthetic */ zzejo zza;
    public final /* synthetic */ zzfgt zzb;
    public final /* synthetic */ zzfhf zzc;
    public final /* synthetic */ zzdsh zzd;

    public /* synthetic */ zzejl(zzejo zzejo, zzfgt zzfgt, zzfhf zzfhf, zzdsh zzdsh) {
        this.zza = zzejo;
        this.zzb = zzfgt;
        this.zzc = zzfhf;
        this.zzd = zzdsh;
    }

    public final ListenableFuture zza(Object obj) {
        return this.zza.zzc(this.zzb, this.zzc, this.zzd, obj);
    }
}
