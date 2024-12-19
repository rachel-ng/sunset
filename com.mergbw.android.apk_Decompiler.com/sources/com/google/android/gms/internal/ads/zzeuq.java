package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzeuq implements zzexv {
    public final String zza;
    public final boolean zzb;

    public zzeuq(String str, boolean z) {
        this.zza = str;
        this.zzb = z;
    }

    public final /* bridge */ /* synthetic */ void zzj(Object obj) {
        Bundle bundle = (Bundle) obj;
        bundle.putString("gct", this.zza);
        if (this.zzb) {
            bundle.putString("de", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
    }
}
