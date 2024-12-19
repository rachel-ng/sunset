package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.alibaba.android.arouter.utils.Consts;
import com.google.android.gms.ads.internal.client.zzba;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzele {
    final String zza;
    final String zzb;
    int zzc;
    long zzd;
    final Integer zze;

    zzele(String str, String str2, int i, long j, Integer num) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = i;
        this.zzd = j;
        this.zze = num;
    }

    public final String toString() {
        String str = this.zza + Consts.DOT + this.zzc + Consts.DOT + this.zzd;
        if (!TextUtils.isEmpty(this.zzb)) {
            str = str + Consts.DOT + this.zzb;
        }
        if (!((Boolean) zzba.zzc().zza(zzbep.zzbD)).booleanValue() || this.zze == null || TextUtils.isEmpty(this.zzb)) {
            return str;
        }
        return str + Consts.DOT + this.zze;
    }
}
