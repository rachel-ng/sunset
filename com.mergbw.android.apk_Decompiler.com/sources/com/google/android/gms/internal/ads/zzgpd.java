package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgpd {
    private static final zzgpd zza = new zzgpd();
    private final Map zzb = new HashMap();

    public static zzgpd zza() {
        return zza;
    }

    public final synchronized void zzb(zzgpc zzgpc, Class cls) throws GeneralSecurityException {
        zzgpc zzgpc2 = (zzgpc) this.zzb.get(cls);
        if (zzgpc2 != null) {
            if (!zzgpc2.equals(zzgpc)) {
                throw new GeneralSecurityException("Different key creator for parameters class already inserted");
            }
        }
        this.zzb.put(cls, zzgpc);
    }
}
