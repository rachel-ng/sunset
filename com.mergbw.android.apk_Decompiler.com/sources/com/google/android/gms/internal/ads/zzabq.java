package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.SystemClock;
import android.view.Surface;
import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzabq {
    private final zzabp zza;
    private final zzabu zzb;
    private boolean zzc;
    private int zzd = 0;
    private long zze = C.TIME_UNSET;
    private long zzf;
    private long zzg = C.TIME_UNSET;
    private long zzh = C.TIME_UNSET;
    private boolean zzi;
    private float zzj = 1.0f;
    private zzer zzk = zzer.zza;

    public zzabq(Context context, zzabp zzabp, long j) {
        this.zza = zzabp;
        this.zzb = new zzabu(context);
    }

    private final void zzq(int i) {
        this.zzd = Math.min(this.zzd, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0075, code lost:
        if (r15 > 100000) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0080, code lost:
        if (r3 >= r24) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0085, code lost:
        if (r0.zzc != false) goto L_0x0087;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(long r18, long r20, long r22, long r24, boolean r26, com.google.android.gms.internal.ads.zzabo r27) throws com.google.android.gms.internal.ads.zzjh {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r3 = r20
            r5 = r27
            com.google.android.gms.internal.ads.zzabo.zzg(r27)
            long r6 = r0.zze
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 != 0) goto L_0x0018
            r0.zze = r3
        L_0x0018:
            long r6 = r0.zzg
            int r6 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r6 == 0) goto L_0x0025
            com.google.android.gms.internal.ads.zzabu r6 = r0.zzb
            r6.zzd(r1)
            r0.zzg = r1
        L_0x0025:
            long r1 = r1 - r3
            float r6 = r0.zzj
            double r6 = (double) r6
            boolean r10 = r0.zzc
            double r1 = (double) r1
            double r1 = r1 / r6
            long r1 = (long) r1
            if (r10 == 0) goto L_0x003b
            long r6 = android.os.SystemClock.elapsedRealtime()
            long r6 = com.google.android.gms.internal.ads.zzgd.zzr(r6)
            long r6 = r6 - r22
            long r1 = r1 - r6
        L_0x003b:
            r5.zza = r1
            long r1 = r27.zza
            long r6 = r0.zzh
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            r10 = -30000(0xffffffffffff8ad0, double:NaN)
            r7 = 3
            r12 = 2
            r13 = 0
            r14 = 1
            if (r6 == 0) goto L_0x0053
            boolean r6 = r0.zzi
            if (r6 != 0) goto L_0x0053
            goto L_0x0088
        L_0x0053:
            int r6 = r0.zzd
            if (r6 == 0) goto L_0x0083
            if (r6 == r14) goto L_0x0087
            if (r6 == r12) goto L_0x007e
            if (r6 != r7) goto L_0x0078
            long r15 = android.os.SystemClock.elapsedRealtime()
            long r15 = com.google.android.gms.internal.ads.zzgd.zzr(r15)
            long r7 = r0.zzf
            long r15 = r15 - r7
            boolean r6 = r0.zzc
            if (r6 == 0) goto L_0x0088
            int r1 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r1 >= 0) goto L_0x0088
            r1 = 100000(0x186a0, double:4.94066E-319)
            int r1 = (r15 > r1 ? 1 : (r15 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x0088
            goto L_0x0087
        L_0x0078:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x007e:
            int r1 = (r3 > r24 ? 1 : (r3 == r24 ? 0 : -1))
            if (r1 < 0) goto L_0x0088
            goto L_0x0087
        L_0x0083:
            boolean r1 = r0.zzc
            if (r1 == 0) goto L_0x0088
        L_0x0087:
            return r13
        L_0x0088:
            boolean r1 = r0.zzc
            r2 = 5
            if (r1 == 0) goto L_0x00f8
            long r6 = r0.zze
            int r1 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r1 != 0) goto L_0x0094
            goto L_0x00f8
        L_0x0094:
            com.google.android.gms.internal.ads.zzabu r1 = r0.zzb
            long r6 = java.lang.System.nanoTime()
            long r8 = r27.zza
            r15 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 * r15
            long r8 = r8 + r6
            long r8 = r1.zza(r8)
            r5.zzb = r8
            long r8 = r27.zzb
            long r8 = r8 - r6
            long r8 = r8 / r15
            r5.zza = r8
            long r6 = r0.zzh
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 == 0) goto L_0x00c2
            boolean r1 = r0.zzi
            if (r1 != 0) goto L_0x00c2
            r13 = r14
        L_0x00c2:
            com.google.android.gms.internal.ads.zzabp r1 = r0.zza
            long r6 = r27.zza
            r8 = -500000(0xfffffffffff85ee0, double:NaN)
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 >= 0) goto L_0x00dc
            if (r26 != 0) goto L_0x00dc
            com.google.android.gms.internal.ads.zzabj r1 = (com.google.android.gms.internal.ads.zzabj) r1
            boolean r1 = r1.zzaP(r3, r13)
            if (r1 != 0) goto L_0x00da
            goto L_0x00dc
        L_0x00da:
            r1 = 4
            return r1
        L_0x00dc:
            long r3 = r27.zza
            int r1 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r1 >= 0) goto L_0x00eb
            if (r26 != 0) goto L_0x00eb
            if (r13 == 0) goto L_0x00ea
            r1 = 3
            return r1
        L_0x00ea:
            return r12
        L_0x00eb:
            long r3 = r27.zza
            r5 = 50000(0xc350, double:2.47033E-319)
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x00f7
            return r2
        L_0x00f7:
            return r14
        L_0x00f8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzabq.zza(long, long, long, long, boolean, com.google.android.gms.internal.ads.zzabo):int");
    }

    public final void zzb() {
        if (this.zzd == 0) {
            this.zzd = 1;
        }
    }

    public final void zzc(boolean z) {
        this.zzi = z;
        this.zzh = C.TIME_UNSET;
    }

    public final void zzd() {
        zzq(0);
    }

    public final void zze(boolean z) {
        this.zzd = z ? 1 : 0;
    }

    public final void zzf() {
        zzq(2);
    }

    public final void zzg() {
        this.zzc = true;
        this.zzf = zzgd.zzr(SystemClock.elapsedRealtime());
        this.zzb.zzg();
    }

    public final void zzh() {
        this.zzc = false;
        this.zzh = C.TIME_UNSET;
        this.zzb.zzh();
    }

    public final void zzi() {
        this.zzb.zzf();
        this.zzg = C.TIME_UNSET;
        this.zze = C.TIME_UNSET;
        zzq(1);
        this.zzh = C.TIME_UNSET;
    }

    public final void zzj(int i) {
        this.zzb.zzj(i);
    }

    public final void zzk(zzer zzer) {
        this.zzk = zzer;
    }

    public final void zzl(float f) {
        this.zzb.zzc(f);
    }

    public final void zzm(Surface surface) {
        this.zzb.zzi(surface);
        zzq(1);
    }

    public final void zzn(float f) {
        this.zzj = f;
        this.zzb.zze(f);
    }

    public final boolean zzo(boolean z) {
        boolean z2 = true;
        if (!z || this.zzd != 3) {
            if (this.zzh == C.TIME_UNSET) {
                return false;
            }
            if (SystemClock.elapsedRealtime() >= this.zzh) {
                z2 = false;
            }
            return z2;
        }
        this.zzh = C.TIME_UNSET;
        return z2;
    }

    public final boolean zzp() {
        int i = this.zzd;
        this.zzd = 3;
        this.zzf = zzgd.zzr(SystemClock.elapsedRealtime());
        return i != 3;
    }
}
