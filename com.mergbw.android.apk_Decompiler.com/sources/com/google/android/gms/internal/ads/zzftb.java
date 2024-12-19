package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzftb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzftb> CREATOR = new zzftc();
    public final int zza;
    public final byte[] zzb;
    public final int zzc;

    zzftb(int i, byte[] bArr, int i2) {
        byte[] bArr2;
        this.zza = i;
        if (bArr == null) {
            bArr2 = null;
        } else {
            bArr2 = Arrays.copyOf(bArr, bArr.length);
        }
        this.zzb = bArr2;
        this.zzc = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        SafeParcelWriter.writeByteArray(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzftb(byte[] bArr, int i) {
        this(1, (byte[]) null, 1);
    }
}
