package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfvx extends zzfwq {
    private int zza;
    private String zzb;
    private byte zzc;

    zzfvx() {
    }

    public final zzfwq zza(String str) {
        this.zzb = str;
        return this;
    }

    public final zzfwq zzb(int i) {
        this.zza = i;
        this.zzc = 1;
        return this;
    }

    public final zzfwr zzc() {
        if (this.zzc == 1) {
            return new zzfvz(this.zza, this.zzb, (zzfvy) null);
        }
        throw new IllegalStateException("Missing required properties: statusCode");
    }
}
