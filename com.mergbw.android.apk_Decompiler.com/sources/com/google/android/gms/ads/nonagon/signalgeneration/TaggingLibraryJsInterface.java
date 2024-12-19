package com.google.android.gms.ads.nonagon.signalgeneration;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.query.QueryInfo;
import com.google.android.gms.ads.query.QueryInfoGenerationCallback;
import com.google.android.gms.internal.ads.zzaxd;
import com.google.android.gms.internal.ads.zzaxe;
import com.google.android.gms.internal.ads.zzbep;
import com.google.android.gms.internal.ads.zzbgp;
import com.google.android.gms.internal.ads.zzcci;
import com.google.android.gms.internal.ads.zzdux;
import com.google.android.gms.internal.ads.zzdvh;
import com.google.android.gms.internal.ads.zzfhs;
import com.google.android.gms.internal.ads.zzfmn;
import com.google.android.gms.internal.ads.zzfoe;
import com.google.android.gms.internal.ads.zzgge;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class TaggingLibraryJsInterface {
    private final Context zza;
    /* access modifiers changed from: private */
    public final WebView zzb;
    private final zzaxd zzc;
    private final zzfhs zzd;
    private final int zze;
    private final zzdvh zzf;
    private final boolean zzg;
    /* access modifiers changed from: private */
    public final zzgge zzh = zzcci.zze;
    private final zzfoe zzi;
    private final zze zzj;

    TaggingLibraryJsInterface(WebView webView, zzaxd zzaxd, zzdvh zzdvh, zzfoe zzfoe, zzfhs zzfhs, zze zze2) {
        this.zzb = webView;
        Context context = webView.getContext();
        this.zza = context;
        this.zzc = zzaxd;
        this.zzf = zzdvh;
        zzbep.zza(context);
        this.zze = ((Integer) zzba.zzc().zza(zzbep.zzjC)).intValue();
        this.zzg = ((Boolean) zzba.zzc().zza(zzbep.zzjD)).booleanValue();
        this.zzi = zzfoe;
        this.zzd = zzfhs;
        this.zzj = zze2;
    }

    @JavascriptInterface
    public String getClickSignals(String str) {
        try {
            long currentTimeMillis = zzu.zzB().currentTimeMillis();
            String zze2 = this.zzc.zzc().zze(this.zza, str, this.zzb);
            if (this.zzg) {
                long currentTimeMillis2 = zzu.zzB().currentTimeMillis() - currentTimeMillis;
                zzp.zzd(this.zzf, (zzdux) null, "csg", new Pair("clat", String.valueOf(currentTimeMillis2)));
            }
            return zze2;
        } catch (RuntimeException e) {
            zzm.zzh("Exception getting click signals. ", e);
            zzu.zzo().zzw(e, "TaggingLibraryJsInterface.getClickSignals");
            return "";
        }
    }

    @JavascriptInterface
    public String getClickSignalsWithTimeout(String str, int i) {
        if (i <= 0) {
            zzm.zzg("Invalid timeout for getting click signals. Timeout=" + i);
            return "";
        }
        int min = Math.min(i, this.zze);
        try {
            return (String) zzcci.zza.zzb(new zzba(this, str)).get((long) min, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzm.zzh("Exception getting click signals with timeout. ", e);
            zzu.zzo().zzw(e, "TaggingLibraryJsInterface.getClickSignalsWithTimeout");
            if (e instanceof TimeoutException) {
                return "17";
            }
            return "";
        }
    }

    @JavascriptInterface
    public String getQueryInfo() {
        zzu.zzp();
        String uuid = UUID.randomUUID().toString();
        Bundle bundle = new Bundle();
        bundle.putString("query_info_type", "requester_type_6");
        zzbe zzbe = new zzbe(this, uuid);
        if (((Boolean) zzbgp.zza.zze()).booleanValue()) {
            this.zzj.zzg(this.zzb, zzbe);
        } else {
            if (((Boolean) zzba.zzc().zza(zzbep.zzjF)).booleanValue()) {
                this.zzh.execute(new zzbb(this, bundle, zzbe));
            } else {
                QueryInfo.generate(this.zza, AdFormat.BANNER, ((AdRequest.Builder) new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, bundle)).build(), zzbe);
            }
        }
        return uuid;
    }

    @JavascriptInterface
    public String getViewSignals() {
        try {
            long currentTimeMillis = zzu.zzB().currentTimeMillis();
            String zzh2 = this.zzc.zzc().zzh(this.zza, this.zzb, (Activity) null);
            if (this.zzg) {
                long currentTimeMillis2 = zzu.zzB().currentTimeMillis() - currentTimeMillis;
                zzp.zzd(this.zzf, (zzdux) null, "vsg", new Pair("vlat", String.valueOf(currentTimeMillis2)));
            }
            return zzh2;
        } catch (RuntimeException e) {
            zzm.zzh("Exception getting view signals. ", e);
            zzu.zzo().zzw(e, "TaggingLibraryJsInterface.getViewSignals");
            return "";
        }
    }

    @JavascriptInterface
    public String getViewSignalsWithTimeout(int i) {
        if (i <= 0) {
            zzm.zzg("Invalid timeout for getting view signals. Timeout=" + i);
            return "";
        }
        int min = Math.min(i, this.zze);
        try {
            return (String) zzcci.zza.zzb(new zzay(this)).get((long) min, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzm.zzh("Exception getting view signals with timeout. ", e);
            zzu.zzo().zzw(e, "TaggingLibraryJsInterface.getViewSignalsWithTimeout");
            if (e instanceof TimeoutException) {
                return "17";
            }
            return "";
        }
    }

    @JavascriptInterface
    public void recordClick(String str) {
        if (((Boolean) zzba.zzc().zza(zzbep.zzjH)).booleanValue() && !TextUtils.isEmpty(str)) {
            zzcci.zza.execute(new zzaz(this, str));
        }
    }

    @JavascriptInterface
    public void reportTouchEvent(String str) {
        int i;
        int i2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i3 = jSONObject.getInt("x");
            int i4 = jSONObject.getInt("y");
            int i5 = jSONObject.getInt("duration_ms");
            float f = (float) jSONObject.getDouble("force");
            int i6 = jSONObject.getInt(SessionDescription.ATTR_TYPE);
            if (i6 != 0) {
                int i7 = 1;
                if (i6 != 1) {
                    i7 = 2;
                    if (i6 != 2) {
                        i7 = 3;
                        if (i6 != 3) {
                            i2 = -1;
                        }
                    }
                }
                i = i7;
                this.zzc.zzd(MotionEvent.obtain(0, (long) i5, i, (float) i3, (float) i4, f, 1.0f, 0, 1.0f, 1.0f, 0, 0));
            }
            i2 = 0;
            i = i2;
            try {
                this.zzc.zzd(MotionEvent.obtain(0, (long) i5, i, (float) i3, (float) i4, f, 1.0f, 0, 1.0f, 1.0f, 0, 0));
            } catch (RuntimeException | JSONException e) {
                e = e;
                zzm.zzh("Failed to parse the touch string. ", e);
                zzu.zzo().zzw(e, "TaggingLibraryJsInterface.reportTouchEvent");
            }
        } catch (RuntimeException | JSONException e2) {
            e = e2;
            zzm.zzh("Failed to parse the touch string. ", e);
            zzu.zzo().zzw(e, "TaggingLibraryJsInterface.reportTouchEvent");
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Bundle bundle, QueryInfoGenerationCallback queryInfoGenerationCallback) {
        CookieManager zza2 = zzu.zzq().zza(this.zza);
        bundle.putBoolean("accept_3p_cookie", zza2 != null ? zza2.acceptThirdPartyCookies(this.zzb) : false);
        QueryInfo.generate(this.zza, AdFormat.BANNER, ((AdRequest.Builder) new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, bundle)).build(), queryInfoGenerationCallback);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(String str) {
        zzfhs zzfhs;
        Uri parse = Uri.parse(str);
        try {
            if (!((Boolean) zzba.zzc().zza(zzbep.zzlW)).booleanValue() || (zzfhs = this.zzd) == null) {
                parse = this.zzc.zza(parse, this.zza, this.zzb, (Activity) null);
            } else {
                parse = zzfhs.zza(parse, this.zza, this.zzb, (Activity) null);
            }
        } catch (zzaxe e) {
            zzm.zzf("Failed to append the click signal to URL: ", e);
            zzu.zzo().zzw(e, "TaggingLibraryJsInterface.recordClick");
        }
        this.zzi.zzc(parse.toString(), (zzfmn) null);
    }
}
