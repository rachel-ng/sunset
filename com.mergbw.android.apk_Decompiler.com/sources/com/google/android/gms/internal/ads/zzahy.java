package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzahy implements Parcelable.Creator {
    zzahy() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String readString = parcel.readString();
        readString.getClass();
        String readString2 = parcel.readString();
        String[] createStringArray = parcel.createStringArray();
        createStringArray.getClass();
        return new zzahz(readString, readString2, zzgbc.zzl(createStringArray));
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzahz[i];
    }
}
