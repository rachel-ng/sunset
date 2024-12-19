package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzbae;
import com.google.android.gms.internal.ads.zzbaf;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzbj extends zzbae implements zzbk {
    public zzbj() {
        super("com.google.android.gms.ads.internal.client.IAdLoadCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zzdF(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzc();
        } else if (i != 2) {
            return false;
        } else {
            zzbaf.zzc(parcel);
            zzb((zze) zzbaf.zza(parcel, zze.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
