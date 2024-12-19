package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzmj {
    private static boolean zza(byte b2) {
        return b2 > -65;
    }

    static /* synthetic */ void zza(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i) {
        if (zza(b3) || (((b2 << Ascii.FS) + (b3 + 112)) >> 30) != 0 || zza(b4) || zza(b5)) {
            throw zzjs.zzd();
        }
        byte b6 = ((b2 & 7) << Ascii.DC2) | ((b3 & Utf8.REPLACEMENT_BYTE) << 12) | ((b4 & Utf8.REPLACEMENT_BYTE) << 6) | (b5 & Utf8.REPLACEMENT_BYTE);
        cArr[i] = (char) ((b6 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
        cArr[i + 1] = (char) ((b6 & 1023) + Utf8.LOG_SURROGATE_HEADER);
    }

    static /* synthetic */ void zza(byte b2, char[] cArr, int i) {
        cArr[i] = (char) b2;
    }

    static /* synthetic */ void zza(byte b2, byte b3, byte b4, char[] cArr, int i) {
        if (zza(b3) || ((b2 == -32 && b3 < -96) || ((b2 == -19 && b3 >= -96) || zza(b4)))) {
            throw zzjs.zzd();
        }
        cArr[i] = (char) (((b2 & 15) << 12) | ((b3 & Utf8.REPLACEMENT_BYTE) << 6) | (b4 & Utf8.REPLACEMENT_BYTE));
    }

    static /* synthetic */ void zza(byte b2, byte b3, char[] cArr, int i) {
        if (b2 < -62 || zza(b3)) {
            throw zzjs.zzd();
        }
        cArr[i] = (char) (((b2 & Ascii.US) << 6) | (b3 & Utf8.REPLACEMENT_BYTE));
    }
}
