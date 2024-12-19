package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaoa implements zzaoc {
    private final zzfu zza;
    private final AtomicInteger zzb = new AtomicInteger();
    private final String zzc;
    private final int zzd;
    private String zze;
    private zzafa zzf;
    private int zzg = 0;
    private int zzh;
    private int zzi;
    private long zzj;
    private zzan zzk;
    private int zzl;
    private int zzm;
    private int zzn = -1;
    private int zzo = -1;
    private long zzp = C.TIME_UNSET;

    public zzaoa(String str, int i, int i2) {
        this.zza = new zzfu(new byte[i2]);
        this.zzc = str;
        this.zzd = i;
    }

    @RequiresNonNull({"output"})
    private final void zzf(zzadq zzadq) {
        int i;
        zzal zzal;
        int i2 = zzadq.zzb;
        if (i2 != -2147483647 && (i = zzadq.zzc) != -1) {
            zzan zzan = this.zzk;
            if (zzan == null || i != zzan.zzA || i2 != zzan.zzB || !zzgd.zzG(zzadq.zza, zzan.zzn)) {
                zzan zzan2 = this.zzk;
                if (zzan2 == null) {
                    zzal = new zzal();
                } else {
                    zzal = zzan2.zzb();
                }
                zzal.zzK(this.zze);
                zzal.zzX(zzadq.zza);
                zzal.zzy(zzadq.zzc);
                zzal.zzY(zzadq.zzb);
                zzal.zzO(this.zzc);
                zzal.zzV(this.zzd);
                zzan zzad = zzal.zzad();
                this.zzk = zzad;
                this.zzf.zzl(zzad);
            }
        }
    }

    private final boolean zzg(zzfu zzfu, byte[] bArr, int i) {
        int min = Math.min(zzfu.zzb(), i - this.zzh);
        zzfu.zzG(bArr, this.zzh, min);
        int i2 = this.zzh + min;
        this.zzh = i2;
        return i2 == i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01db  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzfu r17) throws com.google.android.gms.internal.ads.zzch {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            com.google.android.gms.internal.ads.zzafa r2 = r0.zzf
            com.google.android.gms.internal.ads.zzeq.zzb(r2)
        L_0x0009:
            int r2 = r17.zzb()
            if (r2 <= 0) goto L_0x029d
            int r2 = r0.zzg
            r3 = 8
            r4 = 3
            r5 = 2
            r6 = 0
            r7 = 4
            r8 = 1
            if (r2 == 0) goto L_0x020b
            r9 = 5
            r10 = 7
            r11 = 6
            if (r2 == r8) goto L_0x0136
            if (r2 == r5) goto L_0x011a
            r12 = 0
            r14 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r2 == r4) goto L_0x00df
            if (r2 == r7) goto L_0x00b2
            if (r2 == r9) goto L_0x0071
            int r2 = r17.zzb()
            int r3 = r0.zzl
            int r4 = r0.zzh
            int r3 = r3 - r4
            int r2 = java.lang.Math.min(r2, r3)
            com.google.android.gms.internal.ads.zzafa r3 = r0.zzf
            r3.zzq(r1, r2)
            int r3 = r0.zzh
            int r3 = r3 + r2
            r0.zzh = r3
            int r2 = r0.zzl
            if (r3 != r2) goto L_0x0009
            long r2 = r0.zzp
            int r2 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r2 == 0) goto L_0x0051
            r2 = r8
            goto L_0x0052
        L_0x0051:
            r2 = r6
        L_0x0052:
            com.google.android.gms.internal.ads.zzeq.zzf(r2)
            com.google.android.gms.internal.ads.zzafa r9 = r0.zzf
            long r10 = r0.zzp
            int r2 = r0.zzm
            if (r2 != r7) goto L_0x005f
            r12 = r6
            goto L_0x0060
        L_0x005f:
            r12 = r8
        L_0x0060:
            int r13 = r0.zzl
            r14 = 0
            r15 = 0
            r9.zzs(r10, r12, r13, r14, r15)
            long r2 = r0.zzp
            long r4 = r0.zzj
            long r2 = r2 + r4
            r0.zzp = r2
            r0.zzg = r6
            goto L_0x0009
        L_0x0071:
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            byte[] r2 = r2.zzM()
            int r3 = r0.zzo
            boolean r2 = r0.zzg(r1, r2, r3)
            if (r2 == 0) goto L_0x0009
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            java.util.concurrent.atomic.AtomicInteger r3 = r0.zzb
            byte[] r2 = r2.zzM()
            com.google.android.gms.internal.ads.zzadq r2 = com.google.android.gms.internal.ads.zzadr.zze(r2, r3)
            int r3 = r0.zzm
            if (r3 != r4) goto L_0x0092
            r0.zzf(r2)
        L_0x0092:
            int r3 = r2.zzd
            r0.zzl = r3
            long r2 = r2.zze
            int r4 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r4 != 0) goto L_0x009d
            goto L_0x009e
        L_0x009d:
            r12 = r2
        L_0x009e:
            r0.zzj = r12
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            r2.zzK(r6)
            com.google.android.gms.internal.ads.zzafa r2 = r0.zzf
            com.google.android.gms.internal.ads.zzfu r3 = r0.zza
            int r4 = r0.zzo
            r2.zzq(r3, r4)
            r0.zzg = r11
            goto L_0x0009
        L_0x00b2:
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            byte[] r2 = r2.zzM()
            boolean r2 = r0.zzg(r1, r2, r11)
            if (r2 == 0) goto L_0x0009
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            byte[] r2 = r2.zzM()
            int r2 = com.google.android.gms.internal.ads.zzadr.zzb(r2)
            r0.zzo = r2
            int r3 = r0.zzh
            if (r3 <= r2) goto L_0x00db
            int r2 = r3 - r2
            int r3 = r3 - r2
            r0.zzh = r3
            int r3 = r17.zzd()
            int r3 = r3 - r2
            r1.zzK(r3)
        L_0x00db:
            r0.zzg = r9
            goto L_0x0009
        L_0x00df:
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            byte[] r2 = r2.zzM()
            int r3 = r0.zzn
            boolean r2 = r0.zzg(r1, r2, r3)
            if (r2 == 0) goto L_0x0009
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            byte[] r2 = r2.zzM()
            com.google.android.gms.internal.ads.zzadq r2 = com.google.android.gms.internal.ads.zzadr.zzd(r2)
            r0.zzf(r2)
            int r3 = r2.zzd
            r0.zzl = r3
            long r2 = r2.zze
            int r4 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r4 != 0) goto L_0x0105
            goto L_0x0106
        L_0x0105:
            r12 = r2
        L_0x0106:
            r0.zzj = r12
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            r2.zzK(r6)
            com.google.android.gms.internal.ads.zzafa r2 = r0.zzf
            com.google.android.gms.internal.ads.zzfu r3 = r0.zza
            int r4 = r0.zzn
            r2.zzq(r3, r4)
            r0.zzg = r11
            goto L_0x0009
        L_0x011a:
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            byte[] r2 = r2.zzM()
            boolean r2 = r0.zzg(r1, r2, r10)
            if (r2 == 0) goto L_0x0009
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            byte[] r2 = r2.zzM()
            int r2 = com.google.android.gms.internal.ads.zzadr.zza(r2)
            r0.zzn = r2
            r0.zzg = r4
            goto L_0x0009
        L_0x0136:
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            byte[] r2 = r2.zzM()
            r12 = 18
            boolean r2 = r0.zzg(r1, r2, r12)
            if (r2 == 0) goto L_0x0009
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            byte[] r2 = r2.zzM()
            com.google.android.gms.internal.ads.zzan r13 = r0.zzk
            if (r13 != 0) goto L_0x0160
            java.lang.String r13 = r0.zze
            java.lang.String r14 = r0.zzc
            int r15 = r0.zzd
            r12 = 0
            com.google.android.gms.internal.ads.zzan r12 = com.google.android.gms.internal.ads.zzadr.zzc(r2, r13, r14, r15, r12)
            r0.zzk = r12
            com.google.android.gms.internal.ads.zzafa r13 = r0.zzf
            r13.zzl(r12)
        L_0x0160:
            int r12 = com.google.android.gms.internal.ads.zzadr.zza
            byte r12 = r2[r6]
            r13 = 31
            r14 = -1
            r15 = -2
            if (r12 == r15) goto L_0x01a3
            if (r12 == r14) goto L_0x018d
            if (r12 == r13) goto L_0x017b
            byte r3 = r2[r9]
            r3 = r3 & r4
            int r3 = r3 << 12
            byte r4 = r2[r11]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r7
            byte r6 = r2[r10]
            goto L_0x01af
        L_0x017b:
            byte r6 = r2[r11]
            r4 = r4 & r6
            int r4 = r4 << 12
            byte r6 = r2[r10]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << r7
            byte r3 = r2[r3]
            r3 = r3 & 60
            int r3 = r3 >> r5
            r4 = r4 | r6
            r3 = r3 | r4
            goto L_0x01a0
        L_0x018d:
            byte r3 = r2[r10]
            r3 = r3 & r4
            int r3 = r3 << 12
            byte r4 = r2[r11]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r7
            r6 = 9
            byte r6 = r2[r6]
            r6 = r6 & 60
            int r6 = r6 >> r5
            r3 = r3 | r4
            r3 = r3 | r6
        L_0x01a0:
            int r3 = r3 + r8
            r4 = r8
            goto L_0x01b6
        L_0x01a3:
            byte r3 = r2[r7]
            r3 = r3 & r4
            int r3 = r3 << 12
            byte r4 = r2[r10]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r7
            byte r6 = r2[r11]
        L_0x01af:
            r6 = r6 & 240(0xf0, float:3.36E-43)
            int r6 = r6 >> r7
            r3 = r3 | r4
            r3 = r3 | r6
            int r3 = r3 + r8
            r4 = 0
        L_0x01b6:
            if (r4 == 0) goto L_0x01bc
            int r3 = r3 * 16
            int r3 = r3 / 14
        L_0x01bc:
            r0.zzl = r3
            if (r12 == r15) goto L_0x01db
            if (r12 == r14) goto L_0x01d2
            if (r12 == r13) goto L_0x01cb
            byte r3 = r2[r7]
            r3 = r3 & r8
            int r3 = r3 << r11
            byte r2 = r2[r9]
            goto L_0x01e1
        L_0x01cb:
            byte r3 = r2[r9]
            r3 = r3 & r10
            int r3 = r3 << r7
            byte r2 = r2[r11]
            goto L_0x01d8
        L_0x01d2:
            byte r3 = r2[r7]
            r3 = r3 & r10
            int r3 = r3 << r7
            byte r2 = r2[r10]
        L_0x01d8:
            r2 = r2 & 60
            goto L_0x01e3
        L_0x01db:
            byte r3 = r2[r9]
            r3 = r3 & r8
            int r3 = r3 << r11
            byte r2 = r2[r7]
        L_0x01e1:
            r2 = r2 & 252(0xfc, float:3.53E-43)
        L_0x01e3:
            int r2 = r2 >> r5
            r2 = r2 | r3
            int r2 = r2 + r8
            com.google.android.gms.internal.ads.zzan r3 = r0.zzk
            int r3 = r3.zzB
            int r2 = r2 * 32
            long r4 = (long) r2
            long r2 = com.google.android.gms.internal.ads.zzgd.zzs(r4, r3)
            int r2 = com.google.android.gms.internal.ads.zzgea.zzb(r2)
            long r2 = (long) r2
            r0.zzj = r2
            com.google.android.gms.internal.ads.zzfu r2 = r0.zza
            r3 = 0
            r2.zzK(r3)
            com.google.android.gms.internal.ads.zzafa r2 = r0.zzf
            com.google.android.gms.internal.ads.zzfu r3 = r0.zza
            r4 = 18
            r2.zzq(r3, r4)
            r0.zzg = r11
            goto L_0x0009
        L_0x020b:
            int r2 = r17.zzb()
            if (r2 <= 0) goto L_0x0009
            int r2 = r0.zzi
            int r2 = r2 << r3
            r0.zzi = r2
            int r6 = r17.zzm()
            r2 = r2 | r6
            r0.zzi = r2
            int r6 = com.google.android.gms.internal.ads.zzadr.zza
            r6 = 2147385345(0x7ffe8001, float:NaN)
            if (r2 == r6) goto L_0x025d
            r6 = -25230976(0xfffffffffe7f0180, float:-8.474023E37)
            if (r2 == r6) goto L_0x025d
            r6 = 536864768(0x1fffe800, float:1.0838051E-19)
            if (r2 == r6) goto L_0x025d
            r6 = -14745368(0xffffffffff1f00e8, float:-2.1135196E38)
            if (r2 != r6) goto L_0x0234
            goto L_0x025d
        L_0x0234:
            r6 = 1683496997(0x64582025, float:1.5947252E22)
            if (r2 == r6) goto L_0x025b
            r6 = 622876772(0x25205864, float:1.3907736E-16)
            if (r2 != r6) goto L_0x023f
            goto L_0x025b
        L_0x023f:
            r6 = 1078008818(0x40411bf2, float:3.0173306)
            if (r2 == r6) goto L_0x0259
            r6 = -233094848(0xfffffffff21b4140, float:-3.0751398E30)
            if (r2 != r6) goto L_0x024a
            goto L_0x0259
        L_0x024a:
            r6 = 1908687592(0x71c442e8, float:1.9436783E30)
            if (r2 == r6) goto L_0x0257
            r6 = -398277519(0xffffffffe842c471, float:-3.6790512E24)
            if (r2 != r6) goto L_0x0255
            goto L_0x0257
        L_0x0255:
            r2 = 0
            goto L_0x025e
        L_0x0257:
            r2 = r7
            goto L_0x025e
        L_0x0259:
            r2 = r4
            goto L_0x025e
        L_0x025b:
            r2 = r5
            goto L_0x025e
        L_0x025d:
            r2 = r8
        L_0x025e:
            r0.zzm = r2
            if (r2 == 0) goto L_0x020b
            com.google.android.gms.internal.ads.zzfu r3 = r0.zza
            byte[] r3 = r3.zzM()
            int r6 = r0.zzi
            int r9 = r6 >> 24
            r9 = r9 & 255(0xff, float:3.57E-43)
            byte r9 = (byte) r9
            r10 = 0
            r3[r10] = r9
            int r9 = r6 >> 16
            r9 = r9 & 255(0xff, float:3.57E-43)
            byte r9 = (byte) r9
            r3[r8] = r9
            int r9 = r6 >> 8
            r9 = r9 & 255(0xff, float:3.57E-43)
            byte r9 = (byte) r9
            r3[r5] = r9
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = (byte) r6
            r3[r4] = r6
            r0.zzh = r7
            r6 = 0
            r0.zzi = r6
            if (r2 == r4) goto L_0x0299
            if (r2 != r7) goto L_0x028f
            goto L_0x0299
        L_0x028f:
            if (r2 != r8) goto L_0x0295
            r0.zzg = r8
            goto L_0x0009
        L_0x0295:
            r0.zzg = r5
            goto L_0x0009
        L_0x0299:
            r0.zzg = r7
            goto L_0x0009
        L_0x029d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaoa.zza(com.google.android.gms.internal.ads.zzfu):void");
    }

    public final void zzb(zzadx zzadx, zzapo zzapo) {
        zzapo.zzc();
        this.zze = zzapo.zzb();
        this.zzf = zzadx.zzw(zzapo.zza(), 1);
    }

    public final void zzc() {
    }

    public final void zzd(long j, int i) {
        this.zzp = j;
    }

    public final void zze() {
        this.zzg = 0;
        this.zzh = 0;
        this.zzi = 0;
        this.zzp = C.TIME_UNSET;
        this.zzb.set(0);
    }
}
