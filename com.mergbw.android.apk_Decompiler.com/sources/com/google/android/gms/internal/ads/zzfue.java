package com.google.android.gms.internal.ads;

import java.io.Closeable;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzfue implements Closeable {
    public static zzfuq zza() {
        return new zzfuq();
    }

    public static zzfuq zzb(int i, zzfup zzfup) {
        return new zzfuq(new zzfuc(i), new zzfud(), zzfup);
    }

    public static zzfuq zzc(zzfyw<Integer> zzfyw, zzfyw<Integer> zzfyw2, zzfup zzfup) {
        return new zzfuq(zzfyw, zzfyw2, zzfup);
    }

    static /* synthetic */ Integer zze() {
        return -1;
    }
}
