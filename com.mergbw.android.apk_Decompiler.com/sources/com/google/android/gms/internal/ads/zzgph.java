package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgph {
    private static final zzgph zza = new zzgph();
    private final Map zzb = new HashMap();

    zzgph() {
    }

    public static zzgph zzb() {
        return zza;
    }

    public final synchronized zzghx zza(String str) throws GeneralSecurityException {
        if (this.zzb.containsKey("AES128_GCM")) {
        } else {
            throw new GeneralSecurityException("Name AES128_GCM does not exist");
        }
        return (zzghx) this.zzb.get("AES128_GCM");
    }

    public final synchronized void zzc(String str, zzghx zzghx) throws GeneralSecurityException {
        if (!this.zzb.containsKey(str)) {
            this.zzb.put(str, zzghx);
        } else if (!((zzghx) this.zzb.get(str)).equals(zzghx)) {
            String valueOf = String.valueOf(this.zzb.get(str));
            String valueOf2 = String.valueOf(zzghx);
            throw new GeneralSecurityException("Parameters object with name " + str + " already exists (" + valueOf + "), cannot insert " + valueOf2);
        }
    }

    public final synchronized void zzd(Map map) throws GeneralSecurityException {
        for (Map.Entry entry : map.entrySet()) {
            zzc((String) entry.getKey(), (zzghx) entry.getValue());
        }
    }
}
