package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfjj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfjj> CREATOR = new zzfjk();
    @Nullable
    public final Context zza;
    public final zzfjg zzb;
    public final int zzc;
    public final int zzd;
    public final int zze;
    public final String zzf;
    public final int zzg;
    private final zzfjg[] zzh;
    private final int zzi;
    private final int zzj;
    private final int zzk;
    private final int[] zzl;
    private final int[] zzm;

    public zzfjj(int i, int i2, int i3, int i4, String str, int i5, int i6) {
        zzfjg[] values = zzfjg.values();
        this.zzh = values;
        int[] zza2 = zzfjh.zza();
        this.zzl = zza2;
        int[] zza3 = zzfji.zza();
        this.zzm = zza3;
        this.zza = null;
        this.zzi = i;
        this.zzb = values[i];
        this.zzc = i2;
        this.zzd = i3;
        this.zze = i4;
        this.zzf = str;
        this.zzj = i5;
        this.zzg = zza2[i5];
        this.zzk = i6;
        int i7 = zza3[i6];
    }

    @Nullable
    public static zzfjj zza(zzfjg zzfjg, Context context) {
        if (zzfjg == zzfjg.Rewarded) {
            return new zzfjj(context, zzfjg, ((Integer) zzba.zzc().zza(zzbep.zzgC)).intValue(), ((Integer) zzba.zzc().zza(zzbep.zzgI)).intValue(), ((Integer) zzba.zzc().zza(zzbep.zzgK)).intValue(), (String) zzba.zzc().zza(zzbep.zzgM), (String) zzba.zzc().zza(zzbep.zzgE), (String) zzba.zzc().zza(zzbep.zzgG));
        } else if (zzfjg == zzfjg.Interstitial) {
            return new zzfjj(context, zzfjg, ((Integer) zzba.zzc().zza(zzbep.zzgD)).intValue(), ((Integer) zzba.zzc().zza(zzbep.zzgJ)).intValue(), ((Integer) zzba.zzc().zza(zzbep.zzgL)).intValue(), (String) zzba.zzc().zza(zzbep.zzgN), (String) zzba.zzc().zza(zzbep.zzgF), (String) zzba.zzc().zza(zzbep.zzgH));
        } else if (zzfjg != zzfjg.AppOpen) {
            return null;
        } else {
            return new zzfjj(context, zzfjg, ((Integer) zzba.zzc().zza(zzbep.zzgQ)).intValue(), ((Integer) zzba.zzc().zza(zzbep.zzgS)).intValue(), ((Integer) zzba.zzc().zza(zzbep.zzgT)).intValue(), (String) zzba.zzc().zza(zzbep.zzgO), (String) zzba.zzc().zza(zzbep.zzgP), (String) zzba.zzc().zza(zzbep.zzgR));
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zzi;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        SafeParcelWriter.writeInt(parcel, 2, this.zzc);
        SafeParcelWriter.writeInt(parcel, 3, this.zzd);
        SafeParcelWriter.writeInt(parcel, 4, this.zze);
        SafeParcelWriter.writeString(parcel, 5, this.zzf, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzj);
        SafeParcelWriter.writeInt(parcel, 7, this.zzk);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    private zzfjj(@Nullable Context context, zzfjg zzfjg, int i, int i2, int i3, String str, String str2, String str3) {
        int i4;
        this.zzh = zzfjg.values();
        this.zzl = zzfjh.zza();
        this.zzm = zzfji.zza();
        this.zza = context;
        this.zzi = zzfjg.ordinal();
        this.zzb = zzfjg;
        this.zzc = i;
        this.zzd = i2;
        this.zze = i3;
        this.zzf = str;
        if ("oldest".equals(str2)) {
            i4 = 1;
        } else if (!"lru".equals(str2) && "lfu".equals(str2)) {
            i4 = 3;
        } else {
            i4 = 2;
        }
        this.zzg = i4;
        this.zzj = i4 - 1;
        "onAdClosed".equals(str3);
        this.zzk = 0;
    }
}
