package com.google.android.gms.internal.ads;

import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.client.zzba;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzdzl implements Callable {
    public final /* synthetic */ CookieManager zza;

    public /* synthetic */ zzdzl(CookieManager cookieManager) {
        this.zza = cookieManager;
    }

    public final Object call() {
        CookieManager cookieManager = this.zza;
        if (cookieManager == null) {
            return "";
        }
        return cookieManager.getCookie((String) zzba.zzc().zza(zzbep.zzaO));
    }
}
