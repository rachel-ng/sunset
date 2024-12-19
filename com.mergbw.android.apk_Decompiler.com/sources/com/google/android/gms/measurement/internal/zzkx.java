package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzdg;
import com.google.android.gms.internal.measurement.zznk;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzkx extends zze {
    /* access modifiers changed from: private */
    public final zzlw zza;
    /* access modifiers changed from: private */
    public zzfl zzb;
    private volatile Boolean zzc;
    private final zzat zzd;
    private final zzmr zze;
    private final List<Runnable> zzf = new ArrayList();
    private final zzat zzg;

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

    private final zzo zzc(boolean z) {
        return zzg().zza(z ? zzj().zzx() : null);
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    /* access modifiers changed from: protected */
    public final zzaj zzaa() {
        zzt();
        zzu();
        zzfl zzfl = this.zzb;
        if (zzfl == null) {
            zzad();
            zzj().zzc().zza("Failed to get consents; not connected to service yet.");
            return null;
        }
        zzo zzc2 = zzc(false);
        Preconditions.checkNotNull(zzc2);
        try {
            zzaj zza2 = zzfl.zza(zzc2);
            zzaq();
            return zza2;
        } catch (RemoteException e) {
            zzj().zzg().zza("Failed to get consents; remote exception", e);
            return null;
        }
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

    /* access modifiers changed from: package-private */
    public final Boolean zzab() {
        return this.zzc;
    }

    static /* synthetic */ void zzd(zzkx zzkx) {
        zzkx.zzt();
        if (zzkx.zzak()) {
            zzkx.zzj().zzp().zza("Inactivity, disconnecting from the service");
            zzkx.zzae();
        }
    }

    static /* synthetic */ void zza(zzkx zzkx, ComponentName componentName) {
        zzkx.zzt();
        if (zzkx.zzb != null) {
            zzkx.zzb = null;
            zzkx.zzj().zzp().zza("Disconnected from device MeasurementService", componentName);
            zzkx.zzt();
            zzkx.zzad();
        }
    }

    protected zzkx(zzhj zzhj) {
        super(zzhj);
        this.zze = new zzmr(zzhj.zzb());
        this.zza = new zzlw(this);
        this.zzd = new zzlc(this, zzhj);
        this.zzg = new zzll(this, zzhj);
    }

    /* access modifiers changed from: protected */
    public final void zzac() {
        zzt();
        zzu();
        zzo zzc2 = zzc(true);
        zzh().zzab();
        zza((Runnable) new zzlk(this, zzc2));
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
    public final void zzad() {
        zzt();
        zzu();
        if (!zzak()) {
            if (zzao()) {
                this.zza.zza();
            } else if (!zze().zzx()) {
                List<ResolveInfo> queryIntentServices = zza().getPackageManager().queryIntentServices(new Intent().setClassName(zza(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
                if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                    zzj().zzg().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
                    return;
                }
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                intent.setComponent(new ComponentName(zza(), "com.google.android.gms.measurement.AppMeasurementService"));
                this.zza.zza(intent);
            }
        }
    }

    public final void zzae() {
        zzt();
        zzu();
        this.zza.zzb();
        try {
            ConnectionTracker.getInstance().unbindService(zza(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    /* access modifiers changed from: private */
    public final void zzap() {
        zzt();
        zzj().zzp().zza("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        for (Runnable run : this.zzf) {
            try {
                run.run();
            } catch (RuntimeException e) {
                zzj().zzg().zza("Task exception while flushing queue", e);
            }
        }
        this.zzf.clear();
        this.zzg.zza();
    }

    public final void zza(zzdg zzdg) {
        zzt();
        zzu();
        zza((Runnable) new zzlh(this, zzc(false), zzdg));
    }

    public final void zza(AtomicReference<String> atomicReference) {
        zzt();
        zzu();
        zza((Runnable) new zzli(this, atomicReference, zzc(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdg zzdg, String str, String str2) {
        zzt();
        zzu();
        zza((Runnable) new zzlu(this, str, str2, zzc(false), zzdg));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzae>> atomicReference, String str, String str2, String str3) {
        zzt();
        zzu();
        zza((Runnable) new zzlr(this, atomicReference, str, str2, str3, zzc(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzmu>> atomicReference, Bundle bundle) {
        zzt();
        zzu();
        zza((Runnable) new zzld(this, atomicReference, zzc(false), bundle));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzno>> atomicReference, boolean z) {
        zzt();
        zzu();
        zza((Runnable) new zzle(this, atomicReference, zzc(false), z));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdg zzdg, String str, String str2, boolean z) {
        zzt();
        zzu();
        zza((Runnable) new zzlb(this, str, str2, zzc(false), z, zzdg));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzno>> atomicReference, String str, String str2, String str3, boolean z) {
        zzt();
        zzu();
        zza((Runnable) new zzlt(this, atomicReference, str, str2, str3, zzc(false), z));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaf() {
        zzfl zzfl = this.zzb;
        if (zzfl == null) {
            zzj().zzg().zza("Failed to send Dma consent settings to service");
            return;
        }
        try {
            zzo zzc2 = zzc(false);
            Preconditions.checkNotNull(zzc2);
            zzfl.zzf(zzc2);
            zzaq();
        } catch (RemoteException e) {
            zzj().zzg().zza("Failed to send Dma consent settings to the service", e);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzag() {
        zzfl zzfl = this.zzb;
        if (zzfl == null) {
            zzj().zzg().zza("Failed to send storage consent settings to service");
            return;
        }
        try {
            zzo zzc2 = zzc(false);
            Preconditions.checkNotNull(zzc2);
            zzfl.zzh(zzc2);
            zzaq();
        } catch (RemoteException e) {
            zzj().zzg().zza("Failed to send storage consent settings to the service", e);
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbd zzbd, String str) {
        Preconditions.checkNotNull(zzbd);
        zzt();
        zzu();
        zza((Runnable) new zzlp(this, true, zzc(true), zzh().zza(zzbd), zzbd, str));
    }

    public final void zza(zzdg zzdg, zzbd zzbd, String str) {
        zzt();
        zzu();
        if (zzq().zza((int) GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) != 0) {
            zzj().zzu().zza("Not bundling data. Service unavailable or out of date");
            zzq().zza(zzdg, new byte[0]);
            return;
        }
        zza((Runnable) new zzlo(this, zzbd, str, zzdg));
    }

    /* access modifiers changed from: private */
    public final void zzaq() {
        zzt();
        this.zze.zzb();
        this.zzd.zza(zzbf.zzaj.zza(null).longValue());
    }

    /* access modifiers changed from: protected */
    public final void zzah() {
        zzt();
        zzu();
        zzo zzc2 = zzc(false);
        zzh().zzaa();
        zza((Runnable) new zzlf(this, zzc2));
    }

    private final void zza(Runnable runnable) throws IllegalStateException {
        zzt();
        if (zzak()) {
            runnable.run();
        } else if (((long) this.zzf.size()) >= 1000) {
            zzj().zzg().zza("Discarding data. Max runnable queue size reached");
        } else {
            this.zzf.add(runnable);
            this.zzg.zza(60000);
            zzad();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzfl zzfl, AbstractSafeParcelable abstractSafeParcelable, zzo zzo) {
        int i;
        zzt();
        zzu();
        int i2 = 100;
        int i3 = 0;
        while (i3 < 1001 && i2 == 100) {
            ArrayList arrayList = new ArrayList();
            List<AbstractSafeParcelable> zza2 = zzh().zza(100);
            if (zza2 != null) {
                arrayList.addAll(zza2);
                i = zza2.size();
            } else {
                i = 0;
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayList.get(i4);
                i4++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzbd) {
                    try {
                        zzfl.zza((zzbd) abstractSafeParcelable2, zzo);
                    } catch (RemoteException e) {
                        zzj().zzg().zza("Failed to send event to the service", e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzno) {
                    try {
                        zzfl.zza((zzno) abstractSafeParcelable2, zzo);
                    } catch (RemoteException e2) {
                        zzj().zzg().zza("Failed to send user property to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzae) {
                    try {
                        zzfl.zza((zzae) abstractSafeParcelable2, zzo);
                    } catch (RemoteException e3) {
                        zzj().zzg().zza("Failed to send conditional user property to the service", e3);
                    }
                } else {
                    zzj().zzg().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            i3++;
            i2 = i;
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzae zzae) {
        Preconditions.checkNotNull(zzae);
        zzt();
        zzu();
        zza((Runnable) new zzls(this, true, zzc(true), zzh().zza(zzae), new zzae(zzae), zzae));
    }

    /* access modifiers changed from: protected */
    public final void zza(boolean z) {
        zzt();
        zzu();
        if ((!zznk.zza() || !zze().zza(zzbf.zzcu)) && z) {
            zzh().zzaa();
        }
        if (zzam()) {
            zza((Runnable) new zzlq(this, zzc(false)));
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzkp zzkp) {
        zzt();
        zzu();
        zza((Runnable) new zzlj(this, zzkp));
    }

    public final void zza(Bundle bundle) {
        zzt();
        zzu();
        zza((Runnable) new zzlm(this, zzc(false), bundle));
    }

    /* access modifiers changed from: protected */
    public final void zzai() {
        zzt();
        zzu();
        zza((Runnable) new zzkz(this));
    }

    /* access modifiers changed from: protected */
    public final void zzaj() {
        zzt();
        zzu();
        zza((Runnable) new zzln(this, zzc(true)));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzfl zzfl) {
        zzt();
        Preconditions.checkNotNull(zzfl);
        this.zzb = zzfl;
        zzaq();
        zzap();
    }

    /* access modifiers changed from: protected */
    public final void zzb(boolean z) {
        zzt();
        zzu();
        if ((!zznk.zza() || !zze().zza(zzbf.zzcu)) && z) {
            zzh().zzaa();
        }
        zza((Runnable) new zzla(this));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzno zzno) {
        zzt();
        zzu();
        zza((Runnable) new zzlg(this, zzc(true), zzh().zza(zzno), zzno));
    }

    public final boolean zzak() {
        zzt();
        zzu();
        return this.zzb != null;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzal() {
        zzt();
        zzu();
        if (zzao() && zzq().zzg() < 200900) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzam() {
        zzt();
        zzu();
        if (zzao() && zzq().zzg() < zzbf.zzbo.zza(null).intValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzan() {
        zzt();
        zzu();
        if (zzao() && zzq().zzg() < 241200) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzao() {
        /*
            r5 = this;
            r5.zzt()
            r5.zzu()
            java.lang.Boolean r0 = r5.zzc
            if (r0 != 0) goto L_0x0102
            r5.zzt()
            r5.zzu()
            com.google.android.gms.measurement.internal.zzgh r0 = r5.zzk()
            java.lang.Boolean r0 = r0.zzp()
            r1 = 1
            if (r0 == 0) goto L_0x0023
            boolean r2 = r0.booleanValue()
            if (r2 == 0) goto L_0x0023
            goto L_0x00fc
        L_0x0023:
            com.google.android.gms.measurement.internal.zzfq r2 = r5.zzg()
            int r2 = r2.zzaa()
            r3 = 0
            if (r2 != r1) goto L_0x0031
        L_0x002e:
            r0 = r1
            goto L_0x00d8
        L_0x0031:
            com.google.android.gms.measurement.internal.zzfw r2 = r5.zzj()
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzp()
            java.lang.String r4 = "Checking service availability"
            r2.zza(r4)
            com.google.android.gms.measurement.internal.zznp r2 = r5.zzq()
            r4 = 12451000(0xbdfcb8, float:1.7447567E-38)
            int r2 = r2.zza((int) r4)
            if (r2 == 0) goto L_0x00c9
            if (r2 == r1) goto L_0x00b9
            r4 = 2
            if (r2 == r4) goto L_0x0099
            r0 = 3
            if (r2 == r0) goto L_0x0089
            r0 = 9
            if (r2 == r0) goto L_0x007b
            r0 = 18
            if (r2 == r0) goto L_0x006d
            com.google.android.gms.measurement.internal.zzfw r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzu()
            java.lang.String r1 = "Unexpected service status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.zza(r1, r2)
            goto L_0x0096
        L_0x006d:
            com.google.android.gms.measurement.internal.zzfw r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzu()
            java.lang.String r2 = "Service updating"
            r0.zza(r2)
            goto L_0x002e
        L_0x007b:
            com.google.android.gms.measurement.internal.zzfw r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzu()
            java.lang.String r1 = "Service invalid"
            r0.zza(r1)
            goto L_0x0096
        L_0x0089:
            com.google.android.gms.measurement.internal.zzfw r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzu()
            java.lang.String r1 = "Service disabled"
            r0.zza(r1)
        L_0x0096:
            r0 = r3
            r1 = r0
            goto L_0x00d8
        L_0x0099:
            com.google.android.gms.measurement.internal.zzfw r2 = r5.zzj()
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzc()
            java.lang.String r4 = "Service container out of date"
            r2.zza(r4)
            com.google.android.gms.measurement.internal.zznp r2 = r5.zzq()
            int r2 = r2.zzg()
            r4 = 17443(0x4423, float:2.4443E-41)
            if (r2 >= r4) goto L_0x00b3
            goto L_0x00c6
        L_0x00b3:
            if (r0 != 0) goto L_0x00b6
            goto L_0x00b7
        L_0x00b6:
            r1 = r3
        L_0x00b7:
            r0 = r3
            goto L_0x00d8
        L_0x00b9:
            com.google.android.gms.measurement.internal.zzfw r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzp()
            java.lang.String r2 = "Service missing"
            r0.zza(r2)
        L_0x00c6:
            r0 = r1
            r1 = r3
            goto L_0x00d8
        L_0x00c9:
            com.google.android.gms.measurement.internal.zzfw r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzp()
            java.lang.String r2 = "Service available"
            r0.zza(r2)
            goto L_0x002e
        L_0x00d8:
            if (r1 != 0) goto L_0x00f2
            com.google.android.gms.measurement.internal.zzag r2 = r5.zze()
            boolean r2 = r2.zzx()
            if (r2 == 0) goto L_0x00f2
            com.google.android.gms.measurement.internal.zzfw r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()
            java.lang.String r2 = "No way to upload. Consider using the full version of Analytics"
            r0.zza(r2)
            goto L_0x00f3
        L_0x00f2:
            r3 = r0
        L_0x00f3:
            if (r3 == 0) goto L_0x00fc
            com.google.android.gms.measurement.internal.zzgh r0 = r5.zzk()
            r0.zza((boolean) r1)
        L_0x00fc:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r5.zzc = r0
        L_0x0102:
            java.lang.Boolean r0 = r5.zzc
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkx.zzao():boolean");
    }
}
