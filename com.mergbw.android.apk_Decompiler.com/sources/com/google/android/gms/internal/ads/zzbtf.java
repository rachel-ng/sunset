package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzbtf extends zzbad implements zzbth {
    zzbtf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
    }

    public final void zze(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzdc(1, zza);
    }

    public final void zzf(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzdc(2, zza);
    }

    public final void zzg(zze zze) throws RemoteException {
        Parcel zza = zza();
        zzbaf.zzd(zza, zze);
        zzdc(3, zza);
    }
}