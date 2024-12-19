package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzks extends zze {
    protected zzkp zza;
    private volatile zzkp zzb;
    private volatile zzkp zzc;
    private final Map<Activity, zzkp> zzd = new ConcurrentHashMap();
    private Activity zze;
    private volatile boolean zzf;
    private volatile zzkp zzg;
    /* access modifiers changed from: private */
    public zzkp zzh;
    private boolean zzi;
    private final Object zzj = new Object();

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zzb zzc() {
        return super.zzc();
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

    public final /* bridge */ /* synthetic */ zzfq zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzfp zzh() {
        return super.zzh();
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

    public final /* bridge */ /* synthetic */ zziv zzm() {
        return super.zzm();
    }

    private final zzkp zzd(Activity activity) {
        Preconditions.checkNotNull(activity);
        zzkp zzkp = this.zzd.get(activity);
        if (zzkp == null) {
            zzkp zzkp2 = new zzkp((String) null, zza(activity.getClass(), "Activity"), zzq().zzm());
            this.zzd.put(activity, zzkp2);
            zzkp = zzkp2;
        }
        return this.zzg != null ? this.zzg : zzkp;
    }

    public final zzkp zzaa() {
        return this.zzb;
    }

    public final zzkp zza(boolean z) {
        zzu();
        zzt();
        if (!z) {
            return this.zza;
        }
        zzkp zzkp = this.zza;
        return zzkp != null ? zzkp : this.zzh;
    }

    public final /* bridge */ /* synthetic */ zzks zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzkx zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzmh zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zznp zzq() {
        return super.zzq();
    }

    private final String zza(Class<?> cls, String str) {
        String str2;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            return str;
        }
        String[] split = canonicalName.split("\\.");
        if (split.length > 0) {
            str2 = split[split.length - 1];
        } else {
            str2 = "";
        }
        return str2.length() > zze().zza((String) null, false) ? str2.substring(0, zze().zza((String) null, false)) : str2;
    }

    static /* synthetic */ void zza(zzks zzks, Bundle bundle, zzkp zzkp, zzkp zzkp2, long j) {
        if (bundle != null) {
            bundle.remove(FirebaseAnalytics.Param.SCREEN_NAME);
            bundle.remove(FirebaseAnalytics.Param.SCREEN_CLASS);
        }
        zzks.zza(zzkp, zzkp2, j, true, zzks.zzq().zza((String) null, FirebaseAnalytics.Event.SCREEN_VIEW, bundle, (List<String>) null, false));
    }

    public zzks(zzhj zzhj) {
        super(zzhj);
    }

    private final void zza(Activity activity, zzkp zzkp, boolean z) {
        zzkp zzkp2;
        zzkp zzkp3 = zzkp;
        zzkp zzkp4 = this.zzb == null ? this.zzc : this.zzb;
        if (zzkp3.zzb == null) {
            zzkp2 = new zzkp(zzkp3.zza, activity != null ? zza(activity.getClass(), "Activity") : null, zzkp3.zzc, zzkp3.zze, zzkp3.zzf);
        } else {
            zzkp2 = zzkp3;
        }
        this.zzc = this.zzb;
        this.zzb = zzkp2;
        zzl().zzb((Runnable) new zzku(this, zzkp2, zzkp4, zzb().elapsedRealtime(), z));
    }

    /* access modifiers changed from: private */
    public final void zza(zzkp zzkp, zzkp zzkp2, long j, boolean z, Bundle bundle) {
        Bundle bundle2;
        String str;
        zzkp zzkp3 = zzkp;
        zzkp zzkp4 = zzkp2;
        long j2 = j;
        Bundle bundle3 = bundle;
        zzt();
        boolean z2 = false;
        boolean z3 = zzkp4 == null || zzkp4.zzc != zzkp3.zzc || !Objects.equals(zzkp4.zzb, zzkp3.zzb) || !Objects.equals(zzkp4.zza, zzkp3.zza);
        if (z && this.zza != null) {
            z2 = true;
        }
        if (z3) {
            if (bundle3 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle4 = bundle2;
            zznp.zza(zzkp3, bundle4, true);
            if (zzkp4 != null) {
                if (zzkp4.zza != null) {
                    bundle4.putString("_pn", zzkp4.zza);
                }
                if (zzkp4.zzb != null) {
                    bundle4.putString("_pc", zzkp4.zzb);
                }
                bundle4.putLong("_pi", zzkp4.zzc);
            }
            if (z2) {
                long zza2 = zzp().zzb.zza(j2);
                if (zza2 > 0) {
                    zzq().zza(bundle4, zza2);
                }
            }
            if (!zze().zzv()) {
                bundle4.putLong("_mst", 1);
            }
            if (zzkp3.zze) {
                str = "app";
            } else {
                str = "auto";
            }
            zzm().zza(str, "_vs", (!zzkp3.zze || zzkp3.zzf == 0) ? zzb().currentTimeMillis() : zzkp3.zzf, bundle4);
        }
        if (z2) {
            zza(this.zza, true, j2);
        }
        this.zza = zzkp3;
        if (zzkp3.zze) {
            this.zzh = zzkp3;
        }
        zzo().zza(zzkp3);
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

    public final void zza(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (zze().zzv() && bundle != null && (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) != null) {
            this.zzd.put(activity, new zzkp(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), bundle2.getString("referrer_name"), bundle2.getLong(TtmlNode.ATTR_ID)));
        }
    }

    public final void zza(Activity activity) {
        synchronized (this.zzj) {
            if (activity == this.zze) {
                this.zze = null;
            }
        }
        if (zze().zzv()) {
            this.zzd.remove(activity);
        }
    }

    public final void zzb(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = false;
            this.zzf = true;
        }
        long elapsedRealtime = zzb().elapsedRealtime();
        if (!zze().zzv()) {
            this.zzb = null;
            zzl().zzb((Runnable) new zzkw(this, elapsedRealtime));
            return;
        }
        zzkp zzd2 = zzd(activity);
        this.zzc = this.zzb;
        this.zzb = null;
        zzl().zzb((Runnable) new zzkv(this, zzd2, elapsedRealtime));
    }

    public final void zzc(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = true;
            if (activity != this.zze) {
                synchronized (this.zzj) {
                    this.zze = activity;
                    this.zzf = false;
                }
                if (zze().zzv()) {
                    this.zzg = null;
                    zzl().zzb((Runnable) new zzky(this));
                }
            }
        }
        if (!zze().zzv()) {
            this.zzb = this.zzg;
            zzl().zzb((Runnable) new zzkt(this));
            return;
        }
        zza(activity, zzd(activity), false);
        zzb zzc2 = zzc();
        zzc2.zzl().zzb((Runnable) new zzc(zzc2, zzc2.zzb().elapsedRealtime()));
    }

    public final void zzb(Activity activity, Bundle bundle) {
        zzkp zzkp;
        if (zze().zzv() && bundle != null && (zzkp = this.zzd.get(activity)) != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putLong(TtmlNode.ATTR_ID, zzkp.zzc);
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, zzkp.zza);
            bundle2.putString("referrer_name", zzkp.zzb);
            bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzkp zzkp, boolean z, long j) {
        zzc().zza(zzb().elapsedRealtime());
        if (zzp().zza(zzkp != null && zzkp.zzd, z, j) && zzkp != null) {
            zzkp.zzd = false;
        }
    }

    @Deprecated
    public final void zza(Activity activity, String str, String str2) {
        if (!zze().zzv()) {
            zzj().zzv().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
            return;
        }
        zzkp zzkp = this.zzb;
        if (zzkp == null) {
            zzj().zzv().zza("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzd.get(activity) == null) {
            zzj().zzv().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zza(activity.getClass(), "Activity");
            }
            boolean equals = Objects.equals(zzkp.zzb, str2);
            boolean equals2 = Objects.equals(zzkp.zza, str);
            if (equals && equals2) {
                zzj().zzv().zza("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > zze().zza((String) null, false))) {
                zzj().zzv().zza("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= zze().zza((String) null, false))) {
                zzj().zzp().zza("Setting current screen to name, class", str == null ? "null" : str, str2);
                zzkp zzkp2 = new zzkp(str, str2, zzq().zzm());
                this.zzd.put(activity, zzkp2);
                zza(activity, zzkp2, true);
            } else {
                zzj().zzv().zza("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c6, code lost:
        r1 = zzj().zzp();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d0, code lost:
        if (r10 != null) goto L_0x00d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d2, code lost:
        r3 = "null";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d5, code lost:
        r3 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d6, code lost:
        if (r11 != null) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d8, code lost:
        r4 = "null";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00db, code lost:
        r4 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00dc, code lost:
        r1.zza("Logging screen view with name, class", r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e1, code lost:
        if (r8.zzb != null) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e3, code lost:
        r1 = r8.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e6, code lost:
        r1 = r8.zzb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e8, code lost:
        r5 = r1;
        r9 = new com.google.android.gms.measurement.internal.zzkp(r10, r11, zzq().zzm(), true, r19);
        r8.zzb = r9;
        r8.zzc = r5;
        r8.zzg = r9;
        zzl().zzb((java.lang.Runnable) new com.google.android.gms.measurement.internal.zzkr(r17, r18, r9, r5, zzb().elapsedRealtime()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0119, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(android.os.Bundle r18, long r19) {
        /*
            r17 = this;
            r8 = r17
            r0 = r18
            java.lang.Object r1 = r8.zzj
            monitor-enter(r1)
            boolean r2 = r8.zzi     // Catch:{ all -> 0x011a }
            if (r2 != 0) goto L_0x001a
            com.google.android.gms.measurement.internal.zzfw r0 = r17.zzj()     // Catch:{ all -> 0x011a }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzv()     // Catch:{ all -> 0x011a }
            java.lang.String r2 = "Cannot log screen view event when the app is in the background."
            r0.zza(r2)     // Catch:{ all -> 0x011a }
            monitor-exit(r1)     // Catch:{ all -> 0x011a }
            return
        L_0x001a:
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L_0x0087
            java.lang.String r4 = "screen_name"
            java.lang.String r4 = r0.getString(r4)     // Catch:{ all -> 0x011a }
            if (r4 == 0) goto L_0x0051
            int r5 = r4.length()     // Catch:{ all -> 0x011a }
            if (r5 <= 0) goto L_0x003a
            int r5 = r4.length()     // Catch:{ all -> 0x011a }
            com.google.android.gms.measurement.internal.zzag r6 = r17.zze()     // Catch:{ all -> 0x011a }
            int r6 = r6.zza((java.lang.String) r3, (boolean) r2)     // Catch:{ all -> 0x011a }
            if (r5 <= r6) goto L_0x0051
        L_0x003a:
            com.google.android.gms.measurement.internal.zzfw r0 = r17.zzj()     // Catch:{ all -> 0x011a }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzv()     // Catch:{ all -> 0x011a }
            java.lang.String r2 = "Invalid screen name length for screen view. Length"
            int r3 = r4.length()     // Catch:{ all -> 0x011a }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x011a }
            r0.zza(r2, r3)     // Catch:{ all -> 0x011a }
            monitor-exit(r1)     // Catch:{ all -> 0x011a }
            return
        L_0x0051:
            java.lang.String r5 = "screen_class"
            java.lang.String r5 = r0.getString(r5)     // Catch:{ all -> 0x011a }
            if (r5 == 0) goto L_0x0084
            int r6 = r5.length()     // Catch:{ all -> 0x011a }
            if (r6 <= 0) goto L_0x006d
            int r6 = r5.length()     // Catch:{ all -> 0x011a }
            com.google.android.gms.measurement.internal.zzag r7 = r17.zze()     // Catch:{ all -> 0x011a }
            int r3 = r7.zza((java.lang.String) r3, (boolean) r2)     // Catch:{ all -> 0x011a }
            if (r6 <= r3) goto L_0x0084
        L_0x006d:
            com.google.android.gms.measurement.internal.zzfw r0 = r17.zzj()     // Catch:{ all -> 0x011a }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzv()     // Catch:{ all -> 0x011a }
            java.lang.String r2 = "Invalid screen class length for screen view. Length"
            int r3 = r5.length()     // Catch:{ all -> 0x011a }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x011a }
            r0.zza(r2, r3)     // Catch:{ all -> 0x011a }
            monitor-exit(r1)     // Catch:{ all -> 0x011a }
            return
        L_0x0084:
            r10 = r4
            r3 = r5
            goto L_0x0088
        L_0x0087:
            r10 = r3
        L_0x0088:
            if (r3 != 0) goto L_0x009b
            android.app.Activity r3 = r8.zze     // Catch:{ all -> 0x011a }
            if (r3 == 0) goto L_0x0099
            java.lang.Class r3 = r3.getClass()     // Catch:{ all -> 0x011a }
            java.lang.String r4 = "Activity"
            java.lang.String r3 = r8.zza((java.lang.Class<?>) r3, (java.lang.String) r4)     // Catch:{ all -> 0x011a }
            goto L_0x009b
        L_0x0099:
            java.lang.String r3 = "Activity"
        L_0x009b:
            r11 = r3
            com.google.android.gms.measurement.internal.zzkp r3 = r8.zzb     // Catch:{ all -> 0x011a }
            boolean r4 = r8.zzf     // Catch:{ all -> 0x011a }
            if (r4 == 0) goto L_0x00c5
            if (r3 == 0) goto L_0x00c5
            r8.zzf = r2     // Catch:{ all -> 0x011a }
            java.lang.String r2 = r3.zzb     // Catch:{ all -> 0x011a }
            boolean r2 = java.util.Objects.equals(r2, r11)     // Catch:{ all -> 0x011a }
            java.lang.String r3 = r3.zza     // Catch:{ all -> 0x011a }
            boolean r3 = java.util.Objects.equals(r3, r10)     // Catch:{ all -> 0x011a }
            if (r2 == 0) goto L_0x00c5
            if (r3 == 0) goto L_0x00c5
            com.google.android.gms.measurement.internal.zzfw r0 = r17.zzj()     // Catch:{ all -> 0x011a }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzv()     // Catch:{ all -> 0x011a }
            java.lang.String r2 = "Ignoring call to log screen view event with duplicate parameters."
            r0.zza(r2)     // Catch:{ all -> 0x011a }
            monitor-exit(r1)     // Catch:{ all -> 0x011a }
            return
        L_0x00c5:
            monitor-exit(r1)     // Catch:{ all -> 0x011a }
            com.google.android.gms.measurement.internal.zzfw r1 = r17.zzj()
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzp()
            java.lang.String r2 = "Logging screen view with name, class"
            if (r10 != 0) goto L_0x00d5
            java.lang.String r3 = "null"
            goto L_0x00d6
        L_0x00d5:
            r3 = r10
        L_0x00d6:
            if (r11 != 0) goto L_0x00db
            java.lang.String r4 = "null"
            goto L_0x00dc
        L_0x00db:
            r4 = r11
        L_0x00dc:
            r1.zza(r2, r3, r4)
            com.google.android.gms.measurement.internal.zzkp r1 = r8.zzb
            if (r1 != 0) goto L_0x00e6
            com.google.android.gms.measurement.internal.zzkp r1 = r8.zzc
            goto L_0x00e8
        L_0x00e6:
            com.google.android.gms.measurement.internal.zzkp r1 = r8.zzb
        L_0x00e8:
            r5 = r1
            com.google.android.gms.measurement.internal.zzkp r4 = new com.google.android.gms.measurement.internal.zzkp
            com.google.android.gms.measurement.internal.zznp r1 = r17.zzq()
            long r12 = r1.zzm()
            r14 = 1
            r9 = r4
            r15 = r19
            r9.<init>(r10, r11, r12, r14, r15)
            r8.zzb = r4
            r8.zzc = r5
            r8.zzg = r4
            com.google.android.gms.common.util.Clock r1 = r17.zzb()
            long r6 = r1.elapsedRealtime()
            com.google.android.gms.measurement.internal.zzhc r9 = r17.zzl()
            com.google.android.gms.measurement.internal.zzkr r10 = new com.google.android.gms.measurement.internal.zzkr
            r1 = r10
            r2 = r17
            r3 = r18
            r1.<init>(r2, r3, r4, r5, r6)
            r9.zzb((java.lang.Runnable) r10)
            return
        L_0x011a:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x011a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzks.zza(android.os.Bundle, long):void");
    }
}
