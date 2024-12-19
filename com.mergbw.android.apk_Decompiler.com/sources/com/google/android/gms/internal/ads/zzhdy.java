package com.google.android.gms.internal.ads;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhdy extends zzhac {
    static final int[] zza = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    private final int zzc;
    /* access modifiers changed from: private */
    public final zzhac zzd;
    /* access modifiers changed from: private */
    public final zzhac zze;
    private final int zzf;
    private final int zzg;

    private zzhdy(zzhac zzhac, zzhac zzhac2) {
        this.zzd = zzhac;
        this.zze = zzhac2;
        int zzd2 = zzhac.zzd();
        this.zzf = zzd2;
        this.zzc = zzd2 + zzhac2.zzd();
        this.zzg = Math.max(zzhac.zzf(), zzhac2.zzf()) + 1;
    }

    static zzhac zzD(zzhac zzhac, zzhac zzhac2) {
        if (zzhac2.zzd() == 0) {
            return zzhac;
        }
        if (zzhac.zzd() == 0) {
            return zzhac2;
        }
        int zzd2 = zzhac.zzd() + zzhac2.zzd();
        if (zzd2 < 128) {
            return zzE(zzhac, zzhac2);
        }
        if (zzhac instanceof zzhdy) {
            zzhdy zzhdy = (zzhdy) zzhac;
            if (zzhdy.zze.zzd() + zzhac2.zzd() < 128) {
                return new zzhdy(zzhdy.zzd, zzE(zzhdy.zze, zzhac2));
            }
            if (zzhdy.zzd.zzf() > zzhdy.zze.zzf() && zzhdy.zzg > zzhac2.zzf()) {
                return new zzhdy(zzhdy.zzd, new zzhdy(zzhdy.zze, zzhac2));
            }
        }
        if (zzd2 >= zzc(Math.max(zzhac.zzf(), zzhac2.zzf()) + 1)) {
            return new zzhdy(zzhac, zzhac2);
        }
        return zzhdu.zza(new zzhdu((zzhdt) null), zzhac, zzhac2);
    }

    private static zzhac zzE(zzhac zzhac, zzhac zzhac2) {
        int zzd2 = zzhac.zzd();
        int zzd3 = zzhac2.zzd();
        byte[] bArr = new byte[(zzd2 + zzd3)];
        zzhac.zzA(bArr, 0, 0, zzd2);
        zzhac2.zzA(bArr, 0, zzd2, zzd3);
        return new zzgzy(bArr);
    }

    static int zzc(int i) {
        int[] iArr = zza;
        int length = iArr.length;
        if (i >= 47) {
            return Integer.MAX_VALUE;
        }
        return iArr[i];
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzhac)) {
            return false;
        }
        zzhac zzhac = (zzhac) obj;
        if (this.zzc != zzhac.zzd()) {
            return false;
        }
        if (this.zzc == 0) {
            return true;
        }
        int zzr = zzr();
        int zzr2 = zzhac.zzr();
        if (zzr != 0 && zzr2 != 0 && zzr != zzr2) {
            return false;
        }
        zzhdw zzhdw = new zzhdw(this, (zzhdv) null);
        zzgzx zza2 = zzhdw.next();
        zzhdw zzhdw2 = new zzhdw(zzhac, (zzhdv) null);
        zzgzx zza3 = zzhdw2.next();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int zzd2 = zza2.zzd() - i;
            int zzd3 = zza3.zzd() - i2;
            int min = Math.min(zzd2, zzd3);
            if (i == 0) {
                z = zza2.zzg(zza3, i2, min);
            } else {
                z = zza3.zzg(zza2, i, min);
            }
            if (!z) {
                return false;
            }
            i3 += min;
            int i4 = this.zzc;
            if (i3 < i4) {
                if (min == zzd2) {
                    zza2 = zzhdw.next();
                    i = 0;
                } else {
                    i += min;
                }
                if (min == zzd3) {
                    zza3 = zzhdw2.next();
                    i2 = 0;
                } else {
                    i2 += min;
                }
            } else if (i3 == i4) {
                return true;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public final /* synthetic */ Iterator iterator() {
        return new zzhds(this);
    }

    public final byte zza(int i) {
        zzz(i, this.zzc);
        return zzb(i);
    }

    /* access modifiers changed from: package-private */
    public final byte zzb(int i) {
        int i2 = this.zzf;
        if (i < i2) {
            return this.zzd.zzb(i);
        }
        return this.zze.zzb(i - i2);
    }

    public final int zzd() {
        return this.zzc;
    }

    /* access modifiers changed from: protected */
    public final void zze(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.zzf;
        if (i4 <= i5) {
            this.zzd.zze(bArr, i, i2, i3);
        } else if (i >= i5) {
            this.zze.zze(bArr, i - i5, i2, i3);
        } else {
            int i6 = i5 - i;
            this.zzd.zze(bArr, i, i2, i6);
            this.zze.zze(bArr, 0, i2 + i6, i3 - i6);
        }
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final boolean zzh() {
        return this.zzc >= zzc(this.zzg);
    }

    /* access modifiers changed from: protected */
    public final int zzi(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.zzf;
        if (i4 <= i5) {
            return this.zzd.zzi(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.zze.zzi(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.zze.zzi(this.zzd.zzi(i, i2, i6), 0, i3 - i6);
    }

    /* access modifiers changed from: protected */
    public final int zzj(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.zzf;
        if (i4 <= i5) {
            return this.zzd.zzj(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.zze.zzj(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.zze.zzj(this.zzd.zzj(i, i2, i6), 0, i3 - i6);
    }

    public final zzhac zzk(int i, int i2) {
        int zzq = zzq(i, i2, this.zzc);
        if (zzq == 0) {
            return zzhac.zzb;
        }
        if (zzq == this.zzc) {
            return this;
        }
        int i3 = this.zzf;
        if (i2 <= i3) {
            return this.zzd.zzk(i, i2);
        }
        if (i >= i3) {
            return this.zze.zzk(i - i3, i2 - i3);
        }
        zzhac zzhac = this.zzd;
        return new zzhdy(zzhac.zzk(i, zzhac.zzd()), this.zze.zzk(0, i2 - this.zzf));
    }

    public final zzham zzl() {
        ArrayList<ByteBuffer> arrayList = new ArrayList<>();
        zzhdw zzhdw = new zzhdw(this, (zzhdv) null);
        while (zzhdw.hasNext()) {
            arrayList.add(zzhdw.next().zzn());
        }
        int i = zzham.zzd;
        boolean z = false;
        int i2 = 0;
        for (ByteBuffer byteBuffer : arrayList) {
            i2 += byteBuffer.remaining();
            if (byteBuffer.hasArray()) {
                z |= true;
            } else {
                z = byteBuffer.isDirect() ? z | true : z | true;
            }
        }
        if (z) {
            return new zzhag(arrayList, i2, true, (zzhaf) null);
        }
        return zzham.zzI(new zzhce(arrayList), 4096);
    }

    /* access modifiers changed from: protected */
    public final String zzm(Charset charset) {
        return new String(zzB(), charset);
    }

    public final ByteBuffer zzn() {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public final void zzo(zzgzq zzgzq) throws IOException {
        this.zzd.zzo(zzgzq);
        this.zze.zzo(zzgzq);
    }

    public final boolean zzp() {
        zzhac zzhac = this.zzd;
        zzhac zzhac2 = this.zze;
        if (zzhac2.zzj(zzhac.zzj(0, 0, this.zzf), 0, zzhac2.zzd()) == 0) {
            return true;
        }
        return false;
    }

    public final zzgzv zzs() {
        return new zzhds(this);
    }
}
