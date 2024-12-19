package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.zzbb;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzbxj extends zzbae implements zzbxk {
    public zzbxj() {
        super("com.google.android.gms.ads.internal.request.IAdsServiceResponseListener");
    }

    /* access modifiers changed from: protected */
    public final boolean zzdF(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzbaf.zzc(parcel);
            zzf((ParcelFileDescriptor) zzbaf.zza(parcel, ParcelFileDescriptor.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            zzbaf.zzc(parcel);
            zze((zzbb) zzbaf.zza(parcel, zzbb.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
