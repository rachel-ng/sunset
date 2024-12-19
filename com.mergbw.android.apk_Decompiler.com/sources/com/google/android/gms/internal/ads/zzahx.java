package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzahx extends zzahr {
    public static final Parcelable.Creator<zzahx> CREATOR = new zzahw();
    public final String zza;
    public final byte[] zzb;

    zzahx(Parcel parcel) {
        super(PrivFrame.ID);
        String readString = parcel.readString();
        int i = zzgd.zza;
        this.zza = readString;
        this.zzb = parcel.createByteArray();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzahx zzahx = (zzahx) obj;
            return zzgd.zzG(this.zza, zzahx.zza) && Arrays.equals(this.zzb, zzahx.zzb);
        }
    }

    public final int hashCode() {
        String str = this.zza;
        return (((str != null ? str.hashCode() : 0) + 527) * 31) + Arrays.hashCode(this.zzb);
    }

    public final String toString() {
        return this.zzf + ": owner=" + this.zza;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zza);
        parcel.writeByteArray(this.zzb);
    }

    public zzahx(String str, byte[] bArr) {
        super(PrivFrame.ID);
        this.zza = str;
        this.zzb = bArr;
    }
}
