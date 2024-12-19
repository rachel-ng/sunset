package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.collection.ArrayMap;
import androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzhi implements zzgn {
    private static final Map<String, zzhi> zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final Runnable zzc;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzd;
    private final Object zze = new Object();
    private volatile Map<String, ?> zzf;
    private final List<zzgl> zzg = new ArrayList();

    private static SharedPreferences zza(Context context, String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzgi.zza()) {
                    context = Trace$$ExternalSyntheticApiModelOutline0.m(context);
                }
                return context.getSharedPreferences(str.substring(12), 0);
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return sharedPreferences;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    static zzhi zza(Context context, String str, Runnable runnable) {
        zzhi zzhi;
        if (!((!zzgi.zza() || str.startsWith("direct_boot:")) ? true : zzgi.zzb(context))) {
            return null;
        }
        synchronized (zzhi.class) {
            Map<String, zzhi> map = zza;
            zzhi = map.get(str);
            if (zzhi == null) {
                zzhi = new zzhi(zza(context, str), runnable);
                map.put(str, zzhi);
            }
        }
        return zzhi;
    }

    /* JADX INFO: finally extract failed */
    public final Object zza(String str) {
        Map<String, ?> map = this.zzf;
        if (map == null) {
            synchronized (this.zze) {
                map = this.zzf;
                if (map == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        Map<String, ?> all = this.zzb.getAll();
                        this.zzf = all;
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        map = all;
                    } catch (Throwable th) {
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        throw th;
                    }
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    private zzhi(SharedPreferences sharedPreferences, Runnable runnable) {
        zzhl zzhl = new zzhl(this);
        this.zzd = zzhl;
        this.zzb = sharedPreferences;
        this.zzc = runnable;
        sharedPreferences.registerOnSharedPreferenceChangeListener(zzhl);
    }

    static synchronized void zza() {
        synchronized (zzhi.class) {
            for (zzhi next : zza.values()) {
                next.zzb.unregisterOnSharedPreferenceChangeListener(next.zzd);
            }
            zza.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zze) {
            this.zzf = null;
            this.zzc.run();
        }
        synchronized (this) {
            for (zzgl zza2 : this.zzg) {
                zza2.zza();
            }
        }
    }
}
