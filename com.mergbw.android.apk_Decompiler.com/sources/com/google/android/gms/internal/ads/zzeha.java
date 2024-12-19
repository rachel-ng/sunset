package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.zzu;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzeha implements zzehb {
    static /* synthetic */ zzehg zzc(String str, String str2, String str3, zzehc zzehc, String str4, WebView webView, String str5, String str6, zzehd zzehd) {
        zzfow zza = zzfow.zza("Google", str2);
        zzfov zzp = zzp("javascript");
        zzfoo zzn = zzn(zzehc.toString());
        if (zzp == zzfov.NONE) {
            zzm.zzj("Omid html session error; Unable to parse impression owner: javascript");
            return null;
        } else if (zzn == null) {
            zzm.zzj("Omid html session error; Unable to parse creative type: ".concat(String.valueOf(String.valueOf(zzehc))));
            return null;
        } else {
            zzfov zzp2 = zzp(str4);
            if (zzn == zzfoo.VIDEO && zzp2 == zzfov.NONE) {
                zzm.zzj("Omid html session error; Video events owner unknown for video creative: ".concat(String.valueOf(str4)));
                return null;
            }
            zzfol zzb = zzfol.zzb(zza, webView, str5, "");
            return new zzehg(zzfoj.zza(zzfok.zza(zzn, zzo(zzehd.toString()), zzp, zzp2, true), zzb), zzb);
        }
    }

    static /* synthetic */ zzehg zzd(String str, String str2, String str3, String str4, zzehc zzehc, WebView webView, String str5, String str6, zzehd zzehd) {
        zzfow zza = zzfow.zza(str, str2);
        zzfov zzp = zzp("javascript");
        zzfov zzp2 = zzp(str4);
        zzfoo zzn = zzn(zzehc.toString());
        if (zzp == zzfov.NONE) {
            zzm.zzj("Omid js session error; Unable to parse impression owner: javascript");
            return null;
        } else if (zzn == null) {
            zzm.zzj("Omid js session error; Unable to parse creative type: ".concat(String.valueOf(String.valueOf(zzehc))));
            return null;
        } else if (zzn == zzfoo.VIDEO && zzp2 == zzfov.NONE) {
            zzm.zzj("Omid js session error; Video events owner unknown for video creative: ".concat(String.valueOf(str4)));
            return null;
        } else {
            zzfol zzc = zzfol.zzc(zza, webView, str5, "");
            return new zzehg(zzfoj.zza(zzfok.zza(zzn, zzo(zzehd.toString()), zzp, zzp2, true), zzc), zzc);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.ads.zzfoo zzn(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = -382745961(0xffffffffe92fc297, float:-1.3280059E25)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x002a
            r1 = 112202875(0x6b0147b, float:6.6233935E-35)
            if (r0 == r1) goto L_0x0020
            r1 = 714893483(0x2a9c68ab, float:2.7783795E-13)
            if (r0 == r1) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r0 = "nativeDisplay"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0034
            r4 = r3
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "video"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0034
            r4 = r2
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "htmlDisplay"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0034
            r4 = 0
            goto L_0x0035
        L_0x0034:
            r4 = -1
        L_0x0035:
            if (r4 == 0) goto L_0x0043
            if (r4 == r3) goto L_0x0040
            if (r4 == r2) goto L_0x003d
            r4 = 0
            return r4
        L_0x003d:
            com.google.android.gms.internal.ads.zzfoo r4 = com.google.android.gms.internal.ads.zzfoo.VIDEO
            return r4
        L_0x0040:
            com.google.android.gms.internal.ads.zzfoo r4 = com.google.android.gms.internal.ads.zzfoo.NATIVE_DISPLAY
            return r4
        L_0x0043:
            com.google.android.gms.internal.ads.zzfoo r4 = com.google.android.gms.internal.ads.zzfoo.HTML_DISPLAY
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeha.zzn(java.lang.String):com.google.android.gms.internal.ads.zzfoo");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.ads.zzfor zzo(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = -1104128070(0xffffffffbe3057ba, float:-0.17220965)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x002a
            r1 = 1318088141(0x4e906dcd, float:1.2115575E9)
            if (r0 == r1) goto L_0x0020
            r1 = 1988248512(0x768243c0, float:1.3210405E33)
            if (r0 == r1) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r0 = "onePixel"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0034
            r4 = r2
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "definedByJavascript"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0034
            r4 = r3
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "beginToRender"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0034
            r4 = 0
            goto L_0x0035
        L_0x0034:
            r4 = -1
        L_0x0035:
            if (r4 == 0) goto L_0x0044
            if (r4 == r3) goto L_0x0041
            if (r4 == r2) goto L_0x003e
            com.google.android.gms.internal.ads.zzfor r4 = com.google.android.gms.internal.ads.zzfor.UNSPECIFIED
            return r4
        L_0x003e:
            com.google.android.gms.internal.ads.zzfor r4 = com.google.android.gms.internal.ads.zzfor.ONE_PIXEL
            return r4
        L_0x0041:
            com.google.android.gms.internal.ads.zzfor r4 = com.google.android.gms.internal.ads.zzfor.DEFINED_BY_JAVASCRIPT
            return r4
        L_0x0044:
            com.google.android.gms.internal.ads.zzfor r4 = com.google.android.gms.internal.ads.zzfor.BEGIN_TO_RENDER
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeha.zzo(java.lang.String):com.google.android.gms.internal.ads.zzfor");
    }

    private static zzfov zzp(String str) {
        if ("native".equals(str)) {
            return zzfov.NATIVE;
        }
        if ("javascript".equals(str)) {
            return zzfov.JAVASCRIPT;
        }
        return zzfov.NONE;
    }

    private static final Object zzq(zzegz zzegz) {
        try {
            return zzegz.zza();
        } catch (RuntimeException e) {
            zzu.zzo().zzv(e, "omid exception");
            return null;
        }
    }

    private static final void zzr(Runnable runnable) {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            zzu.zzo().zzv(e, "omid exception");
        }
    }

    public final zzehg zza(String str, WebView webView, String str2, String str3, String str4, zzehd zzehd, zzehc zzehc, String str5) {
        if (!((Boolean) zzba.zzc().zza(zzbep.zzeZ)).booleanValue() || !zzfoh.zzb()) {
            return null;
        }
        return (zzehg) zzq(new zzegp("Google", str, "javascript", zzehc, str4, webView, str5, "", zzehd));
    }

    public final zzehg zzb(String str, WebView webView, String str2, String str3, String str4, String str5, zzehd zzehd, zzehc zzehc, String str6) {
        if (!((Boolean) zzba.zzc().zza(zzbep.zzeZ)).booleanValue() || !zzfoh.zzb()) {
            return null;
        }
        return (zzehg) zzq(new zzegs(str5, str, "javascript", str4, zzehc, webView, str6, "", zzehd));
    }

    public final zzfou zze(VersionInfoParcel versionInfoParcel, WebView webView, boolean z) {
        return (zzfou) zzq(new zzegx(versionInfoParcel, webView, true));
    }

    public final String zzf(Context context) {
        if (!((Boolean) zzba.zzc().zza(zzbep.zzeZ)).booleanValue()) {
            return null;
        }
        return (String) zzq(new zzegv());
    }

    public final void zzg(zzfoj zzfoj, View view) {
        zzr(new zzego(zzfoj, view));
    }

    public final void zzh(zzfou zzfou, View view) {
        zzr(new zzegu(zzfou, view));
    }

    public final void zzi(zzfoj zzfoj) {
        zzr(new zzegy(zzfoj));
    }

    public final void zzj(zzfoj zzfoj, View view) {
        zzr(new zzegq(zzfoj, view));
    }

    public final void zzk(zzfoj zzfoj) {
        if (((Boolean) zzba.zzc().zza(zzbep.zzeZ)).booleanValue() && zzfoh.zzb()) {
            Objects.requireNonNull(zzfoj);
            zzr(new zzegr(zzfoj));
        }
    }

    public final boolean zzl(Context context) {
        if (!((Boolean) zzba.zzc().zza(zzbep.zzeZ)).booleanValue()) {
            zzm.zzj("Omid flag is disabled");
            return false;
        }
        Boolean bool = (Boolean) zzq(new zzegt(context));
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    public final void zzm(zzfou zzfou, zzchs zzchs) {
        zzr(new zzegw(zzfou, zzchs));
    }
}
