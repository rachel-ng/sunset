package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzdo;
import com.google.android.gms.internal.measurement.zzgz;
import com.google.android.gms.internal.measurement.zzoj;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public class zzhj implements zzil {
    private static volatile zzhj zzb;
    final long zza;
    private Boolean zzaa;
    private long zzab;
    private volatile Boolean zzac;
    private Boolean zzad;
    private Boolean zzae;
    private volatile boolean zzaf;
    private int zzag;
    private int zzah;
    private AtomicInteger zzai = new AtomicInteger(0);
    private final Context zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final boolean zzg;
    private final zzab zzh;
    private final zzag zzi;
    private final zzgh zzj;
    private final zzfw zzk;
    private final zzhc zzl;
    private final zzmh zzm;
    private final zznp zzn;
    private final zzfr zzo;
    private final Clock zzp;
    private final zzks zzq;
    private final zziv zzr;
    private final zzb zzs;
    private final zzkj zzt;
    private final String zzu;
    private zzfp zzv;
    private zzkx zzw;
    private zzax zzx;
    private zzfq zzy;
    private boolean zzz = false;

    public final int zzc() {
        zzl().zzt();
        if (this.zzi.zzw()) {
            return 1;
        }
        Boolean bool = this.zzae;
        if (bool != null && bool.booleanValue()) {
            return 2;
        }
        if (!zzad()) {
            return 8;
        }
        Boolean zzv2 = zzn().zzv();
        if (zzv2 == null) {
            Boolean zze2 = this.zzi.zze("firebase_analytics_collection_enabled");
            if (zze2 == null) {
                Boolean bool2 = this.zzad;
                if (bool2 != null) {
                    if (bool2.booleanValue()) {
                        return 0;
                    }
                    return 5;
                } else if (this.zzac == null || this.zzac.booleanValue()) {
                    return 0;
                } else {
                    return 7;
                }
            } else if (zze2.booleanValue()) {
                return 0;
            } else {
                return 4;
            }
        } else if (zzv2.booleanValue()) {
            return 0;
        } else {
            return 3;
        }
    }

    @Pure
    public final Context zza() {
        return this.zzc;
    }

    @Pure
    public final Clock zzb() {
        return this.zzp;
    }

    @Pure
    public final zzb zze() {
        zzb zzb2 = this.zzs;
        if (zzb2 != null) {
            return zzb2;
        }
        throw new IllegalStateException("Component not created");
    }

    @Pure
    public final zzab zzd() {
        return this.zzh;
    }

    @Pure
    public final zzag zzf() {
        return this.zzi;
    }

    @Pure
    public final zzax zzg() {
        zza((zzii) this.zzx);
        return this.zzx;
    }

    @Pure
    public final zzfq zzh() {
        zza((zze) this.zzy);
        return this.zzy;
    }

    @Pure
    public final zzfp zzi() {
        zza((zze) this.zzv);
        return this.zzv;
    }

    @Pure
    public final zzfr zzk() {
        return this.zzo;
    }

    @Pure
    public final zzfw zzj() {
        zza((zzii) this.zzk);
        return this.zzk;
    }

    public final zzfw zzm() {
        zzfw zzfw = this.zzk;
        if (zzfw == null || !zzfw.zzaf()) {
            return null;
        }
        return this.zzk;
    }

    @Pure
    public final zzgh zzn() {
        zza((zzij) this.zzj);
        return this.zzj;
    }

    @Pure
    public final zzhc zzl() {
        zza((zzii) this.zzl);
        return this.zzl;
    }

    /* access modifiers changed from: package-private */
    @SideEffectFree
    public final zzhc zzo() {
        return this.zzl;
    }

    public static zzhj zza(Context context, zzdo zzdo, Long l) {
        if (zzdo != null && (zzdo.zze == null || zzdo.zzf == null)) {
            zzdo = new zzdo(zzdo.zza, zzdo.zzb, zzdo.zzc, zzdo.zzd, (String) null, (String) null, zzdo.zzg, (String) null);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzhj.class) {
                if (zzb == null) {
                    zzb = new zzhj(new zzit(context, zzdo, l));
                }
            }
        } else if (!(zzdo == null || zzdo.zzg == null || !zzdo.zzg.containsKey("dataCollectionDefaultEnabled"))) {
            Preconditions.checkNotNull(zzb);
            zzb.zza(zzdo.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(zzb);
        return zzb;
    }

    @Pure
    public final zziv zzp() {
        zza((zze) this.zzr);
        return this.zzr;
    }

    @Pure
    private final zzkj zzai() {
        zza((zzii) this.zzt);
        return this.zzt;
    }

    @Pure
    public final zzks zzq() {
        zza((zze) this.zzq);
        return this.zzq;
    }

    @Pure
    public final zzkx zzr() {
        zza((zze) this.zzw);
        return this.zzw;
    }

    @Pure
    public final zzmh zzs() {
        zza((zze) this.zzm);
        return this.zzm;
    }

    @Pure
    public final zznp zzt() {
        zza((zzij) this.zzn);
        return this.zzn;
    }

    @Pure
    public final String zzu() {
        return this.zzd;
    }

    @Pure
    public final String zzv() {
        return this.zze;
    }

    @Pure
    public final String zzw() {
        return this.zzf;
    }

    @Pure
    public final String zzx() {
        return this.zzu;
    }

    static /* synthetic */ void zza(zzhj zzhj, zzit zzit) {
        zzhj.zzl().zzt();
        zzax zzax = new zzax(zzhj);
        zzax.zzad();
        zzhj.zzx = zzax;
        zzfq zzfq = new zzfq(zzhj, zzit.zzf);
        zzfq.zzv();
        zzhj.zzy = zzfq;
        zzfp zzfp = new zzfp(zzhj);
        zzfp.zzv();
        zzhj.zzv = zzfp;
        zzkx zzkx = new zzkx(zzhj);
        zzkx.zzv();
        zzhj.zzw = zzkx;
        zzhj.zzn.zzae();
        zzhj.zzj.zzae();
        zzhj.zzy.zzw();
        zzhj.zzj().zzn().zza("App measurement initialized, version", 97001L);
        zzhj.zzj().zzn().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String zzad2 = zzfq.zzad();
        if (TextUtils.isEmpty(zzhj.zzd)) {
            if (zzhj.zzt().zzd(zzad2, zzhj.zzi.zzp())) {
                zzhj.zzj().zzn().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            } else {
                zzfy zzn2 = zzhj.zzj().zzn();
                zzn2.zza("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app " + zzad2);
            }
        }
        zzhj.zzj().zzc().zza("Debug-level message logging enabled");
        if (zzhj.zzag != zzhj.zzai.get()) {
            zzhj.zzj().zzg().zza("Not all components initialized", Integer.valueOf(zzhj.zzag), Integer.valueOf(zzhj.zzai.get()));
        }
        zzhj.zzz = true;
    }

    private zzhj(zzit zzit) {
        long j;
        boolean z = false;
        Preconditions.checkNotNull(zzit);
        zzab zzab2 = new zzab(zzit.zza);
        this.zzh = zzab2;
        zzfk.zza = zzab2;
        Context context = zzit.zza;
        this.zzc = context;
        this.zzd = zzit.zzb;
        this.zze = zzit.zzc;
        this.zzf = zzit.zzd;
        this.zzg = zzit.zzh;
        this.zzac = zzit.zze;
        this.zzu = zzit.zzj;
        this.zzaf = true;
        zzdo zzdo = zzit.zzg;
        if (!(zzdo == null || zzdo.zzg == null)) {
            Object obj = zzdo.zzg.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zzad = (Boolean) obj;
            }
            Object obj2 = zzdo.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzae = (Boolean) obj2;
            }
        }
        zzgz.zzb(context);
        Clock instance = DefaultClock.getInstance();
        this.zzp = instance;
        if (zzit.zzi != null) {
            j = zzit.zzi.longValue();
        } else {
            j = instance.currentTimeMillis();
        }
        this.zza = j;
        this.zzi = new zzag(this);
        zzgh zzgh = new zzgh(this);
        zzgh.zzad();
        this.zzj = zzgh;
        zzfw zzfw = new zzfw(this);
        zzfw.zzad();
        this.zzk = zzfw;
        zznp zznp = new zznp(this);
        zznp.zzad();
        this.zzn = zznp;
        this.zzo = new zzfr(new zziw(zzit, this));
        this.zzs = new zzb(this);
        zzks zzks = new zzks(this);
        zzks.zzv();
        this.zzq = zzks;
        zziv zziv = new zziv(this);
        zziv.zzv();
        this.zzr = zziv;
        zzmh zzmh = new zzmh(this);
        zzmh.zzv();
        this.zzm = zzmh;
        zzkj zzkj = new zzkj(this);
        zzkj.zzad();
        this.zzt = zzkj;
        zzhc zzhc = new zzhc(this);
        zzhc.zzad();
        this.zzl = zzhc;
        if (!(zzit.zzg == null || zzit.zzg.zzb == 0)) {
            z = true;
        }
        boolean z2 = !z;
        if (context.getApplicationContext() instanceof Application) {
            zzp().zzb(z2);
        } else {
            zzj().zzu().zza("Application context is not an Application");
        }
        zzhc.zzb((Runnable) new zzhk(this, zzit));
    }

    private static void zza(zzij zzij) {
        if (zzij == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static void zza(zze zze2) {
        if (zze2 == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zze2.zzy()) {
            String valueOf = String.valueOf(zze2.getClass());
            throw new IllegalStateException("Component not initialized: " + valueOf);
        }
    }

    private static void zza(zzii zzii) {
        if (zzii == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzii.zzaf()) {
            String valueOf = String.valueOf(zzii.getClass());
            throw new IllegalStateException("Component not initialized: " + valueOf);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzy() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: package-private */
    public final void zzz() {
        this.zzai.incrementAndGet();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str, int i, Throwable th, byte[] bArr, Map map) {
        if ((i == 200 || i == 204 || i == 304) && th == null) {
            zzn().zzo.zza(true);
            if (bArr == null || bArr.length == 0) {
                zzj().zzc().zza("Deferred Deep Link response empty.");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr));
                String optString = jSONObject.optString("deeplink", "");
                if (TextUtils.isEmpty(optString)) {
                    zzj().zzc().zza("Deferred Deep Link is empty.");
                    return;
                }
                String optString2 = jSONObject.optString("gclid", "");
                String optString3 = jSONObject.optString("gbraid", "");
                String optString4 = jSONObject.optString("gad_source", "");
                double optDouble = jSONObject.optDouble("timestamp", 0.0d);
                Bundle bundle = new Bundle();
                if (!zzoj.zza() || !this.zzi.zza(zzbf.zzcl)) {
                    if (!zzt().zzi(optString)) {
                        zzj().zzu().zza("Deferred Deep Link validation failed. gclid, deep link", optString2, optString);
                        return;
                    }
                } else if (!zzt().zzi(optString)) {
                    zzj().zzu().zza("Deferred Deep Link validation failed. gclid, gbraid, deep link", optString2, optString3, optString);
                    return;
                } else {
                    if (!TextUtils.isEmpty(optString3)) {
                        bundle.putString("gbraid", optString3);
                    }
                    if (!TextUtils.isEmpty(optString4)) {
                        bundle.putString("gad_source", optString4);
                    }
                }
                if (zzoj.zza()) {
                    this.zzi.zza(zzbf.zzcl);
                }
                bundle.putString("gclid", optString2);
                bundle.putString("_cis", "ddp");
                this.zzr.zzc("auto", "_cmp", bundle);
                zznp zzt2 = zzt();
                if (!TextUtils.isEmpty(optString) && zzt2.zza(optString, optDouble)) {
                    zzt2.zza().sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
                }
            } catch (JSONException e) {
                zzj().zzg().zza("Failed to parse the Deferred Deep Link response. exception", e);
            }
        } else {
            zzj().zzu().zza("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzaa() {
        this.zzag++;
    }

    /* access modifiers changed from: package-private */
    public final void zza(boolean z) {
        this.zzac = Boolean.valueOf(z);
    }

    public final void zzb(boolean z) {
        zzl().zzt();
        this.zzaf = z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ee, code lost:
        if (r1.zzk() != false) goto L_0x0167;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0163, code lost:
        if (r1.zzk() != false) goto L_0x0167;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x02a0  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x02a2  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x02a8  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x02d3  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0300  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0385  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0211 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0250  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.measurement.zzdo r13) {
        /*
            r12 = this;
            com.google.android.gms.measurement.internal.zzhc r0 = r12.zzl()
            r0.zzt()
            boolean r0 = com.google.android.gms.internal.measurement.zzpg.zza()
            if (r0 == 0) goto L_0x004e
            com.google.android.gms.measurement.internal.zzag r0 = r12.zzi
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzbf.zzca
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r1)
            if (r0 == 0) goto L_0x004e
            com.google.android.gms.measurement.internal.zznp r0 = r12.zzt()
            boolean r0 = r0.zzw()
            if (r0 == 0) goto L_0x004e
            com.google.android.gms.measurement.internal.zznp r0 = r12.zzt()
            r0.zzt()
            android.content.IntentFilter r1 = new android.content.IntentFilter
            r1.<init>()
            java.lang.String r2 = "com.google.android.gms.measurement.TRIGGERS_AVAILABLE"
            r1.addAction(r2)
            com.google.android.gms.measurement.internal.zzq r2 = new com.google.android.gms.measurement.internal.zzq
            com.google.android.gms.measurement.internal.zzhj r3 = r0.zzu
            r2.<init>(r3)
            android.content.Context r3 = r0.zza()
            r4 = 2
            androidx.core.content.ContextCompat.registerReceiver(r3, r2, r1, r4)
            com.google.android.gms.measurement.internal.zzfw r0 = r0.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzc()
            java.lang.String r1 = "Registered app receiver"
            r0.zza(r1)
        L_0x004e:
            com.google.android.gms.measurement.internal.zzgh r0 = r12.zzn()
            com.google.android.gms.measurement.internal.zzin r0 = r0.zzn()
            int r1 = r0.zza()
            boolean r2 = com.google.android.gms.internal.measurement.zzne.zza()
            r3 = 40
            r4 = 10
            java.lang.String r5 = "google_analytics_default_allow_analytics_storage"
            java.lang.String r6 = "google_analytics_default_allow_ad_storage"
            r7 = 0
            r8 = 0
            r9 = -10
            r10 = 30
            if (r2 == 0) goto L_0x00f2
            com.google.android.gms.measurement.internal.zzag r2 = r12.zzi
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzbf.zzcq
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r11)
            if (r2 == 0) goto L_0x00f2
            com.google.android.gms.measurement.internal.zzag r2 = r12.zzi
            com.google.android.gms.measurement.internal.zzim r2 = r2.zzc((java.lang.String) r6, (boolean) r7)
            com.google.android.gms.measurement.internal.zzag r6 = r12.zzi
            com.google.android.gms.measurement.internal.zzim r5 = r6.zzc((java.lang.String) r5, (boolean) r7)
            com.google.android.gms.measurement.internal.zzim r6 = com.google.android.gms.measurement.internal.zzim.UNINITIALIZED
            if (r2 != r6) goto L_0x008c
            com.google.android.gms.measurement.internal.zzim r6 = com.google.android.gms.measurement.internal.zzim.UNINITIALIZED
            if (r5 == r6) goto L_0x009c
        L_0x008c:
            com.google.android.gms.measurement.internal.zzgh r6 = r12.zzn()
            boolean r6 = r6.zza((int) r9)
            if (r6 == 0) goto L_0x009c
            com.google.android.gms.measurement.internal.zzin r1 = com.google.android.gms.measurement.internal.zzin.zza(r2, r5, r9)
            goto L_0x0167
        L_0x009c:
            com.google.android.gms.measurement.internal.zzfq r2 = r12.zzh()
            java.lang.String r2 = r2.zzae()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x00c6
            if (r1 == 0) goto L_0x00b6
            if (r1 == r10) goto L_0x00b6
            if (r1 == r4) goto L_0x00b6
            if (r1 == r10) goto L_0x00b6
            if (r1 == r10) goto L_0x00b6
            if (r1 != r3) goto L_0x00c6
        L_0x00b6:
            com.google.android.gms.measurement.internal.zziv r1 = r12.zzp()
            com.google.android.gms.measurement.internal.zzin r2 = new com.google.android.gms.measurement.internal.zzin
            r2.<init>(r8, r8, r9)
            long r3 = r12.zza
            r1.zza((com.google.android.gms.measurement.internal.zzin) r2, (long) r3, (boolean) r7)
            goto L_0x0166
        L_0x00c6:
            com.google.android.gms.measurement.internal.zzfq r1 = r12.zzh()
            java.lang.String r1 = r1.zzae()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0166
            if (r13 == 0) goto L_0x0166
            android.os.Bundle r1 = r13.zzg
            if (r1 == 0) goto L_0x0166
            com.google.android.gms.measurement.internal.zzgh r1 = r12.zzn()
            boolean r1 = r1.zza((int) r10)
            if (r1 == 0) goto L_0x0166
            android.os.Bundle r1 = r13.zzg
            com.google.android.gms.measurement.internal.zzin r1 = com.google.android.gms.measurement.internal.zzin.zza((android.os.Bundle) r1, (int) r10)
            boolean r2 = r1.zzk()
            if (r2 == 0) goto L_0x0166
            goto L_0x0167
        L_0x00f2:
            com.google.android.gms.measurement.internal.zzag r2 = r12.zzi
            java.lang.Boolean r2 = r2.zze(r6)
            com.google.android.gms.measurement.internal.zzag r6 = r12.zzi
            java.lang.Boolean r5 = r6.zze(r5)
            if (r2 != 0) goto L_0x0102
            if (r5 == 0) goto L_0x0112
        L_0x0102:
            com.google.android.gms.measurement.internal.zzgh r6 = r12.zzn()
            boolean r6 = r6.zza((int) r9)
            if (r6 == 0) goto L_0x0112
            com.google.android.gms.measurement.internal.zzin r1 = new com.google.android.gms.measurement.internal.zzin
            r1.<init>(r2, r5, r9)
            goto L_0x0167
        L_0x0112:
            com.google.android.gms.measurement.internal.zzfq r2 = r12.zzh()
            java.lang.String r2 = r2.zzae()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x013b
            if (r1 == 0) goto L_0x012c
            if (r1 == r10) goto L_0x012c
            if (r1 == r4) goto L_0x012c
            if (r1 == r10) goto L_0x012c
            if (r1 == r10) goto L_0x012c
            if (r1 != r3) goto L_0x013b
        L_0x012c:
            com.google.android.gms.measurement.internal.zziv r1 = r12.zzp()
            com.google.android.gms.measurement.internal.zzin r2 = new com.google.android.gms.measurement.internal.zzin
            r2.<init>(r8, r8, r9)
            long r3 = r12.zza
            r1.zza((com.google.android.gms.measurement.internal.zzin) r2, (long) r3, (boolean) r7)
            goto L_0x0166
        L_0x013b:
            com.google.android.gms.measurement.internal.zzfq r1 = r12.zzh()
            java.lang.String r1 = r1.zzae()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0166
            if (r13 == 0) goto L_0x0166
            android.os.Bundle r1 = r13.zzg
            if (r1 == 0) goto L_0x0166
            com.google.android.gms.measurement.internal.zzgh r1 = r12.zzn()
            boolean r1 = r1.zza((int) r10)
            if (r1 == 0) goto L_0x0166
            android.os.Bundle r1 = r13.zzg
            com.google.android.gms.measurement.internal.zzin r1 = com.google.android.gms.measurement.internal.zzin.zza((android.os.Bundle) r1, (int) r10)
            boolean r2 = r1.zzk()
            if (r2 == 0) goto L_0x0166
            goto L_0x0167
        L_0x0166:
            r1 = r8
        L_0x0167:
            if (r1 == 0) goto L_0x017b
            com.google.android.gms.measurement.internal.zziv r0 = r12.zzp()
            long r2 = r12.zza
            com.google.android.gms.measurement.internal.zzag r4 = r12.zzi
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbf.zzct
            boolean r4 = r4.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r5)
            r0.zza((com.google.android.gms.measurement.internal.zzin) r1, (long) r2, (boolean) r4)
            r0 = r1
        L_0x017b:
            com.google.android.gms.measurement.internal.zziv r1 = r12.zzp()
            r1.zza((com.google.android.gms.measurement.internal.zzin) r0)
            com.google.android.gms.measurement.internal.zzgh r0 = r12.zzn()
            com.google.android.gms.measurement.internal.zzav r0 = r0.zzm()
            int r0 = r0.zza()
            boolean r1 = com.google.android.gms.internal.measurement.zzne.zza()
            java.lang.String r2 = "google_analytics_default_allow_ad_user_data"
            r3 = 1
            if (r1 == 0) goto L_0x01df
            com.google.android.gms.measurement.internal.zzag r1 = r12.zzi
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzbf.zzcq
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r4)
            if (r1 == 0) goto L_0x01df
            com.google.android.gms.measurement.internal.zzag r1 = r12.zzi
            java.lang.String r4 = "google_analytics_default_allow_ad_personalization_signals"
            com.google.android.gms.measurement.internal.zzim r1 = r1.zzc((java.lang.String) r4, (boolean) r3)
            com.google.android.gms.measurement.internal.zzim r4 = com.google.android.gms.measurement.internal.zzim.UNINITIALIZED
            if (r1 == r4) goto L_0x01ba
            com.google.android.gms.measurement.internal.zzfw r4 = r12.zzj()
            com.google.android.gms.measurement.internal.zzfy r4 = r4.zzp()
            java.lang.String r5 = "Default ad personalization consent from Manifest"
            r4.zza(r5, r1)
        L_0x01ba:
            com.google.android.gms.measurement.internal.zzag r1 = r12.zzi
            com.google.android.gms.measurement.internal.zzim r1 = r1.zzc((java.lang.String) r2, (boolean) r3)
            com.google.android.gms.measurement.internal.zzim r2 = com.google.android.gms.measurement.internal.zzim.UNINITIALIZED
            if (r1 == r2) goto L_0x0203
            boolean r2 = com.google.android.gms.measurement.internal.zzin.zza((int) r9, (int) r0)
            if (r2 == 0) goto L_0x0203
            com.google.android.gms.measurement.internal.zziv r13 = r12.zzp()
            com.google.android.gms.measurement.internal.zzav r0 = com.google.android.gms.measurement.internal.zzav.zza((com.google.android.gms.measurement.internal.zzim) r1, (int) r9)
            com.google.android.gms.measurement.internal.zzag r1 = r12.zzi
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzct
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r2)
            r13.zza((com.google.android.gms.measurement.internal.zzav) r0, (boolean) r1)
            goto L_0x0296
        L_0x01df:
            com.google.android.gms.measurement.internal.zzag r1 = r12.zzi
            java.lang.Boolean r1 = r1.zze(r2)
            if (r1 == 0) goto L_0x0203
            boolean r2 = com.google.android.gms.measurement.internal.zzin.zza((int) r9, (int) r0)
            if (r2 == 0) goto L_0x0203
            com.google.android.gms.measurement.internal.zziv r13 = r12.zzp()
            com.google.android.gms.measurement.internal.zzav r0 = new com.google.android.gms.measurement.internal.zzav
            r0.<init>(r1, r9)
            com.google.android.gms.measurement.internal.zzag r1 = r12.zzi
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzct
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r2)
            r13.zza((com.google.android.gms.measurement.internal.zzav) r0, (boolean) r1)
            goto L_0x0296
        L_0x0203:
            com.google.android.gms.measurement.internal.zzfq r1 = r12.zzh()
            java.lang.String r1 = r1.zzae()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x022a
            if (r0 == 0) goto L_0x0215
            if (r0 != r10) goto L_0x022a
        L_0x0215:
            com.google.android.gms.measurement.internal.zziv r13 = r12.zzp()
            com.google.android.gms.measurement.internal.zzav r0 = new com.google.android.gms.measurement.internal.zzav
            r0.<init>(r8, r9)
            com.google.android.gms.measurement.internal.zzag r1 = r12.zzi
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzct
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r2)
            r13.zza((com.google.android.gms.measurement.internal.zzav) r0, (boolean) r1)
            goto L_0x0296
        L_0x022a:
            com.google.android.gms.measurement.internal.zzfq r1 = r12.zzh()
            java.lang.String r1 = r1.zzae()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x025f
            if (r13 == 0) goto L_0x025f
            android.os.Bundle r1 = r13.zzg
            if (r1 == 0) goto L_0x025f
            boolean r0 = com.google.android.gms.measurement.internal.zzin.zza((int) r10, (int) r0)
            if (r0 == 0) goto L_0x025f
            android.os.Bundle r0 = r13.zzg
            com.google.android.gms.measurement.internal.zzav r0 = com.google.android.gms.measurement.internal.zzav.zza((android.os.Bundle) r0, (int) r10)
            boolean r1 = r0.zzg()
            if (r1 == 0) goto L_0x025f
            com.google.android.gms.measurement.internal.zziv r1 = r12.zzp()
            com.google.android.gms.measurement.internal.zzag r2 = r12.zzi
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzbf.zzct
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r4)
            r1.zza((com.google.android.gms.measurement.internal.zzav) r0, (boolean) r2)
        L_0x025f:
            com.google.android.gms.measurement.internal.zzfq r0 = r12.zzh()
            java.lang.String r0 = r0.zzae()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0296
            if (r13 == 0) goto L_0x0296
            android.os.Bundle r0 = r13.zzg
            if (r0 == 0) goto L_0x0296
            com.google.android.gms.measurement.internal.zzgh r0 = r12.zzn()
            com.google.android.gms.measurement.internal.zzgn r0 = r0.zzh
            java.lang.String r0 = r0.zza()
            if (r0 != 0) goto L_0x0296
            android.os.Bundle r0 = r13.zzg
            java.lang.Boolean r0 = com.google.android.gms.measurement.internal.zzav.zza((android.os.Bundle) r0)
            if (r0 == 0) goto L_0x0296
            com.google.android.gms.measurement.internal.zziv r1 = r12.zzp()
            java.lang.String r13 = r13.zze
            java.lang.String r2 = "allow_personalized_ads"
            java.lang.String r0 = r0.toString()
            r1.zza((java.lang.String) r13, (java.lang.String) r2, (java.lang.Object) r0, (boolean) r7)
        L_0x0296:
            com.google.android.gms.measurement.internal.zzag r13 = r12.zzi
            java.lang.String r0 = "google_analytics_tcf_data_enabled"
            java.lang.Boolean r13 = r13.zze(r0)
            if (r13 != 0) goto L_0x02a2
            r13 = r3
            goto L_0x02a6
        L_0x02a2:
            boolean r13 = r13.booleanValue()
        L_0x02a6:
            if (r13 == 0) goto L_0x02c3
            com.google.android.gms.measurement.internal.zzfw r13 = r12.zzj()
            com.google.android.gms.measurement.internal.zzfy r13 = r13.zzc()
            java.lang.String r0 = "TCF client enabled."
            r13.zza(r0)
            com.google.android.gms.measurement.internal.zziv r13 = r12.zzp()
            r13.zzas()
            com.google.android.gms.measurement.internal.zziv r13 = r12.zzp()
            r13.zzaq()
        L_0x02c3:
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            com.google.android.gms.measurement.internal.zzgm r13 = r13.zzc
            long r0 = r13.zza()
            r4 = 0
            int r13 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r13 != 0) goto L_0x02f1
            com.google.android.gms.measurement.internal.zzfw r13 = r12.zzj()
            com.google.android.gms.measurement.internal.zzfy r13 = r13.zzp()
            long r0 = r12.zza
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.String r1 = "Persisting first open"
            r13.zza(r1, r0)
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            com.google.android.gms.measurement.internal.zzgm r13 = r13.zzc
            long r0 = r12.zza
            r13.zza(r0)
        L_0x02f1:
            com.google.android.gms.measurement.internal.zziv r13 = r12.zzp()
            com.google.android.gms.measurement.internal.zzr r13 = r13.zza
            r13.zzb()
            boolean r13 = r12.zzaf()
            if (r13 != 0) goto L_0x0385
            boolean r13 = r12.zzac()
            if (r13 == 0) goto L_0x04e5
            com.google.android.gms.measurement.internal.zznp r13 = r12.zzt()
            java.lang.String r0 = "android.permission.INTERNET"
            boolean r13 = r13.zze(r0)
            if (r13 != 0) goto L_0x031f
            com.google.android.gms.measurement.internal.zzfw r13 = r12.zzj()
            com.google.android.gms.measurement.internal.zzfy r13 = r13.zzg()
            java.lang.String r0 = "App is missing INTERNET permission"
            r13.zza(r0)
        L_0x031f:
            com.google.android.gms.measurement.internal.zznp r13 = r12.zzt()
            java.lang.String r0 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r13 = r13.zze(r0)
            if (r13 != 0) goto L_0x0338
            com.google.android.gms.measurement.internal.zzfw r13 = r12.zzj()
            com.google.android.gms.measurement.internal.zzfy r13 = r13.zzg()
            java.lang.String r0 = "App is missing ACCESS_NETWORK_STATE permission"
            r13.zza(r0)
        L_0x0338:
            android.content.Context r13 = r12.zzc
            com.google.android.gms.common.wrappers.PackageManagerWrapper r13 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r13)
            boolean r13 = r13.isCallerInstantApp()
            if (r13 != 0) goto L_0x0376
            com.google.android.gms.measurement.internal.zzag r13 = r12.zzi
            boolean r13 = r13.zzx()
            if (r13 != 0) goto L_0x0376
            android.content.Context r13 = r12.zzc
            boolean r13 = com.google.android.gms.measurement.internal.zznp.zza((android.content.Context) r13)
            if (r13 != 0) goto L_0x0361
            com.google.android.gms.measurement.internal.zzfw r13 = r12.zzj()
            com.google.android.gms.measurement.internal.zzfy r13 = r13.zzg()
            java.lang.String r0 = "AppMeasurementReceiver not registered/enabled"
            r13.zza(r0)
        L_0x0361:
            android.content.Context r13 = r12.zzc
            boolean r13 = com.google.android.gms.measurement.internal.zznp.zza((android.content.Context) r13, (boolean) r7)
            if (r13 != 0) goto L_0x0376
            com.google.android.gms.measurement.internal.zzfw r13 = r12.zzj()
            com.google.android.gms.measurement.internal.zzfy r13 = r13.zzg()
            java.lang.String r0 = "AppMeasurementService not registered/enabled"
            r13.zza(r0)
        L_0x0376:
            com.google.android.gms.measurement.internal.zzfw r13 = r12.zzj()
            com.google.android.gms.measurement.internal.zzfy r13 = r13.zzg()
            java.lang.String r0 = "Uploading is not possible. App measurement disabled"
            r13.zza(r0)
            goto L_0x04e5
        L_0x0385:
            com.google.android.gms.measurement.internal.zzfq r13 = r12.zzh()
            java.lang.String r13 = r13.zzae()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 == 0) goto L_0x03a1
            com.google.android.gms.measurement.internal.zzfq r13 = r12.zzh()
            java.lang.String r13 = r13.zzac()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x0421
        L_0x03a1:
            r12.zzt()
            com.google.android.gms.measurement.internal.zzfq r13 = r12.zzh()
            java.lang.String r13 = r13.zzae()
            com.google.android.gms.measurement.internal.zzgh r0 = r12.zzn()
            java.lang.String r0 = r0.zzy()
            com.google.android.gms.measurement.internal.zzfq r1 = r12.zzh()
            java.lang.String r1 = r1.zzac()
            com.google.android.gms.measurement.internal.zzgh r2 = r12.zzn()
            java.lang.String r2 = r2.zzx()
            boolean r13 = com.google.android.gms.measurement.internal.zznp.zza((java.lang.String) r13, (java.lang.String) r0, (java.lang.String) r1, (java.lang.String) r2)
            if (r13 == 0) goto L_0x0403
            com.google.android.gms.measurement.internal.zzfw r13 = r12.zzj()
            com.google.android.gms.measurement.internal.zzfy r13 = r13.zzn()
            java.lang.String r0 = "Rechecking which service to use due to a GMP App Id change"
            r13.zza(r0)
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            r13.zzz()
            com.google.android.gms.measurement.internal.zzfp r13 = r12.zzi()
            r13.zzaa()
            com.google.android.gms.measurement.internal.zzkx r13 = r12.zzw
            r13.zzae()
            com.google.android.gms.measurement.internal.zzkx r13 = r12.zzw
            r13.zzad()
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            com.google.android.gms.measurement.internal.zzgm r13 = r13.zzc
            long r0 = r12.zza
            r13.zza(r0)
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            com.google.android.gms.measurement.internal.zzgn r13 = r13.zze
            r13.zza(r8)
        L_0x0403:
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            com.google.android.gms.measurement.internal.zzfq r0 = r12.zzh()
            java.lang.String r0 = r0.zzae()
            r13.zzc(r0)
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            com.google.android.gms.measurement.internal.zzfq r0 = r12.zzh()
            java.lang.String r0 = r0.zzac()
            r13.zzb((java.lang.String) r0)
        L_0x0421:
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            com.google.android.gms.measurement.internal.zzin r13 = r13.zzn()
            com.google.android.gms.measurement.internal.zzin$zza r0 = com.google.android.gms.measurement.internal.zzin.zza.ANALYTICS_STORAGE
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzin.zza) r0)
            if (r13 != 0) goto L_0x043a
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            com.google.android.gms.measurement.internal.zzgn r13 = r13.zze
            r13.zza(r8)
        L_0x043a:
            com.google.android.gms.measurement.internal.zziv r13 = r12.zzp()
            com.google.android.gms.measurement.internal.zzgh r0 = r12.zzn()
            com.google.android.gms.measurement.internal.zzgn r0 = r0.zze
            java.lang.String r0 = r0.zza()
            r13.zzc((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zznp r13 = r12.zzt()
            boolean r13 = r13.zzx()
            if (r13 != 0) goto L_0x047b
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            com.google.android.gms.measurement.internal.zzgn r13 = r13.zzq
            java.lang.String r13 = r13.zza()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x047b
            com.google.android.gms.measurement.internal.zzfw r13 = r12.zzj()
            com.google.android.gms.measurement.internal.zzfy r13 = r13.zzu()
            java.lang.String r0 = "Remote config removed with active feature rollouts"
            r13.zza(r0)
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            com.google.android.gms.measurement.internal.zzgn r13 = r13.zzq
            r13.zza(r8)
        L_0x047b:
            com.google.android.gms.measurement.internal.zzfq r13 = r12.zzh()
            java.lang.String r13 = r13.zzae()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 == 0) goto L_0x0497
            com.google.android.gms.measurement.internal.zzfq r13 = r12.zzh()
            java.lang.String r13 = r13.zzac()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x04e5
        L_0x0497:
            boolean r13 = r12.zzac()
            com.google.android.gms.measurement.internal.zzgh r0 = r12.zzn()
            boolean r0 = r0.zzab()
            if (r0 != 0) goto L_0x04b6
            com.google.android.gms.measurement.internal.zzag r0 = r12.zzi
            boolean r0 = r0.zzw()
            if (r0 != 0) goto L_0x04b6
            com.google.android.gms.measurement.internal.zzgh r0 = r12.zzn()
            r1 = r13 ^ 1
            r0.zzb((boolean) r1)
        L_0x04b6:
            if (r13 == 0) goto L_0x04bf
            com.google.android.gms.measurement.internal.zziv r13 = r12.zzp()
            r13.zzam()
        L_0x04bf:
            com.google.android.gms.measurement.internal.zzmh r13 = r12.zzs()
            com.google.android.gms.measurement.internal.zzmp r13 = r13.zza
            r13.zza()
            com.google.android.gms.measurement.internal.zzkx r13 = r12.zzr()
            java.util.concurrent.atomic.AtomicReference r0 = new java.util.concurrent.atomic.AtomicReference
            r0.<init>()
            r13.zza((java.util.concurrent.atomic.AtomicReference<java.lang.String>) r0)
            com.google.android.gms.measurement.internal.zzkx r13 = r12.zzr()
            com.google.android.gms.measurement.internal.zzgh r0 = r12.zzn()
            com.google.android.gms.measurement.internal.zzgj r0 = r0.zzt
            android.os.Bundle r0 = r0.zza()
            r13.zza((android.os.Bundle) r0)
        L_0x04e5:
            boolean r13 = com.google.android.gms.internal.measurement.zzpg.zza()
            if (r13 == 0) goto L_0x0513
            com.google.android.gms.measurement.internal.zzag r13 = r12.zzi
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r0 = com.google.android.gms.measurement.internal.zzbf.zzca
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r0)
            if (r13 == 0) goto L_0x0513
            com.google.android.gms.measurement.internal.zznp r13 = r12.zzt()
            boolean r13 = r13.zzw()
            if (r13 == 0) goto L_0x0513
            java.lang.Thread r13 = new java.lang.Thread
            com.google.android.gms.measurement.internal.zziv r0 = r12.zzp()
            java.util.Objects.requireNonNull(r0)
            com.google.android.gms.measurement.internal.zzhi r1 = new com.google.android.gms.measurement.internal.zzhi
            r1.<init>(r0)
            r13.<init>(r1)
            r13.start()
        L_0x0513:
            com.google.android.gms.measurement.internal.zzgh r13 = r12.zzn()
            com.google.android.gms.measurement.internal.zzgk r13 = r13.zzj
            r13.zza(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhj.zza(com.google.android.gms.internal.measurement.zzdo):void");
    }

    public final boolean zzab() {
        return this.zzac != null && this.zzac.booleanValue();
    }

    public final boolean zzac() {
        return zzc() == 0;
    }

    public final boolean zzad() {
        zzl().zzt();
        return this.zzaf;
    }

    @Pure
    public final boolean zzae() {
        return TextUtils.isEmpty(this.zzd);
    }

    /* access modifiers changed from: protected */
    public final boolean zzaf() {
        if (this.zzz) {
            zzl().zzt();
            Boolean bool = this.zzaa;
            if (bool == null || this.zzab == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.zzp.elapsedRealtime() - this.zzab) > 1000)) {
                this.zzab = this.zzp.elapsedRealtime();
                boolean z = true;
                Boolean valueOf = Boolean.valueOf(zzt().zze("android.permission.INTERNET") && zzt().zze("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzc).isCallerInstantApp() || this.zzi.zzx() || (zznp.zza(this.zzc) && zznp.zza(this.zzc, false))));
                this.zzaa = valueOf;
                if (valueOf.booleanValue()) {
                    if (!zzt().zza(zzh().zzae(), zzh().zzac()) && TextUtils.isEmpty(zzh().zzac())) {
                        z = false;
                    }
                    this.zzaa = Boolean.valueOf(z);
                }
            }
            return this.zzaa.booleanValue();
        }
        throw new IllegalStateException("AppMeasurement is not initialized");
    }

    @Pure
    public final boolean zzag() {
        return this.zzg;
    }

    public final boolean zzah() {
        zzl().zzt();
        zza((zzii) zzai());
        String zzad2 = zzh().zzad();
        Pair<String, Boolean> zza2 = zzn().zza(zzad2);
        boolean z = false;
        if (!this.zzi.zzu() || ((Boolean) zza2.second).booleanValue() || TextUtils.isEmpty((CharSequence) zza2.first)) {
            zzj().zzc().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            return false;
        } else if (!zzai().zzc()) {
            zzj().zzu().zza("Network is not available for Deferred Deep Link request. Skipping");
            return false;
        } else {
            StringBuilder sb = new StringBuilder();
            zzkx zzr2 = zzr();
            zzr2.zzt();
            zzr2.zzu();
            if (!zzr2.zzao() || zzr2.zzq().zzg() >= 234200) {
                zzaj zzab2 = zzp().zzab();
                Bundle bundle = zzab2 != null ? zzab2.zza : null;
                int i = 1;
                if (bundle == null) {
                    int i2 = this.zzah;
                    this.zzah = i2 + 1;
                    if (i2 < 10) {
                        z = true;
                    }
                    zzj().zzc().zza("Failed to retrieve DMA consent from the service, " + (z ? "Retrying." : "Skipping.") + " retryCount", Integer.valueOf(this.zzah));
                    return z;
                }
                zzin zza3 = zzin.zza(bundle, 100);
                sb.append("&gcs=");
                sb.append(zza3.zzg());
                zzav zza4 = zzav.zza(bundle, 100);
                sb.append("&dma=");
                sb.append(zza4.zzd() == Boolean.FALSE ? 0 : 1);
                if (!TextUtils.isEmpty(zza4.zze())) {
                    sb.append("&dma_cps=");
                    sb.append(zza4.zze());
                }
                if (zzav.zza(bundle) == Boolean.TRUE) {
                    i = 0;
                }
                sb.append("&npa=");
                sb.append(i);
                zzj().zzp().zza("Consent query parameters to Bow", sb);
            }
            zznp zzt2 = zzt();
            zzh();
            URL zza5 = zzt2.zza(97001, zzad2, (String) zza2.first, zzn().zzp.zza() - 1, sb.toString());
            if (zza5 != null) {
                zzkj zzai2 = zzai();
                zzhl zzhl = new zzhl(this);
                zzai2.zzt();
                zzai2.zzac();
                Preconditions.checkNotNull(zza5);
                Preconditions.checkNotNull(zzhl);
                zzai2.zzl().zza((Runnable) new zzkl(zzai2, zzad2, zza5, (byte[]) null, (Map<String, String>) null, zzhl));
            }
            return false;
        }
    }
}
