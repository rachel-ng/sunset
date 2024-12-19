package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhli extends CustomTabsServiceConnection {
    private final WeakReference zza;

    public zzhli(zzbfm zzbfm) {
        this.zza = new WeakReference(zzbfm);
    }

    public final void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzbfm zzbfm = (zzbfm) this.zza.get();
        if (zzbfm != null) {
            zzbfm.zzc(customTabsClient);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzbfm zzbfm = (zzbfm) this.zza.get();
        if (zzbfm != null) {
            zzbfm.zzd();
        }
    }
}
