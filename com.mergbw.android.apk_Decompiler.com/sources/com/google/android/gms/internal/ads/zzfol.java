package com.google.android.gms.internal.ads;

import android.webkit.WebView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfol {
    private final zzfow zza;
    private final WebView zzb;
    private final List zzc = new ArrayList();
    private final Map zzd = new HashMap();
    private final String zze;
    private final String zzf;
    private final zzfom zzg;

    private zzfol(zzfow zzfow, WebView webView, String str, List list, String str2, String str3, zzfom zzfom) {
        this.zza = zzfow;
        this.zzb = webView;
        this.zzg = zzfom;
        this.zzf = str2;
        this.zze = str3;
    }

    public static zzfol zzb(zzfow zzfow, WebView webView, String str, String str2) {
        if (str2 != null) {
            zzfqd.zzd(str2, 256, "CustomReferenceData is greater than 256 characters");
        }
        return new zzfol(zzfow, webView, (String) null, (List) null, str, str2, zzfom.HTML);
    }

    public static zzfol zzc(zzfow zzfow, WebView webView, String str, String str2) {
        zzfqd.zzd("", 256, "CustomReferenceData is greater than 256 characters");
        return new zzfol(zzfow, webView, (String) null, (List) null, str, "", zzfom.JAVASCRIPT);
    }

    public final WebView zza() {
        return this.zzb;
    }

    public final zzfom zzd() {
        return this.zzg;
    }

    public final zzfow zze() {
        return this.zza;
    }

    public final String zzf() {
        return this.zzf;
    }

    public final String zzg() {
        return this.zze;
    }

    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public final Map zzi() {
        return Collections.unmodifiableMap(this.zzd);
    }
}
