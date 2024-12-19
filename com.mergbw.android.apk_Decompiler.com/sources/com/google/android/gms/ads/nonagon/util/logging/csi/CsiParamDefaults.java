package com.google.android.gms.ads.nonagon.util.logging.csi;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.zzt;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.ads.zzbeg;
import com.google.android.gms.internal.ads.zzbep;
import com.google.android.gms.internal.ads.zzfyv;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class CsiParamDefaults {
    private final Context zza;
    private final String zzb;
    private final String zzc;

    public CsiParamDefaults(Context context, VersionInfoParcel versionInfoParcel) {
        this.zza = context;
        this.zzb = context.getPackageName();
        this.zzc = versionInfoParcel.afmaVersion;
    }

    public void set(Map<String, String> map) {
        map.put("s", "gmob_sdk");
        map.put("v", ExifInterface.GPS_MEASUREMENT_3D);
        map.put("os", Build.VERSION.RELEASE);
        map.put("api_v", Build.VERSION.SDK);
        zzu.zzp();
        map.put("device", zzt.zzr());
        map.put("app", this.zzb);
        zzu.zzp();
        boolean zzE = zzt.zzE(this.zza);
        String str = SessionDescription.SUPPORTED_SDP_VERSION;
        map.put("is_lite_sdk", true != zzE ? str : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        zzbeg zzbeg = zzbep.zza;
        List zzb2 = zzba.zza().zzb();
        if (((Boolean) zzba.zzc().zza(zzbep.zzhd)).booleanValue()) {
            zzb2.addAll(zzu.zzo().zzi().zzh().zzd());
        }
        map.put("e", TextUtils.join(",", zzb2));
        map.put(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, this.zzc);
        if (((Boolean) zzba.zzc().zza(zzbep.zzlp)).booleanValue()) {
            zzu.zzp();
            if (true == zzt.zzB(this.zza)) {
                str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            map.put("is_bstar", str);
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzju)).booleanValue()) {
            if (((Boolean) zzba.zzc().zza(zzbep.zzck)).booleanValue()) {
                map.put("plugin", zzfyv.zzc(zzu.zzo().zzn()));
            }
        }
    }
}
