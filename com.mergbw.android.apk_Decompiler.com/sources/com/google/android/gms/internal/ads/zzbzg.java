package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzbzg extends zzbae implements zzbzh {
    public zzbzg() {
        super("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdLoadCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zzdF(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzg();
        } else if (i == 2) {
            int readInt = parcel.readInt();
            zzbaf.zzc(parcel);
            zze(readInt);
        } else if (i != 3) {
            return false;
        } else {
            zzbaf.zzc(parcel);
            zzf((zze) zzbaf.zza(parcel, zze.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
