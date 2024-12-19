package com.google.android.gms.internal.measurement;

import com.google.android.exoplayer2.C;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
public final class zzjm {
    static final Charset zza = Charset.forName("UTF-8");
    public static final byte[] zzb;

    public static int zza(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zza(byte[] bArr) {
        int length = bArr.length;
        int zza2 = zza(length, bArr, 0, length);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static <T> T zza(T t) {
        t.getClass();
        return t;
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    static {
        Charset.forName(C.ASCII_NAME);
        Charset.forName(C.ISO88591_NAME);
        byte[] bArr = new byte[0];
        zzb = bArr;
        ByteBuffer.wrap(bArr);
        zzio.zza(bArr, 0, bArr.length, false);
    }

    static boolean zza(zzkt zzkt) {
        if (!(zzkt instanceof zzhs)) {
            return false;
        }
        zzhs zzhs = (zzhs) zzkt;
        return false;
    }
}
