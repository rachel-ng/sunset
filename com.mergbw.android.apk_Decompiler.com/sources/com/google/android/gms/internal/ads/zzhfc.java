package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
abstract class zzhfc {
    zzhfc() {
    }

    static final String zzd(ByteBuffer byteBuffer, int i, int i2) throws zzhcd {
        int i3;
        if ((((byteBuffer.limit() - i) - i2) | i | i2) >= 0) {
            int i4 = i + i2;
            char[] cArr = new char[i2];
            int i5 = 0;
            while (r10 < i4) {
                byte b2 = byteBuffer.get(r10);
                if (!zzhfb.zzd(b2)) {
                    break;
                }
                i = r10 + 1;
                cArr[i5] = (char) b2;
                i5++;
            }
            int i6 = i5;
            while (r10 < i4) {
                int i7 = r10 + 1;
                byte b3 = byteBuffer.get(r10);
                if (zzhfb.zzd(b3)) {
                    cArr[i6] = (char) b3;
                    i6++;
                    r10 = i7;
                    while (r10 < i4) {
                        byte b4 = byteBuffer.get(r10);
                        if (!zzhfb.zzd(b4)) {
                            break;
                        }
                        r10++;
                        cArr[i6] = (char) b4;
                        i6++;
                    }
                } else {
                    if (zzhfb.zzf(b3)) {
                        if (i7 < i4) {
                            i3 = i6 + 1;
                            r10 = r10 + 2;
                            zzhfb.zzc(b3, byteBuffer.get(i7), cArr, i6);
                        } else {
                            throw zzhcd.zzd();
                        }
                    } else if (zzhfb.zze(b3)) {
                        if (i7 < i4 - 1) {
                            i3 = i6 + 1;
                            int i8 = r10 + 2;
                            r10 = r10 + 3;
                            zzhfb.zzb(b3, byteBuffer.get(i7), byteBuffer.get(i8), cArr, i6);
                        } else {
                            throw zzhcd.zzd();
                        }
                    } else if (i7 < i4 - 2) {
                        byte b5 = byteBuffer.get(i7);
                        int i9 = r10 + 3;
                        byte b6 = byteBuffer.get(r10 + 2);
                        r10 += 4;
                        zzhfb.zza(b3, b5, b6, byteBuffer.get(i9), cArr, i6);
                        i6 += 2;
                    } else {
                        throw zzhcd.zzd();
                    }
                    i6 = i3;
                }
            }
            return new String(cArr, 0, i6);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    /* access modifiers changed from: package-private */
    public abstract int zza(int i, byte[] bArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract String zzb(byte[] bArr, int i, int i2) throws zzhcd;

    /* access modifiers changed from: package-private */
    public final boolean zzc(byte[] bArr, int i, int i2) {
        return zza(0, bArr, i, i2) == 0;
    }
}
