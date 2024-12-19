package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfro {
    public static zzftb zza(Context context, int i, zzazw zzazw, String str, String str2, String str3, zzfre zzfre) {
        return new zzfrn(context, 1, zzazw, str, str2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, zzfre).zzb(50000);
    }
}
