package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzaa;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzu;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzehy implements zzdjp {
    private final VersionInfoParcel zza;
    private final ListenableFuture zzb;
    private final zzfgt zzc;
    private final zzchd zzd;
    private final zzfho zze;
    private final zzbls zzf;
    private final boolean zzg;
    private final zzegk zzh;

    zzehy(VersionInfoParcel versionInfoParcel, ListenableFuture listenableFuture, zzfgt zzfgt, zzchd zzchd, zzfho zzfho, boolean z, zzbls zzbls, zzegk zzegk) {
        this.zza = versionInfoParcel;
        this.zzb = listenableFuture;
        this.zzc = zzfgt;
        this.zzd = zzchd;
        this.zze = zzfho;
        this.zzg = z;
        this.zzf = zzbls;
        this.zzh = zzegk;
    }

    public final void zza(boolean z, Context context, zzczy zzczy) {
        zzcrq zzcrq = (zzcrq) zzgft.zzq(this.zzb);
        this.zzd.zzaq(true);
        boolean zze2 = this.zzg ? this.zzf.zze(true) : true;
        boolean z2 = this.zzg;
        zzk zzk = new zzk(zze2, true, z2 ? this.zzf.zzd() : false, z2 ? this.zzf.zza() : 0.0f, -1, z, this.zzc.zzP, false);
        if (zzczy != null) {
            zzczy.zzf();
        }
        zzu.zzi();
        zzdje zzg2 = zzcrq.zzg();
        zzchd zzchd = this.zzd;
        int i = this.zzc.zzR;
        if (i == -1) {
            zzw zzw = this.zze.zzj;
            if (zzw != null) {
                int i2 = zzw.zza;
                if (i2 == 1) {
                    i = 7;
                } else if (i2 == 2) {
                    i = 6;
                }
            }
            zzm.zze("Error setting app open orientation; no targeting orientation available.");
            i = this.zzc.zzR;
        }
        int i3 = i;
        VersionInfoParcel versionInfoParcel = this.zza;
        zzfgt zzfgt = this.zzc;
        String str = zzfgt.zzC;
        zzfgy zzfgy = zzfgt.zzt;
        AdOverlayInfoParcel adOverlayInfoParcel = r4;
        AdOverlayInfoParcel adOverlayInfoParcel2 = new AdOverlayInfoParcel((zza) null, (zzp) zzg2, (zzaa) null, zzchd, i3, versionInfoParcel, str, zzk, zzfgy.zzb, zzfgy.zza, this.zze.zzf, zzczy, (zzbuz) zzfgt.zzaj ? this.zzh : null);
        zzn.zza(context, adOverlayInfoParcel, true);
    }
}
