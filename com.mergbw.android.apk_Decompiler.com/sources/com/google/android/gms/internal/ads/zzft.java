package com.google.android.gms.internal.ads;

import androidx.core.view.MotionEventCompat;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzft {
    public byte[] zza;
    private int zzb;
    private int zzc;
    private int zzd;

    public zzft() {
        this.zza = zzgd.zzf;
    }

    public zzft(byte[] bArr, int i) {
        this.zza = bArr;
        this.zzd = i;
    }

    private final void zzp() {
        int i;
        int i2 = this.zzb;
        boolean z = false;
        if (i2 >= 0 && (i2 < (i = this.zzd) || (i2 == i && this.zzc == 0))) {
            z = true;
        }
        zzeq.zzf(z);
    }

    public final int zza() {
        return ((this.zzd - this.zzb) * 8) - this.zzc;
    }

    public final int zzb() {
        zzeq.zzf(this.zzc == 0);
        return this.zzb;
    }

    public final int zzc() {
        return (this.zzb * 8) + this.zzc;
    }

    public final int zzd(int i) {
        int i2;
        if (i == 0) {
            return 0;
        }
        this.zzc += i;
        int i3 = 0;
        while (true) {
            i2 = this.zzc;
            if (i2 <= 8) {
                break;
            }
            int i4 = i2 - 8;
            this.zzc = i4;
            byte[] bArr = this.zza;
            int i5 = this.zzb;
            this.zzb = i5 + 1;
            i3 |= (bArr[i5] & 255) << i4;
        }
        byte[] bArr2 = this.zza;
        int i6 = this.zzb;
        int i7 = i3 | ((bArr2[i6] & 255) >> (8 - i2));
        int i8 = 32 - i;
        if (i2 == 8) {
            this.zzc = 0;
            this.zzb = i6 + 1;
        }
        int i9 = (-1 >>> i8) & i7;
        zzp();
        return i9;
    }

    public final void zze() {
        if (this.zzc != 0) {
            this.zzc = 0;
            this.zzb++;
            zzp();
        }
    }

    public final void zzf(int i, int i2) {
        int min = Math.min(8 - this.zzc, 14);
        int i3 = this.zzc;
        int i4 = (8 - i3) - min;
        int i5 = MotionEventCompat.ACTION_POINTER_INDEX_MASK >> i3;
        byte[] bArr = this.zza;
        int i6 = this.zzb;
        byte b2 = (byte) ((i5 | ((1 << i4) - 1)) & bArr[i6]);
        bArr[i6] = b2;
        int i7 = 14 - min;
        int i8 = i & 16383;
        bArr[i6] = (byte) (b2 | ((i8 >>> i7) << i4));
        int i9 = i6 + 1;
        while (i7 > 8) {
            i7 -= 8;
            this.zza[i9] = (byte) (i8 >>> i7);
            i9++;
        }
        int i10 = 8 - i7;
        byte[] bArr2 = this.zza;
        byte b3 = (byte) (bArr2[i9] & ((1 << i10) - 1));
        bArr2[i9] = b3;
        bArr2[i9] = (byte) (((i8 & ((1 << i7) - 1)) << i10) | b3);
        zzm(14);
        zzp();
    }

    public final void zzg(byte[] bArr, int i, int i2) {
        int i3;
        int i4 = 0;
        while (true) {
            i3 = i2 >> 3;
            if (i4 >= i3) {
                break;
            }
            byte[] bArr2 = this.zza;
            int i5 = this.zzb;
            int i6 = i5 + 1;
            this.zzb = i6;
            byte b2 = bArr2[i5];
            int i7 = this.zzc;
            byte b3 = (byte) (b2 << i7);
            bArr[i4] = b3;
            bArr[i4] = (byte) (((bArr2[i6] & 255) >> (8 - i7)) | b3);
            i4++;
        }
        int i8 = i2 & 7;
        if (i8 != 0) {
            byte b4 = (byte) (bArr[i3] & (255 >> i8));
            bArr[i3] = b4;
            int i9 = this.zzc;
            if (i9 + i8 > 8) {
                byte[] bArr3 = this.zza;
                int i10 = this.zzb;
                this.zzb = i10 + 1;
                b4 = (byte) (b4 | ((bArr3[i10] & 255) << i9));
                bArr[i3] = b4;
                i9 -= 8;
            }
            int i11 = i9 + i8;
            this.zzc = i11;
            byte[] bArr4 = this.zza;
            int i12 = this.zzb;
            bArr[i3] = (byte) (((byte) (((255 & bArr4[i12]) >> (8 - i11)) << (8 - i8))) | b4);
            if (i11 == 8) {
                this.zzc = 0;
                this.zzb = i12 + 1;
            }
            zzp();
        }
    }

    public final void zzh(byte[] bArr, int i, int i2) {
        zzeq.zzf(this.zzc == 0);
        System.arraycopy(this.zza, this.zzb, bArr, 0, i2);
        this.zzb += i2;
        zzp();
    }

    public final void zzi(zzfu zzfu) {
        zzj(zzfu.zzM(), zzfu.zze());
        zzk(zzfu.zzd() * 8);
    }

    public final void zzj(byte[] bArr, int i) {
        this.zza = bArr;
        this.zzb = 0;
        this.zzc = 0;
        this.zzd = i;
    }

    public final void zzk(int i) {
        int i2 = i / 8;
        this.zzb = i2;
        this.zzc = i - (i2 * 8);
        zzp();
    }

    public final void zzl() {
        int i = this.zzc + 1;
        this.zzc = i;
        if (i == 8) {
            this.zzc = 0;
            this.zzb++;
        }
        zzp();
    }

    public final void zzm(int i) {
        int i2 = i / 8;
        int i3 = this.zzb + i2;
        this.zzb = i3;
        int i4 = this.zzc + (i - (i2 * 8));
        this.zzc = i4;
        if (i4 > 7) {
            this.zzb = i3 + 1;
            this.zzc = i4 - 8;
        }
        zzp();
    }

    public final void zzn(int i) {
        zzeq.zzf(this.zzc == 0);
        this.zzb += i;
        zzp();
    }

    public final boolean zzo() {
        byte b2 = this.zza[this.zzb] & (128 >> this.zzc);
        zzl();
        return b2 != 0;
    }
}
