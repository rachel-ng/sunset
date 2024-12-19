package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zzdd;
import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.util.zzt;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfgk extends zzbyz {
    private final zzfgg zza;
    private final zzffw zzb;
    private final String zzc;
    /* access modifiers changed from: private */
    public final zzfhg zzd;
    private final Context zze;
    private final VersionInfoParcel zzf;
    private final zzaxd zzg;
    private final zzdvc zzh;
    /* access modifiers changed from: private */
    public zzdrh zzi;
    private boolean zzj = ((Boolean) zzba.zzc().zza(zzbep.zzaE)).booleanValue();

    public zzfgk(String str, zzfgg zzfgg, Context context, zzffw zzffw, zzfhg zzfhg, VersionInfoParcel versionInfoParcel, zzaxd zzaxd, zzdvc zzdvc) {
        this.zzc = str;
        this.zza = zzfgg;
        this.zzb = zzffw;
        this.zzd = zzfhg;
        this.zze = context;
        this.zzf = versionInfoParcel;
        this.zzg = zzaxd;
        this.zzh = zzdvc;
    }

    private final synchronized void zzu(zzl zzl, zzbzh zzbzh, int i) throws RemoteException {
        boolean z = false;
        if (((Boolean) zzbgi.zzl.zze()).booleanValue()) {
            if (((Boolean) zzba.zzc().zza(zzbep.zzlg)).booleanValue()) {
                z = true;
            }
        }
        if (this.zzf.clientJarVersion < ((Integer) zzba.zzc().zza(zzbep.zzlh)).intValue() || !z) {
            Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        }
        this.zzb.zzk(zzbzh);
        zzu.zzp();
        if (zzt.zzH(this.zze)) {
            if (zzl.zzs == null) {
                zzm.zzg("Failed to load the ad because app ID is missing.");
                this.zzb.zzdB(zzfiq.zzd(4, (String) null, (zze) null));
                return;
            }
        }
        if (this.zzi == null) {
            zzffy zzffy = new zzffy((String) null);
            this.zza.zzj(i);
            this.zza.zzb(zzl, this.zzc, zzffy, new zzfgj(this));
        }
    }

    public final Bundle zzb() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzdrh zzdrh = this.zzi;
        return zzdrh != null ? zzdrh.zza() : new Bundle();
    }

    public final zzdn zzc() {
        zzdrh zzdrh;
        if (((Boolean) zzba.zzc().zza(zzbep.zzgW)).booleanValue() && (zzdrh = this.zzi) != null) {
            return zzdrh.zzl();
        }
        return null;
    }

    public final zzbyx zzd() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzdrh zzdrh = this.zzi;
        if (zzdrh != null) {
            return zzdrh.zzc();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String zze() throws android.os.RemoteException {
        /*
            r2 = this;
            monitor-enter(r2)
            com.google.android.gms.internal.ads.zzdrh r0 = r2.zzi     // Catch:{ all -> 0x0018 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfgk.zze():java.lang.String");
    }

    public final synchronized void zzf(zzl zzl, zzbzh zzbzh) throws RemoteException {
        zzu(zzl, zzbzh, 2);
    }

    public final synchronized void zzg(zzl zzl, zzbzh zzbzh) throws RemoteException {
        zzu(zzl, zzbzh, 3);
    }

    public final synchronized void zzh(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzj = z;
    }

    public final void zzi(zzdd zzdd) {
        if (zzdd == null) {
            this.zzb.zzg((OnAdMetadataChangedListener) null);
        } else {
            this.zzb.zzg(new zzfgi(this, zzdd));
        }
    }

    public final void zzj(zzdg zzdg) {
        Preconditions.checkMainThread("setOnPaidEventListener must be called on the main UI thread.");
        try {
            if (!zzdg.zzf()) {
                this.zzh.zze();
            }
        } catch (RemoteException e) {
            zzm.zzf("Error in making CSI ping for reporting paid event callback", e);
        }
        this.zzb.zzi(zzdg);
    }

    public final void zzk(zzbzd zzbzd) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzb.zzj(zzbzd);
    }

    public final synchronized void zzl(zzbzo zzbzo) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzfhg zzfhg = this.zzd;
        zzfhg.zza = zzbzo.zza;
        zzfhg.zzb = zzbzo.zzb;
    }

    public final synchronized void zzm(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzn(iObjectWrapper, this.zzj);
    }

    public final synchronized void zzn(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzi == null) {
            zzm.zzj("Rewarded can not be shown before loaded");
            this.zzb.zzq(zzfiq.zzd(9, (String) null, (zze) null));
            return;
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzcH)).booleanValue()) {
            this.zzg.zzc().zzn(new Throwable().getStackTrace());
        }
        this.zzi.zzh(z, (Activity) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final boolean zzo() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzdrh zzdrh = this.zzi;
        return zzdrh != null && !zzdrh.zzf();
    }

    public final void zzp(zzbzi zzbzi) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzb.zzo(zzbzi);
    }
}
