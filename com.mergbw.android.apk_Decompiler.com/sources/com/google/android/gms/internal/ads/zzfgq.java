package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzby;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfgq extends zzbyj {
    private final zzfgg zza;
    private final zzffw zzb;
    /* access modifiers changed from: private */
    public final zzfhg zzc;
    /* access modifiers changed from: private */
    public zzdrh zzd;
    private boolean zze = false;

    public zzfgq(zzfgg zzfgg, zzffw zzffw, zzfhg zzfhg) {
        this.zza = zzfgg;
        this.zzb = zzffw;
        this.zzc = zzfhg;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean zzy() {
        /*
            r1 = this;
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzdrh r0 = r1.zzd     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x000e
            boolean r0 = r0.zze()     // Catch:{ all -> 0x0011 }
            if (r0 != 0) goto L_0x000e
            monitor-exit(r1)
            r0 = 1
            return r0
        L_0x000e:
            monitor-exit(r1)
            r0 = 0
            return r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0011 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfgq.zzy():boolean");
    }

    public final Bundle zzb() {
        Preconditions.checkMainThread("getAdMetadata can only be called from the UI thread.");
        zzdrh zzdrh = this.zzd;
        return zzdrh != null ? zzdrh.zza() : new Bundle();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.android.gms.ads.internal.client.zzdn zzc() throws android.os.RemoteException {
        /*
            r2 = this;
            monitor-enter(r2)
            com.google.android.gms.internal.ads.zzbeg r0 = com.google.android.gms.internal.ads.zzbep.zzgW     // Catch:{ all -> 0x0021 }
            com.google.android.gms.internal.ads.zzben r1 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x0021 }
            java.lang.Object r0 = r1.zza(r0)     // Catch:{ all -> 0x0021 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0021 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0021 }
            if (r0 != 0) goto L_0x0014
            goto L_0x001e
        L_0x0014:
            com.google.android.gms.internal.ads.zzdrh r0 = r2.zzd     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x001e
            com.google.android.gms.internal.ads.zzcze r0 = r0.zzl()     // Catch:{ all -> 0x0021 }
            monitor-exit(r2)
            return r0
        L_0x001e:
            monitor-exit(r2)
            r0 = 0
            return r0
        L_0x0021:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0021 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfgq.zzc():com.google.android.gms.ads.internal.client.zzdn");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String zzd() throws android.os.RemoteException {
        /*
            r2 = this;
            monitor-enter(r2)
            com.google.android.gms.internal.ads.zzdrh r0 = r2.zzd     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x0015
            com.google.android.gms.internal.ads.zzcze r1 = r0.zzl()     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x0015
            com.google.android.gms.internal.ads.zzcze r0 = r0.zzl()     // Catch:{ all -> 0x0018 }
            java.lang.String r0 = r0.zzg()     // Catch:{ all -> 0x0018 }
            monitor-exit(r2)
            return r0
        L_0x0015:
            monitor-exit(r2)
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0018 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfgq.zzd():java.lang.String");
    }

    public final void zze() throws RemoteException {
        zzf((IObjectWrapper) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.content.Context} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzf(com.google.android.gms.dynamic.IObjectWrapper r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = "destroy must be called on the main UI thread."
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.ads.zzffw r0 = r2.zzb     // Catch:{ all -> 0x0027 }
            r1 = 0
            r0.zzg(r1)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.ads.zzdrh r0 = r2.zzd     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x0025
            if (r3 != 0) goto L_0x0013
            goto L_0x001a
        L_0x0013:
            java.lang.Object r3 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r3)     // Catch:{ all -> 0x0027 }
            r1 = r3
            android.content.Context r1 = (android.content.Context) r1     // Catch:{ all -> 0x0027 }
        L_0x001a:
            com.google.android.gms.internal.ads.zzdrh r3 = r2.zzd     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.ads.zzdak r3 = r3.zzm()     // Catch:{ all -> 0x0027 }
            r3.zza(r1)     // Catch:{ all -> 0x0027 }
            monitor-exit(r2)
            return
        L_0x0025:
            monitor-exit(r2)
            return
        L_0x0027:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0027 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfgq.zzf(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (((java.lang.Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(com.google.android.gms.internal.ads.zzbep.zzfz)).booleanValue() == false) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0043, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        if (java.util.regex.Pattern.matches(r1, r0) != false) goto L_0x0042;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzg(com.google.android.gms.internal.ads.zzbyo r5) throws android.os.RemoteException {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = "loadAd must be called on the main UI thread."
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)     // Catch:{ all -> 0x0062 }
            java.lang.String r0 = r5.zzb     // Catch:{ all -> 0x0062 }
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzfx     // Catch:{ all -> 0x0062 }
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x0062 }
            java.lang.Object r1 = r2.zza(r1)     // Catch:{ all -> 0x0062 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0062 }
            if (r1 == 0) goto L_0x002a
            if (r0 != 0) goto L_0x0019
            goto L_0x002a
        L_0x0019:
            boolean r0 = java.util.regex.Pattern.matches(r1, r0)     // Catch:{ RuntimeException -> 0x0020 }
            if (r0 == 0) goto L_0x002a
            goto L_0x0042
        L_0x0020:
            r0 = move-exception
            java.lang.String r1 = "NonagonUtil.isPatternMatched"
            com.google.android.gms.internal.ads.zzcby r2 = com.google.android.gms.ads.internal.zzu.zzo()     // Catch:{ all -> 0x0062 }
            r2.zzw(r0, r1)     // Catch:{ all -> 0x0062 }
        L_0x002a:
            boolean r0 = r4.zzy()     // Catch:{ all -> 0x0062 }
            if (r0 == 0) goto L_0x0044
            com.google.android.gms.internal.ads.zzbeg r0 = com.google.android.gms.internal.ads.zzbep.zzfz     // Catch:{ all -> 0x0062 }
            com.google.android.gms.internal.ads.zzben r1 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x0062 }
            java.lang.Object r0 = r1.zza(r0)     // Catch:{ all -> 0x0062 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0062 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0062 }
            if (r0 != 0) goto L_0x0044
        L_0x0042:
            monitor-exit(r4)
            return
        L_0x0044:
            com.google.android.gms.internal.ads.zzffy r0 = new com.google.android.gms.internal.ads.zzffy     // Catch:{ all -> 0x0062 }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x0062 }
            r4.zzd = r1     // Catch:{ all -> 0x0062 }
            com.google.android.gms.internal.ads.zzfgg r1 = r4.zza     // Catch:{ all -> 0x0062 }
            r2 = 1
            r1.zzj(r2)     // Catch:{ all -> 0x0062 }
            com.google.android.gms.internal.ads.zzfgg r1 = r4.zza     // Catch:{ all -> 0x0062 }
            com.google.android.gms.ads.internal.client.zzl r2 = r5.zza     // Catch:{ all -> 0x0062 }
            java.lang.String r5 = r5.zzb     // Catch:{ all -> 0x0062 }
            com.google.android.gms.internal.ads.zzfgo r3 = new com.google.android.gms.internal.ads.zzfgo     // Catch:{ all -> 0x0062 }
            r3.<init>(r4)     // Catch:{ all -> 0x0062 }
            r1.zzb(r2, r5, r0, r3)     // Catch:{ all -> 0x0062 }
            monitor-exit(r4)
            return
        L_0x0062:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0062 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfgq.zzg(com.google.android.gms.internal.ads.zzbyo):void");
    }

    public final void zzh() {
        zzi((IObjectWrapper) null);
    }

    public final synchronized void zzi(IObjectWrapper iObjectWrapper) {
        Context context;
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (this.zzd != null) {
            if (iObjectWrapper == null) {
                context = null;
            } else {
                context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            }
            this.zzd.zzm().zzb(context);
        }
    }

    public final void zzj() {
        zzk((IObjectWrapper) null);
    }

    public final synchronized void zzk(IObjectWrapper iObjectWrapper) {
        Context context;
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        if (this.zzd != null) {
            if (iObjectWrapper == null) {
                context = null;
            } else {
                context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            }
            this.zzd.zzm().zzc(context);
        }
    }

    public final void zzl(zzby zzby) {
        Preconditions.checkMainThread("setAdMetadataListener can only be called from the UI thread.");
        if (zzby == null) {
            this.zzb.zzg((OnAdMetadataChangedListener) null);
        } else {
            this.zzb.zzg(new zzfgp(this, zzby));
        }
    }

    public final synchronized void zzm(String str) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setCustomData");
        this.zzc.zzb = str;
    }

    public final synchronized void zzn(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zze = z;
    }

    public final void zzo(zzbyn zzbyn) throws RemoteException {
        Preconditions.checkMainThread("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zzb.zzm(zzbyn);
    }

    public final synchronized void zzp(String str) throws RemoteException {
        Preconditions.checkMainThread("setUserId must be called on the main UI thread.");
        this.zzc.zza = str;
    }

    public final synchronized void zzq() throws RemoteException {
        zzr((IObjectWrapper) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: android.app.Activity} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzr(com.google.android.gms.dynamic.IObjectWrapper r3) throws android.os.RemoteException {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = "showAd must be called on the main UI thread."
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)     // Catch:{ all -> 0x0024 }
            com.google.android.gms.internal.ads.zzdrh r0 = r2.zzd     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0022
            r0 = 0
            if (r3 != 0) goto L_0x000e
            goto L_0x0019
        L_0x000e:
            java.lang.Object r3 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r3)     // Catch:{ all -> 0x0024 }
            boolean r1 = r3 instanceof android.app.Activity     // Catch:{ all -> 0x0024 }
            if (r1 == 0) goto L_0x0019
            r0 = r3
            android.app.Activity r0 = (android.app.Activity) r0     // Catch:{ all -> 0x0024 }
        L_0x0019:
            com.google.android.gms.internal.ads.zzdrh r3 = r2.zzd     // Catch:{ all -> 0x0024 }
            boolean r1 = r2.zze     // Catch:{ all -> 0x0024 }
            r3.zzh(r1, r0)     // Catch:{ all -> 0x0024 }
            monitor-exit(r2)
            return
        L_0x0022:
            monitor-exit(r2)
            return
        L_0x0024:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0024 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfgq.zzr(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    public final boolean zzs() throws RemoteException {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return zzy();
    }

    public final boolean zzt() {
        zzdrh zzdrh = this.zzd;
        return zzdrh != null && zzdrh.zzg();
    }

    public final void zzu(zzbyi zzbyi) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedAdSkuListener");
        this.zzb.zzn(zzbyi);
    }
}
