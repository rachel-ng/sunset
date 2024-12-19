package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfvm extends zzbad implements zzfvo {
    zzfvm(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.lmd.protocol.ILmdOverlayService");
    }

    public final void zze(Bundle bundle, zzfvq zzfvq) throws RemoteException {
        Parcel zza = zza();
        zzbaf.zzd(zza, bundle);
        zzbaf.zzf(zza, zzfvq);
        zzdd(2, zza);
    }

    public final void zzf(String str, Bundle bundle, zzfvq zzfvq) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzbaf.zzd(zza, bundle);
        zzbaf.zzf(zza, zzfvq);
        zzdd(1, zza);
    }

    public final void zzg(Bundle bundle, zzfvq zzfvq) throws RemoteException {
        Parcel zza = zza();
        zzbaf.zzd(zza, bundle);
        zzbaf.zzf(zza, zzfvq);
        zzdd(3, zza);
    }
}
