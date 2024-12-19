package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zzcs;
import com.google.android.gms.ads.internal.client.zzcw;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzdnu implements zzdmb {
    private final zzbru zza;
    private final zzdad zzb;
    private final zzczj zzc;
    private final zzdhg zzd;
    private final Context zze;
    private final zzfgt zzf;
    private final VersionInfoParcel zzg;
    private final zzfho zzh;
    private boolean zzi = false;
    private boolean zzj = false;
    private boolean zzk = true;
    private final zzbrq zzl;
    private final zzbrr zzm;

    public zzdnu(zzbrq zzbrq, zzbrr zzbrr, zzbru zzbru, zzdad zzdad, zzczj zzczj, zzdhg zzdhg, Context context, zzfgt zzfgt, VersionInfoParcel versionInfoParcel, zzfho zzfho) {
        this.zzl = zzbrq;
        this.zzm = zzbrr;
        this.zza = zzbru;
        this.zzb = zzdad;
        this.zzc = zzczj;
        this.zzd = zzdhg;
        this.zze = context;
        this.zzf = zzfgt;
        this.zzg = versionInfoParcel;
        this.zzh = zzfho;
    }

    private final void zzb(View view) {
        try {
            zzbru zzbru = this.zza;
            if (zzbru == null || zzbru.zzA()) {
                zzbrq zzbrq = this.zzl;
                if (zzbrq == null || zzbrq.zzx()) {
                    zzbrr zzbrr = this.zzm;
                    if (zzbrr != null && !zzbrr.zzv()) {
                        this.zzm.zzq(ObjectWrapper.wrap(view));
                        this.zzc.onAdClicked();
                        if (((Boolean) zzba.zzc().zza(zzbep.zzkM)).booleanValue()) {
                            this.zzd.zzdG();
                            return;
                        }
                        return;
                    }
                    return;
                }
                this.zzl.zzs(ObjectWrapper.wrap(view));
                this.zzc.onAdClicked();
                if (((Boolean) zzba.zzc().zza(zzbep.zzkM)).booleanValue()) {
                    this.zzd.zzdG();
                    return;
                }
                return;
            }
            this.zza.zzw(ObjectWrapper.wrap(view));
            this.zzc.onAdClicked();
            if (((Boolean) zzba.zzc().zza(zzbep.zzkM)).booleanValue()) {
                this.zzd.zzdG();
            }
        } catch (RemoteException e) {
            zzm.zzk("Failed to call handleClick", e);
        }
    }

    private static final HashMap zzc(Map map) {
        HashMap hashMap = new HashMap();
        if (map != null) {
            synchronized (map) {
                for (Map.Entry entry : map.entrySet()) {
                    View view = (View) ((WeakReference) entry.getValue()).get();
                    if (view != null) {
                        hashMap.put((String) entry.getKey(), view);
                    }
                }
            }
        }
        return hashMap;
    }

    public final boolean zzA() {
        return true;
    }

    public final boolean zzB() {
        return this.zzf.zzM;
    }

    public final boolean zzC(Bundle bundle) {
        return false;
    }

    public final int zza() {
        return 0;
    }

    public final JSONObject zze(View view, Map map, Map map2, ImageView.ScaleType scaleType) {
        return null;
    }

    public final JSONObject zzf(View view, Map map, Map map2, ImageView.ScaleType scaleType) {
        return null;
    }

    public final void zzg() {
        throw null;
    }

    public final void zzh() {
    }

    public final void zzi() {
    }

    public final void zzj(zzcw zzcw) {
        zzm.zzj("Mute This Ad is not supported for 3rd party ads");
    }

    public final void zzk(View view, View view2, Map map, Map map2, boolean z, ImageView.ScaleType scaleType) {
        if (!this.zzj || !this.zzf.zzM) {
            zzb(view);
        }
    }

    public final void zzl(String str) {
    }

    public final void zzm(Bundle bundle) {
    }

    public final void zzo(View view, View view2, Map map, Map map2, boolean z, ImageView.ScaleType scaleType, int i) {
        if (!this.zzj) {
            zzm.zzj("Custom click reporting for 3p ads failed. enableCustomClickGesture is not set.");
        } else if (!this.zzf.zzM) {
            zzm.zzj("Custom click reporting for 3p ads failed. Ad unit id not in allow list.");
        } else {
            zzb(view2);
        }
    }

    public final void zzp() {
    }

    public final void zzq(View view, Map map, Map map2, ImageView.ScaleType scaleType) {
        try {
            if (!this.zzi) {
                this.zzi = zzu.zzs().zzn(this.zze, this.zzg.afmaVersion, this.zzf.zzD.toString(), this.zzh.zzf);
            }
            if (this.zzk) {
                zzbru zzbru = this.zza;
                if (zzbru == null || zzbru.zzB()) {
                    zzbrq zzbrq = this.zzl;
                    if (zzbrq == null || zzbrq.zzy()) {
                        zzbrr zzbrr = this.zzm;
                        if (zzbrr != null && !zzbrr.zzw()) {
                            this.zzm.zzr();
                            this.zzb.zza();
                            return;
                        }
                        return;
                    }
                    this.zzl.zzt();
                    this.zzb.zza();
                    return;
                }
                this.zza.zzx();
                this.zzb.zza();
            }
        } catch (RemoteException e) {
            zzm.zzk("Failed to call recordImpression", e);
        }
    }

    public final void zzr() {
    }

    public final void zzs(View view, MotionEvent motionEvent, View view2) {
    }

    public final void zzt(Bundle bundle) {
    }

    public final void zzu(View view) {
    }

    public final void zzv() {
        this.zzj = true;
    }

    public final void zzw(zzcs zzcs) {
        zzm.zzj("Mute This Ad is not supported for 3rd party ads");
    }

    public final void zzx(zzbjp zzbjp) {
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzy(android.view.View r9, java.util.Map r10, java.util.Map r11, android.view.View.OnTouchListener r12, android.view.View.OnClickListener r13) {
        /*
            r8 = this;
            com.google.android.gms.dynamic.IObjectWrapper r9 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r9)     // Catch:{ RemoteException -> 0x0122 }
            com.google.android.gms.internal.ads.zzfgt r12 = r8.zzf     // Catch:{ RemoteException -> 0x0122 }
            org.json.JSONObject r12 = r12.zzak     // Catch:{ RemoteException -> 0x0122 }
            com.google.android.gms.internal.ads.zzbeg r13 = com.google.android.gms.internal.ads.zzbep.zzby     // Catch:{ RemoteException -> 0x0122 }
            com.google.android.gms.internal.ads.zzben r0 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ RemoteException -> 0x0122 }
            java.lang.Object r13 = r0.zza(r13)     // Catch:{ RemoteException -> 0x0122 }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ RemoteException -> 0x0122 }
            boolean r13 = r13.booleanValue()     // Catch:{ RemoteException -> 0x0122 }
            r0 = 1
            if (r13 == 0) goto L_0x00de
            int r13 = r12.length()     // Catch:{ RemoteException -> 0x0122 }
            if (r13 != 0) goto L_0x0023
            goto L_0x00de
        L_0x0023:
            if (r10 != 0) goto L_0x002b
            java.util.HashMap r13 = new java.util.HashMap     // Catch:{ RemoteException -> 0x0122 }
            r13.<init>()     // Catch:{ RemoteException -> 0x0122 }
            goto L_0x002c
        L_0x002b:
            r13 = r10
        L_0x002c:
            if (r11 != 0) goto L_0x0034
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ RemoteException -> 0x0122 }
            r1.<init>()     // Catch:{ RemoteException -> 0x0122 }
            goto L_0x0035
        L_0x0034:
            r1 = r11
        L_0x0035:
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ RemoteException -> 0x0122 }
            r2.<init>()     // Catch:{ RemoteException -> 0x0122 }
            r2.putAll(r13)     // Catch:{ RemoteException -> 0x0122 }
            r2.putAll(r1)     // Catch:{ RemoteException -> 0x0122 }
            java.util.Iterator r13 = r12.keys()     // Catch:{ RemoteException -> 0x0122 }
        L_0x0044:
            boolean r1 = r13.hasNext()     // Catch:{ RemoteException -> 0x0122 }
            if (r1 == 0) goto L_0x00de
            java.lang.Object r1 = r13.next()     // Catch:{ RemoteException -> 0x0122 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ RemoteException -> 0x0122 }
            org.json.JSONArray r3 = r12.optJSONArray(r1)     // Catch:{ RemoteException -> 0x0122 }
            if (r3 == 0) goto L_0x0044
            java.lang.Object r4 = r2.get(r1)     // Catch:{ RemoteException -> 0x0122 }
            java.lang.ref.WeakReference r4 = (java.lang.ref.WeakReference) r4     // Catch:{ RemoteException -> 0x0122 }
            r5 = 0
            if (r4 != 0) goto L_0x0062
        L_0x005f:
            r0 = r5
            goto L_0x00de
        L_0x0062:
            java.lang.Object r4 = r4.get()     // Catch:{ RemoteException -> 0x0122 }
            if (r4 != 0) goto L_0x0069
            goto L_0x005f
        L_0x0069:
            java.lang.Class r4 = r4.getClass()     // Catch:{ RemoteException -> 0x0122 }
            com.google.android.gms.internal.ads.zzbeg r6 = com.google.android.gms.internal.ads.zzbep.zzbz     // Catch:{ RemoteException -> 0x0122 }
            com.google.android.gms.internal.ads.zzben r7 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ RemoteException -> 0x0122 }
            java.lang.Object r6 = r7.zza(r6)     // Catch:{ RemoteException -> 0x0122 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ RemoteException -> 0x0122 }
            boolean r6 = r6.booleanValue()     // Catch:{ RemoteException -> 0x0122 }
            if (r6 == 0) goto L_0x00b1
            java.lang.String r6 = "3010"
            boolean r1 = r1.equals(r6)     // Catch:{ RemoteException -> 0x0122 }
            if (r1 == 0) goto L_0x00b1
            com.google.android.gms.internal.ads.zzbru r1 = r8.zza     // Catch:{ RemoteException -> 0x0122 }
            r4 = 0
            if (r1 == 0) goto L_0x0091
            com.google.android.gms.dynamic.IObjectWrapper r1 = r1.zzn()     // Catch:{ RemoteException -> 0x00aa }
            goto L_0x00a4
        L_0x0091:
            com.google.android.gms.internal.ads.zzbrq r1 = r8.zzl     // Catch:{ RemoteException -> 0x0122 }
            if (r1 == 0) goto L_0x009a
            com.google.android.gms.dynamic.IObjectWrapper r1 = r1.zzk()     // Catch:{ RemoteException -> 0x00aa }
            goto L_0x00a4
        L_0x009a:
            com.google.android.gms.internal.ads.zzbrr r1 = r8.zzm     // Catch:{ RemoteException -> 0x0122 }
            if (r1 == 0) goto L_0x00a3
            com.google.android.gms.dynamic.IObjectWrapper r1 = r1.zzj()     // Catch:{ RemoteException -> 0x00aa }
            goto L_0x00a4
        L_0x00a3:
            r1 = r4
        L_0x00a4:
            if (r1 == 0) goto L_0x00aa
            java.lang.Object r4 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r1)     // Catch:{  }
        L_0x00aa:
            if (r4 != 0) goto L_0x00ad
            goto L_0x005f
        L_0x00ad:
            java.lang.Class r4 = r4.getClass()     // Catch:{ RemoteException -> 0x0122 }
        L_0x00b1:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ JSONException -> 0x0044 }
            r1.<init>()     // Catch:{ JSONException -> 0x0044 }
            com.google.android.gms.ads.internal.util.zzbw.zzc(r3, r1)     // Catch:{ JSONException -> 0x0044 }
            com.google.android.gms.ads.internal.zzu.zzp()     // Catch:{ JSONException -> 0x0044 }
            android.content.Context r3 = r8.zze     // Catch:{ JSONException -> 0x0044 }
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch:{ JSONException -> 0x0044 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ JSONException -> 0x0044 }
        L_0x00c6:
            boolean r6 = r1.hasNext()     // Catch:{ JSONException -> 0x0044 }
            if (r6 == 0) goto L_0x005f
            java.lang.Object r6 = r1.next()     // Catch:{ JSONException -> 0x0044 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ JSONException -> 0x0044 }
            java.lang.Class r6 = java.lang.Class.forName(r6, r5, r3)     // Catch:{ all -> 0x00c6 }
            boolean r6 = r6.isAssignableFrom(r4)     // Catch:{ all -> 0x00c6 }
            if (r6 == 0) goto L_0x00c6
            goto L_0x0044
        L_0x00de:
            r8.zzk = r0     // Catch:{ RemoteException -> 0x0122 }
            java.util.HashMap r10 = zzc(r10)     // Catch:{ RemoteException -> 0x0122 }
            java.util.HashMap r11 = zzc(r11)     // Catch:{ RemoteException -> 0x0122 }
            com.google.android.gms.internal.ads.zzbru r12 = r8.zza     // Catch:{ RemoteException -> 0x0122 }
            if (r12 == 0) goto L_0x00f8
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r10)     // Catch:{ RemoteException -> 0x0122 }
            com.google.android.gms.dynamic.IObjectWrapper r11 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r11)     // Catch:{ RemoteException -> 0x0122 }
            r12.zzy(r9, r10, r11)     // Catch:{ RemoteException -> 0x0122 }
            return
        L_0x00f8:
            com.google.android.gms.internal.ads.zzbrq r12 = r8.zzl     // Catch:{ RemoteException -> 0x0122 }
            if (r12 == 0) goto L_0x010d
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r10)     // Catch:{ RemoteException -> 0x0122 }
            com.google.android.gms.dynamic.IObjectWrapper r11 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r11)     // Catch:{ RemoteException -> 0x0122 }
            r12.zzv(r9, r10, r11)     // Catch:{ RemoteException -> 0x0122 }
            com.google.android.gms.internal.ads.zzbrq r10 = r8.zzl     // Catch:{ RemoteException -> 0x0122 }
            r10.zzu(r9)     // Catch:{ RemoteException -> 0x0122 }
            return
        L_0x010d:
            com.google.android.gms.internal.ads.zzbrr r12 = r8.zzm     // Catch:{ RemoteException -> 0x0122 }
            if (r12 == 0) goto L_0x0121
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r10)     // Catch:{ RemoteException -> 0x0122 }
            com.google.android.gms.dynamic.IObjectWrapper r11 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r11)     // Catch:{ RemoteException -> 0x0122 }
            r12.zzt(r9, r10, r11)     // Catch:{ RemoteException -> 0x0122 }
            com.google.android.gms.internal.ads.zzbrr r10 = r8.zzm     // Catch:{ RemoteException -> 0x0122 }
            r10.zzs(r9)     // Catch:{ RemoteException -> 0x0122 }
        L_0x0121:
            return
        L_0x0122:
            r9 = move-exception
            java.lang.String r10 = "Failed to call trackView"
            com.google.android.gms.ads.internal.util.client.zzm.zzk(r10, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdnu.zzy(android.view.View, java.util.Map, java.util.Map, android.view.View$OnTouchListener, android.view.View$OnClickListener):void");
    }

    public final void zzz(View view, Map map) {
        try {
            IObjectWrapper wrap = ObjectWrapper.wrap(view);
            zzbru zzbru = this.zza;
            if (zzbru != null) {
                zzbru.zzz(wrap);
                return;
            }
            zzbrq zzbrq = this.zzl;
            if (zzbrq != null) {
                zzbrq.zzw(wrap);
                return;
            }
            zzbrr zzbrr = this.zzm;
            if (zzbrr != null) {
                zzbrr.zzu(wrap);
            }
        } catch (RemoteException e) {
            zzm.zzk("Failed to call untrackView", e);
        }
    }
}
