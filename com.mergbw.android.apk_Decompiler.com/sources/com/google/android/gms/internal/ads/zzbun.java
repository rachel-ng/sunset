package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzbun extends NativeAd.Image {
    private final zzbhv zza;
    private final Drawable zzb;
    private final Uri zzc;
    private final double zzd;
    private final int zze;
    private final int zzf;

    public zzbun(zzbhv zzbhv) {
        Drawable drawable;
        double d;
        int i;
        this.zza = zzbhv;
        Uri uri = null;
        try {
            IObjectWrapper zzf2 = zzbhv.zzf();
            if (zzf2 != null) {
                drawable = (Drawable) ObjectWrapper.unwrap(zzf2);
                this.zzb = drawable;
                uri = this.zza.zze();
                this.zzc = uri;
                d = this.zza.zzb();
                this.zzd = d;
                int i2 = -1;
                i = this.zza.zzd();
                this.zze = i;
                i2 = this.zza.zzc();
                this.zzf = i2;
            }
        } catch (RemoteException e) {
            zzm.zzh("", e);
        }
        drawable = null;
        this.zzb = drawable;
        try {
            uri = this.zza.zze();
        } catch (RemoteException e2) {
            zzm.zzh("", e2);
        }
        this.zzc = uri;
        try {
            d = this.zza.zzb();
        } catch (RemoteException e3) {
            zzm.zzh("", e3);
            d = 1.0d;
        }
        this.zzd = d;
        int i22 = -1;
        try {
            i = this.zza.zzd();
        } catch (RemoteException e4) {
            zzm.zzh("", e4);
            i = -1;
        }
        this.zze = i;
        try {
            i22 = this.zza.zzc();
        } catch (RemoteException e5) {
            zzm.zzh("", e5);
        }
        this.zzf = i22;
    }

    public final Drawable getDrawable() {
        return this.zzb;
    }

    public final double getScale() {
        return this.zzd;
    }

    public final Uri getUri() {
        return this.zzc;
    }

    public final int zza() {
        return this.zzf;
    }

    public final int zzb() {
        return this.zze;
    }
}
