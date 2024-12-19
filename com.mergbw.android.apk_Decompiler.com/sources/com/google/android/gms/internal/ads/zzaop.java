package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaop implements zzaoc {
    private final String zza;
    private final int zzb;
    private final zzfu zzc;
    private final zzft zzd;
    private zzafa zze;
    private String zzf;
    private zzan zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private long zzl = C.TIME_UNSET;
    private boolean zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private boolean zzq;
    private long zzr;
    private int zzs;
    private long zzt;
    private int zzu;
    private String zzv;

    public zzaop(String str, int i) {
        this.zza = str;
        this.zzb = i;
        zzfu zzfu = new zzfu(1024);
        this.zzc = zzfu;
        byte[] zzM = zzfu.zzM();
        this.zzd = new zzft(zzM, zzM.length);
    }

    private final int zzf(zzft zzft) throws zzch {
        int zza2 = zzft.zza();
        zzacp zzb2 = zzacq.zzb(zzft, true);
        this.zzv = zzb2.zzc;
        this.zzs = zzb2.zza;
        this.zzu = zzb2.zzb;
        return zza2 - zzft.zza();
    }

    private static long zzg(zzft zzft) {
        return (long) zzft.zzd((zzft.zzd(2) + 1) * 8);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0159, code lost:
        if (r14.zzm == false) goto L_0x01c5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzfu r15) throws com.google.android.gms.internal.ads.zzch {
        /*
            r14 = this;
            com.google.android.gms.internal.ads.zzafa r0 = r14.zze
            com.google.android.gms.internal.ads.zzeq.zzb(r0)
        L_0x0005:
            int r0 = r15.zzb()
            if (r0 <= 0) goto L_0x0224
            int r0 = r14.zzh
            r1 = 86
            r2 = 1
            if (r0 == 0) goto L_0x021a
            r3 = 2
            r4 = 0
            if (r0 == r2) goto L_0x0204
            r1 = 3
            r5 = 8
            if (r0 == r3) goto L_0x01da
            int r0 = r15.zzb()
            int r3 = r14.zzj
            int r6 = r14.zzi
            int r3 = r3 - r6
            int r0 = java.lang.Math.min(r0, r3)
            com.google.android.gms.internal.ads.zzft r3 = r14.zzd
            byte[] r3 = r3.zza
            int r6 = r14.zzi
            r15.zzG(r3, r6, r0)
            int r3 = r14.zzi
            int r3 = r3 + r0
            r14.zzi = r3
            int r0 = r14.zzj
            if (r3 != r0) goto L_0x0005
            com.google.android.gms.internal.ads.zzft r0 = r14.zzd
            r0.zzk(r4)
            com.google.android.gms.internal.ads.zzft r0 = r14.zzd
            boolean r3 = r0.zzo()
            r6 = 0
            if (r3 != 0) goto L_0x0157
            r14.zzm = r2
            int r3 = r0.zzd(r2)
            if (r3 != r2) goto L_0x0056
            int r3 = r0.zzd(r2)
            r7 = r2
            goto L_0x0058
        L_0x0056:
            r7 = r3
            r3 = r4
        L_0x0058:
            r14.zzn = r3
            if (r3 != 0) goto L_0x0152
            if (r7 != r2) goto L_0x0062
            zzg(r0)
            r7 = r2
        L_0x0062:
            boolean r3 = r0.zzo()
            if (r3 == 0) goto L_0x014d
            r3 = 6
            int r8 = r0.zzd(r3)
            r14.zzo = r8
            r8 = 4
            int r9 = r0.zzd(r8)
            int r10 = r0.zzd(r1)
            if (r9 != 0) goto L_0x0148
            if (r10 != 0) goto L_0x0148
            if (r7 != 0) goto L_0x00dd
            int r9 = r0.zzc()
            int r10 = r14.zzf(r0)
            r0.zzk(r9)
            int r9 = r10 + 7
            int r9 = r9 / r5
            byte[] r9 = new byte[r9]
            r0.zzg(r9, r4, r10)
            com.google.android.gms.internal.ads.zzal r10 = new com.google.android.gms.internal.ads.zzal
            r10.<init>()
            java.lang.String r11 = r14.zzf
            r10.zzK(r11)
            java.lang.String r11 = "audio/mp4a-latm"
            r10.zzX(r11)
            java.lang.String r11 = r14.zzv
            r10.zzz(r11)
            int r11 = r14.zzu
            r10.zzy(r11)
            int r11 = r14.zzs
            r10.zzY(r11)
            java.util.List r9 = java.util.Collections.singletonList(r9)
            r10.zzL(r9)
            java.lang.String r9 = r14.zza
            r10.zzO(r9)
            int r9 = r14.zzb
            r10.zzV(r9)
            com.google.android.gms.internal.ads.zzan r9 = r10.zzad()
            com.google.android.gms.internal.ads.zzan r10 = r14.zzg
            boolean r10 = r9.equals(r10)
            if (r10 != 0) goto L_0x00ea
            r14.zzg = r9
            int r10 = r9.zzB
            long r10 = (long) r10
            r12 = 1024000000(0x3d090000, double:5.059232213E-315)
            long r12 = r12 / r10
            r14.zzt = r12
            com.google.android.gms.internal.ads.zzafa r10 = r14.zze
            r10.zzl(r9)
            goto L_0x00ea
        L_0x00dd:
            long r9 = zzg(r0)
            int r9 = (int) r9
            int r10 = r14.zzf(r0)
            int r9 = r9 - r10
            r0.zzm(r9)
        L_0x00ea:
            int r9 = r0.zzd(r1)
            r14.zzp = r9
            if (r9 == 0) goto L_0x0115
            if (r9 == r2) goto L_0x010f
            if (r9 == r1) goto L_0x010b
            if (r9 == r8) goto L_0x010b
            r1 = 5
            if (r9 == r1) goto L_0x010b
            if (r9 == r3) goto L_0x0107
            r1 = 7
            if (r9 != r1) goto L_0x0101
            goto L_0x0107
        L_0x0101:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            r15.<init>()
            throw r15
        L_0x0107:
            r0.zzm(r2)
            goto L_0x0118
        L_0x010b:
            r0.zzm(r3)
            goto L_0x0118
        L_0x010f:
            r1 = 9
            r0.zzm(r1)
            goto L_0x0118
        L_0x0115:
            r0.zzm(r5)
        L_0x0118:
            boolean r1 = r0.zzo()
            r14.zzq = r1
            r8 = 0
            r14.zzr = r8
            if (r1 == 0) goto L_0x013e
            if (r7 == r2) goto L_0x0138
        L_0x0126:
            boolean r1 = r0.zzo()
            long r7 = r14.zzr
            long r7 = r7 << r5
            int r3 = r0.zzd(r5)
            long r9 = (long) r3
            long r7 = r7 + r9
            r14.zzr = r7
            if (r1 != 0) goto L_0x0126
            goto L_0x013e
        L_0x0138:
            long r7 = zzg(r0)
            r14.zzr = r7
        L_0x013e:
            boolean r1 = r0.zzo()
            if (r1 == 0) goto L_0x015c
            r0.zzm(r5)
            goto L_0x015c
        L_0x0148:
            com.google.android.gms.internal.ads.zzch r15 = com.google.android.gms.internal.ads.zzch.zza(r6, r6)
            throw r15
        L_0x014d:
            com.google.android.gms.internal.ads.zzch r15 = com.google.android.gms.internal.ads.zzch.zza(r6, r6)
            throw r15
        L_0x0152:
            com.google.android.gms.internal.ads.zzch r15 = com.google.android.gms.internal.ads.zzch.zza(r6, r6)
            throw r15
        L_0x0157:
            boolean r1 = r14.zzm
            if (r1 != 0) goto L_0x015c
            goto L_0x01c5
        L_0x015c:
            int r1 = r14.zzn
            if (r1 != 0) goto L_0x01d5
            int r1 = r14.zzo
            if (r1 != 0) goto L_0x01d0
            int r1 = r14.zzp
            if (r1 != 0) goto L_0x01cb
            r1 = r4
        L_0x0169:
            int r3 = r0.zzd(r5)
            int r10 = r1 + r3
            r1 = 255(0xff, float:3.57E-43)
            if (r3 == r1) goto L_0x01c9
            int r1 = r0.zzc()
            r3 = r1 & 7
            if (r3 != 0) goto L_0x0183
            com.google.android.gms.internal.ads.zzfu r3 = r14.zzc
            int r1 = r1 >> 3
            r3.zzK(r1)
            goto L_0x0193
        L_0x0183:
            com.google.android.gms.internal.ads.zzfu r1 = r14.zzc
            int r3 = r10 * 8
            byte[] r1 = r1.zzM()
            r0.zzg(r1, r4, r3)
            com.google.android.gms.internal.ads.zzfu r1 = r14.zzc
            r1.zzK(r4)
        L_0x0193:
            com.google.android.gms.internal.ads.zzafa r1 = r14.zze
            com.google.android.gms.internal.ads.zzfu r3 = r14.zzc
            r1.zzq(r3, r10)
            long r5 = r14.zzl
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 == 0) goto L_0x01a6
            goto L_0x01a7
        L_0x01a6:
            r2 = r4
        L_0x01a7:
            com.google.android.gms.internal.ads.zzeq.zzf(r2)
            com.google.android.gms.internal.ads.zzafa r6 = r14.zze
            long r7 = r14.zzl
            r11 = 0
            r12 = 0
            r9 = 1
            r6.zzs(r7, r9, r10, r11, r12)
            long r1 = r14.zzl
            long r5 = r14.zzt
            long r1 = r1 + r5
            r14.zzl = r1
            boolean r1 = r14.zzq
            if (r1 == 0) goto L_0x01c5
            long r1 = r14.zzr
            int r1 = (int) r1
            r0.zzm(r1)
        L_0x01c5:
            r14.zzh = r4
            goto L_0x0005
        L_0x01c9:
            r1 = r10
            goto L_0x0169
        L_0x01cb:
            com.google.android.gms.internal.ads.zzch r15 = com.google.android.gms.internal.ads.zzch.zza(r6, r6)
            throw r15
        L_0x01d0:
            com.google.android.gms.internal.ads.zzch r15 = com.google.android.gms.internal.ads.zzch.zza(r6, r6)
            throw r15
        L_0x01d5:
            com.google.android.gms.internal.ads.zzch r15 = com.google.android.gms.internal.ads.zzch.zza(r6, r6)
            throw r15
        L_0x01da:
            int r0 = r14.zzk
            r0 = r0 & -225(0xffffffffffffff1f, float:NaN)
            int r0 = r0 << r5
            int r2 = r15.zzm()
            r0 = r0 | r2
            r14.zzj = r0
            com.google.android.gms.internal.ads.zzfu r2 = r14.zzc
            byte[] r3 = r2.zzM()
            int r3 = r3.length
            if (r0 <= r3) goto L_0x01fe
            r2.zzH(r0)
            com.google.android.gms.internal.ads.zzft r0 = r14.zzd
            com.google.android.gms.internal.ads.zzfu r2 = r14.zzc
            byte[] r2 = r2.zzM()
            int r3 = r2.length
            r0.zzj(r2, r3)
        L_0x01fe:
            r14.zzi = r4
            r14.zzh = r1
            goto L_0x0005
        L_0x0204:
            int r0 = r15.zzm()
            r2 = r0 & 224(0xe0, float:3.14E-43)
            r5 = 224(0xe0, float:3.14E-43)
            if (r2 != r5) goto L_0x0214
            r14.zzk = r0
            r14.zzh = r3
            goto L_0x0005
        L_0x0214:
            if (r0 == r1) goto L_0x0005
            r14.zzh = r4
            goto L_0x0005
        L_0x021a:
            int r0 = r15.zzm()
            if (r0 != r1) goto L_0x0005
            r14.zzh = r2
            goto L_0x0005
        L_0x0224:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaop.zza(com.google.android.gms.internal.ads.zzfu):void");
    }

    public final void zzb(zzadx zzadx, zzapo zzapo) {
        zzapo.zzc();
        this.zze = zzadx.zzw(zzapo.zza(), 1);
        this.zzf = zzapo.zzb();
    }

    public final void zzc() {
    }

    public final void zzd(long j, int i) {
        this.zzl = j;
    }

    public final void zze() {
        this.zzh = 0;
        this.zzl = C.TIME_UNSET;
        this.zzm = false;
    }
}
