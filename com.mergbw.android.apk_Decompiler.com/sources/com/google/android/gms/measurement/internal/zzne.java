package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzne implements zzgc {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zznc zzb;

    zzne(zznc zznc, String str) {
        this.zza = str;
        this.zzb = zznc;
    }

    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzb.zza(true, i, th, bArr, this.zza);
    }
}
