package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.exoplayer2.PlaybackException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfsv implements zzfrh {
    private final Object zza;
    private final zzfsw zzb;
    private final zzfth zzc;
    private final zzfre zzd;

    zzfsv(Object obj, zzfsw zzfsw, zzfth zzfth, zzfre zzfre) {
        this.zza = obj;
        this.zzb = zzfsw;
        this.zzc = zzfth;
        this.zzd = zzfre;
    }

    private static String zzi(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        zzavk zza2 = zzavl.zza();
        zza2.zzb(zzauz.DG);
        zza2.zza(zzhac.zzv(bArr, 0, bArr.length));
        return Base64.encodeToString(((zzavl) zza2.zzbr()).zzaV(), 11);
    }

    private final synchronized byte[] zzj(Map map, Map map2) {
        Class<Map> cls;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            cls = Map.class;
        } catch (Exception e) {
            this.zzd.zzc(PlaybackException.ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED, System.currentTimeMillis() - currentTimeMillis, e);
            return null;
        }
        return (byte[]) this.zza.getClass().getDeclaredMethod("xss", new Class[]{cls, cls}).invoke(this.zza, new Object[]{null, map2});
    }

    public final synchronized String zza(Context context, String str, String str2, View view, Activity activity) {
        Map zza2;
        zza2 = this.zzc.zza();
        zza2.put("f", "c");
        zza2.put("ctx", context);
        zza2.put("cs", str2);
        zza2.put("aid", (Object) null);
        zza2.put("view", view);
        zza2.put("act", activity);
        return zzi(zzj((Map) null, zza2));
    }

    public final synchronized String zzb(Context context, String str, View view, Activity activity) {
        Map zzc2;
        zzc2 = this.zzc.zzc();
        zzc2.put("f", "v");
        zzc2.put("ctx", context);
        zzc2.put("aid", (Object) null);
        zzc2.put("view", view);
        zzc2.put("act", activity);
        return zzi(zzj((Map) null, zzc2));
    }

    public final synchronized String zzc(Context context, String str) {
        Map zzb2;
        zzb2 = this.zzc.zzb();
        zzb2.put("f", "q");
        zzb2.put("ctx", context);
        zzb2.put("aid", (Object) null);
        return zzi(zzj((Map) null, zzb2));
    }

    public final synchronized void zzd(String str, MotionEvent motionEvent) throws zzftf {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            HashMap hashMap = new HashMap();
            hashMap.put("t", new Throwable());
            hashMap.put("aid", (Object) null);
            hashMap.put("evt", motionEvent);
            this.zza.getClass().getDeclaredMethod("he", new Class[]{Map.class}).invoke(this.zza, new Object[]{hashMap});
            this.zzd.zzd(PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED, System.currentTimeMillis() - currentTimeMillis);
        } catch (Exception e) {
            throw new zzftf((int) PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND, (Throwable) e);
        }
    }

    public final synchronized int zze() throws zzftf {
        try {
        } catch (Exception e) {
            throw new zzftf((int) PlaybackException.ERROR_CODE_IO_NO_PERMISSION, (Throwable) e);
        }
        return ((Integer) this.zza.getClass().getDeclaredMethod("lcs", (Class[]) null).invoke(this.zza, (Object[]) null)).intValue();
    }

    /* access modifiers changed from: package-private */
    public final zzfsw zzf() {
        return this.zzb;
    }

    public final synchronized void zzg() throws zzftf {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.zza.getClass().getDeclaredMethod("close", (Class[]) null).invoke(this.zza, (Object[]) null);
            this.zzd.zzd(PlaybackException.ERROR_CODE_PARSING_CONTAINER_MALFORMED, System.currentTimeMillis() - currentTimeMillis);
        } catch (Exception e) {
            throw new zzftf((int) PlaybackException.ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE, (Throwable) e);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzh() throws zzftf {
        try {
        } catch (Exception e) {
            throw new zzftf((int) PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED, (Throwable) e);
        }
        return ((Boolean) this.zza.getClass().getDeclaredMethod("init", (Class[]) null).invoke(this.zza, (Object[]) null)).booleanValue();
    }
}
