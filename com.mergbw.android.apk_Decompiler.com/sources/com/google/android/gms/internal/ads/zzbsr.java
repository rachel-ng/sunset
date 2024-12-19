package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzbsr extends zzbae implements zzbss {
    public zzbsr() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zzdF(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            zzbaf.zzc(parcel);
            zzg(asInterface);
        } else if (i == 2) {
            String readString = parcel.readString();
            zzbaf.zzc(parcel);
            zze(readString);
        } else if (i == 3) {
            zzbaf.zzc(parcel);
            zzf((zze) zzbaf.zza(parcel, zze.CREATOR));
        } else if (i != 4) {
            return false;
        } else {
            zzbro zzb = zzbrn.zzb(parcel.readStrongBinder());
            zzbaf.zzc(parcel);
            zzh(zzb);
        }
        parcel2.writeNoException();
        return true;
    }
}
