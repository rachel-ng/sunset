package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import androidx.work.WorkRequest;
import com.google.android.exoplayer2.C;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzhd;
import com.google.android.gms.measurement.internal.zzir;
import com.google.android.gms.measurement.internal.zziu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
public class zzdq {
    private static volatile zzdq zzb;
    protected final Clock zza;
    /* access modifiers changed from: private */
    public final String zzc;
    private final ExecutorService zzd;
    private final AppMeasurementSdk zze;
    private final List<Pair<zziu, zzb>> zzf;
    private int zzg;
    /* access modifiers changed from: private */
    public boolean zzh;
    private String zzi;
    /* access modifiers changed from: private */
    public volatile zzdb zzj;

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
    static class zzb extends zzdk {
        private final zziu zza;

        public final int zza() {
            return System.identityHashCode(this.zza);
        }

        zzb(zziu zziu) {
            this.zza = zziu;
        }

        public final void zza(String str, String str2, Bundle bundle, long j) {
            this.zza.onEvent(str, str2, bundle, j);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
    static class zzc extends zzdk {
        private final zzir zza;

        public final int zza() {
            return System.identityHashCode(this.zza);
        }

        zzc(zzir zzir) {
            this.zza = zzir;
        }

        public final void zza(String str, String str2, Bundle bundle, long j) {
            this.zza.interceptEvent(str, str2, bundle, j);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
    class zzd implements Application.ActivityLifecycleCallbacks {
        zzd() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
            zzdq.this.zza((zza) new zzey(this, bundle, activity));
        }

        public final void onActivityDestroyed(Activity activity) {
            zzdq.this.zza((zza) new zzfd(this, activity));
        }

        public final void onActivityPaused(Activity activity) {
            zzdq.this.zza((zza) new zzfc(this, activity));
        }

        public final void onActivityResumed(Activity activity) {
            zzdq.this.zza((zza) new zzez(this, activity));
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zzdc zzdc = new zzdc();
            zzdq.this.zza((zza) new zzfe(this, activity, zzdc));
            Bundle zza2 = zzdc.zza(50);
            if (zza2 != null) {
                bundle.putAll(zza2);
            }
        }

        public final void onActivityStarted(Activity activity) {
            zzdq.this.zza((zza) new zzfa(this, activity));
        }

        public final void onActivityStopped(Activity activity) {
            zzdq.this.zza((zza) new zzfb(this, activity));
        }
    }

    public final int zza(String str) {
        zzdc zzdc = new zzdc();
        zza((zza) new zzen(this, str, zzdc));
        Integer num = (Integer) zzdc.zza(zzdc.zza((long) WorkRequest.MIN_BACKOFF_MILLIS), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
    abstract class zza implements Runnable {
        final long zza;
        final long zzb;
        private final boolean zzc;

        zza(zzdq zzdq) {
            this(true);
        }

        /* access modifiers changed from: package-private */
        public abstract void zza() throws RemoteException;

        /* access modifiers changed from: protected */
        public void zzb() {
        }

        zza(boolean z) {
            this.zza = zzdq.this.zza.currentTimeMillis();
            this.zzb = zzdq.this.zza.elapsedRealtime();
            this.zzc = z;
        }

        public void run() {
            if (zzdq.this.zzh) {
                zzb();
                return;
            }
            try {
                zza();
            } catch (Exception e) {
                zzdq.this.zza(e, false, this.zzc);
                zzb();
            }
        }
    }

    public final long zza() {
        zzdc zzdc = new zzdc();
        zza((zza) new zzei(this, zzdc));
        Long zzb2 = zzdc.zzb(500);
        if (zzb2 != null) {
            return zzb2.longValue();
        }
        long nextLong = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
        int i = this.zzg + 1;
        this.zzg = i;
        return nextLong + ((long) i);
    }

    public final Bundle zza(Bundle bundle, boolean z) {
        zzdc zzdc = new zzdc();
        zza((zza) new zzeo(this, bundle, zzdc));
        if (z) {
            return zzdc.zza(5000);
        }
        return null;
    }

    public final AppMeasurementSdk zzb() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final zzdb zza(Context context, boolean z) {
        try {
            return zzde.asInterface(DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        } catch (DynamiteModule.LoadingException e) {
            zza((Exception) e, true, false);
            return null;
        }
    }

    public static zzdq zza(Context context) {
        return zza(context, (String) null, (String) null, (String) null, (Bundle) null);
    }

    public static zzdq zza(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (zzb == null) {
            synchronized (zzdq.class) {
                if (zzb == null) {
                    zzb = new zzdq(context, str, str2, str3, bundle);
                }
            }
        }
        return zzb;
    }

    public final Long zzc() {
        zzdc zzdc = new zzdc();
        zza((zza) new zzep(this, zzdc));
        return zzdc.zzb(120000);
    }

    public final Object zza(int i) {
        zzdc zzdc = new zzdc();
        zza((zza) new zzes(this, zzdc, i));
        return zzdc.zza(zzdc.zza((long) C.DEFAULT_SEEK_FORWARD_INCREMENT_MS), Object.class);
    }

    public final String zzd() {
        return this.zzi;
    }

    public final String zze() {
        zzdc zzdc = new zzdc();
        zza((zza) new zzeq(this, zzdc));
        return zzdc.zzc(120000);
    }

    public final String zzf() {
        zzdc zzdc = new zzdc();
        zza((zza) new zzef(this, zzdc));
        return zzdc.zzc(50);
    }

    public final String zzg() {
        zzdc zzdc = new zzdc();
        zza((zza) new zzek(this, zzdc));
        return zzdc.zzc(500);
    }

    public final String zzh() {
        zzdc zzdc = new zzdc();
        zza((zza) new zzeh(this, zzdc));
        return zzdc.zzc(500);
    }

    public final String zzi() {
        zzdc zzdc = new zzdc();
        zza((zza) new zzeg(this, zzdc));
        return zzdc.zzc(500);
    }

    public final List<Bundle> zza(String str, String str2) {
        zzdc zzdc = new zzdc();
        zza((zza) new zzdt(this, str, str2, zzdc));
        List<Bundle> list = (List) zzdc.zza(zzdc.zza(5000), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        zzdc zzdc = new zzdc();
        zza((zza) new zzej(this, str, str2, z, zzdc));
        Bundle zza2 = zzdc.zza(5000);
        if (zza2 == null || zza2.size() == 0) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(zza2.size());
        for (String str3 : zza2.keySet()) {
            Object obj = zza2.get(str3);
            if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                hashMap.put(str3, obj);
            }
        }
        return hashMap;
    }

    private zzdq(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str == null || !zzc(str2, str3)) {
            this.zzc = "FA";
        } else {
            this.zzc = str;
        }
        this.zza = DefaultClock.getInstance();
        boolean z = true;
        this.zzd = zzcu.zza().zza(new zzec(this), 1);
        this.zze = new AppMeasurementSdk(this);
        this.zzf = new ArrayList();
        if (!zzb(context) || zzk()) {
            if (!zzc(str2, str3)) {
                this.zzi = "fa";
                if (str2 == null || str3 == null) {
                    if ((str2 == null) ^ (str3 != null ? false : z)) {
                        Log.w(this.zzc, "Specified origin or custom app id is null. Both parameters will be ignored.");
                    }
                } else {
                    Log.v(this.zzc, "Deferring to Google Analytics for Firebase for event data collection. https://firebase.google.com/docs/analytics");
                }
            } else {
                this.zzi = str2;
            }
            zza((zza) new zzdp(this, str2, str3, context, bundle));
            Application application = (Application) context.getApplicationContext();
            if (application == null) {
                Log.w(this.zzc, "Unable to register lifecycle notifications. Application null.");
            } else {
                application.registerActivityLifecycleCallbacks(new zzd());
            }
        } else {
            this.zzi = null;
            this.zzh = true;
            Log.w(this.zzc, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
        }
    }

    /* access modifiers changed from: private */
    public final void zza(Exception exc, boolean z, boolean z2) {
        this.zzh |= z;
        if (z) {
            Log.w(this.zzc, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z2) {
            zza(5, "Error with data collection. Data lost.", (Object) exc, (Object) null, (Object) null);
        }
        Log.w(this.zzc, "Error with data collection. Data lost.", exc);
    }

    public final void zzb(String str) {
        zza((zza) new zzee(this, str));
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza((zza) new zzdu(this, str, str2, bundle));
    }

    public final void zzc(String str) {
        zza((zza) new zzed(this, str));
    }

    public final void zza(String str, Bundle bundle) {
        zza((String) null, str, bundle, false, true, (Long) null);
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, (Long) null);
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, bundle, true, false, Long.valueOf(j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, Long l) {
        zza((zza) new zzex(this, l, str, str2, bundle, z, z2));
    }

    public final void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zza((zza) new zzem(this, false, 5, str, obj, (Object) null, (Object) null));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (r4.zzj == null) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r4.zzj.registerOnMeasurementEventListener(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        android.util.Log.w(r4.zzc, "Failed to register event listener on calling thread. Trying again on the dynamite thread.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004c, code lost:
        zza((com.google.android.gms.internal.measurement.zzdq.zza) new com.google.android.gms.internal.measurement.zzew(r4, r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.measurement.internal.zziu r5) {
        /*
            r4 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zziu, com.google.android.gms.internal.measurement.zzdq$zzb>> r0 = r4.zzf
            monitor-enter(r0)
            r1 = 0
        L_0x0007:
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zziu, com.google.android.gms.internal.measurement.zzdq$zzb>> r2 = r4.zzf     // Catch:{ all -> 0x0055 }
            int r2 = r2.size()     // Catch:{ all -> 0x0055 }
            if (r1 >= r2) goto L_0x002b
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zziu, com.google.android.gms.internal.measurement.zzdq$zzb>> r2 = r4.zzf     // Catch:{ all -> 0x0055 }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x0055 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x0055 }
            java.lang.Object r2 = r2.first     // Catch:{ all -> 0x0055 }
            boolean r2 = r5.equals(r2)     // Catch:{ all -> 0x0055 }
            if (r2 == 0) goto L_0x0028
            java.lang.String r5 = r4.zzc     // Catch:{ all -> 0x0055 }
            java.lang.String r1 = "OnEventListener already registered."
            android.util.Log.w(r5, r1)     // Catch:{ all -> 0x0055 }
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return
        L_0x0028:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x002b:
            com.google.android.gms.internal.measurement.zzdq$zzb r1 = new com.google.android.gms.internal.measurement.zzdq$zzb     // Catch:{ all -> 0x0055 }
            r1.<init>(r5)     // Catch:{ all -> 0x0055 }
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zziu, com.google.android.gms.internal.measurement.zzdq$zzb>> r2 = r4.zzf     // Catch:{ all -> 0x0055 }
            android.util.Pair r3 = new android.util.Pair     // Catch:{ all -> 0x0055 }
            r3.<init>(r5, r1)     // Catch:{ all -> 0x0055 }
            r2.add(r3)     // Catch:{ all -> 0x0055 }
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            com.google.android.gms.internal.measurement.zzdb r5 = r4.zzj
            if (r5 == 0) goto L_0x004c
            com.google.android.gms.internal.measurement.zzdb r5 = r4.zzj     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x0045 }
            r5.registerOnMeasurementEventListener(r1)     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x0045 }
            return
        L_0x0045:
            java.lang.String r5 = r4.zzc
            java.lang.String r0 = "Failed to register event listener on calling thread. Trying again on the dynamite thread."
            android.util.Log.w(r5, r0)
        L_0x004c:
            com.google.android.gms.internal.measurement.zzew r5 = new com.google.android.gms.internal.measurement.zzew
            r5.<init>(r4, r1)
            r4.zza((com.google.android.gms.internal.measurement.zzdq.zza) r5)
            return
        L_0x0055:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzdq.zza(com.google.android.gms.measurement.internal.zziu):void");
    }

    public final void zzj() {
        zza((zza) new zzdz(this));
    }

    /* access modifiers changed from: private */
    public final void zza(zza zza2) {
        this.zzd.execute(zza2);
    }

    public final void zza(Bundle bundle) {
        zza((zza) new zzdr(this, bundle));
    }

    public final void zzb(Bundle bundle) {
        zza((zza) new zzdx(this, bundle));
    }

    public final void zzc(Bundle bundle) {
        zza((zza) new zzea(this, bundle));
    }

    public final void zza(Activity activity, String str, String str2) {
        zza((zza) new zzdv(this, activity, str, str2));
    }

    public final void zza(boolean z) {
        zza((zza) new zzer(this, z));
    }

    public final void zzd(Bundle bundle) {
        zza((zza) new zzeu(this, bundle));
    }

    public final void zza(zzir zzir) {
        zzc zzc2 = new zzc(zzir);
        if (this.zzj != null) {
            try {
                this.zzj.setEventInterceptor(zzc2);
                return;
            } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                Log.w(this.zzc, "Failed to set event interceptor on calling thread. Trying again on the dynamite thread.");
            }
        }
        zza((zza) new zzel(this, zzc2));
    }

    public final void zza(Boolean bool) {
        zza((zza) new zzdy(this, bool));
    }

    public final void zza(long j) {
        zza((zza) new zzeb(this, j));
    }

    public final void zza(Intent intent) {
        zza((zza) new zzet(this, intent));
    }

    public final void zzd(String str) {
        zza((zza) new zzdw(this, str));
    }

    public final void zzb(String str, String str2) {
        zza((String) null, str, (Object) str2, false);
    }

    public final void zza(String str, String str2, Object obj, boolean z) {
        zza((zza) new zzds(this, str, str2, obj, z));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0043, code lost:
        if (r3.zzj == null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r3.zzj.unregisterOnMeasurementEventListener(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        android.util.Log.w(r3.zzc, "Failed to unregister event listener on calling thread. Trying again on the dynamite thread.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0052, code lost:
        zza((com.google.android.gms.internal.measurement.zzdq.zza) new com.google.android.gms.internal.measurement.zzev(r3, r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.measurement.internal.zziu r4) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zziu, com.google.android.gms.internal.measurement.zzdq$zzb>> r0 = r3.zzf
            monitor-enter(r0)
            r1 = 0
        L_0x0007:
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zziu, com.google.android.gms.internal.measurement.zzdq$zzb>> r2 = r3.zzf     // Catch:{ all -> 0x005b }
            int r2 = r2.size()     // Catch:{ all -> 0x005b }
            if (r1 >= r2) goto L_0x002b
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zziu, com.google.android.gms.internal.measurement.zzdq$zzb>> r2 = r3.zzf     // Catch:{ all -> 0x005b }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x005b }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x005b }
            java.lang.Object r2 = r2.first     // Catch:{ all -> 0x005b }
            boolean r2 = r4.equals(r2)     // Catch:{ all -> 0x005b }
            if (r2 == 0) goto L_0x0028
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zziu, com.google.android.gms.internal.measurement.zzdq$zzb>> r4 = r3.zzf     // Catch:{ all -> 0x005b }
            java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x005b }
            android.util.Pair r4 = (android.util.Pair) r4     // Catch:{ all -> 0x005b }
            goto L_0x002c
        L_0x0028:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x002b:
            r4 = 0
        L_0x002c:
            if (r4 != 0) goto L_0x0037
            java.lang.String r4 = r3.zzc     // Catch:{ all -> 0x005b }
            java.lang.String r1 = "OnEventListener had not been registered."
            android.util.Log.w(r4, r1)     // Catch:{ all -> 0x005b }
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            return
        L_0x0037:
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zziu, com.google.android.gms.internal.measurement.zzdq$zzb>> r1 = r3.zzf     // Catch:{ all -> 0x005b }
            r1.remove(r4)     // Catch:{ all -> 0x005b }
            java.lang.Object r4 = r4.second     // Catch:{ all -> 0x005b }
            com.google.android.gms.internal.measurement.zzdq$zzb r4 = (com.google.android.gms.internal.measurement.zzdq.zzb) r4     // Catch:{ all -> 0x005b }
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            com.google.android.gms.internal.measurement.zzdb r0 = r3.zzj
            if (r0 == 0) goto L_0x0052
            com.google.android.gms.internal.measurement.zzdb r0 = r3.zzj     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x004b }
            r0.unregisterOnMeasurementEventListener(r4)     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x004b }
            return
        L_0x004b:
            java.lang.String r0 = r3.zzc
            java.lang.String r1 = "Failed to unregister event listener on calling thread. Trying again on the dynamite thread."
            android.util.Log.w(r0, r1)
        L_0x0052:
            com.google.android.gms.internal.measurement.zzev r0 = new com.google.android.gms.internal.measurement.zzev
            r0.<init>(r3, r4)
            r3.zza((com.google.android.gms.internal.measurement.zzdq.zza) r0)
            return
        L_0x005b:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzdq.zzb(com.google.android.gms.measurement.internal.zziu):void");
    }

    /* access modifiers changed from: private */
    public final boolean zzc(String str, String str2) {
        return (str2 == null || str == null || zzk()) ? false : true;
    }

    private final boolean zzk() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics", false, getClass().getClassLoader());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static boolean zzb(Context context) {
        try {
            if (new zzhd(context, zzhd.zza(context)).zza("google_app_id") != null) {
                return true;
            }
            return false;
        } catch (IllegalStateException unused) {
        }
    }
}
