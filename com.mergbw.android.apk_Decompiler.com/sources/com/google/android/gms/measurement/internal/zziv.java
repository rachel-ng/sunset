package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.measurement.zzdg;
import com.google.android.gms.internal.measurement.zzne;
import com.google.android.gms.internal.measurement.zznk;
import com.google.android.gms.internal.measurement.zzpg;
import com.google.android.gms.internal.measurement.zzpn;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzin;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zziv extends zze {
    final zzr zza;
    private zzki zzb;
    private zzir zzc;
    private final Set<zziu> zzd = new CopyOnWriteArraySet();
    private boolean zze;
    private final AtomicReference<String> zzf = new AtomicReference<>();
    private final Object zzg = new Object();
    /* access modifiers changed from: private */
    public boolean zzh = false;
    /* access modifiers changed from: private */
    public int zzi = 1;
    private zzat zzj;
    private PriorityQueue<zzmu> zzk;
    private zzin zzl = zzin.zza;
    private final AtomicLong zzm = new AtomicLong(0);
    private long zzn = -1;
    private boolean zzo = true;
    /* access modifiers changed from: private */
    public zzat zzp;
    private SharedPreferences.OnSharedPreferenceChangeListener zzq;
    private zzat zzr;
    private final zznr zzs = new zzka(this);

    public static int zza(String str) {
        Preconditions.checkNotEmpty(str);
        return 25;
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    public final Application.ActivityLifecycleCallbacks zzaa() {
        return this.zzb;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
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

    public final zzaj zzab() {
        zzt();
        return zzo().zzaa();
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

    public final Boolean zzac() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzl().zza(atomicReference, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, "boolean test flag value", new zzjf(this, atomicReference));
    }

    public final Double zzad() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzl().zza(atomicReference, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, "double test flag value", new zzkb(this, atomicReference));
    }

    public final Integer zzae() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzl().zza(atomicReference, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, "int test flag value", new zzkc(this, atomicReference));
    }

    public final Long zzaf() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzl().zza(atomicReference, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, "long test flag value", new zzjz(this, atomicReference));
    }

    public final String zzag() {
        return this.zzf.get();
    }

    public final String zzah() {
        zzkp zzaa = this.zzu.zzq().zzaa();
        if (zzaa != null) {
            return zzaa.zzb;
        }
        return null;
    }

    public final String zzai() {
        zzkp zzaa = this.zzu.zzq().zzaa();
        if (zzaa != null) {
            return zzaa.zza;
        }
        return null;
    }

    public final String zzaj() {
        if (this.zzu.zzu() != null) {
            return this.zzu.zzu();
        }
        try {
            return new zzhd(zza(), this.zzu.zzx()).zza("google_app_id");
        } catch (IllegalStateException e) {
            this.zzu.zzj().zzg().zza("getGoogleAppId failed with exception", e);
            return null;
        }
    }

    public final String zzak() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzl().zza(atomicReference, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, "String test flag value", new zzjs(this, atomicReference));
    }

    public final ArrayList<Bundle> zza(String str, String str2) {
        if (zzl().zzg()) {
            zzj().zzg().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        } else if (zzab.zza()) {
            zzj().zzg().zza("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzu.zzl().zza(atomicReference, 5000, "get conditional user properties", new zzjv(this, atomicReference, (String) null, str, str2));
            List list = (List) atomicReference.get();
            if (list != null) {
                return zznp.zzb((List<zzae>) list);
            }
            zzj().zzg().zza("Timed out waiting for get conditional user properties", (Object) null);
            return new ArrayList<>();
        }
    }

    public final List<zzno> zza(boolean z) {
        zzu();
        zzj().zzp().zza("Getting user properties (FE)");
        if (zzl().zzg()) {
            zzj().zzg().zza("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        } else if (zzab.zza()) {
            zzj().zzg().zza("Cannot get all user properties from main thread");
            return Collections.emptyList();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzu.zzl().zza(atomicReference, 5000, "get user properties", new zzjp(this, atomicReference, z));
            List<zzno> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zzj().zzg().zza("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyList();
        }
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        if (zzl().zzg()) {
            zzj().zzg().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        } else if (zzab.zza()) {
            zzj().zzg().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzu.zzl().zza(atomicReference, 5000, "get user properties", new zzjy(this, atomicReference, (String) null, str, str2, z));
            List<zzno> list = (List) atomicReference.get();
            if (list == null) {
                zzj().zzg().zza("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
                return Collections.emptyMap();
            }
            ArrayMap arrayMap = new ArrayMap(list.size());
            for (zzno zzno : list) {
                Object zza2 = zzno.zza();
                if (zza2 != null) {
                    arrayMap.put(zzno.zza, zza2);
                }
            }
            return arrayMap;
        }
    }

    /* access modifiers changed from: package-private */
    public final PriorityQueue<zzmu> zzal() {
        if (this.zzk == null) {
            Chip$$ExternalSyntheticApiModelOutline0.m$2();
            this.zzk = Chip$$ExternalSyntheticApiModelOutline0.m(Comparator.comparing(new zziy(), new zzix()));
        }
        return this.zzk;
    }

    static /* synthetic */ void zza(zziv zziv, Bundle bundle) {
        Bundle bundle2 = bundle;
        zziv.zzt();
        zziv.zzu();
        Preconditions.checkNotNull(bundle);
        String checkNotEmpty = Preconditions.checkNotEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        if (!zziv.zzu.zzac()) {
            zziv.zzj().zzp().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        zzno zzno = new zzno(checkNotEmpty, 0, (Object) null, "");
        try {
            zzbd zza2 = zziv.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), "", bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, true);
            String string = bundle2.getString("app_id");
            long j = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP);
            boolean z = bundle2.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE);
            zzae zzae = r4;
            zzae zzae2 = new zzae(string, "", zzno, j, z, bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), (zzbd) null, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), (zzbd) null, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zza2);
            zziv.zzo().zza(zzae);
        } catch (IllegalArgumentException unused) {
        }
    }

    static /* synthetic */ void zza(zziv zziv, zzin zzin, zzin zzin2) {
        if (!zznk.zza() || !zziv.zze().zza(zzbf.zzcu)) {
            boolean zza2 = zzin.zza(zzin2, zzin.zza.ANALYTICS_STORAGE, zzin.zza.AD_STORAGE);
            boolean zzb2 = zzin.zzb(zzin2, zzin.zza.ANALYTICS_STORAGE, zzin.zza.AD_STORAGE);
            if (zza2 || zzb2) {
                zziv.zzg().zzag();
            }
        }
    }

    static /* synthetic */ void zzb(zziv zziv, Bundle bundle) {
        Bundle bundle2 = bundle;
        zziv.zzt();
        zziv.zzu();
        Preconditions.checkNotNull(bundle);
        String string = bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        String string2 = bundle2.getString("origin");
        Preconditions.checkNotEmpty(string);
        Preconditions.checkNotEmpty(string2);
        Preconditions.checkNotNull(bundle2.get("value"));
        if (!zziv.zzu.zzac()) {
            zziv.zzj().zzp().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        zzno zzno = new zzno(string, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle2.get("value"), string2);
        try {
            zzbd zza2 = zziv.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), string2, 0, true, true);
            zzbd zza3 = zziv.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), string2, 0, true, true);
            zzbd zza4 = zziv.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), string2, 0, true, true);
            zziv.zzo().zza(new zzae(bundle2.getString("app_id"), string2, zzno, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), zza3, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), zza2, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zza4));
        } catch (IllegalArgumentException unused) {
        }
    }

    static /* synthetic */ void zza(zziv zziv, zzin zzin, long j, boolean z, boolean z2) {
        zziv.zzt();
        zziv.zzu();
        zzin zzn2 = zziv.zzk().zzn();
        if (j <= zziv.zzn && zzin.zza(zzn2.zza(), zzin.zza())) {
            zziv.zzj().zzn().zza("Dropped out-of-date consent setting, proposed settings", zzin);
        } else if (zziv.zzk().zza(zzin)) {
            zziv.zzj().zzp().zza("Setting storage consent(FE)", zzin);
            zziv.zzn = j;
            if (zziv.zzo().zzan()) {
                zziv.zzo().zzb(z);
            } else {
                zziv.zzo().zza(z);
            }
            if (z2) {
                zziv.zzo().zza((AtomicReference<String>) new AtomicReference());
            }
        } else {
            zziv.zzj().zzn().zza("Lower precedence consent source ignored, proposed source", Integer.valueOf(zzin.zza()));
        }
    }

    static /* synthetic */ void zzb(zziv zziv, int i) {
        if (zziv.zzj == null) {
            zziv.zzj = new zzjk(zziv, zziv.zzu);
        }
        zziv.zzj.zza((long) (i * 1000));
    }

    protected zziv(zzhj zzhj) {
        super(zzhj);
        this.zza = new zzr(zzhj);
    }

    public final void zzam() {
        zzt();
        zzu();
        if (this.zzu.zzaf()) {
            Boolean zze2 = zze().zze("google_analytics_deferred_deep_link_enabled");
            if (zze2 != null && zze2.booleanValue()) {
                zzj().zzc().zza("Deferred Deep Link feature enabled.");
                zzl().zzb((Runnable) new zzje(this));
            }
            zzo().zzac();
            this.zzo = false;
            String zzw = zzk().zzw();
            if (!TextUtils.isEmpty(zzw)) {
                zzf().zzac();
                if (!zzw.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzw);
                    zzc("auto", "_ou", bundle);
                }
            }
        }
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

    public final void zza(String str, String str2, Bundle bundle) {
        long currentTimeMillis = zzb().currentTimeMillis();
        Preconditions.checkNotEmpty(str);
        Bundle bundle2 = new Bundle();
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str2 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str2);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzl().zzb((Runnable) new zzjw(this, bundle2));
    }

    public final void zzan() {
        if ((zza().getApplicationContext() instanceof Application) && this.zzb != null) {
            ((Application) zza().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zzb);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzao() {
        if (zzpg.zza() && zze().zza(zzbf.zzca)) {
            if (zzl().zzg()) {
                zzj().zzg().zza("Cannot get trigger URIs from analytics worker thread");
            } else if (zzab.zza()) {
                zzj().zzg().zza("Cannot get trigger URIs from main thread");
            } else {
                zzu();
                zzj().zzp().zza("Getting trigger URIs (FE)");
                AtomicReference atomicReference = new AtomicReference();
                zzl().zza(atomicReference, 5000, "get trigger URIs", new zzja(this, atomicReference));
                List list = (List) atomicReference.get();
                if (list == null) {
                    zzj().zzg().zza("Timed out waiting for get trigger URIs");
                } else {
                    zzl().zzb((Runnable) new zziz(this, list));
                }
            }
        }
    }

    public final void zzap() {
        zzt();
        if (zzk().zzo.zza()) {
            zzj().zzc().zza("Deferred Deep Link already retrieved. Not fetching again.");
            return;
        }
        long zza2 = zzk().zzp.zza();
        zzk().zzp.zza(1 + zza2);
        if (zza2 >= 5) {
            zzj().zzu().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
            zzk().zzo.zza(true);
            return;
        }
        if (this.zzp == null) {
            this.zzp = new zzjr(this, this.zzu);
        }
        this.zzp.zza(0);
    }

    public final void zza(zzdg zzdg) throws RemoteException {
        zzl().zzb((Runnable) new zzjx(this, zzdg));
    }

    public final void zzaq() {
        zzt();
        zzj().zzc().zza("Handle tcf update.");
        zzms zza2 = zzms.zza(zzk().zzc());
        zzj().zzp().zza("Tcf preferences read", zza2);
        if (zzk().zza(zza2)) {
            Bundle zza3 = zza2.zza();
            zzj().zzp().zza("Consent generated from Tcf", zza3);
            if (zza3 != Bundle.EMPTY) {
                zza(zza3, -30, zzb().currentTimeMillis());
            }
            Bundle bundle = new Bundle();
            bundle.putString("_tcfd", zza2.zzb());
            zzc("auto", "_tcf", bundle);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(AtomicReference atomicReference) {
        Bundle zza2 = zzk().zzi.zza();
        zzkx zzo2 = zzo();
        if (zza2 == null) {
            zza2 = new Bundle();
        }
        zzo2.zza((AtomicReference<List<zzmu>>) atomicReference, zza2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(List list) {
        zzt();
        if (Build.VERSION.SDK_INT >= 30) {
            SparseArray<Long> zzh2 = zzk().zzh();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                zzmu zzmu = (zzmu) it.next();
                if (!zzh2.contains(zzmu.zzc) || zzh2.get(zzmu.zzc).longValue() < zzmu.zzb) {
                    zzal().add(zzmu);
                }
            }
            zzar();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        if ("IABTCF_TCString".equals(str)) {
            zzj().zzp().zza("IABTCF_TCString change picked up in listener.");
            ((zzat) Preconditions.checkNotNull(this.zzr)).zza(500);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Bundle bundle, long j) {
        if (TextUtils.isEmpty(zzg().zzae())) {
            zza(bundle, 0, j);
        } else {
            zzj().zzv().zza("Using developer consent only; google app id found");
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Bundle bundle) {
        if (bundle == null) {
            zzk().zzt.zza(new Bundle());
            return;
        }
        Bundle zza2 = zzk().zzt.zza();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                zzq();
                if (zznp.zza(obj)) {
                    zzq();
                    zznp.zza(this.zzs, 27, (String) null, (String) null, 0);
                }
                zzj().zzv().zza("Invalid default event parameter type. Name, value", str, obj);
            } else if (zznp.zzg(str)) {
                zzj().zzv().zza("Invalid default event parameter name. Name", str);
            } else if (obj == null) {
                zza2.remove(str);
            } else if (zzq().zza("param", str, zze().zza((String) null, false), obj)) {
                zzq().zza(zza2, str, obj);
            }
        }
        zzq();
        if (zznp.zza(zza2, zze().zzg())) {
            zzq();
            zznp.zza(this.zzs, 26, (String) null, (String) null, 0);
            zzj().zzv().zza("Too many default event parameters set. Discarding beyond event parameter limit");
        }
        zzk().zzt.zza(zza2);
        zzo().zza(zza2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(String str) {
        if (zzg().zzb(str)) {
            zzg().zzag();
        }
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, zzb().currentTimeMillis());
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        String str3 = str == null ? "app" : str;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        String str4 = str2;
        if (Objects.equals(str2, FirebaseAnalytics.Event.SCREEN_VIEW)) {
            zzn().zza(bundle2, j);
            return;
        }
        long j2 = j;
        zzb(str3, str2, j, bundle2, z2, !z2 || this.zzc == null || zznp.zzg(str2), z, (String) null);
    }

    public final void zza(String str, String str2, Bundle bundle, String str3) {
        zzs();
        zzb(str, str2, zzb().currentTimeMillis(), bundle, false, true, true, str3);
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, bundle, true, false, j);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(String str, String str2, Bundle bundle) {
        zzt();
        zza(str, str2, zzb().currentTimeMillis(), bundle);
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, String str2, long j, Bundle bundle) {
        zzt();
        zza(str, str2, j, bundle, true, this.zzc == null || zznp.zzg(str2), true, (String) null);
    }

    /* access modifiers changed from: protected */
    public final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        boolean z4;
        String str4;
        long j2;
        String str5;
        String str6;
        Class<?> cls;
        String str7 = str;
        String str8 = str2;
        long j3 = j;
        Bundle bundle2 = bundle;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzt();
        zzu();
        if (!this.zzu.zzac()) {
            zzj().zzc().zza("Event not sent since app measurement is disabled");
            return;
        }
        List<String> zzaf = zzg().zzaf();
        if (zzaf == null || zzaf.contains(str8)) {
            int i = 0;
            if (!this.zze) {
                this.zze = true;
                try {
                    if (!this.zzu.zzag()) {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, zza().getClassLoader());
                    } else {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
                    }
                    try {
                        cls.getDeclaredMethod("initialize", new Class[]{Context.class}).invoke((Object) null, new Object[]{zza()});
                    } catch (Exception e) {
                        zzj().zzu().zza("Failed to invoke Tag Manager's initialize() method", e);
                    }
                } catch (ClassNotFoundException unused) {
                    zzj().zzn().zza("Tag Manager is not found and thus will not be used");
                }
            }
            if ("_cmp".equals(str8) && bundle2.containsKey("gclid")) {
                zza("auto", "_lgclid", (Object) bundle2.getString("gclid"), zzb().currentTimeMillis());
            }
            if (z && zznp.zzj(str2)) {
                zzq().zza(bundle2, zzk().zzt.zza());
            }
            if (!z3 && !"_iap".equals(str8)) {
                zznp zzt = this.zzu.zzt();
                int i2 = 2;
                if (zzt.zzc(NotificationCompat.CATEGORY_EVENT, str8)) {
                    if (!zzt.zza(NotificationCompat.CATEGORY_EVENT, zziq.zza, zziq.zzb, str8)) {
                        i2 = 13;
                    } else if (zzt.zza(NotificationCompat.CATEGORY_EVENT, 40, str8)) {
                        i2 = 0;
                    }
                }
                if (i2 != 0) {
                    zzj().zzh().zza("Invalid public event name. Event will not be logged (FE)", zzi().zza(str8));
                    this.zzu.zzt();
                    String zza2 = zznp.zza(str8, 40, true);
                    if (str8 != null) {
                        i = str2.length();
                    }
                    this.zzu.zzt();
                    zznp.zza(this.zzs, i2, "_ev", zza2, i);
                    return;
                }
            }
            zzkp zza3 = zzn().zza(false);
            if (zza3 != null && !bundle2.containsKey("_sc")) {
                zza3.zzd = true;
            }
            zznp.zza(zza3, bundle2, z && !z3);
            boolean equals = "am".equals(str7);
            boolean zzg2 = zznp.zzg(str2);
            if (z && this.zzc != null && !zzg2 && !equals) {
                zzj().zzc().zza("Passing event to registered event handler (FE)", zzi().zza(str8), zzi().zza(bundle2));
                Preconditions.checkNotNull(this.zzc);
                this.zzc.interceptEvent(str, str2, bundle, j);
            } else if (this.zzu.zzaf()) {
                int zza4 = zzq().zza(str8);
                if (zza4 != 0) {
                    zzj().zzh().zza("Invalid event name. Event will not be logged (FE)", zzi().zza(str8));
                    zzq();
                    String zza5 = zznp.zza(str8, 40, true);
                    if (str8 != null) {
                        i = str2.length();
                    }
                    this.zzu.zzt();
                    zznp.zza(this.zzs, str3, zza4, "_ev", zza5, i);
                    return;
                }
                String str9 = "_o";
                Bundle zza6 = zzq().zza(str3, str2, bundle, (List<String>) CollectionUtils.listOf((T[]) new String[]{"_o", "_sn", "_sc", "_si"}), z3);
                Preconditions.checkNotNull(zza6);
                if (zzn().zza(false) != null && "_ae".equals(str8)) {
                    zzmn zzmn = zzp().zzb;
                    long elapsedRealtime = zzmn.zzb.zzb().elapsedRealtime();
                    long j4 = elapsedRealtime - zzmn.zza;
                    zzmn.zza = elapsedRealtime;
                    if (j4 > 0) {
                        zzq().zza(zza6, j4);
                    }
                }
                if (!"auto".equals(str7) && "_ssr".equals(str8)) {
                    zznp zzq2 = zzq();
                    String string = zza6.getString("_ffr");
                    if (Strings.isEmptyOrWhitespace(string)) {
                        string = null;
                    } else if (string != null) {
                        string = string.trim();
                    }
                    if (Objects.equals(string, zzq2.zzk().zzq.zza())) {
                        zzq2.zzj().zzc().zza("Not logging duplicate session_start_with_rollout event");
                        return;
                    }
                    zzq2.zzk().zzq.zza(string);
                } else if ("_ae".equals(str8)) {
                    String zza7 = zzq().zzk().zzq.zza();
                    if (!TextUtils.isEmpty(zza7)) {
                        zza6.putString("_ffr", zza7);
                    }
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(zza6);
                if (zze().zza(zzbf.zzcg)) {
                    z4 = zzp().zzaa();
                } else {
                    z4 = zzk().zzn.zza();
                }
                if (zzk().zzk.zza() <= 0 || !zzk().zza(j3) || !z4) {
                    str4 = "_ae";
                    j2 = 0;
                } else {
                    zzj().zzp().zza("Current session is expired, remove the session number, ID, and engagement time");
                    j2 = 0;
                    str4 = "_ae";
                    zza("auto", "_sid", (Object) null, zzb().currentTimeMillis());
                    zza("auto", "_sno", (Object) null, zzb().currentTimeMillis());
                    zza("auto", "_se", (Object) null, zzb().currentTimeMillis());
                    zzk().zzl.zza(0);
                }
                if (zza6.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, j2) == 1) {
                    zzj().zzp().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                    this.zzu.zzs().zza.zza(j3, true);
                }
                ArrayList arrayList2 = new ArrayList(zza6.keySet());
                Collections.sort(arrayList2);
                ArrayList arrayList3 = arrayList2;
                int size = arrayList2.size();
                int i3 = 0;
                while (i3 < size) {
                    Object obj = arrayList2.get(i3);
                    i3++;
                    String str10 = (String) obj;
                    if (str10 != null) {
                        zzq();
                        Bundle[] zzb2 = zznp.zzb(zza6.get(str10));
                        if (zzb2 != null) {
                            zza6.putParcelableArray(str10, zzb2);
                        }
                    }
                }
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    Bundle bundle3 = (Bundle) arrayList.get(i4);
                    if (i4 != 0) {
                        str6 = "_ep";
                        str5 = str;
                    } else {
                        str5 = str;
                        str6 = str2;
                    }
                    bundle3.putString(str9, str5);
                    if (z2) {
                        bundle3 = zzq().zza(bundle3, (String) null);
                    }
                    Bundle bundle4 = bundle3;
                    Bundle bundle5 = bundle4;
                    zzbd zzbd = r1;
                    zzbd zzbd2 = new zzbd(str6, new zzbc(bundle4), str, j);
                    zzo().zza(zzbd, str3);
                    if (!equals) {
                        for (zziu onEvent : this.zzd) {
                            onEvent.onEvent(str, str2, new Bundle(bundle5), j);
                            String str11 = str3;
                        }
                    }
                }
                if (zzn().zza(false) != null && str4.equals(str2)) {
                    zzp().zza(true, true, zzb().elapsedRealtime());
                }
            }
        } else {
            zzj().zzc().zza("Dropping non-safelisted event. event name, origin", str8, str7);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzar() {
        zzmu poll;
        MeasurementManagerFutures zzn2;
        zzt();
        if (!zzal().isEmpty() && !this.zzh && (poll = zzal().poll()) != null && (zzn2 = zzq().zzn()) != null) {
            this.zzh = true;
            zzj().zzp().zza("Registering trigger URI", poll.zza);
            ListenableFuture<Unit> registerTriggerAsync = zzn2.registerTriggerAsync(Uri.parse(poll.zza));
            if (registerTriggerAsync == null) {
                this.zzh = false;
                zzal().add(poll);
                return;
            }
            if (!zze().zza(zzbf.zzcf)) {
                SparseArray<Long> zzh2 = zzk().zzh();
                zzh2.put(poll.zzc, Long.valueOf(poll.zzb));
                zzk().zza(zzh2);
            }
            Futures.addCallback(registerTriggerAsync, new zzjh(this, poll), new zzji(this));
        }
    }

    public final void zza(zziu zziu) {
        zzu();
        Preconditions.checkNotNull(zziu);
        if (!this.zzd.add(zziu)) {
            zzj().zzu().zza("OnEventListener already registered");
        }
    }

    public final void zzas() {
        zzt();
        zzj().zzc().zza("Register tcfPrefChangeListener.");
        if (this.zzq == null) {
            this.zzr = new zzjo(this, this.zzu);
            this.zzq = new zzjd(this);
        }
        zzk().zzc().registerOnSharedPreferenceChangeListener(this.zzq);
    }

    public final void zza(long j) {
        zzc((String) null);
        zzl().zzb((Runnable) new zzju(this, j));
    }

    /* access modifiers changed from: package-private */
    public final void zzb(long j) {
        zza(j, true);
    }

    /* access modifiers changed from: package-private */
    public final void zza(long j, boolean z) {
        zzt();
        zzu();
        zzj().zzc().zza("Resetting analytics data (FE)");
        zzmh zzp2 = zzp();
        zzp2.zzt();
        zzp2.zzb.zza();
        zzg().zzag();
        boolean zzac = this.zzu.zzac();
        zzgh zzk2 = zzk();
        zzk2.zzc.zza(j);
        if (!TextUtils.isEmpty(zzk2.zzk().zzq.zza())) {
            zzk2.zzq.zza((String) null);
        }
        zzk2.zzk.zza(0);
        zzk2.zzl.zza(0);
        if (!zzk2.zze().zzw()) {
            zzk2.zzb(!zzac);
        }
        zzk2.zzr.zza((String) null);
        zzk2.zzs.zza(0);
        zzk2.zzt.zza((Bundle) null);
        if (z) {
            zzo().zzah();
        }
        zzp().zza.zza();
        this.zzo = !zzac;
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzl().zzb((Runnable) new zzjn(this, str, str2, j, zznp.zza(bundle), z, z2, z3, str3));
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzl().zzb((Runnable) new zzjq(this, str, str2, obj, j));
    }

    public final void zzb(boolean z) {
        if (zza().getApplicationContext() instanceof Application) {
            Application application = (Application) zza().getApplicationContext();
            if (this.zzb == null) {
                this.zzb = new zzki(this);
            }
            if (z) {
                application.unregisterActivityLifecycleCallbacks(this.zzb);
                application.registerActivityLifecycleCallbacks(this.zzb);
                zzj().zzp().zza("Registered activity lifecycle callback");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(String str) {
        this.zzf.set(str);
    }

    public final void zzb(Bundle bundle) {
        zzb(bundle, zzb().currentTimeMillis());
    }

    public final void zzb(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            zzj().zzu().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        Preconditions.checkNotNull(bundle2);
        zzik.zza(bundle2, "app_id", String.class, null);
        zzik.zza(bundle2, "origin", String.class, null);
        zzik.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.NAME, String.class, null);
        zzik.zza(bundle2, "value", Object.class, null);
        zzik.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzik.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzik.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzik.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzik.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzik.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzik.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzik.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzik.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        Preconditions.checkNotEmpty(bundle2.getString("origin"));
        Preconditions.checkNotNull(bundle2.get("value"));
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        Object obj = bundle2.get("value");
        if (zzq().zzb(string) != 0) {
            zzj().zzg().zza("Invalid conditional user property name", zzi().zzc(string));
        } else if (zzq().zza(string, obj) != 0) {
            zzj().zzg().zza("Invalid conditional user property value", zzi().zzc(string), obj);
        } else {
            Object zzc2 = zzq().zzc(string, obj);
            if (zzc2 == null) {
                zzj().zzg().zza("Unable to normalize conditional user property value", zzi().zzc(string), obj);
                return;
            }
            zzik.zza(bundle2, zzc2);
            long j2 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            if (TextUtils.isEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME)) || (j2 <= 15552000000L && j2 >= 1)) {
                long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
                if (j3 > 15552000000L || j3 < 1) {
                    zzj().zzg().zza("Invalid conditional user property time to live", zzi().zzc(string), Long.valueOf(j3));
                } else {
                    zzl().zzb((Runnable) new zzjt(this, bundle2));
                }
            } else {
                zzj().zzg().zza("Invalid conditional user property timeout", zzi().zzc(string), Long.valueOf(j2));
            }
        }
    }

    public final void zzc(Bundle bundle, long j) {
        zzl().zzc((Runnable) new zzjb(this, bundle, j));
    }

    private final void zza(Bundle bundle, int i, long j) {
        String str;
        zzu();
        String zza2 = zzin.zza(bundle);
        if (zza2 != null) {
            zzj().zzv().zza("Ignoring invalid consent setting", zza2);
            zzj().zzv().zza("Valid consent values are 'granted', 'denied'");
        }
        boolean zzg2 = zzl().zzg();
        zzin zza3 = zzin.zza(bundle, i);
        if (zza3.zzk()) {
            zza(zza3, j, zzg2);
        }
        zzav zza4 = zzav.zza(bundle, i);
        if (zza4.zzg()) {
            zza(zza4, zzg2);
        }
        Boolean zza5 = zzav.zza(bundle);
        if (zza5 != null) {
            if (i == -30) {
                str = "tcf";
            } else {
                str = "app";
            }
            zza(str, FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS, (Object) zza5.toString(), false);
        }
    }

    public final void zzd(Bundle bundle, long j) {
        zza(bundle, -20, j);
    }

    public final void zzc(boolean z) {
        zzu();
        zzl().zzb((Runnable) new zzjj(this, z));
    }

    public final void zzc(Bundle bundle) {
        zzl().zzb((Runnable) new zzjc(this, bundle == null ? null : new Bundle(bundle)));
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzav zzav, boolean z) {
        zzkd zzkd = new zzkd(this, zzav);
        if (z) {
            zzt();
            zzkd.run();
            return;
        }
        zzl().zzb((Runnable) zzkd);
    }

    public final void zza(zzir zzir) {
        zzir zzir2;
        zzt();
        zzu();
        if (!(zzir == null || zzir == (zzir2 = this.zzc))) {
            Preconditions.checkState(zzir2 == null, "EventInterceptor already set.");
        }
        this.zzc = zzir;
    }

    public final void zza(Boolean bool) {
        zzu();
        zzl().zzb((Runnable) new zzke(this, bool));
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzin zzin) {
        zzt();
        boolean z = (zzin.zzj() && zzin.zzi()) || zzo().zzam();
        if (z != this.zzu.zzad()) {
            this.zzu.zzb(z);
            Boolean zzu = zzk().zzu();
            if (!z || zzu == null || zzu.booleanValue()) {
                zza(Boolean.valueOf(z), false);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zza(Boolean bool, boolean z) {
        zzt();
        zzu();
        zzj().zzc().zza("Setting app measurement enabled (FE)", bool);
        zzk().zza(bool);
        if (z) {
            zzk().zzb(bool);
        }
        if (this.zzu.zzad() || (bool != null && !bool.booleanValue())) {
            zzat();
        }
    }

    public final void zzc(long j) {
        zzl().zzb((Runnable) new zzjl(this, j));
    }

    public final void zza(Intent intent) {
        if (zzpn.zza() && zze().zza(zzbf.zzbt)) {
            Uri data = intent.getData();
            if (data == null) {
                zzj().zzn().zza("Activity intent has no data. Preview Mode was not enabled.");
                return;
            }
            String queryParameter = data.getQueryParameter("sgtm_debug_enable");
            if (queryParameter == null || !queryParameter.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                zzj().zzn().zza("Preview Mode was not enabled.");
                zze().zzh((String) null);
                return;
            }
            String queryParameter2 = data.getQueryParameter("sgtm_preview_key");
            if (!TextUtils.isEmpty(queryParameter2)) {
                zzj().zzn().zza("Preview Mode was enabled. Using the sgtmPreviewKey: ", queryParameter2);
                zze().zzh(queryParameter2);
            }
        }
    }

    public final void zza(zzin zzin, long j, boolean z) {
        zzin zzin2;
        boolean z2;
        boolean z3;
        boolean z4;
        zzin zzin3 = zzin;
        zzu();
        int zza2 = zzin.zza();
        if (!zzne.zza() || !zze().zza(zzbf.zzcq)) {
            if (zza2 != -10 && zzin.zze() == null && zzin.zzf() == null) {
                zzj().zzv().zza("Discarding empty consent settings");
                return;
            }
        } else if (zza2 != -10 && zzin.zzc() == zzim.UNINITIALIZED && zzin.zzd() == zzim.UNINITIALIZED) {
            zzj().zzv().zza("Ignoring empty consent settings");
            return;
        }
        synchronized (this.zzg) {
            zzin2 = this.zzl;
            z2 = false;
            if (zzin.zza(zza2, zzin2.zza())) {
                z4 = zzin.zzc(this.zzl);
                if (zzin.zzj() && !this.zzl.zzj()) {
                    z2 = true;
                }
                zzin3 = zzin.zzb(this.zzl);
                this.zzl = zzin3;
                z3 = z2;
                z2 = true;
            } else {
                z4 = false;
                z3 = false;
            }
        }
        if (!z2) {
            zzj().zzn().zza("Ignoring lower-priority consent settings, proposed settings", zzin3);
            return;
        }
        long andIncrement = this.zzm.getAndIncrement();
        if (z4) {
            zzc((String) null);
            zzkg zzkg = new zzkg(this, zzin3, j, andIncrement, z3, zzin2);
            if (z) {
                zzt();
                zzkg.run();
                return;
            }
            zzl().zzc((Runnable) zzkg);
            return;
        }
        zzkf zzkf = new zzkf(this, zzin3, andIncrement, z3, zzin2);
        if (z) {
            zzt();
            zzkf.run();
        } else if (zza2 == 30 || zza2 == -10) {
            zzl().zzc((Runnable) zzkf);
        } else {
            zzl().zzb((Runnable) zzkf);
        }
    }

    public final void zza(String str, long j) {
        if (str == null || !TextUtils.isEmpty(str)) {
            zzl().zzb((Runnable) new zzjg(this, str));
            zza((String) null, "_id", (Object) str, true, j);
            return;
        }
        this.zzu.zzj().zzu().zza("User ID must be non-empty or null");
    }

    public final void zza(String str, String str2, Object obj, boolean z) {
        zza(str, str2, obj, z, zzb().currentTimeMillis());
    }

    public final void zza(String str, String str2, Object obj, boolean z, long j) {
        int i;
        if (str == null) {
            str = "app";
        }
        String str3 = str;
        int i2 = 0;
        if (z) {
            i = zzq().zzb(str2);
        } else {
            zznp zzq2 = zzq();
            if (zzq2.zzc("user property", str2)) {
                if (!zzq2.zza("user property", zzis.zza, str2)) {
                    i = 15;
                } else if (zzq2.zza("user property", 24, str2)) {
                    i = 0;
                }
            }
            i = 6;
        }
        if (i != 0) {
            zzq();
            String zza2 = zznp.zza(str2, 24, true);
            if (str2 != null) {
                i2 = str2.length();
            }
            this.zzu.zzt();
            zznp.zza(this.zzs, i, "_ev", zza2, i2);
        } else if (obj != null) {
            int zza3 = zzq().zza(str2, obj);
            if (zza3 != 0) {
                zzq();
                String zza4 = zznp.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i2 = String.valueOf(obj).length();
                }
                this.zzu.zzt();
                zznp.zza(this.zzs, zza3, "_ev", zza4, i2);
                return;
            }
            Object zzc2 = zzq().zzc(str2, obj);
            if (zzc2 != null) {
                zza(str3, str2, j, zzc2);
            }
        } else {
            zza(str3, str2, j, (Object) null);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, String str2, Object obj, long j) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzu();
        if (FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS.equals(str2)) {
            if (obj instanceof String) {
                String str3 = (String) obj;
                if (!TextUtils.isEmpty(str3)) {
                    String str4 = "false";
                    Long valueOf = Long.valueOf(str4.equals(str3.toLowerCase(Locale.ENGLISH)) ? 1 : 0);
                    zzgn zzgn = zzk().zzh;
                    Long l = valueOf;
                    if (valueOf.longValue() == 1) {
                        str4 = "true";
                    }
                    zzgn.zza(str4);
                    obj = valueOf;
                    str2 = "_npa";
                    zzj().zzp().zza("Setting user property(FE)", "non_personalized_ads(_npa)", obj);
                }
            }
            if (obj == null) {
                zzk().zzh.zza("unset");
                str2 = "_npa";
            }
            zzj().zzp().zza("Setting user property(FE)", "non_personalized_ads(_npa)", obj);
        }
        String str5 = str2;
        Object obj2 = obj;
        if (!this.zzu.zzac()) {
            zzj().zzp().zza("User property not set since app measurement is disabled");
        } else if (this.zzu.zzaf()) {
            zzo().zza(new zzno(str5, j, obj2, str));
        }
    }

    public final void zzb(zziu zziu) {
        zzu();
        Preconditions.checkNotNull(zziu);
        if (!this.zzd.remove(zziu)) {
            zzj().zzu().zza("OnEventListener had not been registered");
        }
    }

    /* access modifiers changed from: private */
    public final void zzat() {
        zzt();
        String zza2 = zzk().zzh.zza();
        if (zza2 != null) {
            if ("unset".equals(zza2)) {
                zza("app", "_npa", (Object) null, zzb().currentTimeMillis());
            } else {
                zza("app", "_npa", (Object) Long.valueOf("true".equals(zza2) ? 1 : 0), zzb().currentTimeMillis());
            }
        }
        if (!this.zzu.zzac() || !this.zzo) {
            zzj().zzc().zza("Updating Scion state (FE)");
            zzo().zzaj();
            return;
        }
        zzj().zzc().zza("Recording app launch after enabling measurement for the first time (FE)");
        zzam();
        zzp().zza.zza();
        zzl().zzb((Runnable) new zzjm(this));
    }
}
