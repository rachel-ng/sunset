package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzapa implements zzadu {
    public static final zzaea zza = new zzaoy();
    private final zzgb zzb = new zzgb(0);
    private final SparseArray zzc = new SparseArray();
    private final zzfu zzd = new zzfu(4096);
    private final zzaox zze = new zzaox();
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private long zzi;
    private zzaow zzj;
    private zzadx zzk;
    private boolean zzl;

    /* JADX WARNING: Removed duplicated region for block: B:59:0x014f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzb(com.google.android.gms.internal.ads.zzadv r17, com.google.android.gms.internal.ads.zzaeq r18) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            com.google.android.gms.internal.ads.zzadx r3 = r0.zzk
            com.google.android.gms.internal.ads.zzeq.zzb(r3)
            long r10 = r17.zzd()
            r12 = -1
            int r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r3 == 0) goto L_0x0023
            com.google.android.gms.internal.ads.zzaox r4 = r0.zze
            boolean r5 = r4.zze()
            if (r5 == 0) goto L_0x001e
            goto L_0x0023
        L_0x001e:
            int r1 = r4.zza(r1, r2)
            return r1
        L_0x0023:
            boolean r4 = r0.zzl
            r14 = 1
            if (r4 != 0) goto L_0x0064
            r0.zzl = r14
            com.google.android.gms.internal.ads.zzaox r4 = r0.zze
            long r5 = r4.zzb()
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 == 0) goto L_0x0054
            com.google.android.gms.internal.ads.zzaow r15 = new com.google.android.gms.internal.ads.zzaow
            com.google.android.gms.internal.ads.zzgb r5 = r4.zzd()
            long r6 = r4.zzb()
            r4 = r15
            r8 = r10
            r4.<init>(r5, r6, r8)
            r0.zzj = r15
            com.google.android.gms.internal.ads.zzadx r4 = r0.zzk
            com.google.android.gms.internal.ads.zzaet r5 = r15.zzb()
            r4.zzO(r5)
            goto L_0x0064
        L_0x0054:
            com.google.android.gms.internal.ads.zzadx r5 = r0.zzk
            com.google.android.gms.internal.ads.zzaes r6 = new com.google.android.gms.internal.ads.zzaes
            long r7 = r4.zzb()
            r14 = 0
            r6.<init>(r7, r14)
            r5.zzO(r6)
        L_0x0064:
            com.google.android.gms.internal.ads.zzaow r5 = r0.zzj
            if (r5 == 0) goto L_0x0074
            boolean r6 = r5.zze()
            if (r6 != 0) goto L_0x006f
            goto L_0x0074
        L_0x006f:
            int r1 = r5.zza(r1, r2)
            return r1
        L_0x0074:
            r17.zzj()
            if (r3 == 0) goto L_0x007f
            long r2 = r17.zze()
            long r10 = r10 - r2
            goto L_0x0080
        L_0x007f:
            r10 = r12
        L_0x0080:
            int r2 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            r3 = -1
            if (r2 == 0) goto L_0x008d
            r5 = 4
            int r2 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r2 < 0) goto L_0x008c
            goto L_0x008d
        L_0x008c:
            return r3
        L_0x008d:
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            byte[] r2 = r2.zzM()
            r5 = 4
            r6 = 0
            r4 = 1
            boolean r2 = r1.zzm(r2, r6, r5, r4)
            if (r2 != 0) goto L_0x009d
            return r3
        L_0x009d:
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            r2.zzK(r6)
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            int r2 = r2.zzg()
            r5 = 441(0x1b9, float:6.18E-43)
            if (r2 != r5) goto L_0x00ad
            return r3
        L_0x00ad:
            r3 = 442(0x1ba, float:6.2E-43)
            if (r2 != r3) goto L_0x00d3
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            byte[] r2 = r2.zzM()
            com.google.android.gms.internal.ads.zzadi r1 = (com.google.android.gms.internal.ads.zzadi) r1
            r3 = 10
            r1.zzm(r2, r6, r3, r6)
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            r3 = 9
            r2.zzK(r3)
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            int r2 = r2.zzm()
            r2 = r2 & 7
            int r2 = r2 + 14
            r1.zzo(r2, r6)
            return r6
        L_0x00d3:
            r3 = 443(0x1bb, float:6.21E-43)
            r5 = 2
            r7 = 6
            if (r2 != r3) goto L_0x00f4
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            byte[] r2 = r2.zzM()
            com.google.android.gms.internal.ads.zzadi r1 = (com.google.android.gms.internal.ads.zzadi) r1
            r1.zzm(r2, r6, r5, r6)
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            r2.zzK(r6)
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            int r2 = r2.zzq()
            int r2 = r2 + r7
            r1.zzo(r2, r6)
            return r6
        L_0x00f4:
            int r3 = r2 >> 8
            r4 = 1
            if (r3 == r4) goto L_0x00ff
            com.google.android.gms.internal.ads.zzadi r1 = (com.google.android.gms.internal.ads.zzadi) r1
            r1.zzo(r4, r6)
            return r6
        L_0x00ff:
            r3 = r2 & 255(0xff, float:3.57E-43)
            android.util.SparseArray r8 = r0.zzc
            java.lang.Object r8 = r8.get(r3)
            com.google.android.gms.internal.ads.zzaoz r8 = (com.google.android.gms.internal.ads.zzaoz) r8
            boolean r9 = r0.zzf
            if (r9 != 0) goto L_0x0189
            if (r8 != 0) goto L_0x0169
            r9 = 189(0xbd, float:2.65E-43)
            r10 = 0
            if (r3 != r9) goto L_0x0124
            com.google.android.gms.internal.ads.zzans r2 = new com.google.android.gms.internal.ads.zzans
            r2.<init>(r10, r6)
            r4 = 1
            r0.zzg = r4
            long r9 = r17.zzf()
            r0.zzi = r9
        L_0x0122:
            r10 = r2
            goto L_0x014d
        L_0x0124:
            r4 = 1
            r9 = r2 & 224(0xe0, float:3.14E-43)
            r11 = 192(0xc0, float:2.69E-43)
            if (r9 != r11) goto L_0x0139
            com.google.android.gms.internal.ads.zzaoq r2 = new com.google.android.gms.internal.ads.zzaoq
            r2.<init>(r10, r6)
            r0.zzg = r4
            long r9 = r17.zzf()
            r0.zzi = r9
            goto L_0x0122
        L_0x0139:
            r2 = r2 & 240(0xf0, float:3.36E-43)
            r9 = 224(0xe0, float:3.14E-43)
            if (r2 != r9) goto L_0x014d
            com.google.android.gms.internal.ads.zzaoe r2 = new com.google.android.gms.internal.ads.zzaoe
            r2.<init>(r10)
            r0.zzh = r4
            long r9 = r17.zzf()
            r0.zzi = r9
            goto L_0x0122
        L_0x014d:
            if (r10 == 0) goto L_0x0169
            com.google.android.gms.internal.ads.zzapo r2 = new com.google.android.gms.internal.ads.zzapo
            r8 = -2147483648(0xffffffff80000000, float:-0.0)
            r9 = 256(0x100, float:3.59E-43)
            r2.<init>(r8, r3, r9)
            com.google.android.gms.internal.ads.zzadx r8 = r0.zzk
            r10.zzb(r8, r2)
            com.google.android.gms.internal.ads.zzgb r2 = r0.zzb
            com.google.android.gms.internal.ads.zzaoz r8 = new com.google.android.gms.internal.ads.zzaoz
            r8.<init>(r10, r2)
            android.util.SparseArray r2 = r0.zzc
            r2.put(r3, r8)
        L_0x0169:
            boolean r2 = r0.zzg
            r9 = 1048576(0x100000, double:5.180654E-318)
            if (r2 == 0) goto L_0x0179
            boolean r2 = r0.zzh
            if (r2 == 0) goto L_0x0179
            long r2 = r0.zzi
            r9 = 8192(0x2000, double:4.0474E-320)
            long r9 = r9 + r2
        L_0x0179:
            long r2 = r17.zzf()
            int r2 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r2 <= 0) goto L_0x0189
            r2 = 1
            r0.zzf = r2
            com.google.android.gms.internal.ads.zzadx r2 = r0.zzk
            r2.zzD()
        L_0x0189:
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            byte[] r2 = r2.zzM()
            com.google.android.gms.internal.ads.zzadi r1 = (com.google.android.gms.internal.ads.zzadi) r1
            r1.zzm(r2, r6, r5, r6)
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            r2.zzK(r6)
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzd
            int r2 = r2.zzq()
            int r2 = r2 + r7
            if (r8 != 0) goto L_0x01a6
            r1.zzo(r2, r6)
            goto L_0x01c7
        L_0x01a6:
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzd
            r3.zzH(r2)
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzd
            byte[] r3 = r3.zzM()
            r1.zzn(r3, r6, r2, r6)
            com.google.android.gms.internal.ads.zzfu r1 = r0.zzd
            r1.zzK(r7)
            com.google.android.gms.internal.ads.zzfu r1 = r0.zzd
            r8.zza(r1)
            com.google.android.gms.internal.ads.zzfu r1 = r0.zzd
            int r2 = r1.zzc()
            r1.zzJ(r2)
        L_0x01c7:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapa.zzb(com.google.android.gms.internal.ads.zzadv, com.google.android.gms.internal.ads.zzaeq):int");
    }

    public final /* synthetic */ List zzc() {
        return zzgbc.zzm();
    }

    public final void zzd(zzadx zzadx) {
        this.zzk = zzadx;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001f, code lost:
        if (r0 != r7) goto L_0x0021;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034 A[LOOP:0: B:12:0x002c->B:14:0x0034, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zze(long r5, long r7) {
        /*
            r4 = this;
            com.google.android.gms.internal.ads.zzgb r5 = r4.zzb
            long r0 = r5.zzf()
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0021
            long r0 = r5.zzd()
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0024
            r2 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0024
            int r6 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r6 == 0) goto L_0x0024
        L_0x0021:
            r5.zzi(r7)
        L_0x0024:
            com.google.android.gms.internal.ads.zzaow r5 = r4.zzj
            r6 = 0
            if (r5 == 0) goto L_0x002c
            r5.zzd(r7)
        L_0x002c:
            android.util.SparseArray r5 = r4.zzc
            int r5 = r5.size()
            if (r6 >= r5) goto L_0x0042
            android.util.SparseArray r5 = r4.zzc
            java.lang.Object r5 = r5.valueAt(r6)
            com.google.android.gms.internal.ads.zzaoz r5 = (com.google.android.gms.internal.ads.zzaoz) r5
            r5.zzb()
            int r6 = r6 + 1
            goto L_0x002c
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapa.zze(long, long):void");
    }

    public final boolean zzf(zzadv zzadv) throws IOException {
        byte[] bArr = new byte[14];
        zzadi zzadi = (zzadi) zzadv;
        zzadi.zzm(bArr, 0, 14, false);
        if ((((bArr[0] & 255) << Ascii.CAN) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255)) != 442 || (bArr[4] & 196) != 68 || (bArr[6] & 4) != 4 || (bArr[8] & 4) != 4 || (bArr[9] & 1) != 1 || (bArr[12] & 3) != 3) {
            return false;
        }
        zzadi.zzl(bArr[13] & 7, false);
        zzadi.zzm(bArr, 0, 3, false);
        return ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8)) | (bArr[2] & 255)) == 1;
    }
}
