package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.util.client.zzm;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfhw implements zzblp {
    public final /* synthetic */ zzdhi zza;
    public final /* synthetic */ zzcqd zzb;
    public final /* synthetic */ zzfoe zzc;
    public final /* synthetic */ zzefz zzd;

    public /* synthetic */ zzfhw(zzdhi zzdhi, zzcqd zzcqd, zzfoe zzfoe, zzefz zzefz) {
        this.zza = zzdhi;
        this.zzb = zzcqd;
        this.zzc = zzfoe;
        this.zzd = zzefz;
    }

    public final void zza(Object obj, Map map) {
        zzchd zzchd = (zzchd) obj;
        zzblo.zzc(map, this.zza);
        String str = (String) map.get("u");
        if (str == null) {
            zzm.zzj("URL missing from click GMSG.");
            return;
        }
        zzefz zzefz = this.zzd;
        zzfoe zzfoe = this.zzc;
        zzgft.zzr(zzblo.zza(zzchd, str), new zzfhy(zzchd, this.zzb, zzfoe, zzefz), zzcci.zza);
    }
}
