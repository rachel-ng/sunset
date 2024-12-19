package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzbe;
import com.google.android.gms.ads.internal.client.zzbh;
import com.google.android.gms.ads.internal.client.zzbk;
import com.google.android.gms.ads.internal.client.zzbt;
import com.google.android.gms.ads.internal.client.zzby;
import com.google.android.gms.ads.internal.client.zzcb;
import com.google.android.gms.ads.internal.client.zzcf;
import com.google.android.gms.ads.internal.client.zzci;
import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.internal.client.zzdu;
import com.google.android.gms.ads.internal.client.zzfk;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfcz extends zzbt implements zzp, zzbca {
    protected zzcrz zza;
    private final zzcjd zzb;
    private final Context zzc;
    private AtomicBoolean zzd = new AtomicBoolean();
    private final String zze;
    private final zzfct zzf;
    /* access modifiers changed from: private */
    public final zzfcr zzg;
    private final VersionInfoParcel zzh;
    /* access modifiers changed from: private */
    public final zzdvc zzi;
    private long zzj = -1;
    private zzcrm zzk;

    public zzfcz(zzcjd zzcjd, Context context, String str, zzfct zzfct, zzfcr zzfcr, VersionInfoParcel versionInfoParcel, zzdvc zzdvc) {
        this.zzb = zzcjd;
        this.zzc = context;
        this.zze = str;
        this.zzf = zzfct;
        this.zzg = zzfcr;
        this.zzh = versionInfoParcel;
        this.zzi = zzdvc;
        zzfcr.zzm(this);
    }

    private final synchronized void zzq(int i) {
        if (this.zzd.compareAndSet(false, true)) {
            this.zzg.zzj();
            zzcrm zzcrm = this.zzk;
            if (zzcrm != null) {
                zzu.zzb().zze(zzcrm);
            }
            if (this.zza != null) {
                long j = -1;
                if (this.zzj != -1) {
                    j = zzu.zzB().elapsedRealtime() - this.zzj;
                }
                this.zza.zze(j, i);
            }
            zzx();
        }
    }

    public final synchronized void zzA() {
    }

    public final synchronized void zzB() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
    }

    public final void zzC(zzbe zzbe) {
    }

    public final void zzD(zzbh zzbh) {
    }

    public final void zzE(zzby zzby) {
    }

    public final synchronized void zzF(zzq zzq) {
        Preconditions.checkMainThread("setAdSize must be called on the main UI thread.");
    }

    public final void zzG(zzcb zzcb) {
    }

    public final void zzH(zzbcj zzbcj) {
        this.zzg.zzo(zzbcj);
    }

    public final void zzI(zzw zzw) {
        this.zzf.zzl(zzw);
    }

    public final void zzJ(zzci zzci) {
    }

    public final void zzK(zzdu zzdu) {
    }

    public final void zzL(boolean z) {
    }

    public final void zzM(zzbvp zzbvp) {
    }

    public final synchronized void zzN(boolean z) {
    }

    public final synchronized void zzO(zzbfk zzbfk) {
    }

    public final void zzP(zzdg zzdg) {
    }

    public final void zzQ(zzbvs zzbvs, String str) {
    }

    public final void zzR(String str) {
    }

    public final void zzS(zzbyn zzbyn) {
    }

    public final void zzT(String str) {
    }

    public final synchronized void zzU(zzfk zzfk) {
    }

    public final void zzW(IObjectWrapper iObjectWrapper) {
    }

    public final synchronized void zzX() {
    }

    public final synchronized boolean zzY() {
        return false;
    }

    public final synchronized boolean zzZ() {
        return this.zzf.zza();
    }

    public final void zza() {
        zzq(3);
    }

    public final boolean zzaa() {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006a A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c A[SYNTHETIC, Splitter:B:25:0x006c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean zzab(com.google.android.gms.ads.internal.client.zzl r6) throws android.os.RemoteException {
        /*
            r5 = this;
            monitor-enter(r5)
            com.google.android.gms.internal.ads.zzbfv r0 = com.google.android.gms.internal.ads.zzbgi.zzd     // Catch:{ all -> 0x0087 }
            java.lang.Object r0 = r0.zze()     // Catch:{ all -> 0x0087 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0087 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0087 }
            r1 = 0
            if (r0 == 0) goto L_0x0024
            com.google.android.gms.internal.ads.zzbeg r0 = com.google.android.gms.internal.ads.zzbep.zzlg     // Catch:{ all -> 0x0087 }
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x0087 }
            java.lang.Object r0 = r2.zza(r0)     // Catch:{ all -> 0x0087 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0087 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0087 }
            if (r0 == 0) goto L_0x0024
            r0 = 1
            goto L_0x0025
        L_0x0024:
            r0 = r1
        L_0x0025:
            com.google.android.gms.ads.internal.util.client.VersionInfoParcel r2 = r5.zzh     // Catch:{ all -> 0x0087 }
            int r2 = r2.clientJarVersion     // Catch:{ all -> 0x0087 }
            com.google.android.gms.internal.ads.zzbeg r3 = com.google.android.gms.internal.ads.zzbep.zzlh     // Catch:{ all -> 0x0087 }
            com.google.android.gms.internal.ads.zzben r4 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x0087 }
            java.lang.Object r3 = r4.zza(r3)     // Catch:{ all -> 0x0087 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ all -> 0x0087 }
            int r3 = r3.intValue()     // Catch:{ all -> 0x0087 }
            if (r2 < r3) goto L_0x003d
            if (r0 != 0) goto L_0x0042
        L_0x003d:
            java.lang.String r0 = "loadAd must be called on the main UI thread."
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)     // Catch:{ all -> 0x0087 }
        L_0x0042:
            com.google.android.gms.ads.internal.zzu.zzp()     // Catch:{ all -> 0x0087 }
            android.content.Context r0 = r5.zzc     // Catch:{ all -> 0x0087 }
            boolean r0 = com.google.android.gms.ads.internal.util.zzt.zzH(r0)     // Catch:{ all -> 0x0087 }
            if (r0 == 0) goto L_0x0064
            com.google.android.gms.ads.internal.client.zzc r0 = r6.zzs     // Catch:{ all -> 0x0087 }
            if (r0 == 0) goto L_0x0052
            goto L_0x0064
        L_0x0052:
            java.lang.String r6 = "Failed to load the ad because app ID is missing."
            com.google.android.gms.ads.internal.util.client.zzm.zzg(r6)     // Catch:{ all -> 0x0087 }
            com.google.android.gms.internal.ads.zzfcr r6 = r5.zzg     // Catch:{ all -> 0x0087 }
            r0 = 4
            r2 = 0
            com.google.android.gms.ads.internal.client.zze r0 = com.google.android.gms.internal.ads.zzfiq.zzd(r0, r2, r2)     // Catch:{ all -> 0x0087 }
            r6.zzdB(r0)     // Catch:{ all -> 0x0087 }
            monitor-exit(r5)
            return r1
        L_0x0064:
            boolean r0 = r5.zzZ()     // Catch:{ all -> 0x0087 }
            if (r0 == 0) goto L_0x006c
            monitor-exit(r5)
            return r1
        L_0x006c:
            java.util.concurrent.atomic.AtomicBoolean r0 = new java.util.concurrent.atomic.AtomicBoolean     // Catch:{ all -> 0x0087 }
            r0.<init>()     // Catch:{ all -> 0x0087 }
            r5.zzd = r0     // Catch:{ all -> 0x0087 }
            com.google.android.gms.internal.ads.zzfcx r0 = new com.google.android.gms.internal.ads.zzfcx     // Catch:{ all -> 0x0087 }
            r0.<init>(r5)     // Catch:{ all -> 0x0087 }
            com.google.android.gms.internal.ads.zzfct r1 = r5.zzf     // Catch:{ all -> 0x0087 }
            java.lang.String r2 = r5.zze     // Catch:{ all -> 0x0087 }
            com.google.android.gms.internal.ads.zzfcy r3 = new com.google.android.gms.internal.ads.zzfcy     // Catch:{ all -> 0x0087 }
            r3.<init>(r5)     // Catch:{ all -> 0x0087 }
            boolean r6 = r1.zzb(r6, r2, r0, r3)     // Catch:{ all -> 0x0087 }
            monitor-exit(r5)
            return r6
        L_0x0087:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0087 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfcz.zzab(com.google.android.gms.ads.internal.client.zzl):boolean");
    }

    public final synchronized void zzac(zzcf zzcf) {
    }

    public final Bundle zzd() {
        return new Bundle();
    }

    public final void zzdH() {
    }

    public final void zzdk() {
    }

    public final void zzdq() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzdr() {
        /*
            r4 = this;
            monitor-enter(r4)
            com.google.android.gms.internal.ads.zzcrz r0 = r4.zza     // Catch:{ all -> 0x0035 }
            if (r0 != 0) goto L_0x0006
            goto L_0x0033
        L_0x0006:
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ all -> 0x0035 }
            long r0 = r0.elapsedRealtime()     // Catch:{ all -> 0x0035 }
            r4.zzj = r0     // Catch:{ all -> 0x0035 }
            com.google.android.gms.internal.ads.zzcrz r0 = r4.zza     // Catch:{ all -> 0x0035 }
            int r0 = r0.zza()     // Catch:{ all -> 0x0035 }
            if (r0 <= 0) goto L_0x0033
            com.google.android.gms.internal.ads.zzcjd r1 = r4.zzb     // Catch:{ all -> 0x0035 }
            com.google.android.gms.internal.ads.zzcrm r2 = new com.google.android.gms.internal.ads.zzcrm     // Catch:{ all -> 0x0035 }
            java.util.concurrent.ScheduledExecutorService r1 = r1.zzC()     // Catch:{ all -> 0x0035 }
            com.google.android.gms.common.util.Clock r3 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ all -> 0x0035 }
            r2.<init>(r1, r3)     // Catch:{ all -> 0x0035 }
            r4.zzk = r2     // Catch:{ all -> 0x0035 }
            com.google.android.gms.internal.ads.zzfcw r1 = new com.google.android.gms.internal.ads.zzfcw     // Catch:{ all -> 0x0035 }
            r1.<init>(r4)     // Catch:{ all -> 0x0035 }
            r2.zzd(r0, r1)     // Catch:{ all -> 0x0035 }
            monitor-exit(r4)
            return
        L_0x0033:
            monitor-exit(r4)
            return
        L_0x0035:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0035 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfcz.zzdr():void");
    }

    public final synchronized void zzdt() {
        zzcrz zzcrz = this.zza;
        if (zzcrz != null) {
            zzcrz.zze(zzu.zzB().elapsedRealtime() - this.zzj, 1);
        }
    }

    public final synchronized zzq zzg() {
        return null;
    }

    public final zzbh zzi() {
        return null;
    }

    public final zzcb zzj() {
        return null;
    }

    public final synchronized zzdn zzk() {
        return null;
    }

    public final synchronized zzdq zzl() {
        return null;
    }

    public final IObjectWrapper zzn() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzo() {
        zzq(5);
    }

    public final void zzp() {
        this.zzb.zzB().execute(new zzfcv(this));
    }

    public final synchronized String zzr() {
        return this.zze;
    }

    public final synchronized String zzs() {
        return null;
    }

    public final synchronized String zzt() {
        return null;
    }

    public final synchronized void zzx() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        zzcrz zzcrz = this.zza;
        if (zzcrz != null) {
            zzcrz.zzb();
        }
    }

    public final void zzy(zzl zzl, zzbk zzbk) {
    }

    public final synchronized void zzz() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
    }

    public final void zzdu(int i) {
        if (i != 0) {
            int i2 = i - 1;
            if (i2 == 0) {
                zzq(2);
            } else if (i2 == 1) {
                zzq(4);
            } else if (i2 != 2) {
                zzq(6);
            } else {
                zzq(3);
            }
        } else {
            throw null;
        }
    }
}
