package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgqa {
    /* access modifiers changed from: private */
    public final Map zza;
    /* access modifiers changed from: private */
    public final Map zzb;

    private zzgqa() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
    }

    public final zzgqa zza(zzgpy zzgpy) throws GeneralSecurityException {
        if (zzgpy != null) {
            zzgqc zzgqc = new zzgqc(zzgpy.zzc(), zzgpy.zzd(), (zzgqb) null);
            if (this.zza.containsKey(zzgqc)) {
                zzgpy zzgpy2 = (zzgpy) this.zza.get(zzgqc);
                if (!zzgpy2.equals(zzgpy) || !zzgpy.equals(zzgpy2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal PrimitiveConstructor object for already existing object of type: ".concat(zzgqc.toString()));
                }
            } else {
                this.zza.put(zzgqc, zzgpy);
            }
            return this;
        }
        throw new NullPointerException("primitive constructor must be non-null");
    }

    public final zzgqa zzb(zzghy zzghy) throws GeneralSecurityException {
        Map map = this.zzb;
        Class zzb2 = zzghy.zzb();
        if (map.containsKey(zzb2)) {
            zzghy zzghy2 = (zzghy) this.zzb.get(zzb2);
            if (!zzghy2.equals(zzghy) || !zzghy.equals(zzghy2)) {
                throw new GeneralSecurityException("Attempt to register non-equal PrimitiveWrapper object or input class object for already existing object of type".concat(zzb2.toString()));
            }
        } else {
            this.zzb.put(zzb2, zzghy);
        }
        return this;
    }

    /* synthetic */ zzgqa(zzgpz zzgpz) {
        this.zza = new HashMap();
        this.zzb = new HashMap();
    }

    /* synthetic */ zzgqa(zzgqe zzgqe, zzgpz zzgpz) {
        this.zza = new HashMap(zzgqe.zza);
        this.zzb = new HashMap(zzgqe.zzb);
    }
}
