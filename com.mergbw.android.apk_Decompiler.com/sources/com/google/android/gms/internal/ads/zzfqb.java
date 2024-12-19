package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.IntentFilter;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfqb {
    /* access modifiers changed from: private */
    public static int zza = 2;

    public static void zza(Context context) {
        context.registerReceiver(new zzfqa(), new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
    }

    public static int zzb() {
        if (zzfpx.zza() != zzfop.CTV) {
            return 2;
        }
        return zza;
    }
}
