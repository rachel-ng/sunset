package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzhay {
    static final zzhay zza = new zzhay(true);
    private static volatile boolean zzb = false;
    private static volatile zzhay zzc;
    private final Map zzd;

    zzhay() {
        this.zzd = new HashMap();
    }

    public static zzhay zza() {
        return zza;
    }

    public final zzhbm zzc(zzhde zzhde, int i) {
        return (zzhbm) this.zzd.get(new zzhax(zzhde, i));
    }

    zzhay(boolean z) {
        this.zzd = Collections.emptyMap();
    }

    public static zzhay zzb() {
        zzhay zzhay = zzc;
        if (zzhay != null) {
            return zzhay;
        }
        synchronized (zzhay.class) {
            zzhay zzhay2 = zzc;
            if (zzhay2 != null) {
                return zzhay2;
            }
            zzhay zzb2 = zzhbg.zzb(zzhay.class);
            zzc = zzb2;
            return zzb2;
        }
    }
}
