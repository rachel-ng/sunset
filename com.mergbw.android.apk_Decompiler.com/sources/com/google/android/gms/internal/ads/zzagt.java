package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzagt implements zzcc {
    public static final Parcelable.Creator<zzagt> CREATOR = new zzags();
    private static final zzan zzf;
    private static final zzan zzg;
    public final String zza;
    public final String zzb;
    public final long zzc;
    public final long zzd;
    public final byte[] zze;
    private int zzh;

    static {
        zzal zzal = new zzal();
        zzal.zzX(MimeTypes.APPLICATION_ID3);
        zzf = zzal.zzad();
        zzal zzal2 = new zzal();
        zzal2.zzX(MimeTypes.APPLICATION_SCTE35);
        zzg = zzal2.zzad();
    }

    zzagt(Parcel parcel) {
        String readString = parcel.readString();
        int i = zzgd.zza;
        this.zza = readString;
        this.zzb = parcel.readString();
        this.zzc = parcel.readLong();
        this.zzd = parcel.readLong();
        this.zze = parcel.createByteArray();
    }

    public zzagt(String str, String str2, long j, long j2, byte[] bArr) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = j;
        this.zzd = j2;
        this.zze = bArr;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzagt zzagt = (zzagt) obj;
            return this.zzc == zzagt.zzc && this.zzd == zzagt.zzd && zzgd.zzG(this.zza, zzagt.zza) && zzgd.zzG(this.zzb, zzagt.zzb) && Arrays.equals(this.zze, zzagt.zze);
        }
    }

    public final int hashCode() {
        int i = this.zzh;
        if (i != 0) {
            return i;
        }
        String str = this.zza;
        int i2 = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        String str2 = this.zzb;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        long j = this.zzc;
        long j2 = this.zzd;
        int hashCode2 = ((((((((hashCode + 527) * 31) + i2) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + Arrays.hashCode(this.zze);
        this.zzh = hashCode2;
        return hashCode2;
    }

    public final String toString() {
        return "EMSG: scheme=" + this.zza + ", id=" + this.zzd + ", durationMs=" + this.zzc + ", value=" + this.zzb;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zza);
        parcel.writeString(this.zzb);
        parcel.writeLong(this.zzc);
        parcel.writeLong(this.zzd);
        parcel.writeByteArray(this.zze);
    }

    public final /* synthetic */ void zza(zzby zzby) {
    }
}
