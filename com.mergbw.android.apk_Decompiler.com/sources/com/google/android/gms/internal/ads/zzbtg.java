package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzbtg extends zzbae implements zzbth {
    public zzbtg() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zzdF(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            String readString = parcel.readString();
            zzbaf.zzc(parcel);
            zze(readString);
        } else if (i == 2) {
            String readString2 = parcel.readString();
            zzbaf.zzc(parcel);
            zzf(readString2);
        } else if (i != 3) {
            return false;
        } else {
            zzbaf.zzc(parcel);
            zzg((zze) zzbaf.zza(parcel, zze.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
