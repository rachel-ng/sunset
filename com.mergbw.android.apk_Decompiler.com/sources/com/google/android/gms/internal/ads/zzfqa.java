package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfqa extends BroadcastReceiver {
    zzfqa() {
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent.getAction() == "android.media.action.HDMI_AUDIO_PLUG") {
            int intExtra = intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", -1);
            if (intExtra == 0) {
                zzfqb.zza = 1;
            } else if (intExtra == 1) {
                zzfqb.zza = 2;
            }
        }
    }
}
