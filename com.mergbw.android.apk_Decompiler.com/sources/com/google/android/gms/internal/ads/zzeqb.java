package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzeqb implements zzexv {
    private final boolean zza;

    public zzeqb(boolean z) {
        this.zza = z;
    }

    public final /* bridge */ /* synthetic */ void zzj(Object obj) {
        String str;
        Bundle bundle = (Bundle) obj;
        if (true != this.zza) {
            str = SessionDescription.SUPPORTED_SDP_VERSION;
        } else {
            str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        bundle.putString("adid_p", str);
    }
}
