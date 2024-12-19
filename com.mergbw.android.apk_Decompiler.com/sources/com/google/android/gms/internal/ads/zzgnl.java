package com.google.android.gms.internal.ads;

import javax.crypto.Cipher;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgnl {
    public static final /* synthetic */ int zza = 0;
    private static final ThreadLocal zzb = new zzgnk();

    public static Cipher zza() {
        return (Cipher) zzb.get();
    }
}
