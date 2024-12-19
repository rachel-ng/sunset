package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzvj extends zzxt {
    private final boolean zzb;
    private final zzdb zzc;
    private final zzcz zzd;
    private zzvh zze;
    private zzvg zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;

    public zzvj(zzvq zzvq, boolean z) {
        super(zzvq);
        boolean z2;
        if (z) {
            zzvq.zzv();
            z2 = true;
        } else {
            z2 = false;
        }
        this.zzb = z2;
        this.zzc = new zzdb();
        this.zzd = new zzcz();
        zzvq.zzM();
        this.zze = zzvh.zzq(zzvq.zzJ());
    }

    private final Object zzK(Object obj) {
        return (this.zze.zzf == null || !obj.equals(zzvh.zzd)) ? obj : this.zze.zzf;
    }

    @RequiresNonNull({"unpreparedMaskingMediaPeriod"})
    private final void zzL(long j) {
        zzvg zzvg = this.zzf;
        int zza = this.zze.zza(zzvg.zza.zza);
        if (zza != -1) {
            zzvh zzvh = this.zze;
            zzcz zzcz = this.zzd;
            zzvh.zzd(zza, zzcz, false);
            long j2 = zzcz.zze;
            if (j2 != C.TIME_UNSET && j >= j2) {
                j = Math.max(0, j2 - 1);
            }
            zzvg.zzs(j);
        }
    }

    public final zzdc zzC() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final zzvo zzD(zzvo zzvo) {
        Object zzs = this.zze.zzf;
        Object obj = zzvo.zza;
        if (zzs != null && this.zze.zzf.equals(obj)) {
            obj = zzvh.zzd;
        }
        return zzvo.zza(obj);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzE(com.google.android.gms.internal.ads.zzdc r15) {
        /*
            r14 = this;
            boolean r0 = r14.zzh
            r1 = 0
            if (r0 == 0) goto L_0x001a
            com.google.android.gms.internal.ads.zzvh r0 = r14.zze
            com.google.android.gms.internal.ads.zzvh r15 = r0.zzp(r15)
            r14.zze = r15
            com.google.android.gms.internal.ads.zzvg r15 = r14.zzf
            if (r15 == 0) goto L_0x009b
            long r2 = r15.zzn()
            r14.zzL(r2)
            goto L_0x009b
        L_0x001a:
            boolean r0 = r15.zzo()
            if (r0 == 0) goto L_0x0036
            boolean r0 = r14.zzi
            if (r0 == 0) goto L_0x002b
            com.google.android.gms.internal.ads.zzvh r0 = r14.zze
            com.google.android.gms.internal.ads.zzvh r15 = r0.zzp(r15)
            goto L_0x0033
        L_0x002b:
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzdb.zza
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzvh.zzd
            com.google.android.gms.internal.ads.zzvh r15 = com.google.android.gms.internal.ads.zzvh.zzr(r15, r0, r2)
        L_0x0033:
            r14.zze = r15
            goto L_0x009b
        L_0x0036:
            com.google.android.gms.internal.ads.zzdb r0 = r14.zzc
            r2 = 0
            r3 = 0
            r15.zze(r2, r0, r3)
            com.google.android.gms.internal.ads.zzdb r0 = r14.zzc
            java.lang.Object r0 = r0.zzc
            com.google.android.gms.internal.ads.zzvg r5 = r14.zzf
            if (r5 == 0) goto L_0x0062
            long r6 = r5.zzq()
            com.google.android.gms.internal.ads.zzvh r8 = r14.zze
            com.google.android.gms.internal.ads.zzcz r9 = r14.zzd
            com.google.android.gms.internal.ads.zzvo r5 = r5.zza
            java.lang.Object r5 = r5.zza
            r8.zzn(r5, r9)
            com.google.android.gms.internal.ads.zzvh r5 = r14.zze
            com.google.android.gms.internal.ads.zzdb r8 = r14.zzc
            r5.zze(r2, r8, r3)
            int r2 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r2 == 0) goto L_0x0062
            r12 = r6
            goto L_0x0063
        L_0x0062:
            r12 = r3
        L_0x0063:
            com.google.android.gms.internal.ads.zzdb r9 = r14.zzc
            com.google.android.gms.internal.ads.zzcz r10 = r14.zzd
            r11 = 0
            r8 = r15
            android.util.Pair r2 = r8.zzl(r9, r10, r11, r12)
            java.lang.Object r3 = r2.first
            java.lang.Object r2 = r2.second
            java.lang.Long r2 = (java.lang.Long) r2
            long r4 = r2.longValue()
            boolean r2 = r14.zzi
            if (r2 == 0) goto L_0x0082
            com.google.android.gms.internal.ads.zzvh r0 = r14.zze
            com.google.android.gms.internal.ads.zzvh r15 = r0.zzp(r15)
            goto L_0x0086
        L_0x0082:
            com.google.android.gms.internal.ads.zzvh r15 = com.google.android.gms.internal.ads.zzvh.zzr(r15, r0, r3)
        L_0x0086:
            r14.zze = r15
            com.google.android.gms.internal.ads.zzvg r15 = r14.zzf
            if (r15 == 0) goto L_0x009b
            r14.zzL(r4)
            com.google.android.gms.internal.ads.zzvo r15 = r15.zza
            java.lang.Object r0 = r15.zza
            java.lang.Object r0 = r14.zzK(r0)
            com.google.android.gms.internal.ads.zzvo r1 = r15.zza(r0)
        L_0x009b:
            r15 = 1
            r14.zzi = r15
            r14.zzh = r15
            com.google.android.gms.internal.ads.zzvh r15 = r14.zze
            r14.zzo(r15)
            if (r1 == 0) goto L_0x00af
            com.google.android.gms.internal.ads.zzvg r15 = r14.zzf
            r15.getClass()
            r15.zzr(r1)
        L_0x00af:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzvj.zzE(com.google.android.gms.internal.ads.zzdc):void");
    }

    public final void zzF() {
        if (!this.zzb) {
            this.zzg = true;
            zzB((Object) null, this.zza);
        }
    }

    public final void zzG(zzvm zzvm) {
        ((zzvg) zzvm).zzt();
        if (zzvm == this.zzf) {
            this.zzf = null;
        }
    }

    /* renamed from: zzH */
    public final zzvg zzI(zzvo zzvo, zzzv zzzv, long j) {
        zzvg zzvg = new zzvg(zzvo, zzzv, j);
        zzvg.zzu(this.zza);
        if (this.zzh) {
            zzvg.zzr(zzvo.zza(zzK(zzvo.zza)));
        } else {
            this.zzf = zzvg;
            if (!this.zzg) {
                this.zzg = true;
                zzB((Object) null, this.zza);
            }
        }
        return zzvg;
    }

    public final void zzq() {
        this.zzh = false;
        this.zzg = false;
        super.zzq();
    }

    public final void zzt(zzbu zzbu) {
        if (this.zzi) {
            this.zze = this.zze.zzp(new zzxo(this.zze.zzc, zzbu));
        } else {
            this.zze = zzvh.zzq(zzbu);
        }
        this.zza.zzt(zzbu);
    }

    public final void zzz() {
    }
}
