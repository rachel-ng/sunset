package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.OutputStream;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgha {
    private final OutputStream zza;

    private zzgha(OutputStream outputStream) {
        this.zza = outputStream;
    }

    public static zzgha zzb(OutputStream outputStream) {
        return new zzgha(outputStream);
    }

    public final void zza(zzgwu zzgwu) throws IOException {
        try {
            zzgwu.zzaU(this.zza);
        } finally {
            this.zza.close();
        }
    }
}
