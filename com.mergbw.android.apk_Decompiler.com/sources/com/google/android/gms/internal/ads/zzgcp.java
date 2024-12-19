package com.google.android.gms.internal.ads;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgcp extends zzgbc {
    final /* synthetic */ zzgcq zza;

    zzgcp(zzgcq zzgcq) {
        this.zza = zzgcq;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzfyg.zza(i, this.zza.zzc, FirebaseAnalytics.Param.INDEX);
        int i2 = i + i;
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(this.zza.zzb[i2]), Objects.requireNonNull(this.zza.zzb[i2 + 1]));
    }

    public final int size() {
        return this.zza.zzc;
    }

    public final boolean zzf() {
        return true;
    }
}
