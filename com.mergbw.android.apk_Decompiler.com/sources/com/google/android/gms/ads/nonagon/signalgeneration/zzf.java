package com.google.android.gms.ads.nonagon.signalgeneration;

import android.util.Pair;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.query.QueryInfo;
import com.google.android.gms.ads.query.QueryInfoGenerationCallback;
import com.google.android.gms.internal.ads.zzbep;
import com.google.android.gms.internal.ads.zzdux;
import com.google.android.gms.internal.ads.zzdvh;
import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzf extends QueryInfoGenerationCallback {
    private final zze zza;
    private final zzdvh zzb;
    private final boolean zzc;
    private final int zzd;
    private final long zze = zzu.zzB().currentTimeMillis();
    private final Boolean zzf;

    public zzf(zze zze2, boolean z, int i, Boolean bool, zzdvh zzdvh) {
        this.zza = zze2;
        this.zzc = z;
        this.zzd = i;
        this.zzf = bool;
        this.zzb = zzdvh;
    }

    private static long zza() {
        return zzu.zzB().currentTimeMillis() + ((Long) zzba.zzc().zza(zzbep.zzjR)).longValue();
    }

    private final long zzb() {
        return zzu.zzB().currentTimeMillis() - this.zze;
    }

    public final void onFailure(String str) {
        String str2;
        Pair pair = new Pair("sgf_reason", str);
        Pair pair2 = new Pair("se", "query_g");
        Pair pair3 = new Pair(FirebaseAnalytics.Param.AD_FORMAT, AdFormat.BANNER.name());
        Pair pair4 = new Pair("rtype", Integer.toString(6));
        Pair pair5 = new Pair("scar", "true");
        Pair pair6 = new Pair("lat_ms", Long.toString(zzb()));
        Pair pair7 = new Pair("sgpc_rn", Integer.toString(this.zzd));
        Pair pair8 = new Pair("sgpc_lsu", String.valueOf(this.zzf));
        if (true != this.zzc) {
            str2 = SessionDescription.SUPPORTED_SDP_VERSION;
        } else {
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        zzp.zzd(this.zzb, (zzdux) null, "sgpcf", pair, pair2, pair3, pair4, pair5, pair6, pair7, pair8, new Pair("tpc", str2));
        this.zza.zzf(this.zzc, new zzg((QueryInfo) null, str, zza(), this.zzd));
    }

    public final void onSuccess(QueryInfo queryInfo) {
        String str;
        Pair pair = new Pair("se", "query_g");
        Pair pair2 = new Pair(FirebaseAnalytics.Param.AD_FORMAT, AdFormat.BANNER.name());
        Pair pair3 = new Pair("rtype", Integer.toString(6));
        Pair pair4 = new Pair("scar", "true");
        Pair pair5 = new Pair("lat_ms", Long.toString(zzb()));
        Pair pair6 = new Pair("sgpc_rn", Integer.toString(this.zzd));
        Pair pair7 = new Pair("sgpc_lsu", String.valueOf(this.zzf));
        if (true != this.zzc) {
            str = SessionDescription.SUPPORTED_SDP_VERSION;
        } else {
            str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        zzp.zzd(this.zzb, (zzdux) null, "sgpcs", pair, pair2, pair3, pair4, pair5, pair6, pair7, new Pair("tpc", str));
        this.zza.zzf(this.zzc, new zzg(queryInfo, "", zza(), this.zzd));
    }
}
