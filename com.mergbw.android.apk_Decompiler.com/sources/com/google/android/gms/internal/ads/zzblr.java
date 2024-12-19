package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.ads.internal.util.client.zzm;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzblr implements zzblp {
    private final zzbls zza;

    public zzblr(zzbls zzbls) {
        this.zza = zzbls;
    }

    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        zzchd zzchd = (zzchd) obj;
        boolean equals = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(map.get("transparentBackground"));
        boolean equals2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(map.get("blur"));
        float f = 0.0f;
        try {
            if (map.get("blurRadius") != null) {
                f = Float.parseFloat((String) map.get("blurRadius"));
            }
        } catch (NumberFormatException e) {
            zzm.zzh("Fail to parse float", e);
        }
        this.zza.zzc(equals);
        this.zza.zzb(equals2, f);
        zzchd.zzay(equals);
    }
}
