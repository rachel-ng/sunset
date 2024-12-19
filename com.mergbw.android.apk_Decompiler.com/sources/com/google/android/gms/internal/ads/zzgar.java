package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzgar {
    /* access modifiers changed from: private */
    public static final zzgar zza = new zzgao();
    /* access modifiers changed from: private */
    public static final zzgar zzb = new zzgap(-1);
    /* access modifiers changed from: private */
    public static final zzgar zzc = new zzgap(1);

    /* synthetic */ zzgar(zzgaq zzgaq) {
    }

    public static zzgar zzk() {
        return zza;
    }

    public abstract int zza();

    public abstract zzgar zzb(int i, int i2);

    public abstract zzgar zzc(long j, long j2);

    public abstract zzgar zzd(Object obj, Object obj2, Comparator comparator);

    public abstract zzgar zze(boolean z, boolean z2);

    public abstract zzgar zzf(boolean z, boolean z2);
}
