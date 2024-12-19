package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzblh implements zzblp {
    zzblh() {
    }

    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        ((zzchd) obj).zzao(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(map.get("custom_close")));
    }
}
