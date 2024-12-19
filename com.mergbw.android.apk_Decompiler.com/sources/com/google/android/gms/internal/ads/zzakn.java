package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzakn {
    private static final zzfyt zza = zzfyt.zzc(zzfxr.zzc(':'));
    private static final zzfyt zzb = zzfyt.zzc(zzfxr.zzc('*'));
    private final List zzc = new ArrayList();
    private int zzd = 0;
    private int zze;

    public final void zzb() {
        this.zzc.clear();
        this.zzd = 0;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(com.google.android.gms.internal.ads.zzadv r30, com.google.android.gms.internal.ads.zzaeq r31, java.util.List r32) throws java.io.IOException {
        /*
            r29 = this;
            r1 = r29
            r0 = r30
            r2 = r31
            int r3 = r1.zzd
            r6 = 1
            if (r3 == 0) goto L_0x0206
            r7 = 8
            r8 = 2
            r9 = 0
            if (r3 == r6) goto L_0x01d3
            r11 = 2819(0xb03, float:3.95E-42)
            r12 = 2817(0xb01, float:3.947E-42)
            r13 = 2816(0xb00, float:3.946E-42)
            r14 = 2192(0x890, float:3.072E-42)
            r15 = 3
            if (r3 == r8) goto L_0x0159
            long r16 = r30.zzf()
            long r18 = r30.zzd()
            long r20 = r30.zzf()
            long r18 = r18 - r20
            int r3 = r1.zze
            long r4 = (long) r3
            com.google.android.gms.internal.ads.zzfu r3 = new com.google.android.gms.internal.ads.zzfu
            long r4 = r18 - r4
            int r4 = (int) r4
            r3.<init>((int) r4)
            byte[] r5 = r3.zzM()
            r0.zzi(r5, r9, r4)
            r0 = r9
        L_0x003d:
            java.util.List r4 = r1.zzc
            int r4 = r4.size()
            if (r0 >= r4) goto L_0x0152
            java.util.List r4 = r1.zzc
            java.lang.Object r4 = r4.get(r0)
            com.google.android.gms.internal.ads.zzakm r4 = (com.google.android.gms.internal.ads.zzakm) r4
            long r9 = r4.zza
            long r9 = r9 - r16
            int r7 = (int) r9
            r3.zzK(r7)
            r7 = 4
            r3.zzL(r7)
            int r9 = r3.zzi()
            java.nio.charset.Charset r10 = com.google.android.gms.internal.ads.zzfxs.zzc
            java.lang.String r10 = r3.zzA(r9, r10)
            int r19 = r10.hashCode()
            r22 = -1
            switch(r19) {
                case -1711564334: goto L_0x0095;
                case -1332107749: goto L_0x008b;
                case -1251387154: goto L_0x0081;
                case -830665521: goto L_0x0077;
                case 1760745220: goto L_0x006d;
                default: goto L_0x006c;
            }
        L_0x006c:
            goto L_0x009f
        L_0x006d:
            java.lang.String r5 = "Super_SlowMotion_BGM"
            boolean r5 = r10.equals(r5)
            if (r5 == 0) goto L_0x009f
            r5 = r8
            goto L_0x00a1
        L_0x0077:
            java.lang.String r5 = "Super_SlowMotion_Deflickering_On"
            boolean r5 = r10.equals(r5)
            if (r5 == 0) goto L_0x009f
            r5 = r7
            goto L_0x00a1
        L_0x0081:
            java.lang.String r5 = "Super_SlowMotion_Data"
            boolean r5 = r10.equals(r5)
            if (r5 == 0) goto L_0x009f
            r5 = r6
            goto L_0x00a1
        L_0x008b:
            java.lang.String r5 = "Super_SlowMotion_Edit_Data"
            boolean r5 = r10.equals(r5)
            if (r5 == 0) goto L_0x009f
            r5 = r15
            goto L_0x00a1
        L_0x0095:
            java.lang.String r5 = "SlowMotion_Data"
            boolean r5 = r10.equals(r5)
            if (r5 == 0) goto L_0x009f
            r5 = 0
            goto L_0x00a1
        L_0x009f:
            r5 = r22
        L_0x00a1:
            r10 = 0
            if (r5 == 0) goto L_0x00bc
            if (r5 == r6) goto L_0x00ba
            if (r5 == r8) goto L_0x00b8
            if (r5 == r15) goto L_0x00b6
            if (r5 != r7) goto L_0x00af
            r5 = 2820(0xb04, float:3.952E-42)
            goto L_0x00bd
        L_0x00af:
            java.lang.String r0 = "Invalid SEF name"
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zza(r0, r10)
            throw r0
        L_0x00b6:
            r5 = r11
            goto L_0x00bd
        L_0x00b8:
            r5 = r12
            goto L_0x00bd
        L_0x00ba:
            r5 = r13
            goto L_0x00bd
        L_0x00bc:
            r5 = r14
        L_0x00bd:
            int r4 = r4.zzb
            int r9 = r9 + 8
            int r4 = r4 - r9
            if (r5 == r14) goto L_0x00d8
            if (r5 == r13) goto L_0x00d5
            if (r5 == r12) goto L_0x00d5
            if (r5 == r11) goto L_0x00d5
            r4 = 2820(0xb04, float:3.952E-42)
            if (r5 != r4) goto L_0x00cf
            goto L_0x00d5
        L_0x00cf:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L_0x00d5:
            r7 = r32
            goto L_0x014b
        L_0x00d8:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.nio.charset.Charset r5 = com.google.android.gms.internal.ads.zzfxs.zzc
            java.lang.String r4 = r3.zzA(r4, r5)
            com.google.android.gms.internal.ads.zzfyt r5 = zzb
            java.util.List r4 = r5.zzf(r4)
            r9 = 0
        L_0x00ea:
            int r5 = r4.size()
            if (r9 >= r5) goto L_0x0141
            com.google.android.gms.internal.ads.zzfyt r5 = zza
            java.lang.Object r19 = r4.get(r9)
            r11 = r19
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            java.util.List r11 = r5.zzf(r11)
            int r5 = r11.size()
            if (r5 != r15) goto L_0x013c
            r5 = 0
            java.lang.Object r19 = r11.get(r5)     // Catch:{ NumberFormatException -> 0x0136 }
            java.lang.String r19 = (java.lang.String) r19     // Catch:{ NumberFormatException -> 0x0136 }
            long r24 = java.lang.Long.parseLong(r19)     // Catch:{ NumberFormatException -> 0x0136 }
            java.lang.Object r19 = r11.get(r6)     // Catch:{ NumberFormatException -> 0x0136 }
            java.lang.String r19 = (java.lang.String) r19     // Catch:{ NumberFormatException -> 0x0136 }
            long r26 = java.lang.Long.parseLong(r19)     // Catch:{ NumberFormatException -> 0x0136 }
            java.lang.Object r11 = r11.get(r8)     // Catch:{ NumberFormatException -> 0x0136 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ NumberFormatException -> 0x0136 }
            int r11 = java.lang.Integer.parseInt(r11)     // Catch:{ NumberFormatException -> 0x0136 }
            int r11 = r11 + -1
            int r28 = r6 << r11
            com.google.android.gms.internal.ads.zzaii r11 = new com.google.android.gms.internal.ads.zzaii     // Catch:{ NumberFormatException -> 0x0136 }
            r23 = r11
            r23.<init>(r24, r26, r28)     // Catch:{ NumberFormatException -> 0x0136 }
            r7.add(r11)     // Catch:{ NumberFormatException -> 0x0136 }
            int r9 = r9 + 1
            r11 = 2819(0xb03, float:3.95E-42)
            goto L_0x00ea
        L_0x0136:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zza(r10, r0)
            throw r0
        L_0x013c:
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zza(r10, r10)
            throw r0
        L_0x0141:
            com.google.android.gms.internal.ads.zzaij r4 = new com.google.android.gms.internal.ads.zzaij
            r4.<init>(r7)
            r7 = r32
            r7.add(r4)
        L_0x014b:
            int r0 = r0 + 1
            r9 = 0
            r11 = 2819(0xb03, float:3.95E-42)
            goto L_0x003d
        L_0x0152:
            r9 = 0
            r2.zza = r9
            r0 = r6
            goto L_0x0224
        L_0x0159:
            long r3 = r30.zzd()
            int r9 = r1.zze
            int r9 = r9 + -20
            com.google.android.gms.internal.ads.zzfu r10 = new com.google.android.gms.internal.ads.zzfu
            r10.<init>((int) r9)
            byte[] r11 = r10.zzM()
            r5 = 0
            r0.zzi(r11, r5, r9)
            r0 = 0
        L_0x016f:
            int r11 = r9 / 12
            if (r0 >= r11) goto L_0x01b6
            r10.zzL(r8)
            short r11 = r10.zzC()
            if (r11 == r14) goto L_0x018e
            if (r11 == r13) goto L_0x018e
            if (r11 == r12) goto L_0x018e
            r5 = 2819(0xb03, float:3.95E-42)
            if (r11 == r5) goto L_0x018e
            r5 = 2820(0xb04, float:3.952E-42)
            if (r11 == r5) goto L_0x018e
            r10.zzL(r7)
            r32 = r9
            goto L_0x01a9
        L_0x018e:
            int r5 = r1.zze
            long r12 = (long) r5
            long r12 = r3 - r12
            int r5 = r10.zzi()
            r32 = r9
            long r8 = (long) r5
            int r5 = r10.zzi()
            java.util.List r14 = r1.zzc
            com.google.android.gms.internal.ads.zzakm r6 = new com.google.android.gms.internal.ads.zzakm
            long r12 = r12 - r8
            r6.<init>(r11, r12, r5)
            r14.add(r6)
        L_0x01a9:
            int r0 = r0 + 1
            r9 = r32
            r6 = 1
            r8 = 2
            r12 = 2817(0xb01, float:3.947E-42)
            r13 = 2816(0xb00, float:3.946E-42)
            r14 = 2192(0x890, float:3.072E-42)
            goto L_0x016f
        L_0x01b6:
            java.util.List r0 = r1.zzc
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x01c3
            r3 = 0
            r2.zza = r3
            goto L_0x0204
        L_0x01c3:
            r1.zzd = r15
            java.util.List r0 = r1.zzc
            r3 = 0
            java.lang.Object r0 = r0.get(r3)
            com.google.android.gms.internal.ads.zzakm r0 = (com.google.android.gms.internal.ads.zzakm) r0
            long r3 = r0.zza
            r2.zza = r3
            goto L_0x0204
        L_0x01d3:
            r3 = r9
            com.google.android.gms.internal.ads.zzfu r4 = new com.google.android.gms.internal.ads.zzfu
            r4.<init>((int) r7)
            byte[] r5 = r4.zzM()
            r0.zzi(r5, r3, r7)
            int r3 = r4.zzi()
            int r3 = r3 + r7
            r1.zze = r3
            int r3 = r4.zzg()
            r4 = 1397048916(0x53454654, float:8.4728847E11)
            if (r3 == r4) goto L_0x01f5
            r3 = 0
            r2.zza = r3
            goto L_0x0204
        L_0x01f5:
            long r3 = r30.zzf()
            int r0 = r1.zze
            int r0 = r0 + -12
            long r5 = (long) r0
            long r3 = r3 - r5
            r2.zza = r3
            r0 = 2
            r1.zzd = r0
        L_0x0204:
            r0 = 1
            goto L_0x0224
        L_0x0206:
            r3 = 0
            long r5 = r30.zzd()
            r7 = -1
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x021e
            r7 = 8
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0219
            goto L_0x021e
        L_0x0219:
            r3 = -8
            long r4 = r5 + r3
            goto L_0x021f
        L_0x021e:
            r4 = r3
        L_0x021f:
            r2.zza = r4
            r0 = 1
            r1.zzd = r0
        L_0x0224:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakn.zza(com.google.android.gms.internal.ads.zzadv, com.google.android.gms.internal.ads.zzaeq, java.util.List):int");
    }
}
