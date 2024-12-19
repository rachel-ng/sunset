package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.id3.ChapterTocFrame;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzahi extends zzahr {
    public static final Parcelable.Creator<zzahi> CREATOR = new zzahh();
    public final String zza;
    public final boolean zzb;
    public final boolean zzc;
    public final String[] zzd;
    private final zzahr[] zze;

    zzahi(Parcel parcel) {
        super(ChapterTocFrame.ID);
        String readString = parcel.readString();
        int i = zzgd.zza;
        this.zza = readString;
        boolean z = true;
        this.zzb = parcel.readByte() != 0;
        this.zzc = parcel.readByte() == 0 ? false : z;
        this.zzd = parcel.createStringArray();
        int readInt = parcel.readInt();
        this.zze = new zzahr[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            this.zze[i2] = (zzahr) parcel.readParcelable(zzahr.class.getClassLoader());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzahi zzahi = (zzahi) obj;
            return this.zzb == zzahi.zzb && this.zzc == zzahi.zzc && zzgd.zzG(this.zza, zzahi.zza) && Arrays.equals(this.zzd, zzahi.zzd) && Arrays.equals(this.zze, zzahi.zze);
        }
    }

    public final int hashCode() {
        String str = this.zza;
        return (((((this.zzb ? 1 : 0) + true) * 31) + (this.zzc ? 1 : 0)) * 31) + (str != null ? str.hashCode() : 0);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zza);
        parcel.writeByte(this.zzb ? (byte) 1 : 0);
        parcel.writeByte(this.zzc ? (byte) 1 : 0);
        parcel.writeStringArray(this.zzd);
        parcel.writeInt(this.zze.length);
        for (zzahr writeParcelable : this.zze) {
            parcel.writeParcelable(writeParcelable, 0);
        }
    }

    public zzahi(String str, boolean z, boolean z2, String[] strArr, zzahr[] zzahrArr) {
        super(ChapterTocFrame.ID);
        this.zza = str;
        this.zzb = z;
        this.zzc = z2;
        this.zzd = strArr;
        this.zze = zzahrArr;
    }
}
