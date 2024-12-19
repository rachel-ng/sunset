package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzaa;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.zzt;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzu;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzejn implements zzdjp {
    private final Context zza;
    private final VersionInfoParcel zzb;
    private final ListenableFuture zzc;
    private final zzfgt zzd;
    private final zzchd zze;
    private final zzfho zzf;
    private final zzbls zzg;
    private final boolean zzh;
    private final zzegk zzi;

    zzejn(Context context, VersionInfoParcel versionInfoParcel, ListenableFuture listenableFuture, zzfgt zzfgt, zzchd zzchd, zzfho zzfho, boolean z, zzbls zzbls, zzegk zzegk) {
        this.zza = context;
        this.zzb = versionInfoParcel;
        this.zzc = listenableFuture;
        this.zzd = zzfgt;
        this.zze = zzchd;
        this.zzf = zzfho;
        this.zzg = zzbls;
        this.zzh = z;
        this.zzi = zzegk;
    }

    public final void zza(boolean z, Context context, zzczy zzczy) {
        zzdih zzdih = (zzdih) zzgft.zzq(this.zzc);
        this.zze.zzaq(true);
        boolean zze2 = this.zzh ? this.zzg.zze(false) : false;
        zzu.zzp();
        zzk zzk = new zzk(zze2, zzt.zzI(this.zza), this.zzh ? this.zzg.zzd() : false, this.zzh ? this.zzg.zza() : 0.0f, -1, z, this.zzd.zzP, false);
        if (zzczy != null) {
            zzczy.zzf();
        }
        zzu.zzi();
        zzdje zzh2 = zzdih.zzh();
        zzchd zzchd = this.zze;
        zzfgt zzfgt = this.zzd;
        VersionInfoParcel versionInfoParcel = this.zzb;
        int i = zzfgt.zzR;
        String str = zzfgt.zzC;
        zzfgy zzfgy = zzfgt.zzt;
        AdOverlayInfoParcel adOverlayInfoParcel = r4;
        AdOverlayInfoParcel adOverlayInfoParcel2 = new AdOverlayInfoParcel((zza) null, (zzp) zzh2, (zzaa) null, zzchd, i, versionInfoParcel, str, zzk, zzfgy.zzb, zzfgy.zza, this.zzf.zzf, zzczy, (zzbuz) zzfgt.zzaj ? this.zzi : null);
        zzn.zza(context, adOverlayInfoParcel, true);
    }
}
