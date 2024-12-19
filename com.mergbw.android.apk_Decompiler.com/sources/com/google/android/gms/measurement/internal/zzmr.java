package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzmr {
    private final Clock zza;
    private long zzb;

    public zzmr(Clock clock) {
        Preconditions.checkNotNull(clock);
        this.zza = clock;
    }

    public final void zza() {
        this.zzb = 0;
    }

    public final void zzb() {
        this.zzb = this.zza.elapsedRealtime();
    }

    public final boolean zza(long j) {
        if (this.zzb != 0 && this.zza.elapsedRealtime() - this.zzb < 3600000) {
            return false;
        }
        return true;
    }
}
