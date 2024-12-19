package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.webkit.WebSettings;
import androidx.browser.trusted.sharing.ShareTarget;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Executor;
import org.apache.commons.math3.optimization.direct.CMAESOptimizer;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
final class zzu {
    private final Application zza;
    private final zzab zzb;
    private final Handler zzc;
    private final Executor zzd;
    private final zzap zze;
    private final zzbn zzf;
    private final zzl zzg;
    private final zzx zzh;
    private final zze zzi;

    zzu(Application application, zzab zzab, Handler handler, Executor executor, zzap zzap, zzbn zzbn, zzl zzl, zzx zzx, zze zze2) {
        this.zza = application;
        this.zzb = zzab;
        this.zzc = handler;
        this.zzd = executor;
        this.zze = zzap;
        this.zzf = zzbn;
        this.zzg = zzl;
        this.zzh = zzx;
        this.zzi = zze2;
    }

    private final zzck zzd(zzci zzci) throws zzg {
        JsonWriter jsonWriter;
        JsonReader jsonReader;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://fundingchoicesmessages.google.com/a/consent").openConnection();
            httpURLConnection.setRequestProperty("User-Agent", WebSettings.getDefaultUserAgent(this.zza));
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(CMAESOptimizer.DEFAULT_MAXITERATIONS);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod(ShareTarget.METHOD_POST);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
            try {
                jsonWriter = new JsonWriter(outputStreamWriter);
                jsonWriter.beginObject();
                String str = zzci.zza;
                if (str != null) {
                    jsonWriter.name("admob_app_id");
                    jsonWriter.value(str);
                }
                zzce zzce = zzci.zzb;
                if (zzce != null) {
                    jsonWriter.name("device_info");
                    jsonWriter.beginObject();
                    int i = zzce.zzc;
                    if (i != 1) {
                        jsonWriter.name("os_type");
                        int i2 = i - 1;
                        if (i2 == 0) {
                            jsonWriter.value("UNKNOWN");
                        } else if (i2 == 1) {
                            jsonWriter.value("ANDROID");
                        }
                    }
                    String str2 = zzce.zza;
                    if (str2 != null) {
                        jsonWriter.name("model");
                        jsonWriter.value(str2);
                    }
                    Integer num = zzce.zzb;
                    if (num != null) {
                        jsonWriter.name("android_api_level");
                        jsonWriter.value(num);
                    }
                    jsonWriter.endObject();
                }
                String str3 = zzci.zzc;
                if (str3 != null) {
                    jsonWriter.name("language_code");
                    jsonWriter.value(str3);
                }
                Boolean bool = zzci.zzd;
                if (bool != null) {
                    jsonWriter.name("tag_for_under_age_of_consent");
                    jsonWriter.value(bool.booleanValue());
                }
                Map map = zzci.zze;
                if (!map.isEmpty()) {
                    jsonWriter.name("stored_infos_map");
                    jsonWriter.beginObject();
                    for (Map.Entry entry : map.entrySet()) {
                        jsonWriter.name((String) entry.getKey());
                        jsonWriter.value((String) entry.getValue());
                    }
                    jsonWriter.endObject();
                }
                zzcg zzcg = zzci.zzf;
                if (zzcg != null) {
                    jsonWriter.name("screen_info");
                    jsonWriter.beginObject();
                    Integer num2 = zzcg.zza;
                    if (num2 != null) {
                        jsonWriter.name("width");
                        jsonWriter.value(num2);
                    }
                    Integer num3 = zzcg.zzb;
                    if (num3 != null) {
                        jsonWriter.name("height");
                        jsonWriter.value(num3);
                    }
                    Double d = zzcg.zzc;
                    if (d != null) {
                        jsonWriter.name("density");
                        jsonWriter.value(d);
                    }
                    List<zzcf> list = zzcg.zzd;
                    if (!list.isEmpty()) {
                        jsonWriter.name("screen_insets");
                        jsonWriter.beginArray();
                        for (zzcf zzcf : list) {
                            jsonWriter.beginObject();
                            Integer num4 = zzcf.zza;
                            if (num4 != null) {
                                jsonWriter.name("top");
                                jsonWriter.value(num4);
                            }
                            Integer num5 = zzcf.zzb;
                            if (num5 != null) {
                                jsonWriter.name(TtmlNode.LEFT);
                                jsonWriter.value(num5);
                            }
                            Integer num6 = zzcf.zzc;
                            if (num6 != null) {
                                jsonWriter.name(TtmlNode.RIGHT);
                                jsonWriter.value(num6);
                            }
                            Integer num7 = zzcf.zzd;
                            if (num7 != null) {
                                jsonWriter.name("bottom");
                                jsonWriter.value(num7);
                            }
                            jsonWriter.endObject();
                        }
                        jsonWriter.endArray();
                    }
                    jsonWriter.endObject();
                }
                zzcc zzcc = zzci.zzg;
                if (zzcc != null) {
                    jsonWriter.name("app_info");
                    jsonWriter.beginObject();
                    String str4 = zzcc.zza;
                    if (str4 != null) {
                        jsonWriter.name("package_name");
                        jsonWriter.value(str4);
                    }
                    String str5 = zzcc.zzb;
                    if (str5 != null) {
                        jsonWriter.name("publisher_display_name");
                        jsonWriter.value(str5);
                    }
                    String str6 = zzcc.zzc;
                    if (str6 != null) {
                        jsonWriter.name("version");
                        jsonWriter.value(str6);
                    }
                    jsonWriter.endObject();
                }
                zzch zzch = zzci.zzh;
                if (zzch != null) {
                    jsonWriter.name("sdk_info");
                    jsonWriter.beginObject();
                    String str7 = zzch.zza;
                    if (str7 != null) {
                        jsonWriter.name("version");
                        jsonWriter.value(str7);
                    }
                    jsonWriter.endObject();
                }
                List<zzcd> list2 = zzci.zzi;
                if (!list2.isEmpty()) {
                    jsonWriter.name("debug_params");
                    jsonWriter.beginArray();
                    for (zzcd ordinal : list2) {
                        int ordinal2 = ordinal.ordinal();
                        if (ordinal2 == 0) {
                            jsonWriter.value("DEBUG_PARAM_UNKNOWN");
                        } else if (ordinal2 == 1) {
                            jsonWriter.value("ALWAYS_SHOW");
                        } else if (ordinal2 == 2) {
                            jsonWriter.value("GEO_OVERRIDE_EEA");
                        } else if (ordinal2 == 3) {
                            jsonWriter.value("GEO_OVERRIDE_NON_EEA");
                        } else if (ordinal2 == 4) {
                            jsonWriter.value("PREVIEWING_DEBUG_MESSAGES");
                        }
                    }
                    jsonWriter.endArray();
                }
                jsonWriter.endObject();
                jsonWriter.close();
                outputStreamWriter.close();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    String headerField = httpURLConnection.getHeaderField("x-ump-using-header");
                    if (headerField != null) {
                        zzck zza2 = zzck.zza(new JsonReader(new StringReader(headerField)));
                        zza2.zza = new Scanner(httpURLConnection.getInputStream()).useDelimiter("\\A").next();
                        return zza2;
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    try {
                        bufferedReader.readLine();
                        jsonReader = new JsonReader(bufferedReader);
                        zzck zza3 = zzck.zza(jsonReader);
                        jsonReader.close();
                        bufferedReader.close();
                        return zza3;
                    } catch (Throwable th) {
                        bufferedReader.close();
                        throw th;
                    }
                } else {
                    throw new IOException("Http error code - " + responseCode + ".\n" + new Scanner(httpURLConnection.getErrorStream()).useDelimiter("\\A").next());
                }
                throw th;
                throw th;
            } catch (Throwable th2) {
                outputStreamWriter.close();
                throw th2;
            }
        } catch (SocketTimeoutException e) {
            throw new zzg(4, "The server timed out.", e);
        } catch (IOException e2) {
            throw new zzg(2, "Error making request.", e2);
        } catch (Throwable th3) {
            th.addSuppressed(th3);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(ConsentInformation.OnConsentInfoUpdateSuccessListener onConsentInfoUpdateSuccessListener, zzz zzz) {
        Objects.requireNonNull(onConsentInfoUpdateSuccessListener);
        this.zzc.post(new zzt(onConsentInfoUpdateSuccessListener));
        if (zzz.zzb != ConsentInformation.PrivacyOptionsRequirementStatus.NOT_REQUIRED) {
            this.zzf.zzc();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Activity activity, ConsentRequestParameters consentRequestParameters, ConsentInformation.OnConsentInfoUpdateSuccessListener onConsentInfoUpdateSuccessListener, ConsentInformation.OnConsentInfoUpdateFailureListener onConsentInfoUpdateFailureListener) {
        try {
            ConsentDebugSettings consentDebugSettings = consentRequestParameters.getConsentDebugSettings();
            if (consentDebugSettings == null || !consentDebugSettings.isTestDevice()) {
                String zza2 = zzcl.zza(this.zza);
                Log.i("UserMessagingPlatform", "Use new ConsentDebugSettings.Builder().addTestDeviceHashedId(\"" + zza2 + "\") to set this as a debug device.");
            }
            zzz zza3 = new zzw(this.zzh, zzd(this.zzg.zzc(activity, consentRequestParameters))).zza();
            this.zze.zzg(zza3.zza);
            this.zze.zzh(zza3.zzb);
            this.zzf.zzd(zza3.zzc);
            this.zzi.zza().execute(new zzp(this, onConsentInfoUpdateSuccessListener, zza3));
        } catch (zzg e) {
            this.zzc.post(new zzr(onConsentInfoUpdateFailureListener, e));
        } catch (RuntimeException e2) {
            this.zzc.post(new zzs(onConsentInfoUpdateFailureListener, new zzg(1, "Caught exception when trying to request consent info update: ".concat(String.valueOf(Log.getStackTraceString(e2))))));
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(Activity activity, ConsentRequestParameters consentRequestParameters, ConsentInformation.OnConsentInfoUpdateSuccessListener onConsentInfoUpdateSuccessListener, ConsentInformation.OnConsentInfoUpdateFailureListener onConsentInfoUpdateFailureListener) {
        this.zzd.execute(new zzq(this, activity, consentRequestParameters, onConsentInfoUpdateSuccessListener, onConsentInfoUpdateFailureListener));
    }
}
