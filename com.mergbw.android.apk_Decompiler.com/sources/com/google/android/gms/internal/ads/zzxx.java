package com.google.android.gms.internal.ads;

import androidx.work.WorkRequest;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzxx {
    private final zzer zza = zzer.zza;

    /* access modifiers changed from: protected */
    public final zzxy zza(zzde zzde, int[] iArr, int i, zzzu zzzu, zzgbc zzgbc) {
        return new zzxy(zzde, iArr, 0, zzzu, WorkRequest.MIN_BACKOFF_MILLIS, 25000, 25000, AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 0.7f, 0.75f, zzgbc, this.zza);
    }
}
