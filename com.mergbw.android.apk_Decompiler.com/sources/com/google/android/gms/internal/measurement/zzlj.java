package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzlj implements zzkr {
    private final zzkt zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    public final zzkt zza() {
        return this.zza;
    }

    public final zzle zzb() {
        int i = this.zzd;
        if ((i & 1) != 0) {
            return zzle.PROTO2;
        }
        if ((i & 4) == 4) {
            return zzle.EDITIONS;
        }
        return zzle.PROTO3;
    }

    /* access modifiers changed from: package-private */
    public final String zzd() {
        return this.zzb;
    }

    zzlj(zzkt zzkt, String str, Object[] objArr) {
        this.zza = zzkt;
        this.zzb = str;
        this.zzc = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.zzd = charAt;
            return;
        }
        char c2 = charAt & 8191;
        int i = 13;
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 >= 55296) {
                c2 |= (charAt2 & 8191) << i;
                i += 13;
                i2 = i3;
            } else {
                this.zzd = c2 | (charAt2 << i);
                return;
            }
        }
    }

    public final boolean zzc() {
        return (this.zzd & 2) == 2;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zze() {
        return this.zzc;
    }
}
