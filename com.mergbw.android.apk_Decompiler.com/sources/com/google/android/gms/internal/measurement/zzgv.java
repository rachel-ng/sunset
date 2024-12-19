package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0;
import com.google.common.base.Optional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzgv {
    private static zzgt zza(Context context, File file) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
            HashMap hashMap = new HashMap();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] split = readLine.split(" ", 3);
                    if (split.length != 3) {
                        Log.e("HermeticFileOverrides", "Invalid: " + readLine);
                    } else {
                        String zza2 = zza(split[0]);
                        String decode = Uri.decode(zza(split[1]));
                        String str = (String) hashMap.get(split[2]);
                        if (str == null) {
                            String zza3 = zza(split[2]);
                            str = Uri.decode(zza3);
                            if (str.length() < 1024 || str == zza3) {
                                hashMap.put(zza3, str);
                            }
                        }
                        SimpleArrayMap simpleArrayMap2 = (SimpleArrayMap) simpleArrayMap.get(zza2);
                        if (simpleArrayMap2 == null) {
                            simpleArrayMap2 = new SimpleArrayMap();
                            simpleArrayMap.put(zza2, simpleArrayMap2);
                        }
                        simpleArrayMap2.put(decode, str);
                    }
                } else {
                    String valueOf = String.valueOf(file);
                    String packageName = context.getPackageName();
                    Log.w("HermeticFileOverrides", "Parsed " + valueOf + " for Android package " + packageName);
                    zzgo zzgo = new zzgo(simpleArrayMap);
                    bufferedReader.close();
                    return zzgo;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
    public static class zza {
        private static volatile Optional<zzgt> zza;

        public static Optional<zzgt> zza(Context context) {
            Optional<zzgt> optional;
            Optional<zzgt> optional2 = zza;
            if (optional2 == null) {
                synchronized (zza.class) {
                    optional2 = zza;
                    if (optional2 == null) {
                        new zzgv();
                        if (!zzgw.zza(Build.TYPE, Build.TAGS)) {
                            optional = Optional.absent();
                        } else {
                            if (zzgi.zza()) {
                                if (!context.isDeviceProtectedStorage()) {
                                    context = Trace$$ExternalSyntheticApiModelOutline0.m(context);
                                }
                            }
                            optional = zzgv.zza(context);
                        }
                        zza = optional;
                        optional2 = optional;
                    }
                }
            }
            return optional2;
        }

        private zza() {
        }
    }

    private static Optional<File> zzb(Context context) {
        try {
            File file = new File(context.getDir("phenotype_hermetic", 0), "overrides.txt");
            return file.exists() ? Optional.of(file) : Optional.absent();
        } catch (RuntimeException e) {
            Log.e("HermeticFileOverrides", "no data dir", e);
            return Optional.absent();
        }
    }

    static Optional<zzgt> zza(Context context) {
        Optional<zzgt> optional;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            StrictMode.allowThreadDiskWrites();
            Optional<File> zzb = zzb(context);
            if (zzb.isPresent()) {
                optional = Optional.of(zza(context, zzb.get()));
            } else {
                optional = Optional.absent();
            }
            return optional;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private static final String zza(String str) {
        return new String(str);
    }
}
