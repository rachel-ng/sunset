package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.work.WorkRequest;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.internal.measurement.zzad;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzhn extends zzfo {
    /* access modifiers changed from: private */
    public final zznc zza;
    private Boolean zzb;
    private String zzc;

    public final zzaj zza(zzo zzo) {
        zzb(zzo, false);
        Preconditions.checkNotEmpty(zzo.zza);
        try {
            return (zzaj) this.zza.zzl().zzb(new zzia(this, zzo)).get(WorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zza.zzj().zzg().zza("Failed to get consent. appId", zzfw.zza(zzo.zza), e);
            return new zzaj((Bundle) null);
        }
    }

    /* access modifiers changed from: package-private */
    public final zzbd zzb(zzbd zzbd, zzo zzo) {
        if (!(!"_cmp".equals(zzbd.zza) || zzbd.zzb == null || zzbd.zzb.zza() == 0)) {
            String zzd = zzbd.zzb.zzd("_cis");
            if ("referrer broadcast".equals(zzd) || "referrer API".equals(zzd)) {
                this.zza.zzj().zzn().zza("Event has been filtered ", zzbd.toString());
                return new zzbd("_cmpx", zzbd.zzb, zzbd.zzc, zzbd.zzd);
            }
        }
        return zzbd;
    }

    public final String zzb(zzo zzo) {
        zzb(zzo, false);
        return this.zza.zzb(zzo);
    }

    public final List<zzmu> zza(zzo zzo, Bundle bundle) {
        zzb(zzo, false);
        Preconditions.checkNotNull(zzo.zza);
        try {
            return (List) this.zza.zzl().zza(new zzih(this, zzo, bundle)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get trigger URIs. appId", zzfw.zza(zzo.zza), e);
            return Collections.emptyList();
        }
    }

    public final List<zzno> zza(zzo zzo, boolean z) {
        zzb(zzo, false);
        String str = zzo.zza;
        Preconditions.checkNotNull(str);
        try {
            List<zznq> list = (List) this.zza.zzl().zza(new zzig(this, str)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zznq zznq : list) {
                if (z || !zznp.zzg(zznq.zzc)) {
                    arrayList.add(new zzno(zznq));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get user properties. appId", zzfw.zza(zzo.zza), e);
            return null;
        }
    }

    public final List<zzae> zza(String str, String str2, zzo zzo) {
        zzb(zzo, false);
        String str3 = zzo.zza;
        Preconditions.checkNotNull(str3);
        try {
            return (List) this.zza.zzl().zza(new zzhw(this, str3, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    public final List<zzae> zza(String str, String str2, String str3) {
        zza(str, true);
        try {
            return (List) this.zza.zzl().zza(new zzhz(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get conditional user properties as", e);
            return Collections.emptyList();
        }
    }

    public final List<zzno> zza(String str, String str2, boolean z, zzo zzo) {
        zzb(zzo, false);
        String str3 = zzo.zza;
        Preconditions.checkNotNull(str3);
        try {
            List<zznq> list = (List) this.zza.zzl().zza(new zzhu(this, str3, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zznq zznq : list) {
                if (z || !zznp.zzg(zznq.zzc)) {
                    arrayList.add(new zzno(zznq));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to query user properties. appId", zzfw.zza(zzo.zza), e);
            return Collections.emptyList();
        }
    }

    public final List<zzno> zza(String str, String str2, String str3, boolean z) {
        zza(str, true);
        try {
            List<zznq> list = (List) this.zza.zzl().zza(new zzhx(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zznq zznq : list) {
                if (z || !zznp.zzg(zznq.zzc)) {
                    arrayList.add(new zzno(zznq));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get user properties as. appId", zzfw.zza(str), e);
            return Collections.emptyList();
        }
    }

    public zzhn(zznc zznc) {
        this(zznc, (String) null);
    }

    private zzhn(zznc zznc, String str) {
        Preconditions.checkNotNull(zznc);
        this.zza = zznc;
        this.zzc = null;
    }

    public final void zzc(zzo zzo) {
        zzb(zzo, false);
        zzb((Runnable) new zzhq(this, zzo));
    }

    private final void zzb(zzo zzo, boolean z) {
        Preconditions.checkNotNull(zzo);
        Preconditions.checkNotEmpty(zzo.zza);
        zza(zzo.zza, false);
        this.zza.zzq().zza(zzo.zzb, zzo.zzp);
    }

    private final void zza(String str, boolean z) {
        boolean z2;
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    if (this.zzb == null) {
                        if (!"com.google.android.gms".equals(this.zzc) && !UidVerifier.isGooglePlayServicesUid(this.zza.zza(), Binder.getCallingUid())) {
                            if (!GoogleSignatureVerifier.getInstance(this.zza.zza()).isUidGoogleSigned(Binder.getCallingUid())) {
                                z2 = false;
                                this.zzb = Boolean.valueOf(z2);
                            }
                        }
                        z2 = true;
                        this.zzb = Boolean.valueOf(z2);
                    }
                    if (this.zzb.booleanValue()) {
                        return;
                    }
                } catch (SecurityException e) {
                    this.zza.zzj().zzg().zza("Measurement Service called with invalid calling package. appId", zzfw.zza(str));
                    throw e;
                }
            }
            if (this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zza(), Binder.getCallingUid(), str)) {
                this.zzc = str;
            }
            if (!str.equals(this.zzc)) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
            }
            return;
        }
        this.zza.zzj().zzg().zza("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzbd zzbd, zzo zzo) {
        zzb zzb2;
        boolean z;
        if (!this.zza.zzi().zzl(zzo.zza)) {
            zzd(zzbd, zzo);
            return;
        }
        this.zza.zzj().zzp().zza("EES config found for", zzo.zza);
        zzgt zzi = this.zza.zzi();
        String str = zzo.zza;
        if (TextUtils.isEmpty(str)) {
            zzb2 = null;
        } else {
            zzb2 = zzi.zza.get(str);
        }
        if (zzb2 == null) {
            this.zza.zzj().zzp().zza("EES not loaded for", zzo.zza);
            zzd(zzbd, zzo);
            return;
        }
        try {
            Map<String, Object> zza2 = this.zza.zzp().zza(zzbd.zzb.zzb(), true);
            String zza3 = zziq.zza(zzbd.zza);
            if (zza3 == null) {
                zza3 = zzbd.zza;
            }
            z = zzb2.zza(new zzad(zza3, zzbd.zzd, zza2));
        } catch (zzc unused) {
            this.zza.zzj().zzg().zza("EES error. appId, eventName", zzo.zzb, zzbd.zza);
            z = false;
        }
        if (!z) {
            this.zza.zzj().zzp().zza("EES was not applied to event", zzbd.zza);
            zzd(zzbd, zzo);
            return;
        }
        if (zzb2.zzd()) {
            this.zza.zzj().zzp().zza("EES edited event", zzbd.zza);
            zzd(this.zza.zzp().zza(zzb2.zza().zzb()), zzo);
        } else {
            zzd(zzbd, zzo);
        }
        if (zzb2.zzc()) {
            for (zzad next : zzb2.zza().zzc()) {
                this.zza.zzj().zzp().zza("EES logging created event", next.zzb());
                zzd(this.zza.zzp().zza(next), zzo);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str, Bundle bundle) {
        this.zza.zzf().zza(str, bundle);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(zzo zzo) {
        this.zza.zzr();
        this.zza.zze(zzo);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzj(zzo zzo) {
        this.zza.zzr();
        this.zza.zzf(zzo);
    }

    public final void zza(zzbd zzbd, zzo zzo) {
        Preconditions.checkNotNull(zzbd);
        zzb(zzo, false);
        zzb((Runnable) new zzid(this, zzbd, zzo));
    }

    public final void zza(zzbd zzbd, String str, String str2) {
        Preconditions.checkNotNull(zzbd);
        Preconditions.checkNotEmpty(str);
        zza(str, true);
        zzb((Runnable) new zzic(this, zzbd, str));
    }

    private final void zzd(zzbd zzbd, zzo zzo) {
        this.zza.zzr();
        this.zza.zza(zzbd, zzo);
    }

    public final void zzd(zzo zzo) {
        Preconditions.checkNotEmpty(zzo.zza);
        zza(zzo.zza, false);
        zzb((Runnable) new zzhy(this, zzo));
    }

    private final void zza(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzl().zzg()) {
            runnable.run();
        } else {
            this.zza.zzl().zzc(runnable);
        }
    }

    private final void zzb(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzl().zzg()) {
            runnable.run();
        } else {
            this.zza.zzl().zzb(runnable);
        }
    }

    public final void zza(zzae zzae, zzo zzo) {
        Preconditions.checkNotNull(zzae);
        Preconditions.checkNotNull(zzae.zzc);
        zzb(zzo, false);
        zzae zzae2 = new zzae(zzae);
        zzae2.zza = zzo.zza;
        zzb((Runnable) new zzhs(this, zzae2, zzo));
    }

    public final void zza(zzae zzae) {
        Preconditions.checkNotNull(zzae);
        Preconditions.checkNotNull(zzae.zzc);
        Preconditions.checkNotEmpty(zzae.zza);
        zza(zzae.zza, true);
        zzb((Runnable) new zzhv(this, new zzae(zzae)));
    }

    public final void zze(zzo zzo) {
        Preconditions.checkNotEmpty(zzo.zza);
        Preconditions.checkNotNull(zzo.zzt);
        zza((Runnable) new zzib(this, zzo));
    }

    public final void zza(long j, String str, String str2, String str3) {
        zzb((Runnable) new zzht(this, str2, str3, str, j));
    }

    public final void zza(Bundle bundle, zzo zzo) {
        zzb(zzo, false);
        String str = zzo.zza;
        Preconditions.checkNotNull(str);
        zzb((Runnable) new zzho(this, str, bundle));
    }

    public final void zzf(zzo zzo) {
        Preconditions.checkNotEmpty(zzo.zza);
        Preconditions.checkNotNull(zzo.zzt);
        zza((Runnable) new zzhp(this, zzo));
    }

    public final void zzg(zzo zzo) {
        zzb(zzo, false);
        zzb((Runnable) new zzhr(this, zzo));
    }

    public final void zzh(zzo zzo) {
        Preconditions.checkNotEmpty(zzo.zza);
        Preconditions.checkNotNull(zzo.zzt);
        zza((Runnable) new zzhm(this, zzo));
    }

    public final void zza(zzno zzno, zzo zzo) {
        Preconditions.checkNotNull(zzno);
        zzb(zzo, false);
        zzb((Runnable) new zzie(this, zzno, zzo));
    }

    public final byte[] zza(zzbd zzbd, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzbd);
        zza(str, true);
        this.zza.zzj().zzc().zza("Log and bundle. event", this.zza.zzg().zza(zzbd.zza));
        long nanoTime = this.zza.zzb().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zza.zzl().zzb(new zzif(this, zzbd, str)).get();
            if (bArr == null) {
                this.zza.zzj().zzg().zza("Log and bundle returned null. appId", zzfw.zza(str));
                bArr = new byte[0];
            }
            this.zza.zzj().zzc().zza("Log and bundle processed. event, size, time_ms", this.zza.zzg().zza(zzbd.zza), Integer.valueOf(bArr.length), Long.valueOf((this.zza.zzb().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to log and bundle. appId, event, error", zzfw.zza(str), this.zza.zzg().zza(zzbd.zza), e);
            return null;
        }
    }
}
