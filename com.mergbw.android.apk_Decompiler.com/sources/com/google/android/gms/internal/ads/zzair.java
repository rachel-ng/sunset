package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayDeque;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzair implements zzait {
    private final byte[] zza = new byte[8];
    private final ArrayDeque zzb = new ArrayDeque();
    private final zzaja zzc = new zzaja();
    private zzais zzd;
    private int zze;
    private int zzf;
    private long zzg;

    private final long zzd(zzadv zzadv, int i) throws IOException {
        ((zzadi) zzadv).zzn(this.zza, 0, i, false);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j = (j << 8) | ((long) (this.zza[i2] & 255));
        }
        return j;
    }

    public final void zza(zzais zzais) {
        this.zzd = zzais;
    }

    public final void zzb() {
        this.zze = 0;
        this.zzb.clear();
        this.zzc.zze();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0095, code lost:
        if (r0 == 1) goto L_0x0097;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzc(com.google.android.gms.internal.ads.zzadv r14) throws java.io.IOException {
        /*
            r13 = this;
            com.google.android.gms.internal.ads.zzais r0 = r13.zzd
            com.google.android.gms.internal.ads.zzeq.zzb(r0)
        L_0x0005:
            java.util.ArrayDeque r0 = r13.zzb
            java.lang.Object r0 = r0.peek()
            com.google.android.gms.internal.ads.zzaiq r0 = (com.google.android.gms.internal.ads.zzaiq) r0
            r1 = 1
            if (r0 == 0) goto L_0x0033
            long r2 = r14.zzf()
            long r4 = r0.zzb
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x001d
            goto L_0x0033
        L_0x001d:
            com.google.android.gms.internal.ads.zzais r14 = r13.zzd
            java.util.ArrayDeque r0 = r13.zzb
            java.lang.Object r0 = r0.pop()
            com.google.android.gms.internal.ads.zzaiq r0 = (com.google.android.gms.internal.ads.zzaiq) r0
            int r0 = r0.zza
            com.google.android.gms.internal.ads.zzaiw r14 = (com.google.android.gms.internal.ads.zzaiw) r14
            com.google.android.gms.internal.ads.zzaiy r14 = r14.zza
            r14.zzi(r0)
            return r1
        L_0x0033:
            int r0 = r13.zze
            r2 = 4
            r3 = 0
            if (r0 != 0) goto L_0x0095
            com.google.android.gms.internal.ads.zzaja r0 = r13.zzc
            long r4 = r0.zzd(r14, r1, r3, r2)
            r6 = -2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x0088
            r14.zzj()
        L_0x0048:
            byte[] r0 = r13.zza
            r4 = r14
            com.google.android.gms.internal.ads.zzadi r4 = (com.google.android.gms.internal.ads.zzadi) r4
            r4.zzm(r0, r3, r2, r3)
            byte[] r0 = r13.zza
            byte r0 = r0[r3]
            int r0 = com.google.android.gms.internal.ads.zzaja.zzb(r0)
            r5 = -1
            if (r0 == r5) goto L_0x0084
            if (r0 > r2) goto L_0x0084
            byte[] r5 = r13.zza
            long r5 = com.google.android.gms.internal.ads.zzaja.zzc(r5, r0, r3)
            int r5 = (int) r5
            com.google.android.gms.internal.ads.zzais r6 = r13.zzd
            com.google.android.gms.internal.ads.zzaiw r6 = (com.google.android.gms.internal.ads.zzaiw) r6
            com.google.android.gms.internal.ads.zzaiy r6 = r6.zza
            r6 = 357149030(0x1549a966, float:4.072526E-26)
            if (r5 == r6) goto L_0x007f
            r6 = 524531317(0x1f43b675, float:4.144378E-20)
            if (r5 == r6) goto L_0x007f
            r6 = 475249515(0x1c53bb6b, float:7.0056276E-22)
            if (r5 == r6) goto L_0x007f
            r6 = 374648427(0x1654ae6b, float:1.718026E-25)
            if (r5 != r6) goto L_0x0084
            r5 = r6
        L_0x007f:
            r4.zzo(r0, r3)
            long r4 = (long) r5
            goto L_0x0088
        L_0x0084:
            r4.zzo(r1, r3)
            goto L_0x0048
        L_0x0088:
            r6 = -1
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x008f
            return r3
        L_0x008f:
            int r0 = (int) r4
            r13.zzf = r0
            r13.zze = r1
            goto L_0x0097
        L_0x0095:
            if (r0 != r1) goto L_0x00a4
        L_0x0097:
            com.google.android.gms.internal.ads.zzaja r0 = r13.zzc
            r4 = 8
            long r4 = r0.zzd(r14, r3, r1, r4)
            r13.zzg = r4
            r0 = 2
            r13.zze = r0
        L_0x00a4:
            com.google.android.gms.internal.ads.zzais r0 = r13.zzd
            int r4 = r13.zzf
            com.google.android.gms.internal.ads.zzaiw r0 = (com.google.android.gms.internal.ads.zzaiw) r0
            com.google.android.gms.internal.ads.zzaiy r5 = r0.zza
            r6 = 8
            r8 = 0
            switch(r4) {
                case 131: goto L_0x0165;
                case 134: goto L_0x0125;
                case 136: goto L_0x0165;
                case 155: goto L_0x0165;
                case 159: goto L_0x0165;
                case 160: goto L_0x0102;
                case 161: goto L_0x00f9;
                case 163: goto L_0x00f9;
                case 165: goto L_0x00f9;
                case 166: goto L_0x0102;
                case 174: goto L_0x0102;
                case 176: goto L_0x0165;
                case 179: goto L_0x0165;
                case 181: goto L_0x00bf;
                case 183: goto L_0x0102;
                case 186: goto L_0x0165;
                case 187: goto L_0x0102;
                case 215: goto L_0x0165;
                case 224: goto L_0x0102;
                case 225: goto L_0x0102;
                case 231: goto L_0x0165;
                case 238: goto L_0x0165;
                case 241: goto L_0x0165;
                case 251: goto L_0x0165;
                case 16868: goto L_0x0102;
                case 16871: goto L_0x0165;
                case 16877: goto L_0x00f9;
                case 16980: goto L_0x0165;
                case 16981: goto L_0x00f9;
                case 17026: goto L_0x0125;
                case 17029: goto L_0x0165;
                case 17143: goto L_0x0165;
                case 17545: goto L_0x00bf;
                case 18401: goto L_0x0165;
                case 18402: goto L_0x00f9;
                case 18407: goto L_0x0102;
                case 18408: goto L_0x0165;
                case 19899: goto L_0x0102;
                case 20529: goto L_0x0165;
                case 20530: goto L_0x0165;
                case 20532: goto L_0x0102;
                case 20533: goto L_0x0102;
                case 21358: goto L_0x0125;
                case 21419: goto L_0x00f9;
                case 21420: goto L_0x0165;
                case 21432: goto L_0x0165;
                case 21680: goto L_0x0165;
                case 21682: goto L_0x0165;
                case 21690: goto L_0x0165;
                case 21930: goto L_0x0165;
                case 21936: goto L_0x0102;
                case 21938: goto L_0x0165;
                case 21945: goto L_0x0165;
                case 21946: goto L_0x0165;
                case 21947: goto L_0x0165;
                case 21948: goto L_0x0165;
                case 21949: goto L_0x0165;
                case 21968: goto L_0x0102;
                case 21969: goto L_0x00bf;
                case 21970: goto L_0x00bf;
                case 21971: goto L_0x00bf;
                case 21972: goto L_0x00bf;
                case 21973: goto L_0x00bf;
                case 21974: goto L_0x00bf;
                case 21975: goto L_0x00bf;
                case 21976: goto L_0x00bf;
                case 21977: goto L_0x00bf;
                case 21978: goto L_0x00bf;
                case 21998: goto L_0x0165;
                case 22186: goto L_0x0165;
                case 22203: goto L_0x0165;
                case 25152: goto L_0x0102;
                case 25188: goto L_0x0165;
                case 25506: goto L_0x00f9;
                case 28032: goto L_0x0102;
                case 30113: goto L_0x0102;
                case 30114: goto L_0x0165;
                case 30320: goto L_0x0102;
                case 30321: goto L_0x0165;
                case 30322: goto L_0x00f9;
                case 30323: goto L_0x00bf;
                case 30324: goto L_0x00bf;
                case 30325: goto L_0x00bf;
                case 2274716: goto L_0x0125;
                case 2352003: goto L_0x0165;
                case 2807729: goto L_0x0165;
                case 290298740: goto L_0x0102;
                case 357149030: goto L_0x0102;
                case 374648427: goto L_0x0102;
                case 408125543: goto L_0x0102;
                case 440786851: goto L_0x0102;
                case 475249515: goto L_0x0102;
                case 524531317: goto L_0x0102;
                default: goto L_0x00b2;
            }
        L_0x00b2:
            long r0 = r13.zzg
            int r0 = (int) r0
            r1 = r14
            com.google.android.gms.internal.ads.zzadi r1 = (com.google.android.gms.internal.ads.zzadi) r1
            r1.zzo(r0, r3)
            r13.zze = r3
            goto L_0x0005
        L_0x00bf:
            long r9 = r13.zzg
            r11 = 4
            int r5 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r5 == 0) goto L_0x00df
            int r5 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r5 != 0) goto L_0x00cc
            goto L_0x00df
        L_0x00cc:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r0 = "Invalid float size: "
            r14.<init>(r0)
            r14.append(r9)
            java.lang.String r14 = r14.toString()
            com.google.android.gms.internal.ads.zzch r14 = com.google.android.gms.internal.ads.zzch.zza(r14, r8)
            throw r14
        L_0x00df:
            int r5 = (int) r9
            long r6 = r13.zzd(r14, r5)
            if (r5 != r2) goto L_0x00ed
            int r14 = (int) r6
            float r14 = java.lang.Float.intBitsToFloat(r14)
            double r5 = (double) r14
            goto L_0x00f1
        L_0x00ed:
            double r5 = java.lang.Double.longBitsToDouble(r6)
        L_0x00f1:
            com.google.android.gms.internal.ads.zzaiy r14 = r0.zza
            r14.zzj(r4, r5)
            r13.zze = r3
            return r1
        L_0x00f9:
            long r6 = r13.zzg
            int r0 = (int) r6
            r5.zzh(r4, r0, r14)
            r13.zze = r3
            return r1
        L_0x0102:
            long r9 = r14.zzf()
            long r5 = r13.zzg
            long r5 = r5 + r9
            java.util.ArrayDeque r14 = r13.zzb
            com.google.android.gms.internal.ads.zzaiq r0 = new com.google.android.gms.internal.ads.zzaiq
            r0.<init>(r4, r5, r8)
            r14.push(r0)
            com.google.android.gms.internal.ads.zzais r14 = r13.zzd
            int r7 = r13.zzf
            long r4 = r13.zzg
            com.google.android.gms.internal.ads.zzaiw r14 = (com.google.android.gms.internal.ads.zzaiw) r14
            com.google.android.gms.internal.ads.zzaiy r6 = r14.zza
            r8 = r9
            r10 = r4
            r6.zzl(r7, r8, r10)
            r13.zze = r3
            return r1
        L_0x0125:
            long r5 = r13.zzg
            r9 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r2 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r2 > 0) goto L_0x0152
            int r2 = (int) r5
            if (r2 != 0) goto L_0x0134
            java.lang.String r14 = ""
            goto L_0x014a
        L_0x0134:
            byte[] r5 = new byte[r2]
            com.google.android.gms.internal.ads.zzadi r14 = (com.google.android.gms.internal.ads.zzadi) r14
            r14.zzn(r5, r3, r2, r3)
        L_0x013b:
            if (r2 <= 0) goto L_0x0145
            int r14 = r2 + -1
            byte r6 = r5[r14]
            if (r6 != 0) goto L_0x0145
            r2 = r14
            goto L_0x013b
        L_0x0145:
            java.lang.String r14 = new java.lang.String
            r14.<init>(r5, r3, r2)
        L_0x014a:
            com.google.android.gms.internal.ads.zzaiy r0 = r0.zza
            r0.zzm(r4, r14)
            r13.zze = r3
            return r1
        L_0x0152:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r0 = "String element size: "
            r14.<init>(r0)
            r14.append(r5)
            java.lang.String r14 = r14.toString()
            com.google.android.gms.internal.ads.zzch r14 = com.google.android.gms.internal.ads.zzch.zza(r14, r8)
            throw r14
        L_0x0165:
            long r9 = r13.zzg
            int r2 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r2 > 0) goto L_0x0178
            int r2 = (int) r9
            long r5 = r13.zzd(r14, r2)
            com.google.android.gms.internal.ads.zzaiy r14 = r0.zza
            r14.zzk(r4, r5)
            r13.zze = r3
            return r1
        L_0x0178:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r0 = "Invalid integer size: "
            r14.<init>(r0)
            r14.append(r9)
            java.lang.String r14 = r14.toString()
            com.google.android.gms.internal.ads.zzch r14 = com.google.android.gms.internal.ads.zzch.zza(r14, r8)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzair.zzc(com.google.android.gms.internal.ads.zzadv):boolean");
    }
}
