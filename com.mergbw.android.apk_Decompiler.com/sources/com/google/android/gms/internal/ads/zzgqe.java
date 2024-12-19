package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgqe {
    /* access modifiers changed from: private */
    public final Map zza;
    /* access modifiers changed from: private */
    public final Map zzb;

    /* synthetic */ zzgqe(zzgqa zzgqa, zzgqd zzgqd) {
        this.zza = new HashMap(zzgqa.zza);
        this.zzb = new HashMap(zzgqa.zzb);
    }

    public final Class zza(Class cls) throws GeneralSecurityException {
        if (this.zzb.containsKey(cls)) {
            return ((zzghy) this.zzb.get(cls)).zza();
        }
        String obj = cls.toString();
        throw new GeneralSecurityException("No input primitive class for " + obj + " available");
    }

    public final Object zzb(zzghi zzghi, Class cls) throws GeneralSecurityException {
        zzgqc zzgqc = new zzgqc(zzghi.getClass(), cls, (zzgqb) null);
        if (this.zza.containsKey(zzgqc)) {
            return ((zzgpy) this.zza.get(zzgqc)).zza(zzghi);
        }
        String obj = zzgqc.toString();
        throw new GeneralSecurityException("No PrimitiveConstructor for " + obj + " available");
    }

    public final Object zzc(zzgqk zzgqk, Class cls) throws GeneralSecurityException {
        if (this.zzb.containsKey(cls)) {
            zzghy zzghy = (zzghy) this.zzb.get(cls);
            if (zzgqk.zzd().equals(zzghy.zza()) && zzghy.zza().equals(zzgqk.zzd())) {
                return zzghy.zzc(zzgqk);
            }
            throw new GeneralSecurityException("Input primitive type of the wrapper doesn't match the type of primitives in the provided PrimitiveSet");
        }
        throw new GeneralSecurityException("No wrapper found for ".concat(cls.toString()));
    }
}
