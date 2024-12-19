package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzhac implements Iterable<Byte>, Serializable {
    private static final Comparator zza = new zzgzs();
    public static final zzhac zzb = new zzgzy(zzhcb.zzd);
    private static final zzhab zzd = new zzhab((zzhaa) null);
    private int zzc = 0;

    static {
        int i = zzgzm.zza;
    }

    zzhac() {
    }

    public static zzgzz zzt() {
        return new zzgzz(128);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.util.Collection, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzhac zzu(java.lang.Iterable r3) {
        /*
            boolean r0 = r3 instanceof java.util.Collection
            if (r0 != 0) goto L_0x0015
            java.util.Iterator r0 = r3.iterator()
            r1 = 0
        L_0x0009:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0019
            r0.next()
            int r1 = r1 + 1
            goto L_0x0009
        L_0x0015:
            int r1 = r3.size()
        L_0x0019:
            if (r1 != 0) goto L_0x001e
            com.google.android.gms.internal.ads.zzhac r3 = zzb
            return r3
        L_0x001e:
            java.util.Iterator r3 = r3.iterator()
            com.google.android.gms.internal.ads.zzhac r3 = zzc(r3, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhac.zzu(java.lang.Iterable):com.google.android.gms.internal.ads.zzhac");
    }

    public static zzhac zzv(byte[] bArr, int i, int i2) {
        zzq(i, i + i2, bArr.length);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new zzgzy(bArr2);
    }

    public static zzhac zzw(String str) {
        return new zzgzy(str.getBytes(zzhcb.zzb));
    }

    static void zzz(int i, int i2) {
        if (((i2 - (i + 1)) | i) >= 0) {
            return;
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + i2);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzc;
        if (i == 0) {
            int zzd2 = zzd();
            i = zzi(zzd2, 0, zzd2);
            if (i == 0) {
                i = 1;
            }
            this.zzc = i;
        }
        return i;
    }

    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(zzd()), zzd() <= 50 ? zzheo.zza(this) : zzheo.zza(zzk(0, 47)).concat("...")});
    }

    @Deprecated
    public final void zzA(byte[] bArr, int i, int i2, int i3) {
        zzq(0, i3, zzd());
        zzq(i2, i2 + i3, bArr.length);
        if (i3 > 0) {
            zze(bArr, 0, i2, i3);
        }
    }

    public final byte[] zzB() {
        int zzd2 = zzd();
        if (zzd2 == 0) {
            return zzhcb.zzd;
        }
        byte[] bArr = new byte[zzd2];
        zze(bArr, 0, 0, zzd2);
        return bArr;
    }

    public abstract byte zza(int i);

    /* access modifiers changed from: package-private */
    public abstract byte zzb(int i);

    public abstract int zzd();

    /* access modifiers changed from: protected */
    public abstract void zze(byte[] bArr, int i, int i2, int i3);

    /* access modifiers changed from: protected */
    public abstract int zzf();

    /* access modifiers changed from: protected */
    public abstract boolean zzh();

    /* access modifiers changed from: protected */
    public abstract int zzi(int i, int i2, int i3);

    /* access modifiers changed from: protected */
    public abstract int zzj(int i, int i2, int i3);

    public abstract zzhac zzk(int i, int i2);

    public abstract zzham zzl();

    /* access modifiers changed from: protected */
    public abstract String zzm(Charset charset);

    public abstract ByteBuffer zzn();

    /* access modifiers changed from: package-private */
    public abstract void zzo(zzgzq zzgzq) throws IOException;

    public abstract boolean zzp();

    /* access modifiers changed from: protected */
    public final int zzr() {
        return this.zzc;
    }

    /* renamed from: zzs */
    public zzgzv iterator() {
        return new zzgzr(this);
    }

    public final String zzx(Charset charset) {
        return zzd() == 0 ? "" : zzm(charset);
    }

    public final String zzy() {
        return zzx(zzhcb.zzb);
    }

    private static zzhac zzc(Iterator it, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(String.format("length (%s) must be >= 1", new Object[]{Integer.valueOf(i)}));
        } else if (i == 1) {
            return (zzhac) it.next();
        } else {
            int i2 = i >>> 1;
            zzhac zzc2 = zzc(it, i2);
            zzhac zzc3 = zzc(it, i - i2);
            if (Integer.MAX_VALUE - zzc2.zzd() >= zzc3.zzd()) {
                return zzhdy.zzD(zzc2, zzc3);
            }
            int zzd2 = zzc2.zzd();
            int zzd3 = zzc3.zzd();
            throw new IllegalArgumentException("ByteString would be too long: " + zzd2 + "+" + zzd3);
        }
    }

    static int zzq(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        } else if (i2 < i) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
        } else {
            throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
        }
    }
}
