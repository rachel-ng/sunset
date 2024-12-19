package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzus implements zzvm, zzvl {
    public final zzvm zza;
    long zzb;
    private zzvl zzc;
    private zzur[] zzd = new zzur[0];
    private long zze = 0;

    public zzus(zzvm zzvm, boolean z, long j, long j2) {
        this.zza = zzvm;
        this.zzb = j2;
    }

    public final long zza(long j, zzmr zzmr) {
        if (j == 0) {
            return 0;
        }
        long max = Math.max(0, Math.min(zzmr.zzf, j));
        long j2 = zzmr.zzg;
        long j3 = this.zzb;
        long max2 = Math.max(0, Math.min(j2, j3 == Long.MIN_VALUE ? Long.MAX_VALUE : j3 - j));
        if (!(max == zzmr.zzf && max2 == zzmr.zzg)) {
            zzmr = new zzmr(max, max2);
        }
        return this.zza.zza(j, zzmr);
    }

    public final long zzb() {
        long zzb2 = this.zza.zzb();
        if (zzb2 != Long.MIN_VALUE) {
            long j = this.zzb;
            if (j == Long.MIN_VALUE || zzb2 < j) {
                return zzb2;
            }
        }
        return Long.MIN_VALUE;
    }

    public final long zzc() {
        long zzc2 = this.zza.zzc();
        if (zzc2 != Long.MIN_VALUE) {
            long j = this.zzb;
            if (j == Long.MIN_VALUE || zzc2 < j) {
                return zzc2;
            }
        }
        return Long.MIN_VALUE;
    }

    public final long zzd() {
        if (zzq()) {
            long j = this.zze;
            this.zze = C.TIME_UNSET;
            long zzd2 = zzd();
            return zzd2 != C.TIME_UNSET ? zzd2 : j;
        }
        long zzd3 = this.zza.zzd();
        if (zzd3 == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        boolean z = false;
        zzeq.zzf(zzd3 >= 0);
        long j2 = this.zzb;
        if (j2 == Long.MIN_VALUE || zzd3 <= j2) {
            z = true;
        }
        zzeq.zzf(z);
        return zzd3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        if (r0 > r3) goto L_0x0036;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zze(long r8) {
        /*
            r7 = this;
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r7.zze = r0
            com.google.android.gms.internal.ads.zzur[] r0 = r7.zzd
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L_0x000c:
            if (r3 >= r1) goto L_0x0018
            r4 = r0[r3]
            if (r4 == 0) goto L_0x0015
            r4.zzc()
        L_0x0015:
            int r3 = r3 + 1
            goto L_0x000c
        L_0x0018:
            com.google.android.gms.internal.ads.zzvm r0 = r7.zza
            long r0 = r0.zze(r8)
            int r8 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            r9 = 1
            if (r8 == 0) goto L_0x0035
            r3 = 0
            int r8 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r8 < 0) goto L_0x0036
            long r3 = r7.zzb
            r5 = -9223372036854775808
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 == 0) goto L_0x0035
            int r8 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r8 > 0) goto L_0x0036
        L_0x0035:
            r2 = r9
        L_0x0036:
            com.google.android.gms.internal.ads.zzeq.zzf(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzus.zze(long):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0059, code lost:
        if (r4 > r6) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zzf(com.google.android.gms.internal.ads.zzzg[] r15, boolean[] r16, com.google.android.gms.internal.ads.zzxf[] r17, boolean[] r18, long r19) {
        /*
            r14 = this;
            r0 = r14
            r1 = r17
            int r2 = r1.length
            com.google.android.gms.internal.ads.zzur[] r3 = new com.google.android.gms.internal.ads.zzur[r2]
            r0.zzd = r3
            com.google.android.gms.internal.ads.zzxf[] r2 = new com.google.android.gms.internal.ads.zzxf[r2]
            r3 = 0
            r4 = r3
        L_0x000c:
            int r5 = r1.length
            r11 = 0
            if (r4 >= r5) goto L_0x0021
            com.google.android.gms.internal.ads.zzur[] r5 = r0.zzd
            r6 = r1[r4]
            com.google.android.gms.internal.ads.zzur r6 = (com.google.android.gms.internal.ads.zzur) r6
            r5[r4] = r6
            if (r6 == 0) goto L_0x001c
            com.google.android.gms.internal.ads.zzxf r11 = r6.zza
        L_0x001c:
            r2[r4] = r11
            int r4 = r4 + 1
            goto L_0x000c
        L_0x0021:
            com.google.android.gms.internal.ads.zzvm r4 = r0.zza
            r5 = r15
            r6 = r16
            r7 = r2
            r8 = r18
            r9 = r19
            long r4 = r4.zzf(r5, r6, r7, r8, r9)
            boolean r6 = r14.zzq()
            r7 = 0
            if (r6 == 0) goto L_0x003d
            int r6 = (r19 > r7 ? 1 : (r19 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x003d
            r9 = r7
            goto L_0x003f
        L_0x003d:
            r9 = r19
        L_0x003f:
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0.zze = r12
            int r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            r9 = 1
            if (r6 == 0) goto L_0x005d
            int r6 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r6 < 0) goto L_0x005c
            long r6 = r0.zzb
            r12 = -9223372036854775808
            int r8 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r8 == 0) goto L_0x005d
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 > 0) goto L_0x005c
            goto L_0x005d
        L_0x005c:
            r9 = r3
        L_0x005d:
            com.google.android.gms.internal.ads.zzeq.zzf(r9)
        L_0x0060:
            int r6 = r1.length
            if (r3 >= r6) goto L_0x0086
            r6 = r2[r3]
            if (r6 != 0) goto L_0x006c
            com.google.android.gms.internal.ads.zzur[] r6 = r0.zzd
            r6[r3] = r11
            goto L_0x007d
        L_0x006c:
            com.google.android.gms.internal.ads.zzur[] r7 = r0.zzd
            r8 = r7[r3]
            if (r8 == 0) goto L_0x0076
            com.google.android.gms.internal.ads.zzxf r8 = r8.zza
            if (r8 == r6) goto L_0x007d
        L_0x0076:
            com.google.android.gms.internal.ads.zzur r8 = new com.google.android.gms.internal.ads.zzur
            r8.<init>(r14, r6)
            r7[r3] = r8
        L_0x007d:
            com.google.android.gms.internal.ads.zzur[] r6 = r0.zzd
            r6 = r6[r3]
            r1[r3] = r6
            int r3 = r3 + 1
            goto L_0x0060
        L_0x0086:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzus.zzf(com.google.android.gms.internal.ads.zzzg[], boolean[], com.google.android.gms.internal.ads.zzxf[], boolean[], long):long");
    }

    public final /* bridge */ /* synthetic */ void zzg(zzxh zzxh) {
        zzvm zzvm = (zzvm) zzxh;
        zzvl zzvl = this.zzc;
        zzvl.getClass();
        zzvl.zzg(this);
    }

    public final zzxr zzh() {
        return this.zza.zzh();
    }

    public final void zzj(long j, boolean z) {
        this.zza.zzj(j, false);
    }

    public final void zzk() throws IOException {
        this.zza.zzk();
    }

    public final void zzl(zzvl zzvl, long j) {
        this.zzc = zzvl;
        this.zza.zzl(this, j);
    }

    public final void zzm(long j) {
        this.zza.zzm(j);
    }

    public final void zzn(long j, long j2) {
        this.zzb = j2;
    }

    public final boolean zzo(zzlo zzlo) {
        return this.zza.zzo(zzlo);
    }

    public final boolean zzp() {
        return this.zza.zzp();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzq() {
        return this.zze != C.TIME_UNSET;
    }

    public final void zzi(zzvm zzvm) {
        zzvl zzvl = this.zzc;
        zzvl.getClass();
        zzvl.zzi(this);
    }
}
