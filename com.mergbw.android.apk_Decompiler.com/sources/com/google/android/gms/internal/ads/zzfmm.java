package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.nonagon.signalgeneration.zzp;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfmm {
    public static void zzb(ListenableFuture listenableFuture, zzfmn zzfmn, zzfmc zzfmc) {
        zzg(listenableFuture, zzfmn, zzfmc, false);
    }

    public static void zzc(ListenableFuture listenableFuture, zzfmn zzfmn, zzfmc zzfmc) {
        zzg(listenableFuture, zzfmn, zzfmc, true);
    }

    public static void zzd(ListenableFuture listenableFuture, zzfmn zzfmn, zzfmc zzfmc) {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            zzgft.zzr(zzgfk.zzu(listenableFuture), new zzfml(zzfmn, zzfmc), zzcci.zzf);
        }
    }

    public static void zze(ListenableFuture listenableFuture, zzfmc zzfmc) {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            zzgft.zzr(zzgfk.zzu(listenableFuture), new zzfmj(zzfmc), zzcci.zzf);
        }
    }

    public static boolean zzf(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.matches((String) zzba.zzc().zza(zzbep.zziO), str);
    }

    private static void zzg(ListenableFuture listenableFuture, zzfmn zzfmn, zzfmc zzfmc, boolean z) {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            zzgft.zzr(zzgfk.zzu(listenableFuture), new zzfmk(zzfmn, zzfmc, z), zzcci.zzf);
        }
    }

    public static zzfmu zza(zzfho zzfho) {
        int zzf = zzp.zzf(zzfho) - 1;
        if (zzf == 0 || zzf == 1) {
            return zzfmu.CUI_NAME_ADREQUEST;
        }
        return zzfmu.CUI_NAME_SCAR_RENDERING;
    }
}
