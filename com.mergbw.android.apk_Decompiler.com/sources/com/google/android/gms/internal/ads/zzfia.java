package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.zzu;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfia {
    private final zzfgt zza;
    private final zzfgw zzb;
    private final zzefz zzc;
    private final zzfoe zzd;
    private final zzfmn zze;
    private final zzcqd zzf;

    public zzfia(zzefz zzefz, zzfoe zzfoe, zzfgt zzfgt, zzfgw zzfgw, zzcqd zzcqd, zzfmn zzfmn) {
        this.zza = zzfgt;
        this.zzb = zzfgw;
        this.zzc = zzefz;
        this.zzd = zzfoe;
        this.zzf = zzcqd;
        this.zze = zzfmn;
    }

    public final void zza(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzb((String) it.next(), 2);
        }
    }

    public final void zzb(String str, int i) {
        if (!this.zza.zzaj) {
            this.zzd.zzc(str, this.zze);
            return;
        }
        this.zzc.zzd(new zzegb(zzu.zzB().currentTimeMillis(), this.zzb.zzb, str, i));
    }

    public final void zzc(List list, int i) {
        ListenableFuture listenableFuture;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!((Boolean) zzba.zzc().zza(zzbep.zzka)).booleanValue() || !zzcqd.zzj(str)) {
                listenableFuture = zzgft.zzh(str);
            } else {
                listenableFuture = this.zzf.zzb(str, zzay.zze());
            }
            zzgft.zzr(listenableFuture, new zzfhz(this, i), zzcci.zza);
        }
    }
}
