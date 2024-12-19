package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzahq {
    public static final zzaho zza = new zzahn();

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ac A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.google.android.gms.internal.ads.zzcd zza(byte[] r11, int r12, com.google.android.gms.internal.ads.zzaho r13, com.google.android.gms.internal.ads.zzagr r14) {
        /*
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            com.google.android.gms.internal.ads.zzfu r0 = new com.google.android.gms.internal.ads.zzfu
            r0.<init>(r11, r12)
            int r11 = r0.zzb()
            r12 = 2
            r1 = 4
            r2 = 0
            r3 = 1
            java.lang.String r4 = "Id3Decoder"
            r5 = 0
            r6 = 10
            if (r11 >= r6) goto L_0x0021
            java.lang.String r11 = "Data too short to be an ID3 tag"
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r11)
        L_0x001e:
            r9 = r5
            goto L_0x00aa
        L_0x0021:
            int r11 = r0.zzo()
            r7 = 4801587(0x494433, float:6.728456E-39)
            if (r11 == r7) goto L_0x0046
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.Object[] r7 = new java.lang.Object[r3]
            r7[r2] = r11
            java.lang.String r11 = "%06X"
            java.lang.String r11 = java.lang.String.format(r11, r7)
            java.lang.String r11 = java.lang.String.valueOf(r11)
            java.lang.String r7 = "Unexpected first three bytes of ID3 tag header: 0x"
            java.lang.String r11 = r7.concat(r11)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r11)
            goto L_0x001e
        L_0x0046:
            int r11 = r0.zzm()
            r0.zzL(r3)
            int r7 = r0.zzm()
            int r8 = r0.zzl()
            if (r11 != r12) goto L_0x0061
            r9 = r7 & 64
            if (r9 == 0) goto L_0x0088
            java.lang.String r11 = "Skipped ID3 tag with majorVersion=2 and undefined compression scheme"
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r11)
            goto L_0x001e
        L_0x0061:
            r9 = 3
            if (r11 != r9) goto L_0x0072
            r9 = r7 & 64
            if (r9 == 0) goto L_0x0088
            int r9 = r0.zzg()
            r0.zzL(r9)
            int r9 = r9 + r1
            int r8 = r8 - r9
            goto L_0x0088
        L_0x0072:
            if (r11 != r1) goto L_0x0097
            r9 = r7 & 64
            if (r9 == 0) goto L_0x0082
            int r9 = r0.zzl()
            int r10 = r9 + -4
            r0.zzL(r10)
            int r8 = r8 - r9
        L_0x0082:
            r9 = r7 & 16
            if (r9 == 0) goto L_0x0088
            int r8 = r8 + -10
        L_0x0088:
            if (r11 >= r1) goto L_0x0090
            r7 = r7 & 128(0x80, float:1.794E-43)
            if (r7 == 0) goto L_0x0090
            r7 = r3
            goto L_0x0091
        L_0x0090:
            r7 = r2
        L_0x0091:
            com.google.android.gms.internal.ads.zzahp r9 = new com.google.android.gms.internal.ads.zzahp
            r9.<init>(r11, r7, r8)
            goto L_0x00aa
        L_0x0097:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Skipped ID3 tag with unsupported majorVersion="
            r7.<init>(r8)
            r7.append(r11)
            java.lang.String r11 = r7.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r11)
            goto L_0x001e
        L_0x00aa:
            if (r9 != 0) goto L_0x00ad
            return r5
        L_0x00ad:
            int r11 = r0.zzd()
            int r7 = r9.zza
            if (r7 != r12) goto L_0x00b8
            r6 = 6
        L_0x00b8:
            int r12 = r9.zzc
            boolean r7 = r9.zzb
            if (r7 == 0) goto L_0x00ca
            int r12 = r9.zzc
            int r12 = zze(r0, r12)
        L_0x00ca:
            int r11 = r11 + r12
            r0.zzJ(r11)
            int r11 = r9.zza
            boolean r11 = zzk(r0, r11, r6, r2)
            if (r11 != 0) goto L_0x00fc
            int r11 = r9.zza
            if (r11 != r1) goto L_0x00e6
            boolean r11 = zzk(r0, r1, r6, r3)
            if (r11 == 0) goto L_0x00e6
            r2 = r3
            goto L_0x00fc
        L_0x00e6:
            int r11 = r9.zza
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "Failed to validate ID3 tag with majorVersion="
            r12.<init>(r13)
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r11)
            return r5
        L_0x00fc:
            int r11 = r0.zzb()
            if (r11 < r6) goto L_0x0110
            int r11 = r9.zza
            com.google.android.gms.internal.ads.zzahr r11 = zzf(r11, r0, r2, r6, r13)
            if (r11 == 0) goto L_0x00fc
            r14.add(r11)
            goto L_0x00fc
        L_0x0110:
            com.google.android.gms.internal.ads.zzcd r11 = new com.google.android.gms.internal.ads.zzcd
            r11.<init>((java.util.List) r14)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahq.zza(byte[], int, com.google.android.gms.internal.ads.zzaho, com.google.android.gms.internal.ads.zzagr):com.google.android.gms.internal.ads.zzcd");
    }

    private static int zzb(int i) {
        return (i == 0 || i == 3) ? 1 : 2;
    }

    private static int zzc(byte[] bArr, int i, int i2) {
        int zzd = zzd(bArr, i);
        if (i2 == 0 || i2 == 3) {
            return zzd;
        }
        while (true) {
            int length = bArr.length;
            if (zzd >= length - 1) {
                return length;
            }
            int i3 = zzd + 1;
            if ((zzd - i) % 2 == 0 && bArr[i3] == 0) {
                return zzd;
            }
            zzd = zzd(bArr, i3);
        }
    }

    private static int zzd(byte[] bArr, int i) {
        while (true) {
            int length = bArr.length;
            if (i >= length) {
                return length;
            }
            if (bArr[i] == 0) {
                return i;
            }
            i++;
        }
    }

    private static int zze(zzfu zzfu, int i) {
        byte[] zzM = zzfu.zzM();
        int zzd = zzfu.zzd();
        int i2 = zzd;
        while (true) {
            int i3 = i2 + 1;
            if (i3 >= zzd + i) {
                return i;
            }
            if ((zzM[i2] & 255) == 255 && zzM[i3] == 0) {
                System.arraycopy(zzM, i2 + 2, zzM, i3, (i - (i2 - zzd)) - 2);
                i--;
            }
            i2 = i3;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: com.google.android.gms.internal.ads.zzfu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: com.google.android.gms.internal.ads.zzfu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: com.google.android.gms.internal.ads.zzahg} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: com.google.android.gms.internal.ads.zzahg} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: com.google.android.gms.internal.ads.zzahe} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: com.google.android.gms.internal.ads.zzahv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v1, resolved type: com.google.android.gms.internal.ads.zzahi} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v28, resolved type: com.google.android.gms.internal.ads.zzahv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v48, resolved type: com.google.android.gms.internal.ads.zzahv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v72, resolved type: com.google.android.gms.internal.ads.zzfu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v75, resolved type: com.google.android.gms.internal.ads.zzfu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v78, resolved type: com.google.android.gms.internal.ads.zzfu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v79, resolved type: com.google.android.gms.internal.ads.zzfu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v80, resolved type: com.google.android.gms.internal.ads.zzfu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v81, resolved type: com.google.android.gms.internal.ads.zzfu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v3, resolved type: com.google.android.gms.internal.ads.zzahv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v4, resolved type: com.google.android.gms.internal.ads.zzahg} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v84, resolved type: com.google.android.gms.internal.ads.zzfu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v87, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v88, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: type inference failed for: r3v21, types: [com.google.android.gms.internal.ads.zzahk] */
    /* JADX WARNING: type inference failed for: r3v25, types: [com.google.android.gms.internal.ads.zzahc] */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x027a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x027d, code lost:
        r23 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0280, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0283, code lost:
        r23 = r5;
        r22 = "Id3Decoder";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0287, code lost:
        r8 = r2;
        r3 = r6;
        r4 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x02e7, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x0431, code lost:
        r0 = th;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x04b9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x04bb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x04bc, code lost:
        r21 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x04bf, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x04c1, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x04c2, code lost:
        r21 = r6;
        r20 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:277:0x050d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x0510, code lost:
        r2 = r0;
        r10 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x0514, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x0517, code lost:
        r21 = r6;
        r20 = r7;
        r18 = r10;
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:281:0x051e, code lost:
        r4 = r20;
        r3 = r21;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0583, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:296:0x0586, code lost:
        r2 = r0;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x016c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x016d, code lost:
        r1 = r0;
        r8 = r2;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:199:0x0373, B:240:0x044d] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:199:0x0373, B:249:0x0492] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:199:0x0373, B:252:0x0496] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:199:0x0373, B:255:0x04a0] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:199:0x0373, B:290:0x053e] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:79:0x010c, B:127:0x022d] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:79:0x010c, B:130:0x0245] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:79:0x010c, B:133:0x024a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02c5 A[SYNTHETIC, Splitter:B:159:0x02c5] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x02eb  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x0431 A[ExcHandler: all (th java.lang.Throwable), PHI: r8 r24 
      PHI: (r8v16 com.google.android.gms.internal.ads.zzfu) = (r8v72 com.google.android.gms.internal.ads.zzfu), (r8v75 com.google.android.gms.internal.ads.zzfu), (r8v78 com.google.android.gms.internal.ads.zzfu), (r8v79 com.google.android.gms.internal.ads.zzfu), (r8v80 com.google.android.gms.internal.ads.zzfu), (r8v81 com.google.android.gms.internal.ads.zzfu), (r8v84 com.google.android.gms.internal.ads.zzfu), (r8v88 java.lang.String) binds: [B:290:0x053e, B:240:0x044d, B:249:0x0492, B:250:?, B:252:0x0496, B:255:0x04a0, B:199:0x0373, B:177:0x031c] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r24v5 int) = (r24v8 int), (r24v8 int), (r24v8 int), (r24v8 int), (r24v8 int), (r24v8 int), (r24v8 int), (r24v15 int) binds: [B:290:0x053e, B:240:0x044d, B:249:0x0492, B:250:?, B:252:0x0496, B:255:0x04a0, B:199:0x0373, B:177:0x031c] A[DONT_GENERATE, DONT_INLINE], Splitter:B:199:0x0373] */
    /* JADX WARNING: Removed duplicated region for block: B:284:0x052c  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x058e  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x05c2  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x016c A[Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }, ExcHandler: all (r0v1 'th' java.lang.Throwable A[CUSTOM_DECLARE, Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }]), Splitter:B:79:0x010c] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.ads.zzahr zzf(int r35, com.google.android.gms.internal.ads.zzfu r36, boolean r37, int r38, com.google.android.gms.internal.ads.zzaho r39) {
        /*
            r1 = r35
            r2 = r36
            r3 = r37
            r4 = r38
            int r5 = r36.zzm()
            int r6 = r36.zzm()
            int r7 = r36.zzm()
            r9 = 3
            if (r1 < r9) goto L_0x001c
            int r10 = r36.zzm()
            goto L_0x001d
        L_0x001c:
            r10 = 0
        L_0x001d:
            r11 = 4
            if (r1 != r11) goto L_0x003c
            int r12 = r36.zzp()
            if (r3 != 0) goto L_0x0047
            r13 = r12 & 255(0xff, float:3.57E-43)
            int r14 = r12 >> 8
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r15 = r12 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r12 = r12 >> 24
            int r14 = r14 << 7
            r13 = r13 | r14
            int r14 = r15 << 14
            r13 = r13 | r14
            int r12 = r12 << 21
            r12 = r12 | r13
            goto L_0x0047
        L_0x003c:
            if (r1 != r9) goto L_0x0043
            int r12 = r36.zzp()
            goto L_0x0047
        L_0x0043:
            int r12 = r36.zzo()
        L_0x0047:
            if (r1 < r9) goto L_0x004e
            int r13 = r36.zzq()
            goto L_0x004f
        L_0x004e:
            r13 = 0
        L_0x004f:
            r14 = 0
            if (r5 != 0) goto L_0x0065
            if (r6 != 0) goto L_0x0065
            if (r7 != 0) goto L_0x0065
            if (r10 != 0) goto L_0x0065
            if (r12 != 0) goto L_0x0065
            if (r13 == 0) goto L_0x005d
            goto L_0x0065
        L_0x005d:
            int r1 = r36.zze()
            r2.zzK(r1)
            return r14
        L_0x0065:
            int r15 = r36.zzd()
            int r15 = r15 + r12
            int r8 = r36.zze()
            java.lang.String r11 = "Id3Decoder"
            if (r15 <= r8) goto L_0x007f
            java.lang.String r1 = "Frame size exceeds remaining tag data"
            com.google.android.gms.internal.ads.zzfk.zzf(r11, r1)
            int r1 = r36.zze()
            r2.zzK(r1)
            return r14
        L_0x007f:
            if (r39 != 0) goto L_0x05f0
            r8 = 1
            if (r1 != r9) goto L_0x00a2
            r17 = r13 & 64
            r9 = r13 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x008c
            r9 = r8
            goto L_0x008d
        L_0x008c:
            r9 = 0
        L_0x008d:
            if (r17 == 0) goto L_0x0092
            r17 = r8
            goto L_0x0094
        L_0x0092:
            r17 = 0
        L_0x0094:
            r13 = r13 & 32
            if (r13 == 0) goto L_0x009a
            r13 = r8
            goto L_0x009b
        L_0x009a:
            r13 = 0
        L_0x009b:
            r19 = r17
            r20 = 0
            r17 = r9
            goto L_0x00d6
        L_0x00a2:
            r9 = 4
            if (r1 != r9) goto L_0x00ce
            r9 = r13 & 64
            if (r9 == 0) goto L_0x00ab
            r9 = r8
            goto L_0x00ac
        L_0x00ab:
            r9 = 0
        L_0x00ac:
            r17 = r13 & 8
            if (r17 == 0) goto L_0x00b3
            r17 = r8
            goto L_0x00b5
        L_0x00b3:
            r17 = 0
        L_0x00b5:
            r19 = r13 & 4
            if (r19 == 0) goto L_0x00bc
            r19 = r8
            goto L_0x00be
        L_0x00bc:
            r19 = 0
        L_0x00be:
            r20 = r13 & 2
            if (r20 == 0) goto L_0x00c5
            r20 = r8
            goto L_0x00c7
        L_0x00c5:
            r20 = 0
        L_0x00c7:
            r13 = r13 & r8
            r34 = r13
            r13 = r9
            r9 = r34
            goto L_0x00d6
        L_0x00ce:
            r9 = 0
            r13 = 0
            r17 = 0
            r19 = 0
            r20 = 0
        L_0x00d6:
            if (r17 != 0) goto L_0x05e4
            if (r19 == 0) goto L_0x00dc
            goto L_0x05e4
        L_0x00dc:
            if (r13 == 0) goto L_0x00e3
            r2.zzL(r8)
            int r12 = r12 + -1
        L_0x00e3:
            if (r9 == 0) goto L_0x00eb
            r9 = 4
            r2.zzL(r9)
            int r12 = r12 + -4
        L_0x00eb:
            if (r20 == 0) goto L_0x00f1
            int r12 = zze(r2, r12)
        L_0x00f1:
            r9 = 84
            r13 = 88
            r8 = 2
            if (r5 != r9) goto L_0x0137
            if (r6 != r13) goto L_0x0137
            if (r7 != r13) goto L_0x0137
            if (r1 == r8) goto L_0x0100
            if (r10 != r13) goto L_0x0137
        L_0x0100:
            if (r12 > 0) goto L_0x010c
            r8 = r2
            r23 = r5
            r3 = r6
            r4 = r7
            r22 = r11
            r2 = r14
            goto L_0x05a6
        L_0x010c:
            int r3 = r36.zzm()     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            int r4 = r12 + -1
            byte[] r8 = new byte[r4]     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r9 = 0
            r2.zzG(r8, r9, r4)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            int r4 = zzc(r8, r9, r3)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.lang.String r13 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.nio.charset.Charset r14 = zzj(r3)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r13.<init>(r8, r9, r4, r14)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            int r9 = zzb(r3)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            int r4 = r4 + r9
            com.google.android.gms.internal.ads.zzgbc r3 = zzg(r8, r3, r4)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            com.google.android.gms.internal.ads.zzahz r4 = new com.google.android.gms.internal.ads.zzahz     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.lang.String r8 = "TXXX"
            r4.<init>(r8, r13, r3)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            goto L_0x01dd
        L_0x0137:
            if (r5 != r9) goto L_0x017e
            java.lang.String r3 = zzi(r1, r9, r6, r7, r10)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            if (r12 > 0) goto L_0x0149
        L_0x013f:
            r8 = r2
            r23 = r5
            r3 = r6
            r4 = r7
            r22 = r11
        L_0x0146:
            r2 = 0
            goto L_0x05a6
        L_0x0149:
            int r4 = r36.zzm()     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            int r8 = r12 + -1
            byte[] r9 = new byte[r8]     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r13 = 0
            r2.zzG(r9, r13, r8)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            com.google.android.gms.internal.ads.zzgbc r4 = zzg(r9, r4, r13)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            com.google.android.gms.internal.ads.zzahz r8 = new com.google.android.gms.internal.ads.zzahz     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r9 = 0
            r8.<init>(r3, r9, r4)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r23 = r5
            r3 = r6
            r4 = r7
            r22 = r11
        L_0x0165:
            r34 = r8
            r8 = r2
            r2 = r34
            goto L_0x05a6
        L_0x016c:
            r0 = move-exception
            r1 = r0
            r8 = r2
            goto L_0x05b1
        L_0x0171:
            r0 = move-exception
            goto L_0x0174
        L_0x0173:
            r0 = move-exception
        L_0x0174:
            r8 = r2
            r23 = r5
            r3 = r6
            r4 = r7
            r22 = r11
        L_0x017b:
            r2 = r0
            goto L_0x05bc
        L_0x017e:
            r14 = 87
            if (r5 != r14) goto L_0x01bf
            if (r6 != r13) goto L_0x01bd
            if (r7 != r13) goto L_0x01bd
            if (r1 == r8) goto L_0x018a
            if (r10 != r13) goto L_0x01bd
        L_0x018a:
            if (r12 > 0) goto L_0x018d
            goto L_0x013f
        L_0x018d:
            int r3 = r36.zzm()     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            int r4 = r12 + -1
            byte[] r8 = new byte[r4]     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r9 = 0
            r2.zzG(r8, r9, r4)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            int r4 = zzc(r8, r9, r3)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.lang.String r13 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.nio.charset.Charset r14 = zzj(r3)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r13.<init>(r8, r9, r4, r14)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            int r3 = zzb(r3)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            int r4 = r4 + r3
            int r3 = zzd(r8, r4)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.nio.charset.Charset r9 = com.google.android.gms.internal.ads.zzfxs.zzb     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.lang.String r3 = zzh(r8, r4, r3, r9)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            com.google.android.gms.internal.ads.zzaib r4 = new com.google.android.gms.internal.ads.zzaib     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.lang.String r8 = "WXXX"
            r4.<init>(r8, r13, r3)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            goto L_0x01dd
        L_0x01bd:
            r13 = r14
            goto L_0x01c0
        L_0x01bf:
            r13 = r5
        L_0x01c0:
            if (r13 != r14) goto L_0x01e7
            java.lang.String r3 = zzi(r1, r14, r6, r7, r10)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            byte[] r4 = new byte[r12]     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r8 = 0
            r2.zzG(r4, r8, r12)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            int r9 = zzd(r4, r8)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.lang.String r13 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.nio.charset.Charset r14 = com.google.android.gms.internal.ads.zzfxs.zzb     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r13.<init>(r4, r8, r9, r14)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            com.google.android.gms.internal.ads.zzaib r4 = new com.google.android.gms.internal.ads.zzaib     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r8 = 0
            r4.<init>(r3, r8, r13)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
        L_0x01dd:
            r8 = r2
            r2 = r4
            r23 = r5
            r3 = r6
            r4 = r7
            r22 = r11
            goto L_0x05a6
        L_0x01e7:
            r14 = 73
            r9 = 80
            if (r13 != r9) goto L_0x0215
            r13 = 82
            if (r6 != r13) goto L_0x0214
            if (r7 != r14) goto L_0x0214
            r13 = 86
            if (r10 != r13) goto L_0x0214
            byte[] r3 = new byte[r12]     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r4 = 0
            r2.zzG(r3, r4, r12)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            int r8 = zzd(r3, r4)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.lang.String r9 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            java.nio.charset.Charset r13 = com.google.android.gms.internal.ads.zzfxs.zzb     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r9.<init>(r3, r4, r8, r13)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r4 = 1
            int r8 = r8 + r4
            byte[] r3 = zzl(r3, r8, r12)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            com.google.android.gms.internal.ads.zzahx r4 = new com.google.android.gms.internal.ads.zzahx     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            r4.<init>(r9, r3)     // Catch:{ OutOfMemoryError -> 0x0173, Exception -> 0x0171, all -> 0x016c }
            goto L_0x01dd
        L_0x0214:
            r13 = r9
        L_0x0215:
            r14 = 79
            r9 = 71
            if (r13 != r9) goto L_0x0292
            r13 = 69
            if (r6 != r13) goto L_0x028c
            if (r7 != r14) goto L_0x028c
            r13 = 66
            if (r10 == r13) goto L_0x022d
            if (r1 != r8) goto L_0x0228
            goto L_0x022d
        L_0x0228:
            r23 = r5
            r13 = r9
            goto L_0x0294
        L_0x022d:
            int r3 = r36.zzm()     // Catch:{ OutOfMemoryError -> 0x0282, Exception -> 0x0280, all -> 0x016c }
            java.nio.charset.Charset r4 = zzj(r3)     // Catch:{ OutOfMemoryError -> 0x0282, Exception -> 0x0280, all -> 0x016c }
            int r8 = r12 + -1
            byte[] r9 = new byte[r8]     // Catch:{ OutOfMemoryError -> 0x0282, Exception -> 0x0280, all -> 0x016c }
            r13 = 0
            r2.zzG(r9, r13, r8)     // Catch:{ OutOfMemoryError -> 0x0282, Exception -> 0x0280, all -> 0x016c }
            int r14 = zzd(r9, r13)     // Catch:{ OutOfMemoryError -> 0x0282, Exception -> 0x0280, all -> 0x016c }
            java.lang.String r13 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x0282, Exception -> 0x0280, all -> 0x016c }
            r22 = r11
            java.nio.charset.Charset r11 = com.google.android.gms.internal.ads.zzfxs.zzb     // Catch:{ OutOfMemoryError -> 0x027c, Exception -> 0x027a, all -> 0x016c }
            r23 = r5
            r5 = 0
            r13.<init>(r9, r5, r14, r11)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            java.lang.String r5 = com.google.android.gms.internal.ads.zzcg.zze(r13)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            r11 = 1
            int r14 = r14 + r11
            int r11 = zzc(r9, r14, r3)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            java.lang.String r13 = zzh(r9, r14, r11, r4)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            int r14 = zzb(r3)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            int r11 = r11 + r14
            int r14 = zzc(r9, r11, r3)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            java.lang.String r4 = zzh(r9, r11, r14, r4)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            int r3 = zzb(r3)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            int r14 = r14 + r3
            byte[] r3 = zzl(r9, r14, r8)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            com.google.android.gms.internal.ads.zzahm r8 = new com.google.android.gms.internal.ads.zzahm     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            r8.<init>(r5, r13, r4, r3)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            r3 = r6
            r4 = r7
            goto L_0x0165
        L_0x027a:
            r0 = move-exception
            goto L_0x027d
        L_0x027c:
            r0 = move-exception
        L_0x027d:
            r23 = r5
            goto L_0x0287
        L_0x0280:
            r0 = move-exception
            goto L_0x0283
        L_0x0282:
            r0 = move-exception
        L_0x0283:
            r23 = r5
            r22 = r11
        L_0x0287:
            r8 = r2
            r3 = r6
            r4 = r7
            goto L_0x017b
        L_0x028c:
            r23 = r5
            r22 = r11
            r13 = r9
            goto L_0x0296
        L_0x0292:
            r23 = r5
        L_0x0294:
            r22 = r11
        L_0x0296:
            r5 = 65
            r9 = 67
            if (r1 != r8) goto L_0x02a7
            r11 = 80
            if (r13 != r11) goto L_0x0349
            r14 = 73
            if (r6 != r14) goto L_0x0349
            if (r7 != r9) goto L_0x0349
            goto L_0x02b3
        L_0x02a7:
            r11 = 80
            r14 = 73
            if (r13 != r5) goto L_0x0349
            if (r6 != r11) goto L_0x0349
            if (r7 != r14) goto L_0x0349
            if (r10 != r9) goto L_0x0349
        L_0x02b3:
            int r3 = r36.zzm()     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            java.nio.charset.Charset r4 = zzj(r3)     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            int r5 = r12 + -1
            byte[] r9 = new byte[r5]     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            r11 = 0
            r2.zzG(r9, r11, r5)     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            if (r1 != r8) goto L_0x02eb
            java.lang.String r13 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            java.nio.charset.Charset r14 = com.google.android.gms.internal.ads.zzfxs.zzb     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            r8 = 3
            r13.<init>(r9, r11, r8, r14)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            java.lang.String r8 = com.google.android.gms.internal.ads.zzfxm.zza(r13)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            java.lang.String r11 = "image/"
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            java.lang.String r8 = r11.concat(r8)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            java.lang.String r11 = "image/jpg"
            boolean r11 = r11.equals(r8)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            if (r11 == 0) goto L_0x02e5
            java.lang.String r8 = "image/jpeg"
        L_0x02e5:
            r11 = 2
            goto L_0x030e
        L_0x02e7:
            r0 = move-exception
            goto L_0x0287
        L_0x02e9:
            r0 = move-exception
            goto L_0x0287
        L_0x02eb:
            r8 = r11
            int r11 = zzd(r9, r8)     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            java.lang.String r13 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            java.nio.charset.Charset r14 = com.google.android.gms.internal.ads.zzfxs.zzb     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            r13.<init>(r9, r8, r11, r14)     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            java.lang.String r8 = com.google.android.gms.internal.ads.zzfxm.zza(r13)     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            r13 = 47
            int r13 = r8.indexOf(r13)     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            r14 = -1
            if (r13 != r14) goto L_0x030e
            java.lang.String r13 = "image/"
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
            java.lang.String r8 = r13.concat(r8)     // Catch:{ OutOfMemoryError -> 0x02e9, Exception -> 0x02e7, all -> 0x016c }
        L_0x030e:
            int r13 = r11 + 1
            byte r13 = r9[r13]     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            r13 = r13 & 255(0xff, float:3.57E-43)
            r14 = 2
            int r11 = r11 + r14
            int r14 = zzc(r9, r11, r3)     // Catch:{ OutOfMemoryError -> 0x033f, Exception -> 0x033d, all -> 0x0335 }
            r24 = r15
            java.lang.String r15 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x03a9, Exception -> 0x03a7, all -> 0x03a2 }
            int r2 = r14 - r11
            r15.<init>(r9, r11, r2, r4)     // Catch:{ OutOfMemoryError -> 0x03a9, Exception -> 0x03a7, all -> 0x03a2 }
            int r2 = zzb(r3)     // Catch:{ OutOfMemoryError -> 0x03a9, Exception -> 0x03a7, all -> 0x03a2 }
            int r14 = r14 + r2
            byte[] r2 = zzl(r9, r14, r5)     // Catch:{ OutOfMemoryError -> 0x03a9, Exception -> 0x03a7, all -> 0x03a2 }
            com.google.android.gms.internal.ads.zzahc r3 = new com.google.android.gms.internal.ads.zzahc     // Catch:{ OutOfMemoryError -> 0x03a9, Exception -> 0x03a7, all -> 0x03a2 }
            r3.<init>(r8, r15, r13, r2)     // Catch:{ OutOfMemoryError -> 0x03a9, Exception -> 0x03a7, all -> 0x03a2 }
            r8 = r36
            goto L_0x039d
        L_0x0335:
            r0 = move-exception
            r24 = r15
            r8 = r36
        L_0x033a:
            r1 = r0
            goto L_0x05b1
        L_0x033d:
            r0 = move-exception
            goto L_0x0340
        L_0x033f:
            r0 = move-exception
        L_0x0340:
            r24 = r15
            r8 = r36
            r2 = r0
            r3 = r6
            r4 = r7
            goto L_0x05bc
        L_0x0349:
            r24 = r15
            r2 = 77
            if (r13 != r9) goto L_0x03ae
            r8 = 79
            if (r6 != r8) goto L_0x03ae
            if (r7 != r2) goto L_0x03ae
            if (r10 == r2) goto L_0x035a
            r8 = 2
            if (r1 != r8) goto L_0x03ae
        L_0x035a:
            r2 = 4
            if (r12 >= r2) goto L_0x0365
            r8 = r36
            r3 = r6
            r4 = r7
            r15 = r24
            goto L_0x0146
        L_0x0365:
            int r2 = r36.zzm()     // Catch:{ OutOfMemoryError -> 0x03a9, Exception -> 0x03a7, all -> 0x03a2 }
            java.nio.charset.Charset r3 = zzj(r2)     // Catch:{ OutOfMemoryError -> 0x03a9, Exception -> 0x03a7, all -> 0x03a2 }
            r4 = 3
            byte[] r5 = new byte[r4]     // Catch:{ OutOfMemoryError -> 0x03a9, Exception -> 0x03a7, all -> 0x03a2 }
            r8 = r36
            r9 = 0
            r8.zzG(r5, r9, r4)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            java.lang.String r11 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            r11.<init>(r5, r9, r4)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            int r4 = r12 + -4
            byte[] r5 = new byte[r4]     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            r8.zzG(r5, r9, r4)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            int r4 = zzc(r5, r9, r2)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            java.lang.String r13 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            r13.<init>(r5, r9, r4, r3)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            int r9 = zzb(r2)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            int r4 = r4 + r9
            int r2 = zzc(r5, r4, r2)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            java.lang.String r2 = zzh(r5, r4, r2, r3)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            com.google.android.gms.internal.ads.zzahk r3 = new com.google.android.gms.internal.ads.zzahk     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            r3.<init>(r11, r13, r2)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
        L_0x039d:
            r2 = r3
        L_0x039e:
            r3 = r6
            r4 = r7
            goto L_0x05a4
        L_0x03a2:
            r0 = move-exception
            r8 = r36
            goto L_0x0432
        L_0x03a7:
            r0 = move-exception
            goto L_0x03aa
        L_0x03a9:
            r0 = move-exception
        L_0x03aa:
            r8 = r36
            goto L_0x043a
        L_0x03ae:
            r8 = r36
            if (r13 != r9) goto L_0x0441
            r11 = 72
            if (r6 != r11) goto L_0x0441
            if (r7 != r5) goto L_0x0441
            r5 = 80
            if (r10 != r5) goto L_0x0441
            int r2 = r36.zzd()     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            byte[] r5 = r36.zzM()     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            int r5 = zzd(r5, r2)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            java.lang.String r9 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            byte[] r11 = r36.zzM()     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            int r13 = r5 - r2
            java.nio.charset.Charset r14 = com.google.android.gms.internal.ads.zzfxs.zzb     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            r9.<init>(r11, r2, r13, r14)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            r11 = 1
            int r5 = r5 + r11
            r8.zzK(r5)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            int r27 = r36.zzg()     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            int r28 = r36.zzg()     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            long r13 = r36.zzu()     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            r18 = 4294967295(0xffffffff, double:2.1219957905E-314)
            int r5 = (r13 > r18 ? 1 : (r13 == r18 ? 0 : -1))
            if (r5 != 0) goto L_0x03f1
            r13 = -1
        L_0x03f1:
            r29 = r13
            long r13 = r36.zzu()     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            r18 = 4294967295(0xffffffff, double:2.1219957905E-314)
            int r5 = (r13 > r18 ? 1 : (r13 == r18 ? 0 : -1))
            if (r5 != 0) goto L_0x0402
            r13 = -1
        L_0x0402:
            r31 = r13
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            r5.<init>()     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            int r2 = r2 + r12
        L_0x040a:
            int r11 = r36.zzd()     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            if (r11 >= r2) goto L_0x041b
            r11 = 0
            com.google.android.gms.internal.ads.zzahr r13 = zzf(r1, r8, r3, r4, r11)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            if (r13 == 0) goto L_0x040a
            r5.add(r13)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            goto L_0x040a
        L_0x041b:
            r2 = 0
            com.google.android.gms.internal.ads.zzahr[] r2 = new com.google.android.gms.internal.ads.zzahr[r2]     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            java.lang.Object[] r2 = r5.toArray(r2)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            r33 = r2
            com.google.android.gms.internal.ads.zzahr[] r33 = (com.google.android.gms.internal.ads.zzahr[]) r33     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            com.google.android.gms.internal.ads.zzahg r2 = new com.google.android.gms.internal.ads.zzahg     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            r25 = r2
            r26 = r9
            r25.<init>(r26, r27, r28, r29, r31, r33)     // Catch:{ OutOfMemoryError -> 0x0439, Exception -> 0x0437, all -> 0x0431 }
            goto L_0x039e
        L_0x0431:
            r0 = move-exception
        L_0x0432:
            r1 = r0
            r15 = r24
            goto L_0x05b1
        L_0x0437:
            r0 = move-exception
            goto L_0x043a
        L_0x0439:
            r0 = move-exception
        L_0x043a:
            r2 = r0
            r3 = r6
            r4 = r7
        L_0x043d:
            r15 = r24
            goto L_0x05bc
        L_0x0441:
            if (r13 != r9) goto L_0x0524
            r5 = 84
            if (r6 != r5) goto L_0x0524
            r5 = 79
            if (r7 != r5) goto L_0x0524
            if (r10 != r9) goto L_0x0524
            int r2 = r36.zzd()     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            byte[] r5 = r36.zzM()     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            int r5 = zzd(r5, r2)     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            java.lang.String r9 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            byte[] r11 = r36.zzM()     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            int r13 = r5 - r2
            java.nio.charset.Charset r14 = com.google.android.gms.internal.ads.zzfxs.zzb     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            r9.<init>(r11, r2, r13, r14)     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            r11 = 1
            int r5 = r5 + r11
            r8.zzK(r5)     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            int r5 = r36.zzm()     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            r13 = r5 & 2
            if (r13 == 0) goto L_0x0476
            r27 = r11
            goto L_0x0478
        L_0x0476:
            r27 = 0
        L_0x0478:
            r5 = r5 & r11
            int r11 = r36.zzm()     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            java.lang.String[] r13 = new java.lang.String[r11]     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            r14 = 0
        L_0x0480:
            if (r14 >= r11) goto L_0x04c7
            int r15 = r36.zzd()     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            r16 = r11
            byte[] r11 = r36.zzM()     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            int r11 = zzd(r11, r15)     // Catch:{ OutOfMemoryError -> 0x0516, Exception -> 0x0514, all -> 0x0431 }
            r18 = r10
            java.lang.String r10 = new java.lang.String     // Catch:{ OutOfMemoryError -> 0x04c1, Exception -> 0x04bf, all -> 0x0431 }
            r20 = r7
            byte[] r7 = r36.zzM()     // Catch:{ OutOfMemoryError -> 0x04bb, Exception -> 0x04b9, all -> 0x0431 }
            r21 = r6
            int r6 = r11 - r15
            r19 = r9
            java.nio.charset.Charset r9 = com.google.android.gms.internal.ads.zzfxs.zzb     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            r10.<init>(r7, r15, r6, r9)     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            r13[r14] = r10     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            int r11 = r11 + 1
            r8.zzK(r11)     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            int r14 = r14 + 1
            r11 = r16
            r10 = r18
            r9 = r19
            r7 = r20
            r6 = r21
            goto L_0x0480
        L_0x04b9:
            r0 = move-exception
            goto L_0x04bc
        L_0x04bb:
            r0 = move-exception
        L_0x04bc:
            r21 = r6
            goto L_0x0510
        L_0x04bf:
            r0 = move-exception
            goto L_0x04c2
        L_0x04c1:
            r0 = move-exception
        L_0x04c2:
            r21 = r6
            r20 = r7
            goto L_0x0510
        L_0x04c7:
            r21 = r6
            r20 = r7
            r19 = r9
            r18 = r10
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            r6.<init>()     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            int r2 = r2 + r12
        L_0x04d5:
            int r7 = r36.zzd()     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            if (r7 >= r2) goto L_0x04e6
            r7 = 0
            com.google.android.gms.internal.ads.zzahr r9 = zzf(r1, r8, r3, r4, r7)     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            if (r9 == 0) goto L_0x04d5
            r6.add(r9)     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            goto L_0x04d5
        L_0x04e6:
            r2 = 0
            com.google.android.gms.internal.ads.zzahr[] r3 = new com.google.android.gms.internal.ads.zzahr[r2]     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            java.lang.Object[] r2 = r6.toArray(r3)     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            r30 = r2
            com.google.android.gms.internal.ads.zzahr[] r30 = (com.google.android.gms.internal.ads.zzahr[]) r30     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            com.google.android.gms.internal.ads.zzahi r4 = new com.google.android.gms.internal.ads.zzahi     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            r2 = 1
            if (r2 == r5) goto L_0x04f9
            r28 = 0
            goto L_0x04fb
        L_0x04f9:
            r28 = r2
        L_0x04fb:
            r25 = r4
            r26 = r19
            r29 = r13
            r25.<init>(r26, r27, r28, r29, r30)     // Catch:{ OutOfMemoryError -> 0x050f, Exception -> 0x050d, all -> 0x0431 }
            r2 = r4
            r10 = r18
            r4 = r20
            r3 = r21
            goto L_0x05a4
        L_0x050d:
            r0 = move-exception
            goto L_0x0510
        L_0x050f:
            r0 = move-exception
        L_0x0510:
            r2 = r0
            r10 = r18
            goto L_0x051e
        L_0x0514:
            r0 = move-exception
            goto L_0x0517
        L_0x0516:
            r0 = move-exception
        L_0x0517:
            r21 = r6
            r20 = r7
            r18 = r10
            r2 = r0
        L_0x051e:
            r4 = r20
            r3 = r21
            goto L_0x043d
        L_0x0524:
            r21 = r6
            r20 = r7
            r18 = r10
            if (r13 != r2) goto L_0x058e
            r2 = 76
            r3 = r21
            if (r3 != r2) goto L_0x0589
            r2 = 76
            r4 = r20
            r10 = r18
            if (r4 != r2) goto L_0x0594
            r2 = 84
            if (r10 != r2) goto L_0x0594
            int r26 = r36.zzq()     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            int r27 = r36.zzo()     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            int r28 = r36.zzo()     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            int r2 = r36.zzm()     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            int r5 = r36.zzm()     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            com.google.android.gms.internal.ads.zzft r6 = new com.google.android.gms.internal.ads.zzft     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            r6.<init>()     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            r6.zzi(r8)     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            int r7 = r12 + -10
            int r7 = r7 * 8
            int r9 = r2 + r5
            int r7 = r7 / r9
            int[] r9 = new int[r7]     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            int[] r11 = new int[r7]     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            r13 = 0
        L_0x0566:
            if (r13 >= r7) goto L_0x0577
            int r14 = r6.zzd(r2)     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            int r15 = r6.zzd(r5)     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            r9[r13] = r14     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            r11[r13] = r15     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            int r13 = r13 + 1
            goto L_0x0566
        L_0x0577:
            com.google.android.gms.internal.ads.zzahv r2 = new com.google.android.gms.internal.ads.zzahv     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            r25 = r2
            r29 = r9
            r30 = r11
            r25.<init>(r26, r27, r28, r29, r30)     // Catch:{ OutOfMemoryError -> 0x0585, Exception -> 0x0583, all -> 0x0431 }
            goto L_0x05a4
        L_0x0583:
            r0 = move-exception
            goto L_0x0586
        L_0x0585:
            r0 = move-exception
        L_0x0586:
            r2 = r0
            goto L_0x043d
        L_0x0589:
            r10 = r18
            r4 = r20
            goto L_0x0594
        L_0x058e:
            r10 = r18
            r4 = r20
            r3 = r21
        L_0x0594:
            java.lang.String r2 = zzi(r1, r13, r3, r4, r10)     // Catch:{ OutOfMemoryError -> 0x05b7, Exception -> 0x05b5, all -> 0x05ac }
            byte[] r5 = new byte[r12]     // Catch:{ OutOfMemoryError -> 0x05b7, Exception -> 0x05b5, all -> 0x05ac }
            r6 = 0
            r8.zzG(r5, r6, r12)     // Catch:{ OutOfMemoryError -> 0x05b7, Exception -> 0x05b5, all -> 0x05ac }
            com.google.android.gms.internal.ads.zzahe r6 = new com.google.android.gms.internal.ads.zzahe     // Catch:{ OutOfMemoryError -> 0x05b7, Exception -> 0x05b5, all -> 0x05ac }
            r6.<init>(r2, r5)     // Catch:{ OutOfMemoryError -> 0x05b7, Exception -> 0x05b5, all -> 0x05ac }
            r2 = r6
        L_0x05a4:
            r15 = r24
        L_0x05a6:
            r8.zzK(r15)
            r14 = r2
            r2 = 0
            goto L_0x05c0
        L_0x05ac:
            r0 = move-exception
            r15 = r24
            goto L_0x033a
        L_0x05b1:
            r8.zzK(r15)
            throw r1
        L_0x05b5:
            r0 = move-exception
            goto L_0x05b8
        L_0x05b7:
            r0 = move-exception
        L_0x05b8:
            r15 = r24
            goto L_0x017b
        L_0x05bc:
            r8.zzK(r15)
            r14 = 0
        L_0x05c0:
            if (r14 != 0) goto L_0x05e3
            r5 = r23
            java.lang.String r1 = zzi(r1, r5, r3, r4, r10)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Failed to decode frame: id="
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = ", frameSize="
            r3.append(r1)
            r3.append(r12)
            java.lang.String r1 = r3.toString()
            r3 = r22
            com.google.android.gms.internal.ads.zzfk.zzg(r3, r1, r2)
        L_0x05e3:
            return r14
        L_0x05e4:
            r8 = r2
            r3 = r11
            java.lang.String r1 = "Skipping unsupported compressed or encrypted frame"
            com.google.android.gms.internal.ads.zzfk.zzf(r3, r1)
            r8.zzK(r15)
            r1 = 0
            return r1
        L_0x05f0:
            r8 = r2
            r1 = r14
            r8.zzK(r15)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahq.zzf(int, com.google.android.gms.internal.ads.zzfu, boolean, int, com.google.android.gms.internal.ads.zzaho):com.google.android.gms.internal.ads.zzahr");
    }

    private static zzgbc zzg(byte[] bArr, int i, int i2) {
        if (i2 >= bArr.length) {
            return zzgbc.zzn("");
        }
        zzgaz zzgaz = new zzgaz();
        int zzc = zzc(bArr, i2, i);
        while (i2 < zzc) {
            zzgaz.zzf(new String(bArr, i2, zzc - i2, zzj(i)));
            i2 = zzb(i) + zzc;
            zzc = zzc(bArr, i2, i);
        }
        zzgbc zzi = zzgaz.zzi();
        return zzi.isEmpty() ? zzgbc.zzn("") : zzi;
    }

    private static String zzh(byte[] bArr, int i, int i2, Charset charset) {
        return (i2 <= i || i2 > bArr.length) ? "" : new String(bArr, i, i2 - i, charset);
    }

    private static String zzi(int i, int i2, int i3, int i4, int i5) {
        if (i == 2) {
            return String.format(Locale.US, "%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
        }
        return String.format(Locale.US, "%c%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        r4 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zzk(com.google.android.gms.internal.ads.zzfu r21, int r22, int r23, boolean r24) {
        /*
            r1 = r21
            r0 = r22
            int r2 = r21.zzd()
        L_0x0008:
            int r3 = r21.zzb()     // Catch:{ all -> 0x00a8 }
            r4 = 1
            r5 = r23
            if (r3 < r5) goto L_0x00a4
            r3 = 3
            r6 = 0
            if (r0 < r3) goto L_0x0022
            int r7 = r21.zzg()     // Catch:{ all -> 0x00a8 }
            long r8 = r21.zzu()     // Catch:{ all -> 0x00a8 }
            int r10 = r21.zzq()     // Catch:{ all -> 0x00a8 }
            goto L_0x002c
        L_0x0022:
            int r7 = r21.zzo()     // Catch:{ all -> 0x00a8 }
            int r8 = r21.zzo()     // Catch:{ all -> 0x00a8 }
            long r8 = (long) r8     // Catch:{ all -> 0x00a8 }
            r10 = r6
        L_0x002c:
            r11 = 0
            if (r7 != 0) goto L_0x0038
            int r7 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r7 != 0) goto L_0x0038
            if (r10 != 0) goto L_0x0038
            goto L_0x00a4
        L_0x0038:
            r7 = 4
            if (r0 != r7) goto L_0x0068
            if (r24 != 0) goto L_0x0068
            r13 = 8421504(0x808080, double:4.160776E-317)
            long r13 = r13 & r8
            int r11 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x0048
        L_0x0045:
            r4 = r6
            goto L_0x00a4
        L_0x0048:
            r11 = 255(0xff, double:1.26E-321)
            long r13 = r8 & r11
            r15 = 8
            long r15 = r8 >> r15
            r17 = 16
            long r17 = r8 >> r17
            r19 = 24
            long r8 = r8 >> r19
            long r15 = r15 & r11
            long r11 = r17 & r11
            r17 = 7
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 14
            long r11 = r11 << r15
            long r11 = r11 | r13
            r13 = 21
            long r8 = r8 << r13
            long r8 = r8 | r11
        L_0x0068:
            if (r0 != r7) goto L_0x0078
            r3 = r10 & 64
            if (r3 == 0) goto L_0x006f
            goto L_0x0070
        L_0x006f:
            r4 = r6
        L_0x0070:
            r3 = r10 & 1
            r20 = r4
            r4 = r3
            r3 = r20
            goto L_0x008a
        L_0x0078:
            if (r0 != r3) goto L_0x0088
            r3 = r10 & 32
            if (r3 == 0) goto L_0x0080
            r3 = r4
            goto L_0x0081
        L_0x0080:
            r3 = r6
        L_0x0081:
            r7 = r10 & 128(0x80, float:1.794E-43)
            if (r7 == 0) goto L_0x0086
            goto L_0x008a
        L_0x0086:
            r4 = r6
            goto L_0x008a
        L_0x0088:
            r3 = r6
            r4 = r3
        L_0x008a:
            if (r4 == 0) goto L_0x008e
            int r3 = r3 + 4
        L_0x008e:
            long r3 = (long) r3     // Catch:{ all -> 0x00a8 }
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x0094
            goto L_0x0045
        L_0x0094:
            int r3 = r21.zzb()     // Catch:{ all -> 0x00a8 }
            long r3 = (long) r3     // Catch:{ all -> 0x00a8 }
            int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x009e
            goto L_0x0045
        L_0x009e:
            int r3 = (int) r8     // Catch:{ all -> 0x00a8 }
            r1.zzL(r3)     // Catch:{ all -> 0x00a8 }
            goto L_0x0008
        L_0x00a4:
            r1.zzK(r2)
            return r4
        L_0x00a8:
            r0 = move-exception
            r1.zzK(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahq.zzk(com.google.android.gms.internal.ads.zzfu, int, int, boolean):boolean");
    }

    private static byte[] zzl(byte[] bArr, int i, int i2) {
        if (i2 <= i) {
            return zzgd.zzf;
        }
        return Arrays.copyOfRange(bArr, i, i2);
    }

    private static Charset zzj(int i) {
        if (i == 1) {
            return zzfxs.zzf;
        }
        if (i == 2) {
            return zzfxs.zzd;
        }
        if (i != 3) {
            return zzfxs.zzb;
        }
        return zzfxs.zzc;
    }
}
