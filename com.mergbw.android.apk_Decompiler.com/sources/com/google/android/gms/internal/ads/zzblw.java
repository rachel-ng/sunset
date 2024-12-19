package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzblw implements zzblp {
    static final Map zza = CollectionUtils.mapOfKeyValueArrays(new String[]{"resize", "playVideo", "storePicture", "createCalendarEvent", "setOrientationProperties", "closeResizedAd", "unload"}, new Integer[]{1, 2, 3, 4, 5, 6, 7});
    private final zzb zzb;
    private final zzbud zzc;
    private final zzbuk zzd;

    public zzblw(zzb zzb2, zzbud zzbud, zzbuk zzbuk) {
        this.zzb = zzb2;
        this.zzc = zzbud;
        this.zzd = zzbuk;
    }

    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        zzchd zzchd = (zzchd) obj;
        int intValue = ((Integer) zza.get((String) map.get("a"))).intValue();
        int i = 6;
        boolean z = true;
        if (intValue != 5) {
            if (intValue != 7) {
                if (!this.zzb.zzc()) {
                    this.zzb.zzb((String) null);
                    return;
                } else if (intValue == 1) {
                    this.zzc.zzb(map);
                    return;
                } else if (intValue == 3) {
                    new zzbug(zzchd, map).zzb();
                    return;
                } else if (intValue == 4) {
                    new zzbua(zzchd, map).zzc();
                    return;
                } else if (intValue != 5) {
                    if (intValue == 6) {
                        this.zzc.zza(true);
                        return;
                    } else if (intValue != 7) {
                        zzm.zzi("Unknown MRAID command called.");
                        return;
                    }
                }
            }
            this.zzd.zzc();
            return;
        }
        String str = (String) map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            z = Boolean.parseBoolean((String) map.get("allowOrientationChange"));
        }
        if (zzchd == null) {
            zzm.zzj("AdWebView is null");
            return;
        }
        if ("portrait".equalsIgnoreCase(str)) {
            i = 7;
        } else if (!"landscape".equalsIgnoreCase(str)) {
            i = z ? -1 : 14;
        }
        zzchd.zzau(i);
    }
}
