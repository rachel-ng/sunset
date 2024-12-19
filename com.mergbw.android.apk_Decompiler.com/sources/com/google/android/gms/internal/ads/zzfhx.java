package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfhx implements zzblp {
    public final /* synthetic */ zzfoe zza;
    public final /* synthetic */ zzefz zzb;

    public /* synthetic */ zzfhx(zzfoe zzfoe, zzefz zzefz) {
        this.zza = zzfoe;
        this.zzb = zzefz;
    }

    public final void zza(Object obj, Map map) {
        zzcgu zzcgu = (zzcgu) obj;
        String str = (String) map.get("u");
        if (str == null) {
            zzm.zzj("URL missing from httpTrack GMSG.");
        } else if (!zzcgu.zzD().zzaj) {
            this.zza.zzc(str, (zzfmn) null);
        } else {
            this.zzb.zzd(new zzegb(zzu.zzB().currentTimeMillis(), ((zzcig) zzcgu).zzR().zzb, str, 2));
        }
    }
}
