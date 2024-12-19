package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzif {
    private final zzit zza;
    private final byte[] zzb;

    public final zzia zza() {
        this.zza.zzb();
        return new zzij(this.zzb);
    }

    public final zzit zzb() {
        return this.zza;
    }

    private zzif(int i) {
        byte[] bArr = new byte[i];
        this.zzb = bArr;
        this.zza = zzit.zzb(bArr);
    }
}
