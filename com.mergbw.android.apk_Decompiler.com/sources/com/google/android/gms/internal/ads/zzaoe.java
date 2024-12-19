package com.google.android.gms.internal.ads;

import com.alibaba.fastjson.asm.Opcodes;
import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaoe implements zzaoc {
    private static final double[] zza = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private String zzb;
    private zzafa zzc;
    private final zzapr zzd;
    private final zzfu zze;
    private final zzaor zzf;
    private final boolean[] zzg;
    private final zzaod zzh;
    private long zzi;
    private boolean zzj;
    private boolean zzk;
    private long zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private boolean zzp;
    private boolean zzq;

    public zzaoe() {
        throw null;
    }

    zzaoe(zzapr zzapr) {
        zzfu zzfu;
        this.zzd = zzapr;
        this.zzg = new boolean[4];
        this.zzh = new zzaod(128);
        if (zzapr != null) {
            this.zzf = new zzaor(Opcodes.GETSTATIC, 128);
            zzfu = new zzfu();
        } else {
            zzfu = null;
            this.zzf = null;
        }
        this.zze = zzfu;
        this.zzm = C.TIME_UNSET;
        this.zzo = C.TIME_UNSET;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x017a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzfu r20) {
        /*
            r19 = this;
            r0 = r19
            com.google.android.gms.internal.ads.zzafa r1 = r0.zzc
            com.google.android.gms.internal.ads.zzeq.zzb(r1)
            int r1 = r20.zzd()
            int r2 = r20.zze()
            byte[] r3 = r20.zzM()
            long r4 = r0.zzi
            int r6 = r20.zzb()
            long r6 = (long) r6
            long r4 = r4 + r6
            r0.zzi = r4
            com.google.android.gms.internal.ads.zzafa r4 = r0.zzc
            int r5 = r20.zzb()
            r6 = r20
            r4.zzq(r6, r5)
        L_0x0028:
            boolean[] r4 = r0.zzg
            int r4 = com.google.android.gms.internal.ads.zzgr.zza(r3, r1, r2, r4)
            if (r4 != r2) goto L_0x0041
            boolean r4 = r0.zzk
            if (r4 != 0) goto L_0x0039
            com.google.android.gms.internal.ads.zzaod r4 = r0.zzh
            r4.zza(r3, r1, r2)
        L_0x0039:
            com.google.android.gms.internal.ads.zzaor r4 = r0.zzf
            if (r4 == 0) goto L_0x0040
            r4.zza(r3, r1, r2)
        L_0x0040:
            return
        L_0x0041:
            byte[] r5 = r20.zzM()
            int r7 = r4 + 3
            byte r5 = r5[r7]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r8 = r4 - r1
            boolean r9 = r0.zzk
            if (r9 != 0) goto L_0x0122
            if (r8 <= 0) goto L_0x0058
            com.google.android.gms.internal.ads.zzaod r9 = r0.zzh
            r9.zza(r3, r1, r4)
        L_0x0058:
            if (r8 >= 0) goto L_0x005c
            int r9 = -r8
            goto L_0x005d
        L_0x005c:
            r9 = 0
        L_0x005d:
            com.google.android.gms.internal.ads.zzaod r12 = r0.zzh
            boolean r9 = r12.zzc(r5, r9)
            if (r9 == 0) goto L_0x0122
            com.google.android.gms.internal.ads.zzaod r9 = r0.zzh
            java.lang.String r12 = r0.zzb
            r12.getClass()
            byte[] r13 = r9.zzc
            int r14 = r9.zza
            byte[] r13 = java.util.Arrays.copyOf(r13, r14)
            r14 = 4
            byte r15 = r13[r14]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r16 = 5
            byte r10 = r13[r16]
            r11 = r10 & 255(0xff, float:3.57E-43)
            r17 = 6
            byte r14 = r13[r17]
            r14 = r14 & 255(0xff, float:3.57E-43)
            r17 = 7
            byte r6 = r13[r17]
            r6 = r6 & 240(0xf0, float:3.36E-43)
            r10 = r10 & 15
            r18 = r7
            r7 = 4
            int r15 = r15 << r7
            int r11 = r11 >> r7
            r11 = r11 | r15
            int r6 = r6 >> r7
            r15 = 8
            int r10 = r10 << r15
            r10 = r10 | r14
            r14 = 2
            if (r6 == r14) goto L_0x00ad
            r14 = 3
            if (r6 == r14) goto L_0x00a8
            if (r6 == r7) goto L_0x00a3
            r6 = 1065353216(0x3f800000, float:1.0)
            goto L_0x00b4
        L_0x00a3:
            int r6 = r10 * 121
            int r7 = r11 * 100
            goto L_0x00b1
        L_0x00a8:
            int r6 = r10 * 16
            int r7 = r11 * 9
            goto L_0x00b1
        L_0x00ad:
            int r6 = r10 * 4
            int r7 = r11 * 3
        L_0x00b1:
            float r6 = (float) r6
            float r7 = (float) r7
            float r6 = r6 / r7
        L_0x00b4:
            com.google.android.gms.internal.ads.zzal r7 = new com.google.android.gms.internal.ads.zzal
            r7.<init>()
            r7.zzK(r12)
            java.lang.String r12 = "video/mpeg2"
            r7.zzX(r12)
            r7.zzac(r11)
            r7.zzI(r10)
            r7.zzT(r6)
            java.util.List r6 = java.util.Collections.singletonList(r13)
            r7.zzL(r6)
            com.google.android.gms.internal.ads.zzan r6 = r7.zzad()
            byte r7 = r13[r17]
            r7 = r7 & 15
            int r7 = r7 + -1
            r10 = 0
            if (r7 < 0) goto L_0x0103
            if (r7 >= r15) goto L_0x0103
            double[] r10 = zza
            r11 = r10[r7]
            int r7 = r9.zzb
            int r7 = r7 + 9
            byte r7 = r13[r7]
            r9 = r7 & 96
            int r9 = r9 >> 5
            r7 = r7 & 31
            if (r9 == r7) goto L_0x00fc
            double r9 = (double) r9
            int r7 = r7 + 1
            r13 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r9 = r9 + r13
            double r13 = (double) r7
            double r9 = r9 / r13
            double r11 = r11 * r9
        L_0x00fc:
            r9 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r9 = r9 / r11
            long r10 = (long) r9
        L_0x0103:
            java.lang.Long r7 = java.lang.Long.valueOf(r10)
            android.util.Pair r6 = android.util.Pair.create(r6, r7)
            com.google.android.gms.internal.ads.zzafa r7 = r0.zzc
            java.lang.Object r9 = r6.first
            com.google.android.gms.internal.ads.zzan r9 = (com.google.android.gms.internal.ads.zzan) r9
            r7.zzl(r9)
            java.lang.Object r6 = r6.second
            java.lang.Long r6 = (java.lang.Long) r6
            long r6 = r6.longValue()
            r0.zzl = r6
            r6 = 1
            r0.zzk = r6
            goto L_0x0124
        L_0x0122:
            r18 = r7
        L_0x0124:
            com.google.android.gms.internal.ads.zzaor r6 = r0.zzf
            if (r6 == 0) goto L_0x016b
            if (r8 <= 0) goto L_0x012f
            r6.zza(r3, r1, r4)
            r1 = 0
            goto L_0x0130
        L_0x012f:
            int r1 = -r8
        L_0x0130:
            com.google.android.gms.internal.ads.zzaor r6 = r0.zzf
            boolean r1 = r6.zzd(r1)
            if (r1 == 0) goto L_0x0156
            com.google.android.gms.internal.ads.zzaor r1 = r0.zzf
            byte[] r6 = r1.zza
            int r1 = r1.zzb
            int r1 = com.google.android.gms.internal.ads.zzgr.zzb(r6, r1)
            com.google.android.gms.internal.ads.zzfu r6 = r0.zze
            int r7 = com.google.android.gms.internal.ads.zzgd.zza
            com.google.android.gms.internal.ads.zzaor r7 = r0.zzf
            byte[] r7 = r7.zza
            r6.zzI(r7, r1)
            com.google.android.gms.internal.ads.zzapr r1 = r0.zzd
            long r6 = r0.zzo
            com.google.android.gms.internal.ads.zzfu r8 = r0.zze
            r1.zza(r6, r8)
        L_0x0156:
            r1 = 178(0xb2, float:2.5E-43)
            if (r5 != r1) goto L_0x016b
            byte[] r5 = r20.zzM()
            int r6 = r4 + 2
            byte r5 = r5[r6]
            r6 = 1
            if (r5 != r6) goto L_0x016a
            com.google.android.gms.internal.ads.zzaor r5 = r0.zzf
            r5.zzc(r1)
        L_0x016a:
            r5 = r1
        L_0x016b:
            if (r5 == 0) goto L_0x017a
            r1 = 179(0xb3, float:2.51E-43)
            if (r5 != r1) goto L_0x0172
            goto L_0x017a
        L_0x0172:
            r1 = 184(0xb8, float:2.58E-43)
            if (r5 != r1) goto L_0x01da
            r1 = 1
            r0.zzp = r1
            goto L_0x01da
        L_0x017a:
            int r1 = r2 - r4
            boolean r4 = r0.zzq
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 == 0) goto L_0x01a0
            boolean r4 = r0.zzk
            if (r4 == 0) goto L_0x01a0
            long r7 = r0.zzo
            int r4 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x01a0
            boolean r9 = r0.zzp
            long r10 = r0.zzi
            long r13 = r0.zzn
            long r10 = r10 - r13
            int r4 = (int) r10
            int r10 = r4 - r1
            com.google.android.gms.internal.ads.zzafa r6 = r0.zzc
            r12 = 0
            r11 = r1
            r6.zzs(r7, r9, r10, r11, r12)
        L_0x01a0:
            boolean r4 = r0.zzj
            if (r4 == 0) goto L_0x01ac
            boolean r4 = r0.zzq
            if (r4 == 0) goto L_0x01a9
            goto L_0x01ac
        L_0x01a9:
            r1 = 0
            r4 = 1
            goto L_0x01d3
        L_0x01ac:
            long r6 = r0.zzi
            long r8 = (long) r1
            long r6 = r6 - r8
            r0.zzn = r6
            long r6 = r0.zzm
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 == 0) goto L_0x01be
            goto L_0x01c9
        L_0x01be:
            long r6 = r0.zzo
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 == 0) goto L_0x01c8
            long r10 = r0.zzl
            long r6 = r6 + r10
            goto L_0x01c9
        L_0x01c8:
            r6 = r8
        L_0x01c9:
            r0.zzo = r6
            r1 = 0
            r0.zzp = r1
            r0.zzm = r8
            r4 = 1
            r0.zzj = r4
        L_0x01d3:
            if (r5 != 0) goto L_0x01d7
            r10 = r4
            goto L_0x01d8
        L_0x01d7:
            r10 = r1
        L_0x01d8:
            r0.zzq = r10
        L_0x01da:
            r6 = r20
            r1 = r18
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaoe.zza(com.google.android.gms.internal.ads.zzfu):void");
    }

    public final void zzb(zzadx zzadx, zzapo zzapo) {
        zzapo.zzc();
        this.zzb = zzapo.zzb();
        this.zzc = zzadx.zzw(zzapo.zza(), 2);
        zzapr zzapr = this.zzd;
        if (zzapr != null) {
            zzapr.zzb(zzadx, zzapo);
        }
    }

    public final void zzc() {
    }

    public final void zzd(long j, int i) {
        this.zzm = j;
    }

    public final void zze() {
        zzgr.zzf(this.zzg);
        this.zzh.zzb();
        zzaor zzaor = this.zzf;
        if (zzaor != null) {
            zzaor.zzb();
        }
        this.zzi = 0;
        this.zzj = false;
        this.zzm = C.TIME_UNSET;
        this.zzo = C.TIME_UNSET;
    }
}
