package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzbjt extends UnifiedNativeAd {
    private final zzbjs zza;
    private final List zzb = new ArrayList();
    private final zzbhw zzc;
    private final VideoController zzd = new VideoController();
    private final NativeAd.AdChoicesInfo zze;
    private final List zzf = new ArrayList();

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0050 A[Catch:{ RemoteException -> 0x005b }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00af A[Catch:{ RemoteException -> 0x00bc }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0027 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzbjt(com.google.android.gms.internal.ads.zzbjs r6) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            r5.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5.zzb = r1
            com.google.android.gms.ads.VideoController r1 = new com.google.android.gms.ads.VideoController
            r1.<init>()
            r5.zzd = r1
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5.zzf = r1
            r5.zza = r6
            r1 = 0
            java.util.List r6 = r6.zzu()     // Catch:{ RemoteException -> 0x005b }
            if (r6 == 0) goto L_0x005f
            java.util.Iterator r6 = r6.iterator()     // Catch:{ RemoteException -> 0x005b }
        L_0x0027:
            boolean r2 = r6.hasNext()     // Catch:{ RemoteException -> 0x005b }
            if (r2 == 0) goto L_0x005f
            java.lang.Object r2 = r6.next()     // Catch:{ RemoteException -> 0x005b }
            boolean r3 = r2 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x005b }
            if (r3 == 0) goto L_0x004d
            android.os.IBinder r2 = (android.os.IBinder) r2     // Catch:{ RemoteException -> 0x005b }
            if (r2 != 0) goto L_0x003a
            goto L_0x004d
        L_0x003a:
            java.lang.String r3 = "com.google.android.gms.ads.internal.formats.client.INativeAdImage"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)     // Catch:{ RemoteException -> 0x005b }
            boolean r4 = r3 instanceof com.google.android.gms.internal.ads.zzbhv     // Catch:{ RemoteException -> 0x005b }
            if (r4 == 0) goto L_0x0047
            com.google.android.gms.internal.ads.zzbhv r3 = (com.google.android.gms.internal.ads.zzbhv) r3     // Catch:{ RemoteException -> 0x005b }
            goto L_0x004e
        L_0x0047:
            com.google.android.gms.internal.ads.zzbht r3 = new com.google.android.gms.internal.ads.zzbht     // Catch:{ RemoteException -> 0x005b }
            r3.<init>(r2)     // Catch:{ RemoteException -> 0x005b }
            goto L_0x004e
        L_0x004d:
            r3 = r1
        L_0x004e:
            if (r3 == 0) goto L_0x0027
            java.util.List r2 = r5.zzb     // Catch:{ RemoteException -> 0x005b }
            com.google.android.gms.internal.ads.zzbhw r4 = new com.google.android.gms.internal.ads.zzbhw     // Catch:{ RemoteException -> 0x005b }
            r4.<init>(r3)     // Catch:{ RemoteException -> 0x005b }
            r2.add(r4)     // Catch:{ RemoteException -> 0x005b }
            goto L_0x0027
        L_0x005b:
            r6 = move-exception
            com.google.android.gms.ads.internal.util.client.zzm.zzh(r0, r6)
        L_0x005f:
            com.google.android.gms.internal.ads.zzbjs r6 = r5.zza     // Catch:{ RemoteException -> 0x008e }
            java.util.List r6 = r6.zzv()     // Catch:{ RemoteException -> 0x008e }
            if (r6 == 0) goto L_0x0092
            java.util.Iterator r6 = r6.iterator()     // Catch:{ RemoteException -> 0x008e }
        L_0x006b:
            boolean r2 = r6.hasNext()     // Catch:{ RemoteException -> 0x008e }
            if (r2 == 0) goto L_0x0092
            java.lang.Object r2 = r6.next()     // Catch:{ RemoteException -> 0x008e }
            boolean r3 = r2 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x008e }
            if (r3 == 0) goto L_0x0080
            android.os.IBinder r2 = (android.os.IBinder) r2     // Catch:{ RemoteException -> 0x008e }
            com.google.android.gms.ads.internal.client.zzcw r2 = com.google.android.gms.ads.internal.client.zzcv.zzb(r2)     // Catch:{ RemoteException -> 0x008e }
            goto L_0x0081
        L_0x0080:
            r2 = r1
        L_0x0081:
            if (r2 == 0) goto L_0x006b
            java.util.List r3 = r5.zzf     // Catch:{ RemoteException -> 0x008e }
            com.google.android.gms.ads.internal.client.zzcx r4 = new com.google.android.gms.ads.internal.client.zzcx     // Catch:{ RemoteException -> 0x008e }
            r4.<init>(r2)     // Catch:{ RemoteException -> 0x008e }
            r3.add(r4)     // Catch:{ RemoteException -> 0x008e }
            goto L_0x006b
        L_0x008e:
            r6 = move-exception
            com.google.android.gms.ads.internal.util.client.zzm.zzh(r0, r6)
        L_0x0092:
            com.google.android.gms.internal.ads.zzbjs r6 = r5.zza     // Catch:{ RemoteException -> 0x00a0 }
            com.google.android.gms.internal.ads.zzbhv r6 = r6.zzk()     // Catch:{ RemoteException -> 0x00a0 }
            if (r6 == 0) goto L_0x00a4
            com.google.android.gms.internal.ads.zzbhw r2 = new com.google.android.gms.internal.ads.zzbhw     // Catch:{ RemoteException -> 0x00a0 }
            r2.<init>(r6)     // Catch:{ RemoteException -> 0x00a0 }
            goto L_0x00a5
        L_0x00a0:
            r6 = move-exception
            com.google.android.gms.ads.internal.util.client.zzm.zzh(r0, r6)
        L_0x00a4:
            r2 = r1
        L_0x00a5:
            r5.zzc = r2
            com.google.android.gms.internal.ads.zzbjs r6 = r5.zza     // Catch:{ RemoteException -> 0x00bc }
            com.google.android.gms.internal.ads.zzbho r6 = r6.zzi()     // Catch:{ RemoteException -> 0x00bc }
            if (r6 == 0) goto L_0x00c0
            com.google.android.gms.internal.ads.zzbhp r6 = new com.google.android.gms.internal.ads.zzbhp     // Catch:{ RemoteException -> 0x00bc }
            com.google.android.gms.internal.ads.zzbjs r2 = r5.zza     // Catch:{ RemoteException -> 0x00bc }
            com.google.android.gms.internal.ads.zzbho r2 = r2.zzi()     // Catch:{ RemoteException -> 0x00bc }
            r6.<init>(r2)     // Catch:{ RemoteException -> 0x00bc }
            r1 = r6
            goto L_0x00c0
        L_0x00bc:
            r6 = move-exception
            com.google.android.gms.ads.internal.util.client.zzm.zzh(r0, r6)
        L_0x00c0:
            r5.zze = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbjt.<init>(com.google.android.gms.internal.ads.zzbjs):void");
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zza.zzz(bundle);
        } catch (RemoteException e) {
            zzm.zzh("", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zza.zzI(bundle);
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zza.zzB(bundle);
        } catch (RemoteException e) {
            zzm.zzh("", e);
        }
    }

    public final VideoController zza() {
        try {
            if (this.zza.zzh() != null) {
                this.zzd.zzb(this.zza.zzh());
            }
        } catch (RemoteException e) {
            zzm.zzh("Exception occurred while getting video controller", e);
        }
        return this.zzd;
    }

    public final NativeAd.Image zzb() {
        return this.zzc;
    }

    public final Double zzc() {
        try {
            double zze2 = this.zza.zze();
            if (zze2 == -1.0d) {
                return null;
            }
            return Double.valueOf(zze2);
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final Object zzd() {
        try {
            IObjectWrapper zzl = this.zza.zzl();
            if (zzl != null) {
                return ObjectWrapper.unwrap(zzl);
            }
            return null;
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final String zze() {
        try {
            return this.zza.zzn();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final String zzf() {
        try {
            return this.zza.zzo();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final String zzg() {
        try {
            return this.zza.zzp();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final String zzh() {
        try {
            return this.zza.zzq();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final String zzi() {
        try {
            return this.zza.zzs();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final String zzj() {
        try {
            return this.zza.zzt();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final List zzk() {
        return this.zzb;
    }
}
