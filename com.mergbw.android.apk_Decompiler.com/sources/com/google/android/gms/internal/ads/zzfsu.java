package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfsu extends zzbad implements IInterface {
    zzfsu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gass.internal.IGassService");
    }

    public final zzfss zze(zzfsq zzfsq) throws RemoteException {
        Parcel zza = zza();
        zzbaf.zzd(zza, zzfsq);
        Parcel zzdb = zzdb(1, zza);
        zzfss zzfss = (zzfss) zzbaf.zza(zzdb, zzfss.CREATOR);
        zzdb.recycle();
        return zzfss;
    }

    public final zzftb zzf(zzfsz zzfsz) throws RemoteException {
        Parcel zza = zza();
        zzbaf.zzd(zza, zzfsz);
        Parcel zzdb = zzdb(3, zza);
        zzftb zzftb = (zzftb) zzbaf.zza(zzdb, zzftb.CREATOR);
        zzdb.recycle();
        return zzftb;
    }

    public final void zzg(zzfsn zzfsn) throws RemoteException {
        Parcel zza = zza();
        zzbaf.zzd(zza, zzfsn);
        zzdc(2, zza);
    }
}
