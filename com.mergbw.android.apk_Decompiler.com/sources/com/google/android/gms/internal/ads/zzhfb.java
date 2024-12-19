package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhfb {
    static /* bridge */ /* synthetic */ void zza(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i) {
        if (zzg(b3) || (((b2 << Ascii.FS) + (b3 + 112)) >> 30) != 0 || zzg(b4) || zzg(b5)) {
            throw zzhcd.zzd();
        }
        byte b6 = b3 & Utf8.REPLACEMENT_BYTE;
        byte b7 = b4 & Utf8.REPLACEMENT_BYTE;
        byte b8 = ((b2 & 7) << Ascii.DC2) | (b6 << 12) | (b7 << 6) | (b5 & Utf8.REPLACEMENT_BYTE);
        cArr[i] = (char) ((b8 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
        cArr[i + 1] = (char) ((b8 & 1023) + Utf8.LOG_SURROGATE_HEADER);
    }

    static /* bridge */ /* synthetic */ void zzc(byte b2, byte b3, char[] cArr, int i) {
        if (b2 < -62 || zzg(b3)) {
            throw zzhcd.zzd();
        }
        cArr[i] = (char) (((b2 & Ascii.US) << 6) | (b3 & Utf8.REPLACEMENT_BYTE));
    }

    static /* bridge */ /* synthetic */ boolean zzd(byte b2) {
        return b2 >= 0;
    }

    static /* bridge */ /* synthetic */ boolean zze(byte b2) {
        return b2 < -16;
    }

    static /* bridge */ /* synthetic */ boolean zzf(byte b2) {
        return b2 < -32;
    }

    private static boolean zzg(byte b2) {
        return b2 > -65;
    }

    static /* bridge */ /* synthetic */ void zzb(byte b2, byte b3, byte b4, char[] cArr, int i) {
        if (!zzg(b3)) {
            if (b2 == -32) {
                if (b3 >= -96) {
                    b2 = -32;
                }
            }
            if (b2 == -19) {
                if (b3 < -96) {
                    b2 = -19;
                }
            }
            if (!zzg(b4)) {
                cArr[i] = (char) (((b2 & 15) << 12) | ((b3 & Utf8.REPLACEMENT_BYTE) << 6) | (b4 & Utf8.REPLACEMENT_BYTE));
                return;
            }
        }
        throw zzhcd.zzd();
    }
}
