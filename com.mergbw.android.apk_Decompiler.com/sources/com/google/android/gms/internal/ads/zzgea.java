package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgea extends zzgeb {
    static /* bridge */ /* synthetic */ int zza(int[] iArr, int i, int i2, int i3) {
        while (i2 < i3) {
            if (iArr[i2] == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int zzb(long j) {
        int i = (int) j;
        zzfyg.zzg(((long) i) == j, "Out of range: %s", j);
        return i;
    }

    public static int zzc(int i, int i2, int i3) {
        zzfyg.zzh(true, "min (%s) must be less than or equal to max (%s)", i2, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
        return Math.min(Math.max(i, i2), LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    public static int zzd(byte[] bArr) {
        int length = bArr.length;
        zzfyg.zzh(length >= 4, "array too small: %s < %s", length, 4);
        return (bArr[3] & 255) | (bArr[0] << Ascii.CAN) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    public static int zze(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    public static List zzf(int... iArr) {
        int length = iArr.length;
        if (length == 0) {
            return Collections.emptyList();
        }
        return new zzgdz(iArr, 0, length);
    }

    public static int[] zzg(Collection collection) {
        Object[] array = collection.toArray();
        int length = array.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            Object obj = array[i];
            obj.getClass();
            iArr[i] = ((Number) obj).intValue();
        }
        return iArr;
    }
}
