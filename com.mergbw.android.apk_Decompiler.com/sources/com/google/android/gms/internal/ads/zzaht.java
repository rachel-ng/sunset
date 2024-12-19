package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaht extends zzahr {
    public static final Parcelable.Creator<zzaht> CREATOR = new zzahs();
    public final String zza;
    public final String zzb;
    public final String zzc;

    zzaht(Parcel parcel) {
        super(InternalFrame.ID);
        String readString = parcel.readString();
        int i = zzgd.zza;
        this.zza = readString;
        this.zzb = parcel.readString();
        this.zzc = parcel.readString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzaht zzaht = (zzaht) obj;
            return zzgd.zzG(this.zzb, zzaht.zzb) && zzgd.zzG(this.zza, zzaht.zza) && zzgd.zzG(this.zzc, zzaht.zzc);
        }
    }

    public final int hashCode() {
        String str = this.zza;
        int i = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        String str2 = this.zzb;
        int hashCode2 = str2 != null ? str2.hashCode() : 0;
        int i2 = hashCode + 527;
        String str3 = this.zzc;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return (((i2 * 31) + hashCode2) * 31) + i;
    }

    public final String toString() {
        return this.zzf + ": domain=" + this.zza + ", description=" + this.zzb;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzf);
        parcel.writeString(this.zza);
        parcel.writeString(this.zzc);
    }

    public zzaht(String str, String str2, String str3) {
        super(InternalFrame.ID);
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }
}
