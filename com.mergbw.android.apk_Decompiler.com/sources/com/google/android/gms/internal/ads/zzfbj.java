package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zzba;
import java.util.HashSet;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfbj implements zzhkp {
    public static zzexz zza(Context context, zzcbm zzcbm, zzcbn zzcbn, Object obj, zzeyy zzeyy, zzfap zzfap, zzhkj zzhkj, zzhkj zzhkj2, zzhkj zzhkj3, zzhkj zzhkj4, zzhkj zzhkj5, zzhkj zzhkj6, zzhkj zzhkj7, zzhkj zzhkj8, zzhkj zzhkj9, Executor executor, zzfmn zzfmn, zzdvc zzdvc) {
        HashSet hashSet = new HashSet();
        hashSet.add((zzfai) obj);
        zzeyy zzeyy2 = zzeyy;
        hashSet.add(zzeyy);
        zzfap zzfap2 = zzfap;
        hashSet.add(zzfap);
        if (((Boolean) zzba.zzc().zza(zzbep.zzfS)).booleanValue()) {
            hashSet.add((zzexw) zzhkj.zzb());
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzfT)).booleanValue()) {
            hashSet.add((zzexw) zzhkj2.zzb());
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzfU)).booleanValue()) {
            hashSet.add((zzexw) zzhkj3.zzb());
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzfV)).booleanValue()) {
            hashSet.add((zzexw) zzhkj4.zzb());
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzfZ)).booleanValue()) {
            hashSet.add((zzexw) zzhkj6.zzb());
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzga)).booleanValue()) {
            hashSet.add((zzexw) zzhkj7.zzb());
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzcQ)).booleanValue()) {
            hashSet.add((zzexw) zzhkj9.zzb());
        }
        return new zzexz(context, executor, hashSet, zzfmn, zzdvc);
    }

    public final /* bridge */ /* synthetic */ Object zzb() {
        throw null;
    }
}
