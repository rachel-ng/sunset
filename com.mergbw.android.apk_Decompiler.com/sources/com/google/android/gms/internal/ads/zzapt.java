package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import java.math.RoundingMode;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzapt implements zzapu {
    private static final int[] zza = {-1, -1, -1, -1, 2, 4, 6, 8, -1, -1, -1, -1, 2, 4, 6, 8};
    private static final int[] zzb = {7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 21, 23, 25, 28, 31, 34, 37, 41, 45, 50, 55, 60, 66, 73, 80, 88, 97, 107, 118, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 143, 157, 173, 190, 209, 230, 253, 279, 307, 337, 371, 408, 449, 494, 544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411, 1552, 1707, 1878, 2066, 2272, 2499, 2749, 3024, 3327, 3660, 4026, 4428, 4871, 5358, 5894, 6484, 7132, 7845, 8630, 9493, 10442, 11487, 12635, 13899, 15289, 16818, 18500, 20350, 22385, 24623, 27086, 29794, 32767};
    private final zzadx zzc;
    private final zzafa zzd;
    private final zzapx zze;
    private final int zzf;
    private final byte[] zzg;
    private final zzfu zzh;
    private final int zzi;
    private final zzan zzj;
    private int zzk;
    private long zzl;
    private int zzm;
    private long zzn;

    public zzapt(zzadx zzadx, zzafa zzafa, zzapx zzapx) throws zzch {
        this.zzc = zzadx;
        this.zzd = zzafa;
        this.zze = zzapx;
        int max = Math.max(1, zzapx.zzc / 10);
        this.zzi = max;
        zzfu zzfu = new zzfu(zzapx.zzf);
        zzfu.zzk();
        int zzk2 = zzfu.zzk();
        this.zzf = zzk2;
        int i = zzapx.zzb;
        int i2 = (((zzapx.zzd - (i * 4)) * 8) / (zzapx.zze * i)) + 1;
        if (zzk2 == i2) {
            int i3 = zzgd.zza;
            int i4 = ((max + zzk2) - 1) / zzk2;
            this.zzg = new byte[(zzapx.zzd * i4)];
            this.zzh = new zzfu(i4 * (zzk2 + zzk2) * i);
            int i5 = ((zzapx.zzc * zzapx.zzd) * 8) / zzk2;
            zzal zzal = new zzal();
            zzal.zzX(MimeTypes.AUDIO_RAW);
            zzal.zzx(i5);
            zzal.zzS(i5);
            zzal.zzP((max + max) * i);
            zzal.zzy(zzapx.zzb);
            zzal.zzY(zzapx.zzc);
            zzal.zzR(2);
            this.zzj = zzal.zzad();
            return;
        }
        throw zzch.zza("Expected frames per block: " + i2 + "; got: " + zzk2, (Throwable) null);
    }

    private final int zzd(int i) {
        int i2 = this.zze.zzb;
        return i / (i2 + i2);
    }

    private final int zze(int i) {
        return (i + i) * this.zze.zzb;
    }

    private final void zzf(int i) {
        long zzt = this.zzl + zzgd.zzt(this.zzn, 1000000, (long) this.zze.zzc, RoundingMode.FLOOR);
        int zze2 = zze(i);
        this.zzd.zzs(zzt, 1, zze2, this.zzm - zze2, (zzaez) null);
        this.zzn += (long) i;
        this.zzm -= zze2;
    }

    public final void zza(int i, long j) {
        this.zzc.zzO(new zzaqa(this.zze, this.zzf, (long) i, j));
        this.zzd.zzl(this.zzj);
    }

    public final void zzb(long j) {
        this.zzk = 0;
        this.zzl = j;
        this.zzm = 0;
        this.zzn = 0;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0044 A[EDGE_INSN: B:38:0x0044->B:10:0x0044 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0025  */
    public final boolean zzc(com.google.android.gms.internal.ads.zzadv r21, long r22) throws java.io.IOException {
        /*
            r20 = this;
            r0 = r20
            r1 = r22
            int r3 = r0.zzm
            int r3 = r0.zzd(r3)
            int r4 = r0.zzi
            int r4 = r4 - r3
            int r3 = com.google.android.gms.internal.ads.zzgd.zza
            int r3 = r0.zzf
            int r4 = r4 + r3
            com.google.android.gms.internal.ads.zzapx r5 = r0.zze
            r6 = -1
            int r4 = r4 + r6
            int r4 = r4 / r3
            int r3 = r5.zzd
            int r4 = r4 * r3
            r7 = 0
            int r3 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x0022
        L_0x0020:
            r3 = 1
            goto L_0x0023
        L_0x0022:
            r3 = 0
        L_0x0023:
            if (r3 != 0) goto L_0x0044
            int r8 = r0.zzk
            if (r8 >= r4) goto L_0x0044
            int r8 = r4 - r8
            long r8 = (long) r8
            long r8 = java.lang.Math.min(r8, r1)
            int r8 = (int) r8
            byte[] r9 = r0.zzg
            int r10 = r0.zzk
            r11 = r21
            int r8 = r11.zza(r9, r10, r8)
            if (r8 != r6) goto L_0x003e
            goto L_0x0020
        L_0x003e:
            int r9 = r0.zzk
            int r9 = r9 + r8
            r0.zzk = r9
            goto L_0x0023
        L_0x0044:
            int r1 = r0.zzk
            com.google.android.gms.internal.ads.zzapx r2 = r0.zze
            int r2 = r2.zzd
            int r1 = r1 / r2
            if (r1 <= 0) goto L_0x014a
            byte[] r2 = r0.zzg
            com.google.android.gms.internal.ads.zzfu r4 = r0.zzh
            r6 = 0
        L_0x0052:
            if (r6 >= r1) goto L_0x0117
            r8 = 0
        L_0x0055:
            com.google.android.gms.internal.ads.zzapx r9 = r0.zze
            int r10 = r9.zzb
            if (r8 >= r10) goto L_0x010f
            byte[] r11 = r4.zzM()
            int r9 = r9.zzd
            int r12 = r6 * r9
            int r9 = r9 / r10
            int r9 = r9 + -4
            int r13 = r8 * 4
            int r12 = r12 + r13
            int r13 = r12 + 1
            byte r13 = r2[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            byte r14 = r2[r12]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r15 = r12 + 2
            byte r15 = r2[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r5 = 88
            int r15 = java.lang.Math.min(r15, r5)
            int[] r16 = zzb
            r16 = r16[r15]
            int r5 = r0.zzf
            int r5 = r5 * r6
            int r5 = r5 * r10
            int r5 = r5 + r8
            int r13 = r13 << 8
            r13 = r13 | r14
            short r13 = (short) r13
            r14 = r13 & 255(0xff, float:3.57E-43)
            int r5 = r5 + r5
            byte r14 = (byte) r14
            r11[r5] = r14
            int r14 = r5 + 1
            int r7 = r13 >> 8
            byte r7 = (byte) r7
            r11[r14] = r7
            r7 = 0
        L_0x009a:
            int r14 = r9 + r9
            if (r7 >= r14) goto L_0x0107
            int r14 = r10 * 4
            int r14 = r14 + r12
            int r18 = r7 / 8
            int r19 = r7 / 2
            int r19 = r19 % 4
            int r18 = r18 * r10
            int r18 = r18 * 4
            int r14 = r14 + r18
            int r14 = r14 + r19
            byte r14 = r2[r14]
            r18 = r2
            r2 = r14 & 255(0xff, float:3.57E-43)
            int r19 = r7 % 2
            if (r19 != 0) goto L_0x00bc
            r2 = r14 & 15
            goto L_0x00be
        L_0x00bc:
            int r2 = r2 >> 4
        L_0x00be:
            r14 = r2 & 7
            int r14 = r14 + r14
            r17 = 1
            int r14 = r14 + 1
            int r14 = r14 * r16
            r16 = r2 & 8
            int r14 = r14 >> 3
            if (r16 == 0) goto L_0x00ce
            int r14 = -r14
        L_0x00ce:
            int r13 = r13 + r14
            r14 = 32767(0x7fff, float:4.5916E-41)
            int r13 = java.lang.Math.min(r13, r14)
            r14 = -32768(0xffffffffffff8000, float:NaN)
            int r13 = java.lang.Math.max(r14, r13)
            int r14 = r10 + r10
            int r5 = r5 + r14
            r14 = r13 & 255(0xff, float:3.57E-43)
            byte r14 = (byte) r14
            r11[r5] = r14
            int r14 = r5 + 1
            r22 = r5
            int r5 = r13 >> 8
            byte r5 = (byte) r5
            r11[r14] = r5
            int[] r5 = zza
            r2 = r5[r2]
            int r15 = r15 + r2
            r2 = 88
            int r5 = java.lang.Math.min(r15, r2)
            r14 = 0
            int r15 = java.lang.Math.max(r14, r5)
            int[] r5 = zzb
            r16 = r5[r15]
            int r7 = r7 + 1
            r5 = r22
            r2 = r18
            goto L_0x009a
        L_0x0107:
            r18 = r2
            r17 = 1
            int r8 = r8 + 1
            goto L_0x0055
        L_0x010f:
            r18 = r2
            r17 = 1
            int r6 = r6 + 1
            goto L_0x0052
        L_0x0117:
            int r2 = r0.zzf
            int r2 = r2 * r1
            int r2 = r0.zze(r2)
            r5 = 0
            r4.zzK(r5)
            r4.zzJ(r2)
            int r2 = r0.zzk
            com.google.android.gms.internal.ads.zzapx r4 = r0.zze
            int r4 = r4.zzd
            int r1 = r1 * r4
            int r2 = r2 - r1
            r0.zzk = r2
            com.google.android.gms.internal.ads.zzfu r1 = r0.zzh
            com.google.android.gms.internal.ads.zzafa r2 = r0.zzd
            int r4 = r1.zze()
            com.google.android.gms.internal.ads.zzaey.zzb(r2, r1, r4)
            int r1 = r0.zzm
            int r1 = r1 + r4
            r0.zzm = r1
            int r1 = r0.zzd(r1)
            int r2 = r0.zzi
            if (r1 < r2) goto L_0x014a
            r0.zzf(r2)
        L_0x014a:
            if (r3 == 0) goto L_0x0157
            int r1 = r0.zzm
            int r1 = r0.zzd(r1)
            if (r1 <= 0) goto L_0x0157
            r0.zzf(r1)
        L_0x0157:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapt.zzc(com.google.android.gms.internal.ads.zzadv, long):boolean");
    }
}
