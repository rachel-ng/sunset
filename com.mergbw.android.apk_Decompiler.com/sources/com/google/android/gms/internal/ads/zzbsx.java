package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzbsx extends zzbae implements zzbsy {
    public zzbsx() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zzdF(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzbru zzb = zzbrt.zzb(parcel.readStrongBinder());
            zzbaf.zzc(parcel);
            zzg(zzb);
        } else if (i == 2) {
            String readString = parcel.readString();
            zzbaf.zzc(parcel);
            zze(readString);
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
