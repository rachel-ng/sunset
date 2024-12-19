package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.OutputStream;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhar extends zzhao {
    private final OutputStream zzg;

    zzhar(OutputStream outputStream, int i) {
        super(i);
        if (outputStream != null) {
            this.zzg = outputStream;
            return;
        }
        throw new NullPointerException("out");
    }

    private final void zzJ() throws IOException {
        this.zzg.write(this.zza, 0, this.zzc);
        this.zzc = 0;
    }

    private final void zzK(int i) throws IOException {
        if (this.zzb - this.zzc < i) {
            zzJ();
        }
    }

    public final void zzL() throws IOException {
        if (this.zzc > 0) {
            zzJ();
        }
    }

    public final void zzM(byte b2) throws IOException {
        if (this.zzc == this.zzb) {
            zzJ();
        }
        zzc(b2);
    }

    public final void zzN(int i, boolean z) throws IOException {
        zzK(11);
        zzf(i << 3);
        zzc(z ? (byte) 1 : 0);
    }

    public final void zzO(int i, zzhac zzhac) throws IOException {
        zzu((i << 3) | 2);
        zzu(zzhac.zzd());
        zzhac.zzo(this);
    }

    public final void zza(byte[] bArr, int i, int i2) throws IOException {
        zzr(bArr, i, i2);
    }

    public final void zzh(int i, int i2) throws IOException {
        zzK(14);
        zzf((i << 3) | 5);
        zzd(i2);
    }

    public final void zzi(int i) throws IOException {
        zzK(4);
        zzd(i);
    }

    public final void zzj(int i, long j) throws IOException {
        zzK(18);
        zzf((i << 3) | 1);
        zze(j);
    }

    public final void zzk(long j) throws IOException {
        zzK(8);
        zze(j);
    }

    public final void zzl(int i, int i2) throws IOException {
        zzK(20);
        zzf(i << 3);
        if (i2 >= 0) {
            zzf(i2);
        } else {
            zzg((long) i2);
        }
    }

    public final void zzm(int i) throws IOException {
        if (i >= 0) {
            zzu(i);
        } else {
            zzw((long) i);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzn(int i, zzhde zzhde, zzhdz zzhdz) throws IOException {
        zzu((i << 3) | 2);
        zzu(((zzgzi) zzhde).zzaM(zzhdz));
        zzhdz.zzj(zzhde, this.zze);
    }

    public final void zzo(int i, zzhde zzhde) throws IOException {
        zzu(11);
        zzt(2, i);
        zzu(26);
        zzu(zzhde.zzaY());
        zzhde.zzda(this);
        zzu(12);
    }

    public final void zzp(int i, zzhac zzhac) throws IOException {
        zzu(11);
        zzt(2, i);
        zzO(3, zzhac);
        zzu(12);
    }

    public final void zzq(int i, String str) throws IOException {
        zzu((i << 3) | 2);
        zzx(str);
    }

    public final void zzr(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.zzb;
        int i4 = this.zzc;
        int i5 = i3 - i4;
        if (i5 >= i2) {
            System.arraycopy(bArr, i, this.zza, i4, i2);
            this.zzc += i2;
            this.zzd += i2;
            return;
        }
        System.arraycopy(bArr, i, this.zza, i4, i5);
        int i6 = i + i5;
        this.zzc = this.zzb;
        this.zzd += i5;
        zzJ();
        int i7 = i2 - i5;
        if (i7 <= this.zzb) {
            System.arraycopy(bArr, i6, this.zza, 0, i7);
            this.zzc = i7;
        } else {
            this.zzg.write(bArr, i6, i7);
        }
        this.zzd += i7;
    }

    public final void zzs(int i, int i2) throws IOException {
        zzu((i << 3) | i2);
    }

    public final void zzt(int i, int i2) throws IOException {
        zzK(20);
        zzf(i << 3);
        zzf(i2);
    }

    public final void zzu(int i) throws IOException {
        zzK(5);
        zzf(i);
    }

    public final void zzv(int i, long j) throws IOException {
        zzK(20);
        zzf(i << 3);
        zzg(j);
    }

    public final void zzw(long j) throws IOException {
        zzK(10);
        zzg(j);
    }

    public final void zzx(String str) throws IOException {
        int i;
        int i2;
        try {
            int length = str.length() * 3;
            int zzD = zzD(length);
            int i3 = zzD + length;
            int i4 = this.zzb;
            if (i3 > i4) {
                byte[] bArr = new byte[length];
                int zzd = zzhff.zzd(str, bArr, 0, length);
                zzu(zzd);
                zzr(bArr, 0, zzd);
                return;
            }
            if (i3 > i4 - this.zzc) {
                zzJ();
            }
            int zzD2 = zzD(str.length());
            i = this.zzc;
            if (zzD2 == zzD) {
                int i5 = i + zzD2;
                this.zzc = i5;
                int zzd2 = zzhff.zzd(str, this.zza, i5, this.zzb - i5);
                this.zzc = i;
                i2 = (zzd2 - i) - zzD2;
                zzf(i2);
                this.zzc = zzd2;
            } else {
                i2 = zzhff.zze(str);
                zzf(i2);
                this.zzc = zzhff.zzd(str, this.zza, this.zzc, i2);
            }
            this.zzd += i2;
        } catch (zzhfe e) {
            this.zzd -= this.zzc - i;
            this.zzc = i;
            throw e;
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new zzhaq(e2);
        } catch (zzhfe e3) {
            zzH(str, e3);
        }
    }
}
