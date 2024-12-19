package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfeg {
    private final zzfjc zza;
    private final zzcys zzb;
    private final Executor zzc;
    /* access modifiers changed from: private */
    public zzfef zzd;

    public zzfeg(zzfjc zzfjc, zzcys zzcys, Executor executor) {
        this.zza = zzfjc;
        this.zzb = zzcys;
        this.zzc = executor;
    }

    /* access modifiers changed from: private */
    @Deprecated
    public final zzfjm zze() {
        zzfho zzg = this.zzb.zzg();
        return this.zza.zzc(zzg.zzd, zzg.zzf, zzg.zzj);
    }

    public final ListenableFuture zzc() {
        ListenableFuture listenableFuture;
        zzfef zzfef = this.zzd;
        if (zzfef != null) {
            return zzgft.zzh(zzfef);
        }
        if (!((Boolean) zzbgr.zza.zze()).booleanValue()) {
            zzfef zzfef2 = new zzfef((zzbxu) null, zze(), (zzfee) null);
            this.zzd = zzfef2;
            listenableFuture = zzgft.zzh(zzfef2);
        } else {
            Class<zzebh> cls = zzebh.class;
            listenableFuture = zzgft.zze(zzgft.zzm(zzgfk.zzu(this.zzb.zzb().zze(this.zza.zza())), new zzfed(this), this.zzc), cls, new zzfec(this), this.zzc);
        }
        return zzgft.zzm(listenableFuture, new zzfeb(), this.zzc);
    }
}
