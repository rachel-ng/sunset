package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzlm {
    /* access modifiers changed from: private */
    public long zza;
    /* access modifiers changed from: private */
    public float zzb;
    /* access modifiers changed from: private */
    public long zzc;

    public zzlm() {
        this.zza = C.TIME_UNSET;
        this.zzb = -3.4028235E38f;
        this.zzc = C.TIME_UNSET;
    }

    /* synthetic */ zzlm(zzlo zzlo, zzll zzll) {
        this.zza = zzlo.zza;
        this.zzb = zzlo.zzb;
        this.zzc = zzlo.zzc;
    }

    public final zzlm zzd(long j) {
        boolean z = true;
        if (j < 0) {
            if (j == C.TIME_UNSET) {
                j = -9223372036854775807L;
            } else {
                z = false;
            }
        }
        zzeq.zzd(z);
        this.zzc = j;
        return this;
    }

    public final zzlm zze(long j) {
        this.zza = j;
        return this;
    }

    public final zzlm zzf(float f) {
        boolean z = true;
        if (f <= 0.0f && f != -3.4028235E38f) {
            z = false;
        }
        zzeq.zzd(z);
        this.zzb = f;
        return this;
    }

    public final zzlo zzg() {
        return new zzlo(this, (zzln) null);
    }
}
