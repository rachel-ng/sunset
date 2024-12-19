package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaep {
    public static int zza(byte[] bArr) {
        return (bArr[10] & 255) | ((bArr[11] & 255) << 8);
    }

    public static int zzb(ByteBuffer byteBuffer) {
        int i;
        byte b2 = 0;
        if ((byteBuffer.get(5) & 2) == 0) {
            i = 0;
        } else {
            byte b3 = byteBuffer.get(26);
            int i2 = 28;
            int i3 = 28;
            for (int i4 = 0; i4 < b3; i4++) {
                i3 += byteBuffer.get(i4 + 27);
            }
            byte b4 = byteBuffer.get(i3 + 26);
            for (int i5 = 0; i5 < b4; i5++) {
                i2 += byteBuffer.get(i3 + 27 + i5);
            }
            i = i3 + i2;
        }
        int i6 = byteBuffer.get(i + 26) + Ascii.ESC + i;
        byte b5 = byteBuffer.get(i6);
        if (byteBuffer.limit() - i6 > 1) {
            b2 = byteBuffer.get(i6 + 1);
        }
        return (int) ((zzg(b5, b2) * 48000) / 1000000);
    }

    public static int zzc(ByteBuffer byteBuffer) {
        byte b2 = 0;
        byte b3 = byteBuffer.get(0);
        if (byteBuffer.limit() > 1) {
            b2 = byteBuffer.get(1);
        }
        return (int) ((zzg(b3, b2) * 48000) / 1000000);
    }

    public static long zzd(byte[] bArr) {
        byte b2 = 0;
        byte b3 = bArr[0];
        if (bArr.length > 1) {
            b2 = bArr[1];
        }
        return zzg(b3, b2);
    }

    public static List zze(byte[] bArr) {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(bArr);
        arrayList.add(zzi(zzh((long) zza(bArr))));
        arrayList.add(zzi(zzh(3840)));
        return arrayList;
    }

    public static boolean zzf(long j, long j2) {
        return j - j2 <= zzh(3840) / 1000;
    }

    private static long zzg(byte b2, byte b3) {
        byte b4;
        byte b5 = b2 & 255;
        byte b6 = b2 & 3;
        if (b6 != 0) {
            b4 = 2;
            if (!(b6 == 1 || b6 == 2)) {
                b4 = b3 & Utf8.REPLACEMENT_BYTE;
            }
        } else {
            b4 = 1;
        }
        int i = b5 >> 3;
        int i2 = i & 3;
        return ((long) b4) * ((long) (i >= 16 ? DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS << i2 : i >= 12 ? 10000 << (i & 1) : i2 == 3 ? 60000 : 10000 << i2));
    }

    private static long zzh(long j) {
        return (j * C.NANOS_PER_SECOND) / 48000;
    }

    private static byte[] zzi(long j) {
        return ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(j).array();
    }
}
