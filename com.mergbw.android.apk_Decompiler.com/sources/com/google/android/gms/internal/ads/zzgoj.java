package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgoj {
    private static final Logger zza = Logger.getLogger(zzgoj.class.getName());
    private static final zzgoj zzb = new zzgoj();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final ConcurrentMap zzd = new ConcurrentHashMap();

    public static zzgoj zzc() {
        return zzb;
    }

    private final synchronized zzghj zzg(String str) throws GeneralSecurityException {
        if (this.zzc.containsKey(str)) {
        } else {
            throw new GeneralSecurityException("No key manager found for key type ".concat(String.valueOf(str)));
        }
        return (zzghj) this.zzc.get(str);
    }

    private final synchronized void zzh(zzghj zzghj, boolean z, boolean z2) throws GeneralSecurityException {
        String str = ((zzgos) zzghj).zza;
        if (this.zzd.containsKey(str)) {
            if (!((Boolean) this.zzd.get(str)).booleanValue()) {
                throw new GeneralSecurityException("New keys are already disallowed for key type ".concat(str));
            }
        }
        zzghj zzghj2 = (zzghj) this.zzc.get(str);
        if (zzghj2 != null) {
            if (!zzghj2.getClass().equals(zzghj.getClass())) {
                zza.logp(Level.WARNING, "com.google.crypto.tink.internal.KeyManagerRegistry", "insertKeyManager", "Attempted overwrite of a registered key manager for key type ".concat(str));
                throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", new Object[]{str, zzghj2.getClass().getName(), zzghj.getClass().getName()}));
            }
        }
        this.zzc.putIfAbsent(str, zzghj);
        this.zzd.put(str, true);
    }

    public final zzghj zza(String str, Class cls) throws GeneralSecurityException {
        zzghj zzg = zzg(str);
        if (zzg.zzb().equals(cls)) {
            return zzg;
        }
        String name = cls.getName();
        String valueOf = String.valueOf(zzg.getClass());
        String obj = zzg.zzb().toString();
        throw new GeneralSecurityException("Primitive type " + name + " not supported by key manager of type " + valueOf + ", which only supports: " + obj);
    }

    public final zzghj zzb(String str) throws GeneralSecurityException {
        return zzg(str);
    }

    public final synchronized void zzd(zzghj zzghj, boolean z) throws GeneralSecurityException {
        zzf(zzghj, 1, true);
    }

    public final boolean zze(String str) {
        return ((Boolean) this.zzd.get(str)).booleanValue();
    }

    public final synchronized void zzf(zzghj zzghj, int i, boolean z) throws GeneralSecurityException {
        if (zzgoc.zza(i)) {
            zzh(zzghj, false, true);
        } else {
            throw new GeneralSecurityException("Cannot register key manager: FIPS compatibility insufficient");
        }
    }
}
