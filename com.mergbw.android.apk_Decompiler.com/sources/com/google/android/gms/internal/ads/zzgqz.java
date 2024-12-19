package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgqz extends RuntimeException {
    public zzgqz(String str) {
        super(str);
    }

    public static Object zza(zzgqy zzgqy) {
        try {
            return zzgqy.zza();
        } catch (Exception e) {
            throw new zzgqz((Throwable) e);
        }
    }

    public zzgqz(String str, Throwable th) {
        super(str, th);
    }

    public zzgqz(Throwable th) {
        super(th);
    }
}
