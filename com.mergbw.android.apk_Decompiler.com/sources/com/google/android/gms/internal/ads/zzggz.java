package com.google.android.gms.internal.ads;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzggz {
    private final InputStream zza;

    private zzggz(InputStream inputStream) {
        this.zza = inputStream;
    }

    public static zzggz zzb(byte[] bArr) {
        return new zzggz(new ByteArrayInputStream(bArr));
    }

    public final zzgwu zza() throws IOException {
        try {
            return zzgwu.zzg(this.zza, zzhay.zza());
        } finally {
            this.zza.close();
        }
    }
}
