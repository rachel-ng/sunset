package com.google.android.gms.ads.internal.util;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.ads.zzbdv;
import com.google.android.gms.internal.ads.zzbep;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzw extends zzv {
    public final Intent zzf(Activity activity) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", activity.getPackageName());
        return intent;
    }

    public final zzbdv.zzq zzg(Context context, TelephonyManager telephonyManager) {
        zzu.zzp();
        if (zzt.zzA(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return telephonyManager.isDataEnabled() ? zzbdv.zzq.ENUM_TRUE : zzbdv.zzq.ENUM_FALSE;
        }
        return zzbdv.zzq.ENUM_FALSE;
    }

    public final void zzh(Context context, String str, String str2) {
        Chip$$ExternalSyntheticApiModelOutline0.m();
        NotificationChannel m = ComponentDialog$$ExternalSyntheticApiModelOutline0.m("offline_notification_channel", (CharSequence) "AdMob Offline Notifications", ((Integer) zzba.zzc().zza(zzbep.zziC)).intValue());
        m.setShowBadge(false);
        ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(m);
    }

    public final boolean zzi(Context context, String str) {
        NotificationChannel m = ((NotificationManager) context.getSystemService(NotificationManager.class)).getNotificationChannel("offline_notification_channel");
        if (m != null && m.getImportance() == 0) {
            return true;
        }
        return false;
    }
}
