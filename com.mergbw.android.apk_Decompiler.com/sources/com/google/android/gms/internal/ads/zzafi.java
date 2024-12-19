package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzafi implements zzadu {
    public static final zzaea zza = new zzafh();
    private static final int[] zzb = {13, 14, 16, 18, 20, 21, 27, 32, 6, 7, 6, 6, 1, 1, 1, 1};
    private static final int[] zzc;
    private static final byte[] zzd = "#!AMR\n".getBytes(zzfxs.zzc);
    private static final byte[] zze = "#!AMR-WB\n".getBytes(zzfxs.zzc);
    private static final int zzf;
    private final byte[] zzg;
    private boolean zzh;
    private long zzi;
    private int zzj;
    private int zzk;
    private boolean zzl;
    private int zzm;
    private int zzn;
    private long zzo;
    private zzadx zzp;
    private zzafa zzq;
    private zzaet zzr;
    private boolean zzs;

    static {
        int[] iArr = {18, 24, 33, 37, 41, 47, 51, 59, 61, 6, 1, 1, 1, 1, 1, 1};
        zzc = iArr;
        int i = zzgd.zza;
        zzf = iArr[8];
    }

    public zzafi() {
        throw null;
    }

    public zzafi(int i) {
        this.zzg = new byte[1];
        this.zzm = -1;
    }

    private static boolean zzg(zzadv zzadv, byte[] bArr) throws IOException {
        zzadv.zzj();
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        ((zzadi) zzadv).zzm(bArr2, 0, length, false);
        return Arrays.equals(bArr2, bArr);
    }

    private final boolean zzh(zzadv zzadv) throws IOException {
        byte[] bArr = zzd;
        if (zzg(zzadv, bArr)) {
            this.zzh = false;
            ((zzadi) zzadv).zzo(bArr.length, false);
            return true;
        }
        byte[] bArr2 = zze;
        if (!zzg(zzadv, bArr2)) {
            return false;
        }
        this.zzh = true;
        ((zzadi) zzadv).zzo(bArr2.length, false);
        return true;
    }

    public final int zzb(zzadv zzadv, zzaeq zzaeq) throws IOException {
        String str;
        zzeq.zzb(this.zzq);
        int i = zzgd.zza;
        if (zzadv.zzf() != 0 || zzh(zzadv)) {
            if (!this.zzs) {
                this.zzs = true;
                boolean z = this.zzh;
                if (true != z) {
                    str = MimeTypes.AUDIO_AMR_NB;
                } else {
                    str = MimeTypes.AUDIO_AMR_WB;
                }
                int i2 = true != z ? 8000 : AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND;
                zzafa zzafa = this.zzq;
                zzal zzal = new zzal();
                zzal.zzX(str);
                zzal.zzP(zzf);
                zzal.zzy(1);
                zzal.zzY(i2);
                zzafa.zzl(zzal.zzad());
            }
            int zza2 = zza(zzadv);
            if (this.zzl) {
                return zza2;
            }
            zzaes zzaes = new zzaes(C.TIME_UNSET, 0);
            this.zzr = zzaes;
            this.zzp.zzO(zzaes);
            this.zzl = true;
            return zza2;
        }
        throw zzch.zza("Could not find AMR header.", (Throwable) null);
    }

    public final /* synthetic */ List zzc() {
        return zzgbc.zzm();
    }

    public final void zzd(zzadx zzadx) {
        this.zzp = zzadx;
        this.zzq = zzadx.zzw(0, 1);
        zzadx.zzD();
    }

    public final void zze(long j, long j2) {
        this.zzi = 0;
        this.zzj = 0;
        this.zzk = 0;
        this.zzo = 0;
    }

    public final boolean zzf(zzadv zzadv) throws IOException {
        return zzh(zzadv);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003e A[Catch:{ EOFException -> 0x00b0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0043 A[Catch:{ EOFException -> 0x00b0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0055  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"trackOutput"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(com.google.android.gms.internal.ads.zzadv r14) throws java.io.IOException {
        /*
            r13 = this;
            java.lang.String r0 = "Illegal AMR "
            java.lang.String r1 = "Invalid padding bits for frame header "
            int r2 = r13.zzk
            r3 = -1
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x000c
            goto L_0x005a
        L_0x000c:
            r14.zzj()     // Catch:{ EOFException -> 0x00b0 }
            byte[] r2 = r13.zzg     // Catch:{ EOFException -> 0x00b0 }
            r6 = r14
            com.google.android.gms.internal.ads.zzadi r6 = (com.google.android.gms.internal.ads.zzadi) r6     // Catch:{ EOFException -> 0x00b0 }
            r6.zzm(r2, r5, r4, r5)     // Catch:{ EOFException -> 0x00b0 }
            byte[] r2 = r13.zzg     // Catch:{ EOFException -> 0x00b0 }
            byte r2 = r2[r5]     // Catch:{ EOFException -> 0x00b0 }
            r6 = r2 & 131(0x83, float:1.84E-43)
            r7 = 0
            if (r6 > 0) goto L_0x009f
            int r1 = r2 >> 3
            boolean r2 = r13.zzh     // Catch:{ EOFException -> 0x00b0 }
            r1 = r1 & 15
            if (r2 == 0) goto L_0x0031
            r6 = 10
            if (r1 < r6) goto L_0x003c
            r6 = 13
            if (r1 <= r6) goto L_0x0031
            goto L_0x003c
        L_0x0031:
            if (r2 != 0) goto L_0x007f
            r6 = 12
            if (r1 < r6) goto L_0x003c
            r6 = 14
            if (r1 > r6) goto L_0x003c
            goto L_0x007f
        L_0x003c:
            if (r2 == 0) goto L_0x0043
            int[] r0 = zzc     // Catch:{ EOFException -> 0x00b0 }
            r0 = r0[r1]     // Catch:{ EOFException -> 0x00b0 }
            goto L_0x0047
        L_0x0043:
            int[] r0 = zzb     // Catch:{ EOFException -> 0x00b0 }
            r0 = r0[r1]     // Catch:{ EOFException -> 0x00b0 }
        L_0x0047:
            r2 = r0
            r13.zzj = r2     // Catch:{ EOFException -> 0x00b0 }
            r13.zzk = r2
            int r0 = r13.zzm
            if (r0 != r3) goto L_0x0053
            r13.zzm = r2
            r0 = r2
        L_0x0053:
            if (r0 != r2) goto L_0x005a
            int r0 = r13.zzn
            int r0 = r0 + r4
            r13.zzn = r0
        L_0x005a:
            com.google.android.gms.internal.ads.zzafa r0 = r13.zzq
            int r14 = com.google.android.gms.internal.ads.zzaey.zza(r0, r14, r2, r4)
            if (r14 != r3) goto L_0x0063
            return r3
        L_0x0063:
            int r0 = r13.zzk
            int r0 = r0 - r14
            r13.zzk = r0
            if (r0 <= 0) goto L_0x006b
            return r5
        L_0x006b:
            com.google.android.gms.internal.ads.zzafa r6 = r13.zzq
            long r7 = r13.zzi
            int r10 = r13.zzj
            r11 = 0
            r12 = 0
            r9 = 1
            r6.zzs(r7, r9, r10, r11, r12)
            long r0 = r13.zzi
            r2 = 20000(0x4e20, double:9.8813E-320)
            long r0 = r0 + r2
            r13.zzi = r0
            return r5
        L_0x007f:
            java.lang.String r14 = "WB"
            java.lang.String r5 = "NB"
            if (r4 == r2) goto L_0x0086
            r14 = r5
        L_0x0086:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x00b0 }
            r2.<init>(r0)     // Catch:{ EOFException -> 0x00b0 }
            r2.append(r14)     // Catch:{ EOFException -> 0x00b0 }
            java.lang.String r14 = " frame type "
            r2.append(r14)     // Catch:{ EOFException -> 0x00b0 }
            r2.append(r1)     // Catch:{ EOFException -> 0x00b0 }
            java.lang.String r14 = r2.toString()     // Catch:{ EOFException -> 0x00b0 }
            com.google.android.gms.internal.ads.zzch r14 = com.google.android.gms.internal.ads.zzch.zza(r14, r7)     // Catch:{ EOFException -> 0x00b0 }
            throw r14     // Catch:{ EOFException -> 0x00b0 }
        L_0x009f:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x00b0 }
            r14.<init>(r1)     // Catch:{ EOFException -> 0x00b0 }
            r14.append(r2)     // Catch:{ EOFException -> 0x00b0 }
            java.lang.String r14 = r14.toString()     // Catch:{ EOFException -> 0x00b0 }
            com.google.android.gms.internal.ads.zzch r14 = com.google.android.gms.internal.ads.zzch.zza(r14, r7)     // Catch:{ EOFException -> 0x00b0 }
            throw r14     // Catch:{ EOFException -> 0x00b0 }
        L_0x00b0:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafi.zza(com.google.android.gms.internal.ads.zzadv):int");
    }
}
