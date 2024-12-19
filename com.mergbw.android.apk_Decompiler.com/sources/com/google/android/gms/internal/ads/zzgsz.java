package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgsz {
    public static byte[] zza(byte[] bArr) {
        if (bArr.length == 16) {
            byte[] bArr2 = new byte[16];
            int i = 0;
            while (i < 16) {
                int i2 = i + 1;
                byte b2 = bArr[i];
                byte b3 = (byte) ((b2 + b2) & 254);
                bArr2[i] = b3;
                if (i < 15) {
                    bArr2[i] = (byte) (((bArr[i2] >> 7) & 1) | b3);
                }
                i = i2;
            }
            bArr2[15] = (byte) (((byte) ((bArr[0] >> 7) & TsExtractor.TS_STREAM_TYPE_E_AC3)) ^ bArr2[15]);
            return bArr2;
        }
        throw new IllegalArgumentException("value must be a block.");
    }
}
