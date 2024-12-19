package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzfx {
    private static zzga zza;

    public static synchronized zzga zza() {
        zzga zzga;
        synchronized (zzfx.class) {
            if (zza == null) {
                zza(new zzfz());
            }
            zzga = zza;
        }
        return zzga;
    }

    private static synchronized void zza(zzga zzga) {
        synchronized (zzfx.class) {
            if (zza == null) {
                zza = zzga;
            } else {
                throw new IllegalStateException("init() already called");
            }
        }
    }
}
