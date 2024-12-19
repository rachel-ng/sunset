package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zze;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzflz implements zzdgn, zzdab, zzdgr {
    private final zzfmn zza;
    private final zzfmc zzb;

    zzflz(Context context, zzfmn zzfmn) {
        this.zza = zzfmn;
        this.zzb = zzfmb.zza(context, zzfmu.CUI_NAME_ADSHOW);
    }

    public final void zza() {
    }

    public final void zzb() {
        if (((Boolean) zzbgd.zzd.zze()).booleanValue()) {
            zzfmn zzfmn = this.zza;
            zzfmc zzfmc = this.zzb;
            zzfmc.zzh(true);
            zzfmn.zza(zzfmc);
        }
    }

    public final void zzk() {
    }

    public final void zzl() {
        if (((Boolean) zzbgd.zzd.zze()).booleanValue()) {
            this.zzb.zzj();
        }
    }

    public final void zzq(zze zze) {
        if (((Boolean) zzbgd.zzd.zze()).booleanValue()) {
            zzfmn zzfmn = this.zza;
            zzfmc zzfmc = this.zzb;
            zzfmc.zzc(zze.zza().toString());
            zzfmc.zzh(false);
            zzfmn.zza(zzfmc);
        }
    }
}
