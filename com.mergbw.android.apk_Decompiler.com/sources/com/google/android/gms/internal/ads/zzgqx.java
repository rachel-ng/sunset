package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgqx {
    /* access modifiers changed from: private */
    public final Map zza;
    /* access modifiers changed from: private */
    public final Map zzb;
    /* access modifiers changed from: private */
    public final Map zzc;
    /* access modifiers changed from: private */
    public final Map zzd;

    /* synthetic */ zzgqx(zzgqr zzgqr, zzgqw zzgqw) {
        this.zza = new HashMap(zzgqr.zza);
        this.zzb = new HashMap(zzgqr.zzb);
        this.zzc = new HashMap(zzgqr.zzc);
        this.zzd = new HashMap(zzgqr.zzd);
    }

    public final zzghi zza(zzgqq zzgqq, @Nullable zzgic zzgic) throws GeneralSecurityException {
        zzgqt zzgqt = new zzgqt(zzgqq.getClass(), zzgqq.zzd(), (zzgqs) null);
        if (this.zzb.containsKey(zzgqt)) {
            return ((zzgon) this.zzb.get(zzgqt)).zza(zzgqq, zzgic);
        }
        String obj = zzgqt.toString();
        throw new GeneralSecurityException("No Key Parser for requested key type " + obj + " available");
    }

    public final zzghx zzb(zzgqq zzgqq) throws GeneralSecurityException {
        zzgqt zzgqt = new zzgqt(zzgqq.getClass(), zzgqq.zzd(), (zzgqs) null);
        if (this.zzd.containsKey(zzgqt)) {
            return ((zzgpq) this.zzd.get(zzgqt)).zza(zzgqq);
        }
        String obj = zzgqt.toString();
        throw new GeneralSecurityException("No Parameters Parser for requested key type " + obj + " available");
    }

    public final zzgqq zzc(zzghi zzghi, Class cls, @Nullable zzgic zzgic) throws GeneralSecurityException {
        zzgqv zzgqv = new zzgqv(zzghi.getClass(), cls, (zzgqu) null);
        if (this.zza.containsKey(zzgqv)) {
            return ((zzgor) this.zza.get(zzgqv)).zza(zzghi, zzgic);
        }
        String obj = zzgqv.toString();
        throw new GeneralSecurityException("No Key serializer for " + obj + " available");
    }

    public final zzgqq zzd(zzghx zzghx, Class cls) throws GeneralSecurityException {
        zzgqv zzgqv = new zzgqv(zzghx.getClass(), cls, (zzgqu) null);
        if (this.zzc.containsKey(zzgqv)) {
            return ((zzgpu) this.zzc.get(zzgqv)).zza(zzghx);
        }
        String obj = zzgqv.toString();
        throw new GeneralSecurityException("No Key Format serializer for " + obj + " available");
    }

    public final boolean zzi(zzgqq zzgqq) {
        return this.zzb.containsKey(new zzgqt(zzgqq.getClass(), zzgqq.zzd(), (zzgqs) null));
    }

    public final boolean zzj(zzgqq zzgqq) {
        return this.zzd.containsKey(new zzgqt(zzgqq.getClass(), zzgqq.zzd(), (zzgqs) null));
    }
}
