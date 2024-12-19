package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzht implements zzfyh {
    public final boolean zza(Object obj) {
        String str = (String) obj;
        if (str == null) {
            return false;
        }
        String zza = zzfxm.zza(str);
        if (!TextUtils.isEmpty(zza)) {
            return (!zza.contains("text") || zza.contains(MimeTypes.TEXT_VTT)) && !zza.contains("html") && !zza.contains("xml");
        }
        return false;
    }
}
