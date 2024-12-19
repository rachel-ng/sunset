package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.audio.OpusUtil;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzacq {
    public static final /* synthetic */ int zza = 0;
    private static final int[] zzb = {96000, 88200, 64000, OpusUtil.SAMPLE_RATE, 44100, 32000, 24000, 22050, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 12000, 11025, 8000, 7350};
    private static final int[] zzc = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public static zzacp zza(byte[] bArr) throws zzch {
        return zzb(new zzft(bArr, bArr.length), false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ae, code lost:
        if (r11 != 3) goto L_0x00cb;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzacp zzb(com.google.android.gms.internal.ads.zzft r11, boolean r12) throws com.google.android.gms.internal.ads.zzch {
        /*
            int r0 = zzc(r11)
            int r1 = zzd(r11)
            r2 = 4
            int r3 = r11.zzd(r2)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "mp4a.40."
            r4.<init>(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            r5 = 5
            r6 = 22
            if (r0 == r5) goto L_0x0024
            r5 = 29
            if (r0 != r5) goto L_0x0032
        L_0x0024:
            int r1 = zzd(r11)
            int r0 = zzc(r11)
            if (r0 != r6) goto L_0x0032
            int r3 = r11.zzd(r2)
        L_0x0032:
            if (r12 == 0) goto L_0x00cb
            r12 = 17
            r5 = 6
            r7 = 1
            r8 = 2
            r9 = 3
            if (r0 == r7) goto L_0x005f
            if (r0 == r8) goto L_0x005f
            if (r0 == r9) goto L_0x005f
            if (r0 == r2) goto L_0x005f
            if (r0 == r5) goto L_0x005f
            r2 = 7
            if (r0 == r2) goto L_0x005f
            if (r0 == r12) goto L_0x005f
            switch(r0) {
                case 19: goto L_0x005f;
                case 20: goto L_0x005f;
                case 21: goto L_0x005f;
                case 22: goto L_0x005f;
                case 23: goto L_0x005f;
                default: goto L_0x004c;
            }
        L_0x004c:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "Unsupported audio object type: "
            r11.<init>(r12)
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            com.google.android.gms.internal.ads.zzch r11 = com.google.android.gms.internal.ads.zzch.zzc(r11)
            throw r11
        L_0x005f:
            boolean r2 = r11.zzo()
            if (r2 == 0) goto L_0x006c
            java.lang.String r2 = "AacUtil"
            java.lang.String r10 = "Unexpected frameLengthFlag = 1"
            com.google.android.gms.internal.ads.zzfk.zzf(r2, r10)
        L_0x006c:
            boolean r2 = r11.zzo()
            if (r2 == 0) goto L_0x0077
            r2 = 14
            r11.zzm(r2)
        L_0x0077:
            boolean r2 = r11.zzo()
            if (r3 == 0) goto L_0x00c5
            r10 = 20
            if (r0 == r5) goto L_0x0084
            if (r0 != r10) goto L_0x0087
            r0 = r10
        L_0x0084:
            r11.zzm(r9)
        L_0x0087:
            if (r2 == 0) goto L_0x00a4
            if (r0 != r6) goto L_0x0091
            r2 = 16
            r11.zzm(r2)
            goto L_0x0092
        L_0x0091:
            r6 = r0
        L_0x0092:
            if (r6 == r12) goto L_0x009e
            r12 = 19
            if (r6 == r12) goto L_0x009e
            if (r6 == r10) goto L_0x009e
            r12 = 23
            if (r6 != r12) goto L_0x00a1
        L_0x009e:
            r11.zzm(r9)
        L_0x00a1:
            r11.zzm(r7)
        L_0x00a4:
            switch(r0) {
                case 17: goto L_0x00a8;
                case 18: goto L_0x00a7;
                case 19: goto L_0x00a8;
                case 20: goto L_0x00a8;
                case 21: goto L_0x00a8;
                case 22: goto L_0x00a8;
                case 23: goto L_0x00a8;
                default: goto L_0x00a7;
            }
        L_0x00a7:
            goto L_0x00cb
        L_0x00a8:
            int r11 = r11.zzd(r8)
            if (r11 == r8) goto L_0x00b1
            if (r11 == r9) goto L_0x00b2
            goto L_0x00cb
        L_0x00b1:
            r9 = r11
        L_0x00b2:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "Unsupported epConfig: "
            r11.<init>(r12)
            r11.append(r9)
            java.lang.String r11 = r11.toString()
            com.google.android.gms.internal.ads.zzch r11 = com.google.android.gms.internal.ads.zzch.zzc(r11)
            throw r11
        L_0x00c5:
            java.lang.UnsupportedOperationException r11 = new java.lang.UnsupportedOperationException
            r11.<init>()
            throw r11
        L_0x00cb:
            int[] r11 = zzc
            r11 = r11[r3]
            r12 = -1
            r0 = 0
            if (r11 == r12) goto L_0x00d9
            com.google.android.gms.internal.ads.zzacp r12 = new com.google.android.gms.internal.ads.zzacp
            r12.<init>(r1, r11, r4, r0)
            return r12
        L_0x00d9:
            com.google.android.gms.internal.ads.zzch r11 = com.google.android.gms.internal.ads.zzch.zza(r0, r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacq.zzb(com.google.android.gms.internal.ads.zzft, boolean):com.google.android.gms.internal.ads.zzacp");
    }

    private static int zzc(zzft zzft) {
        int zzd = zzft.zzd(5);
        return zzd == 31 ? zzft.zzd(6) + 32 : zzd;
    }

    private static int zzd(zzft zzft) throws zzch {
        int zzd = zzft.zzd(4);
        if (zzd == 15) {
            if (zzft.zza() >= 24) {
                return zzft.zzd(24);
            }
            throw zzch.zza("AAC header insufficient data", (Throwable) null);
        } else if (zzd < 13) {
            return zzb[zzd];
        } else {
            throw zzch.zza("AAC header wrong Sampling Frequency Index", (Throwable) null);
        }
    }
}
