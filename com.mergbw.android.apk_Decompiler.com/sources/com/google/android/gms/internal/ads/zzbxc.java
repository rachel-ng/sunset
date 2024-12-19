package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzbxc extends zzbae implements zzbxd {
    public zzbxc() {
        super("com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    /* access modifiers changed from: protected */
    public final boolean zzdF(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzbxn zzbxn = null;
        if (i == 1) {
            zzbws zzbws = (zzbws) zzbaf.zza(parcel, zzbws.CREATOR);
            zzbaf.zzc(parcel);
            parcel2.writeNoException();
            zzbaf.zze(parcel2, (Parcelable) null);
        } else if (i == 2) {
            zzbws zzbws2 = (zzbws) zzbaf.zza(parcel, zzbws.CREATOR);
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                if (queryLocalInterface instanceof zzbxe) {
                    zzbxe zzbxe = (zzbxe) queryLocalInterface;
                }
            }
            zzbaf.zzc(parcel);
            parcel2.writeNoException();
        } else if (i == 4) {
            zzbxu zzbxu = (zzbxu) zzbaf.zza(parcel, zzbxu.CREATOR);
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            if (readStrongBinder2 != null) {
                IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                zzbxn = queryLocalInterface2 instanceof zzbxn ? (zzbxn) queryLocalInterface2 : new zzbxl(readStrongBinder2);
            }
            zzbaf.zzc(parcel);
            zzg(zzbxu, zzbxn);
            parcel2.writeNoException();
        } else if (i == 5) {
            zzbxu zzbxu2 = (zzbxu) zzbaf.zza(parcel, zzbxu.CREATOR);
            IBinder readStrongBinder3 = parcel.readStrongBinder();
            if (readStrongBinder3 != null) {
                IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                zzbxn = queryLocalInterface3 instanceof zzbxn ? (zzbxn) queryLocalInterface3 : new zzbxl(readStrongBinder3);
            }
            zzbaf.zzc(parcel);
            zzf(zzbxu2, zzbxn);
            parcel2.writeNoException();
        } else if (i == 6) {
            zzbxu zzbxu3 = (zzbxu) zzbaf.zza(parcel, zzbxu.CREATOR);
            IBinder readStrongBinder4 = parcel.readStrongBinder();
            if (readStrongBinder4 != null) {
                IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                zzbxn = queryLocalInterface4 instanceof zzbxn ? (zzbxn) queryLocalInterface4 : new zzbxl(readStrongBinder4);
            }
            zzbaf.zzc(parcel);
            zze(zzbxu3, zzbxn);
            parcel2.writeNoException();
        } else if (i != 7) {
            return false;
        } else {
            String readString = parcel.readString();
            IBinder readStrongBinder5 = parcel.readStrongBinder();
            if (readStrongBinder5 != null) {
                IInterface queryLocalInterface5 = readStrongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                zzbxn = queryLocalInterface5 instanceof zzbxn ? (zzbxn) queryLocalInterface5 : new zzbxl(readStrongBinder5);
            }
            zzbaf.zzc(parcel);
            zzh(readString, zzbxn);
            parcel2.writeNoException();
        }
        return true;
    }
}
