package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzpc {
    final /* synthetic */ zzpd zza;
    /* access modifiers changed from: private */
    public final String zzb;
    /* access modifiers changed from: private */
    public int zzc;
    /* access modifiers changed from: private */
    public long zzd;
    /* access modifiers changed from: private */
    public zzvo zze;
    /* access modifiers changed from: private */
    public boolean zzf;
    /* access modifiers changed from: private */
    public boolean zzg;

    public zzpc(zzpd zzpd, String str, int i, zzvo zzvo) {
        this.zza = zzpd;
        this.zzb = str;
        this.zzc = i;
        this.zzd = zzvo == null ? -1 : zzvo.zzd;
        if (zzvo != null && zzvo.zzb()) {
            this.zze = zzvo;
        }
    }

    public final void zzg(int i, zzvo zzvo) {
        if (this.zzd == -1 && i == this.zzc && zzvo != null) {
            zzpd zzpd = this.zza;
            long j = zzvo.zzd;
            if (j >= zzpd.zzl()) {
                this.zzd = j;
            }
        }
    }

    public final boolean zzj(int i, zzvo zzvo) {
        if (zzvo == null) {
            return i == this.zzc;
        }
        zzvo zzvo2 = this.zze;
        return zzvo2 == null ? !zzvo.zzb() && zzvo.zzd == this.zzd : zzvo.zzd == zzvo2.zzd && zzvo.zzb == zzvo2.zzb && zzvo.zzc == zzvo2.zzc;
    }

    public final boolean zzk(zzmy zzmy) {
        zzvo zzvo = zzmy.zzd;
        if (zzvo == null) {
            return this.zzc != zzmy.zzc;
        }
        long j = this.zzd;
        if (j == -1) {
            return false;
        }
        if (zzvo.zzd > j) {
            return true;
        }
        if (this.zze == null) {
            return false;
        }
        zzdc zzdc = zzmy.zzb;
        int zza2 = zzdc.zza(zzvo.zza);
        int zza3 = zzdc.zza(this.zze.zza);
        zzvo zzvo2 = zzmy.zzd;
        if (zzvo2.zzd < this.zze.zzd || zza2 < zza3) {
            return false;
        }
        if (zza2 > zza3) {
            return true;
        }
        if (zzvo2.zzb()) {
            zzvo zzvo3 = zzmy.zzd;
            int i = zzvo3.zzb;
            int i2 = zzvo3.zzc;
            zzvo zzvo4 = this.zze;
            int i3 = zzvo4.zzb;
            if (i > i3) {
                return true;
            }
            if (i == i3) {
                return i2 > zzvo4.zzc;
            }
            return false;
        }
        int i4 = zzmy.zzd.zze;
        return i4 == -1 || i4 > this.zze.zzb;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000e, code lost:
        if (r0 < r8.zzc()) goto L_0x004a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzl(com.google.android.gms.internal.ads.zzdc r7, com.google.android.gms.internal.ads.zzdc r8) {
        /*
            r6 = this;
            int r0 = r6.zzc
            int r1 = r7.zzc()
            r2 = 0
            r3 = -1
            if (r0 < r1) goto L_0x0013
            int r7 = r8.zzc()
            if (r0 >= r7) goto L_0x0011
            goto L_0x004a
        L_0x0011:
            r0 = r3
            goto L_0x004a
        L_0x0013:
            com.google.android.gms.internal.ads.zzpd r1 = r6.zza
            com.google.android.gms.internal.ads.zzdb r1 = r1.zzc
            r4 = 0
            r7.zze(r0, r1, r4)
            com.google.android.gms.internal.ads.zzpd r0 = r6.zza
            com.google.android.gms.internal.ads.zzdb r0 = r0.zzc
            int r0 = r0.zzp
        L_0x0026:
            com.google.android.gms.internal.ads.zzpd r1 = r6.zza
            com.google.android.gms.internal.ads.zzdb r1 = r1.zzc
            int r1 = r1.zzq
            if (r0 > r1) goto L_0x0011
            java.lang.Object r1 = r7.zzf(r0)
            int r1 = r8.zza(r1)
            if (r1 == r3) goto L_0x0047
            com.google.android.gms.internal.ads.zzpd r7 = r6.zza
            com.google.android.gms.internal.ads.zzcz r7 = r7.zzd
            com.google.android.gms.internal.ads.zzcz r7 = r8.zzd(r1, r7, r2)
            int r0 = r7.zzd
            goto L_0x004a
        L_0x0047:
            int r0 = r0 + 1
            goto L_0x0026
        L_0x004a:
            r6.zzc = r0
            if (r0 != r3) goto L_0x004f
            return r2
        L_0x004f:
            com.google.android.gms.internal.ads.zzvo r7 = r6.zze
            r0 = 1
            if (r7 != 0) goto L_0x0055
            return r0
        L_0x0055:
            java.lang.Object r7 = r7.zza
            int r7 = r8.zza(r7)
            if (r7 == r3) goto L_0x005e
            return r0
        L_0x005e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpc.zzl(com.google.android.gms.internal.ads.zzdc, com.google.android.gms.internal.ads.zzdc):boolean");
    }
}
