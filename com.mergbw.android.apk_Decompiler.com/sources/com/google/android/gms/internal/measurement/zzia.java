package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
public abstract class zzia implements Serializable, Iterable<Byte> {
    public static final zzia zza = new zzij(zzjm.zzb);
    private static final zzid zzb = new zzim();
    private int zzc = 0;

    static /* synthetic */ int zza(byte b2) {
        return b2 & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract byte zza(int i);

    public abstract zzia zza(int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void zza(zzhx zzhx) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract byte zzb(int i);

    public abstract int zzb();

    /* access modifiers changed from: protected */
    public abstract int zzb(int i, int i2, int i3);

    static int zza(int i, int i2, int i3) {
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

    public final int hashCode() {
        int i = this.zzc;
        if (i == 0) {
            int zzb2 = zzb();
            i = zzb(zzb2, 0, zzb2);
            if (i == 0) {
                i = 1;
            }
            this.zzc = i;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        return this.zzc;
    }

    static zzif zzc(int i) {
        return new zzif(i);
    }

    public static zzia zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    public static zzia zza(byte[] bArr, int i, int i2) {
        zza(i, i + i2, bArr.length);
        return new zzij(zzb.zza(bArr, i, i2));
    }

    public static zzia zza(String str) {
        return new zzij(str.getBytes(zzjm.zza));
    }

    static zzia zzb(byte[] bArr) {
        return new zzij(bArr);
    }

    public final String toString() {
        String str;
        Locale locale = Locale.ROOT;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        Integer valueOf = Integer.valueOf(zzb());
        if (zzb() <= 50) {
            str = zzmb.zza(this);
        } else {
            str = zzmb.zza(zza(0, 47)) + "...";
        }
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", new Object[]{hexString, valueOf, str});
    }

    public /* synthetic */ Iterator iterator() {
        return new zzhz(this);
    }

    static {
        new zzic();
    }

    zzia() {
    }
}
