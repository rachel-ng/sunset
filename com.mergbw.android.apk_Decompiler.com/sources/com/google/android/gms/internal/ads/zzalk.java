package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzalk extends zzali {
    private zzalj zza;
    private int zzb;
    private boolean zzc;
    private zzaff zzd;
    private zzafd zze;

    zzalk() {
    }

    /* access modifiers changed from: protected */
    public final long zza(zzfu zzfu) {
        int i;
        int i2 = 0;
        if ((zzfu.zzM()[0] & 1) == 1) {
            return -1;
        }
        byte b2 = zzfu.zzM()[0];
        zzalj zzalj = this.zza;
        zzeq.zzb(zzalj);
        if (!zzalj.zzd[(b2 >> 1) & (255 >>> (8 - zzalj.zze))].zza) {
            i = zzalj.zza.zze;
        } else {
            i = zzalj.zza.zzf;
        }
        if (this.zzc) {
            i2 = (this.zzb + i) / 4;
        }
        if (zzfu.zzc() < zzfu.zze() + 4) {
            byte[] copyOf = Arrays.copyOf(zzfu.zzM(), zzfu.zze() + 4);
            zzfu.zzI(copyOf, copyOf.length);
        } else {
            zzfu.zzJ(zzfu.zze() + 4);
        }
        long j = (long) i2;
        byte[] zzM = zzfu.zzM();
        zzM[zzfu.zze() - 4] = (byte) ((int) (j & 255));
        zzM[zzfu.zze() - 3] = (byte) ((int) ((j >>> 8) & 255));
        zzM[zzfu.zze() - 2] = (byte) ((int) ((j >>> 16) & 255));
        zzM[zzfu.zze() - 1] = (byte) ((int) ((j >>> 24) & 255));
        this.zzc = true;
        this.zzb = i;
        return j;
    }

    /* access modifiers changed from: protected */
    public final void zzb(boolean z) {
        super.zzb(z);
        if (z) {
            this.zza = null;
            this.zzd = null;
            this.zze = null;
        }
        this.zzb = 0;
        this.zzc = false;
    }

    /* access modifiers changed from: protected */
    public final void zzi(long j) {
        super.zzi(j);
        int i = 0;
        this.zzc = j != 0;
        zzaff zzaff = this.zzd;
        if (zzaff != null) {
            i = zzaff.zze;
        }
        this.zzb = i;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x03b7 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x03b9  */
    @org.checkerframework.checker.nullness.qual.EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzc(com.google.android.gms.internal.ads.zzfu r26, long r27, com.google.android.gms.internal.ads.zzalf r29) throws java.io.IOException {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r2 = r29
            com.google.android.gms.internal.ads.zzalj r3 = r0.zza
            r4 = 0
            if (r3 == 0) goto L_0x0011
            com.google.android.gms.internal.ads.zzan r1 = r2.zza
            r1.getClass()
            return r4
        L_0x0011:
            com.google.android.gms.internal.ads.zzaff r6 = r0.zzd
            r5 = 4
            r11 = 1
            if (r6 != 0) goto L_0x0084
            com.google.android.gms.internal.ads.zzafg.zzd(r11, r1, r4)
            int r13 = r26.zzj()
            int r14 = r26.zzm()
            int r15 = r26.zzj()
            int r6 = r26.zzi()
            if (r6 > 0) goto L_0x002f
            r16 = -1
            goto L_0x0031
        L_0x002f:
            r16 = r6
        L_0x0031:
            int r6 = r26.zzi()
            if (r6 > 0) goto L_0x003a
            r17 = -1
            goto L_0x003c
        L_0x003a:
            r17 = r6
        L_0x003c:
            int r6 = r26.zzi()
            if (r6 > 0) goto L_0x0045
            r18 = -1
            goto L_0x0047
        L_0x0045:
            r18 = r6
        L_0x0047:
            int r3 = r26.zzm()
            r6 = r3 & 15
            double r8 = (double) r6
            r11 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r8 = java.lang.Math.pow(r11, r8)
            int r6 = (int) r8
            r3 = r3 & 240(0xf0, float:3.36E-43)
            int r3 = r3 >> r5
            double r8 = (double) r3
            double r8 = java.lang.Math.pow(r11, r8)
            int r3 = (int) r8
            int r5 = r26.zzm()
            r8 = 1
            r5 = r5 & r8
            if (r8 == r5) goto L_0x0069
            r21 = r4
            goto L_0x006b
        L_0x0069:
            r21 = 1
        L_0x006b:
            byte[] r4 = r26.zzM()
            int r1 = r26.zze()
            byte[] r22 = java.util.Arrays.copyOf(r4, r1)
            com.google.android.gms.internal.ads.zzaff r1 = new com.google.android.gms.internal.ads.zzaff
            r12 = r1
            r19 = r6
            r20 = r3
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            r0.zzd = r1
            goto L_0x008f
        L_0x0084:
            com.google.android.gms.internal.ads.zzafd r8 = r0.zze
            if (r8 != 0) goto L_0x0092
            r9 = 1
            com.google.android.gms.internal.ads.zzafd r1 = com.google.android.gms.internal.ads.zzafg.zzc(r1, r9, r9)
            r0.zze = r1
        L_0x008f:
            r7 = 0
            goto L_0x03b3
        L_0x0092:
            int r9 = r26.zze()
            byte[] r9 = new byte[r9]
            byte[] r10 = r26.zzM()
            int r11 = r26.zze()
            java.lang.System.arraycopy(r10, r4, r9, r4, r11)
            int r10 = r6.zza
            r11 = 5
            com.google.android.gms.internal.ads.zzafg.zzd(r11, r1, r4)
            int r12 = r26.zzm()
            r13 = 1
            int r12 = r12 + r13
            com.google.android.gms.internal.ads.zzafc r13 = new com.google.android.gms.internal.ads.zzafc
            byte[] r14 = r26.zzM()
            r13.<init>(r14)
            int r1 = r26.zzd()
            r14 = 8
            int r1 = r1 * r14
            r13.zzc(r1)
            r1 = r4
        L_0x00c3:
            r15 = 24
            r3 = 2
            r4 = 16
            if (r1 >= r12) goto L_0x018a
            int r14 = r13.zzb(r15)
            r7 = 5653314(0x564342, float:7.92198E-39)
            if (r14 != r7) goto L_0x0172
            int r4 = r13.zzb(r4)
            int r7 = r13.zzb(r15)
            boolean r14 = r13.zzd()
            if (r14 != 0) goto L_0x00fa
            boolean r14 = r13.zzd()
            r15 = 0
        L_0x00e6:
            if (r15 >= r7) goto L_0x010c
            if (r14 == 0) goto L_0x00f4
            boolean r18 = r13.zzd()
            if (r18 == 0) goto L_0x00f7
            r13.zzc(r11)
            goto L_0x00f7
        L_0x00f4:
            r13.zzc(r11)
        L_0x00f7:
            int r15 = r15 + 1
            goto L_0x00e6
        L_0x00fa:
            r13.zzc(r11)
            r14 = 0
        L_0x00fe:
            if (r14 >= r7) goto L_0x010c
            int r15 = r7 - r14
            int r15 = com.google.android.gms.internal.ads.zzafg.zza(r15)
            int r15 = r13.zzb(r15)
            int r14 = r14 + r15
            goto L_0x00fe
        L_0x010c:
            int r14 = r13.zzb(r5)
            if (r14 > r3) goto L_0x015e
            r15 = 1
            if (r14 == r15) goto L_0x011b
            if (r14 != r3) goto L_0x0118
            goto L_0x011c
        L_0x0118:
            r18 = r6
            goto L_0x0154
        L_0x011b:
            r3 = r14
        L_0x011c:
            r14 = 32
            r13.zzc(r14)
            r13.zzc(r14)
            int r14 = r13.zzb(r5)
            int r14 = r14 + r15
            r13.zzc(r15)
            if (r3 != r15) goto L_0x0149
            if (r4 == 0) goto L_0x0144
            r18 = r6
            long r5 = (long) r7
            long r3 = (long) r4
            double r3 = (double) r3
            double r5 = (double) r5
            r20 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r3 = r20 / r3
            double r3 = java.lang.Math.pow(r5, r3)
            double r3 = java.lang.Math.floor(r3)
            long r3 = (long) r3
            goto L_0x014e
        L_0x0144:
            r18 = r6
            r3 = 0
            goto L_0x014e
        L_0x0149:
            r18 = r6
            long r3 = (long) r4
            long r5 = (long) r7
            long r3 = r3 * r5
        L_0x014e:
            long r5 = (long) r14
            long r3 = r3 * r5
            int r3 = (int) r3
            r13.zzc(r3)
        L_0x0154:
            int r1 = r1 + 1
            r6 = r18
            r4 = 0
            r5 = 4
            r14 = 8
            goto L_0x00c3
        L_0x015e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "lookup type greater than 2 not decodable: "
            r1.<init>(r2)
            r1.append(r14)
            java.lang.String r1 = r1.toString()
            r2 = 0
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r2)
            throw r1
        L_0x0172:
            r2 = 0
            int r1 = r13.zza()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "expected code book to start with [0x56, 0x43, 0x42] at "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r2)
            throw r1
        L_0x018a:
            r18 = r6
            r1 = 6
            int r5 = r13.zzb(r1)
            r6 = 1
            int r5 = r5 + r6
            r6 = 0
        L_0x0194:
            if (r6 >= r5) goto L_0x01a7
            int r7 = r13.zzb(r4)
            if (r7 != 0) goto L_0x019f
            int r6 = r6 + 1
            goto L_0x0194
        L_0x019f:
            java.lang.String r1 = "placeholder of time domain transforms not zeroed out"
            r2 = 0
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r2)
            throw r1
        L_0x01a7:
            int r5 = r13.zzb(r1)
            r6 = 1
            int r5 = r5 + r6
            r7 = 0
        L_0x01ae:
            r12 = 3
            if (r7 >= r5) goto L_0x0273
            int r14 = r13.zzb(r4)
            if (r14 == 0) goto L_0x023d
            if (r14 != r6) goto L_0x0229
            int r6 = r13.zzb(r11)
            int[] r14 = new int[r6]
            r11 = 0
            r15 = -1
        L_0x01c1:
            if (r11 >= r6) goto L_0x01d3
            r1 = 4
            int r4 = r13.zzb(r1)
            r14[r11] = r4
            if (r4 <= r15) goto L_0x01cd
            r15 = r4
        L_0x01cd:
            int r11 = r11 + 1
            r1 = 6
            r4 = 16
            goto L_0x01c1
        L_0x01d3:
            int r15 = r15 + 1
            int[] r1 = new int[r15]
            r4 = 0
        L_0x01d8:
            if (r4 >= r15) goto L_0x020a
            int r11 = r13.zzb(r12)
            r23 = 1
            int r11 = r11 + 1
            r1[r4] = r11
            int r11 = r13.zzb(r3)
            if (r11 <= 0) goto L_0x01f0
            r12 = 8
            r13.zzc(r12)
            goto L_0x01f2
        L_0x01f0:
            r12 = 8
        L_0x01f2:
            r24 = r5
            r3 = 0
        L_0x01f5:
            int r5 = r23 << r11
            if (r3 >= r5) goto L_0x0203
            r13.zzc(r12)
            int r3 = r3 + 1
            r12 = 8
            r23 = 1
            goto L_0x01f5
        L_0x0203:
            int r4 = r4 + 1
            r5 = r24
            r3 = 2
            r12 = 3
            goto L_0x01d8
        L_0x020a:
            r24 = r5
            r13.zzc(r3)
            r3 = 4
            int r4 = r13.zzb(r3)
            r3 = 0
            r5 = 0
            r11 = 0
        L_0x0217:
            if (r3 >= r6) goto L_0x0265
            r12 = r14[r3]
            r12 = r1[r12]
            int r5 = r5 + r12
        L_0x021e:
            if (r11 >= r5) goto L_0x0226
            r13.zzc(r4)
            int r11 = r11 + 1
            goto L_0x021e
        L_0x0226:
            int r3 = r3 + 1
            goto L_0x0217
        L_0x0229:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "floor type greater than 1 not decodable: "
            r1.<init>(r2)
            r1.append(r14)
            java.lang.String r1 = r1.toString()
            r2 = 0
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r2)
            throw r1
        L_0x023d:
            r24 = r5
            r1 = 8
            r13.zzc(r1)
            r3 = 16
            r13.zzc(r3)
            r13.zzc(r3)
            r3 = 6
            r13.zzc(r3)
            r13.zzc(r1)
            r3 = 4
            int r4 = r13.zzb(r3)
            r3 = 1
            int r4 = r4 + r3
            r3 = 0
        L_0x025b:
            if (r3 >= r4) goto L_0x0265
            r13.zzc(r1)
            int r3 = r3 + 1
            r1 = 8
            goto L_0x025b
        L_0x0265:
            int r7 = r7 + 1
            r5 = r24
            r1 = 6
            r3 = 2
            r4 = 16
            r6 = 1
            r11 = 5
            r15 = 24
            goto L_0x01ae
        L_0x0273:
            int r3 = r13.zzb(r1)
            r4 = 1
            int r3 = r3 + r4
            r5 = 0
        L_0x027a:
            if (r5 >= r3) goto L_0x02e8
            r6 = 16
            int r7 = r13.zzb(r6)
            r6 = 2
            if (r7 > r6) goto L_0x02e0
            r6 = 24
            r13.zzc(r6)
            r13.zzc(r6)
            r13.zzc(r6)
            int r7 = r13.zzb(r1)
            int r7 = r7 + r4
            r1 = 8
            r13.zzc(r1)
            int[] r4 = new int[r7]
            r11 = 0
        L_0x029d:
            if (r11 >= r7) goto L_0x02bc
            r12 = 3
            int r14 = r13.zzb(r12)
            boolean r15 = r13.zzd()
            if (r15 == 0) goto L_0x02b0
            r15 = 5
            int r20 = r13.zzb(r15)
            goto L_0x02b3
        L_0x02b0:
            r15 = 5
            r20 = 0
        L_0x02b3:
            int r20 = r20 * 8
            int r20 = r20 + r14
            r4[r11] = r20
            int r11 = r11 + 1
            goto L_0x029d
        L_0x02bc:
            r12 = 3
            r15 = 5
            r11 = 0
        L_0x02bf:
            if (r11 >= r7) goto L_0x02db
            r14 = 0
        L_0x02c2:
            if (r14 >= r1) goto L_0x02d6
            r20 = r4[r11]
            r21 = 1
            int r23 = r21 << r14
            r20 = r20 & r23
            if (r20 == 0) goto L_0x02d1
            r13.zzc(r1)
        L_0x02d1:
            int r14 = r14 + 1
            r1 = 8
            goto L_0x02c2
        L_0x02d6:
            int r11 = r11 + 1
            r1 = 8
            goto L_0x02bf
        L_0x02db:
            int r5 = r5 + 1
            r1 = 6
            r4 = 1
            goto L_0x027a
        L_0x02e0:
            java.lang.String r1 = "residueType greater than 2 is not decodable"
            r2 = 0
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r2)
            throw r1
        L_0x02e8:
            int r3 = r13.zzb(r1)
            r1 = 1
            int r3 = r3 + r1
            r1 = 0
        L_0x02ef:
            if (r1 >= r3) goto L_0x0373
            r4 = 16
            int r5 = r13.zzb(r4)
            if (r5 == 0) goto L_0x030f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "mapping type other than 0 not supported: "
            r4.<init>(r6)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "VorbisUtil"
            com.google.android.gms.internal.ads.zzfk.zzc(r5, r4)
            r4 = 2
            r7 = 4
            goto L_0x0368
        L_0x030f:
            boolean r4 = r13.zzd()
            if (r4 == 0) goto L_0x031d
            r4 = 4
            int r5 = r13.zzb(r4)
            r4 = 1
            int r5 = r5 + r4
            goto L_0x031f
        L_0x031d:
            r4 = 1
            r5 = r4
        L_0x031f:
            boolean r6 = r13.zzd()
            if (r6 == 0) goto L_0x0342
            r6 = 8
            int r7 = r13.zzb(r6)
            int r7 = r7 + r4
            r4 = 0
        L_0x032d:
            if (r4 >= r7) goto L_0x0342
            int r6 = r10 + -1
            int r11 = com.google.android.gms.internal.ads.zzafg.zza(r6)
            r13.zzc(r11)
            int r6 = com.google.android.gms.internal.ads.zzafg.zza(r6)
            r13.zzc(r6)
            int r4 = r4 + 1
            goto L_0x032d
        L_0x0342:
            r4 = 2
            int r6 = r13.zzb(r4)
            if (r6 != 0) goto L_0x036b
            r6 = 1
            if (r5 <= r6) goto L_0x0356
            r6 = 0
        L_0x034d:
            if (r6 >= r10) goto L_0x0356
            r7 = 4
            r13.zzc(r7)
            int r6 = r6 + 1
            goto L_0x034d
        L_0x0356:
            r7 = 4
            r6 = 0
        L_0x0358:
            if (r6 >= r5) goto L_0x0368
            r11 = 8
            r13.zzc(r11)
            r13.zzc(r11)
            r13.zzc(r11)
            int r6 = r6 + 1
            goto L_0x0358
        L_0x0368:
            int r1 = r1 + 1
            goto L_0x02ef
        L_0x036b:
            java.lang.String r1 = "to reserved bits must be zero after mapping coupling steps"
            r2 = 0
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r2)
            throw r1
        L_0x0373:
            r1 = 6
            int r1 = r13.zzb(r1)
            int r3 = r1 + 1
            com.google.android.gms.internal.ads.zzafe[] r4 = new com.google.android.gms.internal.ads.zzafe[r3]
            r5 = 0
        L_0x037d:
            if (r5 >= r3) goto L_0x039d
            boolean r6 = r13.zzd()
            r7 = 16
            int r10 = r13.zzb(r7)
            int r11 = r13.zzb(r7)
            r12 = 8
            int r14 = r13.zzb(r12)
            com.google.android.gms.internal.ads.zzafe r15 = new com.google.android.gms.internal.ads.zzafe
            r15.<init>(r6, r10, r11, r14)
            r4[r5] = r15
            int r5 = r5 + 1
            goto L_0x037d
        L_0x039d:
            boolean r3 = r13.zzd()
            if (r3 == 0) goto L_0x0402
            com.google.android.gms.internal.ads.zzalj r3 = new com.google.android.gms.internal.ads.zzalj
            int r10 = com.google.android.gms.internal.ads.zzafg.zza(r1)
            r5 = r3
            r6 = r18
            r7 = r8
            r8 = r9
            r9 = r4
            r5.<init>(r6, r7, r8, r9, r10)
            r7 = r3
        L_0x03b3:
            r0.zza = r7
            if (r7 != 0) goto L_0x03b9
            r1 = 1
            return r1
        L_0x03b9:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.google.android.gms.internal.ads.zzaff r3 = r7.zza
            byte[] r4 = r3.zzg
            r1.add(r4)
            byte[] r4 = r7.zzc
            r1.add(r4)
            com.google.android.gms.internal.ads.zzafd r4 = r7.zzb
            java.lang.String[] r4 = r4.zzb
            com.google.android.gms.internal.ads.zzgbc r4 = com.google.android.gms.internal.ads.zzgbc.zzl(r4)
            com.google.android.gms.internal.ads.zzcd r4 = com.google.android.gms.internal.ads.zzafg.zzb(r4)
            com.google.android.gms.internal.ads.zzal r5 = new com.google.android.gms.internal.ads.zzal
            r5.<init>()
            java.lang.String r6 = "audio/vorbis"
            r5.zzX(r6)
            int r6 = r3.zzd
            r5.zzx(r6)
            int r6 = r3.zzc
            r5.zzS(r6)
            int r6 = r3.zza
            r5.zzy(r6)
            int r3 = r3.zzb
            r5.zzY(r3)
            r5.zzL(r1)
            r5.zzQ(r4)
            com.google.android.gms.internal.ads.zzan r1 = r5.zzad()
            r2.zza = r1
            r1 = 1
            return r1
        L_0x0402:
            java.lang.String r1 = "framing bit after modes not set as expected"
            r2 = 0
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzalk.zzc(com.google.android.gms.internal.ads.zzfu, long, com.google.android.gms.internal.ads.zzalf):boolean");
    }
}
