package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzmk {
    private static final zzmm zza = new zzml();

    static /* synthetic */ int zza(byte[] bArr, int i, int i2) {
        byte b2 = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 != 0) {
            if (i3 == 1) {
                byte b3 = bArr[i];
                if (b2 > -12 || b3 > -65) {
                    return -1;
                }
                return (b3 << 8) ^ b2;
            } else if (i3 == 2) {
                byte b4 = bArr[i];
                byte b5 = bArr[i + 1];
                if (b2 > -12 || b4 > -65 || b5 > -65) {
                    return -1;
                }
                return (b5 << 16) ^ ((b4 << 8) ^ b2);
            } else {
                throw new AssertionError();
            }
        } else if (b2 > -12) {
            return -1;
        } else {
            return b2;
        }
    }

    static int zza(String str, byte[] bArr, int i, int i2) {
        return zza.zza(str, bArr, i, i2);
    }

    static int zza(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && str.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = str.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = str.length();
                while (i2 < length2) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(str, i2) >= 65536) {
                                i2++;
                            } else {
                                throw new zzmo(i2, length2);
                            }
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i3) + 4294967296L));
    }

    static String zzb(byte[] bArr, int i, int i2) throws zzjs {
        return zza.zza(bArr, i, i2);
    }

    static {
        if (zzmg.zzc()) {
            boolean zzd = zzmg.zzd();
        }
    }

    static boolean zzc(byte[] bArr, int i, int i2) {
        if (zza.zza(0, bArr, i, i2) == 0) {
            return true;
        }
        return false;
    }
}
