package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgpb {
    public static final /* synthetic */ int zza = 0;
    private static final zzgpa zzb = new zzgoz();
    private static final zzgpb zzc = zze();
    private final Map zzd = new HashMap();

    public static zzgpb zzb() {
        return zzc;
    }

    private final synchronized zzghi zzd(zzghx zzghx, @Nullable Integer num) throws GeneralSecurityException {
        zzgpa zzgpa;
        zzgpa = (zzgpa) this.zzd.get(zzghx.getClass());
        if (zzgpa != null) {
        } else {
            String obj = zzghx.toString();
            throw new GeneralSecurityException("Cannot create a new key for parameters " + obj + ": no key creator for this class was registered.");
        }
        return zzgpa.zza(zzghx, num);
    }

    private static zzgpb zze() {
        zzgpb zzgpb = new zzgpb();
        try {
            zzgpb.zzc(zzb, zzgov.class);
            return zzgpb;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("unexpected error.", e);
        }
    }

    public final zzghi zza(zzghx zzghx, @Nullable Integer num) throws GeneralSecurityException {
        return zzd(zzghx, num);
    }

    public final synchronized void zzc(zzgpa zzgpa, Class cls) throws GeneralSecurityException {
        zzgpa zzgpa2 = (zzgpa) this.zzd.get(cls);
        if (zzgpa2 != null) {
            if (!zzgpa2.equals(zzgpa)) {
                String obj = cls.toString();
                throw new GeneralSecurityException("Different key creator for parameters class " + obj + " already inserted");
            }
        }
        this.zzd.put(cls, zzgpa);
    }
}
