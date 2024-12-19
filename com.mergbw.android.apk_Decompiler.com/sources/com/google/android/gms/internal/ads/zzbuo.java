package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.MuteThisAdListener;
import com.google.android.gms.ads.MuteThisAdReason;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.internal.client.zzct;
import com.google.android.gms.ads.internal.client.zzcw;
import com.google.android.gms.ads.internal.client.zzcx;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzep;
import com.google.android.gms.ads.internal.client.zzfe;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.nativead.NativeAd;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzbuo extends NativeAd {
    private final zzbjs zza;
    private final List zzb = new ArrayList();
    private final zzbun zzc;
    private final NativeAd.AdChoicesInfo zzd;
    private final List zze = new ArrayList();

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0097 A[Catch:{ RemoteException -> 0x00a4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzbuo(com.google.android.gms.internal.ads.zzbjs r6) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            r5.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5.zzb = r1
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5.zze = r1
            r5.zza = r6
            r1 = 0
            java.util.List r6 = r6.zzu()     // Catch:{ RemoteException -> 0x0043 }
            if (r6 == 0) goto L_0x0047
            java.util.Iterator r6 = r6.iterator()     // Catch:{ RemoteException -> 0x0043 }
        L_0x0020:
            boolean r2 = r6.hasNext()     // Catch:{ RemoteException -> 0x0043 }
            if (r2 == 0) goto L_0x0047
            java.lang.Object r2 = r6.next()     // Catch:{ RemoteException -> 0x0043 }
            boolean r3 = r2 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x0043 }
            if (r3 == 0) goto L_0x0035
            android.os.IBinder r2 = (android.os.IBinder) r2     // Catch:{ RemoteException -> 0x0043 }
            com.google.android.gms.internal.ads.zzbhv r2 = com.google.android.gms.internal.ads.zzbhu.zzg(r2)     // Catch:{ RemoteException -> 0x0043 }
            goto L_0x0036
        L_0x0035:
            r2 = r1
        L_0x0036:
            if (r2 == 0) goto L_0x0020
            java.util.List r3 = r5.zzb     // Catch:{ RemoteException -> 0x0043 }
            com.google.android.gms.internal.ads.zzbun r4 = new com.google.android.gms.internal.ads.zzbun     // Catch:{ RemoteException -> 0x0043 }
            r4.<init>(r2)     // Catch:{ RemoteException -> 0x0043 }
            r3.add(r4)     // Catch:{ RemoteException -> 0x0043 }
            goto L_0x0020
        L_0x0043:
            r6 = move-exception
            com.google.android.gms.ads.internal.util.client.zzm.zzh(r0, r6)
        L_0x0047:
            com.google.android.gms.internal.ads.zzbjs r6 = r5.zza     // Catch:{ RemoteException -> 0x0076 }
            java.util.List r6 = r6.zzv()     // Catch:{ RemoteException -> 0x0076 }
            if (r6 == 0) goto L_0x007a
            java.util.Iterator r6 = r6.iterator()     // Catch:{ RemoteException -> 0x0076 }
        L_0x0053:
            boolean r2 = r6.hasNext()     // Catch:{ RemoteException -> 0x0076 }
            if (r2 == 0) goto L_0x007a
            java.lang.Object r2 = r6.next()     // Catch:{ RemoteException -> 0x0076 }
            boolean r3 = r2 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x0076 }
            if (r3 == 0) goto L_0x0068
            android.os.IBinder r2 = (android.os.IBinder) r2     // Catch:{ RemoteException -> 0x0076 }
            com.google.android.gms.ads.internal.client.zzcw r2 = com.google.android.gms.ads.internal.client.zzcv.zzb(r2)     // Catch:{ RemoteException -> 0x0076 }
            goto L_0x0069
        L_0x0068:
            r2 = r1
        L_0x0069:
            if (r2 == 0) goto L_0x0053
            java.util.List r3 = r5.zze     // Catch:{ RemoteException -> 0x0076 }
            com.google.android.gms.ads.internal.client.zzcx r4 = new com.google.android.gms.ads.internal.client.zzcx     // Catch:{ RemoteException -> 0x0076 }
            r4.<init>(r2)     // Catch:{ RemoteException -> 0x0076 }
            r3.add(r4)     // Catch:{ RemoteException -> 0x0076 }
            goto L_0x0053
        L_0x0076:
            r6 = move-exception
            com.google.android.gms.ads.internal.util.client.zzm.zzh(r0, r6)
        L_0x007a:
            com.google.android.gms.internal.ads.zzbjs r6 = r5.zza     // Catch:{ RemoteException -> 0x0088 }
            com.google.android.gms.internal.ads.zzbhv r6 = r6.zzk()     // Catch:{ RemoteException -> 0x0088 }
            if (r6 == 0) goto L_0x008c
            com.google.android.gms.internal.ads.zzbun r2 = new com.google.android.gms.internal.ads.zzbun     // Catch:{ RemoteException -> 0x0088 }
            r2.<init>(r6)     // Catch:{ RemoteException -> 0x0088 }
            goto L_0x008d
        L_0x0088:
            r6 = move-exception
            com.google.android.gms.ads.internal.util.client.zzm.zzh(r0, r6)
        L_0x008c:
            r2 = r1
        L_0x008d:
            r5.zzc = r2
            com.google.android.gms.internal.ads.zzbjs r6 = r5.zza     // Catch:{ RemoteException -> 0x00a4 }
            com.google.android.gms.internal.ads.zzbho r6 = r6.zzi()     // Catch:{ RemoteException -> 0x00a4 }
            if (r6 == 0) goto L_0x00a8
            com.google.android.gms.internal.ads.zzbul r6 = new com.google.android.gms.internal.ads.zzbul     // Catch:{ RemoteException -> 0x00a4 }
            com.google.android.gms.internal.ads.zzbjs r2 = r5.zza     // Catch:{ RemoteException -> 0x00a4 }
            com.google.android.gms.internal.ads.zzbho r2 = r2.zzi()     // Catch:{ RemoteException -> 0x00a4 }
            r6.<init>(r2)     // Catch:{ RemoteException -> 0x00a4 }
            r1 = r6
            goto L_0x00a8
        L_0x00a4:
            r6 = move-exception
            com.google.android.gms.ads.internal.util.client.zzm.zzh(r0, r6)
        L_0x00a8:
            r5.zzd = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbuo.<init>(com.google.android.gms.internal.ads.zzbjs):void");
    }

    public final void cancelUnconfirmedClick() {
        try {
            this.zza.zzw();
        } catch (RemoteException e) {
            zzm.zzh("Failed to cancelUnconfirmedClick", e);
        }
    }

    public final void destroy() {
        try {
            this.zza.zzx();
        } catch (RemoteException e) {
            zzm.zzh("", e);
        }
    }

    public final void enableCustomClickGesture() {
        try {
            this.zza.zzC();
        } catch (RemoteException e) {
            zzm.zzh("", e);
        }
    }

    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzd;
    }

    public final String getAdvertiser() {
        try {
            return this.zza.zzn();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final String getBody() {
        try {
            return this.zza.zzo();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final String getCallToAction() {
        try {
            return this.zza.zzp();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            Bundle zzf = this.zza.zzf();
            if (zzf != null) {
                return zzf;
            }
        } catch (RemoteException e) {
            zzm.zzh("", e);
        }
        return new Bundle();
    }

    public final String getHeadline() {
        try {
            return this.zza.zzq();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final NativeAd.Image getIcon() {
        return this.zzc;
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzb;
    }

    public final MediaContent getMediaContent() {
        try {
            if (this.zza.zzj() != null) {
                return new zzep(this.zza.zzj(), (zzbip) null);
            }
        } catch (RemoteException e) {
            zzm.zzh("", e);
        }
        return null;
    }

    public final List<MuteThisAdReason> getMuteThisAdReasons() {
        return this.zze;
    }

    public final String getPrice() {
        try {
            return this.zza.zzs();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final ResponseInfo getResponseInfo() {
        zzdn zzdn;
        try {
            zzdn = this.zza.zzg();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            zzdn = null;
        }
        return ResponseInfo.zza(zzdn);
    }

    public final Double getStarRating() {
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

    public final String getStore() {
        try {
            return this.zza.zzt();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }

    public final boolean isCustomClickGestureEnabled() {
        try {
            return this.zza.zzG();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return false;
        }
    }

    public final boolean isCustomMuteThisAdEnabled() {
        try {
            return this.zza.zzH();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return false;
        }
    }

    public final void muteThisAd(MuteThisAdReason muteThisAdReason) {
        try {
            if (this.zza.zzH()) {
                if (muteThisAdReason == null) {
                    try {
                        this.zza.zzy((zzcw) null);
                        return;
                    } catch (RemoteException e) {
                        zzm.zzh("", e);
                        return;
                    }
                } else if (muteThisAdReason instanceof zzcx) {
                    this.zza.zzy(((zzcx) muteThisAdReason).zza());
                    return;
                } else {
                    zzm.zzg("Use mute reason from UnifiedNativeAd.getMuteThisAdReasons() or null");
                    return;
                }
            }
        } catch (RemoteException e2) {
            zzm.zzh("", e2);
        }
        zzm.zzg("Ad is not custom mute enabled");
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zza.zzz(bundle);
        } catch (RemoteException e) {
            zzm.zzh("", e);
        }
    }

    public final void recordCustomClickGesture() {
        try {
            this.zza.zzA();
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

    public final void setMuteThisAdListener(MuteThisAdListener muteThisAdListener) {
        try {
            this.zza.zzD(new zzct(muteThisAdListener));
        } catch (RemoteException e) {
            zzm.zzh("", e);
        }
    }

    public final void setOnPaidEventListener(OnPaidEventListener onPaidEventListener) {
        try {
            this.zza.zzE(new zzfe(onPaidEventListener));
        } catch (RemoteException e) {
            zzm.zzh("Failed to setOnPaidEventListener", e);
        }
    }

    public final void setUnconfirmedClickListener(NativeAd.UnconfirmedClickListener unconfirmedClickListener) {
        try {
            this.zza.zzF(new zzbuw(unconfirmedClickListener));
        } catch (RemoteException e) {
            zzm.zzh("Failed to setUnconfirmedClickListener", e);
        }
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object zza() {
        try {
            return this.zza.zzm();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            return null;
        }
    }
}
