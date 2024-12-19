package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzany implements zzaoc {
    private static final byte[] zza = {73, 68, 51};
    private final boolean zzb;
    private final zzft zzc = new zzft(new byte[7], 7);
    private final zzfu zzd = new zzfu(Arrays.copyOf(zza, 10));
    private final String zze;
    private final int zzf;
    private String zzg;
    private zzafa zzh;
    private zzafa zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private boolean zzm;
    private boolean zzn;
    private int zzo;
    private int zzp;
    private int zzq;
    private boolean zzr;
    private long zzs;
    private int zzt;
    private long zzu;
    private zzafa zzv;
    private long zzw;

    public zzany(boolean z, String str, int i) {
        zzh();
        this.zzo = -1;
        this.zzp = -1;
        this.zzs = C.TIME_UNSET;
        this.zzu = C.TIME_UNSET;
        this.zzb = z;
        this.zze = str;
        this.zzf = i;
    }

    public static boolean zzf(int i) {
        return (i & 65526) == 65520;
    }

    private final void zzg() {
        this.zzn = false;
        zzh();
    }

    private final void zzh() {
        this.zzj = 0;
        this.zzk = 0;
        this.zzl = 256;
    }

    private final void zzi() {
        this.zzj = 3;
        this.zzk = 0;
    }

    private final void zzj(zzafa zzafa, long j, int i, int i2) {
        this.zzj = 4;
        this.zzk = i;
        this.zzv = zzafa;
        this.zzw = j;
        this.zzt = i2;
    }

    private final boolean zzk(zzfu zzfu, byte[] bArr, int i) {
        int min = Math.min(zzfu.zzb(), i - this.zzk);
        zzfu.zzG(bArr, this.zzk, min);
        int i2 = this.zzk + min;
        this.zzk = i2;
        return i2 == i;
    }

    private static final boolean zzl(byte b2, byte b3) {
        return zzf((b3 & 255) | 65280);
    }

    private static final boolean zzm(zzfu zzfu, byte[] bArr, int i) {
        if (zzfu.zzb() < i) {
            return false;
        }
        zzfu.zzG(bArr, 0, i);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x028c  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x02c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzfu r18) throws com.google.android.gms.internal.ads.zzch {
        /*
            r17 = this;
            r6 = r17
            r7 = r18
            com.google.android.gms.internal.ads.zzafa r0 = r6.zzh
            r0.getClass()
            int r0 = com.google.android.gms.internal.ads.zzgd.zza
        L_0x000b:
            int r0 = r18.zzb()
            if (r0 <= 0) goto L_0x02de
            int r0 = r6.zzj
            r1 = 13
            r2 = 7
            r3 = 4
            r4 = 3
            r5 = -1
            r8 = 0
            r9 = 2
            r10 = 1
            if (r0 == 0) goto L_0x019f
            if (r0 == r10) goto L_0x0164
            r5 = 10
            if (r0 == r9) goto L_0x0136
            if (r0 == r4) goto L_0x0067
            int r0 = r18.zzb()
            int r1 = r6.zzt
            int r2 = r6.zzk
            int r1 = r1 - r2
            int r0 = java.lang.Math.min(r0, r1)
            com.google.android.gms.internal.ads.zzafa r1 = r6.zzv
            r1.zzq(r7, r0)
            int r1 = r6.zzk
            int r1 = r1 + r0
            r6.zzk = r1
            int r0 = r6.zzt
            if (r1 != r0) goto L_0x000b
            long r0 = r6.zzu
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x004d
            r8 = r10
        L_0x004d:
            com.google.android.gms.internal.ads.zzeq.zzf(r8)
            com.google.android.gms.internal.ads.zzafa r9 = r6.zzv
            long r10 = r6.zzu
            int r13 = r6.zzt
            r14 = 0
            r15 = 0
            r12 = 1
            r9.zzs(r10, r12, r13, r14, r15)
            long r0 = r6.zzu
            long r2 = r6.zzw
            long r0 = r0 + r2
            r6.zzu = r0
            r17.zzh()
            goto L_0x000b
        L_0x0067:
            boolean r0 = r6.zzm
            r11 = 5
            if (r10 == r0) goto L_0x006e
            r0 = r11
            goto L_0x006f
        L_0x006e:
            r0 = r2
        L_0x006f:
            com.google.android.gms.internal.ads.zzft r12 = r6.zzc
            byte[] r12 = r12.zza
            boolean r0 = r6.zzk(r7, r12, r0)
            if (r0 == 0) goto L_0x000b
            com.google.android.gms.internal.ads.zzft r0 = r6.zzc
            r0.zzk(r8)
            boolean r0 = r6.zzr
            if (r0 != 0) goto L_0x010f
            com.google.android.gms.internal.ads.zzft r0 = r6.zzc
            int r0 = r0.zzd(r9)
            int r0 = r0 + r10
            if (r0 == r9) goto L_0x00a3
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r12 = "Detected audio object type: "
            r5.<init>(r12)
            r5.append(r0)
            java.lang.String r0 = ", but assuming AAC LC."
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            java.lang.String r5 = "AdtsReader"
            com.google.android.gms.internal.ads.zzfk.zzf(r5, r0)
        L_0x00a3:
            com.google.android.gms.internal.ads.zzft r0 = r6.zzc
            r0.zzm(r11)
            com.google.android.gms.internal.ads.zzft r0 = r6.zzc
            int r0 = r0.zzd(r4)
            int r5 = r6.zzp
            int r11 = com.google.android.gms.internal.ads.zzacq.zza
            int r11 = r5 >> 1
            r11 = r11 & r2
            r11 = r11 | 16
            byte r11 = (byte) r11
            int r2 = r5 << 7
            int r0 = r0 << r4
            r2 = r2 & 128(0x80, float:1.794E-43)
            r0 = r0 & 120(0x78, float:1.68E-43)
            r0 = r0 | r2
            byte r0 = (byte) r0
            byte[] r2 = new byte[r9]
            r2[r8] = r11
            r2[r10] = r0
            com.google.android.gms.internal.ads.zzacp r0 = com.google.android.gms.internal.ads.zzacq.zza(r2)
            com.google.android.gms.internal.ads.zzal r4 = new com.google.android.gms.internal.ads.zzal
            r4.<init>()
            java.lang.String r5 = r6.zzg
            r4.zzK(r5)
            java.lang.String r5 = "audio/mp4a-latm"
            r4.zzX(r5)
            java.lang.String r5 = r0.zzc
            r4.zzz(r5)
            int r5 = r0.zzb
            r4.zzy(r5)
            int r0 = r0.zza
            r4.zzY(r0)
            java.util.List r0 = java.util.Collections.singletonList(r2)
            r4.zzL(r0)
            java.lang.String r0 = r6.zze
            r4.zzO(r0)
            int r0 = r6.zzf
            r4.zzV(r0)
            com.google.android.gms.internal.ads.zzan r0 = r4.zzad()
            int r2 = r0.zzB
            long r4 = (long) r2
            r8 = 1024000000(0x3d090000, double:5.059232213E-315)
            long r8 = r8 / r4
            r6.zzs = r8
            com.google.android.gms.internal.ads.zzafa r2 = r6.zzh
            r2.zzl(r0)
            r6.zzr = r10
            goto L_0x0114
        L_0x010f:
            com.google.android.gms.internal.ads.zzft r0 = r6.zzc
            r0.zzm(r5)
        L_0x0114:
            com.google.android.gms.internal.ads.zzft r0 = r6.zzc
            r0.zzm(r3)
            com.google.android.gms.internal.ads.zzft r0 = r6.zzc
            int r0 = r0.zzd(r1)
            int r1 = r0 + -7
            boolean r2 = r6.zzm
            if (r2 == 0) goto L_0x0129
            int r0 = r0 + -9
            r5 = r0
            goto L_0x012a
        L_0x0129:
            r5 = r1
        L_0x012a:
            com.google.android.gms.internal.ads.zzafa r1 = r6.zzh
            long r2 = r6.zzs
            r4 = 0
            r0 = r17
            r0.zzj(r1, r2, r4, r5)
            goto L_0x000b
        L_0x0136:
            com.google.android.gms.internal.ads.zzfu r0 = r6.zzd
            byte[] r0 = r0.zzM()
            boolean r0 = r6.zzk(r7, r0, r5)
            if (r0 == 0) goto L_0x000b
            com.google.android.gms.internal.ads.zzafa r0 = r6.zzi
            com.google.android.gms.internal.ads.zzfu r1 = r6.zzd
            r0.zzq(r1, r5)
            com.google.android.gms.internal.ads.zzfu r0 = r6.zzd
            r1 = 6
            r0.zzK(r1)
            com.google.android.gms.internal.ads.zzafa r1 = r6.zzi
            com.google.android.gms.internal.ads.zzfu r0 = r6.zzd
            int r0 = r0.zzl()
            r4 = 10
            int r5 = r0 + 10
            r2 = 0
            r0 = r17
            r0.zzj(r1, r2, r4, r5)
            goto L_0x000b
        L_0x0164:
            int r0 = r18.zzb()
            if (r0 == 0) goto L_0x000b
            com.google.android.gms.internal.ads.zzft r0 = r6.zzc
            byte[] r1 = r0.zza
            byte[] r2 = r18.zzM()
            int r4 = r18.zzd()
            byte r2 = r2[r4]
            r1[r8] = r2
            r0.zzk(r9)
            com.google.android.gms.internal.ads.zzft r0 = r6.zzc
            int r0 = r0.zzd(r3)
            int r1 = r6.zzp
            if (r1 == r5) goto L_0x018e
            if (r0 == r1) goto L_0x018e
            r17.zzg()
            goto L_0x000b
        L_0x018e:
            boolean r1 = r6.zzn
            if (r1 != 0) goto L_0x019a
            r6.zzn = r10
            int r1 = r6.zzq
            r6.zzo = r1
            r6.zzp = r0
        L_0x019a:
            r17.zzi()
            goto L_0x000b
        L_0x019f:
            byte[] r0 = r18.zzM()
            int r11 = r18.zzd()
            int r12 = r18.zze()
        L_0x01ab:
            if (r11 >= r12) goto L_0x02d9
            int r13 = r11 + 1
            byte r14 = r0[r11]
            r15 = r14 & 255(0xff, float:3.57E-43)
            int r8 = r6.zzl
            r4 = 512(0x200, float:7.175E-43)
            if (r8 != r4) goto L_0x0283
            byte r8 = (byte) r15
            boolean r8 = zzl(r5, r8)
            if (r8 == 0) goto L_0x0283
            boolean r8 = r6.zzn
            if (r8 != 0) goto L_0x0261
            int r8 = r11 + -1
            r7.zzK(r11)
            com.google.android.gms.internal.ads.zzft r4 = r6.zzc
            byte[] r4 = r4.zza
            boolean r4 = zzm(r7, r4, r10)
            if (r4 != 0) goto L_0x01d5
            goto L_0x0283
        L_0x01d5:
            com.google.android.gms.internal.ads.zzft r4 = r6.zzc
            r4.zzk(r3)
            com.google.android.gms.internal.ads.zzft r4 = r6.zzc
            int r4 = r4.zzd(r10)
            int r2 = r6.zzo
            if (r2 == r5) goto L_0x01ea
            if (r4 != r2) goto L_0x01e7
            goto L_0x01ea
        L_0x01e7:
            r9 = 7
            goto L_0x0284
        L_0x01ea:
            int r2 = r6.zzp
            if (r2 == r5) goto L_0x020d
            com.google.android.gms.internal.ads.zzft r2 = r6.zzc
            byte[] r2 = r2.zza
            boolean r2 = zzm(r7, r2, r10)
            if (r2 != 0) goto L_0x01f9
            goto L_0x0261
        L_0x01f9:
            com.google.android.gms.internal.ads.zzft r2 = r6.zzc
            r2.zzk(r9)
            com.google.android.gms.internal.ads.zzft r2 = r6.zzc
            int r2 = r2.zzd(r3)
            int r9 = r6.zzp
            if (r2 != r9) goto L_0x01e7
            int r2 = r11 + 1
            r7.zzK(r2)
        L_0x020d:
            com.google.android.gms.internal.ads.zzft r2 = r6.zzc
            byte[] r2 = r2.zza
            boolean r2 = zzm(r7, r2, r3)
            if (r2 == 0) goto L_0x0261
            com.google.android.gms.internal.ads.zzft r2 = r6.zzc
            r9 = 14
            r2.zzk(r9)
            com.google.android.gms.internal.ads.zzft r2 = r6.zzc
            int r2 = r2.zzd(r1)
            r9 = 7
            if (r2 < r9) goto L_0x0284
            byte[] r16 = r18.zzM()
            int r1 = r18.zze()
            int r8 = r8 + r2
            if (r8 >= r1) goto L_0x0261
            byte r2 = r16[r8]
            if (r2 != r5) goto L_0x0249
            int r8 = r8 + 1
            if (r8 == r1) goto L_0x0261
            byte r1 = r16[r8]
            boolean r2 = zzl(r5, r1)
            if (r2 == 0) goto L_0x0284
            r1 = r1 & 8
            r2 = 3
            int r1 = r1 >> r2
            if (r1 != r4) goto L_0x0284
            goto L_0x0261
        L_0x0249:
            r4 = 73
            if (r2 != r4) goto L_0x0284
            int r2 = r8 + 1
            if (r2 == r1) goto L_0x0261
            byte r2 = r16[r2]
            r4 = 68
            if (r2 != r4) goto L_0x0284
            int r8 = r8 + 2
            if (r8 == r1) goto L_0x0261
            byte r1 = r16[r8]
            r2 = 51
            if (r1 != r2) goto L_0x0284
        L_0x0261:
            r0 = r14 & 8
            r1 = 3
            int r0 = r0 >> r1
            r6.zzq = r0
            r0 = r14 & 1
            r0 = r0 ^ r10
            if (r10 == r0) goto L_0x026e
            r0 = 0
            goto L_0x026f
        L_0x026e:
            r0 = r10
        L_0x026f:
            r6.zzm = r0
            boolean r0 = r6.zzn
            if (r0 != 0) goto L_0x027b
            r6.zzj = r10
            r0 = 0
            r6.zzk = r0
            goto L_0x027e
        L_0x027b:
            r17.zzi()
        L_0x027e:
            r7.zzK(r13)
            goto L_0x000b
        L_0x0283:
            r9 = r2
        L_0x0284:
            int r1 = r6.zzl
            r2 = r1 | r15
            r4 = 329(0x149, float:4.61E-43)
            if (r2 == r4) goto L_0x02c9
            r4 = 511(0x1ff, float:7.16E-43)
            if (r2 == r4) goto L_0x02c3
            r4 = 836(0x344, float:1.171E-42)
            if (r2 == r4) goto L_0x02bd
            r4 = 1075(0x433, float:1.506E-42)
            if (r2 == r4) goto L_0x02aa
            r2 = 256(0x100, float:3.59E-43)
            if (r1 == r2) goto L_0x02a6
            r6.zzl = r2
            r2 = r9
            r1 = 13
            r4 = 3
            r8 = 0
            r9 = 2
            goto L_0x01ab
        L_0x02a6:
            r1 = 2
            r2 = 3
            r4 = 0
            goto L_0x02d0
        L_0x02aa:
            r1 = 2
            r6.zzj = r1
            r2 = 3
            r6.zzk = r2
            r4 = 0
            r6.zzt = r4
            com.google.android.gms.internal.ads.zzfu r0 = r6.zzd
            r0.zzK(r4)
            r7.zzK(r13)
            goto L_0x000b
        L_0x02bd:
            r1 = 2
            r2 = 3
            r4 = 0
            r8 = 1024(0x400, float:1.435E-42)
            goto L_0x02ce
        L_0x02c3:
            r1 = 2
            r2 = 3
            r4 = 0
            r8 = 512(0x200, float:7.175E-43)
            goto L_0x02ce
        L_0x02c9:
            r1 = 2
            r2 = 3
            r4 = 0
            r8 = 768(0x300, float:1.076E-42)
        L_0x02ce:
            r6.zzl = r8
        L_0x02d0:
            r8 = r4
            r11 = r13
            r4 = r2
            r2 = r9
            r9 = r1
            r1 = 13
            goto L_0x01ab
        L_0x02d9:
            r7.zzK(r11)
            goto L_0x000b
        L_0x02de:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzany.zza(com.google.android.gms.internal.ads.zzfu):void");
    }

    public final void zzb(zzadx zzadx, zzapo zzapo) {
        zzapo.zzc();
        this.zzg = zzapo.zzb();
        zzafa zzw2 = zzadx.zzw(zzapo.zza(), 1);
        this.zzh = zzw2;
        this.zzv = zzw2;
        if (this.zzb) {
            zzapo.zzc();
            zzafa zzw3 = zzadx.zzw(zzapo.zza(), 5);
            this.zzi = zzw3;
            zzal zzal = new zzal();
            zzal.zzK(zzapo.zzb());
            zzal.zzX(MimeTypes.APPLICATION_ID3);
            zzw3.zzl(zzal.zzad());
            return;
        }
        this.zzi = new zzadt();
    }

    public final void zzc() {
    }

    public final void zzd(long j, int i) {
        this.zzu = j;
    }

    public final void zze() {
        this.zzu = C.TIME_UNSET;
        zzg();
    }
}
