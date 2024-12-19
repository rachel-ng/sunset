package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzbu;
import com.google.android.gms.internal.measurement.zzbw;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzfn extends zzbu implements zzfl {
    public final zzaj zza(zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzo);
        Parcel zza = zza(21, a_);
        zzaj zzaj = (zzaj) zzbw.zza(zza, zzaj.CREATOR);
        zza.recycle();
        return zzaj;
    }

    public final String zzb(zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzo);
        Parcel zza = zza(11, a_);
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final List<zzmu> zza(zzo zzo, Bundle bundle) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzo);
        zzbw.zza(a_, (Parcelable) bundle);
        Parcel zza = zza(24, a_);
        ArrayList<zzmu> createTypedArrayList = zza.createTypedArrayList(zzmu.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final List<zzno> zza(zzo zzo, boolean z) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzo);
        zzbw.zza(a_, z);
        Parcel zza = zza(7, a_);
        ArrayList<zzno> createTypedArrayList = zza.createTypedArrayList(zzno.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final List<zzae> zza(String str, String str2, zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (Parcelable) zzo);
        Parcel zza = zza(16, a_);
        ArrayList<zzae> createTypedArrayList = zza.createTypedArrayList(zzae.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final List<zzae> zza(String str, String str2, String str3) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        Parcel zza = zza(17, a_);
        ArrayList<zzae> createTypedArrayList = zza.createTypedArrayList(zzae.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final List<zzno> zza(String str, String str2, boolean z, zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, z);
        zzbw.zza(a_, (Parcelable) zzo);
        Parcel zza = zza(14, a_);
        ArrayList<zzno> createTypedArrayList = zza.createTypedArrayList(zzno.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final List<zzno> zza(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        zzbw.zza(a_, z);
        Parcel zza = zza(15, a_);
        ArrayList<zzno> createTypedArrayList = zza.createTypedArrayList(zzno.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    zzfn(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public final void zzc(zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzo);
        zzb(4, a_);
    }

    public final void zza(zzbd zzbd, zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzbd);
        zzbw.zza(a_, (Parcelable) zzo);
        zzb(1, a_);
    }

    public final void zza(zzbd zzbd, String str, String str2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzbd);
        a_.writeString(str);
        a_.writeString(str2);
        zzb(5, a_);
    }

    public final void zzd(zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzo);
        zzb(18, a_);
    }

    public final void zza(zzae zzae, zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzae);
        zzbw.zza(a_, (Parcelable) zzo);
        zzb(12, a_);
    }

    public final void zza(zzae zzae) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzae);
        zzb(13, a_);
    }

    public final void zze(zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzo);
        zzb(20, a_);
    }

    public final void zza(long j, String str, String str2, String str3) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j);
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        zzb(10, a_);
    }

    public final void zza(Bundle bundle, zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        zzbw.zza(a_, (Parcelable) zzo);
        zzb(19, a_);
    }

    public final void zzf(zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzo);
        zzb(26, a_);
    }

    public final void zzg(zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzo);
        zzb(6, a_);
    }

    public final void zzh(zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzo);
        zzb(25, a_);
    }

    public final void zza(zzno zzno, zzo zzo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzno);
        zzbw.zza(a_, (Parcelable) zzo);
        zzb(2, a_);
    }

    public final byte[] zza(zzbd zzbd, String str) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzbd);
        a_.writeString(str);
        Parcel zza = zza(9, a_);
        byte[] createByteArray = zza.createByteArray();
        zza.recycle();
        return createByteArray;
    }
}
