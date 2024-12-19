package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgpm {
    public static final zzgze zza = zzgze.zzb(new byte[0]);

    public static final zzgze zza(int i) {
        return zzgze.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(i).array());
    }

    public static final zzgze zzb(int i) {
        return zzgze.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(i).array());
    }
}
