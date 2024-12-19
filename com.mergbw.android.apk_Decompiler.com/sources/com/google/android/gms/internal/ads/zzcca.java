package com.google.android.gms.internal.ads;

import java.math.BigInteger;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcca {
    private BigInteger zza = BigInteger.ONE;
    private String zzb = SessionDescription.SUPPORTED_SDP_VERSION;

    public final synchronized String zza() {
        String bigInteger;
        bigInteger = this.zza.toString();
        this.zza = this.zza.add(BigInteger.ONE);
        this.zzb = bigInteger;
        return bigInteger;
    }

    public final synchronized String zzb() {
        return this.zzb;
    }
}
