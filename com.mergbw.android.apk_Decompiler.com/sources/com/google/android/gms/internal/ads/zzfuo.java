package com.google.android.gms.internal.ads;

import java.net.URL;
import java.net.URLConnection;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final /* synthetic */ class zzfuo implements zzfup {
    public final /* synthetic */ URL zza;

    public /* synthetic */ zzfuo(URL url) {
        this.zza = url;
    }

    public final URLConnection zza() {
        return this.zza.openConnection();
    }
}
