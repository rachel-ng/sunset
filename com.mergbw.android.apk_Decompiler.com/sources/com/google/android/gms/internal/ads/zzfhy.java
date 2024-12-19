package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.zzu;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfhy implements zzgfp {
    final /* synthetic */ zzchd zza;
    final /* synthetic */ zzcqd zzb;
    final /* synthetic */ zzfoe zzc;
    final /* synthetic */ zzefz zzd;

    zzfhy(zzchd zzchd, zzcqd zzcqd, zzfoe zzfoe, zzefz zzefz) {
        this.zza = zzchd;
        this.zzb = zzcqd;
        this.zzc = zzfoe;
        this.zzd = zzefz;
    }

    public final void zza(Throwable th) {
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        String str = (String) obj;
        if (!this.zza.zzD().zzaj) {
            if (!((Boolean) zzba.zzc().zza(zzbep.zzka)).booleanValue() || this.zzb == null || !zzcqd.zzj(str)) {
                this.zzc.zzc(str, (zzfmn) null);
            } else {
                this.zzb.zzi(str, this.zzc, zzay.zze());
            }
        } else {
            long currentTimeMillis = zzu.zzB().currentTimeMillis();
            String str2 = this.zza.zzR().zzb;
            int i = 2;
            if (!zzu.zzo().zzA(this.zza.getContext())) {
                if (!((Boolean) zzba.zzc().zza(zzbep.zzgx)).booleanValue() || !this.zza.zzD().zzT) {
                    i = 1;
                }
            }
            this.zzd.zzd(new zzegb(currentTimeMillis, str2, str, i));
        }
    }
}
