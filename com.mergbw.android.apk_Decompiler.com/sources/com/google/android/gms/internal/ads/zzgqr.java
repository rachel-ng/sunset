package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgqr {
    /* access modifiers changed from: private */
    public final Map zza;
    /* access modifiers changed from: private */
    public final Map zzb;
    /* access modifiers changed from: private */
    public final Map zzc;
    /* access modifiers changed from: private */
    public final Map zzd;

    public zzgqr() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new HashMap();
        this.zzd = new HashMap();
    }

    public final zzgqr zza(zzgon zzgon) throws GeneralSecurityException {
        zzgqt zzgqt = new zzgqt(zzgon.zzd(), zzgon.zzc(), (zzgqs) null);
        if (this.zzb.containsKey(zzgqt)) {
            zzgon zzgon2 = (zzgon) this.zzb.get(zzgqt);
            if (!zzgon2.equals(zzgon) || !zzgon.equals(zzgon2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: ".concat(zzgqt.toString()));
            }
        } else {
            this.zzb.put(zzgqt, zzgon);
        }
        return this;
    }

    public final zzgqr zzb(zzgor zzgor) throws GeneralSecurityException {
        zzgqv zzgqv = new zzgqv(zzgor.zzc(), zzgor.zzd(), (zzgqu) null);
        if (this.zza.containsKey(zzgqv)) {
            zzgor zzgor2 = (zzgor) this.zza.get(zzgqv);
            if (!zzgor2.equals(zzgor) || !zzgor.equals(zzgor2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(zzgqv.toString()));
            }
        } else {
            this.zza.put(zzgqv, zzgor);
        }
        return this;
    }

    public final zzgqr zzc(zzgpq zzgpq) throws GeneralSecurityException {
        zzgqt zzgqt = new zzgqt(zzgpq.zzd(), zzgpq.zzc(), (zzgqs) null);
        if (this.zzd.containsKey(zzgqt)) {
            zzgpq zzgpq2 = (zzgpq) this.zzd.get(zzgqt);
            if (!zzgpq2.equals(zzgpq) || !zzgpq.equals(zzgpq2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: ".concat(zzgqt.toString()));
            }
        } else {
            this.zzd.put(zzgqt, zzgpq);
        }
        return this;
    }

    public final zzgqr zzd(zzgpu zzgpu) throws GeneralSecurityException {
        zzgqv zzgqv = new zzgqv(zzgpu.zzc(), zzgpu.zzd(), (zzgqu) null);
        if (this.zzc.containsKey(zzgqv)) {
            zzgpu zzgpu2 = (zzgpu) this.zzc.get(zzgqv);
            if (!zzgpu2.equals(zzgpu) || !zzgpu.equals(zzgpu2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(zzgqv.toString()));
            }
        } else {
            this.zzc.put(zzgqv, zzgpu);
        }
        return this;
    }

    public zzgqr(zzgqx zzgqx) {
        this.zza = new HashMap(zzgqx.zza);
        this.zzb = new HashMap(zzgqx.zzb);
        this.zzc = new HashMap(zzgqx.zzc);
        this.zzd = new HashMap(zzgqx.zzd);
    }
}
