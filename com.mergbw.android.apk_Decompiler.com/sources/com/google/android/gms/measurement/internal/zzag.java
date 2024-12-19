package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzop;
import com.google.android.gms.internal.measurement.zzou;
import java.lang.reflect.InvocationTargetException;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzag extends zzij {
    private Boolean zza;
    private String zzb;
    private zzai zzc = new zzaf();
    private Boolean zzd;

    public final double zza(String str, zzfj<Double> zzfj) {
        if (TextUtils.isEmpty(str)) {
            return zzfj.zza(null).doubleValue();
        }
        String zza2 = this.zzc.zza(str, zzfj.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzfj.zza(null).doubleValue();
        }
        try {
            return zzfj.zza(Double.valueOf(Double.parseDouble(zza2))).doubleValue();
        } catch (NumberFormatException unused) {
            return zzfj.zza(null).doubleValue();
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzc() {
        if (!zzou.zza() || !zze().zzf((String) null, zzbf.zzbx) || !zzq().zza(231100000, true)) {
            return 0;
        }
        return 35;
    }

    /* access modifiers changed from: package-private */
    public final int zza(String str) {
        return zza(str, zzbf.zzah, 500, 2000);
    }

    /* access modifiers changed from: package-private */
    public final int zza(String str, boolean z) {
        if (!zzop.zza() || !zze().zzf((String) null, zzbf.zzcn)) {
            return 100;
        }
        if (z) {
            return zza(str, zzbf.zzar, 100, 500);
        }
        return 500;
    }

    /* access modifiers changed from: package-private */
    public final int zzb(String str, boolean z) {
        return Math.max(zza(str, z), 256);
    }

    public final int zzg() {
        return zzq().zza(201500000, true) ? 100 : 25;
    }

    public final int zzb(String str) {
        return zza(str, zzbf.zzai, 25, 100);
    }

    public final int zzc(String str) {
        return zzb(str, zzbf.zzo);
    }

    public final int zzb(String str, zzfj<Integer> zzfj) {
        if (TextUtils.isEmpty(str)) {
            return zzfj.zza(null).intValue();
        }
        String zza2 = this.zzc.zza(str, zzfj.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzfj.zza(null).intValue();
        }
        try {
            return zzfj.zza(Integer.valueOf(Integer.parseInt(zza2))).intValue();
        } catch (NumberFormatException unused) {
            return zzfj.zza(null).intValue();
        }
    }

    public final int zza(String str, zzfj<Integer> zzfj, int i, int i2) {
        return Math.max(Math.min(zzb(str, zzfj), i2), i);
    }

    /* access modifiers changed from: package-private */
    public final long zzd(String str) {
        return zzc(str, zzbf.zza);
    }

    public static long zzh() {
        return zzbf.zzd.zza(null).longValue();
    }

    public static long zzm() {
        return zzbf.zzad.zza(null).longValue();
    }

    public final long zzc(String str, zzfj<Long> zzfj) {
        if (TextUtils.isEmpty(str)) {
            return zzfj.zza(null).longValue();
        }
        String zza2 = this.zzc.zza(str, zzfj.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzfj.zza(null).longValue();
        }
        try {
            return zzfj.zza(Long.valueOf(Long.parseLong(zza2))).longValue();
        } catch (NumberFormatException unused) {
            return zzfj.zza(null).longValue();
        }
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    private final Bundle zzz() {
        try {
            if (zza().getPackageManager() == null) {
                zzj().zzg().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(zza()).getApplicationInfo(zza().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            zzj().zzg().zza("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            zzj().zzg().zza("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzax zzf() {
        return super.zzf();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfr zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfw zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhc zzl() {
        return super.zzl();
    }

    public final zzim zzc(String str, boolean z) {
        Object obj;
        Preconditions.checkNotEmpty(str);
        Bundle zzz = zzz();
        if (zzz == null) {
            zzj().zzg().zza("Failed to load metadata: Metadata bundle is null");
            obj = null;
        } else {
            obj = zzz.get(str);
        }
        if (obj == null) {
            return zzim.UNINITIALIZED;
        }
        if (Boolean.TRUE.equals(obj)) {
            return zzim.GRANTED;
        }
        if (Boolean.FALSE.equals(obj)) {
            return zzim.DENIED;
        }
        if (z && "eu_consent_policy".equals(obj)) {
            return zzim.POLICY;
        }
        zzj().zzu().zza("Invalid manifest metadata for", str);
        return zzim.UNINITIALIZED;
    }

    @Pure
    public final /* bridge */ /* synthetic */ zznp zzq() {
        return super.zzq();
    }

    /* access modifiers changed from: package-private */
    public final Boolean zze(String str) {
        Preconditions.checkNotEmpty(str);
        Bundle zzz = zzz();
        if (zzz == null) {
            zzj().zzg().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        } else if (!zzz.containsKey(str)) {
            return null;
        } else {
            return Boolean.valueOf(zzz.getBoolean(str));
        }
    }

    public final String zzn() {
        return zza("debug.firebase.analytics.app", "");
    }

    public final String zzo() {
        return zza("debug.deferred.deeplink", "");
    }

    public final String zzd(String str, zzfj<String> zzfj) {
        if (TextUtils.isEmpty(str)) {
            return zzfj.zza(null);
        }
        return zzfj.zza(this.zzc.zza(str, zzfj.zza()));
    }

    public final String zzp() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final String zzf(String str) {
        return zzd(str, zzbf.zzal);
    }

    private final String zza(String str, String str2) {
        try {
            Class<String> cls = String.class;
            String str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{cls, cls}).invoke((Object) null, new Object[]{str, str2});
            Preconditions.checkNotNull(str3);
            return str3;
        } catch (ClassNotFoundException e) {
            zzj().zzg().zza("Could not find SystemProperties class", e);
            return str2;
        } catch (NoSuchMethodException e2) {
            zzj().zzg().zza("Could not find SystemProperties.get() method", e2);
            return str2;
        } catch (IllegalAccessException e3) {
            zzj().zzg().zza("Could not access SystemProperties.get()", e3);
            return str2;
        } catch (InvocationTargetException e4) {
            zzj().zzg().zza("SystemProperties.get() threw an exception", e4);
            return str2;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002b A[SYNTHETIC, Splitter:B:9:0x002b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.String> zzg(java.lang.String r4) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            android.os.Bundle r0 = r3.zzz()
            r1 = 0
            if (r0 != 0) goto L_0x0019
            com.google.android.gms.measurement.internal.zzfw r4 = r3.zzj()
            com.google.android.gms.measurement.internal.zzfy r4 = r4.zzg()
            java.lang.String r0 = "Failed to load metadata: Metadata bundle is null"
            r4.zza(r0)
        L_0x0017:
            r4 = r1
            goto L_0x0028
        L_0x0019:
            boolean r2 = r0.containsKey(r4)
            if (r2 != 0) goto L_0x0020
            goto L_0x0017
        L_0x0020:
            int r4 = r0.getInt(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
        L_0x0028:
            if (r4 != 0) goto L_0x002b
            return r1
        L_0x002b:
            android.content.Context r0 = r3.zza()     // Catch:{ NotFoundException -> 0x0043 }
            android.content.res.Resources r0 = r0.getResources()     // Catch:{ NotFoundException -> 0x0043 }
            int r4 = r4.intValue()     // Catch:{ NotFoundException -> 0x0043 }
            java.lang.String[] r4 = r0.getStringArray(r4)     // Catch:{ NotFoundException -> 0x0043 }
            if (r4 != 0) goto L_0x003e
            return r1
        L_0x003e:
            java.util.List r4 = java.util.Arrays.asList(r4)     // Catch:{ NotFoundException -> 0x0043 }
            return r4
        L_0x0043:
            r4 = move-exception
            com.google.android.gms.measurement.internal.zzfw r0 = r3.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()
            java.lang.String r2 = "Failed to load string array from metadata: resource not found"
            r0.zza(r2, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzag.zzg(java.lang.String):java.util.List");
    }

    zzag(zzhj zzhj) {
        super(zzhj);
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzai zzai) {
        this.zzc = zzai;
    }

    public final void zzh(String str) {
        this.zzb = str;
    }

    public final boolean zzu() {
        Boolean zze = zze("google_analytics_adid_collection_enabled");
        return zze == null || zze.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzi(String str) {
        return zzf(str, zzbf.zzak);
    }

    public final boolean zza(zzfj<Boolean> zzfj) {
        return zzf((String) null, zzfj);
    }

    public final boolean zze(String str, zzfj<Boolean> zzfj) {
        return zzf(str, zzfj);
    }

    public final boolean zzf(String str, zzfj<Boolean> zzfj) {
        if (TextUtils.isEmpty(str)) {
            return zzfj.zza(null).booleanValue();
        }
        String zza2 = this.zzc.zza(str, zzfj.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzfj.zza(null).booleanValue();
        }
        return zzfj.zza(Boolean.valueOf(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(zza2))).booleanValue();
    }

    public final boolean zzj(String str) {
        return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(this.zzc.zza(str, "gaia_collection_enabled"));
    }

    public final boolean zzv() {
        Boolean zze = zze("google_analytics_automatic_screen_reporting_enabled");
        return zze == null || zze.booleanValue();
    }

    public final boolean zzw() {
        Boolean zze = zze("firebase_analytics_collection_deactivated");
        return zze != null && zze.booleanValue();
    }

    public final boolean zzk(String str) {
        return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(this.zzc.zza(str, "measurement.event_sampling_enabled"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzx() {
        if (this.zza == null) {
            Boolean zze = zze("app_measurement_lite");
            this.zza = zze;
            if (zze == null) {
                this.zza = false;
            }
        }
        if (this.zza.booleanValue() || !this.zzu.zzag()) {
            return true;
        }
        return false;
    }

    @EnsuresNonNull({"this.isMainProcess"})
    public final boolean zzy() {
        if (this.zzd == null) {
            synchronized (this) {
                if (this.zzd == null) {
                    ApplicationInfo applicationInfo = zza().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzd = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.zzd == null) {
                        this.zzd = Boolean.TRUE;
                        zzj().zzg().zza("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzd.booleanValue();
    }
}
