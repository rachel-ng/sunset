package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzba;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfbm implements zzexw {
    private final zzcby zza;
    private final boolean zzb;
    private final boolean zzc;
    private final ScheduledExecutorService zzd;
    private final zzgge zze;
    private final String zzf;
    private final zzcbn zzg;

    zzfbm(zzcby zzcby, boolean z, boolean z2, zzcbn zzcbn, zzgge zzgge, String str, ScheduledExecutorService scheduledExecutorService) {
        this.zza = zzcby;
        this.zzb = z;
        this.zzc = z2;
        this.zzg = zzcbn;
        this.zze = zzgge;
        this.zzf = str;
        this.zzd = scheduledExecutorService;
    }

    public final int zza() {
        return 50;
    }

    public final ListenableFuture zzb() {
        if (((Boolean) zzba.zzc().zza(zzbep.zzhq)).booleanValue() && this.zzc) {
            return zzgft.zzh((Object) null);
        }
        if (!this.zzb) {
            return zzgft.zzh((Object) null);
        }
        return zzgft.zze(zzgft.zzo(zzgft.zzm(zzgft.zzh((Object) null), new zzfbk(), this.zze), ((Long) zzbgy.zzc.zze()).longValue(), TimeUnit.MILLISECONDS, this.zzd), Exception.class, new zzfbl(this), this.zze);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzfbn zzc(Exception exc) {
        this.zza.zzw(exc, "TrustlessTokenSignal");
        return null;
    }
}
