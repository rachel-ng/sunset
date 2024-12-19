package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzhjx extends zzhka implements zzasr {
    zzass zza;
    protected final String zzb = "moov";

    public zzhjx(String str) {
    }

    public final String zza() {
        return this.zzb;
    }

    public final void zzb(zzhkb zzhkb, ByteBuffer byteBuffer, long j, zzaso zzaso) throws IOException {
        zzhkb.zzb();
        byteBuffer.remaining();
        byteBuffer.remaining();
        this.zzd = zzhkb;
        this.zzf = zzhkb.zzb();
        zzhkb.zze(zzhkb.zzb() + j);
        this.zzg = zzhkb.zzb();
        this.zzc = zzaso;
    }

    public final void zzc(zzass zzass) {
        this.zza = zzass;
    }
}
