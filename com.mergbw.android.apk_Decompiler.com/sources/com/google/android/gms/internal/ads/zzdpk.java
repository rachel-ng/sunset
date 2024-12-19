package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzdpk implements zzban {
    public final /* synthetic */ zzchd zza;

    public /* synthetic */ zzdpk(zzchd zzchd) {
        this.zza = zzchd;
    }

    public final void zzdp(zzbam zzbam) {
        String str;
        HashMap hashMap = new HashMap();
        if (true != zzbam.zzj) {
            str = SessionDescription.SUPPORTED_SDP_VERSION;
        } else {
            str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        hashMap.put("isVisible", str);
        this.zza.zzd("onAdVisibilityChanged", hashMap);
    }
}
