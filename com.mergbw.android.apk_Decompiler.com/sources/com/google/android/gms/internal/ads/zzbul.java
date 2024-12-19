package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.nativead.NativeAd;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzbul extends NativeAd.AdChoicesInfo {
    private final List zza = new ArrayList();
    private String zzb;

    public zzbul(zzbho zzbho) {
        try {
            this.zzb = zzbho.zzg();
        } catch (RemoteException e) {
            zzm.zzh("", e);
            this.zzb = "";
        }
        try {
            for (Object next : zzbho.zzh()) {
                zzbhv zzg = next instanceof IBinder ? zzbhu.zzg((IBinder) next) : null;
                if (zzg != null) {
                    this.zza.add(new zzbun(zzg));
                }
            }
        } catch (RemoteException e2) {
            zzm.zzh("", e2);
        }
    }

    public final List<NativeAd.Image> getImages() {
        return this.zza;
    }

    public final CharSequence getText() {
        return this.zzb;
    }
}
