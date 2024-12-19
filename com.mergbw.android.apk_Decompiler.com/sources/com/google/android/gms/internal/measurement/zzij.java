package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
class zzij extends zzih {
    protected final byte[] zzb;

    public byte zza(int i) {
        return this.zzb[i];
    }

    /* access modifiers changed from: protected */
    public int zzc() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public byte zzb(int i) {
        return this.zzb[i];
    }

    /* access modifiers changed from: protected */
    public final int zzb(int i, int i2, int i3) {
        return zzjm.zza(i, this.zzb, zzc(), i3);
    }

    public int zzb() {
        return this.zzb.length;
    }

    public final zzia zza(int i, int i2) {
        int zza = zza(0, i2, zzb());
        if (zza == 0) {
            return zzia.zza;
        }
        return new zzie(this.zzb, zzc(), zza);
    }

    zzij(byte[] bArr) {
        super();
        bArr.getClass();
        this.zzb = bArr;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzhx zzhx) throws IOException {
        zzhx.zza(this.zzb, zzc(), zzb());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzia) || zzb() != ((zzia) obj).zzb()) {
            return false;
        }
        if (zzb() == 0) {
            return true;
        }
        if (!(obj instanceof zzij)) {
            return obj.equals(this);
        }
        zzij zzij = (zzij) obj;
        int zza = zza();
        int zza2 = zzij.zza();
        if (zza == 0 || zza2 == 0 || zza == zza2) {
            return zza(zzij, 0, zzb());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzia zzia, int i, int i2) {
        if (i2 > zzia.zzb()) {
            int zzb2 = zzb();
            throw new IllegalArgumentException("Length too large: " + i2 + zzb2);
        } else if (i2 > zzia.zzb()) {
            int zzb3 = zzia.zzb();
            throw new IllegalArgumentException("Ran off end of other: 0, " + i2 + ", " + zzb3);
        } else if (!(zzia instanceof zzij)) {
            return zzia.zza(0, i2).equals(zza(0, i2));
        } else {
            zzij zzij = (zzij) zzia;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzij.zzb;
            int zzc = zzc() + i2;
            int zzc2 = zzc();
            int zzc3 = zzij.zzc();
            while (zzc2 < zzc) {
                if (bArr[zzc2] != bArr2[zzc3]) {
                    return false;
                }
                zzc2++;
                zzc3++;
            }
            return true;
        }
    }
}
