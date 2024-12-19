package com.google.android.gms.internal.ads;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.client.zzf;
import com.google.android.gms.ads.internal.zzj;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.nonagon.signalgeneration.zzp;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzdux {
    private final ConcurrentHashMap zza;
    private final zzccc zzb;
    private final zzfho zzc;
    private final String zzd;
    private final String zze;
    private final zzj zzf;
    private final Bundle zzg = new Bundle();
    private final Context zzh;

    public zzdux(Context context, zzdvh zzdvh, zzccc zzccc, zzfho zzfho, String str, String str2, zzj zzj) {
        ActivityManager.MemoryInfo zzc2;
        String str3;
        ConcurrentHashMap zzc3 = zzdvh.zzc();
        this.zza = zzc3;
        this.zzb = zzccc;
        this.zzc = zzfho;
        this.zzd = str;
        this.zze = str2;
        this.zzf = zzj;
        this.zzh = context;
        zzc3.put(FirebaseAnalytics.Param.AD_FORMAT, str2.toUpperCase(Locale.ROOT));
        boolean booleanValue = ((Boolean) zzba.zzc().zza(zzbep.zzjz)).booleanValue();
        String str4 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        if (booleanValue) {
            int zzj2 = zzj.zzj();
            int i = zzj2 - 1;
            if (zzj2 != 0) {
                if (i != 0) {
                    str3 = i != 1 ? "na" : ExifInterface.GPS_MEASUREMENT_2D;
                } else {
                    str3 = str4;
                }
                zzc3.put("asv", str3);
            } else {
                throw null;
            }
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzcd)).booleanValue()) {
            Runtime runtime = Runtime.getRuntime();
            zzc("rt_f", String.valueOf(runtime.freeMemory()));
            zzc("rt_m", String.valueOf(runtime.maxMemory()));
            zzc("rt_t", String.valueOf(runtime.totalMemory()));
            zzc("wv_c", String.valueOf(zzu.zzo().zzb()));
            if (((Boolean) zzba.zzc().zza(zzbep.zzcf)).booleanValue() && (zzc2 = zzf.zzc(context)) != null) {
                zzc("mem_avl", String.valueOf(zzc2.availMem));
                zzc("mem_tt", String.valueOf(zzc2.totalMem));
                zzc("low_m", true != zzc2.lowMemory ? SessionDescription.SUPPORTED_SDP_VERSION : str4);
            }
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzhj)).booleanValue()) {
            int zzf2 = zzp.zzf(zzfho) - 1;
            if (zzf2 != 0) {
                if (zzf2 == 1) {
                    zzc3.put("request_id", str);
                    zzc3.put("se", "query_g");
                } else if (zzf2 == 2) {
                    zzc3.put("se", "r_adinfo");
                } else if (zzf2 != 3) {
                    zzc3.put("se", "r_both");
                } else {
                    zzc3.put("se", "r_adstring");
                }
                zzc3.put("scar", "true");
                zzc("ragent", zzfho.zzd.zzp);
                zzc("rtype", zzp.zzb(zzp.zzc(zzfho.zzd)));
                return;
            }
            zzc3.put("request_id", str);
            zzc3.put("scar", "false");
        }
    }

    public final Bundle zza() {
        return this.zzg;
    }

    public final Map zzb() {
        return this.zza;
    }

    public final void zzc(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.zza.put(str, str2);
        }
    }

    public final void zzd(zzfhf zzfhf) {
        String str;
        if (!zzfhf.zzb.zza.isEmpty()) {
            zzfgt zzfgt = (zzfgt) zzfhf.zzb.zza.get(0);
            zzc(FirebaseAnalytics.Param.AD_FORMAT, zzfgt.zza(zzfgt.zzb));
            if (zzfgt.zzb == 6) {
                ConcurrentHashMap concurrentHashMap = this.zza;
                if (true != this.zzb.zzm()) {
                    str = SessionDescription.SUPPORTED_SDP_VERSION;
                } else {
                    str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                }
                concurrentHashMap.put("as", str);
            }
        }
        zzc("gqi", zzfhf.zzb.zzb.zzb);
    }

    public final void zze(Bundle bundle) {
        if (bundle != null) {
            if (bundle.containsKey("cnt")) {
                zzc("network_coarse", Integer.toString(bundle.getInt("cnt")));
            }
            if (bundle.containsKey("gnt")) {
                zzc("network_fine", Integer.toString(bundle.getInt("gnt")));
            }
        }
    }
}
