package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgdx {
    public static char zza(long j) {
        char c2 = (char) ((int) j);
        zzfyg.zzg(((long) c2) == j, "Out of range: %s", j);
        return c2;
    }

    public static char zzb(byte b2, byte b3) {
        return (char) ((b2 << 8) | (b3 & 255));
    }
}
